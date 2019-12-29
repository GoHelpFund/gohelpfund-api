#!/bin/sh
getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka-server  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the database server to start on port $DATABASESERVER_PORT"
echo "********************************************************"
while ! `nc -z $DATABASESERVER_URI $DATABASESERVER_PORT`; do sleep 3; done
echo "******** Database Server has started "

echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z config-server $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Waiting for the kafka server to start on port  $(getPort $KAFKASERVER_PORT)"
echo "********************************************************"
while ! `nc -z kafka-server  $(getPort $KAFKASERVER_PORT)`; do sleep 10; done
echo "******* Kafka Server has started"

echo "********************************************************"
echo "Waiting for the authentication service to start on port $AUTHSERVER_PORT"
echo "********************************************************"
while ! `nc -z authentication-service $AUTHSERVER_PORT`; do sleep 3; done
echo "*******  Authentication Service has started"

echo "********************************************************"
echo "Waiting for the donation service to start on port $DONATION_PORT"
echo "********************************************************"
while ! `nc -z donation-service $DONATION_PORT`; do sleep 3; done
echo "*******  Donation Service has started"

#echo "********************************************************"
#echo "Waiting for the zipkin server to start  on port $ZIPKIN_PORT"
#echo "********************************************************"
#while ! `nc -z zipkin-server $ZIPKIN_PORT`; do sleep 10; done
#echo "******* Zipkin Server has started"

echo "********************************************************"
echo "Starting Fundraiser Service with Configuration Service via Eureka :  $EUREKASERVER_URI" ON PORT: $SERVER_PORT;
echo "Using Kafka Server: $KAFKASERVER_URI"
echo "Using ZK    Server: $ZKSERVER_URI"
echo "USing Profile: $PROFILE"
echo "Fundraiser service will use $AUTHSERVER_URI for URI"
echo "********************************************************"
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+UseG1GC \
     -XX:+UseStringDeduplication \
     -XX:InitialRAMPercentage=60.0 \
     -XX:MaxRAMPercentage=60.0 \
     -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.cloud.stream.kafka.binder.zkNodes=$KAFKASERVER_URI          \
     -Dspring.cloud.stream.kafka.binder.brokers=$ZKSERVER_URI             \
     -Dsecurity.oauth2.resource.userInfoUri=$AUTHSERVER_URI               \
     -Dspring.zipkin.baseUrl=$ZIPKIN_URI                                  \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/fundraiser-service/@project.build.finalName@.jar
