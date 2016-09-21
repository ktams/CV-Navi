; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{F91AB7A3-ABB2-4E33-A51A-0F734ED422BE}
AppName=CV-Navi
AppVerName=CV-Navi 1.4
AppPublisher=Tams Elektronik GmbH
AppPublisherURL=http://www.tams-online.de
AppSupportURL=http://www.tams-online.de
AppUpdatesURL=http://www.tams-online.de
DefaultDirName={pf}\CV-Navi
DefaultGroupName=CV-Navi
DisableProgramGroupPage=yes
InfoAfterFile=M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LiesMich.txt
OutputDir=M:\C-Source\NetBeansProjects\KlarText\Versionen\Aktuelle_Version
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "german"; MessagesFile: "compiler:Languages\German.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\KlarText.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\decoder.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\CV_Navi.info"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\CV_Navi.ini"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\B-4.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\FD-M.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\FD-R.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\KlarText.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-G-30.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-G-31.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-G-32.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-G-33.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-G-34.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LD-W-32.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\LiesMich.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\main.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\MasterControl.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\rxtxSerial.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\SD-34.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\WD-34.gif"; DestDir: "{app}"; Flags: ignoreversion
Source: "M:\C-Source\NetBeansProjects\KlarText\KlarText\dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\CV-Navi"; Filename: "{app}\KlarText.jar"; WorkingDir: {app}
Name: "{group}\{cm:UninstallProgram,CV-Navi}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\CV-Navi"; Filename: "{app}\KlarText.jar"; Tasks: desktopicon; WorkingDir: {app}
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\CV-Navi"; Filename: "{app}\KlarText.jar"; Tasks: quicklaunchicon; WorkingDir: {app}

[Run]
Filename: "{app}\KlarText.jar"; Description: "{cm:LaunchProgram,CV-Navi}"; Flags: shellexec postinstall skipifsilent

