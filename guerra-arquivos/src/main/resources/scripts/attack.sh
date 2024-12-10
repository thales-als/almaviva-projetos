#!/bin/bash

interval=0.00001
count=0
max_files=100
output_dir="./GuerraArquivos/thales"

while ((count < max_files)); do
  mkdir -p "$output_dir"
  timestamp=$(date +%Y-%m-%d_%H-%M-%S.%3N)
  filename="$output_dir/thales_$timestamp.txt"

  touch "$filename"

  ((count++))

  sleep "$interval"
done
