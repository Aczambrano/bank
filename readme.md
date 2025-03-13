
# Bank Microservices

Este repositorio contiene los microservicios para una aplicación bancaria utilizando Spring Boot, Docker, MySQL y RabbitMQ. La aplicación se divide en dos microservicios principales: uno para gestionar las cuentas bancarias y otro para gestionar los clientes. Estos microservicios se despliegan utilizando Docker y se comunican entre sí a través de RabbitMQ y guardan sus datos en una base de datos MySQL.

## Requisitos previos

Asegúrate de tener instalados los siguientes programas:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Postman](https://www.postman.com/downloads/)

## Clonación del repositorio

1. Clona este repositorio a tu máquina local:

   ```bash
   git clone https://github.com/Aczambrano/bank.git
   cd bank
   ```

## Construir y ejecutar los contenedores con Docker

2. Una vez dentro del directorio del proyecto, construye y ejecuta los contenedores usando Docker Compose:

   ```bash
   docker-compose up --build -d
   ```

   Este comando hará lo siguiente:

   - Construirá las imágenes de los microservicios `account` y `customer`.
   - Levantará el contenedor de MySQL.
   - Iniciará los microservicios de `account` y `customer` en segundo plano.

3. **Puertos a tener disponibles:**

   La aplicación usa los siguientes puertos, asegúrate de que estén libres en tu máquina:

   - Puerto `3306` para MySQL.
   - Puerto `8081` para el microservicio de cuentas (`account`).
   - Puerto `8082` para el microservicio de clientes (`customer`).

4. Verifica que todos los contenedores estén corriendo correctamente:

   ```bash
   docker ps
   ```

   Deberías ver algo como esto:

   ```bash
   CONTAINER ID   IMAGE                        COMMAND                  CREATED          STATUS                    PORTS                               NAMES
   e31a49208e18   bank-account-microservice    "java -jar app.jar"      19 minutes ago   Up 19 minutes             0.0.0.0:8081->8081/tcp              account-microservice
   5f5dcfcc92b1   bank-customer-microservice   "java -jar app.jar"      19 minutes ago   Up 19 minutes             0.0.0.0:8082->8082/tcp              customer-microservice
   31d08e1e652e   mysql:latest                 "docker-entrypoint.s…"   19 minutes ago   Up 19 minutes (healthy)   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql_bank_db
   ```

5. Si todo está correcto, los microservicios deberían estar accesibles y la base de datos MySQL debería estar disponible en el puerto `3306`.

## Probar la aplicación en Postman

6. Abre Postman e importa la colección que se llama "Bank_Account.postman_collection.json" para probar los endpoints.

## Detener los contenedores

7. Para detener y eliminar los contenedores, usa el siguiente comando:

   ```bash
   docker-compose down
   ```

Este comando detendrá todos los servicios y limpiará los contenedores.
