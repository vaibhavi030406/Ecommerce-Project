<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Little Market | Shop</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<%@ include file="fragments/header.jspf" %>
<main class="wrap">
  <section class="hero"><div><p class="eyebrow">A SMALLER, SIMPLER WAY TO SHOP</p><h1>Everyday essentials.<br><span>Thoughtfully chosen.</span></h1><p>Useful finds for your home, work and everyday routine.</p><a class="button" href="#catalog">Explore products</a></div><div class="hero-mark" aria-hidden="true">lm.</div></section>
  <c:if test="${not empty success}"><div class="notice"><c:out value="${success}"/></div></c:if>
  <section id="catalog" class="catalog">
    <div class="section-head"><div><p class="eyebrow">THE COLLECTION</p><h2>Shop all products</h2><p class="muted">Find something useful for every day.</p></div></div>
    <form class="filters" action="${pageContext.request.contextPath}/products" method="get">
      <label class="search-field"><span class="sr-only">Search products</span><input type="search" name="q" value="<c:out value='${q}'/>" placeholder="Search products, categories..."></label>
      <input type="number" name="min" min="0" step="1" placeholder="Min price ₹" value="${min}">
      <input type="number" name="max" min="0" step="1" placeholder="Max price ₹" value="${max}">
      <select name="category" aria-label="Category"><option value="">All categories</option><c:choose><c:when test="${category == 'Home'}"><option value="Home" selected>Home</option></c:when><c:otherwise><option value="Home">Home</option></c:otherwise></c:choose><c:choose><c:when test="${category == 'Accessories'}"><option value="Accessories" selected>Accessories</option></c:when><c:otherwise><option value="Accessories">Accessories</option></c:otherwise></c:choose><c:choose><c:when test="${category == 'Stationery'}"><option value="Stationery" selected>Stationery</option></c:when><c:otherwise><option value="Stationery">Stationery</option></c:otherwise></c:choose></select>
      <button class="button" type="submit">Search</button><a class="clear-link" href="${pageContext.request.contextPath}/products">Clear</a>
    </form>
    <div class="results-line"><span><c:out value="${products.size()}"/> products found</span></div>
    <div class="grid"><c:forEach var="p" items="${products}"><article class="product"><a class="product-image" href="${pageContext.request.contextPath}/product/${p.id}"><img src="${p.image}" alt="${p.name}" loading="lazy" decoding="async"></a><div class="product-info"><small><c:out value="${p.category}"/></small><h3><a href="${pageContext.request.contextPath}/product/${p.id}"><c:out value="${p.name}"/></a></h3><p class="product-description"><c:out value="${p.description}"/></p><div class="product-bottom"><strong class="price">₹<c:out value="${p.price}"/></strong><a class="view-link" href="${pageContext.request.contextPath}/product/${p.id}">View details <span>→</span></a></div><form method="post" action="${pageContext.request.contextPath}/cart/add"><input type="hidden" name="productId" value="${p.id}"><input type="hidden" name="quantity" value="1"><button class="button button-outline full" type="submit">Add to cart</button></form></div></article></c:forEach></div>
    <c:if test="${empty products}"><div class="empty"><h3>No products found</h3><p>Try a different keyword, category or price range.</p><a class="button" href="${pageContext.request.contextPath}/products">View all products</a></div></c:if>
  </section>
</main><footer>Little Market <span>·</span> Simple finds for everyday living</footer></body></html>
