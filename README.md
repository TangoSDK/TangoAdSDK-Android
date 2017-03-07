# TangoAdSDK-Android

## Prerequisites

### Ad unit id 

Ad unit id is a string that identify particular ad unit. Please use 'carousel_feed_placeholder' in testing app and contact [ads@tango.me](ads@tango.me) for the real ad unit id in production app. 

## Installation

## Native Ads

Native is a set of ad creatives that allow publishers to render them in the similar style as other elements in their apps. With native ads, publishers are able to provide user a consistent and unobstrusive experience. 

A native ad include the following creatives:

1. Title: the title of the native ad

2. Subtitle: the subtitle of the native ad, typically a short description

3. Cta text (optional): the 'call to action' text, for example "Install"

4. Icon: the icon image of the native ad 

5. Image: the main image of the native ad

6. Star rating (optional): specifically for app install ads, represent the star rating of the app 


### Loading native ads

Step 1. Create an instance of AdLoader and initialize it with an ad unit id. 
```
AdLoader loader = new AdLoader(adUnitId);
```

Step 2. Implement AdLoader.Listener to handle events from AdLoader. 
```
@Override
public void onSuccess(@NonNull Ad ad) {
  // This will be called when a native ad is loaded successfully. 
}

@Override
public void onError(@NonNull String errorText) {
  // This will be called when a native ad is failed to load. 
}
```

Step 3. Pass AdLoader.Listener and load an ad from server. 
```
loader.load(this);
```

Step 4. Implement Ad.LoadBitmapListener. 
```
void onLoadBitmapSuccess(@NonNull Bitmap var1) {
  // This will be called when an image is loaded successfully. 
}

void onLoadBitmapError(@NonNull String var1) {
  // This will be called when an image is failed to load. 
}
```

Step 5. When a native ad is loaded successfully, pass LoadBitmapListener and load the icon and the main image. 
```
ad.loadIcon(loadBitmapListenerForIcon);
ad.loadWideImage(loadBitmapListenerForImage);
```

Step 6. Register view for interaction. An Ad.Listener may pass to handle ad events.  
```
ad.register(view, adListener);
```

Step 7. Render the creatives of the native ad
```
ad.getTitle();
ad.getSubtitle();
ad.getCtaText();
ad.getRating();
icon and image received in Ad.LoadBitmapListener
```

## Ad Events

The app will be notified when the following events taken place in Ad if corresponding methods in Ad.Listener are implemented.

1. onAdClicked: the event of a Ad being clicked

2. onAdTracked: the event of a Ad impression being logged 


## Sample app

* [Tango Native Ad Sample](https://github.com/TangoSDK/TangoAdSDK-Android/tree/master/TangoNativeAdSample)

