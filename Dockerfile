FROM openjdk:24-rc-slim

WORKDIR /app
COPY target/authservice-1.0.0.jar app.jar

# Ожидаем внешний конфиг в /config
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=file:/config/"]