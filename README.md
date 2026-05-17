Nombre del microservicio

ms-productos

Descripción

Microservicio desarrollado con Spring Boot para la gestión de productos. Permite registrar, listar, buscar, actualizar y eliminar productos.

Este proyecto fue desarrollado como parte del examen final de microservicios utilizando:

Spring Boot
PostgreSQL (Neon)
Render
Docker
Tecnologías utilizadas
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
PostgreSQL
Neon Database
Docker
Render
Maven
Lombok
Validation
Arquitectura del proyecto

El proyecto está organizado por capas:

src/main/java/com/codigo/ms_productos
├── controller
├── service
├── repository
├── entity
├── dto
└── exception
Variables de entorno

El proyecto utiliza variables de entorno para proteger las credenciales.

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=${PORT:8080}

Variables necesarias:

Variable	Descripción
DB_URL	URL de conexión PostgreSQL Neon
DB_USERNAME	Usuario de la base de datos
DB_PASSWORD	Contraseña de la base de datos
PORT	Puerto del servicio
Entidad Producto
Campo	Tipo
id	Long
nombre	String
descripcion	String
precio	BigDecimal
stock	Integer
estado	Boolean
fechaCreacion	LocalDateTime
Endpoints disponibles
Crear producto
POST /api/productos

Ejemplo JSON:

{
  "nombre": "Laptop Lenovo",
  "descripcion": "Laptop para desarrollo de software",
  "precio": 3500.00,
  "stock": 10,
  "estado": true
}
Listar productos
GET /api/productos
Buscar producto por ID
GET /api/productos/{id}
Actualizar producto
PUT /api/productos/{id}
Eliminar producto
DELETE /api/productos/{id}
Validaciones implementadas
Nombre obligatorio
Precio mayor a cero
Stock no negativo

Anotaciones utilizadas:

@NotBlank
@Positive
@PositiveOrZero
@NotNull
Manejo de errores

El proyecto implementa manejo global de excepciones utilizando:

@RestControllerAdvice
Excepciones personalizadas

Ejemplo de respuesta:

{
  "mensaje": "Producto no encontrado",
  "detalle": "No existe un producto con el ID 10",
  "fecha": "2026-05-09T10:30:00"
}
Docker

El proyecto incluye Dockerfile para despliegue.

FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

El microservicio fue desplegado en Render utilizando Docker.

URL pública:

https://ms-productos-wq6t.onrender.com

Endpoint principal:

https://ms-productos-wq6t.onrender.com/api/productos
Base de datos

Base de datos PostgreSQL alojada en Neon.

Las tablas son generadas automáticamente mediante Hibernate con:

spring.jpa.hibernate.ddl-auto=update
Evidencias requeridas

El proyecto fue probado utilizando Postman Client:

POST /api/productos
GET /api/productos
GET /api/productos/{id}
PUT /api/productos/{id}
DELETE /api/productos/{id}
