# 📰 Newsy – Modern Android News App

Newsy is a modern, scalable Android news application built with **Jetpack Compose**, **Clean Architecture**, and **MVVM**. The application delivers real-time news from multiple sources while following modern Android development best practices, including a **multi-module architecture**, **CI/CD**, **Firebase services**, and comprehensive testing.

---
## 📲 Download

<a href="https://play.google.com/store/apps/details?id=com.ahmadmaaz1.newsy">
    <img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png"
         alt="Get it on Google Play"
         height="80">
</a>

Or download directly from Google Play:

https://play.google.com/store/apps/details?id=com.ahmadmaaz1.newsy
## ✨ Features

### 📰 News
- Browse the latest headlines from multiple categories
- Search news articles by keyword
- Breaking News section
- Infinite scrolling with Paging 3
### 🎨 User Experience
- Built entirely with Jetpack Compose
- Material 3 Design
- Dark & Light Themes
- Responsive UI for phones and tablets
- Beautiful animations
- Error & empty state handling

### 🔖 Personalization
- Save favorite articles
- Search history
- Category-based browsing

### 🔔 Notifications
- Background notification scheduling using WorkManager

### 📈 Analytics & Monitoring
- Firebase Analytics
- Firebase Crashlytics
- Firebase Performance Monitoring

### 💰 Monetization
- Interstitial
### 🚀 DevOps
- GitHub Actions CI/CD
- Firebase App Distribution
- Automated Debug APK Distribution
- Automated Release Builds

### 🧪 Testing
- Unit Testing with JUnit
- Kotlin Flow Testing
- Kotest
- Jetpack Compose UI Testing

---

# 🏗️ Architecture

The project follows **Clean Architecture** and **MVVM** principles.

```
Presentation
│
├── UI (Compose)
├── ViewModel
│
Domain
│
├── UseCases
├── Repository Interfaces
│
Data
│
├── Repository Implementation
├── Remote Data Source
├── Local Data Source
├── Room Database
└── Retrofit API
```

---
# 📦 Project Modules

The application is organized into two modules to improve code organization and maintainability.

```
Newsy
│
├── app
│   ├── data
│   ├── domain
│   ├── presentation
│   ├── di
│   ├── navigation
│   ├── util
│   └── MainActivity
│
└── notification
    ├── worker
    ├── scheduler
```

### 📱 app
The main application module containing:
- Jetpack Compose UI
- Clean Architecture (MVVM)
- Data Layer
- Domain Layer
- Repository
- Dependency Injection (Dagger Hilt) (DI)
- Room Database
- Retrofit
- Paging 3

### 🔔 notification
A dedicated module responsible for:
- WorkManager background tasks
- Daily news notifications
- Notification scheduling
- Opening news articles from notifications
---

# 🛠 Tech Stack

| Category | Technologies |
|-----------|--------------|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM, Clean Architecture |
| Modularization | Multi Module |
| Async | Kotlin Coroutines |
| Reactive | Kotlin Flow, StateFlow |
| Dependency Injection | Dagger Hilt |
| Navigation | Navigation Compose |
| Networking | Retrofit, OkHttp |
| Serialization | Gson |
| Pagination | Paging 3 |
| Local Storage | Room Database, DataStore |
| Image Loading | Coil |
| Background Work | WorkManager |
| Analytics | Firebase Analytics |
| Crash Reporting | Firebase Crashlytics |
| Performance | Firebase Performance Monitoring |
| Monetization | Google AdMob |
| Testing | JUnit, Kotest, Compose UI Test |
| CI/CD | GitHub Actions, Firebase App Distribution |
| Version Control | Git & GitHub |

---

# 📱 Screens
- Onboarding
- Home
- Categories
- Breaking News
- Search
- Search History
- Article Details
- Bookmarks
- Settings

---

# 🚀 CI/CD Pipeline

This project uses **GitHub Actions** for Continuous Integration and Continuous Delivery.

### CI

- Build every Pull Request
- Run Unit Tests
- Run Lint
- Generate APK

### CD

- Automatically build Debug APK
- Upload APK to Firebase App Distribution
- Generate Release AAB
- Ready for Google Play Store release

---

# 📊 Firebase Services

✅ Firebase Analytics

- Screen tracking
- User engagement
- Search analytics
- Article views

✅ Firebase Crashlytics

- Crash reports
- Stack traces
- Non-fatal exception logging

✅ Firebase Performance Monitoring

- Startup time
- Network requests
- Rendering performance

---

# 🧪 Testing

### Unit Testing

- Repository Tests
- Use Case Tests
- ViewModel Tests

### Flow Testing

- Kotlin Flow
- StateFlow

### UI Testing

- Jetpack Compose UI Test

### Testing Libraries

- JUnit
- Kotest
- Compose Test
- Mockito

---

# 💰 AdMob Integration

Supported Ads

- Interstitial 

During development always use **Google Test Ads**.

---

# 📂 Project Structure

```

├── app
│   ├── data
│   ├── domain
│   ├── presentation
│   ├── di
│   ├── navigation
│   ├── util
│   └── MainActivity
│
└── notification
    ├── worker
    ├── scheduler
---

# ⚙️ Setup

## Clone the project

```bash
git clone https://github.com/zmaryalaitooti/Newsy.git
```

```bash
cd Newsy
```

---

## Add News API Key

Create or open **local.properties**

```properties
NEWS_API_KEY=YOUR_API_KEY
```

---

## Firebase

1. Create a Firebase project
2. Download `google-services.json`
3. Place it inside the **app/** module

Enable:

- Analytics
- Crashlytics
- Cloud Messaging
- Performance Monitoring

---

## AdMob

Create your AdMob account.

Replace the sample IDs with your own:

```kotlin
ca-app-pub-xxxxxxxxxxxxxxxx/yyyyyyyyyy
```

Initialize:

```kotlin
MobileAds.initialize(this)
```

---

## Build

```
./gradlew assembleDebug
```

---

## Run Tests

```
./gradlew test
```

---

## Build Release

```
./gradlew bundleRelease
```

---

# 📷 Screenshots

| Home | Search | Detail | Bookmark |
|------|--------|--------|-----------|
| Coming Soon | Coming Soon | Coming Soon | Coming Soon |

---

# 📌 Future Improvements

- AI-powered news summarization
- Voice search
- Offline reading mode
- Multiple languages
- Wear OS support
- Tablet optimized layouts
- Compose Multiplatform support

---

# 👨‍💻 Developer

**Zmaryalai Ali**

📧 Email: alitooti349@gmail.com

💼 LinkedIn

https://www.linkedin.com/in/zmaryalai-ali/

🐙 GitHub

https://github.com/zmaryalaitooti

---

# ⭐ Support

If you like this project, please consider giving it a ⭐ on GitHub. It helps support future development and improvements.
