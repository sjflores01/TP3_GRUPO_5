package ejercicio1;

import dominio.Archivo;
import dominio.Persona;

public class Principal {

	public static void main(String[] args) {
		//Persona p = new Persona("asdas","Santiago","Flores");
		
		Archivo _archivo = new Archivo("Personas.txt");
		
		
		_archivo.getListaDePersonasFromArchivo();
		
		
		
		Archivo R_archivo = new Archivo("Resultado.txt");
		
	
			
				if(R_archivo.GrabarNuevoArchivo(R_archivo.getRuta(),_archivo.getListaDePersonas()))
				{	
				System.out.println("Nuevo archivo generado exitosamente");
				
	          }
				
				
		
			
			
		
		
		
	}

}
