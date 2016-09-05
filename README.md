# Bridge

## Synopsis

Find and join events around you. Geolocation based event curator. Built with Parse ,GoogleMaps API's, Java (Android)

## Demo

Live Version: [link](https://play.google.com/store/apps/details?id=com.exampless.mohammed.bridge&hl=en "Bridge"). 

## Routes

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

- Express.js [link](https://expressjs.com/ "Braintree"). Express used for REST routing
- Heroku [link](https://www.heroku.com/ "Firebase"). Node server hosted on Heroku
- MongoDB [link](https://docs.mongodb.com/ "MongoDB"). Storage, noSQL JSON based storage


## Installation

Clone repo and open in Xcode.

```
git clone https://github.com/furkhan324/myworkrep.git
cd <into_directory_where_cloned>
npm install
```

## Contributors

Mohammed Abdulwahhab (@furkhan324), Taj Shaik(@tajshaik24)

## License

Code may not be copied, edited, or reproduced in any form without the consent of the contributors.
