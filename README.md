# Kalah Game
This is a Java + SpringBoot RESTful WebService application that runs the game of 6-stone Kalah. 
For the general rules of the game please refer to Wikipedia: https://en.wikipedia.org/wiki/Kalah.
The default implementation of this app for 6-stone. 

## About the game
* Each of the two players has six pits in front of him/her. 
* To the right of the six pits, each player has a larger pit, his Kalah or house.
* At the start of the game, six stones are put In each pit.
* The player who begins picks up all the stones in any of their own pits, and distribute the stones on to the right, one in each of the following pits, including his own Kalah. 
* No stones are put in the opponent(s) Kalah. If the players last stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other player's turn.

### How to use this game
* Two human players to play the game, each in his own computer by accessing REST URIs.
1. Create a game using [curl --header "Content-Type: application/json" --request POST http://localhost:8080/games] and returns game identifier.
2. Using Game Identifier, navigate different pit [curl --header "Content-Type: application/json" --request PUT  http://localhost:8080/games/{gameId}/pits/{pitId}] at the end of this action, result show about current status of the game by displaying Pit Id and it's stones count.

## Install & Running
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Spring Boot 1.5.15](https://spring.io/projects/spring-boot) - Application Boot Framework
* [Spring Framework 4+](https://spring.io/projects/spring-framework) - Spring Framework
* [Spring Hateoas 0.25.0](https://spring.io/projects/spring-hateoas) - Spring Hateoas
* [Gradle](https://gradle.org/) - Build tool

### Build & run 

* Run test
```
$ gradle test
```

* Run the web server
```
$ gradle bootRun
```

### API documentation
After running the project on dev environment and browse
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Authors

* **Karthikeyan N**


