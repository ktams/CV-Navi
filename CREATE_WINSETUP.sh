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

# Mal sehen, ob sich die Version im Quelltext geaendert hat und dann ggf. nachfragen
# Versionsteile aus dem Quelltext auslesen...
V_MAJOR=`cat src/my/KlarText/KlarTextUI.java | grep public | grep gsVersionMayor | sed -e 's/;//' | awk '{print $NF}'`
V_MINOR=`cat src/my/KlarText/KlarTextUI.java | grep public | grep gsVersionMinor | sed -e 's/;//' | awk '{print $NF}'`
V_STATUS=`cat src/my/KlarText/KlarTextUI.java | grep "String gsBuild" | sed -e 's#"# #g' | awk '{print $4}'`

ISODATE=`date +'%Y%m%d'`

#echo ${V_MAJOR}.${V_MINOR}
# ...ersetze Version im Setup-Sktipt (-> neuer Name...
cat SetupScript.iss | sed -e "s/^#define myAppVersion \"[.0-9]*\"/#define myAppVersion \"${V_MAJOR}.${V_MINOR}\"/" >SetupScript.iss.new
# ...und vergleiche mit dem Original...
diff SetupScript.iss SetupScript.iss.new >/dev/null
RET_DIFF=$?
if [ ${RET_DIFF} -eq 1 ] ; then
	# Es gibt Unterschiede, also mal ausgeben...
	diff SetupScript.iss SetupScript.iss.new
	# ...und nachfragen, ob das übernommen werden soll...
	echo -e -n "\nVersion im Quelltext hat sich geaendert. Ins Setup Uebernehmen (J/n)? "
	read answer
	if [ "$answer" != "n" ]; then
		# ...nict abgelehnt -> Uebernehmen
		cp -p SetupScript.iss.new SetupScript.iss
	else
		# ...abgelehnt -> vergiss es
		rm SetupScript.iss.new
	fi
else
	# ...kein Unterschied -> vergiss es
	rm SetupScript.iss.new
fi

[ ! -d package ] && mkdir package

# Interactive Oberfläche
# wine "c:\\Program Files\\Inno Setup 5\\Compil32.exe" SetupScript.iss

# Kommandozeilengenerator
wine "c:\\Program Files\\Inno Setup 5\\ISCC.exe" SetupScript.iss
if [ -f package/setup_CV-Navi_${V_MAJOR}.${V_MINOR}.exe ] ; then
	cp -p package/setup_CV-Navi_${V_MAJOR}.${V_MINOR}.exe package/setup_CV-Navi_${V_MAJOR}.${V_MINOR}_${V_STATUS}_${ISODATE}${1}.exe
fi
