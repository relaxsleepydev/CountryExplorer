# **Country Explorer**
Country Explorer is a modern Android application that allows users to browse and search for countries across the globe. Built with Jetpack Compose, the app provides a smooth, responsive UI and features a robust offline-first architecture using Room for local caching and Retrofit for network data fetching.


🚀 Features
•
📜 Country List: Browse an exhaustive list of countries including their names, capitals, and populations.

•
🖼️ Flag Visuals: High-quality flag images loaded asynchronously using Coil.

•
🔍 Real-time Search: Instantly filter countries by name using a dynamic search bar.

•
📱 Detail View: View more information about a specific country in a dedicated detail screen.

•
📶 Offline Support: Data is cached in a local database (Room). Once loaded, you can browse countries even without an internet connection.

•
🔄 Auto-Sync: The app automatically fetches fresh data from the REST Countries API on startup.


🏗️ Architecture
The project follows the MVVM (Model-View-ViewModel) architectural pattern and incorporates the Repository Pattern for a clean separation of concerns:

•
UI Layer (Compose): Handles the display and user interaction.

•
ViewModel: Manages UI state and communicates with the repository.

•
Repository: The "Single Source of Truth." It coordinates data fetching from the Network and saving to the Local Database.

•
Data Layer: Consists of Retrofit for API calls and Room for SQLite persistence.


🛠️ Tech Stack

•
Language: Kotlin

•
UI: Jetpack Compose

•
Networking: Retrofit & Gson

•
Database: Room Persistence Library

•
Asynchronous Programming: Kotlin Coroutines & Flow

•
Image Loading: Coil

•
Navigation: Jetpack Navigation Compose

•
Dependency Management: Version Catalog (libs.versions.toml)


📂 Project Structure
app/src/main/java/com/example/countryexplorer/
├── data/               # Room Entities, DAOs, and Database setup
├── repository/         # Data logic and DTO-to-Entity mapping
├── ui/                 # UI Components (Screens, TopBars, Cards)
├── viewmodel/          # ViewModels and ViewModelFactory
├── CountryApi.kt       # Retrofit API interface
└── CountryApplication.kt # Global initialization (DB, Retrofit, Repository)

💡 Future Improvements

•
Implement Dark Mode support.

•
Add more detailed info like currencies, languages, and timezones
