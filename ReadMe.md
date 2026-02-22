# 📰 NewsApp-Compose

A production-ready Android news application built using **Jetpack Compose**, **MVVM**, **Dagger-Hilt**, **Kotlin Coroutines & Flow**, and **Coil**.

This project demonstrates production-grade Android architecture, reactive state management, lifecycle-aware streams, and scalable code organization aligned with engineering standards.

---

## 🚀 Tech Stack

- **UI:** Jetpack Compose (Declarative UI)
- **Architecture:** MVVM + Unidirectional Data Flow
- **Reactive Streams:** Kotlin Flow / StateFlow / SharedFlow
- **Async Programming:** Kotlin Coroutines
- **Dependency Injection:** Dagger-Hilt
- **Networking:** Retrofit
- **Image Loading:** Coil
- **Lifecycle Awareness:** collectAsStateWithLifecycle()

---

## 🏛 Architectural Principles

- Single source of truth
- Explicit UI state modeling (Initial / Loading / Success / Error)
- Clear separation of persistent state vs one-time UI events
- Lifecycle-safe Flow collection
- Side-effects isolated using Compose effect APIs
- Dependency inversion between layers
- Testable ViewModels and repositories

---

## 🏗 Engineering Decisions

- Used StateFlow instead of LiveData for cold stream transformation support
- Implemented flatMapLatest() to ensure search request cancellation
- Modeled screen state explicitly instead of using nullable data
- Isolated side-effects using LaunchedEffect
- API key secured via secrets.properties

---

## 📂 Project Structure

```
newsapp/
├── di/                 
│   └── module/             # Hilt Modules
├── ui/
│   ├── base/               # BaseActivity.kt + UiState.kt
│   │   └── theme/          # Compose Theme files & common composables
│   ├── main/
│   │   └── model/          # Ui Model used in MainActivity.kt
│   ├── search/             # Search Screen
│   ├── countries/          # Countries Screen
│   ├── languages/          # Languages Screen
│   ├── newssources/        # News Scource Screen
│   └── topheadlines/       # Top Headlines Screen
├── data/
│   ├── api/
│   ├── local/              # Local Data source (List of Countries & Languages)
│   ├── model/              # Model classes
│   │   ├── local/
│   │   └── network/
│   └── repository/
└── utils/
```

Each feature follows a consistent pattern:

- `Screen.kt` → Composable UI
- `ScreenViewModel.kt` → State & logic
- `ScreenActivity.kt` → Corresponding Activity

This structure supports modularization and scaling into multi-module architecture.

---

## ⚡ Key Engineering Highlights

- Fully declarative UI using Compose
- Lifecycle-aware reactive state collection
- Explicit modeling of screen states using sealed classes
- Clean separation between UI, business logic, and data
- Structured error handling strategy
- Coroutine-based concurrency with cancellation safety
- Dependency injection configured for scalability
- Designed for extensibility (Paging, caching, offline support)

---

## 🛠 Setup

1. Clone the repository:

```
git clone https://github.com/darshan-miskin/NewsApp-Compose.git
```

2. Add your API key in `secrets.properties`:

```
api_key=YOUR_API_KEY
```

3. Build and run the project in Android Studio.

---

## 🎯 Why This Project

This project showcases:

- Strong command over modern Android architecture
- Advanced Compose state management patterns
- Deep understanding of Flows & coroutines
- Clean, scalable dependency injection setup
- Production-ready separation of concerns

---

## 🔮 Future Plans

This project is designed to evolve toward a more production-hardened, scalable architecture. Planned improvements include:

- **Pagination Support**
    - Integrate Paging 3 for efficient large dataset handling
    - Optimized memory usage and incremental loading

- **Local Database**
    - Add Room for offline caching
    - Implement single source of truth with local + remote mediation

- **Full Clean Architecture Migration**
    - Introduce explicit Domain layer
    - Use UseCases / Interactors
    - Enforce strict dependency direction (UI → Domain → Data)

- **Multi-Module Architecture**
    - Separate core, data, domain, and feature modules
    - Improve build times and modular scalability
    - Enable independent feature ownership

- **API Key Security Enhancements**
    - Encrypt API keys
    - Move sensitive logic to NDK layer
    - Strengthen protection against reverse engineering

⭐ If you find this project valuable, consider starring the repository.