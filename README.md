# Bridge

## Synopsis

Find and join events around you. Geolocation based event curator. Built with Parse, GoogleMaps API's, Java (Android)

## Demo

Live Version: [link](https://play.google.com/store/apps/details?id=com.exampless.mohammed.bridge&hl=en "Bridge"). 

## Parse Queries

```Java
ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        try {
            Log.e("TAG", "THis is the count "+query.count());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        query.setLimit(10);


        try{
            entries=query.find();}
        catch(Exception e){}
        listAdapter =new userListAdapter(entries);
```
## Dependancies

API's and Frameworks used:

- Java/Android APIs [link](https://developer.android.com/index.html "Android/Java"). Android APIs 
- Google Places API [link](https://developers.google.com/places/ "Firebase"). Used for geocoding
- Parse [link](https://Parse.com/ "Parse"). Storage, noSQL table storage


## Installation

Clone repo and open in Android Studio.

```
git clone https://github.com/furkhan324/bridge-.git
#open gradle.build from Android Studio to launch the project
```

## Contributors

Mohammed Abdulwahhab (@furkhan324)

## License

Code may not be copied, edited, or reproduced in any form without the consent of the contributors.
