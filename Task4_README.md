# INTERNSHIP TASK-4: AI-BASED RECOMMENDATION SYSTEM

**Student:** RUTUJA KUTHE - ENTC Engineering  
**Company:** CODTECH IT SOLUTIONS  
**Task:** AI-Based Recommendation System using Java and Machine Learning Algorithms

## Project Overview
An intelligent recommendation system that suggests products to users based on their preferences and behavior using collaborative filtering algorithms. The system analyzes user ratings and finds similar users to make personalized recommendations.

## Features
- ✅ **Collaborative Filtering Algorithm** - User-based recommendation engine
- ✅ **Pearson Correlation** - Advanced similarity calculation
- ✅ **Sample Data Integration** - Pre-loaded users, products, and ratings
- ✅ **Multiple Product Categories** - Electronics, Books, Sports, Home & Kitchen
- ✅ **Personalized Recommendations** - Tailored suggestions for each user
- ✅ **Scalable Architecture** - Easy to add new users and products

## Algorithm Implementation

### Collaborative Filtering Process:
1. **User Similarity Calculation** - Uses Pearson correlation coefficient
2. **Rating Prediction** - Weighted average of similar users' ratings
3. **Recommendation Ranking** - Sorts products by predicted preference score
4. **Filtering** - Only recommends unrated products

### Mathematical Foundation:
- **Pearson Correlation**: Measures linear correlation between user preferences
- **Weighted Scoring**: Combines similar users' ratings with similarity weights
- **Threshold Filtering**: Only considers users with positive similarity

## Sample Data Structure

### Users and Ratings:
- **Alice**: Electronics enthusiast (Laptop: 5.0, Smartphone: 4.0, Headphones: 4.5)
- **Bob**: Tech & Books lover (Laptop: 4.5, Smartphone: 5.0, Java Book: 4.0)
- **Charlie**: Sports & Accessories (Headphones: 5.0, Running Shoes: 4.0, Yoga Mat: 4.5)
- **Diana**: Programming enthusiast (Java Book: 5.0, Data Science Book: 4.0)

### Product Categories:
- **Electronics**: Laptop, Smartphone, Headphones, Tablet, Wireless Mouse
- **Books**: Java Programming, Data Science
- **Sports**: Running Shoes, Yoga Mat
- **Home & Kitchen**: Coffee Maker

## How to Run

### Step 1: Compile
```bash
javac Task4_RecommendationSystem.java
```

### Step 2: Run
```bash
java Task4_RecommendationSystem
```

## Sample Output
```
=== AI-BASED RECOMMENDATION SYSTEM ===
Student: RUTUJA KUTHE - ENTC Engineering

=== SAMPLE DATA ===
Users and their ratings:
- Alice (ID: 1)
  * Laptop: 5.0
  * Smartphone: 4.0
  * Headphones: 4.5

=== GENERATING RECOMMENDATIONS ===

--- Recommendations for Alice ---
1. Wireless Mouse (Electronics) - Score: 2.85
2. Data Science Book (Books) - Score: 2.10
3. Tablet (Electronics) - Score: 1.95
```

## Technical Implementation

### Core Classes:
- **User**: Represents users with ratings and preferences
- **Product**: Represents items with categories and metadata
- **Recommendation**: Holds recommendation results with confidence scores
- **Task4_RecommendationSystem**: Main engine with collaborative filtering

### Key Methods:
- `generateRecommendations()`: Main recommendation algorithm
- `calculateUserSimilarity()`: Pearson correlation implementation
- `initializeSampleData()`: Sample data setup
- `demonstrateRecommendations()`: System demonstration

### Algorithm Complexity:
- **Time Complexity**: O(U²P) where U = users, P = products
- **Space Complexity**: O(UP) for storing user-product ratings matrix

## AI/ML Concepts Demonstrated

### 1. **Collaborative Filtering**
- User-based recommendation approach
- Finds users with similar preferences
- Predicts ratings for unrated items

### 2. **Similarity Metrics**
- Pearson correlation coefficient
- Measures linear relationship between user preferences
- Handles different rating scales effectively

### 3. **Recommendation Scoring**
- Weighted average of similar users' ratings
- Confidence-based ranking system
- Personalized recommendation lists

## Extensibility Features
- **Add New Users**: `addUser(User user)`
- **Add New Products**: `addProduct(Product product)`
- **Dynamic Ratings**: Users can add new ratings anytime
- **Category Filtering**: Can be extended for category-specific recommendations

## Real-World Applications
- **E-commerce**: Product recommendations (Amazon, eBay)
- **Streaming**: Movie/Music suggestions (Netflix, Spotify)
- **Social Media**: Content recommendations (Facebook, YouTube)
- **News**: Article recommendations (Google News)

---
**Author:** RUTUJA KUTHE - ENTC Engineering