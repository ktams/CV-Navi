#!/bin/bash

usage() {
	echo "$0 [-h] [version]"
	echo "version: optional parameter to append to date"
	exit 1
}
if [ ! -z "${1}" ] ; then
	case ${1} in
		-h|-H)
			usage
			;;
	esac
fi

DIST_DIR=dist
CREATE_PKG=CV-Navi

ISODATE=`date +'%Y%m%d'`

V_MAJOR=`cat src/my/KlarText/KlarTextUI.java | grep public | grep gsVersionMayor | sed -e 's/;//' | awk '{print $NF}'`
V_MINOR=`cat src/my/KlarText/KlarTextUI.java | grep public | grep gsVersionMinor | sed -e 's/;//' | awk '{print $NF}'`
V_STATUS=`cat src/my/KlarText/KlarTextUI.java | grep "String gsBuild" | sed -e 's#"# #g' | awk '{print $4}'`

if [ -d ${CREATE_PKG} ] ; then
	rm -rf ${CREATE_PKG}
fi
mkdir ${CREATE_PKG}
cp -pr ${DIST_DIR}/* ${CREATE_PKG}/

tar cvzf CV-Navi_${V_MAJOR}.${V_MINOR}_${V_STATUS}_${ISODATE}${1}.tar.gz ${CREATE_PKG}
zip -r   CV-Navi_${V_MAJOR}.${V_MINOR}_${V_STATUS}_${ISODATE}${1}.zip    ${CREATE_PKG}

rm -rf ${CREATE_PKG}
