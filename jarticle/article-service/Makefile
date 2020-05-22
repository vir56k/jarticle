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
	docker build -t article-service . -f ./docker/Dockerfile

dockerRun:
	# docker run --publish 11111:11111 --detach --name zuul zuul
	docker run -it --publish 11400:11400 --name article-service --link eureka-service:eureka-service article-service

dockerStart:
	docker rm -f article-service | true
	docker image rm -f article-service | true
	make dockerBuild
	make dockerRun
	docker ps -a
