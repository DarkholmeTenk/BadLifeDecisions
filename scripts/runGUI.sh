#!/bin/sh
CP_POI=../poi-3.7-20101029.jar
CP_EX2=.
MAIN=comp34120.ex2.Main

# Apparently, the data14.xls file needs to be in the current directory, so
# we must start the GUI from here:
cd lib/comp34120-ex2-gui

java -classpath $CP_POI:$CP_EX2 -Djava.rmi.server.hostname=$1 $MAIN &
