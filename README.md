# Little Market — Java Spring Boot E-Commerce Demo

A simple, responsive e-commerce learning project built with **Java 17, Spring Boot 2.7, Spring MVC, JSP, JSTL, HTML and CSS**. It runs locally in VS Code and does not require MySQL.

## Features

- Minimal, responsive storefront and product cards
- Product search by name, category or description
- Filter products by category and price range
- Product details page with real product photography
- Demo signup and login
- Session-based shopping cart
- Add products, update quantities and remove items
- Automatic cart subtotal calculation
- Simulated checkout (no payment gateway)

## Requirements

- JDK 17
- Apache Maven 3.6.3 or later
- Visual Studio Code
- Recommended VS Code extension: Extension Pack for Java

## Run in VS Code (Windows)

1. Extract the ZIP file.
2. Open VS Code and select **File → Open Folder**.
3. Select the `java-ecommerce-no-mysql` folder (the folder containing `pom.xml`).
4. Open **Terminal → New Terminal**.
5. Verify Java and Maven are installed:

   ```powershell
   java -version
   mvn -version
   ```

6. Start the application:

   ```powershell
   mvn spring-boot:run
   ```

7. When the terminal reports that the application has started, open:

   **http://localhost:8080**

To stop the application, press **Ctrl + C** in the terminal where it is running.

### If port 8080 is already in use

In Windows Command Prompt, find the process ID:

```cmd
netstat -ano | findstr :8080
```

Stop only the process using that port (replace `PID` with the number shown):

```cmd
taskkill /PID PID /F
```

## Try the application

1. Choose **Create account** and register a demo username and password (minimum 4 characters).
2. Sign in.
3. Search for products or use the category/price filters.
4. Open a product and add it to your cart.
5. Update a quantity or remove an item.
6. Continue to checkout and place a demo order.

## Project structure

```text
java-ecommerce-no-mysql/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/example/ecommerce/
        │   ├── EcommerceApplication.java
        │   ├── controller/StoreController.java
        │   ├── model/CartItem.java
        │   ├── model/Product.java
        │   └── service/StoreService.java
        ├── resources/
        │   ├── application.properties
        │   └── static/css/style.css
        └── webapp/WEB-INF/jsp/
            ├── fragments/header.jspf
            ├── products.jsp
            ├── detail.jsp
            ├── login.jsp
            ├── signup.jsp
            ├── cart.jsp
            └── checkout.jsp
```

## Product images

The six sample products use real product/lifestyle photographs served from the Unsplash image CDN. The mug photo URL has been corrected, and the images use optimized crop/size parameters for the product cards, detail page and cart. An internet connection is required for these photographs to load. If a photo does not appear, check your internet connection or replace the image URL in `StoreService.java` with another image URL you are permitted to use.

Photo sources: [Tote bag](https://images.unsplash.com/photo-1590874103328-eac38a683ce7), [ceramic mug](https://images.unsplash.com/photo-1514228742587-6b1558fcca3d), [desk lamp](https://images.unsplash.com/photo-1507473885765-e6ed057f782c), [backpack](https://images.unsplash.com/photo-1553062407-98eeb64c6a62), [plant](https://images.unsplash.com/photo-1485955900006-10f4d324d411), and [notebook](https://images.unsplash.com/photo-1531346878377-a5be20888e57).

## Important demo limitations

- **No database is configured.** Products are sample data in Java. Demo accounts are stored in application memory and are cleared when the app restarts. Cart data is stored in the HTTP session.
- Passwords are stored as plain text in memory in this educational starter. Do not use real passwords or deploy this version publicly.
- Login is a basic demo flow, not production-grade authentication or authorization.
- Checkout is simulated. It does not collect payment information, create a persistent order or process a real payment.
- Product photos are loaded from Unsplash and need an internet connection. The shopping features do not depend on the photos.

## GitHub upload note

Do not commit generated `target/` files or real credentials. The included `.gitignore` excludes Maven build output and common IDE files.
