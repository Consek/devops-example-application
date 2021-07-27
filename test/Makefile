.PHONY: test intialize_services install_dependencies all

all: intialize_services install_dependencies test remove_services

test:
	PATH=$$PATH:~/.local/bin pytest run_tests.py

build_images:
	docker-compose -f ../.deployment/docker-compose.yml build --parallel

intialize_services:
	docker-compose -f ../.deployment/docker-compose.yml up -d backend redis caddy
	docker-compose -f ../.deployment/docker-compose.yml exec -t caddy sh -c "while ! wget backend:8080/healthcheck -q --spider; do sleep 1; done"
	docker-compose -f ../.deployment/docker-compose.yml up -d

install_dependencies:
	pip3 install -r requirements.txt

remove_services:
	docker-compose -f ../.deployment/docker-compose.yml down --remove-orphans
