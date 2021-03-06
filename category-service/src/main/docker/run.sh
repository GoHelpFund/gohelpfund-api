#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka-server  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"


#echo "********************************************************"
#echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
#echo "********************************************************"
#while ! `nc -z database $DATABASESERVER_PORT`; do sleep 3; done
#echo "******** Database Server has started "

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z config-server $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Waiting for the zuul server to start on port $ZUULSERVER_PORT"
echo "********************************************************"
while ! `nc -z zuul-server $ZUULSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Waiting for the fundraiser service to start on port $FUNDRAISERSERVICE_PORT"
echo "********************************************************"
while ! `nc -z fundraiser-service $FUNDRAISERSERVICE_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Starting Category Server with Configuration Service via Eureka :  $EUREKASERVER_URI" ON PORT: $SERVER_PORT;
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/category-service/@project.build.finalName@.jar
