package com.example.ecommerce.controller;

import com.example.ecommerce.model.*;
import com.example.ecommerce.service.StoreService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;

@Controller
public class StoreController {
    private final StoreService store;
    public StoreController(StoreService store){this.store=store;}

    @GetMapping({"/","/products"}) public String home(@RequestParam(required=false) Double min,
        @RequestParam(required=false) Double max, @RequestParam(required=false) String category,
        @RequestParam(required=false) String q, Model model) {
        List<Product> filtered = store.getProducts().stream()
          .filter(p -> min==null || p.getPrice()>=min)
          .filter(p -> max==null || p.getPrice()<=max)
          .filter(p -> category==null || category.isBlank() || p.getCategory().equalsIgnoreCase(category))
          .filter(p -> q==null || q.isBlank() || (p.getName()+" "+p.getCategory()+" "+p.getDescription()).toLowerCase(Locale.ROOT).contains(q.trim().toLowerCase(Locale.ROOT))).toList();
        model.addAttribute("products",filtered); model.addAttribute("min",min); model.addAttribute("max",max);
        model.addAttribute("category",category); model.addAttribute("q",q); return "products";
    }
    @GetMapping("/product/{id}") public String detail(@PathVariable int id, Model model){
        Product p=store.getProduct(id).orElse(null); if(p==null)return "redirect:/products";
        model.addAttribute("product",p); return "detail";
    }
    @GetMapping("/signup") public String signup(){return "signup";}
    @PostMapping("/signup") public String doSignup(@RequestParam String username,@RequestParam String password,
        RedirectAttributes ra){
        if(!store.register(username.trim(),password)){ra.addFlashAttribute("error","Username already exists or password is too short (minimum 4 characters).");return "redirect:/signup";}
        ra.addFlashAttribute("success","Account created. Please log in."); return "redirect:/login";
    }
    @GetMapping("/login") public String login(){return "login";}
    @PostMapping("/login") public String doLogin(@RequestParam String username,@RequestParam String password,
        HttpSession session, RedirectAttributes ra){
        if(!store.authenticate(username.trim(),password)){ra.addFlashAttribute("error","Invalid username or password. Create an account first.");return "redirect:/login";}
        session.setAttribute("user",username.trim()); return "redirect:/products";
    }
    @GetMapping("/logout") public String logout(HttpSession session){session.invalidate();return "redirect:/products";}
    @PostMapping("/cart/add") public String add(@RequestParam int productId,@RequestParam(defaultValue="1") int quantity,
        HttpSession session,RedirectAttributes ra){
        if(session.getAttribute("user")==null)return "redirect:/login";
        Product p=store.getProduct(productId).orElse(null); if(p==null)return "redirect:/products";
        Map<Integer,CartItem> cart=cart(session); CartItem item=cart.get(productId);
        int safeQuantity=Math.max(1,Math.min(quantity,99));
        if(item==null)cart.put(productId,new CartItem(p,safeQuantity));else item.setQuantity(Math.min(99,item.getQuantity()+safeQuantity));
        ra.addFlashAttribute("success","Product added to your cart.");return "redirect:/cart";
    }
    @GetMapping("/cart") public String cart(HttpSession session,Model model){
        if(session.getAttribute("user")==null)return "redirect:/login";
        Collection<CartItem> items=cart(session).values(); model.addAttribute("items",items);
        model.addAttribute("total",items.stream().mapToDouble(CartItem::getSubtotal).sum()); return "cart";
    }
    @PostMapping("/cart/update") public String update(@RequestParam int productId,@RequestParam int quantity,HttpSession session){
        CartItem item=cart(session).get(productId); if(item!=null){if(quantity<=0)cart(session).remove(productId);else item.setQuantity(Math.min(quantity,99));}
        return "redirect:/cart";
    }
    @PostMapping("/cart/remove") public String remove(@RequestParam int productId,HttpSession session){cart(session).remove(productId);return "redirect:/cart";}
    @GetMapping("/checkout") public String checkout(HttpSession session,Model model){
        if(session.getAttribute("user")==null)return "redirect:/login";
        Collection<CartItem> items=cart(session).values(); if(items.isEmpty())return "redirect:/cart";
        model.addAttribute("items",items);model.addAttribute("total",items.stream().mapToDouble(CartItem::getSubtotal).sum());return "checkout";
    }
    @PostMapping("/checkout") public String placeOrder(HttpSession session,RedirectAttributes ra){
        if(session.getAttribute("user")==null)return "redirect:/login";
        Map<Integer,CartItem> c=cart(session);if(c.isEmpty())return "redirect:/cart";
        c.clear();ra.addFlashAttribute("success","Demo order placed successfully! No real payment was processed.");return "redirect:/products";
    }
    @SuppressWarnings("unchecked") private Map<Integer,CartItem> cart(HttpSession s){
        Map<Integer,CartItem> c=(Map<Integer,CartItem>)s.getAttribute("cart");
        if(c==null){c=new LinkedHashMap<>();s.setAttribute("cart",c);}return c;
    }
}
