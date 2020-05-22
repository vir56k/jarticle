#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server($EUREKA_SERVER) to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z $EUREKA_SERVER  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

# echo "********************************************************"
# echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
# echo "********************************************************"
# while ! `nc -z configserver $CONFIGSERVER_PORT`; do sleep 3; done
# echo "*******  Configuration Server has started"

# echo "********************************************************"
# echo "Starting Zuul Service with $CONFIGSERVER_URI"
# echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE \
     -jar /article-service/article-service-0.0.1-SNAPSHOT.jar
