# 🚀 Spring Boot + MySQL + Kubernetes (Minikube)

## 📖 Description

Ce projet présente le déploiement d'une application **Spring Boot** connectée à une base de données **MySQL** sur un cluster **Kubernetes** local en utilisant **Minikube**.

L'objectif est de mettre en pratique les principaux concepts Kubernetes tels que :

- Docker
- Kubernetes
- Minikube
- Deployments
- Services
- Persistent Volumes (PV)
- Persistent Volume Claims (PVC)
- Ingress
- Communication entre Spring Boot et MySQL

---

# 🛠️ Technologies

- Java 17
- Spring Boot
- Maven
- MySQL 8
- Docker
- Kubernetes
- Minikube

---

# 📂 Structure du projet

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

# ⚙️ Prérequis

Avant d'exécuter le projet, installer :

- Java 17
- Maven
- Docker Desktop
- Minikube
- kubectl

---

# ▶️ Démarrer Minikube

```bash
minikube start --driver=docker --nodes=2
```

Vérifier le cluster :

```bash
kubectl get nodes
```

---

# 🐳 Construire l'image Docker

Depuis le dossier `springboot-app` :

```bash
docker build -t springboot-app:latest .
```

Charger ensuite l'image dans Minikube :

```bash
minikube image load springboot-app:latest
```

---

# ☸️ Déploiement Kubernetes

## Déployer MySQL

```bash
kubectl apply -f kubernetes/mysql/persistent-volume.yaml
kubectl apply -f kubernetes/mysql/persistent-volume-claim.yaml
kubectl apply -f kubernetes/mysql/mysql-deployment.yaml
kubectl apply -f kubernetes/mysql/mysql-service.yaml
```

## Déployer Spring Boot

```bash
kubectl apply -f kubernetes/springboot/app-deployment.yaml
kubectl apply -f kubernetes/springboot/app-service.yaml
```

## Déployer Ingress

```bash
kubectl apply -f kubernetes/springboot/ingress.yaml
```

---

# 📊 Vérification

Afficher les pods :

```bash
kubectl get pods
```

Afficher les services :

```bash
kubectl get services
```

Afficher les deployments :

```bash
kubectl get deployments
```

---

# 🌐 Activer Ingress

```bash
minikube addons enable ingress
```

Ajouter dans le fichier `/etc/hosts` :

```
192.168.49.2 myapp.local
```

Puis accéder à :

```
http://myapp.local/tasks
```

---

# 🗄️ Configuration MySQL

Dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://mysql-service:3306/demo
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8081
```

---

# 📁 API

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/tasks` | Retourne la liste des tâches |

---

# 🏗️ Architecture

```
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

# 📸 Résultats

Ajouter ici quelques captures d'écran :

- Application Spring Boot
- Docker Images
- `kubectl get pods`
- `kubectl get services`
- Minikube Dashboard

---

# 📚 Concepts Kubernetes utilisés

- Pods
- Deployments
- Services
- Persistent Volume (PV)
- Persistent Volume Claim (PVC)
- Ingress
- Docker Images
- Minikube

---

# 👨‍💻 Auteur

**Hazar Me**

Projet réalisé dans le cadre d'un TP sur Kubernetes avec Spring Boot, Docker et Minikube.
