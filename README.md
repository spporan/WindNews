# WindNews

This sample app that implements MVVM architecture using hilt, Room, Paging3, Retrofit.

# App's Features
1. Splash screen
2. List of news screen
3. News details screen
4. News web view

 => Fetching the news list  supported pagination by scrolling. Each time by scrolling, fetch 30 news items.
 => Show loading progress in network call and handle network related error.
 => Show News Details when click on an list item 
 => Open original news when click on link that appeared details

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Hilt.
3. **ui**: View classes along with their corresponding ViewModel.
4. **utils**: Utility classes.

### Libraries
* [Android Architecture Components][arch]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [Paging 3][paging] for pagination


# Screenshots

<p align="center">
<img src="https://github.com/spporan/WindNews/blob/master/screenshots/Screenshot_1.jpg" alt="drawing" width="230px"/> 
<img src="https://raw.githubusercontent.com/spporan/WindNews/master/screenshots/wind_news.gif" alt="drawing" width="230px" hspace="30"/>  
</p>

# APK Download
[Download APK](https://github.com/spporan/WindNews/blob/master/apk/app-debug.apk)

### How to run the project:

    ##gradle.properties
```groovy
    API_KEY = Should be replay with your API_KEY
```
    


### TODO
I tried my best to cover up all features that you mentioned though there was a tight deadline.



[arch]: https://developer.android.com/arch
[paging]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[hilt]: https://developer.android.com/training/dependency-injection/hilt-android
