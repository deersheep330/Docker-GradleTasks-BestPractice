# SEP-Regression

docker volume create --name gradle-cache

docker build -t test .

docker run -v gradle-cache:/home/molly/.gradle -it test

docker run -v gradle-cache:/home/molly/.gradle -it test Stage IE

docker run --rm -i -v gradle-cache:/tmp/myvolume busybox find /tmp/myvolume

docker-compose -f docker-compose.yml -f docker-compose.stage.chrome.yml up --build