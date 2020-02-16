# Spring Boot Web & Data & RabbitMQ & Kafka

![Java CI](https://github.com/ricardo-ribeiro/SpringBootWebRabbitMQAndKafka/workflows/Java%20CI/badge.svg)





# Running Docker Services

```bash
cd docker
docker-compose up
```

# Docker Services


## ADMINER

Web Application MYSQL/MariaDB database explorer.
Allows you to run queries and manage your database.

```
Host: http://localhost:9999
Server: mariadb
Username: root
Password: root
Database: Empty OR ExampleDatabase
```

[GO TO ADMINER](http://localhost:9999)


![Adminer Image](../master/docs/adminer/Adminer.png)

![Adminer Image](../master/docs/adminer/AdminerConsole.png)


## RabbitMQ Management Console

RabbitMQ Management Console

```
Host: http://localhost:15672
Username: guest
Password: guest
```

[RabbitMQ Management Console](http://localhost:15672)

![RabbitMQ Image](../master/docs/rabbitmq/RabbitLogin.png)

![RabbitMQ Console Image](../master/docs/rabbitmq/RabbitConsole.png)



## RabbitMQ
## Kafka
## Zookeeper

# Running the Application
```bash
./gradlew build bootRun
```

# Configuration


- Default Port: 5757

./src/main/resources/application.yml

```yml
server:
  port: 5757 // Set the Server Port

logging.level.root: INFO // Set the root Logging Level ex.: INFO | DEBUG | TRACE
logging.level.<classpath>.<name>: INFO // INFO | DEBUG ...

# Use When Having Spring Actuator, Expose All Endpoints {Security Concerns}
management:
  endpoints:
    web:
      exposure:
        include: "*" # Expose All The Actuator Endpoints

# When Using spring.security.xtype: JWT ; You Can Customise The token Generation
# key paths and other details.
custom:
  security:
    masterUser:
      username: master@localhost.com
      password: root
      roles:
        - MASTER
        - DEVELOPER
        - CUSTOMER
    jwt:
      header:
        key: Authorization
        value:
          prefix: Bearer
      algorithm : RSA512
      token:
        aliveFor: 3600
        issuer: ExampleApplication@localhost
        audience:
          - banana
          - banana1
          - banana2
        scope:
          - ui
      keys:
        public:
          path: /banana
        private:
          path: /banana


spring:
  # Spring Security Details
  security:
    xtype: BASIC # custom: BASIC or JWT
    user:
      name: root
      password: root
  # Spring Data Default Data Source - MariaDB
  datasource:
    url: jdbc:mariadb://localhost:3306/ExampleDatabase
    username: root
    password: root
    driver-class-name: org.mariadb.jdbc.Driver
    validationQuery: SELECT 1
  application:
    name: ExampleApp
  h2:
    console:
      enabled: true
  # Spring Cloud Streams Binder and Bindings Details
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
              # Specific Internal Kafka Consumer and Producer Properties per Binding
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
                    # Specific Internal RabbitMQ Consumer and Producer Properties per Binding
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




# In This Repository

## A Example React Application

#### Start React Development Server
```bash
cd ui 
npm install
npm start
```
[GO TO UI ON DEVELOPMENT SERVER](http://localhost:3000)

[GO TO BUILT_UI ON SPRING BOOT SERVER](http://localhost:5757)

Proxyed Requests : htto://localhost:3000 : http://localhost:5757


## A Kafka Spring Cloud Binder 
## A Kafka Spring Cloud Binder 
### With a Consumer
### With a Producer


## A RabbitMQ Spring Cloud Binder 
### With a Consumer
### With a Producer

