#!/bin/bash

# Linux-Startumgebung fuer CV-Navi einrichten
#
# Installation:
# - java muss installiert sein
# - der angemeldete Benutzer muss Zugriffsberechtigung 
#   auf die seriellen bzw. USB-Devices haben (s.u.)
# - ggf auch die Berechtigung fuer "lock" im Verzeichnis /var/lock zu setzen (s.u.)
# - diese Datei muss ausfuehrbar sein, damit sie korrekt funktioniert:
#     chmod +x CV-Navi.sh


# V 0.14 20190101 Lothar
# - Falls java nicht im Pfad gefunden wird automatische Suche in bekannten Installationspfaden (auch mit Leerzeichen -> OS-X)

# V 0.13 20180601 Lothar
# - Hinweise auf rxtx-Installationspakete entfernt
# - Hinweise zu OS-X hinzugefuegt (OS-X ist noch nicht vollstaendig getestet)

# V 0.12 20180522 Lothar
# - wegen Umstellung von rxtx auf purejavacomm Pfadsuche entfernt

# V 0.11 20160726 Lothar
# - Pfad fuer rxtx-Bibliothek unter gentoo hinzugefuegt (von Markus F.)

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
# Jeweils als root oder mit root-Rechten (sudo) ausfuehren:
#
# Fedora, Redhat-Server, CentOS:
#
#   usermod -a -G dialout <user>
#   usermod -a -G lock    <user>
#
#
# Debian, Ubuntu, Raspbian, Bananian (RaspberryPi, BananaPi):
#
#   usermod -a -G dialout <user>
#
#
# SuSe (ohne Gewaehr, getestet mit 11.3 und 12.1):
#
#   usermod -A dialout <user>
#   usermod -A lock    <user>
#
# Mac OS-X
#
# Um Java via Kommandozeile zu nutzen muss JAVA_HOME korrekt gesetzt sein
# z.B. Java10 auf OS-X:
#   export JAVA_HOME="/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home"
# oder bei aelteren Java-Versionen (z.B. 1.8):
#   export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
#
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

JAVA_SEARCH_PATHS="
/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home
/opt/java
/opt/jre
/opt/jdk
/usr/local/bin
${USER}/bin
"

PROG_NAME="CV-Navi.jar"
PROG_DIR="."

SAVEIFS=$IFS
IFS=$(echo -en "\n\b")

if [ "x$JAVA_HOME" != "x" ] ; then
	JAVA="$JAVA_HOME/bin/java"
else
	which java >/dev/null
	RET=$?
	[ $DEBUG == 1 ] && echo "RET which java = $RET"
	if [ ${RET} -eq 0 ] ; then
		JAVA=java
	else
		for p in ${JAVA_SEARCH_PATHS} ; do
			if [ -x "$p/bin/java" ] ; then
				JAVA="$p/bin/java"
				[ $DEBUG == 1 ] && echo "JAVA=${JAVA}"
				break
			fi
		done
	fi
fi
IFS=$SAVEIFS

[ $DEBUG == 1 ] && echo "JAVA=${JAVA}"

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

if [ $DEBUG == 1 ] ; then
	"${JAVA}" -version
	echo "${JAVA}" -Djava.library.path="${JAVA_LIBRARY_PATH}" ${JAVA_OPTS} -jar "$PROG_DIR/$PROG_NAME" $*
fi
"${JAVA}" -Djava.library.path="${JAVA_LIBRARY_PATH}" ${JAVA_OPTS} -jar "$PROG_DIR/$PROG_NAME" $*
