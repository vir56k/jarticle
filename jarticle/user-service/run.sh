#!/bin/sh

echo "********************************************************"
echo "Waiting for the eureka server($EUREKA_SERVER) to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z $EUREKA_SERVER  $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Starting user-service Service                           "
echo "Using profile: $PROFILE"
echo "********************************************************"


java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE \
     -jar /user-service/user-service-0.0.1-SNAPSHOT.jar
