# Smart Wardrobe Application

## Overview

The Smart Wardrobe application is a Kotlin-based console application designed to manage and suggest clothing combinations from a user's wardrobe. Leveraging modern programming practices and a clean, modular architecture, the application provides functionalities such as managing user profiles, handling wardrobe items, and suggesting outfits based on user preferences and other criteria.

## Features

- **User Management**: Handle user creation, authentication, and profile management.
- **Wardrobe Management**: Add, update, and remove clothing items from a digital wardrobe.
- **Outfit Suggestions**: Intelligent suggestions for outfit combinations.
- **Data Persistence**: Store and retrieve user and wardrobe data.
- **Interactive Console Interface**: Easy-to-navigate console-based UI for all functionalities.

## Architecture

The application follows the MVC (Model-View-Controller) architectural pattern:

- **Model**: Contains `Clothing`, `User`, and `Wardrobe` classes for data representation.
- **View**: `ConsoleView` and `MenuDisplay` manage user interactions and display.
- **Controller**: `UserAPI` and `WardrobeAPI` handle operations on model data.
- **Service Layer**: Includes `UserManager`, `WardrobeManager`, and `OutfitSuggester` for business logic.
- **Utilities**: `JsonUtil` for JSON operations and `LoggerUtil` for application logging.

## Setup

### Prerequisites

- Kotlin Compiler
- Java Runtime Environment

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/YongHsiangHsieh/Smart-Wardrob.git
   ```

2. Navigate to the project directory:
   ```bash
   cd smart-wardrobe
   ```

3. Compile the project:
   ```bash
   kotlinc src -include-runtime -d smart-wardrobe.jar
   ```

4. Run the application:
   ```bash
   java -jar smart-wardrobe.jar
   ```

## Usage

After launching the application, follow the on-screen prompts to interact with the system. Available options include:

- Managing user accounts
- Adding or removing clothing items
- Viewing and organizing wardrobe
- Getting outfit suggestions

## Contribution

Contributions to the Smart Wardrobe project are welcome. To contribute:

1. Fork the repository.
2. Create a feature branch: `git checkout -b new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin new-feature`
5. Submit a pull request.

## Testing

Most modules in the project are fully tested. Due to the interactive nature of `LoggerUtil`, `ScannerInput`, `MenuDisplay`, and `ConsoleView`, these modules are currently exempt from automated testing but are verified through manual testing.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Future Enhancements

### Automated Clothing ID Assignment
- **Current State**: Users manually input IDs when adding new clothing items.
- **Enhancement**: Implement an automated ID assignment system to streamline the process of adding new clothing to the wardrobe. This will improve user experience by reducing manual input and minimizing the potential for errors.

### Weather-Based Outfit Suggestion
- **Current State**: Outfit suggestions are based on user preferences and wardrobe contents without considering external factors.
- **Enhancement**: Integrate a weather API to fetch daily weather forecasts. Utilize this data to offer more context-aware outfit suggestions, such as recommending an umbrella on rainy days. This feature aims to enhance the application's practicality by adapting suggestions to daily weather conditions.

## Known Issues

### UserManagerTest Fails on Gradle Build

**Issue Description:**
- The `UserManagerTest` suite passes when executed within IntelliJ IDEA, but fails during a Gradle build.
- This inconsistency in test results is yet to be resolved.

**Impact:**
- This issue may affect the reliability of the `UserManager` functionality when the application is built using Gradle.

**Current Workarounds:**
- None at the moment. Tests are currently being executed and validated within the IntelliJ IDEA environment.

**Investigation and Help Needed:**
- We are actively investigating the root cause of this discrepancy.
- Any insights or contributions to resolve this issue would be greatly appreciated. Please refer to our [Contribution Guidelines](link-to-contribution-guidelines) for more details on how you can help.

