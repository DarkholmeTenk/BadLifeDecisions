#!/bin/sh
PID=`pidof rmiregistry`

# For reasons I cannot fathom, rmiregistry must be started from here
cd lib/comp34120-ex2-gui

if [ -z "$PID" ]
then
  rmiregistry &
fi
