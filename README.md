# Docker-GradleTasks-Template

__A demo project shows how to dockerize a Gradle project and build & run Gradle Tasks in Docker.__

*Actually it's not necessary to run Gradle Tasks in Docker since Gradle already handle the project dependencies for you.*

*This demo project is for practice only.*

docker volume create --name gradle-cache

docker build -t browser-test .

docker run -v gradle-cache:/home/molly/.gradle -v /C/Code/docker-gradle-template/archive:/home/molly/app/archive -it browser-test SIT Chrome local
docker run -v gradle-cache:/home/molly/.gradle -it test Stage IE

docker run --rm -i -v gradle-cache:/tmp/myvolume busybox find /tmp/myvolume

docker-compose -f docker-compose.yml -f docker-compose.stage.chrome.yml up --build

## Contents

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