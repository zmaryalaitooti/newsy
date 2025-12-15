# newsy
# 📰 Newsy – Modern News App

Newsy is a modern Android news application built with **Jetpack Compose**, **Clean Architecture**, and **MVVM** pattern.  
It delivers the latest news from multiple sources with beautiful UI, smooth pagination, and AdMob monetization.

---

## 🚀 Features

- 🧠 **Clean Architecture (MVVM + Use Cases)**
- 🖼 **Jetpack Compose UI** – built entirely with Compose components  
- 🔍 **Smart Search** – search news articles by keyword or source  
- 🔄 **Pagination with Jetpack Paging 3**  
- 💬 **News Detail Screen** – rich preview with images and full content  
- 🌙 **Dark & Light Themes**  
- 📱 **Responsive UI** – supports all Android screen sizes  
- 💰 **AdMob Integration**  
  - Banner Ads  
  - Native Ads  
  - Rewarded Ads  
- ⚡ **Shimmer Loading** while fetching articles  
- 🧭 **Navigation Component with Compose**  
- 🔔 **Firebase Cloud Messaging (FCM)** (for notifications, optional)

---

## 🏗️ Tech Stack

| Layer | Libraries / Tools |
|--------|--------------------|
| **UI** | Jetpack Compose, Material 3, Accompanist |
| **Architecture** | MVVM, Clean Architecture |
| **Async / Data** | Kotlin Coroutines, Flow, Paging 3 |
| **Network** | Retrofit, OkHttp, Gson |
| **Dependency Injection** | Hilt (Dagger) |
| **Monetization** | Google AdMob (Banner, Native, Rewarded) |
| **Notifications** | Firebase Cloud Messaging (FCM) |
| **Other** | Coil for image loading, AndroidX Navigation |

---

## 🪜 Project Structure

com.ahmadmaaz1.newsy
│
├── data/ # Repositories, Retrofit APIs, DTOs
├── domain/ # Models and Use Cases
├── presentation/
│ ├── home/ # Home screen with news list
│ ├── detail/ # Article detail screen
│ ├── search/ # Search UI
│ ├── component/ # Reusable composables (ArticleCard, ShimmerEffect, etc.)
│ ├── navgraph/ # App navigation
│ └── ads/ # AdMob banner/native/rewarded components
│
└── di/ # Hilt dependency injection setup

yaml
Copy code

---

## ⚙️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/<your-username>/Newsy.git
   cd Newsy
Open in Android Studio (Arctic Fox or newer)

Add your News API key

Open your local.properties file

Add:

ini
Copy code
NEWS_API_KEY=your_api_key_here
Set up AdMob

Create an AdMob account → https://admob.google.com

Replace the test ad unit IDs with your own:

kotlin
Copy code
adUnitId = "ca-app-pub-xxxxxxxxxxxxxxxx/yyyyyyyyyy"
Call MobileAds.initialize(this) in your Application class.

Run the app 🎉

📸 Screenshots
Home	Search	Detail

💰 AdMob Monetization
Ad Type	Placement	Notes
Banner Ad	Bottom of Home screen	Steady background income
Native Ad	Between news items	Best balance of UX + revenue
Rewarded Ad	Unlock premium feature	Optional user-initiated ads

During development, always use Google Test Ad IDs to avoid invalid traffic.

📦 Dependencies
gradle
Copy code
implementation "androidx.compose.ui:ui:<latest>"
implementation "androidx.compose.material3:material3:<latest>"
implementation "androidx.paging:paging-compose:<latest>"
implementation "com.google.dagger:hilt-android:<latest>"
implementation "com.google.android.gms:play-services-ads:<latest>"
implementation "com.squareup.retrofit2:retrofit:<latest>"
implementation "io.coil-kt:coil-compose:<latest>"
🧑‍💻 Author
Ali Maaz Ahmad
📧 [your-email@example.com]
💼 LinkedIn Profile
🐙 GitHub

🪪 License
sql
Copy code
MIT License

Copyright (c) 2025 Ali Maaz Ahmad

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software...
⭐ Don’t forget to star the repo if you like it!
