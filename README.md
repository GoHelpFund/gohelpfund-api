# Introduction

1.  A Spring Cloud Config server that is deployed as Docker container and can manage a services configuration information using a GitHub-based repository.
3.  A campaign service that will manage campaign data used within GoHelpFund.
4.  A Postgres SQL database used to hold the data for the service.

# Software needed
1.	Apache Maven (http://maven.apache.org).
2.	Docker (http://docker.com). 
3.	Git Client (http://git-scm.com).

# Building the Docker Images
Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

   **mvn clean package docker:build**

# Running the service

Now we are going to use docker-compose to start the actual image.  To start the docker image, issue the following docker-compose command:

   **docker-compose -f docker/local-api/docker-compose.yml up**

