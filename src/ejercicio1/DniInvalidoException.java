package ejercicio1;

import java.io.IOException;

public class DniInvalidoException extends IOException {
	public DniInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		return "El DNI no puede contener letras!";
	}

}
