# simple crud Application

This folder contains two applications one is backend and one is front end
Bakend application is created in spring boot
Front end application is created in angular2

Backend application uses JPA, H2, Maven, Quartz.
 It is developed that uses scheduler to run every day once and checks if there are subscribed users who have last billing date for subscription 30 days ago. if so it creates new billing and stores in the users Billings

 For now it is assumed that there is only one type of subscription but if system has more than one type of subscription then Customer can has set of Subcription objects and we can check inside for loop name of subscriptions customer has and if customer has Primium subscription then we can check last billing date for it and if it is more than 30 days ago we can create new Billing object and add to customer and of course update last billing date. 

 Tests are in tests folder 

 Schedular timer is set in  application.properties file (simplejob.frequency=86400000//this is one day in milliseconds)

 If you want to test scheduler then you can change this time

 Front end application has registration page, login page, subscription page and profile page. 

 This application is designed for smaller screens mostly for mobile if we run on desktop we should play with the css files little bit.

 Authentication is not done it just compares the phone number and password from backend you can go to the links and nothing stops you. In the future JWT authentication can be added easly 


 
 I ran both applications in one computer and because of this:

 @CrossOrigin(origins = "http://localhost:4200") is added in the CustomerContoroller 

 and 

 these 
 headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
 headers.append('Access-Control-Allow-Credentials', 'true');

 are added in the header of the requests in the AuthService in front end application.

 If you want to run these two applications in different computers then you should remove the code above from the files.
