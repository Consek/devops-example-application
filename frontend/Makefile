build: clean
	npm install
	npm run build

clean:
	@rm -rf ./dist

env_substitution:
ifndef BACKEND_URL
	$(error BACKEND_URL is not set)
endif
	@./scripts/envsubstitution.sh

run:
	ng serve

gcp_bucket_upload:
ifndef BUCKET_NAME
	$(error BUCKET_NAME is not set)
endif
	gsutil rsync -r ./dist/frontend gs://${BUCKET_NAME}/
	gsutil web set -m index.html gs://${BUCKET_NAME}
