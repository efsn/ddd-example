FROM openjdk:11-jdk-slim as build

WORKDIR /home

ADD . /home
RUN sh ./gradlew clean bootJar

FROM openjdk:11-jre-slim

LABEL org.label-schema.docker.cmd="docker run -p 8080:8080 -d elmi/ddd-example:latest"

WORKDIR /home/appuser

COPY --from=build /home/build/libs/ddd-example-*.jar app.jar
COPY --from=build /home/entrypoint.sh entrypoint.sh

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && addgroup --system appusers \
    && adduser --system appuser --ingroup appusers \
    && chmod g+r,g+w,g+X -R /home/appuser/

USER appuser

EXPOSE 8080

ENTRYPOINT ["bash","/home/appuser/entrypoint.sh"]
