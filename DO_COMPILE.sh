if [ -z ${1} ] ; then
  ant -f . -Duser.properties.file=${HOME}/.netbeans/8.0/build.properties -Dnb.internal.action.name=build jar
else
  if [ ${1} == all ] ; then
    ant -f . -Duser.properties.file=${HOME}/.netbeans/8.0/build.properties -Dnb.internal.action.name=rebuild clean jar
    [ -f ADD_FILES_TO_DIST.sh ] && ./ADD_FILES_TO_DIST.sh
  fi
fi
