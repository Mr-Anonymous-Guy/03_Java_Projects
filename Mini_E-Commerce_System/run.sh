#!/bin/bash
echo "Building Mini E-Commerce System..."
mvn clean compile
if [ $? -eq 0 ]; then
    echo "Build successful. Starting application..."
    mvn exec:java
else
    echo "Build failed!"
fi
