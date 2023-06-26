## Requirements
* Maven
* Java 11

## Compile
* mvn clean compile package

## Database Initialization
##### Tables and Example Users are created through liquibase at startup.
The username/balance are :
<br>juan.jordaan - 123400.10
<br>michael.scott - 123400.20
<br>pam.support - 123400.30

#### Endpoints exist to create a user
see PlayerController ( POST : /casino/player )


## Docker local
docker build -t casino-app -f .docker\Dockerfile.jvm
<br>docker run -it -p 80:80 casino-app