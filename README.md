# Auth Service

Как запускать
Для локального тестирования с Embedded Redis:

bash
Copy code
mvn test -Dembedded.redis.enabled=true
Для интеграционного тестирования с Testcontainers:

bash
Copy code
mvn test -Dembedded.redis.enabled=false