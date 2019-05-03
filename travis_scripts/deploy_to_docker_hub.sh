#!/bin/sh
echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push gohelpfund/ghf-authentication-service:$BUILD_NAME
docker push gohelpfund/ghf-campaign-service:$BUILD_NAME
docker push gohelpfund/ghf-config-server:$BUILD_NAME
docker push gohelpfund/ghf-eureka-server:$BUILD_NAME
docker push gohelpfund/ghf-fundraiser-service:$BUILD_NAME
docker push gohelpfund/ghf-donation-service:$BUILD_NAME
#docker push gohelpfund/ghf-zipkin-server:$BUILD_NAME
docker push gohelpfund/ghf-zuul-server:$BUILD_NAME