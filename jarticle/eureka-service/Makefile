build:
	# docker-compose build article-service

# up:
# 	docker-compose up -d article-service
#
# run:
# 	docker-compose up article-service

justBuild:
	./mvnw clean package -Dmaven.test.skip=true

justRun:
	./mvnw clean spring-boot:run

dockerBuild:
	make justBuild
	docker build -t eureka-service . -f ./docker/Dockerfile

dockerRun:
	docker run --publish 8761:8761 --detach --name eureka-service eureka-service

dockerStart:
	docker rm -f eureka-service | true
	docker image rm -f eureka-service | true
	make dockerBuild
	make dockerRun
	docker ps -a
