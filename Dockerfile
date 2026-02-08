FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build.sbt .
COPY project ./project

RUN apk add --no-cache bash curl gzip \
    && curl -L https://github.com/sbt/sbt/releases/download/v1.9.9/sbt-1.9.9.tgz | tar xz \
    && chmod +x sbt/bin/sbt \
    && echo "export PATH=$PWD/sbt/bin:$PATH" >> /root/.bashrc

COPY src ./src
COPY . .

EXPOSE 8080

RUN sbt/bin/sbt assembly

CMD ["sbt/bin/sbt", "run"]