#!/bin/bash
if [ ${GIT_BRANCH} == "dev" ]; then
   echo "Building from dev (_alpha.xx)!"
   sh ./gradlew.sh build -PisDev=1
elif [ ${GIT_BRANCH} == "master" ];  then
   echo "Building from master (.xx)!"
   sh ./gradlew.sh build
fi