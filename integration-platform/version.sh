#!/bin/bash

# 项目模块名称
MODULE_NAME=integration-platform

#cd "${WORKSPACE:-}${MODULE_NAME}" || exit

# jar 包文件名称
JAR_NAME="$(find target -name '*.jar' | cut -d '/' -f 2)"
if [ -z "$JAR_NAME" ]; then
  echo "jar 包文件不存在"
  exit 1
fi
# 是否预发布版本
if [[ "$JAR_NAME" =~ 'SNAPSHOT' ]]; then PRE_RELEASE=true; fi
PRE_RELEASE=${PRE_RELEASE:-false}
VERSION_POSITION=$(echo $MODULE_NAME | awk -F "-" '{print NF+1}')
# 原始版本号，不含预发布版本信息
VERSION_TAG=$(echo "${JAR_NAME%.*}" | cut -d "-" -f "${VERSION_POSITION}")
# 预发布版本号 => <PROJECT_VERSION>-beta.<BUILD_NUMBER>
if [ "$PRE_RELEASE" == "true" ]; then
  VERSION_TAG="${VERSION_TAG}-beta.${BUILD_NUMBER:-0}"
else
  PUBLISH_TYPE=正式发布
fi
echo "${PUBLISH_TYPE:-预发布} ${MODULE_NAME} 版本：${VERSION_TAG}"
