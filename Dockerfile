FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY build.sbt .
COPY project ./project
COPY src ./src

RUN apt-get update && apt-get install -y curl gzip \
    && curl -L https://github.com/sbt/sbt/releases/download/v1.9.9/sbt-1.9.9.tgz | tar xz \
    && chmod +x sbt/bin/sbt

RUN sbt/bin/sbt assembly

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/scala-3.4.2/ascii-cats.jar ./ascii-cats.jar

EXPOSE 8080

CMD ["java", "-jar", "ascii-cats.jar"]