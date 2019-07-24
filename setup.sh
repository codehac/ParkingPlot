#!/usr/bin bash
set -e
trap 'catch $? $LINENO' EXIT

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
mvn clean test
mvn clean package -DskipTests
cd ..
DIR1="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
echo "Running Application taking file as input "
java -jar "$DIR/target/goJek-1.0-SNAPSHOT.jar" "$DIR1/functional_spec/fixtures/file_input.txt"

catch() {
  if [ "$1" != "0" ]; then
    echo "Error $1 occurred on $2"
	exit $1
  fi
}