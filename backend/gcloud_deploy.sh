#!/bin/sh
gcloud run deploy backend --image=gcr.io/${PROJECT_ID}/backend:latest \
    --max-instances=5 \
    --platform=managed \
    --vpc-connector=devops-example \
    --set-env-vars=REDIS_HOST=10.128.183.75
