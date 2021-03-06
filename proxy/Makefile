run:
	python3 server.py

gcp_check:
ifndef PROJECT_ID
	$(error PROJECT_ID is not set)
endif

gcp_build: gcp_check
	gcloud builds submit --tag gcr.io/${PROJECT_ID}/proxy

gcp_deploy_check:
ifndef TARGET_URL
	$(error TARGET_URL is not set)
endif

gcp_cloud_run_deploy: gcp_deploy_check
	gcloud run deploy proxy --image=gcr.io/${PROJECT_ID}/proxy:latest \
		--max-instances=5 \
		--platform=managed \
		--set-env-vars=TARGET_URL=${TARGET_URL} \
		--concurrency=5

gcp_app_engine_deploy: gcp_deploy_check gcp_check
	envsubst '\$$TARGET_URL' < "app.yaml" > "app.yaml.tmp"
	mv "app.yaml.tmp" "app.yaml"
	gcloud app deploy --image-url=gcr.io/${PROJECT_ID}/proxy

gcp_compute_engine_template_creation:
	gcloud compute instance-templates create-with-container proxy \
		--container-env=TARGET_URL=${TARGET_URL} \
		--container-image=gcr.io/${PROJECT_ID}/proxy:latest \
		--description="Devops proxy instance" \
		--machine-type=f1-micro \
		--preemptible \
		--tags=devops

gcp_compute_engine_deploy:
	gcloud compute instance-groups managed create proxy \
		--base-instance-name proxy-vm \
		--size 2 \
		--template proxy

