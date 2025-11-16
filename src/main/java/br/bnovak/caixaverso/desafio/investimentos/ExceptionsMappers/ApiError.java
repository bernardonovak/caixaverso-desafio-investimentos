package br.bnovak.caixaverso.desafio.investimentos.ExceptionsMappers;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class ApiError {

	@Schema(
			description = "Código de erro HTTP", 
			implementation = Integer.class,
			type = SchemaType.INTEGER
			)
	Integer codigo;
	
	@Schema(
			description = "Mensagem de erro", 
			implementation = String.class,
			type = SchemaType.STRING
			)
	String mensagem;
	
	@Schema(
			description = "Descrição do erro", 
			implementation = String.class,
			type = SchemaType.STRING
			)
	String detalhes;
	
	public ApiError(Integer codigo, String mensagem ) {
		 this(codigo, mensagem, null);
	}
	
	public ApiError(Integer codigo, String mensagem, String detalhes) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

}
