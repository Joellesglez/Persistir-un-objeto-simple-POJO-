#   Ejercicio 2 – Persistir un objeto simple (POJO)
#  Descripción
Proyecto Java (Java 17 + Maven) que muestra cómo serializar y deserializar un objeto Persona (POJO) a/desde JSON usando Gson. El programa crea una Persona, la guarda en persona.json y luego la vuelve a leer para imprimir sus atributos.

#   Requisitos
Java 17 o superior
Maven 3 o superior
Conexión a Internet (para que Maven descargue Gson)


#  Estructura del proyecto
POJO/
 ├─ pom.xml
 └─ src/
     └─ main/
         └─ java/
             └─ com/
                 └─ ejercicios/
                     └─ json/
                         ├─ Main.java
                         └─ Persona.java   (opcional)
pom.xml (dependencia principal)
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.ejercicios</groupId>
  <artifactId>json-ej2</artifactId>
  <version>1.0-SNAPSHOT</version>
  <properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
  <dependencies>
    <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.10.1</version>
    </dependency>
  </dependencies>
</project>

#  Código (resumen y explicación)

Persona.java
package com.ejercicios.json;

public class Persona {
    private String nombre;
    private int edad;

   #  // Constructor, getters y setters
   # public Persona() {} // necesario para Gson al deserializar
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}
#  Nota: Gson necesita un constructor por defecto (sin parámetros) si vas a usar getters/setters. Si usas campos public o paquete por defecto con acceso directo, también funciona.
Main.java
package com.ejercicios.json;

import com.google.gson.Gson;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Persona p = new Persona("Lucía", 25);

        // Guardar en persona.json
        try (Writer w = new FileWriter("persona.json")) {
            gson.toJson(p, w);
        }

        // Cargar desde persona.json
        try (Reader r = new FileReader("persona.json")) {
            Persona cargada = gson.fromJson(r, Persona.class);
            System.out.println("Nombre: " + cargada.getNombre());
            System.out.println("Edad: " + cargada.getEdad());
        }
    }
}
#  Paso a paso
1.  Se crea una instancia Persona p = new Persona("Lucía", 25).
gson.toJson(p, writer) escribe la representación JSON en persona.json.
gson.fromJson(reader, Persona.class) lee el archivo y reconstruye el objeto Persona.
2.  Se imprimen los atributos leídos.

#   Ejemplo de salida en consola

Nombre: Lucía
Edad: 25

#   Contenido generado en persona.json
{"nombre":"Lucía","edad":25}


#  Cómo ejecutar
En IntelliJ IDEA: File > Open… → selecciona la carpeta Ejercicio2. Abre Main.java y pulsa ▶️ Run. Maven descargará Gson automáticamente.
Por terminal (opcional):
mvn compile

#  Buenas prácticas y mejoras sugeridas
UTF-8 explícito: usar OutputStreamWriter/InputStreamReader con StandardCharsets.UTF_8 para evitar problemas con acentos.
