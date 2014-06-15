#!/bin/bash
# This script is for CW's jenkins CI system that took a lot of time to set up
# If you are building this mod yourself, just ignore this...
echo "Preparing to build"
if [ ${GIT_BRANCH} == "origin/dev" ]; then
   echo "Building from dev (_alpha.xx)!"
   sh "./gradlew build -PisDev=1 -S"
elif [ ${GIT_BRANCH} == "origin/master" ];  then
   echo "Building from master (.xx)!"
   sh "./gradlew build -S"
fi