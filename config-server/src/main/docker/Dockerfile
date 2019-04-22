FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd && apk add curl
RUN mkdir -p /usr/local/config-server
RUN echo $JAVA_HOME
ADD @project.build.finalName@.jar /usr/local/config-server/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
