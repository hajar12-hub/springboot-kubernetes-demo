# Spring Boot + MySQL + Kubernetes (Minikube)

## Description

This project demonstrates the deployment of a **Spring Boot** application connected to a **MySQL** database on a local **Kubernetes** cluster using **Minikube**.

The project covers the following Kubernetes concepts:

- Docker
- Kubernetes
- Minikube
- Deployments
- Services
- Persistent Volumes (PV)
- Persistent Volume Claims (PVC)
- Ingress
- Communication between Spring Boot and MySQL

---

## Technologies

- Java 17
- Spring Boot
- Maven
- MySQL 8
- Docker
- Kubernetes
- Minikube

---

## Project Structure

```text
springboot-kubernetes-demo/
│
├── springboot-app/
│   ├── src/
│   ├── Dockerfile
│   ├── compose.yaml
│   ├── pom.xml
│   └── application.properties
│
├── kubernetes/
│   ├── mysql/
│   │   ├── mysql-deployment.yaml
│   │   ├── mysql-service.yaml
│   │   ├── persistent-volume.yaml
│   │   └── persistent-volume-claim.yaml
│   │
│   ├── springboot/
│   │   ├── app-deployment.yaml
│   │   ├── app-service.yaml
│   │   └── ingress.yaml
│   │
│   └── namespace.yaml
│
└── README.md
```

---

## Prerequisites

Before running the project, install:

- Java 17
- Maven
- Docker Desktop
- Minikube
- kubectl

---

## Start Minikube

```bash
minikube start --driver=docker --nodes=2
```

Verify the cluster:

```bash
kubectl get nodes
```

---

## Build the Docker Image

From the `springboot-app` directory:

```bash
docker build -t springboot-app:latest .
```

Load the image into Minikube:

```bash
minikube image load springboot-app:latest
```

---

## Deploy to Kubernetes

### Deploy MySQL

```bash
kubectl apply -f kubernetes/mysql/persistent-volume.yaml
kubectl apply -f kubernetes/mysql/persistent-volume-claim.yaml
kubectl apply -f kubernetes/mysql/mysql-deployment.yaml
kubectl apply -f kubernetes/mysql/mysql-service.yaml
```

### Deploy the Spring Boot Application

```bash
kubectl apply -f kubernetes/springboot/app-deployment.yaml
kubectl apply -f kubernetes/springboot/app-service.yaml
```

### Deploy Ingress

```bash
kubectl apply -f kubernetes/springboot/ingress.yaml
```

---

## Verify the Deployment

List all pods:

```bash
kubectl get pods
```

List all services:

```bash
kubectl get services
```

List all deployments:

```bash
kubectl get deployments
```

---

## Enable Ingress

Enable the Minikube Ingress controller:

```bash
minikube addons enable ingress
```

Add the following entry to your `/etc/hosts` file:

```
192.168.49.2 myapp.local
```

Access the application:

```
http://myapp.local/tasks
```

---

## Database Configuration

`application.properties`

```properties
spring.datasource.url=jdbc:mysql://mysql-service:3306/demo
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8081
```

---

## API Endpoint

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | Returns the list of tasks |

---

## Architecture

```text
           Client
              │
              ▼
           Ingress
              │
              ▼
    Spring Boot Service
              │
              ▼
      Spring Boot Pod
              │
              ▼
        MySQL Service
              │
              ▼
          MySQL Pod
              │
              ▼
            PV / PVC
```

---

## Kubernetes Concepts Used

- Pods
- Deployments
- Services
- Persistent Volumes (PV)
- Persistent Volume Claims (PVC)
- Ingress
- Docker Images
- Minikube

---
