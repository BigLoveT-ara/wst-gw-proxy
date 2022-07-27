PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`
# Only set CATALINA_HOME if not already set
[ -z "$SERVER_HOME" ] && SERVER_HOME=`cd "$PRGDIR" >/dev/null; pwd`

#echo `cat ${PRGDIR}/run.pid`
kill -9 `cat ${PRGDIR}/run.pid`
echo 'The Service is killed'
rm -rf ${PRGDIR}/run.pid