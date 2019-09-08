#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
echo "********************************************************"
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:+UseG1GC \
     -XX:+UseStringDeduplication \
     -XX:InitialRAMPercentage=60.0 \
     -XX:MaxRAMPercentage=60.0 \
     -Djava.security.egd=file:/dev/./urandom \
     -jar /usr/local/eureka-server/@project.build.finalName@.jar
