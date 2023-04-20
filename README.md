# Proyecto de Mensajería Express

Este es un proyecto de mensajería express que utiliza Java y Spring para crear un sistema de seguimiento y gestión de envíos. El proyecto utiliza una base de datos MySQL con un modelo entidad-relación, y documentación en Swagger para especificar los endpoints de los microservicios. También utiliza el patrón de diseño DTO, pruebas unitarias con Mockito y JUnit, Lombok, y está dividido en microservicios para Clientes, Empleados, Envíos y Paquetes. La integración continua se realiza con GitHub y el despliegue se realiza en Railway.

## Tecnologías utilizadas
- Java: versión 11
- Spring Framework
- Gestor de dependencias: Gradle
- Bases de datos: MySQL

## Configuración 
Antes de comenzar, asegúrese de tener una base de datos configurada y actualice las credenciales de la base de datos en el archivo **application.properties**.
```java
spring.datasource.url=jdbc:mysql://localhost:3306/sistemadereservas
spring.datasource.username=root
spring.datasource.password={password}
spring.jpa.hibernate.ddl-auto=update
server.port=8080
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```
## Documentación
La documentación de la API se genera automáticamente con Swagger. Para acceder a la documentación, abra un navegador web y vaya a [Documentación Swagger](https://sistemadereservas-production-26ff.up.railway.app/swagger-ui/index.html).

## Patrón de Diseño
Este proyecto utiliza el patrón de diseño DTO (Data Transfer Object) para transferir datos entre las diferentes capas de la aplicación. Los DTO son objetos simples que contienen campos y métodos de acceso, y se utilizan para transferir datos entre los controladores y los servicios.

## Diagrama del proyecto por paquetes

```java
com.example.Proyecto-Integrador
├── Configurer
│   └── SwaggerConfig.java
├── Controller
│   ├── ClienteController.java
│   ├── EmpleadoController.java
│   ├── EnvioController.java
│   └── PaqueteController.java
├── Dto
│   ├── ClienteDto.java
│   ├── EmpleadoDto.java
│   ├── EnvioDto.java
│   └── PaqueteDto.java
├── Entity
│   ├── Cliente.java
│   ├── Empleado.java
│   ├── Envio.java
│   └── Paquete.java
├── Repository
│   ├── ClienteRepository.java
│   ├── HabitacionRepository.java
│   ├── ReservaRepository.java
│   └── PaqueteRepository.java
├── Service
│   ├── ClienteService.java
│   ├── HabitacionService.java
│   ├── EnvioService.java
│   └── PaqueteService.java
└── ProyectoIntegradorApplication.java
```

El proyecto está organizado en cuatro paquetes principales, cada uno correspondiente a un microservicio:

- cliente-service: Contiene los controladores, servicios, DTOs y repositorios relacionados con el microservicio de Clientes.
- empleado-service: Contiene los controladores, servicios, DTOs y repositorios relacionados con el microservicio de Empleados.
- envio-service: Contiene los controladores, servicios, DTOs y repositorios relacionados con el microservicio de Envíos.
- paquete-service: Contiene los controladores, servicios, DTOs y repositorios relacionados con el microservicio de Paquetes.
Además, hay un paquete adicional llamado common que contiene clases y utilidades compartidas por los microservicios.

## Integración continua con GitHub
Este proyecto cuenta con integración continua mediante Github Actions. Cada vez que se realiza un push al repositorio, se ejecutan las pruebas unitarias y se crea un archivo JAR ejecutable.

## Despliegue del microservicio (Railway)
Este proyecto cuenta con un despliegue del microservicio mediante Railway. Conecta directamente con Github y nuestra base de datos(MySQL).
