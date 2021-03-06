package dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import dominio.Persona;

public class Archivo {

	private String ruta;
	private String ruta2;
	
	public Archivo(String ruta){
		this.ruta = ruta;
	}
	
	public Archivo(String ruta, String ruta2 ){
		this.ruta = ruta;
		this.ruta2 = ruta2;
	}
	
	public boolean existe(String ruta) {
		File archivo = new File(ruta);
		if(archivo.exists())
			return true;
		return false;
	}

	public boolean crearArchivo() {
		
		System.out.println("Creo archivo");
		FileWriter escribir;
		try {
			escribir = new FileWriter(ruta2, true);
			escribir.write("");
			escribir.close();
			return true;
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getRuta2() {
		return ruta2;
	}

	public void setRuta2(String ruta2) {
		this.ruta2 = ruta2;
	}

	public void leer_letra() {
			
		FileReader entrada;
		try {
			entrada = new FileReader(this.ruta);
			BufferedReader bufferDeLector = new BufferedReader(entrada);
			
			String linea = "";
						
			while(linea != null) {
				linea = bufferDeLector.readLine();
				escribirLetra(linea + "\n");
			}

			entrada.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escribirLetra(String texto) {
		
		try {
			FileWriter escribir = new FileWriter(ruta2, true);
			

				escribir.write(texto);

			
			escribir.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Persona> getListaDePersonasFromArchivo(){
		FileReader lectorDeArchivo;
		ArrayList<Persona> listaDePersonas = new ArrayList<Persona>();
		
		try {
			lectorDeArchivo = new FileReader(this.ruta);
			BufferedReader bufferDeLector = new BufferedReader(lectorDeArchivo);
			
			String lineaDePersona = "";
						
			while(lineaDePersona != null) {
				lineaDePersona = bufferDeLector.readLine();
				
				// Aunque sea redundante, es importante preguntar aca si lineaDePersona es != null que sino java tira un error de NullPointerException
				if(lineaDePersona != null ) {
					String[] separadorDeNombreYDNI = lineaDePersona.split("-");
					String nombre = separadorDeNombreYDNI[0];
					String apellido = separadorDeNombreYDNI[1];
					String dni = separadorDeNombreYDNI[2];	
					
					Persona _persona = new Persona(dni, nombre, apellido);
	
					// to do: antes de agregar hacer la validacion del dni 
					if( ! this.EsDuplicado(listaDePersonas, _persona)) {
						listaDePersonas.add(_persona);	
					}
			 	
				}
				
			
				System.out.println(lineaDePersona);
				
			}
		
			//to do: antes de retornar la lista ordenar alfabeticamente
			
			bufferDeLector.close();
			

		} catch (IOException e) {
			System.out.println("No pude encontrar el archivo");
		}
		
		
		System.out.println(listaDePersonas);
		return listaDePersonas;
	}
	
	
	public boolean EsDuplicado(ArrayList<Persona> listaDePersonas, Persona _personaABuscar){
				
		boolean esDuplicado = false;
		
		Iterator<Persona> personaIterator = listaDePersonas.iterator();
		
		while(personaIterator.hasNext()) {
			 Persona _persona = (Persona) personaIterator.next();
			 
			 if(_persona.getNombre() == _personaABuscar.getNombre() && 
				_persona.getApellido() == _personaABuscar.getApellido() &&
				_persona.getDni() == _personaABuscar.getDni() ) {
			
				 esDuplicado = true;
				 
			 }
			 
		}

		
		return esDuplicado;
	
	}

	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	
	
}
