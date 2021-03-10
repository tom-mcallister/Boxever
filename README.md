# Boxever
Program that calculates the shortest duration between two airports.


## What the App does
Takes String input from teh user, that correspond to the iata codes for supported airports.

After performing some light validation an implementation of Dijkstras Algorithm to find the shortest duration between the two supported Airports.

# Brief description of steps
- The routes that were provided as part of the spec are loaded into Route objects
- Airport objects are extracted from the given routes 
- User input is collected and validated
- DijkstraDurationCalculator object is instantiated with a list of supported airports
- User input is passed to the calculator to determine the shortest duration (cost) between the two airports
- Value objects are used in the calculator implementation so as not to mutate model objects

## main Classes and Interfaces
| Class                         | Package       |
| -------------                 |:-------------:| 
| Main                          | app           | 
| Calculator                    | calculator    |
| CalculatorResult              | calculator    |
| DijkstraDurationCalculator    | calculator    | 
| Loader                        | loader        | 
| Route                         | model.input   | 
| Airport                       | model         | 
| Customer                      | model         | 
| Validator                     | validator     | 


## test Classes 
| Class                             | Package       |
| -------------                     |:-------------:| 
| DijkstraDurationCalculatorTest    | calculator    | 
| LoaderTest                        | loader        |
| AirportFixture                    | model         | 
| RouteFixture                      | model         | 

## resources
- routes.csv


## Improvements
More generic DijkstraDurationCalculator class that operates on a generic Node type instead of a concrete Airport type. 


## Instructions

### Download source to your local machine

### CD to the directory locally 
Using your terminal, cd into the java folder. It will look like this localLocation/RouteFinder/src/main/java

### Build application
`mvn clean compile` 

### Run application
- Create a Jar
`mvn package`

### Run the app (point to jar)
`java -jar target/RouteFinder-1.0-SNAPSHOT.jar`
- Supply user input as prompted


### Run tests
`mvn clean test`