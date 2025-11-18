package br.bnovak.caixaverso.desafio.investimentos.Exceptions;

import java.io.Serial;

public class NaoEncontradoException extends Exception {

	@Serial
    private static final long serialVersionUID = -5563897357984895396L;
	
	public NaoEncontradoException(String message) {
		super(message);
	}

}
