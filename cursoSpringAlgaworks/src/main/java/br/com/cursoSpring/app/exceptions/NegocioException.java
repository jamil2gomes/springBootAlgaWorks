package br.com.cursoSpring.app.exceptions;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 901807986604958474L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}

}
