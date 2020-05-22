FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd

RUN mkdir -p /article-service
ADD ./target/article-service-0.0.1-SNAPSHOT.jar /article-service/

# 添加一些测试数据
ADD ./data /data

ADD ./run.sh run.sh
RUN chmod +x run.sh

CMD ./run.sh
