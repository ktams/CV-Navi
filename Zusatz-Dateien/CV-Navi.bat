@ECHO OFF
REM Starte CV-Navi von Komandozeile
REM
REM V0.03 20200703 Lothar
REM - temporaer das Arbeitsverzeichnis ins Verzeichnis der Batchdatei legen
REM   (auch ueber Laufwerksgrenzen)
REM
REM V0.02 20200102 Lothar
REM - weitere Suchpfade
REM
REM V0.01 20190614 Lothar
REM - erste Testversion
REM - Suchpfade zu Java sind noch fix auf ein Kundensystem und Testsystem bei Lothar
REM - CV-Navi-Installationsverzeichnis wird in den Standard-Systempfaden gesucht
REM


REM save caller drive and directory
set cdDrive=%CD:~0,2%
set cdDir=%CD:~2%
REM save this scripts drive and directory
set batDrive=%~d0
set batDir=%~p0

REM wechsle in das Verzeichnis der Skriptdatei (da ist auch CV-Navi.jar
%batDrive%
cd %batDir%

SET CVNAVIJAR="CV-Navi.jar"
SET CVNAVIDIR=.
IF EXIST %CVNAVIDIR%\%CVNAVIJAR% (
  ECHO "  CVNAVIDIR=%CVNAVIDIR%  "
) ELSE (
  ECHO "%CVNAVIJAR% nicht gefunden"
)


REM JAVAEXE suchen
SET P1="C:\Program Files (x86)\Java\jre1.8.0_211\bin\java.exe"
SET P2="C:\Programme (x86)\Java\jre1.8.0_231\bin\java.exe"
SET P3="C:\Program Files\Java\jre1.8.0_211\bin\java.exe"
SET P4="W:\Program Files\Java\jre1.8.0_202\bin\java.exe"
SET P5="C:\Program Files\Java\jdk-13.0.1\bin\java.exe"
SET P6="C:\Program Files\openjdk\jdk-13.0.1\bin\java.exe"
SET JAVAEXE="  ECHO KEIN_JAVA_EXE  "
REM ist java vielleicht schon im Pfad...
java.exe -version
SET LAST_ERROR=%ERRORLEVEL%
IF "%LAST_ERROR%" == "0" (
  SET JAVAEXE=java.exe
  ECHO "  JAVA im Pfad"
) ELSE (
  IF EXIST %P1% (
    SET JAVAEXE=%P1%
    ECHO "  JAVA in P1"
  ) ELSE (
    IF EXIST %P2% (
      SET JAVAEXE=%P2%
	  ECHO "  JAVA in P2"
    ) ELSE (
      IF EXIST %P3% (
        SET JAVAEXE=%P3%
	    ECHO "  JAVA in P3"
      ) ELSE (
        IF EXIST %P4% (
          SET JAVAEXE=%P4%
	      ECHO "  JAVA in P4"
        ) ELSE (
          IF EXIST %P5% (
            SET JAVAEXE=%P5%
	        ECHO "  JAVA in P5"
          ) ELSE (
            IF EXIST %P6% (
              SET JAVAEXE=%P6%
    	      ECHO "  JAVA in P6"
            ) ELSE (
              ECHO "PROBLEM kein JAVAEXE gefunden"
	    )
          )
        )
      )
    )
  )
)
ECHO "  JAVAEXE=%JAVAEXE%  "

REM echo "  %JAVAEXE% -jar %CVNAVIJAR% %*"
%JAVAEXE% -jar %CVNAVIJAR% %*

%cdDrive%
cd "%cdDir%"
