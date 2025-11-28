# Android Live Updates Demo

A comprehensive demonstration of Android's Live Update notifications using Kotlin and Jetpack Compose.

---

### Features

Live Update Notifications: Implements Android 14+ Live Update API with promoted notifications
Delivery Status Tracking: Five-stage delivery tracking system

* 📋 Order Received (20%)
* 👨‍🍳 Preparing (40%)
* 🚗 On The Way (70%)
* ✅ Delivered (100%)
* ❌ Cancelled (0%)

### High-Priority Heads-Up Notification

The notification channel uses **IMPORTANCE_HIGH**, and notifications use **PRIORITY_HIGH**, ensuring the notification clearly appears on screen.

### Jetpack Compose UI

Two main buttons:

* **Start LiveActivity** → Starts the foreground notification
* **Change Step** → Cycles through delivery statuses

---

## Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **Foreground Service**
* **Notification Manager & Channels**
* **AndroidX Core**

---

## How It Works

### 1. MainActivity

* Creates the notification channel
* Starts or updates the foreground service based on UI interactions
* Contains the Compose UI with the two control buttons

### 2. DeliveryStatus

* Defines delivery stages
* Contains progress percentages
* Manages UI text and emojis

### 3. OrderLiveService

* Creates notification channels
* Builds Live Update notifications
* Handles Android version compatibility

---

## Contributing

Pull requests are welcome! If you'd like to improve the progress system, add animation, or extend Live Activity behavior, feel free to contribute.
