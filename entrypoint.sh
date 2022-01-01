#!/usr/bin/env bash

export JAVA_OPTS="$JAVA_OPTS -XX:MetaspaceSize=256M"

case ${SPRING_PROFILES_ACTIVE} in
  dev)
    export JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8900
    export JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/oom_heap_dump.hprof -Xmx512M"
    ;;
  test)
    export JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8900
    export JAVA_OPTS="$JAVA_OPTS -XX:HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/oom_heap_dump.hprof -Xmx1024M"
    ;;
  uat)
    export JAVA_OPTS="$JAVA_OPTS -Xmx2048M"
    ;;
  prd)
    export JAVA_OPTS="$JAVA_OPTS -Xmx3048M -javaagent:xx"
    ;;
esac

java $JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom -jar /home/appuser/app.jar "$@"
