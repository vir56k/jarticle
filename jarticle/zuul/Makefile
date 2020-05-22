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
	docker build -t zuul . -f ./docker/Dockerfile

dockerRun:
	# docker run --publish 11111:11111 --detach --name zuul zuul
	docker run -it --publish 11111:11111 --name zuul --link eureka-service:eureka-service zuul

dockerStart:
	docker rm -f zuul | true
	docker image rm -f zuul | true
	make dockerBuild
	make dockerRun
	docker ps -a
