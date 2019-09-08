#!/bin/sh
echo "Launching $BUILD_NAME IN AMAZON Elastic Container Service"
ecs-cli configure profile --profile-name ghf-api --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY
ecs-cli configure --region eu-central-1 --cluster ecs-eu-central-1-ghf-api
ecs-cli compose --file docker/dev-api/docker-compose.yml --ecs-params docker/dev-api/ecs-params.yml up
rm -rf ~/.ecs
