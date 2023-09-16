# Authentication 

## OverView
This application is made to authenticate the users into spring boot 
application using the BCrypt.

## Routes

| Route         | Method | Description                                        |
|---------------|--------|----------------------------------------------------|
| /             | GET    | Displays the home page                             |
| /login        | GET    | Displays the login page.                           |
| /login        | POST   | Handles user login.                                |
| /signup       | GET    | Displays the signup page.                          |
| /signup       | POST   | Handles user signup.                               |
| /logout       | GET    | Logs the user out and redirects to the login page. |
| /posts        | GET    | Gets the user posts                                |
| /posts/create | Post   | Handles the user post creation                     |

## Notes

- If the user is authenticated he can only visit specific routes

## Requirements

- PostgresSql
- IntelliJIDE
- Java 11

## How to run the application

- clone the repo into your machine
- create a database called `auth`
- navigate to [Application.properties](./src/main/resources/application.properties)
- update the username and password based on your postgres credit
  run the application.
- go to your browser and type `localhost:8080`
