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
	docker build -t user-service . -f ./docker/Dockerfile

dockerRun:
	# docker run --publish 11111:11111 --detach --name zuul zuul
	docker run -it --publish 11300:11300 --name user-service --link eureka-service:eureka-service user-service

dockerStart:
	docker rm -f user-service | true
	docker image rm -f user-service | true
	make dockerBuild
	make dockerRun
	docker ps -a
