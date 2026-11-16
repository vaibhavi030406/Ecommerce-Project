package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StoreService {
    private final List<Product> products = List.of(
      new Product(1,"Classic Cotton Tote","Accessories","Reusable everyday cotton tote bag.",399,"https://images.unsplash.com/photo-1590874103328-eac38a683ce7?auto=format&fit=crop&w=900&q=85"),
      new Product(2,"Ceramic Coffee Mug","Home","Hand-finished ceramic mug for daily coffee.",249,"https://images.unsplash.com/photo-1514228742587-6b1558fcca3d?auto=format&fit=crop&w=900&q=85"),
      new Product(3,"Minimal Desk Lamp","Home","A clean, modern lamp for your workspace.",899,"https://images.unsplash.com/photo-1507473885765-e6ed057f782c?auto=format&fit=crop&w=900&q=85"),
      new Product(4,"Canvas Backpack","Accessories","Lightweight backpack for work and travel.",1199,"https://images.unsplash.com/photo-1553062407-98eeb64c6a62?auto=format&fit=crop&w=900&q=85"),
      new Product(5,"Plant Pot Set","Home","Simple planters to brighten your room.",549,"https://images.unsplash.com/photo-1485955900006-10f4d324d411?auto=format&fit=crop&w=900&q=85"),
      new Product(6,"Notebook Set","Stationery","Three notebooks for notes and ideas.",199,"https://images.unsplash.com/photo-1531346878377-a5be20888e57?auto=format&fit=crop&w=900&q=85")
    );
    private final Map<String,String> users = new ConcurrentHashMap<>();
    public List<Product> getProducts(){return products;}
    public Optional<Product> getProduct(int id){return products.stream().filter(p->p.getId()==id).findFirst();}
    public synchronized boolean register(String username, String password){
        if(username==null || username.isBlank() || password==null || password.length()<4 || users.containsKey(username.toLowerCase())) return false;
        users.put(username.toLowerCase(), password); return true;
    }
    public boolean authenticate(String username,String password){
        return username!=null && password!=null && password.equals(users.get(username.toLowerCase()));
    }
}
