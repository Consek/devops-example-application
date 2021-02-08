run:
	python3 server.py

gcp_check:
ifndef PROJECT_ID
	$(error PROJECT_ID is not set)
endif

gcp_build: gcp_check
	gcloud builds submit --tag gcr.io/${PROJECT_ID}/proxy

gcp_cloud_run_deploy:
ifndef TARGET_URL
	$(error TARGET_URL is not set)
endif
	gcloud run deploy proxy --image=gcr.io/${PROJECT_ID}/proxy:latest \
		--max-instances=5 \
		--platform=managed \
		--set-env-vars=TARGET_URL=${TARGET_URL} \
		--concurrency=5

