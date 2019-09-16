#!/usr/bin/env bash

# The script for pre-deployment tasks during a Travis CI deployment build of
# this project.
#
# This script is designed for execution in a Travis CI build environment only.
# If you want to build an artifact from the source files, please refer to build
# instructions in this project's documentation. If you want to test this
# script, please execute command `export TRAVIS=true` before you start.
#
# Requirements of correct execution of this script:
# - Global variable `$TRAVIS_BRANCH` which stores the mod version of the
#   current build with "v" prefix must be defined in the environment.

# Performs pre-deployment tasks.
pre_deploy() {
  # Deletes source JAR because we have zip and tarball of sources for a release
  (
    # Suggested by https://github.com/koalaman/shellcheck/wiki/SC2164
    cd build/libs || exit
    find . -type f -iregex ".*-sources\.jar" -delete
  )

  # Removes the "v" prefix in the version tag
  local version_number=${TRAVIS_BRANCH:1:${#TRAVIS_BRANCH}}

  # Replaces placeholders in update JSON file with release information
  sed -i -e "s/<mod-ver>/$version_number/g" .travis/pages/promotions.json

  # Marks completion of pre-deployment tasks
  touch before_deploy_complete
}

main() {
  if ! [ "$TRAVIS" = true ]; then
    echo "This script is intended to be run only in a Travis CI build environment. You should not run this in any other environment."
    echo "If you intend to do so, please run 'export TRAVIS=true' first."
    exit 1
  fi

  # The pre-deployment tasks need to be run only once, so this check is added
  # in case there are multiple providers in deployment
  if [ -f before_deploy_complete ]; then
    echo "Pre-deployment tasks already done"
  else
    pre_deploy
  fi
}

main
