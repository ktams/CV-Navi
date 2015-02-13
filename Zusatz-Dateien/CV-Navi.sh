#!/bin/bash

# Linux-Startumgebung fuer CV-Navi einrichten
#
# Installation:
# - java muss installiert sein
# - die rxtx-Bibliothek der Distribution muss installiert sein (s.u.)
# - der angemeldete Benutzer muss Zugriffsberechtigung 
#   auf die seriellen bzw. USB-Devices haben (s.u.)
# - ggf auch die Berechtigung fuer "lock" im Verzeichnis /var/lock zu setzen (s.u.)
# - diese Datei muss ausfuehrbar sein, damit sie korrekt funktioniert:
#     chmod +x CV-Navi.sh



# V 0.10 20150213 Lothar
# - Sprache: Auswertung der Umgebumgsvariable LANG (z.B. LANG=de_DE.utf8)
#            und Übergabe an Java

# V 0.09 20150130 Lothar
# - Erweiterung fuer OS-X - Tests

# V 0.08 20150108 Lothar
# - armv7l - Platform ergänzt (Raspbian auf BananaPi)

# V 0.07 20141104 Lothar
# - armv6l - Platform ergänzt (Raspbian auf RaspberryPi)

# V 0.06 20140628 Lothar
# - Pfad zur jar-Datei darf Leerzeichen enthalten

# V 0.05 20140615 Lothar
# - erste Unterstuetzung von Kommandozeilenparametern

# V 0.04 20120824 Lothar
# - Ergaenzungen zu SuSE, Redhat, CentOS

# V 0.03 20120817 Lothar
# - fuer dieses Script bash als Shell verwenden
# - Systemtyperkennung umgestellt

# V 0.02 20120715 Lothar
# - ggf. aufloesen von symlinks

# V 0.01 20120708 Lothar
# - erste Tests , Programmsuche noch unvollstaendig


# Distributionspezifische Dinge zur Einrichtung
#
#
# Jeweils als root oder mit root-Rechten (sudo) ausfuehren:
#
# Fedora (14/16/17/18/19/20):
#
#   yum install rxtx
#   usermod -a -G dialout <user>
#   usermod -a -G lock    <user>
#
# Redhat-Server, CentOS:
#
#   die rxtx-Biblothek muss ueber externe (inoffizielle) Repositories (-> epel) installiert werden
#   -> Benutzerrechte: siehe Fedora
#
#
# Debian (6), Ubuntu (12.04), Raspbian (RaspberryPi, BananaPi):
#
#   apt-get install librxtx-java
#   usermod -a -G dialout <user>
#
#
# SuSe (ohne Gewaehr, getestet mit 11.3 und 12.1):
#
#   Das Paket "rxtx-java" ist nicht direkt in der Distribution !
#   Es kann aber ueber zypper hinzuegfuegt werden
#  als root:
#   zypper addrepo http://download.opensuse.org/repositories/Application:/Geo/openSUSE_11.3/Application:Geo.repo
#   zypper install rxtx-java
#   usermod -A dialout <user>
#   usermod -A lock    <user>
#
#  Falls Dateisystemrechte fuer das Verzeichnis /var/lock bei
#   ls -la /var | grep lock
#  nicht
#   "drwxrwxr-x. root lock ..."
#  sind, dann (als root):
#   chown :lock /var/lock
#   chmod g+w /var/lock


DEBUG=${1-0}

# skip 1st parameter if it was "1"
[ $DEBUG == 1 ]&& shift


PROG_NAME="KlarText.jar"
PROG_DIR="."

LIB_NAME="librxtxSerial.so"
LIB_JNINAME="librxtxSerial.jnilib"
LIB_DIR="."
LIB_FOUND=0


if test "x$JAVA_HOME" = "x"; then
    JAVA=java
else
    JAVA=$JAVA_HOME/bin/java
fi

if [ -z "${JAVA_OPTS=}" ] ; then
	[ $DEBUG == 1 ] && echo "INIT JAVA_OPTS"
	JAVA_OPTS=""
else
	[ $DEBUG == 1 ] && echo "JAVA_OPTS=${JAVA_OPTS}"
fi

# parse LANG into java options
if [ ! -z "${LANG}" ]; then
	LANG_PARTS=`echo ${LANG} | sed -e 's#_# #' -e 's#\.# #'`
	myNumLangParts=`echo ${LANG_PARTS} | wc -w`
	[ $DEBUG == 1 ] && echo "myNumLangParts=${myNumLangParts}"
	if [ ${myNumLangParts} -ge 2 ] ; then
		myLANG=`echo ${LANG_PARTS} | cut -d " " -f 1`
		myCOUNTRY=`echo ${LANG_PARTS} | cut -d " " -f 2`
		JAVA_OPTS="${JAVA_OPTS} -Duser.language=${myLANG} -Duser.country=${myCOUNTRY}"
		[ $DEBUG == 1 ] && echo "JAVA_OPTS=${JAVA_OPTS}"
	fi
fi

[ $DEBUG == 1 ]&& echo "calling \$0=$0"
# get the absolute path of the script (real calling path)
SELF_PATH=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
[ $DEBUG == 1 ] && echo "SELF_PATH=${SELF_PATH}"

# resolve symlinks
while [ -h "$SELF_PATH/$(basename -- "$0")" ]; do
	# 1) cd to directory of the symlink
	# 2) cd to the directory of where the symlink points
	# 3) get the pwd
	DIR=$(dirname -- "$SELF_PATH/$(basename -- "$0")")
	SYM=$(readlink $SELF_PATH/$(basename -- "$0"))
	SELF_PATH=$(cd "$DIR" && cd "$(dirname -- "$SYM")" && pwd)
done
[ $DEBUG == 1 ]&& echo "SELF_PATH=${SELF_PATH}"

PROG_DIR=${SELF_PATH}
[ $DEBUG == 1 ]&& echo "PROG_DIR=$PROG_DIR"

DIRLISTE_x86_64="/usr/lib64/rxtx   /usr/lib64     /usr/lib/jni	${PROG_DIR}/lib	${PROG_DIR}"
DIRLISTE_i386="  /usr/lib/rxtx     /usr/lib       /usr/lib/jni	${PROG_DIR}/lib	${PROG_DIR}"

SYSTEMTYPE=`uname -m`
if [ $DEBUG == 1 ]; then
  echo "SYSTEMTYPE=$SYSTEMTYPE"

  PROCESSOR=`uname -p`
  echo "PROCESSOR=$PROCESSOR"
  HARDWARE=`uname -i`
  echo "HARDWARE=$HARDWARE"
  OPERATINGSYSTEM=`uname -o`
  echo "OPERATINGSYSTEM=$OPERATINGSYSTEM"
fi

if [ -z ${JAVA_LIBRARY_PATH} ] ; then
	[ $DEBUG == 1 ]&& echo "Initialisiere JAVA_LIBRARY_PATH"
	JAVA_LIBRARY_PATH="."
fi

case "$SYSTEMTYPE" in
	x86_64)
		SEARCH_LIST="${DIRLISTE_x86_64}"
		ARCH_EXTENSION=64
	;;
	i386|i686|armv6l|armv7l)
		SEARCH_LIST="${DIRLISTE_i386}"
		ARCH_EXTENSION=""
	;;
	*)
		echo "currently unsupported system type $SYSTEMTYPE"
		# try all paths (instead of exiting)
		SEARCH_LIST="${DIRLISTE_x86_64} ${DIRLISTE_i386}"	
		# return 1
esac

# echo SEARCH_LIST="${SEARCH_LIST}"

for d in ${SEARCH_LIST} ; do
	[ $DEBUG == 1 ]&& echo "Suche Bibliothek in: " ${d}/${LIB_NAME}
	if [ -f ${d}/${LIB_NAME} ] ; then
		[ $DEBUG == 1 ] && echo "Bibliothek gefunden:  ${d}/${LIB_NAME}"
		JAVA_LIBRARY_PATH="${d}:${JAVA_LIBRARY_PATH}"
		LIB_FOUND=1
		break;
	fi
done

if [ ${LIB_FOUND} == 0 ] ; then
	for d in ${SEARCH_LIST} ; do
		[ $DEBUG == 1 ]&& echo "Suche Bibliothek in: " ${d}/${LIB_JNINAME}
		if [ -f ${d}/${LIB_JNINAME} ] ; then
			[ $DEBUG == 1 ] && echo "Bibliothek gefunden:  ${d}/${LIB_JNINAME}"
			JAVA_LIBRARY_PATH="${d}:${JAVA_LIBRARY_PATH}"
			LIB_FOUND=1
			break;
		fi
	done
fi

if [ ${LIB_FOUND} == 0 ] ; then
	echo "Bibliothek ${LIB_NAME} nicht gefunden"
	if [ $DEBUG == 1 ] ; then
		echo "Versuche trotzdem zu starten..."
	else
		echo "Bitte lesen Sie die Hinweise am Beginn von $0"
		exit 1
	fi
fi
	
if [ $DEBUG == 1 ] ; then
	echo $JAVA -Djava.library.path=${JAVA_LIBRARY_PATH}  ${JAVA_OPTS} -jar "$PROG_DIR/$PROG_NAME" $*
fi

$JAVA -Djava.library.path=${JAVA_LIBRARY_PATH} ${JAVA_OPTS} -jar "$PROG_DIR/$PROG_NAME" $*
