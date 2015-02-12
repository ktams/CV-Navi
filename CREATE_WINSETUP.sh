#!/bin/bash

[ ! -d package ] && mkdir package

# Interactive Oberfläche
# wine "c:\\Program Files\\Inno Setup 5\\Compil32.exe" SetupScript.iss

# Kommandozeilengenerator
wine "c:\\Program Files\\Inno Setup 5\\ISCC.exe" SetupScript.iss
