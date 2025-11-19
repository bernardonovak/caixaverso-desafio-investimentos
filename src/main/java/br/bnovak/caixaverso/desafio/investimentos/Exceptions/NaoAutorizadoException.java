package br.bnovak.caixaverso.desafio.investimentos.Exceptions;

import java.io.Serial;

public class NaoAutorizadoException extends Exception {

    @Serial
	private static final long serialVersionUID = -3336983775899403575L;

	public NaoAutorizadoException(String message) {
		super(message);
	}

}
