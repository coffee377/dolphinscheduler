#!/bin/bash

BIN_DIR=$(dirname "$0")
APP_HOME=${APP_HOME:-$(cd "$BIN_DIR"/.. || exit ; pwd)}
#source "$APP_HOME/conf/env.sh"

#chmod -R 700 "${APP_HOME}"/config
export APP_WORK_HOME=${DOLPHINSCHEDULER_HOME}
export SPRING_JACKSON_TIME_ZONE=${SPRING_JACKSON_TIME_ZONE:-UTC}

# 定义一些环境变量，方便环境变量传参
APP_LOG_PATH=${APP_LOG_PATH:-"/${APP_HOME}/logs"}

JVM_OPTS=${JVM_OPTS:-""}
JAVA_OPTS=${JAVA_OPTS:-"-server -Duser.timezone=${SPRING_JACKSON_TIME_ZONE} -Xms4g -Xmx4g -Xmn2g -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=dump.hprof"}

if [[ "$DOCKER" == "true" ]]; then
  JAVA_OPTS="${JAVA_OPTS} -XX:-UseContainerSupport"
fi

echo "java $JVM_OPTS $JAVA_OPTS org.springframework.boot.loader.JarLauncher"

java "$JVM_OPTS" "$JAVA_OPTS" org.springframework.boot.loader.JarLauncher
