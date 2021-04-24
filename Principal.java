package ejercicio1;

import dominio.Archivo;
import dominio.Persona;

public class Principal {

	public static void main(String[] args) {
		//Persona p = new Persona("asdas","Santiago","Flores");
		
		Archivo _archivo = new Archivo("Personas.txt");
		_archivo.getListaDePersonasFromArchivo();
	}

}
