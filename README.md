# springBootCrm
## Requisitos previos
1. Java 17.0.7 (Zulu OpenJdk)
2. Maven 3.8.1 
3. docker V4.19.0
## Instalación
### Instala las dependencias:
```bash
cd tu-proyecto
mvn install
````
### Configurscion base de datos en application.properties
````
spring.datasource.url=jdbc:mysql://localhost:3306/crm
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#Hibernate
spring.jpa.show-sql: true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
#Upload files
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=10MB
media.upload-dir=src/main/resources/static/images/
#JWT
jwt.secret.key=0kBaPPC/SdhzXub5t6jxipR5lW8/aReqJ+gsyukFc6w=
````
### Modifica el volumen del docker compose por una del propio equipo
````
services:
  mySql:
    image: mysql:latest
    restart: always
    container_name: CRMDB
    environment:
      - MYSQL_DATABASE=crm
      - MYSQL_ROOT_PASSWORD=root
    ports:
      - '3306:3306'
    volumes:
      - C:\Users\Pablo\Desktop\mysql-data:/var/liv/mysql
````
ejecutamos el docker componse y levantamos la bd
````bash
docker-compose up -d
````
### Ejecutamos  el  archivo data.sql
>crm\src\main\resources\static\sql\img\data.sql
## Uso
### Inicia el servidor de desarrollo:
````
mvn spring-boot:run
````

Abre tu navegador y navega a http://localhost:8080.

### Deploy
### Genera un archivo WAR:
```
mvn clean package
````
Copia el archivo tu-proyecto.war a tu servidor.
Configura tu servidor para que sirva el archivo tu-proyecto.war.
