# 构建参数（注：FROM 指令之前的 ARG，只能用于 FROM 指令中）
ARG NODE_IMAGE="harbor.jqk8s.jqsoft.net/zhmz/node:14-alpine"
ARG NGINX_IMAGE="harbor.jqk8s.jqsoft.net/zhmz/nginx:1.20.1-alpine"

FROM $NODE_IMAGE as builder
# 镜像构建参数
ARG NPM_REGISTRY="http://nexus.jqk8s.jqsoft.net/repository/npm/"
ARG BUILD_CMD="build"

WORKDIR /code
USER root
# 声明容器中 /code/node_modules 为匿名卷，防止容器销毁时 node_modules 数据丢失 https://blog.csdn.net/qq32933432/article/details/120944205
#VOLUME /code/node_modules 这里有问题，暂时不使用
ADD package.json /code
ADD pnpm-lock.yaml /code
# 检查是否存在 pnpm,不存在的则全局安装
RUN command -v pnpm >/dev/null 2>&1 || { echo >&2 "require pnpm but it's not installed. It's being installed now......"; npm i pnpm -g; }
RUN pnpm install --registry $NPM_REGISTRY --prefer-offline --network-timeout 120000
# 添加源码
ADD . /code
# 构建代码
RUN pnpm $BUILD_CMD

FROM $NGINX_IMAGE as web
ARG NGINX_CONF="nginx.conf"
WORKDIR /usr/share/nginx/html/
COPY --from=builder /code/dist .
COPY $NGINX_CONF /etc/nginx/conf.d/default.conf
EXPOSE 80
