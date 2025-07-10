# Usa una imagen base de Java
FROM openjdk:21-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

# Copia el .jar al contenedor
COPY target/*.jar app.jar

# Expone el puerto típico de Spring Boot
EXPOSE 8000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]