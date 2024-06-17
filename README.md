# FinVista Nexus Bank Application

Microservices - POC

---

## Maven Commands

- To generate a jar inside target folder w/o running unit tests

```shell
mvn clean install -Dmaven.test.skip=true 
```

- To run a spring-boot maven project using terminal

```shell
mvn spring-boot:run
```

- To generate a docker image using BuildPacks (Packeto). No need of Dockerfile

```shell
mvn spring-boot:build-image 
```

- To generate a docker image using Google Jib. No need of Dockerfile

```shell
mvn compile jib:dockerBuild 
```

---

## Docker & Docker Compose Commands

### Docker Commands

- To generate the docker image using the Dockerfile created

```shell
docker build . -t devmayank8/finvistanexus-accounts:1.0.1-SNAPSHOT
```

- To run a container with a particular name using that same docker image in detached mode

```shell
docker run -d --name fvn-accounts -p 8080:8080 devmayank8/finvistanexus-accounts:1.0.1-SNAPSHOT
```

- To run a container with a particular name using that same docker image in detached & debug mode

```shell
docker run -d --name fvn-accounts -p 8080:8080 -p 5005:5005 devmayank8/finvistanexus-accounts:1.0.1-SNAPSHOT
docker run -d --name fvn-accounts -p 8090:8090 -p 5010:5010 devmayank8/finvistanexus-loans:1.0.1-SNAPSHOT
docker run -d --name fvn-accounts -p 9000:9000 -p 5015:5015 devmayank8/finvistanexus-cards:1.0.1-SNAPSHOT
```

- To run RabbitMQ 3.13 using docker image and default credentials

```shell
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
```

- To push the image to docker hub registry (make sure you are logged in using docker cli or docker desktop)

```shell
docker image push docker.io/devmayank8/finvistanexus-accounts:1.0.1-SNAPSHOT
```

- To pull same the image from docker hub registry

```shell
docker pull devmayank8/finvistanexus-accounts:1.0.1-SNAPSHOT
```

- To show all the running and stopped containers

```shell
docker ps -a
```

- To login into the docker hub container registry

```shell
docker login -u <username>
```

- To logout from the docker hub container registry

```shell
docker logout
```

- To display the intermediate layers & cmds that were executed when building the image

```shell
docker history <image_name>
```

- To remove a particular docker image

```shell
docker rm <image_id>
```

- To follow the log output of a particular container

```shell
docker conatiner logs -f <container_id>
```

- To open bash inside a running container

```shell
docker exec -it <container_name> bash
```

- To inspect a particular container

```shell
docker conatiner inspect <container_id>
```

- To show all the container statistics

```shell
docker container stats
```

- To remove all the stopped containers

```shell
docker conatiner prune
```

- To remove all the unused containers, volumes, networks and dangling images

```shell
docker system prune
```

### Docker Compose Commands

- To start the containers using a `docker-compose.yml` file

```shell
docker-compose up -d
```

- To stop and delete the containers including volumes

```shell
docker-compose down -v
```

- To stop the containers including volumes

```shell
docker-compose stop
```

---

## Modifying Run/Debug Configurations using spring profiles

- **NOTE**: Priority - CLI arguments > JVM options > Environment Variables

### Using CLI Arguments

- activating `prod` profile instead of default

```shell
--spring.profiles.active=prod
```

- activating `qa` profile instead of default spring profile and changing the value of `build.version`

```shell
--spring.profiles.active=prod --build.version=2.0.1
```

### Using JVM option of passing the arguments

- activating `qa` profile instead of default spring profile and changing the value of `build.version`

```shell
-Dspring.profiles.active=qa -Dbuild.version=3.0.1
```

### Using Environment variables

- activating `prod` profile instead of default spring profile and changing the value of `build.version`

```shell
SPRING_PROFILES_ACTIVE=prod;BUILD.VERSION=5.1.0;
```

---

## Important Links

### Setting Up Your Spring Boot Project

#### 1. Create a Spring Boot Project
Kickstart your project using the [Spring Initializr](https://start.spring.io). This tool helps you generate a Spring Boot project with the necessary dependencies and configurations.

#### 2. Essential Tools and Dependencies

- **Spring Boot**: [Official Website](https://spring.io/projects/spring-boot)
- **Spring Cloud**: Extend your application with microservices support. [Spring Cloud Website](https://spring.io/projects/spring-cloud)

### Designing Your Application

#### 3. Applying Design Patterns
- **DTO Pattern**: Simplify data transfer across different parts of your application. Learn more about the [DTO Pattern](https://martinfowler.com/eaaCatalog/dataTransferObject.html).

#### 4. Mapping and Transformation

- **Model Mapper**: [Official Website](http://modelmapper.org/)
- **MapStruct**: [Official Website](https://mapstruct.org/)

#### 5. OpenAPI Integration

Document your APIs with ease using SpringDoc OpenAPI.

- **Spring Doc**: [Spring Doc Website](https://springdoc.org/)
- **OpenAPI**: [Open API Website](https://www.openapis.org/)

### Containerization and Deployment

#### 6. Docker and Containerization

- **Docker**: [Official Website](https://www.docker.com)
- **Docker Hub**: [Docker Hub Website](https://hub.docker.com)
- **Docker Compose**: Manage multi-container applications. [Docker Compose Website](https://docs.docker.com/compose/)
- **Buildpacks**: [Buildpacks Website](https://buildpacks.io)
- **Google Jib**: [Google Jib Website](https://github.com/GoogleContainerTools/jib)

#### 7. Kubernetes

Deploy and manage your applications at scale.

- **Local Kubernetes Cluster with Docker Desktop**: [Guide](https://docs.docker.com/desktop/kubernetes/)
- **Kubernetes Dashboard**: [Web UI Dashboard](https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/)
- **Helm**: Manage Kubernetes applications. [Helm Website](https://helm.sh)
- **Spring Cloud Kubernetes**: [Spring Cloud Kubernetes Website](https://spring.io/projects/spring-cloud-kubernetes)

### Microservices and Messaging

#### 8. Microservices Architecture

- **Spring Cloud Config**: [Website](https://spring.io/projects/spring-cloud-config)
- **Spring Cloud Bus**: [Website](https://spring.io/projects/spring-cloud-bus)
- **Spring Cloud Netflix**: [Website](https://spring.io/projects/spring-cloud-netflix)
- **Spring Cloud OpenFeign**: [Website](https://spring.io/projects/spring-cloud-openfeign)
- **Resilience4j**: Implement fault tolerance. [Website](https://resilience4j.readme.io)
- **Spring Cloud Gateway**: [Website](https://spring.io/projects/spring-cloud-gateway)

#### 9. Messaging and Event-Driven Architecture

- **RabbitMQ**: [RabbitMQ Website](https://www.rabbitmq.com)
- **Apache Kafka**: [Apache Kafka Website](https://kafka.apache.org)
- **Docker Compose for Kafka**: [Docker Compose File](https://github.com/bitnami/containers/blob/main/bitnami/kafka/docker-compose.yml)

### Monitoring and Observability

#### 10. Metrics and Monitoring

- **Micrometer**: [Micrometer Website](https://micrometer.io)
- **Prometheus**: [Prometheus Website](https://prometheus.io/)
- **Grafana**: [Grafana Website](https://grafana.com)
- **Grafana Loki**: [Setup Guide](https://grafana.com/docs/loki/latest/getting-started/)

#### 11. Tracing and Observability

- **OpenTelemetry**: [OpenTelemetry Website](https://opentelemetry.io/)
- **Automatic Instrumentation**: [Guide](https://opentelemetry.io/docs/instrumentation/java/automatic/)

### Security

#### 12. Identity and Access Management

- **Keycloak**: [Keycloak Website](https://www.keycloak.org/)

### Advanced Topics and Best Practices

#### 13. Twelve-Factor App Methodology

Follow modern best practices for building software-as-a-service apps.

- **Twelve-Factor**: [Twelve-Factor Methodology](https://12factor.net)
- **Beyond the Twelve-Factor App**: [Book](https://www.oreilly.com/library/view/beyond-the-twelve-factor/9781492042631/)

#### 14. Event Storming

Design your domain-driven applications efficiently.

- **Lucidchart Blog**: [Event Storming Guide](https://www.lucidchart.com/blog/ddd-event-storming)

### Additional Resources

#### 15. Package Management and Build Tools

- **Chocolatey**: [Chocolatey Website](https://chocolatey.org/)
- **Bitnami Helm Charts**: [GitHub Repo](https://github.com/bitnami/charts)

#### 16. Cloud Platforms

Deploy your applications to the cloud.

- **Google Cloud Platform (GCP)**: [GCP Website](https://cloud.google.com)
- **GCP SDK Installation**: [Installation Guide](https://cloud.google.com/sdk/docs/install)

#### 17. Service Mesh

Implement service mesh for better microservices management.

- **Istio**: [Istio Website](https://istio.io)