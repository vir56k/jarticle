down_all:
	docker-compose down --rmi all -v --remove-orphans

stop_all:
	docker-compose stop

up_datebase:
	docker-compose up -d database

up_rabbitmq:
	docker-compose up -d rabbitmq

up_all:
	docker-compose rm -f -v -s eureka | true
	docker-compose rm -f -v -s zuul | true
	docker-compose rm -f -v -s user | true
	docker-compose rm -f -v -s article | true
	docker-compose up -d --remove-orphans --build eureka
	docker-compose up -d --remove-orphans --build zuul
	docker-compose up -d --remove-orphans --build user
	docker-compose up -d --remove-orphans --build article

up_eureka:
	cd eureka-service && make justBuild
	docker-compose rm -f -v -s eureka | true
	docker-compose up -d --remove-orphans --build eureka

up_zuul:
	cd zuul && make justBuild
	docker-compose rm -f -v -s zuul | true
	docker-compose up -d --remove-orphans --build zuul

up_user:
	cd user-service && make justBuild
	docker-compose rm -f -v -s user | true
	docker-compose up  -d --remove-orphans --build user

up_article:
	cd article-service && make justBuild
	docker-compose rm -f -v -s article | true
	docker-compose up -d --remove-orphans --build article
