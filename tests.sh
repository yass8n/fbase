#!/bin/bash -e

echo "Branch: $CIRCLE_BRANCH"
echo "Running Gradlew Tests"
./gradlew test

if [[ $CIRCLE_BRANCH = "production" ]] ; then
  echo "Running Production Sanity Tests"
  AppJava="app/src/main/java/com/gthr/android/application/App.java"

  grep 'PRODUCTION = true;' $AppJava | tr -d " \t\n\r" | grep -v "^//"
  grep 'URL = "https://api.gatherwith.us/2.0/";' $AppJava | tr -d " \t\n\r" | grep -v "^//"
  grep 'FB_DATABASE_URL = "https://vivid-inferno-4877.firebaseio.com/";' $AppJava | tr -d " \t\n\r" | grep -v "^//"
  grep 'FBASE_SECRET = "tgNrTtWvun1Cc0kOpPuOZTPsbdvyy4BJOc85WlNV";' $AppJava | tr -d " \t\n\r" | grep -v "^//"
fi

