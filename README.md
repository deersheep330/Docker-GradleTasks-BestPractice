# Docker-GradleTasks-Template

__A demo project shows how to dockerize a Gradle project and build & run Gradle Tasks in Docker.__

*Actually it's not necessary to run Gradle Tasks in Docker since Gradle already handle the project dependencies for you.*

*This demo project is for practice only.*

## Contents

- [Gradle Project Overview](#gradle-project-overview)
- [Dockerize Gradle Project](#dockerize-gradle-project)
- [Demo](#demo)

## Gradle Project Overview

There are __*2 tasks*__ in this Gradle project: __*testGoogle*__ and __*testYahoo*__,
And there are __*3 parameters*__ have to be set in this Gradle project: __*browser*__, __*env*__ and __*machine*__.

The value of __*browser*__ could be: __*Chrome*__ or __*IE*__, which means the tasks would be run on which browser.

The value of __*env*__ could be: __*SIT*__ or __*Stage*__, which means the tasks would test which environment.

The value of __*machine*__ could be: __*local*__ or __*remote*__, which means the tasks would be on a local or remote machine.

We will use Gradle __*-P*__ flag to pass these parameters as project properties. e.g.
```
gradle clean build -Pbrowser=Chrome -Penv=SIT -Pmachine=remote run testGoogle testYahoo copyTestResults
```
will set __browser = Chrome__, __env = SIT__ and __machine = remote__, ans store these values in a .properties file. 

## Dockerize Gradle Project

We'd like to know whether we can dockerize this Gradle project to run the mentioned Gradle tasks in a docker container and still:

- __Benefit from Gradle Build Cache__

Otherwise, every Gradle build in the docker container would fetch the dependencies again, and it could take a long time.
  
- __Archive the test results__

We want to archive the test results on our host machine before the docker container killed.

### Gradle Build Cache

To make every docker container share the same Gradle Build Cache, we have to create a docker volume on our own, and mount it on the gradle build cache path of the docker container. e.g.

```
docker volume create --name gradle-cache
docker run -v gradle-cache:/home/molly/.gradle
```

### Archive Test Results

To store every test results on our host machine, we just need to bind mount a host machine directory with the docker container. e.g.

```
docker run -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive
```

## Demo

The content below demonstrates 3 different scenarios and shows how to

- [Run with Gradle](#run-with-gradle)
- [Run with docker](#run-with-docker)
- [Run with docker-compose](#run-with-docker-compose)

### 3 Scenarios

- __Scenario I__

Pass __browser = Chrome__, __env = SIT__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

- __Scenario II__

Pass __browser = Chrome__, __env = Stage__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

- __Scenario III__

Pass __browser = IE__, __env = SIT__, __machine = local__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

### Run with Gradle

- __Scenario I__

```
gradle clean build -Pbrowser=Chrome -Penv=SIT -Pmachine=remote run testGoogle testYahoo copyTestResults
```

- __Scenario II__

```
gradle clean build -Pbrowser=Chrome -Penv=Stage -Pmachine=remote run testGoogle testYahoo copyTestResults
```

- __Scenario III__

```
gradle clean build -Pbrowser=IE -Penv=SIT -Pmachine=local run testGoogle testYahoo copyTestResults
```

### Run with Docker

We have to mount a gradle cache volume and bind mount a test results archive volume.

Don't worry about the repeat "docker volume create". If a docker volume trying to be created with an existing name, the existing one would be directly used, no new volume would be created. 

- __Scenario I__

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test SIT Chrome remote
```

- __Scenario II__

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test Stage Chrome remote
```

- __Scenario III__

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test SIT IE local
```

### Run with docker-compose

We still have to mount a gradle cache volume and bind mount a test results archive volume.

We can hide the mount syntax in the docker-compose.yml file, but the creating external docker volume command cannot be written in docker-compose.yml file, we still have to execute it explicitly. 

- __Scenario I__

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.sit.chrome.yml up --build
```

- __Scenario II__

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.stage.chrome.yml up --build
```

- __Scenario III__

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.sit.ie.local.yml up --build
```
