#!/bin/sh
echo "Launching $BUILD_NAME IN AMAZON ECS"
ecs-cli configure --region eu-central-1 --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY --cluster ecs-eu-central-1-ghf-api
ecs-cli compose --file docker/dev-api/docker-compose.yml up
rm -rf ~/.ecs
