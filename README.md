# Helyxon Store App Task

This is an Android application that displays a list of products fetched from a server. The app includes features like product sorting, search functionality, and product detail view. The code uses `ViewModel`, `LiveData`, `Retrofit`, and `Picasso` for fetching and displaying the data.

## Features

- Display a list of products fetched from the server
- View detailed product information (including image, price, description, and category)
- Sort products by price (Low to High / High to Low)
- Search products by name
- Handle no internet connection scenario with proper UI feedback
- Error handling for fetching products and displaying appropriate UI feedback

## Prerequisites

- Android Studio (latest version recommended)
- Android SDK
- Gradle

## Installation & Setup

### 1️⃣ Clone the Repository

```bash
git clone https://IbrahimAndroidDev0503/HelyxonStoreApp.git

2️⃣ Open the Project in Android Studio
Launch Android Studio.
Click "Open an Existing Project" and select the folder where the project is located.
Wait for the Gradle Sync to complete (this may take a few minutes).
How to Build and Run the Project
▶️ Using an Emulator (Recommended)
Click on AVD Manager in the Android Studio toolbar.
Create a new Virtual Device (e.g., Pixel 5, or any device of your choice).
Select a system image (API Level 30 or higher is recommended).
Click "Run" (▶️) to start the emulator.
▶️ Using a Physical Device
Enable Developer Mode on your Android phone.
Enable USB Debugging in Developer Settings.
Connect your phone to your computer via USB cable and grant debugging access.
Select your device in the "Run Configurations" dropdown in Android Studio and click "Run" (▶️).
Verify the App is Running
The product list should load successfully from the server.
Clicking on a product should display detailed information about it (image, description, price, and category).
Use the search functionality to find specific products.
Sort products by price and verify that the list changes accordingly.
Try the "Order" functionality (A Toast message should appear confirming the order).

#Tech Stack
Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
Networking: Retrofit,Coroutines
Image Loading: Picasso
State Management: LiveData, ViewModel
