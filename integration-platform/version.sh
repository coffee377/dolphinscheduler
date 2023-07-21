#!/bin/bash

# 项目模块名称
MODULE_NAME=integration-platform
# 镜像仓库, 默认 harbor.jqk8s.jqsoft.net
IMAGE_REGISTRY=${IMAGE_REGISTRY:-harbor.jqk8s.jqsoft.net}
# 镜像命令空间
IMAGE_NAMESPACE=${IMAGE_NAMESPACE:-zhmz}
# 镜像名称
IMAGE_NAME=${MODULE_NAME}-api

cd "${WORKSPACE:-}${MODULE_NAME}" || exit

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
VERSION=$(echo "${JAR_NAME%.*}" | cut -d "-" -f "${VERSION_POSITION}")
# 预发布版本号 => <PROJECT_VERSION>-beta.<BUILD_NUMBER>
if [ "$PRE_RELEASE" == "true" ]; then
  VERSION="${VERSION}-beta.${BUILD_NUMBER:-0}"
elses
  PUBLISH_TYPE=正式发布
fi
echo "${PUBLISH_TYPE:-预发布} ${MODULE_NAME} 版本：${VERSION_TAG}"

# 镜像标签
IMAGE_TAG=${IMAGE_TAG:-${IMAGE_REGISTRY}/${IMAGE_NAMESPACE}/${IMAGE_NAME}:${VERSION}}

#构建docker镜像
docker build  -t "${IMAGE_TAG}" -f ./Dockerfile --build-arg JAVA_IMAGE=harbor.jqk8s.jqsoft.net/k8s/openjdk:8-jre-slim-bullseye .
docker push "${IMAGE_TAG}"

# 部署
K8S_DEPLOYMENT_NAME=platform-api-v1 # 部署名称，这里根实际情况进行添加
kubectl set image deployment ${K8S_DEPLOYMENT_NAME} *="${IMAGE_TAG}" -n "${K8S_NAMESPACE}"
