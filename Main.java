public class Main {

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        
        // Example product data
        String name = "Customizable Mug";
        String description = "A customizable mug that can be printed with your design.";
        double basePrice = 15.99;
        int stockQuantity = 100;
        String imageUrl = "http://example.com/mug.jpg";
        
        // Insert the product into the database
        boolean isProductInserted = productDAO.insertProduct(name, description, basePrice, stockQuantity, imageUrl);
        
        if (isProductInserted) {
            System.out.println("Product inserted successfully!");
        } else {
            System.out.println("Failed to insert product.");
        }
    }
}
