import java.util.*;
import java.util.stream.Collectors;

/**
 * INTERNSHIP TASK-4: AI-BASED RECOMMENDATION SYSTEM
 * Student: RUTUJA KUTHE - ENTC Engineering
 * Company: CODTECH IT SOLUTIONS
 * Recommendation System using Collaborative Filtering Algorithm
 */

// User class to represent users
class User {
    private int userId;
    private String userName;
    private Map<Integer, Double> ratings;
    
    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.ratings = new HashMap<>();
    }
    
    public void addRating(int productId, double rating) {
        ratings.put(productId, rating);
    }
    
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public Map<Integer, Double> getRatings() { return ratings; }
}

// Product class to represent products
class Product {
    private int productId;
    private String productName;
    private String category;
    
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    
    @Override
    public String toString() {
        return productName + " (" + category + ")";
    }
}

// Recommendation class to hold recommendation results
class Recommendation {
    private Product product;
    private double score;
    
    public Recommendation(Product product, double score) {
        this.product = product;
        this.score = score;
    }
    
    public Product getProduct() { return product; }
    public double getScore() { return score; }
    
    @Override
    public String toString() {
        return String.format("%s - Score: %.2f", product.toString(), score);
    }
}

// Main Recommendation Engine
public class Task4_RecommendationSystem {
    private List<User> users;
    private List<Product> products;
    
    public Task4_RecommendationSystem() {
        this.users = new ArrayList<>();
        this.products = new ArrayList<>();
        initializeSampleData();
    }
    
    public static void main(String[] args) {
        System.out.println("=== AI-BASED RECOMMENDATION SYSTEM ===");
        System.out.println("Student: RUTUJA KUTHE - ENTC Engineering\n");
        
        Task4_RecommendationSystem system = new Task4_RecommendationSystem();
        system.demonstrateRecommendations();
    }
    
    private void initializeSampleData() {
        // Initialize products
        products.add(new Product(1, "Laptop", "Electronics"));
        products.add(new Product(2, "Smartphone", "Electronics"));
        products.add(new Product(3, "Headphones", "Electronics"));
        products.add(new Product(4, "Book - Java Programming", "Books"));
        products.add(new Product(5, "Book - Data Science", "Books"));
        products.add(new Product(6, "Coffee Maker", "Home & Kitchen"));
        products.add(new Product(7, "Running Shoes", "Sports"));
        products.add(new Product(8, "Yoga Mat", "Sports"));
        products.add(new Product(9, "Tablet", "Electronics"));
        products.add(new Product(10, "Wireless Mouse", "Electronics"));
        
        // Initialize users with ratings
        User user1 = new User(1, "Alice");
        user1.addRating(1, 5.0); // Laptop
        user1.addRating(2, 4.0); // Smartphone
        user1.addRating(3, 4.5); // Headphones
        user1.addRating(4, 3.0); // Java Book
        user1.addRating(6, 2.0); // Coffee Maker
        
        User user2 = new User(2, "Bob");
        user2.addRating(1, 4.5); // Laptop
        user2.addRating(2, 5.0); // Smartphone
        user2.addRating(4, 4.0); // Java Book
        user2.addRating(5, 4.5); // Data Science Book
        user2.addRating(9, 3.5); // Tablet
        
        User user3 = new User(3, "Charlie");
        user3.addRating(3, 5.0); // Headphones
        user3.addRating(7, 4.0); // Running Shoes
        user3.addRating(8, 4.5); // Yoga Mat
        user3.addRating(6, 3.0); // Coffee Maker
        user3.addRating(10, 4.0); // Wireless Mouse
        
        User user4 = new User(4, "Diana");
        user4.addRating(1, 4.0); // Laptop
        user4.addRating(3, 4.5); // Headphones
        user4.addRating(4, 5.0); // Java Book
        user4.addRating(5, 4.0); // Data Science Book
        user4.addRating(10, 3.5); // Wireless Mouse
        
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }
    
    public void demonstrateRecommendations() {
        System.out.println("=== SAMPLE DATA ===");
        displayUsers();
        displayProducts();
        
        System.out.println("\n=== GENERATING RECOMMENDATIONS ===");
        
        // Generate recommendations for each user
        for (User user : users) {
            System.out.println("\n--- Recommendations for " + user.getUserName() + " ---");
            List<Recommendation> recommendations = generateRecommendations(user, 3);
            
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations available.");
            } else {
                for (int i = 0; i < recommendations.size(); i++) {
                    System.out.println((i + 1) + ". " + recommendations.get(i));
                }
            }
        }
        
        System.out.println("\n=== RECOMMENDATION SYSTEM COMPLETED ===");
    }
    
    private void displayUsers() {
        System.out.println("Users and their ratings:");
        for (User user : users) {
            System.out.println("- " + user.getUserName() + " (ID: " + user.getUserId() + ")");
            for (Map.Entry<Integer, Double> entry : user.getRatings().entrySet()) {
                Product product = getProductById(entry.getKey());
                System.out.println("  * " + product.getProductName() + ": " + entry.getValue());
            }
        }
    }
    
    private void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.println("- " + product.toString() + " (ID: " + product.getProductId() + ")");
        }
    }
    
    public List<Recommendation> generateRecommendations(User targetUser, int numRecommendations) {
        Map<Integer, Double> productScores = new HashMap<>();
        
        // Find similar users using collaborative filtering
        for (User otherUser : users) {
            if (otherUser.getUserId() == targetUser.getUserId()) continue;
            
            double similarity = calculateUserSimilarity(targetUser, otherUser);
            if (similarity > 0) {
                // Add weighted ratings from similar users
                for (Map.Entry<Integer, Double> entry : otherUser.getRatings().entrySet()) {
                    int productId = entry.getKey();
                    double rating = entry.getValue();
                    
                    // Only recommend products the target user hasn't rated
                    if (!targetUser.getRatings().containsKey(productId)) {
                        productScores.put(productId, 
                            productScores.getOrDefault(productId, 0.0) + (similarity * rating));
                    }
                }
            }
        }
        
        // Convert to recommendations and sort by score
        return productScores.entrySet().stream()
            .map(entry -> new Recommendation(getProductById(entry.getKey()), entry.getValue()))
            .sorted((r1, r2) -> Double.compare(r2.getScore(), r1.getScore()))
            .limit(numRecommendations)
            .collect(Collectors.toList());
    }
    
    private double calculateUserSimilarity(User user1, User user2) {
        Set<Integer> commonProducts = new HashSet<>(user1.getRatings().keySet());
        commonProducts.retainAll(user2.getRatings().keySet());
        
        if (commonProducts.isEmpty()) return 0.0;
        
        // Calculate Pearson correlation coefficient
        double sum1 = 0, sum2 = 0, sum1Sq = 0, sum2Sq = 0, pSum = 0;
        int n = commonProducts.size();
        
        for (int productId : commonProducts) {
            double rating1 = user1.getRatings().get(productId);
            double rating2 = user2.getRatings().get(productId);
            
            sum1 += rating1;
            sum2 += rating2;
            sum1Sq += rating1 * rating1;
            sum2Sq += rating2 * rating2;
            pSum += rating1 * rating2;
        }
        
        double numerator = pSum - (sum1 * sum2 / n);
        double denominator = Math.sqrt((sum1Sq - sum1 * sum1 / n) * (sum2Sq - sum2 * sum2 / n));
        
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }
    
    private Product getProductById(int productId) {
        return products.stream()
            .filter(p -> p.getProductId() == productId)
            .findFirst()
            .orElse(null);
    }
    
    // Additional utility methods for system interaction
    public void addUser(User user) {
        users.add(user);
    }
    
    public void addProduct(Product product) {
        products.add(product);
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public List<Product> getProducts() {
        return products;
    }
}