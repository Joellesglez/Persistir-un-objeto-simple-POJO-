package com.ejercicios.json;

import com.google.gson.Gson;
import java.io.*;

class Persona {
    String nombre;
    int edad;
    Persona(String n,int e){ nombre=n; edad=e; }

}

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Persona p = new Persona("Samuel",27);


        try(FileWriter fw=new FileWriter("persona.json")){
            gson.toJson(p, fw);
        }

        try(FileReader fr=new FileReader("persona.json")){
            Persona cargada = gson.fromJson(fr, Persona.class);
            System.out.println(cargada.nombre + " - " + cargada.edad);
        }
    }
}
