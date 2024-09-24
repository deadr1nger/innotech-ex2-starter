# spring-boot-starter-interceptor

`Spring-Boot-Starter-Interceptor` — это Spring-boot-starter, который перехватывает входящие HTTP-запросы и исходящие HTTP-ответы с целью их логирования. Он используется для отслеживания запросов, заголовков и времени обработки запросов.

# Подключение

`http.logging.enabled=true - подключение в application.config`
``` 
http:
  logging:
    enabled: true - подключение в application.yml
```

# Пример лога

Входящий запрос:
`Incoming request: method=GET, uri=/execution/sync, headers=user-agent=PostmanRuntime/7.42.0; accept=*/*; cache-control=no-cache; postman-token=c71deb6b-b472-42dc-962f-57b166a92898; host=localhost:8080; accept-encoding=gzip, deflate, br; connection=keep-alive; content-type=multipart/form-data; boundary=--------------------------637998021507532308138358; content-length=177;`
Исходящий ответ:
`Outgoing response: status=200, processingTime=1055ms`
