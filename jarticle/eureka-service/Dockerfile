FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd

RUN mkdir -p /eurekaserver
ADD ./target/eureka-service-0.0.1-SNAPSHOT.jar /eurekaserver/

ADD ./run.sh run.sh
RUN chmod +x run.sh

CMD ./run.sh
