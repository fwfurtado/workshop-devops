
FROM openjdk:8-jdk-slim as build

WORKDIR /application

COPY gradle /application/gradle
COPY gradlew /application

RUN sh gradlew -v

COPY settings.gradle.kts build.gradle.kts /application/

RUN sh gradlew dependencies -q --no-daemon

COPY src/ /application/src/

ARG SKIP_TEST='yes'
ENV SKIP_TEST=${SKIP_TEST}

ARG DATABASE_URL
ENV DATBASE_URL=${DATABASE_URL}

RUN if test "$SKIP_TEST" = "yes" ; then sh gradlew build -x test -q --no-daemon; else sh gradlew build -q --no-daemon; fi


FROM openjdk:8-jre-slim

RUN apt-get update && apt-get install -y wait-for-it netcat

RUN groupadd application && useradd application -g application -d /application -m

ARG VERSION='0.0.1-SNAPSHOT'
COPY --from=build /application/build/libs/devops-${VERSION}.jar /application/app.jar

COPY docker-entrypoint.sh /usr/local/bin/

USER application

WORKDIR /application

HEALTHCHECK --interval=1m --timeout=3s --start-period=30s --retries=5 CMD nc -vz localhost ${SERVER_PORT:-8080}

ENTRYPOINT ["docker-entrypoint.sh"]