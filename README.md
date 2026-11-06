# CampusConnect

CampusConnect is a student-focused Android app designed to help university and college communities stay connected, informed, and organized. From signing in and creating an account to browsing updates, students can use the app to keep up with campus events, announcements, and important information in a clean, modern experience.

Built with Kotlin and Jetpack Compose, CampusConnect focuses on a simple and friendly user experience while also supporting offline-first access for essential app behavior.

## Why this app exists

Campus life moves fast. Students often miss deadlines, events, and announcements because information is scattered across multiple channels. CampusConnect brings those essentials together in one place so students can:

- log in or register with their student account
- view campus updates and announcements
- access the app even when connectivity is limited
- manage app settings such as dark mode and offline mode
- enjoy a clean interface designed for everyday student use

## Main features

- Student login and registration flow
- Offline authentication support using local storage
- App-wide settings for dark mode and offline mode
- Campus dashboard with welcome section and event feed
- Modern Material 3 interface
- Jetpack Compose navigation between screens

## Tech stack

- Kotlin
- Android Jetpack
- Jetpack Compose
- Room Database
- ViewModel and StateFlow
- Material 3 Design Components

## Project structure

```text
CampusConnect/
├── app/
│   ├── src/main/java/com/example/campusconnect/
│   │   ├── data/
│   │   ├── ui/
│   │   ├── MainActivity.kt
│   │   └── ...
│   ├── build.gradle.kts
│   └── src/main/res/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
├── README.md
└── .gitignore
```

## App flow

1. Launch the app and land on the authentication flow.
2. Register a new student account or log in with an existing one.
3. Access the home dashboard after successful authentication.
4. View latest campus updates and use the settings screen to tailor the app experience.

## Getting started

### Prerequisites

- Android Studio
- JDK 11 or newer
- Android SDK configured for API 24+

### Run the app

```bash
git clone https://github.com/zuzisimelane36-star/CampusConnect.git
cd CampusConnect
./gradlew assembleDebug
```

Then open the project in Android Studio and run it on an emulator or a physical Android device.

## Screens included

- Login screen
- Registration screen
- Splash screen
- Home dashboard
- Settings screen
- Navigation flow between app sections

## Notes

This project is a student app prototype and includes core UI and local data functionality. It is a good base for extending with real backend services, live campus data, announcements, and student profiles.

## Contributing

Contributions are welcome. If you want to improve the UI, add new screens, or connect the app to a real backend, feel free to fork the project and submit a pull request.

## License

This project is currently unlicensed. If you plan to distribute or publish it, consider adding an appropriate open-source license.
