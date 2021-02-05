#!/bin/sh
rm -rf ./dist
npm install
npm run build

#Replace environment variables on bundle with real values
walk_dir () {
    for pathname in "$1"/*; do
        if [ -d "$pathname" ]; then
            walk_dir "$pathname"
        else
            envsubst '\$BACKEND_URL' < "$pathname" > "$pathname.tmp"
            mv "$pathname.tmp" "$pathname"
        fi
    done
}

walk_dir ./dist/frontend

gsutil rsync -r ./dist/frontend gs://devops-example/
gsutil web set -m index.html gs://devops-example
