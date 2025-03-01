# Instagram-like Feed Sample

A performant Android scrollable feed consisting of a few posts to display a single photo, a single video, and a combination of one photo and one video. The implementation demonstrating media handling, smooth scrolling, and efficient caching strategies. The data is locally stored images and videos bundled within the application. The Architecture is compatible to handle the data from remote data source as well.  

![Feed Screenshot](screenshots/feed_demo_1.jpeg)
![Feed Screenshot](screenshots/feed_demo_2.jpeg)


## Features
**Smart Video Playback**
    - Auto-play visible videos
    - Pause on scroll
    - TextureView rendering

**Mixed Media Support**
    - Image posts
    - Video posts
    - Combined image+video posts

**Performance Optimizations**
    - RecyclerView view recycling
    - Player instance pooling
    - Media content caching

**Smooth Scrolling**
    - Prefetching mechanism

## Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM
- **Dependency Injection**: Hilt
- **Media Playback**:
    - Android Media3 ExoPlayer
    - `@UnstableApi` used per Media3 requirements
- **Image Loading**: Glide
- **Caching**:
    - Media3 SimpleCache (50MB)
    - Glide disk cache
- **Build System**: Gradle

## Clone the Repository: 
 - https://github.com/dev-habib-rehman/InstagramFeedsSample**

- Open the project in Android Studio (latest version recommended). Sync the project to download all dependencies. Build and run the app on an emulator or a physical device.

- Feel free to reach out for questions or issues. Enjoy exploring the app!
