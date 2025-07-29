# 🧵 Foro Hub - Challenge Back End Alura

Este proyecto es una API RESTful construida con **Spring Boot** para simular el backend de un foro de preguntas y respuestas. Forma parte del challenge de Back End de Alura Latam. La API permite gestionar tópicos creados por usuarios, realizando operaciones CRUD y persiste la información en una base de datos MySQL.

---

## ✅ Funcionalidades implementadas

- Crear un nuevo tópico
- Listar todos los tópicos
- Obtener un tópico específico por ID
- Actualizar un tópico existente
- Eliminar un tópico
- Validaciones básicas con Java Bean Validation
- Persistencia con JPA + Flyway + MySQL
- Separación por capas (DTOs, servicios, entidades, controladores)

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- MySQL Driver
- Flyway Migration
- Spring Security (estructura lista para integrar JWT)
- Bean Validation (`jakarta.validation`)
- Lombok
- Maven

---

## ⚙️ Configuración inicial

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/foro-hub.git
cd foro-hub
```

### 2. Crear base de datos

Asegúrate de tener un servidor MySQL corriendo y crea la base de datos:

```sql
CREATE DATABASE foro;
```

### 3. Configurar `application.properties`

Edita el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/foro
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

---

## ▶️ Ejecución del proyecto

Desde IntelliJ, VSCode o la terminal:

```bash
./mvnw spring-boot:run
```

Luego accede a la API en:

```
http://localhost:8080/topics
```

---

## 🧪 Endpoints disponibles

| Método | Ruta               | Descripción                |
|--------|--------------------|----------------------------|
| GET    | /topics            | Lista todos los tópicos    |
| GET    | /topics/{id}       | Obtiene un tópico por ID   |
| POST   | /topics            | Crea un nuevo tópico       |
| PUT    | /topics/{id}       | Actualiza un tópico        |
| DELETE | /topics/{id}       | Elimina un tópico por ID   |

### 📝 Ejemplo de JSON para crear un tópico

```json
{
  "titulo": "¿Cómo funciona JPA?",
  "mensaje": "No entiendo cómo se generan las tablas.",
  "autorId": 1
}
```

---

## 📁 Estructura del proyecto

```
src/
└── main/
    ├── java/
    │   └── com.alura.foro/
    │       ├── controller/       # Controladores REST
    │       ├── domain/           # Entidades JPA
    │       ├── dto/              # DTOs para entrada/salida
    │       ├── repository/       # Repositorios JPA
    │       ├── service/          # Lógica de negocio
    │       └── ForoApplication.java
    └── resources/
        ├── application.properties
        └── db/migration/         # Scripts SQL Flyway
```

---
