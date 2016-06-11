#!/bin/bash

## try to find actual netbeans version on the fly
ANT_VERSION=`find ${HOME}/.netbeans/ -name build.properties | sed -e "s#${HOME}/.netbeans/##" -e "s#/build.properties##"|sort -t \. -k 1,1n -k 2,2n -k 3,3n|tail -1`

if [ -z ${1} ] ; then
  ant -f . -Duser.properties.file=${HOME}/.netbeans/${ANT_VERSION}/build.properties -Dnb.internal.action.name=build jar
else
  if [ ${1} == all ] ; then
    ant -f . -Duser.properties.file=${HOME}/.netbeans/${ANT_VERSION}/build.properties -Dnb.internal.action.name=rebuild clean jar
    [ -f ADD_FILES_TO_DIST.sh ] && ./ADD_FILES_TO_DIST.sh
  fi
fi
