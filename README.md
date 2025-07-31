# FacturePDF

Application simple pour générer une facture PDF via un formulaire Angular et un backend Java, le tout containerisé avec Docker.

## 🚀 Lancer l'application avec Docker

### 📦 Prérequis

- [Docker](https://www.docker.com/) installé sur votre machine

---

### 🛠️ Étapes avec Docker CLI

#### 1. Cloner le projet

```bash
git clone https://github.com/PayenThibaud/FacturePDF.git
cd FacturePDF
```

#### 2. Construire les images

Dans chaque dossier contenant un Dockerfile, construisez l’image :
Backend (Java Spring Boot) :

```bash
cd facture-service
docker build -t facture-backend .
cd ..
```

puis Frontend (Angular) :

```bash
cd Angular-FrontEnd
docker build -t facture-frontend .
cd ..
```

#### 3. Lancer les conteneurs

```bash
docker run -d -p 8080:8080 --name backend facture-backend
docker run -d -p 80:80 --name frontend facture-frontend
```

🌐 Accès à l’application : http://localhost

Le backend écoute sur : http://localhost:8080

---

### ✨ Utilisation

L'utilisateur ouvre le navigateur et accède à http://localhost

Il remplit le formulaire

Lorsqu’il clique sur "Valider", le backend génère un PDF de la facture

Le PDF est ensuite téléchargé automatiquement et affiché dans le navigateur, selon sa configuration


---

### 📁 Structure du projet

```bash
FacturePDF/
├── facture-service/         # Backend Java avec Spring Boot
│   └── Dockerfile
├── Angular-FrontEnd/        # Frontend Angular
│   └── Dockerfile
└── README.md

```
