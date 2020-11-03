# Docker-GradleTasks-Template

__A demo project shows how to dockerize a Gradle project and build & run Gradle Tasks in Docker.__

*Actually it's not necessary to run Gradle Tasks in Docker since Gradle already handle the project dependencies for you.*

*This demo project is for practice only.*

## Contents

- [Gradle Project Overview](#gradle-project-overview)
- [Dockerize Gradle Project](#dockerize-gradle-project)
- [Demo](#demo)

## Gradle Project Overview

There are 2 tasks in this Gradle project: testGoogle and testYahoo,
And there are 3 parameters have to be set in this Gradle project: browser, env and machine.

The value of browser could be: Chrome or IE, which means the tasks would be run on which browser.
The value of env could be: SIT or Stage, which means the tasks would test which environment.
The value of machine could be: local or remote, which means the tasks would be on a local or remote machine.

We will use Gradle -P flag to pass these parameters as project properties. e.g.
```
gradle clean build -Pbrowser=Chrome -Penv=SIT -Pmachine=remote run testGoogle testYahoo copyTestResults
```
will set browser = Chrome, env = SIT and machine = remote, ans store these values in a .properties file. 

## Dockerize Gradle Project

We'd like to know whether we can dockerize this Gradle project to run the mentioned Gradle tasks in a docker container and still:
(1) benefit from Gradle Build Cache (otherwise every Gradle build in the docker container would take long time)  
(2) archive the test results (we want to archive the test results on our host machine before the docker container killed)

- __Gradle Build Cache__

docker volume create --name gradle-cache

docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive

- __Archive Test Results__

docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive

## Demo

The content below demonstrates 3 different scenarios and shows how to
 [run with Gradle](#run-with-gradle)
 [run with docker](#run-with-docker) and
 [run with docker-compose](#run-with-docker-compose).

## 3 Scenarios

- __Scenario I__

Pass __browser = Chrome__, __env = SIT__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

- __Scenario II__

Pass __browser = Chrome__, __env = Stage__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

- __Scenario III__

Pass __browser = IE__, __env = SIT__, __machine = local__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

## Run Gradle Tasks with 3 Different Approaches

- [Run with Gradle](#run-with-gradle)
- [Run with Docker](#run-with-docker)
- [Run with docker-compose](#run-with-docker-compose)

## Run with Gradle

- __Scenario I__

Pass __browser = Chrome__, __env = SIT__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
gradle clean build -Pbrowser=Chrome -Penv=SIT -Pmachine=remote run testGoogle testYahoo copyTestResults
```

- __Scenario II__

Pass __browser = Chrome__, __env = Stage__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
gradle clean build -Pbrowser=Chrome -Penv=Stage -Pmachine=remote run testGoogle testYahoo copyTestResults
```

- __Scenario III__

Pass __browser = IE__, __env = SIT__, __machine = local__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
gradle clean build -Pbrowser=IE -Penv=SIT -Pmachine=local run testGoogle testYahoo copyTestResults
```

## Run with Docker

- __Scenario I__

Pass __browser = Chrome__, __env = SIT__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test SIT Chrome remote
```

- __Scenario II__

Pass __browser = Chrome__, __env = Stage__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test Stage Chrome remote
```

- __Scenario III__

Pass __browser = IE__, __env = SIT__, __machine = local__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker build -t browser-test .
docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test SIT IE local
```

## Run with docker-compose

- __Scenario I__

Pass __browser = Chrome__, __env = SIT__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.sit.chrome.yml up --build
```

- __Scenario II__

Pass __browser = Chrome__, __env = Stage__, __machine = remote__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.stage.chrome.yml up --build
```

- __Scenario III__

Pass __browser = IE__, __env = SIT__, __machine = local__ to Gradle, and run testGoogle, testYahoo tasks, finally copy the test result to archive folder.

```
docker volume create --name gradle-cache
docker-compose -f docker-compose.yml -f docker-compose.sit.ie.local.yml up --build
```