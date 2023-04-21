# Proyecto de Mensajería Express <img alt="Java" height="40" width="40" src="https://media.giphy.com/media/KeJXqoqlUE2NSHUYER/giphy.gif">

Este es un proyecto de mensajería express que utiliza Java y Spring para crear un sistema de seguimiento y gestión de envíos. El proyecto utiliza una base de datos MySQL con un modelo entidad-relación, y documentación en Swagger para especificar los endpoints de los microservicios. También utiliza el patrón de diseño DTO, pruebas unitarias con Mockito y JUnit, Lombok, y está dividido en microservicios para Cliente, Empleado, Envío y Paquete. La integración continua se realiza con GitHub y el despliegue se realiza en Railway.

<div align="center" > <img alt="logo" height="100" width="400" align="center" src="https://user-images.githubusercontent.com/115324147/233528141-8f02ce04-0c5a-49c6-9c07-072e909bec20.png">
</div>

## Tecnologías utilizadas
- Java versión: 11
- Spring Framework
- Gestor de dependencias: Gradle
- Lombok
- Bases de datos: MySQL

## Configuración 
Antes de comenzar, asegúrese de tener una base de datos configurada y actualice las credenciales de la base de datos en el archivo **application.properties**.
```java
spring.datasource.url=jdbc:mysql://localhost:3306/proyectointegrador
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

## Autenticación y autorización seguridad
Se utiliza Spring Security para la autenticación y autorización de los usuarios. Solo los usuarios autenticados tienen acceso a las funcionalidades de reserva.

## Diagrama del Modelo Entidad-Relación
Este es el diagrama del modelo entidad-relación para la base de datos MySQL del proyecto de sistema de reservas:
![diagrama](https://user-images.githubusercontent.com/115324147/233475114-f72509af-a274-4fa1-9bab-b0669dbea10e.png)


- La tabla **Cliente** contiene información sobre los clientes, como su cedula, nombre, apellido, celular, ciudad, correo electronico y dirección
- La tabla **Empleado** contiene información sobre los empleados, como su cedula, nombre, apellido, celular, ciudad, correo electronico, dirección, antiguedad, tipo de sangre y tipo de empleado.
- La tabla **Envio** contiene información sobre los envios, como el numero de guia, ciudad de destino, ciudad origen, dirección destino, estado del envio, hora de entrega, nombre de la persona, numero de la persona, valor del envio.
- La tabla **Paquete** contiene información sobre los paquetes, como el id del paquete, peso, tipo de paquete y valor declarado.

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
├── Exception
│   ├── ApiExceptionHandler.java
│   └── ApiRequestException.java
├── Model
│   ├── Cliente.java
│   ├── Empleado.java
│   ├── Envio.java
│   ├── Paquete.java
│   └── Persona.java
├── Repository
│   ├── ClienteRepository.java
│   ├── HabitacionRepository.java
│   ├── ReservaRepository.java
│   └── PaqueteRepository.java
├── Service
│   └── WebSecurityConfig.java
├── Service
│   ├── ClienteService.java
│   ├── HabitacionService.java
│   ├── EnvioService.java
│   └── PaqueteService.java
└── ProyectoIntegradorApplication.java
```

El proyecto está organizado en cuatro paquetes principales, cada uno correspondiente a un microservicio:

- El paquete **Configurer** contiene las clases de configuración para la base de datos y Swagger.
- El paquete **Controller** contiene las clases controladoras para los microservicios de Cliente, Habitación y Reserva.
- El paquete **Dto** contiene las clases DTO (Data Transfer Object) para los objetos Cliente, Habitación y Reserva, que se utilizan para transferir datos entre la capa de presentación y la capa de servicios.
- El paquete **Exception** contiene las clases de Excepciones (ApiExceptionHandler, ApiRequestException) para el manejo de errores.
- El paquete **Model** contiene las clases de entidades JPA (Java Persistence API) para los objetos Cliente, Habitación y Reserva, que se utilizan para mapear las tablas de la base de datos.
- El paquete **Repository** contiene las interfaces de repositorios JPA para los objetos Cliente, Habitación y Reserva, que se utilizan para interactuar con la base de datos.
- El paquete **Security** contiene la clase de seguridad que se utiliza para autenticación y autorización de seguridad
- El paquete **Service** contiene las clases de servicios para los microservicios de Cliente, Habitación y Reserva, que contienen la lógica de negocio.
La clase HotelAshirApplication es la clase principal del proyecto que se utiliza para iniciar la aplicación.

Además, hay un paquete adicional llamado common que contiene clases y utilidades compartidas por los microservicios.

## Microservicios
Este proyecto está dividido en tres microservicios diferentes: Cliente, Habitación y Reserva. Cada microservicio tiene su propia base de datos y API REST. La comunicación entre los microservicios se realiza a través de peticiones HTTP.

#### Cliente Microservicio
Endpoints:
- **POST /clientes** - Crea un nuevo cliente
```java
{
"cedula": 12345,
"nombre":"Mateo",
"apellido":"Zapata",
"correoElectronico" : "mateo@gmail.com",
"direccion" :"calle 46 # 69-90",
"edad" : 18,
"correo" : "mateo@gmail.com"
}
```

#### Habitación Microservicio
Endpoints:
- **POST /habitaciones** - Crea una nueva habitación
```java
{
    "numero": 1,
    "tipoHabitacion": "PREMIUM",
    "precioBase": 25000
}
```

#### Reserva Microservicio
Endpoints:
- **POST /reservar** - Crea una nueva habitación
  Ejemplo de petición:

``(http://localhost:8084/api/v1/reservar/?numero=10&fecha=25-08-2023&cedula=12345)``

Respuesta de petición:
```java
{
    "fechaReserva": "2023-08-25",
    "numero": 99,
    "codigoReserva": 27,
    "totalPago": 750
}
```

- **GET /reservas/{cedula}** - Obtener lista de reservas por cedula

Ejemplo de petición:

``(http://localhost:8084/api/v1/reservas/12345)``

```java
[
     {
        "fechaReserva": "2023-08-25",
        "habitacion": {
            "numero": 99,
            "tipoHabitacion": "PREMIUM",
            "precioBase": 15000
        },
        "cliente": {
            "nombre": "Mateo",
            "apellido": "Zapata",
            "cedula": 123,
            "direccion": "mateo@gmail.com",
            "edad": 18,
            "email": "calle 46 # 69-90"
        },
        "codigoReserva": 27,
        "totalPago": 750
    }
]
```

- **GET /disponibles/{fecha}** - Obtener lista de habitaciones disponibles por fecha

Ejemplo de petición:

``(http://localhost:8084/api/v1/disponibles/25-08-2023)``

- **GET /disponibles/habitacion** - Obtener lista de habitaciones por tipo y fecha

Ejemplo de petición:

``(http://localhost:8084/api/v1/disponibles/habitacion?tipo=premium&fecha=28-03-2023)``

```java
[
    {
        "numero": 11,
        "tipoHabitacion": "premium",
        "precioBase": 20500
    },
    {
        "numero": 12,
        "tipoHabitacion": "premium",
        "precioBase": 20500
    }
]
```
## Pruebas Unitarias <img align="center" alt="Pruebas" height="40" width="40" src="https://media.giphy.com/media/1sMGC0XjA1Hk58wppo/giphy.gif">
Se han incluido pruebas unitarias utilizando Mockito y JUnit para asegurar que los microservicios de Cliente, Habitación y Reserva funcionan correctamente.
Las pruebas unitarias se encuentran en la carpeta src/test/java del proyecto.

## Integración continua con GitHub
Este proyecto cuenta con integración continua mediante Github Actions. Cada vez que se realiza un push al repositorio, se ejecutan las pruebas unitarias y se crea un archivo JAR ejecutable.

## Despliegue del microservicio (Railway) <img align="center" alt="Depliegue" height="40" width="40" src="https://media.giphy.com/media/tzv65Sc3tBQWNMSI3B/giphy.gif">
Este proyecto cuenta con un despliegue del microservicio mediante Railway. Conecta directamente con Github y nuestra base de datos(MySQL).
