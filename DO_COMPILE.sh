#!/bin/bash

ANT_PROP=""
ANT_JDK=""
ACTION_DEFAULT="build jar"
ACTION_ALL="rebuild clean jar"
ACTION=${ACTION_DEFAULT}
DEBUG=""

usage() {
	echo "usage: $0 [all] [-d|--debug] [-p|--prop] [-j|--jdk] [all] [-h|--help]"
	echo "       all       : build all (including copying additional files)"
	echo "       -d|--debug: turn debugging on (be vorbose"
	echo "       -p|--prop : try to find build.properties in most recent ant version and use that version"
	echo "       -j|--jdk  : find JDK_PLATFORM"
	echo "       -h|--help : this help"
}

setAntProp() {
	## try to find actual netbeans version on the fly
	ANT_VERSION=`find ${HOME}/.netbeans/ -name build.properties | sed -e "s#${HOME}/.netbeans/##" -e "s#/build.properties##"|sort -t \. -k 1,1n -k 2,2n -k 3,3n|tail -1`
	ANT_PROP="-Duser.properties.file=${HOME}/.netbeans/${ANT_VERSION}/build.properties"
	#ANT_PROP=""
}

setAntJDK() {
	JDK_VERSION=`grep "platform.active" nbproject/project.properties | awk -F= '{print $2}'`
	# echo "JDK_VERSION=${JDK_VERSION}"
	JAVAC=`which javac`
	JDK_DIR=`echo ${JAVAC}|sed -e "s#/bin/javac##"`
	if [ -L "${JDK_DIR}" ] ; then
		NEW_DIR=`readlink -f "${JDK_DIR}"`
		if [ "${NEW_DIR}" != "${JDK_DIR}" ] ; then
			JDK_DIR="${NEW_DIR}"
		fi
	fi
	ANT_JDK="-Dplatforms.${JDK_VERSION}.home=${JDK_DIR}"
}

setAntAction() {
	ANT_ACTION="-Dnb.internal.action.name=${ACTION}"
}

for arg in "$@" ; do
	case "$arg" in
	all)
		ACTION="${ACTION_ALL}"
		;;
	-d|--debug)
		echo "turn debugging on"
		DEBUG="1"
		;;
	-p|--prop)
		setAntProp
		;;
	-j|--jdk)
		setAntJDK
		;;
	-h|--help)
		usage
		exit 0
		;;
	*)
		echo "Unknown option \"$arg\""
		usage
		exit 1
		;;
	esac
done
setAntAction

if [ ! -z "${DEBUG}" ] ; then
	echo "ANT_PROP=${ANT_PROP}"
	echo "ANT_JDK=${ANT_JDK}"
	echo "ANT_ACTION=${ANT_ACTION}"
fi

ant -f . ${ANT_JDK} ${ANT_PROP} ${ANT_ACTION}
ANT_RET=$?
[ ! -z "${DEBUG}" ] && echo "ANT_RET=${ANT_RET}"
if [ 0 -ne ${ANT_RET} ] ; then
	exit ${ANT_RET}
fi
if [ "${ACTION}" == "${ACTION_ALL}" ] ; then
	[ ! -z "${DEBUG}" ] && echo "LRLRLR ALLES"
	[ -f ADD_FILES_TO_DIST.sh ] && ./ADD_FILES_TO_DIST.sh
fi

