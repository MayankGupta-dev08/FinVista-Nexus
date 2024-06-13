# FinVista Nexus

Learning Microservices - POC

## Maven Commands

- To generate a jar inside target folder

```shell
mvn clean install -Dmaven.test.skip=true 
```

- To start a springboot maven project

```shell
mvn spring-boot:run
```

- To generate a docker image using Buildpacks. No need of Dockerfile

```shell
mvn spring-boot:build-image 
```

- To generate a docker image using Google Jib. No need of Dockerfile

```shell
mvn compile jib:dockerBuild 
```