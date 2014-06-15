#!/bin/bash
# This script is for CW's jenkins CI system that took a lot of time to set up
# If you are building this mod yourself, just ignore this...
if [ ${GIT_BRANCH} == "dev" ]; then
   echo "Building from dev (_alpha.xx)!"
   sh ./gradlew.sh build -PisDev=1 -S
elif [ ${GIT_BRANCH} == "master" ];  then
   echo "Building from master (.xx)!"
   sh ./gradlew.sh build -S
fi