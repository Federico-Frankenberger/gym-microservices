<div align="center">

# 🏋️ Gym Microservices API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue?style=for-the-badge)

**Arquitectura backend basada en microservicios para la gestión integral de un gimnasio.**

[Descripción](#-descripción) • [Arquitectura](#-arquitectura) • [Microservicios](#-microservicios) • [Tecnologías](#-tecnologías) • [Roadmap](#-roadmap-de-infraestructura) • [Consideraciones](#-consideraciones-técnicas) • [Autor](#-autor)

---
</div>

## 📄 Descripción

Este proyecto implementa un ecosistema backend modular para la administración de gimnasios utilizando una **arquitectura de microservicios**.

El sistema está diseñado para ser escalable y mantenible, donde cada servicio gestiona una capacidad de negocio específica (Bounded Context) de forma autónoma. El desarrollo sigue un enfoque evolutivo, partiendo de servicios independientes que progresivamente integran patrones de infraestructura robustos como Service Discovery y API Gateway.

---

## 🏗 Arquitectura

El ecosistema está compuesto por múltiples aplicaciones **Spring Boot** que se comunican vía **REST API**.

* **Diseño:** Arquitectura por capas (Controller, Service, Repository).
* **Independencia:** Cada microservicio posee su propia base de datos (*Database per service*) para garantizar el desacoplamiento.
* **Comunicación:** Interacción síncrona mediante APIs REST (con planes de migrar a mensajería asíncrona).
* **Escalabilidad:** Diseñado para soportar despliegues distribuidos y balanceo de carga.

---

## 🧩 Microservicios

### 1. 👤 Usuario Service (`usuario-service`)
**Responsabilidad:** Gestión centralizada de los datos personales de los usuarios del sistema.
* ✅ Registro y mantenimiento de usuarios.
* ✅ Exposición de datos personales a otros servicios.
* ✅ Búsqueda y consulta de usuarios.

---

### 2. 🔐 Auth Service (`auth-service`)
**Responsabilidad:** Autenticación y autorización del sistema.
* ✅ Autenticación de usuarios.
* ✅ Autorización basada en roles y permisos.
* ✅ Emisión y validación de tokens JWT.
* ✅ Control de acceso a los microservicios.
* ✅ Servicio independiente, sin acceso a datos de negocio.

---

### 3. 📦 Clases Service (`clases-service`)
**Responsabilidad:** Gestión operativa de las actividades del gimnasio.
* ✅ **Gestión de Clases:** Alta, baja y modificación de actividades.
* ✅ **Catálogo:** Listado de clases disponibles para los usuarios.
* ✅ **Metadatos:** Gestión de nombre, descripción, horarios, cupos y profesores.

---

### 4. 📦 Membresía Service (`membresia-service`)
**Responsabilidad:** Administración de los planes de suscripción de los clientes.
* ✅ **Planes:** Creación y configuración de planes (Gold, Silver, Black).
* ✅ **Ciclo de Vida:** Control de estados (Activa, Vencida, Pendiente).
* ✅ **Reglas de Negocio:** Definición de precios, duración y beneficios por plan.

---

### 5. 📦 Pagos Service (`pago-service`)
**Responsabilidad:** Registro financiero y auditoría.
* ✅ **Procesamiento:** Registro de transacciones entrantes.
* ✅ **Conciliación:** Asociación de pagos a membresías específicas.
* ✅ **Auditoría:** Historial inmutable de fechas y montos.

---

## 🛠 Tecnologías Utilizadas

| Categoría | Tecnología                  |
| :--- |:----------------------------|
| **Lenguaje** | Java 21                     |
| **Framework** | Spring Boot 3.x             |
| **Persistencia** | Spring Data JPA / Hibernate |
| **Base de Datos** | MySQL                       |
| **Gestión de Paquetes** | Maven                       |
| **Librerías** | Lombok, Spring Web          |
| **Testing** | JUnit 5, Mockito            |
| **Contenedores** | Docker                      |

---

## 🗺️ Roadmap de Infraestructura

El sistema incorpora componentes de infraestructura de manera progresiva:

- [x] **Fase 1:** Microservicios independientes con bases de datos aisladas.
- [ ] **Fase 2:** Testing (unitarios e integración básica).
- [ ] **Fase 3:** Service Discovery con **Netflix Eureka**.
- [ ] **Fase 4:** Enrutamiento inteligente con **Spring Cloud Gateway**.
- [ ] **Fase 5:** Resiliencia con **Resilience4j** (Circuit Breakers).
- [ ] **Fase 6:** Seguridad con **Spring Security + JWT**.
- [ ] **Fase 7:** Contenerización y orquestación con **Docker Compose**.

---

## 📂 Estructura del Proyecto

```text
gym-microservices/
├── .gitignore
├── README.md
├── usuario-service/      # Microservicio de Usuarios
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── auth-service/         # Microservicio de Autenticación y Autorización
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── clases-service/       # Microservicio de Clases
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── membresia-service/    # Microservicio de Membresías
│   ├── src/
│   ├── pom.xml
│   └── application.properties
└── pago-service/         # Microservicio de Pagos
    ├── src/
    ├── pom.xml
    └── application.properties

```
> **Nota:** Cada carpeta representa un proyecto Maven independiente con su propia configuración y dependencias.

---

## 🚀 Instalación y Ejecución

Sigue estos pasos para levantar el entorno de desarrollo localmente:

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/Federico-Frankenberger/gym-microservices.git](https://github.com/Federico-Frankenberger/gym-microservices.git)
    ```

2.  **Configuración de Base de Datos:**
    Asegúrate de tener MySQL corriendo. Los servicios esperan las siguientes bases de datos (configurables en `application.properties`):

    * `usuarios_db`
    * `clases_db`
    * `membresias_db`
    * `pagos_db`
    * `auth_db`


3.  **Ejecutar los servicios:**
    Navega a la carpeta de cada microservicio y ejecuta:
    ```bash
    mvn spring-boot:run
    ```

---

## 📌 Consideraciones Técnicas

* **Autenticación:** La seguridad (OAuth2/JWT) se implementará tras estabilizar la comunicación entre servicios.
* **Puertos:** Cada servicio corre en un puerto distinto por defecto (ej. `8081`, `8082`, `8083`) para evitar conflictos en local.

---

## 🔮 Consideraciones Futuras

* Incorporación de **mensajería asíncrona** (RabbitMQ o Kafka) para desacoplar procesos como inscripciones, pagos y activación de membresías.
* Migración de la infraestructura a **Kubernetes** para la gestión de despliegues, escalado automático y alta disponibilidad en entornos productivos.

---

## 👤 Autor

**Federico Agustín Frankenberger**
<br>
**Rol:** Java Backend Developer

* 💼 **LinkedIn:** [Ver Perfil](https://www.linkedin.com/in/federico-frankenberger/)
* 🐙 **GitHub:** [Ver Repositorio](https://github.com/Federico-Frankenberger)
* 📧 **Email:** [fafrankenberger@gmail.com](mailto:fafrankenberger@gmail.com)
