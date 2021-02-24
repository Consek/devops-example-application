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

gcp_deploy_check:
ifndef REDIS_HOST
	$(error REDIS_HOST is not set)
endif

gcp_cloud_run_deploy: gcp_deploy_check
ifndef REDIS_HOST
	$(error REDIS_HOST is not set)
endif
	gcloud run deploy backend --image=gcr.io/${PROJECT_ID}/backend:latest \
		--max-instances=5 \
		--platform=managed \
		--vpc-connector=${VPC_CONNECTOR} \
		--set-env-vars=REDIS_HOST=${REDIS_HOST} \
		--concurrency=5

gcp_app_engine_deploy: gcp_deploy_check gcp_check
	envsubst '\$$REDIS_HOST' < "app.yaml" > "app.yaml.tmp"
	mv "app.yaml.tmp" "app.yaml"
	gcloud app deploy --image-url=gcr.io/${PROJECT_ID}/backend

gcp_compute_engine_template_creation:
	gcloud compute instance-templates create-with-container backend \
		--container-env=REDIS_HOST=${REDIS_HOST} \
		--container-image=gcr.io/${PROJECT_ID}/backend:latest \
		--description="Devops backend instance" \
		--machine-type=f1-micro \
		--preemptible \
		--tags=devops

gcp_compute_engine_deploy:
	gcloud compute instance-groups managed create backend \
		--base-instance-name backend-vm \
		--size 3 \
		--template backend 
