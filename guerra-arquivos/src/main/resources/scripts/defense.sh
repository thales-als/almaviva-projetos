#!/bin/bash

monitored_dir="./thales"

mkdir -p "$monitored_dir"

echo "Monitoring directory: $monitored_dir"

while true; do
  for file in "$monitored_dir"/*; do
    if [[ -f "$file" ]]; then
      echo "Deleting file: $file"
      rm -f "$file"
    fi
  done

  sleep 0.1
done