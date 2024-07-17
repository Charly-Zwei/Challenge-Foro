# Proyecto de Foro ![Estado: ](https://img.shields.io/badge/estado-finalizado-brightgreen)

---

## Descripción del Proyecto

El **Foro Hub** es una aplicación web desarrollada en Java que permite a los usuarios interactuar en un foro mediante la creación, visualización, actualización y eliminación de tópicos. Este proyecto ha sido desarrollado siguiendo las mejores prácticas y cumpliendo con las reglas del negocio establecidas para garantizar una experiencia de usuario eficiente y segura.

## Tecnologías Usadas

- **Java 17**: Lenguaje de programación utilizado para desarrollar la lógica del programa.
- **Maven**: Herramienta de gestión de proyectos y construcción utilizada para manejar dependencias y compilar el proyecto.
- **Spring Boot**: Framework de Java que facilita la creación de aplicaciones web robustas y escalables de manera rápida y sencilla.

## Dependencias

- **Lombok**: Librería que simplifica el código Java mediante anotaciones que generan código repetitivo de manera automática.
- **Spring Web**: Módulo de Spring para crear aplicaciones web, incluyendo RESTful services.
- **Spring Boot DevTools**: Herramienta que facilita el desarrollo con características como recarga automática y configuración mejorada.
- **Spring Data JPA**: Abstracción de Spring para trabajar con JPA (Java Persistence API), facilitando la interacción con bases de datos relacionales.
- **Flyway Migration**: Herramienta de migración de base de datos que permite versionar y gestionar cambios en la base de datos.
- **PostgreSQL Driver**: Conector JDBC para interactuar con la base de datos PostgreSQL.
- **Spring Security**: Framework de Spring para manejar la seguridad en aplicaciones, proporcionando autenticación y autorización.

## Funcionalidades del Foro

- **Crear un nuevo tópico**: Permite a los usuarios crear nuevos temas de discusión en el foro.
- **Mostrar todos los tópicos creados**: Muestra una lista de todos los tópicos disponibles en el foro.
- **Mostrar un tópico específico**: Permite a los usuarios visualizar los detalles de un tópico específico mediante su identificación.
- **Actualizar un tópico**: Permite a los usuarios actualizar la información de un tópico existente.
- **Eliminar un tópico**: Permite a los usuarios eliminar un tópico del foro.

## Instalación y Uso

1. **Clonar el repositorio**: `git clone https://github.com/tu_usuario/proyecto-foro.git`
2. **Abrir el proyecto en IntelliJ IDEA**.
3. **Configurar la base de datos PostgreSQL**: Crear una base de datos y actualizar las credenciales en el archivo de configuración de Spring Boot.
4. **Agregar las dependencias**: Asegurarse de que las dependencias de Lombok, Spring Web, Spring Boot DevTools, Spring Data JPA, Flyway Migration, PostgreSQL Driver y Spring Security estén incluidas en el archivo `pom.xml`.
5. **Ejecutar las migraciones de Flyway**: Asegurarse de que las migraciones de base de datos se ejecuten correctamente para configurar la estructura de la base de datos.
6. **Ejecutar la aplicación**.

## Gracias!
