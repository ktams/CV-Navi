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

PROG_DIR="${BASEDIR}/dist"
PACKAGE_DIR="${BASEDIR}/package"
OTHER_LIBDIR="${BASEDIR}/Zusatz-Dateien"
BIBS_DIR="${BASEDIR}/Zusatz_Bibs"

## ALT: alle Dateien kopieren
# cp -pr ${OTHER_LIBDIR}/* ${PROG_DIR}
# NEU: nur noch wenige Dateien notwendig
FILE_LIST_OL_DIR="
LiesMich.txt
CV-Navi.sh
"

FILE_LIST_BIBS="
rxtxSerial.dll
rxtxSerial64.dll
librxtxSerial.jnilib
librxtxSerial64.jnilib
"



[ ! -d ${PROG_DIR} ] && mkdir -p ${PROG_DIR}

for f in ${FILE_LIST_OL_DIR}; do
	echo "copying ${f}"
	[ -f ${OTHER_LIBDIR}/$f ] && cp -p ${OTHER_LIBDIR}/$f ${PROG_DIR}
done

for f in ${FILE_LIST_BIBS}; do
	echo "copying ${f}"
	[ -f ${BIBS_DIR}/$f ] && cp -p ${BIBS_DIR}/$f ${PROG_DIR}
done

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
