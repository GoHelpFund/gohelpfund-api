#!/bin/sh
echo "Launching $BUILD_NAME IN AMAZON Elastic Container Service"
ecs-cli configure profile --profile-name ghf-api --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY
ecs-cli configure --region eu-central-1 --cluster prod-gohelpfund-api
ecs-cli compose --file docker/prod-api/docker-compose.yml --ecs-params docker/prod-api/ecs-params.yml up
rm -rf ~/.ecs
