FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd

RUN mkdir -p /zuul
ADD ./target/zuul-0.0.1-SNAPSHOT.jar /zuul/

ADD ./run.sh run.sh
RUN chmod +x run.sh

CMD ./run.sh
