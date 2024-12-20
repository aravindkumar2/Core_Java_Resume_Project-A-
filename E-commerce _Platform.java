import java.util.*;

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class ECommercePlatform {
    private static List<Product> products = new ArrayList<>();
    private static Map<Product, Integer> cart = new HashMap<>();

    public static void main(String[] args) {
        products.add(new Product(1, "Laptop", 800.0));
        products.add(new Product(2, "Smartphone", 500.0));
        products.add(new Product(3, "Headphones", 50.0));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. View Products\n2. Add to Cart\n3. Remove from Cart\n4. View Cart\n5. Checkout\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> addToCart(scanner);
                case 3 -> removeFromCart(scanner);
                case 4 -> viewCart();
                case 5 -> checkout();
                case 6 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void viewProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println(product.id + ". " + product.name + " - $" + product.price);
        }
    }

    private static void addToCart(Scanner scanner) {
        System.out.print("\nEnter Product ID to add to cart: ");
        int productId = scanner.nextInt();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.id == productId) {
                cart.put(product, cart.getOrDefault(product, 0) + 1);
                System.out.println("Added " + product.name + " to cart.");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void removeFromCart(Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty. Nothing to remove.");
            return;
        }

        System.out.println("\nYour Cart:");
        int index = 0;
        List<Product> cartProducts = new ArrayList<>(cart.keySet());
        for (int i = 0; i < cartProducts.size(); i++) {
            Product product = cartProducts.get(i);
            System.out.println((i + 1) + ". " + product.name + " x" + cart.get(product) + " - $" + (product.price * cart.get(product)));
        }

        System.out.print("Enter the number of the product to remove: ");
        int productIndex = scanner.nextInt();

        if (productIndex > 0 && productIndex <= cartProducts.size()) {
            Product productToRemove = cartProducts.get(productIndex - 1);
            int currentQuantity = cart.get(productToRemove);

            if (currentQuantity > 1) {
                cart.put(productToRemove, currentQuantity - 1);
                System.out.println("Decreased quantity of " + productToRemove.name + " in the cart.");
            } else {
                cart.remove(productToRemove);
                System.out.println("Removed " + productToRemove.name + " from the cart.");
            }
        } else {
            System.out.println("Invalid product number. Try again.");
        }
    }

    private static void viewCart() {
        System.out.println("\nYour Cart:");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            List<Product> cartProducts = new ArrayList<>(cart.keySet());
            for (int i = 0; i < cartProducts.size(); i++) {
                Product product = cartProducts.get(i);
                System.out.println(product.name + " x" + cart.get(product) + " - $" + (product.price * cart.get(product)));
            }
        }
    }

    private static void checkout() {
        System.out.println("\nCheckout:");
        double total = 0;
        List<Product> cartProducts = new ArrayList<>(cart.keySet());
        for (int i = 0; i < cartProducts.size(); i++) {
            Product product = cartProducts.get(i);
            total += product.price * cart.get(product);
        }
        System.out.println("Total: $" + total);
        System.out.println("Thank you for your purchase!");
        cart.clear();
    }
}
