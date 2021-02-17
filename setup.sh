#!/bin/sh

# Install dependencies
./gradlew assemble

# Build/Compile & Run unit test suite
./gradlew clean build