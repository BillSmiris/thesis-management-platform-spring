FROM openjdk:21-jdk-slim

LABEL maintainer="Bill Smiris billyssmiris@gmail.com"

RUN apt-get update && \
    apt-get install -y postgresql postgresql-contrib && \
    rm -rf /var/lib/apt/lists/*

ENV POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=test \
    POSTGRES_DB=thesis_app

RUN echo "host thesis_app postgres 0.0.0.0/0 md5" >> /etc/postgresql/15/main/pg_hba.conf && \
    echo "listen_addresses='*'" >> /etc/postgresql/15/main/postgresql.conf

COPY target/thesis-app-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8081 5432

CMD ["sh", "-c", "service postgresql start && until pg_isready -U postgres; do echo waiting for database; sleep 2; done && java -jar /app/app.jar"]
