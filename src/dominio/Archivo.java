package dominio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import dominio.Persona;
import java.util.Collections;

public class Archivo {

	private String ruta;
	private ArrayList<Persona> listaDePersonas = new ArrayList<Persona>();
	
	public Archivo(String ruta){
		this.ruta = ruta;
	}
	
	public boolean existe() {
		File archivo = new File(ruta);
		if(archivo.exists())
			return true;
		return false;
	}

	public ArrayList<Persona> getListaDePersonasFromArchivo(){
		FileReader lectorDeArchivo;
		
		
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
	
					// pedro:
					// en el if deberiamos agregar la funcion de verificarDni
					// pero lo dejamos para la entrega asi vemos que funque todo
					
					if( ! this.EsDuplicado(listaDePersonas, _persona)) {
						listaDePersonas.add(_persona);	
					}
			 	
				}
				
			
			//	System.out.println(lineaDePersona);
				// pedro: lo comente para no llenar la consola
			}
		
			
			bufferDeLector.close();
			

		} catch (IOException e) {
			System.out.println("No pude encontrar el archivo");
		}
		
		
		listaDePersonas = OrdenarAlfabeticamente(listaDePersonas);
		
		
		for (Persona persona : listaDePersonas) {
			
			System.out.println(persona.toString());
		}
		
		
		return listaDePersonas;
	}
	
	
	//ordenamos por apellido de a-z
	public ArrayList<Persona> OrdenarAlfabeticamente (ArrayList<Persona> lista){
		
		Persona aux; 
		int i, j;
		
        for (i = 0; i < lista.size() - 1; i++) {
            for (j = 0; j < lista.size() - i - 1; j++) {
            	
                if (lista.get(j + 1).getApellido().charAt(0) < lista.get(j).getApellido().charAt(0)) {
                	
                    aux = lista.get(j + 1);
                    lista.set(j + 1, lista.get(j));
                    lista.set(j, aux);
                }
            }
        }
		
		return lista;
	}
	
	
	public boolean EsDuplicado(ArrayList<Persona> listaDePersonas, Persona _personaABuscar){
				
		boolean esDuplicado = false;
		
		Iterator<Persona> personaIterator = listaDePersonas.iterator();
		
		while(personaIterator.hasNext()) {
			 Persona _persona = (Persona) personaIterator.next();
			 
			 if(_persona.getNombre().compareTo(_personaABuscar.getNombre()) == 0 && 
				_persona.getApellido().compareTo(_personaABuscar.getApellido()) == 0 &&
				_persona.getDni().compareTo(_personaABuscar.getDni()) == 0 ) {
			
				 esDuplicado = true;
				 
			 }
			 
		}

		
		return esDuplicado;
	
	}

	
	public ArrayList<Persona> getListaDePersonas() {
		return listaDePersonas;
	}

	public void setListaDePersonas(ArrayList<Persona> listaDePersonas) {
		this.listaDePersonas = listaDePersonas;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	//AGREGO FUNCINO PARA CREAR ARCHIVO
	public boolean creaArchivo()
	{
		
		FileWriter escritura;
		try {
			escritura = new FileWriter(this.ruta, true);
			escritura.write("");
			escritura.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
			
	}
	
	public Boolean GrabarNuevoArchivo(String rutaArchivo,ArrayList<Persona> lista)
	{
		
		
		try 
		{
			if(existe())
			{
			for (Persona persona : lista) {
				
				FileWriter entrada = new FileWriter(rutaArchivo, true);
				BufferedWriter miBuffer = new BufferedWriter(entrada);
				miBuffer.write(persona.toString());
				miBuffer.close();
				entrada.close();
				
			}
			
			
			}
			else
			{
				creaArchivo();
				
				for (Persona persona : lista) {
					
					FileWriter entrada = new FileWriter(rutaArchivo, true);
					BufferedWriter miBuffer = new BufferedWriter(entrada);
					miBuffer.write(persona.toString());
					miBuffer.close();
					entrada.close();
					
				}
				
				
			}
			return true;
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	public void leer_archivo(String rutaArchivo) {
		FileReader entrada;
		try {
			entrada = new FileReader(rutaArchivo);
			BufferedReader miBuffer = new BufferedReader(entrada);
			
		   String linea = "";
			while (linea != null) {
				System.out.println("\n"+linea);
				linea = miBuffer.readLine();
			}
			miBuffer.close();
			entrada.close();

		} catch (IOException e) {
			System.out.println("No se encontro el archivo");
		}
	}
	
	
}
