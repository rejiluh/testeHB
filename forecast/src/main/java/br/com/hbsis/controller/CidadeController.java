package br.com.hbsis.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.hbsis.model.Retorno;

public class CidadeController {
	final String NOME_ARQUIVO = "C:\\temp\\Cidades.json";

	public JsonObject lerArquivo() throws IOException {
		existeArquivo();
		BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO));
		JsonObject jsonObject = new Gson().fromJson(br, JsonObject.class);
		br.close();
		return jsonObject;
	}

	public void existeArquivo() throws IOException {
		File arq = new File("C:\\temp");
		if (!arq.exists())
			arq.mkdirs();

		arq = new File(NOME_ARQUIVO);
		if (!arq.exists()) {
			new File(NOME_ARQUIVO).createNewFile();
			FileWriter fw = new FileWriter(NOME_ARQUIVO);
			fw.append("{\"cidades\":[]}");
			fw.close();
		}
	}

	public Retorno gravarCidade(String prmCidade) throws IOException {
		JsonObject jsonObject;
		jsonObject = lerArquivo();
		JsonArray jsonArray = jsonObject.getAsJsonArray("cidades");
		boolean achou = false;
		for (int i = 0; i < jsonArray.size(); i++)
			if (jsonArray.get(i).getAsString().trim().toLowerCase().matches(prmCidade.trim().toLowerCase()))
				achou = true;

		if (achou) {
			return new Retorno(1, "Cidade já cadastrada, informe outra!");
		}else {
			PrevisaoController pc = new PrevisaoController();
			Retorno r = new Retorno();
			r = pc.buscarPrevisaoPorDia(prmCidade);
			if(r.getCodigo() == 1)
				return new Retorno(2, "Cidade não possui dados de previsão!");	
		}
			
		jsonArray.add(prmCidade);
		FileWriter writeFile = null;
		writeFile = new FileWriter(NOME_ARQUIVO);
		writeFile.write(jsonObject.toString());
		writeFile.close();

		return new Retorno(0, "Cidade cadastrada com sucesso!");
	}
}
