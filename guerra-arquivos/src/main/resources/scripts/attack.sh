#!/bin/bash

output_dir="./GuerraArquivos/thales"
mkdir -p "$output_dir"

while true; do
  timestamp=$(date +%Y-%m-%d_%H-%M-%S.%3N)
  touch "$output_dir/thales_$timestamp"
  sleep 0.00001
done