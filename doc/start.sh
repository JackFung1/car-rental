#! /bin/sh

# the variables used to config match engine
SYMBOL=BTC_USDT
ROLE=MASTER
CASSANDRA_DB=test
MYSQL_DB=test
ENABLE_SNAPSHOT=false


JAVA=java
MODULE_NAME=match-engine
PROFILE=prod

JAVA_OPTS=-server
JAVA_MEM_OPTS="-Xms8g -Xmx14g"
JAVA_GC_OPTS="-Xloggc:gc.out  \
             -XX:+PrintGCDetails \
             -XX:+PrintGCDateStamps \
             -XX:+PrintGCTimeStamps \
             -XX:+HeapDumpOnOutOfMemoryError \
             -XX:HeapDumpPath=dump.out"

ENGINE_OPTS="-Dengine.symbol=${SYMBOL} \
            -Dengine.role=${ROLE} \
            -Dengine.cassandra.cluster-name=MatchEngine \
            -Dengine.cassandra.key-space=${CASSANDRA_DB} \
            -Dengine.snapshot.enabled=${ENABLE_SNAPSHOT} \
            -Dengine.mysql.db=${MYSQL_DB}"

echo "Starting match engine for ${SYMBOL}...";
nohup ${JAVA} -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE} ${JAVA_OPTS} ${JAVA_MEM_OPTS} ${JAVA_GC_OPTS} -jar ${MODULE_NAME}.jar > /dev/null 2>&1 &
echo $! > pid.file
