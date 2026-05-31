#!/bin/bash

echo "Building Simple Banking System..."
mkdir -p bin
javac -d bin -sourcepath src src/Main.java src/model/*.java src/service/*.java src/storage/*.java src/utils/*.java src/report/*.java

if [ $? -eq 0 ]; then
    echo "Build successful! Running application..."
    echo "----------------------------------------"
    java -cp bin Main
else
    echo "Build failed. Please check for errors."
fi
