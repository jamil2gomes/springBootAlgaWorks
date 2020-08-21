package br.com.cursoSpring.app.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cursoSpring.app.exceptions.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var campos = new ArrayList<Problema.Campo>();
		
		for(ObjectError erro: ex.getBindingResult().getAllErrors()) {
			
			String nome = ((FieldError) erro).getField();
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			
			campos.add(new Problema.Campo(nome, mensagem));	
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setMensagem("Um ou mais campos estão inválidos!");
		problema.setData(LocalDateTime.now());
		problema.setCampos(campos);
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest webRequest)
	{
		var status = HttpStatus.BAD_REQUEST;
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setMensagem(ex.getMessage());
		problema.setData(LocalDateTime.now());	
		
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, webRequest);
	}

}
