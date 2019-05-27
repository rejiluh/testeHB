package br.com.hbsis.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.hbsis.controller.PrevisaoController;
import br.com.hbsis.model.Retorno;

@Path("/ws")
public class PrevisaoService {
	PrevisaoController pc = new PrevisaoController();
	
	@GET
	@Path("/previsaoDia")
	@Produces(MediaType.TEXT_PLAIN)
	public String buscarPrevisaoPorDia(@QueryParam("cidade") String cidade) {
		Retorno r = new Retorno();
		r = pc.buscarPrevisaoPorDia(cidade);
		if (r.getCodigo() == 0)
			return "{\"codigo\":\"0\",\"retornoDias\":" + r.getRetornoJson() + "}";
		else
			return "{\"codigo\":\"1\",\"msgErro\":\"Ocorreu um erro na aplicação!\"}";
	}

	@GET
	@Path("/previsaoHora")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPrevisaoHora(@QueryParam("cidade") String cidade) {
		Retorno r = new Retorno();
		r = pc.buscarPrevisaoPorHora(cidade);
		if (r.getCodigo() == 0)
			return "{\"codigo\":\"0\",\"retornoHoras\":" + r.getRetornoJson() + "}";
		else
			return "{\"codigo\":\"1\",\"msgErro\":\"Ocorreu um erro na aplicação!\"}";
	}
}
