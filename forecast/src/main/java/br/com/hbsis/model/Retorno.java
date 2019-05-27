package br.com.hbsis.model;

import com.google.gson.JsonArray;

public class Retorno {
	int codigo;
	String mensagem;
	JsonArray retornoJson;
	
	
	
	public Retorno() {
		super();
	}

	public Retorno(int codigo) {
		this.codigo = codigo;
	}
	
	public Retorno(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}
	
	
	
	public Retorno(int codigo, JsonArray retornoJson) {
		this.codigo = codigo;
		this.retornoJson = retornoJson;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public JsonArray getRetornoJson() {
		return retornoJson;
	}

	public void setRetornoJson(JsonArray retornoJson) {
		this.retornoJson = retornoJson;
	}
	
	
	
	

}
