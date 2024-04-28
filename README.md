# mock-service
Este proyecto es un WebService que simula la respuesta de cualquier otro servicio, muy util para realizar pruebas de rendimiento en un entrono controlado.

## Tecnologías utilizadas

El proyecto esta desarrollado en Java 17, Spring boot 3, maven y se conecta a una base de datos Mongo.

## Características de la aplicación

- `Funcionalidad 1:` Simular la respuesta de cualquier servicio http, se puede configurar el status codes, response body, content type y tiempo de respuesta.
- `Funcionalidad 2:` Las configuraciones de respuesta se almacenan en base de datos.
- `Funcionalidad 3:` Implementa un cache para no consultar a la base de datos en cada petición, en caso de actualizar el registro el servicio expone el endpoint GET `/api/v1/mock/cache/clear` para limpiar la cache.
- `Funcionalidad 4:` El servicio es capas de soportar una carga de 2000 peticiones concurrentes con 1GB de ram, simpre y cuando los request y response utilizados no sean demasiado grandes, en caso de necesitar mas capasidad ajustar la memoria asignada.

## Instalación

Para compilar el proyecto se requiere java 17 o superior y maven.
Para levantar el proyecto se requiere de una base de datos Mongo, se puede ajustar la configuracion de conexión en el archivo `mock-service/src/main/resources/application.yml`.
```
spring:
  data:
    mongodb:
      uri: mongodb://<user>:<password>@localhost:27017/local?authSource=admin
```
Los siguientes comandos son para uso en terminal windows:
```
$ git clone https://github.com/manuel511/mock-service.git
$ cd mock-service
$ mvn clean verify
```
En caso de tener docker se agrega `Dockerfile` para la generacion de imagen:
```
$ docker build . --tag=mock-service:latest
$ docker run -p8080:8080 -m 1024m mock-service
```
En caso de no contar con Docker se puede ejecutar el componente con el siguiente comando:
```
$ java -Xmx1024M -jar target\mock-service-1.0.0.jar
```
Por default se levantara en el puerto 8080.

## Pruebas

Como herramienta de pruebas se puede utilizar [Postman](https://www.postman.com/downloads/). En la base de datos se debe crear la colección `MOCK_CONFIG` y a continuación se agregan algunos ejemplo de registros para probar.
```
- Ejemplo xml:
{
  "_id": "POST/api/v1/mock/xml",
  "contentType": "application/xml",
  "statusCode": 201,
  "responseBody": "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n   <SOAP-ENV:Header/>\r\n   <SOAP-ENV:Body>\r\n      <ns2:getBaseExperienceResponse xmlns:ns2=\"https://pokeapi.co/api/v1/pokemon\">\r\n         <ns2:baseExperience>270</ns2:baseExperience>\r\n      </ns2:getBaseExperienceResponse>\r\n   </SOAP-ENV:Body>\r\n</SOAP-ENV:Envelope>",
  "responseTime": 10
}

- Ejemplo json:
{
  "_id": "GET/api/v1/mock/json",
  "contentType": "application/json",
  "statusCode": 200,
  "responseBody": {
    "data": [
      {
        "id": "23",
        "url": "http://localhost:8080/suite/prueba"
      }
    ],
    "notifications": [
      {
        "code": "OK",
        "level": "INFO",
        "message": "SUCCESS",
        "timestamp": "2024-06-16T09:35:01:503Z"
      }
    ]
  }
}
```
