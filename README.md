# 📚 Proyecto de Consola con Spring Boot para Gestión de Libros

Este proyecto es una aplicación de consola desarrollada en Java utilizando Spring Boot. La aplicación consume datos de la API pública [GutenDex](https://gutendex.com/) para obtener información sobre libros, almacena estos datos en una base de datos PostgreSQL y permite realizar diversas operaciones sobre ellos.

## 🛠️ Tecnologías y Dependencias

El proyecto utiliza las siguientes tecnologías y dependencias:

- **Java** ☕: Lenguaje de programación.
- **Spring Boot** 🌱: Framework principal para el desarrollo de la aplicación.
- **JPA (Java Persistence API)** 💾: Para la gestión de la persistencia de datos.
- **PostgreSQL** 🐘: Base de datos relacional para almacenar los datos de los libros.
- **Lombok** ✨: Para reducir la escritura de código repetitivo, como getters, setters y constructores.
- **Gutendex API** 🌍: Fuente de datos para obtener información sobre libros en formato JSON.

## ✨ Funcionalidades

- 🔄 **Consumo de datos**: La aplicación obtiene información de libros desde la API de GutenDex.
- 💽 **Almacenamiento en Base de Datos**: Los datos obtenidos se guardan en una base de datos PostgreSQL.
- 📊 **Operaciones sobre los datos**: Permite realizar consultas y operaciones de estadísticas sobre los libros almacenados (por ejemplo, cantidad de libros por idioma).

## ⚙️ Requisitos Previos

- **Java 17 o superior** ☕
- **Maven** 📦 (para la gestión de dependencias y construcción del proyecto)
- **PostgreSQL** 🐘 (configurado y corriendo en el puerto correspondiente)

## 🗃️ Configuración de la Base de Datos

Antes de ejecutar el proyecto, asegúrate de que tu base de datos PostgreSQL esté configurada. Puedes crear una base de datos nueva para este proyecto, y luego agregar las credenciales en el archivo de configuración `application.properties`.

1. Crea una base de datos PostgreSQL (por ejemplo, `gutenberg_db`).

2. Agrega las credenciales en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/gutenberg_db
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. Si es necesario, ajusta la configuración de conexión de acuerdo a tu entorno.

## 🚀 Instalación y Ejecución

1. **Clona el repositorio**:

    ```bash
    git clone https://github.com/tu_usuario/tu_repositorio.git
    cd tu_repositorio
    ```

2. **Construye el proyecto**:

    Ejecuta el siguiente comando para compilar el proyecto y resolver las dependencias:

    ```bash
    mvn clean install
    ```

3. **Ejecuta la aplicación**:

    ```bash
    mvn spring-boot:run
    ```

    La aplicación iniciará en modo consola y comenzará a consumir datos de la API, almacenándolos en la base de datos PostgreSQL.

## 📝 Uso de la Aplicación

### Consumo de Datos

Al iniciar la aplicación, esta se conectará a la API de GutenDex y descargará información sobre los libros. Los datos descargados serán almacenados en la base de datos.

### Consultas y Estadísticas

La aplicación permite realizar las siguientes operaciones:

- 📚 **Consultar libros por idioma**: Muestra el número de libros por idioma.
- 📊 **Estadísticas**: Realiza estadísticas sobre los libros almacenados, como el conteo de descargas o el número de autores distintos.

### Ejemplo de Uso

Una vez la aplicación esté ejecutándose, podrás ver resultados en la consola de las operaciones realizadas y estadísticas generadas.

## 🔍 Notas de Implementación

- Este proyecto utiliza **Lombok** para generar automáticamente getters, setters y otros métodos básicos en las clases de entidad. Asegúrate de tener el soporte de Lombok activado en tu IDE para evitar errores de compilación.
- La configuración de `spring.jpa.hibernate.ddl-auto=update` permite crear o actualizar automáticamente las tablas de la base de datos en base a las entidades definidas. Puedes cambiar esta configuración según tus necesidades (por ejemplo, a `validate` en producción).

## 📜 API y Licencias

Este proyecto utiliza la API pública de GutenDex. Asegúrate de revisar los términos de uso de la API para un uso correcto y conforme a las normas de uso de datos.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Si encuentras un error o tienes ideas de mejora, no dudes en abrir un issue o enviar un pull request.

## 🧑‍💻 Autor

Creado por **Estevan-j/Dev**.


