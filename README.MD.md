### **ViveFlores.com.gt**

Sistema de Gestión de Contenido Turístico, Multi-Rol con Interacción Social

Aplicación web tipo Social Tourism Blog enfocada en la Isla de Flores, Petén, Guatemala. Centraliza la información turística del destino conectando a prestadores de servicios locales con turistas, dándole visibilidad digital a pequeños negocios y facilitando una guía confiable y accesible para los viajeros.





###### **Descripción**

En Guatemala existe una brecha importante en cuanto a guías turísticas digitales accesibles. Los pequeños negocios de la Isla de Flores carecen de visibilidad en internet, lo que beneficia únicamente a los establecimientos más grandes. Este proyecto resuelve ese problema ofreciendo una plataforma donde:



Los vendedores pueden registrar y publicar sus negocios o servicios.

Los usuarios pueden explorar el destino, dejar reseñas, subir fotos y guardar favoritos.

El administrador modera el contenido antes de que sea visible al público.





###### **Tecnologías**



**Backend**

Java SE + Spring Boot

Spring Framework (MVC, Hibernate, JPA)

Maven

MySQL



**Frontend**

HTML5, CSS3, JavaScript

Bootstrap

Thymeleaf



**Herramientas**

IntelliJ IDEA

MySQL Workbench

Git / GitHub

Postman



**Despliegue**

Render (servidor)

Railway (base de datos en la nube)





###### **Sistema de roles**

**Administrador:** Acceso total al sistema. Modera publicaciones, gestiona reportes y visualiza estadísticas del portal. 

**Vendedor:** Puede registrar su negocio, subir publicaciones sujetas a aprobación, administrarlas y atender mensajes de usuarios. 

**Usuario:** Puede explorar el contenido, dejar reseñas con calificación, subir fotos al álbum colectivo, guardar favoritos y contactar vendedores.



###### **Funcionalidades principales**

Registro y autenticación de usuarios con control de acceso por roles

Flujo de aprobación de publicaciones Vendedor - Administrador - Público

Sistema de reseñas y calificación por estrellas

Álbum colectivo de fotografías del destino

Gestión de favoritos por usuario

Mensajería directa entre usuario y vendedor

Panel de administración con gráficas y reportes

Diseño responsivo accesible desde cualquier dispositivo





###### **Base de datos**

Esquema relacional en MySQL compuesto por 11 entidades principales:

Usuarios · Publicaciones · Reseñas · Categorías · Reportes · Favoritos · Contactar · Eventos · Servicios · Fotos · SolicitudPublicacion



Instalación y configuración

Requisitos previos



Java 21

Maven

MySQL 8+

IntelliJ IDEA



**Pasos**

Clonar el repositorio

git clone https://github.com/JerryCastro-2023536/ViveFlores.com.gt.git

cd ViveFlores.com.gt



Cambiar a la rama de desarrollo

git checkout develop



Configurar la base de datos en

src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/viveflores

spring.datasource.username=TU\_USUARIO

spring.datasource.password=TU\_CONTRASEÑA



Ejecutar el proyecto con Maven

mvn spring-boot:run

Luego abre tu navegador en http://localhost:8080

###### 

###### **Equipo**

Jerry Lazaro Daniel Castro Rojas SCRUM MASTER

Luis Alejandro Chan Ortega SCRUM

Diego Sebastián Cartajena Lam SCRUM

Luis Enrique Chac González SCRUM

