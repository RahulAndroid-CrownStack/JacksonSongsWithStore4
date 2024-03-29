# Jackson Songs With Store4


## Introduction
The app shows list of albums, fetched from the api, usin dropbox library Store 4. 

Implementing Store 4 provides you with the following advantages:
 - In-memory caching
 - Disk caching for when users are offline
 - Throttling of API calls when parallel requests are made for the same resource
 - Rich API to ask for data whether you want cached, new or a stream of future data updates.


## Pre-requisites
 - Experience with Kotlin syntax.


## Libraries Used
- [Store 4](https://github.com/dropbox/Store): Store is a Kotlin library for loading data from remote and local sources.
- [Navigation Components](https://developer.android.com/guide/navigation)
- [Coil](https://github.com/coil-kt/coil): An image loading library for Android backed by Kotlin Coroutines
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Okhttp](https://github.com/square/okhttp)
- [Retrofit 2](http://square.github.io/retrofit/)
- [Hilt](https://developer.android.com/codelabs/android-hilt#0): Dependency injection
- [View Binding](https://developer.android.com/topic/libraries/view-binding)


## Requirements

- JDK 1.8
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android 11 [(API 30) ](http://developer.android.com/tools/revisions/platforms.html).
- Latest Android SDK Tools and build tools.


## Architecture

This project follows MVVM Android architecture guidelines.


To quickly start a new project from this boilerplate follow the next steps:

* Download this [repository as zip](https://github.com/RahulAndroid-CrownStack/JacksonSongsWithStore4/archive/refs/heads/master.zip).
* Change the package name. 
  * Rename packages in main using Android Studio.
  * In `app/build.gradle` file, `packageName`.
  * In `src/main/AndroidManifest.xml`.
* Create a new git repository.
* Replace the example code with your app code following the same architecture.
* Update README with information relevant to the new project.




