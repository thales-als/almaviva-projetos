#!/bin/bash

monitored_dir="/home/thales-almaviva/GuerraArquivos/"

mkdir -p "$monitored_dir"

echo "Monitoring directory: $monitored_dir"

inotifywait -m -r -e create,delete,modify "$monitored_dir" | while read -r path _ file; do
  find "$monitored_dir" -mindepth 1 -delete
done
