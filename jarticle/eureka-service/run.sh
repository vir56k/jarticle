#!/bin/sh
echo "********************************************************"
echo "Starting the Eureka Server"
echo "输出环境变量 PORT=${PORT},PROFILE=${PROFILE}"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /eurekaserver/eureka-service-0.0.1-SNAPSHOT.jar
