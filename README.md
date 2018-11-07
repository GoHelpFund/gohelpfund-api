# GoHelpFund - API

[![GitHub last commit](https://img.shields.io/github/last-commit/gohelpfund/gohelpfund-api.svg?style=for-the-badge)][github-last-commit]

[![GitHub issues](https://img.shields.io/github/issues/gohelpfund/gohelpfund-api.svg?style=flat-square&longCache=true)][github-issues]
[![GitHub closed issues](https://img.shields.io/github/issues-closed/gohelpfund/gohelpfund-api.svg?style=flat-square&longCache=true)][github-issues-closed]
[![GitHub pull requests](https://img.shields.io/github/issues-pr/gohelpfund/gohelpfund-api.svg?style=flat-square&longCache=true)][github-pulls]
[![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/gohelpfund/gohelpfund-api.svg?style=flat-square&longCache=true)][github-pulls-closed]

GoHelpFund is crowdfunding platform where people can donate and support campaigns like charity, medical, education, emergency and more.

GoHelpFund is still in beta, so please submit any issues ([bug reports][github-bug-report] and [feature requests][github-feature-request]).

The GoHelpFund platform enables people to:

- Create and view campaigns
- Donate to campaigns using the [HELP][helptoken] token
- Filter and sort based on preferences
- See trending campaigns
- View nearby campaigns based on your location
- Share campaigns to social media platforms
- Invite other people to the platform

To learn more about the GoHelpFund platform check out [this presentation video][explainer video] created by us.

# Table of Contents

   * [Getting Started](#getting-started)
      * [Introduction](#introduction)
      * [Software needed](#software-needed)
      * [Building the Docker Images](#building-the-docker-images)
      * [Running the service](#running-the-service)
   * [Components](#components)
      * [Technologies](#technologies)
      * [Directory Structure](#directory-structure)
      * [Patterns](#patterns)
   * [Examples](#examples)
      * [Walkthrough Guides](#walkthrough-guides)
      * [Configuration Files](#configuration-files-1)
   * [Contributing](#contributing)
   * [Changelog](#changelog)
   * [Questions &amp; Improvements](#questions--improvements)

# Getting Started

## Introduction

1.  A Spring Cloud Config server that is deployed as Docker container and can manage a services configuration information using a GitHub-based repository.
3.  A campaign service that will manage campaign data used within GoHelpFund.
4.  A Postgres SQL database used to hold the data for the service.

## Software needed
1.	Apache Maven (http://maven.apache.org).
2.	Docker (http://docker.com). 
3.	Git Client (http://git-scm.com).

## Building the Docker Images
Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

    mvn clean package docker:build

## Running the service

Now we are going to use docker-compose to start the actual image.  To start the docker image, issue the following docker-compose command:

    docker-compose -f docker/local-api/docker-compose.yml up

# Components

To be added

## Technologies

To be added

## Directory Structure

The folders are organized to make it easy to find code and streamline development flow.
Each folder is its own package **without any sub-packages**.

    github.com/gohelpfund/gohelpfund-api
    ├── authentication-service/         # oauth2 authentication and authorization
    ├── campaign-service/               # campaigns logic
    ├── category-service/               # campaign categories logic
    ├── category-service-ab-testing/    # campaign categories - new functionality used for A/B testing
    ├── config-server/                  # git-based configuration repository that manages startup data for all microservices
    ├── docker/                         # config files per environment, containing all microservices
    ├── eureka-server/                  # service discovery and load balancing
    ├── fundraiser-service/             # fundraisers logic
    ├── specialroutes-service/          # service that enables A/B testing
    ├── travis_scripts                  # continous deployment scripts: tag, build, deploy, test
    ├── upload-service/                 # upload images/videos part of the new campaign flow logic
    ├── zuul-server/                    # services gateway and dynamic routing
    ├── zipkin-server/                  # visually understand a user’s transaction as it flows across multiple microservice calls
    ├── pom.xml                         # dependencies
    ├── .travis.yml                     # build/deploy pipeline using Travis CI
    └── ...

## Patterns

To be added

# Examples

It's easier to learn with examples! Take a look at the walkthrough guides and sample configuration files below.

## Walkthrough Guides

To be added

## Configuration Files

To be added

# Contributing

To be added

# Changelog

To be added

# Questions & Improvements

- [Submit a Bug Report][github-bug-report]
- [Submit a Feature Request][github-feature-request]
- [Raise an issue][github-new-issue] that is not a bug report or a feature request
- [Contribute a PR][github-pulls]

[github-last-commit]: https://github.com/gohelpfund/gohelpfund-api/commit/HEAD
[github-releases]: https://github.com/gohelpfund/gohelpfund-api/releases
[github-issues]: https://github.com/gohelpfund/gohelpfund-api/issues
[github-issues-closed]: https://github.com/gohelpfund/gohelpfund-api/issues?q=is%3Aissue+is%3Aclosed
[github-pulls]: https://github.com/gohelpfund/gohelpfund-api/pulls
[github-pulls-closed]: https://github.com/gohelpfund/gohelpfund-api/pulls?q=is%3Apr+is%3Aclosed
[helptoken]: https://coinmarketcap.com/currencies/gohelpfund/

[explainer video]: https://www.youtube.com/watch?v=mGXZzwEqLLc
[github-bug-report]: https://github.com/gohelpfund/gohelpfund-api/issues/new
[github-feature-request]: https://github.com/gohelpfund/gohelpfund-api/issues/new
[github-new-issue]: https://github.com/gohelpfund/gohelpfund-api/issues/new