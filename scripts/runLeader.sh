#!/bin/sh
CP_BLD=./build/libs/BadLifeDecisions.jar

java -classpath $CP_BLD -Djava.rmi.server.hostname=127.0.0.1 $1
