
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

#多种启动脚本任君选择
#nohup java -XX:+UseG1GC -Xms1024m -Xmx1024m $JAVA_AGENT -jar -Dlogging.config="$QETESH_SERVER_HOME"/config/logback-spring.xml christmas-wxh5-server.jar  >/dev/null 2>&1 & echo $! > $PRGDIR/run.pid
nohup java -XX:+UseG1GC -jar -Dlogging.config="$SERVER_HOME"/config/logback-spring.xml "$SERVER_HOME"/${artifactId}.jar  >/dev/null 2>&1 & echo $! > $PRGDIR/run.pid
#nohup java -jar $JAVA_AGENT -jar christmas-wxh5-server.jar & echo $! > $PRGDIR/run.pid
