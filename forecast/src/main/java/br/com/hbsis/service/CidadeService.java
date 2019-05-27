package br.com.hbsis.service;

import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.hbsis.controller.CidadeController;
import br.com.hbsis.model.Retorno;

@Path("/ws")
public class CidadeService {
	CidadeController cc = new CidadeController();

	@POST
	@Path("/listar")
	@Produces(MediaType.TEXT_PLAIN)
	public String listarCidades() {
		ObjectMapper Obj = new ObjectMapper();
		try {
			return cc.lerArquivo().toString();
		} catch (IOException e) {
			try {
				return Obj.writeValueAsString(new Retorno(1, "Ocorreu um erro ao listas as cidades!"));
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@POST
	@Path("/salvar/{cidade}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String gravarCidade(@PathParam("cidade") String cidade) {
		ObjectMapper Obj = new ObjectMapper();
		try {
			return Obj.writeValueAsString(cc.gravarCidade(cidade));
		} catch (Exception e) {
			try {
				return Obj.writeValueAsString(new Retorno(1, "Ocorreu um erro ao gravar a cidade!"));
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

}
