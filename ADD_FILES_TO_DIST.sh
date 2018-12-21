#!/bin/sh

# moegliche Basisverzeichnisse der Quellen
BASEDIRS="
.
"

for d in ${BASEDIRS}; do
#	echo "Testing: ${d}"
	if [ -d ${d} ] ; then
#		echo "BASEDIR detected"
		BASEDIR=${d}
		continue
	fi
done

if [ -z ${BASEDIR} ] ; then
	echo "Kann kein Basisverzeichnis ermitteln !!! ABBRUCH"
	exit 1
fi

PACKAGE_DIR="${BASEDIR}/package"

# Update Changelog.txt in Package directory
# first check if we have a git changelog available
git whatchanged >/dev/null 2>/dev/null
RES=$?
if [ $RES -eq 0 ] ; then
  [ ! -d ${PACKAGE_DIR} ] && mkdir -p ${PACKAGE_DIR}
  echo "create/update: ChangeLog.txt"
  # git whatchanged | grep -v -E "^:|^commit|^Author|^Date|^$"|tac > ${PACKAGE_DIR}/ChangeLog.txt
  git whatchanged | grep -v -E "^:|^commit|^Author|^$" > ${PACKAGE_DIR}/ChangeLog.txt
else
  echo "git whatchanged - failed with errorlevel "${RES}
fi
