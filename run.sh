#!/bin/bash

# Function to build the JAR file using Gradle
build_jar() {
    echo "Building AIS JAR..."
    ./AISCollector/gradlew build
    echo "Building RSE JAR..."
    ./RouteStrategyEngine/gradlew build
    echo "Build complete."
}

# Function to start the application using Docker Compose
start_docker() {
    echo "Starting Docker Compose..."
    docker-compose up --build
    echo "Docker Compose started."
}

# Check the script arguments
case "$1" in
    build)
        build_jar
        ;;
    start)
        start_docker
        ;;
    all)
        build_jar
        start_docker
        ;;
    *)
        echo "Usage: $0 {build|start|all}"
        exit 1
        ;;
esac

exit 0
