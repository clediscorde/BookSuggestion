# BookSuggestion
Coding Assignment – Book Suggestion App

I code this assignment not only to code an assignment, but also to explore technologies I'm less familiar with.

The following technologies were used:
* Java
* Maven
* MongoDB
* Spring
* Spring MVC
* Spring Data Mongo
* Rest
* Html
* AngularJS
* Junit
* Mockito

The web interface is the home page of the application (http://{server:port}/).

The web interface contains fields to search book suggestions.

This is not possible to add, modify of delete books with the web interface, but it can be used via some rest APIs.

Add/modify book:

*Method: POST
*URI: /books/save
*Data example:
```
{
    "id":"562ec8910364d102131849c5"
    "title": "Classic 123",
    "author": "Classic Author 2",
    "genre": "Adventure",
    "numberOfPage": 1000,
    "yearOfPublication": 1884,
    "overallRating": 4
}
```
*To modify a book, the id must be included in the post request
*To add a book, the id must not be included in the post request

Delete book:
* Method: GET
* URI: /books/delete/{id}
* {id} is the id of the book to delete

The data is saved in a MongoDB, hosted on MongoLab.
This is possible to change the database configuration in config.properties.