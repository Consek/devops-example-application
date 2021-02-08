build:
	./mvnw clean package

run:
	java -jar ./target/*.jar

gcp_check:
ifndef PROJECT_ID
	$(error PROJECT_ID is not set)
endif

gcp_build: gcp_check
	gcloud builds submit --tag gcr.io/${PROJECT_ID}/backend

gcp_cloud_run_deploy:
ifndef REDIS_HOST
	$(error REDIS_HOST is not set)
endif
	gcloud run deploy backend --image=gcr.io/${PROJECT_ID}/backend:latest \
		--max-instances=5 \
		--platform=managed \
		--vpc-connector=${VPC_CONNECTOR} \
		--set-env-vars=REDIS_HOST=${REDIS_HOST} \
		--concurrency=5

