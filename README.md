# Smart Wardrob

## Introduction

Welcome to Smart-Wardrob, a unique Kotlin-based application designed to revolutionize the way you manage your wardrobe. In today's fast-paced world, keeping track of your clothing and choosing the right outfit for every occasion can be a challenge. That's where Smart-Wardrob] steps in - to simplify and enhance your wardrobe management experience.

This application serves as an all-in-one solution for users to efficiently manage their clothing items. With features like user account management, wardrobe customization, and intelligent outfit suggestions based on seasonal trends, Smart-Wardrob is not just a utility; it's your personal wardrobe assistant. Whether you're a fashion enthusiast or someone who values organization and ease, our application caters to your needs.

Developed with robust Kotlin programming, Smart-Wardrob offers a seamless and interactive experience, ensuring that managing your wardrobe is both enjoyable and efficient. From adding new clothing items to getting outfit suggestions tailored to the current season, our application simplifies these tasks with intuitive interfaces and advanced features.

Join us on this journey to transform how you interact with your wardrobe. With Smart-Wardrob, prepare to step into a world where style meets technology, leading to a smarter, more organized wardrobe experience.

### Overview
This Kotlin program represents the culmination of sprint two in a series of development sprints. The program's primary focus is on functionality, laying the groundwork for a robust and interactive application. It's structured in a manner that separates concerns, adhering to MVC (Model-View-Controller) principles.

### Components
- **UserAPI & WardrobeAPI (Controllers)**: Handle the business logic related to users and wardrobes. They act as intermediaries between the model and the view, processing user input and manipulating data models.
- **Clothing, User, Wardrobe (Models)**: Represent the core data structures of the application. Each class is designed with serialization in mind, facilitating ease of data storage and retrieval.
- **PersistenceManager**: Manages data persistence, ensuring that user data is stored and retrieved efficiently.
- **OutfitSuggester (Service)**: Provides logic for suggesting outfits, a key feature of the application. It utilizes clothing and wardrobe data to generate suitable outfit recommendations.
- **JsonUtil (Utility)**: A utility class for handling JSON operations, crucial for the serialization and deserialization of model objects.
- **ScannerInput (Utility)**: Handles console input, allowing for user interaction with the application.
- **ConsoleView (View)**: Manages the user interface, presenting data and options to the user and capturing their inputs.

### Current Functionality (As of Sprint Two)
- Basic data models for users, clothing, and wardrobes.
- Controllers to manage user and wardrobe data.
- Basic outfit suggestion logic.
- Console-based user interaction.
- Basic persistence layer for data storage and retrieval.

### Future Developments (Planned for Next Sprints)
- Comprehensive testing and optimization of the code.
- Advanced features in outfit suggestions based on user preferences and external factors like weather.
- Enhanced user interface for a more engaging user experience.
- Additional functionalities in the persistence layer for better data management.

### Setup and Execution
To set up and run the program:
1. Ensure you have Kotlin and a compatible IDE (like IntelliJ IDEA) installed.
2. Clone the repository or download the source code.
3. Open the project in your IDE and resolve any dependencies.
4. Run the program from the main entry point (Main.kt, if available).
