# Smart Wardrobe System

## Introduction
The Smart Wardrobe System is a console-based application developed in Kotlin, designed to manage and suggest clothing options based on various criteria such as user preferences and weather conditions. This system aims to revolutionize the way individuals interact with their wardrobe, offering a user-friendly and intelligent approach to outfit selection.

## Features
- **Wardrobe Management:** Add, remove, and organize clothing items in your virtual wardrobe.
- **User Profile Management:** Create and manage user profiles with personal preferences and sizes.
- **Outfit Suggestions:** Get clothing suggestions based on weather and personal style.
- **Weather Integration:** Receive weather-based outfit recommendations.
- **Data Persistence:** Store and retrieve user and wardrobe data efficiently.

## Installation
To install and run the Smart Wardrobe System, follow these steps:
1. Ensure you have Kotlin and Java installed on your system.
2. Clone the repository: `git clone https://github.com/YongHsiangHsieh/Smart-Wardrob.git`.
3. Navigate to the project directory: `cd smart-wardrobe`.
4. Compile the project: `kotlinc -include-runtime -d smart-wardrobe.jar src/*.kt`.
5. Run the application: `java -jar smart-wardrobe.jar`.

## Usage
The application is operated via a console interface. Follow the on-screen instructions to interact with the system.

### Main Menu Options
1. **Manage Wardrobe:** Add or remove clothing items.
2. **Manage Profile:** Create or update your user profile.
3. **Get Outfit Suggestions:** Receive outfit recommendations.
4. **Exit:** Quit the application.

## Code Structure
- `Clothing.kt`: Defines the clothing item data structure.
- `User.kt`: Manages user data structures and profiles.
- `Wardrobe.kt`: Core logic for wardrobe management.
- `OutfitSuggester.kt`: Algorithm for suggesting outfits.
- Additional utility files (`JsonUtil.kt`, `YamlUtil.kt`, etc.) support various functionalities like data handling and input processing.
- 
### Testing Coverage
- **Comprehensive Testing**: Each class, with the exception of `WeatherAPI`, `LoggerUtil`, `ScannerInput`, `YamlUtil`, `ConsoleView`, and `MenuDisplay`, is covered with unit tests to ensure reliability and stability.
- **Continuous Integration**: Automated testing routines are integrated for continuous quality assurance.
  
## Contributing
We welcome contributions to the Smart Wardrobe System. Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License
This project is licensed under the [MIT License](LICENSE.txt) - see the LICENSE file for details.

## Contact
For any queries or contributions, please contact Yong-Hsiang Hsieh at wilsonhsieh1216@gmail.com.

## Acknowledgements
- Weather API integration for real-time data.
- Kotlin community for continuous support.
