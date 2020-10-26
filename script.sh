#!/bin/bash

env=$1
browser=$2
machine=$3
echo "script.sh get environment: $env"
echo "script.sh get browser: $browser"
echo "script.sh get machine: $machine"

sh ./gradlew build -Pbrowser=$browser -Penv=$env -Pmachine=$machine run testBasicDeclarationStatus testBasicCompanyProfile copyTestResults
