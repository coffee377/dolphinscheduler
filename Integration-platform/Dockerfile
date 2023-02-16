FROM openjdk:8-jre-slim-buster

ENV DOCKER true
ENV TZ Asia/Shanghai
ENV DOLPHINSCHEDULER_HOME /opt/dolphinscheduler

RUN apt update ; \
    apt install -y curl sudo ; \
    rm -rf /var/lib/apt/lists/*

WORKDIR $DOLPHINSCHEDULER_HOME

ADD ./target/worker-server $DOLPHINSCHEDULER_HOME

EXPOSE 1235

CMD [ "/bin/bash", "./bin/start.sh" ]
