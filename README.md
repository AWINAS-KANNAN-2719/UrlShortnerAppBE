# UrlShortnerApp - Back End 

# Features 

1) Create short url of actual url with short url's length not more then 10 Alphanumeric charaters
2) Know all the Short url details with access count
3) By entering short url, we will be navigated to actual url

# Stack 

* Java
* Spring boot 2.5
* Maven
* Junit 5
* H2 Database



# Steps to Run the application

Prerequisite : Git , Maven

1) git clone https://github.com/AWINAS-KANNAN-2719/UrlShortnerAppBE.git
2) cd UrlShortnerAppBE
3) mvn clean install
4) mvn spring-boot:run

The Applicaition will run in localhost:8080

# Code coverage file location

After maven clean install command is executed , the code coverage of application will be generated which can be 
seen in below file.

File location : UrlShortnerAppBE\target\site\jacoco\index.html


# API End Point details

1) Create  short url :
   
   Url :  http://localhost:8080/urlshortner/shorten?origUrl=https://www.google.com
   
   Method : POST
   
   Output :
   ```json
   {
    "httpCode": "200",
    "responseCode": "URL_SHORT_201",
    "responseDescription": "Shorten url generated and stored",
    "id": 1,
    "shortUrl": "gp3f790ds",
    "originalUrl": "https://www.google.com",
    "accessCount": 0
   } 
 
 2) Get All Url details with access count : 
 
    Url : http://localhost:8080/urlshortner/urls
    
    Method : GET
    
    Output :
    ```json
    {
    "httpCode": "200",
    "responseCode": "URL_SHORT_202",
    "responseDescription": "Retrieved all url details",
    "urlList": [
        {
            "id": 1,
            "shortUrl": "gp3f790ds",
            "originalUrl": "https://www.google.com",
            "accessCount": 0
        },
        {
            "id": 2,
            "shortUrl": "lnt0oex0l",
            "originalUrl": "https://www.fb.com",
            "accessCount": 0
        },
        {
            "id": 3,
            "shortUrl": "fjulph19s",
            "originalUrl": "https://www.amazon.com",
            "accessCount": 0
        },
        {
            "id": 4,
            "shortUrl": "9xs8n5li",
            "originalUrl": "https://www.lowes.in",
            "accessCount": 0
        }
       ]
      }
 
3) Redirect using short url :

    Url: http://localhost:8080/gp3f790ds
    
    Method : GET
    
    Output:
    ```json
    {
    "httpCode": "200",
    "responseCode": "URL_SHORT_203",
    "responseDescription": "Original Url Retrieved",
    "id": 1,
    "shortUrl": "gp3f790ds",
    "originalUrl": "https://www.google.com",
    "accessCount": 1
    }
  
   

