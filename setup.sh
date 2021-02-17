#!/bin/sh

# Install dependencies
./gradlew assemble

# Build/Compile & Run unit test suite
./gradlew clean build

# Run Test Suit to validate
./parking_lot