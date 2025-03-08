Helyxon Store App
The Helyxon Store App is an Android application that fetches and displays a list of products from a server. It allows users to interact with the product data by searching, sorting, and viewing detailed product information. The app features robust error handling and feedback mechanisms, including handling no internet connection scenarios.

Features
Display a List of Products: Fetches product data from a server and displays it in a list.
Product Detail View: Users can view detailed product information including the image, price, description, and category.
Sort Products by Price: Allows sorting of products from Low to High or High to Low based on price.
Search Products: Users can search products by name.
No Internet Connection Handling: Displays appropriate feedback when the internet is not available.
Error Handling: Displays UI feedback for errors during product fetching.
Prerequisites
Before starting, ensure that you have the following installed:

Android Studio (latest version recommended)
Android SDK
Gradle
Installation & Setup
1. Clone the Repository
To get started, clone the repository to your local machine:
git clone https://github.com/IbrahimAndroidDev0503/HelyxonStoreApp.git
2. Open the Project in Android Studio
Launch Android Studio.
Click on "Open an Existing Project" and select the folder where the project was cloned.
Wait for Gradle Sync to complete (this might take a few minutes).
How to Build and Run the Project
Running on an Emulator (Recommended)
Click on AVD Manager in the Android Studio toolbar.
Create a new Virtual Device (e.g., Pixel 5 or another device of your choice).
Select a system image (API Level 30 or higher is recommended).
Click Run (▶️) to start the emulator.
Running on a Physical Device
Enable Developer Mode on your Android phone.
Enable USB Debugging in the Developer Settings.
Connect your phone to your computer via USB and grant debugging access.
In Android Studio, select your device in the "Run Configurations" dropdown.
Click Run (▶️) to launch the app on your device.
Verify the App is Running
Once the app is running, verify the following:

The product list should load successfully from the server.
Clicking on a product should display detailed information (image, description, price, and category).
The search functionality should allow searching for products by name.
Sorting by price should reorder the list of products accordingly.
The order functionality should display a Toast message confirming the order.

Tech Stack
Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
Networking: Retrofit, Coroutines
Image Loading: Picasso
State Management: LiveData, ViewModel
