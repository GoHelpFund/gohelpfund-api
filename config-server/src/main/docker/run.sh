#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server to start  on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka-server $EUREKASERVER_PORT`; do sleep 3; done
echo ">>>>>>>>>>>> Eureka Server has started"

echo "********************************************************"
echo "Starting Configuration Service with Eureka Endpoint:  $EUREKASERVER_URI";
echo "********************************************************"
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+UseG1GC \
     -XX:+UseStringDeduplication \
     -XX:InitialRAMPercentage=60.0 \
     -XX:MaxRAMPercentage=60.0 \
     -Djava.security.egd=file:/dev/./urandom \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
     -jar /usr/local/config-server/@project.build.finalName@.jar
