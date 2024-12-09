#!/bin/bash

monitored_dir="/home/thales-almaviva/GuerraArquivos/"

mkdir -p "$monitored_dir"

echo "Monitoring directory: $monitored_dir"

while true; do
  for item in "$monitored_dir"/*; do
    if [[ -e "$item" ]]; then
      echo "Deleting: $item"
      rm -rf "$item"
    fi
  done
done
