## Docker local
docker build -t casino-app -f .docker\Dockerfile.jvm
docker run -it -p 80:80 casino-app