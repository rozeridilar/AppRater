# AppRater
App Rater in Android.

Here is all the source code for asking user to rate your app. 

# Installation

- Add the dependency.

```java
dependencies {
    implementation 'com.github.rozeridilar:AppRater:v1.0'
}
```

<img src="https://user-images.githubusercontent.com/7174879/50829895-0b0d1500-1357-11e9-8dff-507f992937a7.gif" width="50%" height="50%">

# Documentation
## Usage For Fancy App Rater Dialog
```java
//To give a fancy effect to your custom button
AppRater.getInstance(MainActivity.this).addButtonEffect(_btnRateYourApp);

//To show App Rater Dialog
AppRater.getInstance(MainActivity.this).showAppRaterDialog(AppRater.getInstance(MainActivity.this).getRaterTitleMessage(), "someAppId");
```
## Language Support
- You can set all the titles in AppRater

| SetTitleTextMethods  | MethodNames |
| ------------- | ------------- |
| setOneStarMessage(String oneStarMessage)  | Default Name: "Naah."  |
| setTwoStarMessage  | Default Name: "Hard to use."  |
| setThreeStarMessage  | Default Name: "Ok."  |
| setFourStarMessage  | Default Name: "Good!" |
| setFiveStarMessage  | Default Name: "Fantastic!!"  |
| setCancelButtonTitle  | Default Name: "cancel"  |
| setSendButtonTitle  | Default Name: "send"  |
| setRaterTitleMessage  | Default Name: "Do you love our app?" |
| setRaterStartDescription  | Default Name: "Ok."  |

- All the properties above can be reached via get methods.(getRaterTitleMessage(), getRaterStartDescription(), etc.)
