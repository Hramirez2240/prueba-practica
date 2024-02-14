# crud-clientes

Este proyecto utiliza el lenguaje de programación Java, bajo el framework Quarkus utilizado para construir aplicaciones modernas con el fin de convertir a Java en la plataforma líder en kubernetes y ambientes serverless.

Cabe resaltar que también utiliza PostgreSql como motor de base de datos para persistir los datos de los clientes.

## Ejecutar la aplicación en desarrollo

La aplicación se puede ejecutar en modo desarrollo y habilitar la opción de live coding con el siguiente comando:
```shell script
./mvnw compile quarkus:dev
```

Si se quiere acceder a la ui de swagger directamente se puede ir a esta ruta
```
http://localhost:8080/q/swagger-ui/
```

> **_NOTA:_**  Se puede acceder a la ui de Quarkus en dev con la siguiente ruta: http://localhost:8080/q/dev/.

## Diagrama de clase
![Diagrama clase](src\assets\img\diagrama-clase.png)

En este diagrama se define cómo está compuesta la clase principal la cual es Client, que maneja los datos de los diferentes clientes para los cuales queremos hacer las diferentes operaciones.

## Diagrama de arquitectura
![Diagrama arquitectura](src\assets\img\diagrama-arquitectura.png)

En este diagrama se define el flujo básico de la aplicación, la cual es que el actor(la persona) por medio de un cliente como puede ser la ui de swagger o el propio postman, realiza una petición a un endpoint específico y este tiene la tarea de conectarse a la base de datos y realizar la operación que solicita el usuario.

## Estructura del proyecto

### Entity
Como mencioné previamente, este proyecto maneja una entidad principal la cual es **Client**, mediante esta se realizan todas las operaciones que tiene que ver con los clientes.

### Dto
Los dto se utilizan para transformar y mapear los datos antes de hacer modificaciones en la base de datos, este proyecto contiene 2 que son **CreateClientDto** para la creación del cliente y **UpdateClientDto** para la modificación del mismo. Cabe resaltar que estos dto cuentan con validaciones a las propiedades gracias a **jakarta.validation**, que nos permite definir unas validaciones por medio de constraints las cuales de ser violadas, ni siquiera hace la petición a la base de datos.

### Mapper
En este caso el mapper se utiliza para transformar un objeto de dto en su entidad para que posteriormente podamos hacer operaciones con ella como guardar y actualizar cierta información.

### Exceptions
Se utilizan los exceptions para controlar y manejar los errores a lo largo de los endpoints para que estos retornen una respuesta clara al usuario sobre qué está pasando. Estos errores pueden ser desde fallos con el servidor hasta la no existencia de un registro en específico.

### Service
Primero está el **IClientService**, es una interfaz que se encarga de realizar la petición http al servicio que retorna los datos de un país por medio del código, para que podamos sacar de allí el gentilicio específico.

Luego está el servicio de **CountryService**, su función es tomar esa respuesta json generada por la petición anterior y retornar únicamente el gentilicio que es lo que nos interesa para ser guardado en la base de datos.

### Resource
En esta parte definimos las rutas de los diferentes endpoints y es donde radican todas las peticiones.

### Test
El proyecto de test nos permite probar cierta funcionalidad (endpoint o repositorio) con el fin de validar si ejecuta la función de la forma correcta, cabe resaltar que los test se realizaron utilizando la extension de **quarkus-junit5**

## Extensiones utilizadas

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): Utilizado para realizar las peticiones REST.
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Nos simplifica la persistencia de los datos y la conexión a la base de datos mediante el uso del patrón repositorio o active record
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Nos permite establecer una conexión con PostgreSql via JDBC
- Flyway ([guide](https://quarkus.io/guides/flyway)): Utilizado para el manejo de las migraciones.
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Utilizado para realizar validaciones sobre dtos.
- Smallrye OpenApi ([guide](https://quarkus.io/guides/openapi-swaggerui)): Nos ayuda a documentar nuestras apis, viene con la interfaz de swagger
