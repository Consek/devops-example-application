#!/bin/sh

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

walk_dir $1

# Start nginx
exec nginx -g "daemon off;"
