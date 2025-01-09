#!/bin/bash

# Get unique base names (everything before underscore)
for base in $(ls | grep "_" | cut -d"_" -f1 | sort -u); do
    # Create directory if it doesn't exist
    mkdir -p "$base"
    
    # Move all files starting with base name followed by underscore
    mv "$base"_* "$base/"
done

