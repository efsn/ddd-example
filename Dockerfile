FROM openjdk:8-jdk-alpine as build

WORKDIR /home

add . /home
run sh ./gradlew clean spring-webflux-example:bootJar

FROM openjdk:8-jdk-alpine

LABEL org.label-schema.docker.cmd="docker run -p 8080:8080 -d registry.cn-shanghai.aliyuncs.com/app/ddd-example:latest"

WORKDIR /home/appuser

COPY --from=build /home/spring-webflux-example/build/libs/spring-webflux-example-*.jar app.jar

RUN apk --no-cache add curl tzdata bash
    && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && apk del tzdata \
    && addgroup -S appusers \
    && adduser -S appuser -G appusers \
    && chmod g+r,g+w,g+X -R /home/appuser/

USER appuser

EXPOSE 8080

ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Dfile.encoding=UTF-8","-jar","/home/appuser/app.jar"]

CMD ["java","-jar","/home/appuser/app.jar"]
