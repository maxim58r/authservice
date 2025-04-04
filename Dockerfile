FROM openjdk:23-jdk

WORKDIR /app
COPY target/authservice-1.0.0.jar app.jar

# Ожидаем внешний конфиг в /config
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/,file:/config/"]
