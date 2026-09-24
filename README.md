# CampusConnect

CampusConnect is an Android app prototype for helping students keep up with what is happening around campus. The app brings a simple campus feed, local student accounts, and a few data-saving settings together in one place.

This project is being built with Kotlin and Jetpack Compose. It is currently a working prototype rather than a production-ready campus platform, but the main app flow is in place and can be run locally from Android Studio.

## What the app includes

- A short splash screen when the app starts
- Login and registration screens
- Local student account storage using Room
- A home screen with campus announcements and events
- A settings screen with:
  - Dark Mode
  - Offline Mode
  - Download over Wi-Fi only
  - Clear Downloads action placeholder
- Navigation between the login, registration, home, and settings screens
- Logout back to the login screen

## Technology used

- **Kotlin**
- **Jetpack Compose** and Material 3 for the interface
- **Navigation Compose** for screen navigation
- **Room** for local student data
- **KSP** for Room code generation
- **Android Gradle Plugin**
- **Gradle version catalogs** for dependency management

## Getting started

### Requirements

Install the following before opening the project:

- Android Studio with an Android SDK
- A JDK supported by your Android Studio installation
- An Android emulator or a physical Android device

The project currently uses:

- `minSdk`: 24
- `targetSdk`: 37
- Java source and target compatibility: 11

### Run the app in Android Studio

1. Open the project folder in Android Studio.
2. Allow Gradle to sync and download the project dependencies.
3. Select an emulator or connect an Android phone with USB debugging enabled.
4. Click **Run** and choose the `app` configuration.

For a reliable local test, a Pixel emulator is a good choice. If the emulator is slow or unstable, reduce its memory use or test on a physical device.

### Build from the command line

From the project root on Windows, run:

```powershell
.\gradlew.bat assembleDebug
```

The debug APK will be created at:

```text
app\build\outputs\apk\debug\app-debug.apk
```

To run the unit tests:

```powershell
.\gradlew.bat testDebugUnitTest
```

## How the app works

When the app opens, the splash screen takes the user to the login page. From there, a user can either log in or create a local account. Successful authentication takes the user to the home feed.

The home screen shows example campus updates such as events, workshops, sports, and student meetings. The settings icon in the bottom navigation opens the settings page, where the user can change local display and connectivity preferences. The logout icon in the top bar returns the user to the login page.

Accounts are stored locally on the device. This means the current login system is intended for demonstration and offline testing; it is not connected to a remote university account system or cloud authentication service.

## Project structure

```text
app/src/main/java/com/example/campusconnect/
├── data/          Room database, entities, DAO, and repository
├── ui/auth/       Shared authentication state
├── ui/home/       Campus feed and home screen
├── ui/login/      Login screen
├── ui/navigation/ Navigation routes and screen flow
├── ui/registration/ Registration screen
├── ui/settings/   Settings screen and preferences state
├── ui/splash/     Startup splash screen
└── ui/theme/      Compose colors, typography, and theme
```

## Testing with Appetize.io

Appetize.io can run the debug APK in a browser. First build the APK using the command above, then upload this file to Appetize:

```text
app\build\outputs\apk\debug\app-debug.apk
```

Every time the source code changes, build a new APK and upload the new file. Appetize does not automatically receive changes made in the local Android Studio project.

## Current limitations

CampusConnect is still under development. In particular:

- The authentication data is local to the device and is not suitable for real account security.
- The event feed currently uses sample content.
- Notifications, profile navigation, adding posts, and clearing downloaded files are placeholders.
- The app has been tested on an Android emulator, but emulator performance can vary depending on the host computer.
- A complete end-to-end demonstration should be manually checked after installing the latest APK, especially the registration and login flow.

## Next steps

The natural next improvements are connecting the app to a real backend, adding proper authentication and password protection, making campus content dynamic, and completing the profile, notifications, posts, and downloads features.

## License

No license has been selected for this project yet. Until one is added, please treat the source code as project-owned and ask the author before redistributing it.
