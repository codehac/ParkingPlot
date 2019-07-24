#!/usr/bin sh
# Add script to run program here.
# Complete bin/setup so that after it is
# run, bin/parking_lot can be used to launch
# it.

# This variable contains absolute path of this `parking_lot` script
set -e
trap 'catch $? $LINENO' EXIT
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
if [ -z "$1" ];
then
java -jar "$DIR/target/goJek-1.0-SNAPSHOT.jar"
else
java -jar "$DIR/target/goJek-1.0-SNAPSHOT.jar" $1
fi

catch() {
  if [ "$1" != "0" ]; then
    echo "Error $1 occurred on $2"
	exit $1
  fi
}
