package ejercicio1;

import java.io.IOException;

import dominio.Archivo;
import dominio.Persona;
import dominio.ruta;

public class Principal {

	public static void main(String[] args) throws IOException {
		//Persona p = new Persona("asdas","Santiago","Flores");
		
//		Archivo _archivo = new Archivo("Personas.txt");
//		_archivo.getListaDePersonasFromArchivo();
		
		Archivo archivo = new Archivo("Personas.txt");
		archivo.setRuta2("Resultado.txt");
		if(archivo.existe("Resultado.txt")) {
			System.out.println("El archivo ya existe");
		}else {
			System.out.println("El archivo no existe");
			archivo.crearArchivo();
		}
		archivo.leer_letra();

	}

}
