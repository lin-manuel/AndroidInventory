# AndroidInventory

AndroidInventory is an Android inventory management app built with Kotlin and Jetpack Compose. The app is branded in the UI as a mall/store inventory tool and includes authentication, product CRUD screens, image upload support through Cloudinary, and product storage through Firebase Realtime Database.

The current app package is `com.were.myfirstapp`.

## Features

- Splash screen with automatic routing based on Firebase authentication state.
- Welcome screen with registration and login entry points.
- Email/password sign up and login using Firebase Authentication.
- User profile records saved under the `Users` node in Firebase Realtime Database.
- Dashboard with quick actions for viewing products, adding products, and opening a Safaricom-style payments mock screen.
- Product inventory list loaded from Firebase Realtime Database.
- Add product flow with product name, category, price, quantity, description field, and image picker.
- Product image upload to Cloudinary using an unsigned upload preset.
- Product update flow with existing image preview and optional replacement image.
- Product delete flow from Firebase Realtime Database.
- Coil image loading for remote product images.
- Additional sample/demo screens for Android system intents, a calculator, and a Safaricom/M-PESA inspired interface.

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Firebase Authentication
- Firebase Realtime Database
- Cloudinary image upload
- Coil 3 for image loading
- Retrofit and OkHttp
- Gradle Kotlin DSL

## Requirements

- Android Studio with support for Android Gradle Plugin 9.x.
- JDK 11 or newer.
- Android SDK with compile SDK 36 installed.
- A Firebase project with Authentication and Realtime Database enabled.
- A Cloudinary account with an unsigned upload preset.

The project uses the Gradle wrapper, so you do not need to install Gradle separately.

## Project Structure

```text
.
├── app/
│   ├── build.gradle.kts
│   ├── google-services.json
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/were/myfirstapp/
│       │   │   ├── MainActivity.kt
│       │   │   ├── data/
│       │   │   │   ├── AuthViewModel.kt
│       │   │   │   └── ProductViewModel.kt
│       │   │   ├── model/
│       │   │   │   ├── CloudinaryResponse.kt
│       │   │   │   ├── Product.kt
│       │   │   │   └── User.kt
│       │   │   ├── navigation/
│       │   │   │   ├── AppNavHost.kt
│       │   │   │   └── Routes.kt
│       │   │   ├── network/
│       │   │   │   └── CloudinaryApi.kt
│       │   │   └── ui/theme/screens/
│       │   │       ├── calc/
│       │   │       ├── dash/
│       │   │       ├── home/
│       │   │       ├── intent/
│       │   │       ├── login/
│       │   │       ├── products/
│       │   │       ├── register/
│       │   │       ├── safaricom/
│       │   │       └── splash/
│       │   └── res/
│       ├── androidTest/
│       └── test/
├── build.gradle.kts
├── gradle/libs.versions.toml
├── gradle/wrapper/gradle-wrapper.properties
└── settings.gradle.kts
```

## Navigation Flow

The main Compose navigation graph is defined in `AppNavHost.kt`.

Routes currently include:

- `splash`
- `home`
- `Register`
- `login`
- `dashboard`
- `intent`
- `safaricom`
- `add_product`
- `view_products`
- `update_product/{id}`

The app starts at the splash screen. After a short delay, it checks whether a Firebase user is already signed in:

- Signed-in users go to the dashboard.
- Signed-out users go to the home screen.

## Firebase Setup

1. Create a Firebase project.
2. Add an Android app using the package name:

   ```text
   com.were.myfirstapp
   ```

3. Download `google-services.json`.
4. Place it at:

   ```text
   app/google-services.json
   ```

5. Enable Email/Password authentication in Firebase Authentication.
6. Enable Firebase Realtime Database.

The app writes data to these database nodes:

```text
Users/{userId}
Products/{productId}
```

Example product shape:

```json
{
  "id": "firebase-generated-key",
  "productname": "Smart Watch",
  "productcategory": "Electronics",
  "productprice": "2500",
  "productquantity": "12",
  "imageurl": "https://res.cloudinary.com/..."
}
```

## Cloudinary Setup

Product images are uploaded to Cloudinary from `ProductViewModel.kt`.

Current values in the project:

```kotlin
val cloudinaryurl = "https://api.cloudinary.com/v1_1/dpb55u4fy/image/upload"
val uploadPreset = "app_image"
```

To use your own Cloudinary account:

1. Create or open a Cloudinary account.
2. Create an unsigned upload preset.
3. Replace the Cloudinary cloud name in the upload URL.
4. Replace `uploadPreset` with your unsigned preset name.

For production apps, avoid hardcoding service configuration directly in source code. Prefer Gradle build config fields, local properties, or a secure backend upload flow.

## Android Permissions

The manifest declares:

```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.INTERNET" />
```

The current call action uses `Intent.ACTION_DIAL`, which opens the dialer and does not directly place a phone call. Internet access is required for Firebase, Cloudinary, and image loading.

## Getting Started

Clone or open the project in Android Studio, then sync Gradle.

From the command line:

```bash
./gradlew tasks
```

Build a debug APK:

```bash
./gradlew assembleDebug
```

Install on a connected device or emulator:

```bash
./gradlew installDebug
```

Run local unit tests:

```bash
./gradlew test
```

Run instrumented Android tests on a connected device or emulator:

```bash
./gradlew connectedAndroidTest
```

## Main Screens

### Splash

Displays the app logo and tagline, waits briefly, then routes users based on Firebase authentication state.

### Home

Shows the welcome experience and links users to registration or login.

### Register

Creates a Firebase Authentication account and saves the user's name, email, password fields, and Firebase user id to Realtime Database.

### Login

Signs in an existing Firebase Authentication user and routes to the dashboard.

### Dashboard

Provides quick access to product management actions, a search field UI, logout, and sample recent activity cards.

### Add Product

Lets users pick an image, enter product details, upload the image to Cloudinary, and save the product record to Firebase.

### View Products

Loads products from Firebase and displays each item with its image, name, category, price, quantity, edit action, and delete action.

### Update Product

Loads a selected product by id, pre-fills the edit form, optionally uploads a new image, and updates the Firebase record.

### Safaricom

A Compose UI mockup inspired by Safaricom/M-PESA wallet actions. It is currently a UI/demo screen and does not process real payments.

### Intent

A sample screen demonstrating Android intents for SMS, dialer, camera, share, email, and SIM Toolkit launch.

### Calculator

A simple Compose calculator demo for addition, subtraction, multiplication, and division.

## Testing

The project currently includes starter tests:

- `ExampleUnitTest.kt` checks a basic JVM assertion.
- `ExampleInstrumentedTest.kt` checks that the app context package is `com.were.myfirstapp`.

Consider adding tests for:

- Authentication validation.
- Product mapping from Firebase snapshots.
- Navigation routing.
- Product add/update/delete behavior.
- Compose UI behavior for key screens.

## Implementation Notes

- The add product screen contains a description input, but the current product model and Firebase save payload do not persist a description value.
- `CloudinaryApi.kt` defines a Retrofit interface, but `ProductViewModel` currently uploads images using a direct OkHttp request instead.
- `ViewProductsScreen` shows a loading spinner when the products list is empty, so an actual empty inventory and a loading state currently look the same.
- Registration currently stores password and confirm-password values in the Realtime Database user record. For real applications, only Firebase Authentication should handle passwords.
- The Safaricom screen contains static demo balances and actions.
- The dashboard search field is present in the UI but is not currently wired to filter products.

## Security Notes

- Do not commit production Firebase configuration or secrets to a public repository.
- Use Firebase Realtime Database security rules to restrict reads and writes to authorized users.
- Avoid storing user passwords in Realtime Database.
- Prefer a backend-mediated Cloudinary upload flow for production applications.
- Keep unsigned Cloudinary presets limited in scope.

## License

No license file is currently included. Add a license before distributing or publishing the project.
# AndroidInventory
