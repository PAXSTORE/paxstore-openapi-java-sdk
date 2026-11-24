#!/usr/bin/env bash
# Run the live integration tests directly via JUnitCore.
# The pom.xml intentionally sets maven-surefire-plugin skipTests=true, so this
# script bypasses surefire to allow explicit local verification.
set -e
cd "$(dirname "$0")"
mvn -q -DskipTests test-compile
mvn -q dependency:build-classpath -Dmdep.outputFile=/tmp/pax_cp.txt
CP="target/test-classes:target/classes:$(cat /tmp/pax_cp.txt)"
if [ "$#" -eq 0 ]; then
  CLASSES=$(find target/test-classes -name '*Test.class' | sed 's#target/test-classes/##; s#/#.#g; s#\.class$##')
else
  CLASSES="$*"
fi
java -cp "$CP" org.junit.runner.JUnitCore $CLASSES
