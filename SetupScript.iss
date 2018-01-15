; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

;#pragma option -v+
;#pragma verboselevel 9

#define myAppName "CV-Navi"
#define myAppVersion "2.7"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{F91AB7A3-ABB2-4E33-A51A-0F734ED422BE}
AppName={#myAppName}
AppVersion={#myAppVersion}
AppVerName={#myAppName} {#myAppVersion}
VersionInfoVersion={#myAppVersion}
AppPublisher=Tams Elektronik GmbH
AppPublisherURL=http://www.tams-online.de
AppSupportURL=http://www.tams-online.de
AppUpdatesURL=http://www.tams-online.de
DefaultDirName={pf}\{#myAppName}
DefaultGroupName={#myAppName}
DisableProgramGroupPage=yes
InfoAfterFile=dist\LiesMich.txt
OutputDir=package
OutputBaseFilename=setup_{#myAppName}_{#myAppVersion}
Compression=lzma
SolidCompression=yes

; "ArchitecturesInstallIn64BitMode=x64" requests that the install be
; done in "64-bit mode" on x64, meaning it should use the native
; 64-bit Program Files directory and the 64-bit view of the registry.
; On all other architectures it will install in "32-bit mode".
ArchitecturesInstallIn64BitMode=x64
; Note: We don't set ProcessorsAllowed because we want this
; installation to run on all architectures (including Itanium,
; since it's capable of running 32-bit code too).


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "german"; MessagesFile: "compiler:Languages\German.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "dist\KlarText.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\rxtxSerial.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\rxtxSerial64.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\librxtxSerial.jnilib"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\librxtxSerial64.jnilib"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\LiesMich.txt"; DestDir: "{app}"; Flags: ignoreversion isreadme
Source: "dist\CV-Navi.sh"; DestDir: "{app}"; Flags: ignoreversion
Source: "dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

;tests
;Source: "dist\LiesMich.txt"; DestDir: "{app}"; DestName: "Install_user_{username}"; Flags: ignoreversion

[Icons]
Name: "{group}\CV-Navi"; Filename: "{app}\KlarText.jar"; WorkingDir: {app}
Name: "{group}\{cm:UninstallProgram,CV-Navi}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\CV-Navi"; Filename: "{app}\KlarText.jar"; Tasks: desktopicon; WorkingDir: {app}
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\CV-Navi"; Filename: "{app}\KlarText.jar"; Tasks: quicklaunchicon; WorkingDir: {app}

[Run]
Filename: "{app}\KlarText.jar"; Description: "{cm:LaunchProgram,CV-Navi}"; Flags: shellexec postinstall skipifsilent
