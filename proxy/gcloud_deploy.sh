#!/bin/sh
gcloud run deploy proxy --image=gcr.io/${PROJECT_ID}/proxy:latest \
    --max-instances=5 \
    --platform=managed \
    --set-env-vars=TARGET_URL=https://backend-6axrks6l2q-ew.a.run.app
