@ECHO OFF
REM Starte CV-Navi von Komandozeile
REM
REM V0.01 20190614 Lothar
REM - erste Testversion
REM - Suchpfade zu Java sind noch fix auf ein Kundensystem und Testsystem bei Lothar
REM - CV-Navi-Installationsverzeichnis wird in den Standard-Systempfaden gesucht
REM
REM V0.02 20200102 Lothar
REM - weitere Suchpfade


SET CURDIR=%CD%

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
echo weiter
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


SET CVNAVIJAR="CV-Navi.jar"

SET V0="."
SET V1="c:\Programme\CV-Navi"
SET V2="C:\Programme (x86)\CV-Navi"
SET V3="c:\Program Files\CV-Navi"
SET V4="c:\Program Files (x86)\CV-Navi"
SET V5="%ProgramW6432%\CV-Navi"
SET V6="%ProgramFiles%\CV-Navi"
SET V7="%ProgramFiles(x86)%\CV-Navi"

SET CVNAVIDIR="  kein_CV-Navi_DIR  "
IF EXIST %V0%\%CVNAVIJAR% (
  SET CVNAVIDIR=%V0%
  ECHO "  CV-Navi in V0"
) ELSE (
  IF EXIST %V1%\%CVNAVIJAR% (
    SET CVNAVIDIR=%V1%
    ECHO "  CV-Navi in V1"
  ) ELSE (
    IF EXIST %V2%\%CVNAVIJAR% (
      SET CVNAVIDIR=%V2%
	  ECHO "  CV-Navi in V2"
    ) ELSE (
      IF EXIST %V3%\%CVNAVIJAR% (
        SET CVNAVIDIR=%V3%
	    ECHO "  CV-Navi in V3"
      ) ELSE (
        IF EXIST %V4%\%CVNAVIJAR% (
          SET CVNAVIDIR=%V4%
		  ECHO "  CV-Navi in V4"
        ) ELSE (
		  IF EXIST %V5%\%CVNAVIJAR% (
		    SET CVNAVIDIR=%V5%
		    ECHO "  CV-Navi in V5"
		  ) ELSE (
		    IF EXIST %V6%\%CVNAVIJAR% (
		      SET CVNAVIDIR=%V6%
		      ECHO "  CV-Navi in V6"
		    ) ELSE (
		      IF EXIST %V7%\%CVNAVIJAR% (
		        SET CVNAVIDIR=%V7%
		        ECHO "  CV-Navi in V7"
		      ) ELSE (
                ECHO "PROBLEM: kein CVNAVIDIR gefunden"
              )
            )
		  )
        )
      )
    )
  )
)
echo "  CVNAVIDIR=%CVNAVIDIR%  "

cd %CVNAVIDIR%
REM echo "  %JAVAEXE% -jar %CVNAVIJAR%  "
%JAVAEXE% -jar %CVNAVIJAR% %*

cd %CURDIR%
