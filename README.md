# WindNews

This sample app implements MVVM architecture using hilt, Room, Paging3, and Retrofit.

# App's Features
1. Splash screen
2. List of news screen
3. News details screen
4. News web view

 => Fetching the news list  supported pagination by scrolling. Each time by scrolling, fetch 30 news items.
 => Show loading progress in network calls and handle network-related errors.
 => Show News Details when clicking on a list item 
 => Open original news when clicking on the link that appears with details

#### The app has the following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Hilt.
3. **ui**: View classes along with their corresponding ViewModel.
4. **utils**: Utility classes.

### Libraries
* [Android Architecture Components][arch]
* [Retrofit][retrofit] for REST API communication
* [Glide][glide] for image loading
* [Paging 3][paging] for pagination


# Screenshots

<p align="center">
<img src="screenshots/img_list.png" alt="drawing" width="230px" hspace="30"/>
<img src="screenshots/img_details.png" alt="drawing" width="230px" hspace="30"/>
<img src="screenshots/img_webview.png" alt="drawing" width="230px" hspace="30"/>

<img src="screenshots/wind_news.gif" alt="drawing" width="230px" hspace="30"/> 

</p>

# APK Download
[Download APK](apk/app-debug.apk)

### How to run the project:

    ##gradle.properties
```groovy
    API_KEY = Should be replaced with your API_KEY
```
    


### TODO
I tried my best to cover up all the features that you mentioned though there was a tight deadline.



[arch]: https://developer.android.com/arch
[paging]: https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[hilt]: https://developer.android.com/training/dependency-injection/hilt-android
