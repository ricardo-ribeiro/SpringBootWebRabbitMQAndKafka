# Spring Boot Web & Data & RabbitMQ & Kafka

![Java CI](https://github.com/ricardo-ribeiro/SpringBootWebRabbitMQAndKafka/workflows/Java%20CI/badge.svg)





# Running Docker Services

```bash
cd docker
docker-compose up
```

# Docker Services


## [ADMINER](localhost:9999)

Web Application MYSQL/MariaDB database explorer.
Allows you to run queries and manage your database.

![Adminer Image](../master/docs/adminer/Adminer.png)


## [RabbitMQ Management Console](localhost:15672)

RabbitMQ Management Console



# Running the Application
```bash
./gradlew build bootRun
```

# Configuration


- Default Port: 5757

./src/main/resources/application.yml

```yml
server:
  port: 5757

logging.level.root: INFO

spring:
  application:
    name: ExampleApp
  h2:
    console:
      enabled: true
  cloud:
    stream:
      defaultBinder: rabbit
      bindings:
        globalEventsInput:
          binder: kafka
          destination: global_events
          content-type: application/json
          group: input-group-1
        globalEventsOutput:
          binder: kafka
          destination: global_events
          group: output-group-1
          content-type: application/json
        paymentsReceived:
          binder: rabbit
          destination: queue.payment.received
          group: payments
        paymentValidated:
          binder: rabbit
          destination: queue.payment.validated
          group: payments

      binders:
        kafka:
          type: kafka
          environment:
            spring.cloud.stream.kafka:
              binder:
                brokers: localhost
                defaultBrokerPort: 9092
              bindings:
                globalEventsOutput:
                  producer:
                    configuration:
                      key.serializer: org.apache.kafka.common.serialization.StringSerializer
                globalEventsInput:
                  consumer:
                    configuration:
                      isolation.level: read_committed # In case of Transactional Produced, Otherwise read_uncommitted
        rabbit:
          type: rabbit
          environment:
            spring:
              cloud:
                stream:
                  rabbit:
                    bindings:
                      paymentsReceived:
                        consumer:
                          autoBindDlq: true
                          requeueRejected: true
                          republishToDlq: true
                      paymentValidated:
                        producer:
                          autoBindDlq: true
                          republishToDlq: true
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
```


