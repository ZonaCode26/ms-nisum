
## MS-NISUM

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.

## Contexto

Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de
error.

Todos los mensajes deben seguir el formato: {"mensaje": "mensaje de error"}

#### REGISTRO
Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato:
```json
{
   "name":"Juan Rodriguez",
   "email":"juan@rodriguez.org",
   "password":"hunter2",
   "phones":[
      {
         "number":"1234567",
         "citycode":"1",
         "contrycode":"57"
      }
   ]
}

```

- Responder el código de status HTTP adecuado

- En caso de éxito, retorne el usuario y los siguientes campos: 

| Atributo     | Description                |
|:--------     | :------------------------- |
| `id`         | id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID) |
| `created`    |fecha de creación del usuario |
| `modified`   | fecha de la última actualización de usuario |
| `last_login` | del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación) |
| `token`      | token de acceso de la API (puede ser UUID o JWT) |
| `isactive`   | Indica si el usuario sigue habilitado dentro del sistema. |


- Si caso el correo conste en la base de datos, deberá retornar un error "El
correo ya registrado".
- El correo debe seguir una expresión regular para validar que formato sea el correcto.
(aaaaaaa@dominio.cl)
- La clave debe seguir una expresión regular para validar que formato sea el
correcto. (El valor de la expresión regular debe ser configurable)
- El token deberá ser persistido junto con el usuario

## SOLUCIÓN

● Plazo: 3 días ✔️
● Banco de datos en memoria. Ejemplo: HSQLDB o H2. ✔️
● Proceso de build vía Gradle o Maven. ✔️
● Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA. ✔️
● Framework SpringBoot. ✔️
● Java 8+ ✔️
● Entrega en un repositorio público (github o bitbucket) con el código fuente y script de creación de BD. ✔️
● Readme explicando cómo probarlo. ✔️
● Diagrama de la solución. ✔️
● JWT como token ✔️
● Pruebas unitarias ✔️
● Swagger ✔️
● Se recomienda y se valorizará mucho la utilización de Patrones de Diseño y buenas practicas ✔️

## DIAGRAMA SOLUCIÓN
#### Flujo Registro

![Diagrama Solución drawio](https://github.com/ZonaCode26/ms-nisum/assets/28936885/f509e5df-c525-422c-a483-fef5d377f1c4)

## API Reference

#### Registrar usuario

```http
  POST /user/registrar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required**. Nombre del usaurio |
| `email` | `string` | **Required**.Email de usuario |
| `password` | `string` | **Required**. Contraseña de usuario |
| `phones` | `list` | **Required**. LIsta de datos de conctacto celular |

**EJEMPLO**
Request Body
```json
  {
    "name": "Juan Rodriguez",
    "email": "juan_test@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
Response Body - 200 SUCCESS
```json
  {
    "uuid": "03549351-5e07-3bdc-8cee-bfb4e5ff7a91",
    "created": "2023-12-22T03:16:36.4089711",
    "modified": null,
    "last_login": "2023-12-22T03:16:36.4089711",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuX3Rlc3RAcm9kcmlndWV6Lm9yZyIsImlhdCI6MTcwMzIzMjk5NiwiZXhwIjoxNzAzMjUwOTk2fQ.qCMVrCLa5DPia_hbKWJphR1UDdFm2PhmieTv2wcGAzJhD-V3BqofMpO502PXqRfSS_z7IkDLJ7Fpro816_xyfg",
    "isactive": true
}
```
Response Body - 400 BAD REQUEST
```json
{
    "mensaje": "El correo ya registrado"
}

{
    "mensaje": "Contraseña no válida"
}
```


## Screenshots
#### Registro - OK - 200
![image](https://github.com/ZonaCode26/ms-nisum/assets/28936885/1ae85ab3-18fe-4693-809c-d162f899f5e4)

#### Registro - ERROR - 400
- Email con cumple el pattern

![image](https://github.com/ZonaCode26/ms-nisum/assets/28936885/f434d265-415e-405d-97b9-09cb2d8601eb)

- Contraseña no cumple con el pattern

![image](https://github.com/ZonaCode26/ms-nisum/assets/28936885/b120f6ab-0fbb-4e94-b84c-c4ccdc3abee8)


## Environment Variables

To run this project, you will need to add the following environment variables to your application.properties file

```bash
server.port=8081
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=1234
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.component-scan.base-packages=com.nisum

com.nisum.jwt.time=18000
com.nisum.jwt.secret=nisum
com.nisum.pattern.validation.password=^[a-zA-Z0-9]+$
#com.nisum.pattern.validation.password=^[a-zA-Z]+$
spring.jpa.open-in-view=true

```

## Related

- H2  http://localhost:8081/h2-console
- Swagger http://localhost:8081/swagger-ui/

## Authors

- [@AlexiAe](https://github.com/zonacode26)



