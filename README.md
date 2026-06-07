# F1nt3ch - Mini Fintech (Modular Monolith)

¡Bienvenido a **F1nt3ch**! Una plataforma de servicios financieros a pequeña escala implementada bajo un enfoque de **Monolito Modular** y **Arquitectura Hexagonal**, priorizando la alta cohesión, el bajo acoplamiento y la escalabilidad del dominio.

---

## 🚀 Arquitectura y Módulos

El proyecto está diseñado como un monolito modular donde cada módulo de negocio es independiente y se comunica con el resto a través de eventos asíncronos o interfaces de dominio bien definidas.

### Módulos del Sistema
*   **Auth:** Gestión de autenticación segura, emisión y validación de tokens (JWT).
*   **Users:** Administración de perfiles de usuario, verificación y datos personales.
*   **Wallets:** Gestión de billeteras digitales, saldos y monedas.
*   **Transactions:** Procesamiento de transferencias y depósitos.
*   **Notifications:** Envío de alertas del sistema e historial de notificaciones.
*   **Auditing:** Registro centralizado de logs financieros.

---

## 🛠️ Tecnologías y Herramientas

Este backend se apoya en un stack robusto para garantizar consistencia de datos, rendimiento y facilidad de despliegue:

*   **Lenguaje:** Java 21 / ecosistema robusto.
*   **Framework Principal:** Spring Boot (Spring Security, JPA/Hibernate).
*   **Seguridad:** Stateless JWT (JSON Web Tokens).
*   **Base de Datos:** MySQL.
*   **Mensajería Asíncrona:** Apache Kafka (para comunicación de eventos inter-módulos).
*   **Contenedores:** Docker y Docker Compose (para orquestación del entorno de desarrollo).

---

## 📦 Requisitos Previos

Antes de levantar el proyecto, asegúrate de tener instalado:
*   Java Development Kit (JDK) 21 o superior.
*   Docker y Docker Compose.
*   Tu IDE favorito (recomendado: IntelliJ IDEA).

---

## ⚙️ Configuración y Despliegue Local

Sigue estos pasos para clonar el repositorio y levantar el entorno completo:

### 1. Clonar el repositorio
```bash
git clone https://github.com/pastillazz/f1nt3ch-app.git
cd f1nt3ch
