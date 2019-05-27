package br.com.hbsis.controller;

import java.text.Format;
import java.text.SimpleDateFormat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.hbsis.model.Retorno;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Country;
import net.aksingh.owmjapis.core.OWM.Language;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.ForecastData;
import net.aksingh.owmjapis.model.param.WeatherData;
import net.aksingh.owmjapis.util.WeatherConditions;

public class PrevisaoController {
	
	public Retorno buscarPrevisaoPorDia(String prmCidade) {
		try {
			OWM owm = new OWM("eb8b1a9405e659b2ffc78f0a520b1a46");
			WeatherConditions wd = new WeatherConditions();
			DailyWeatherForecast cwd;
			ForecastData fd;
			JsonObject jsonObject;
			JsonArray jsonArray = new JsonArray();
			Format fmtDia = new SimpleDateFormat("dd/MM/yyyy");

			owm.setUnit(Unit.METRIC);
			owm.setLanguage(Language.PORTUGUESE);

			try {
				cwd = owm.dailyWeatherForecastByCityName(prmCidade, Country.BRAZIL, 6);

				for (int i = 1; i < cwd.getDataList().size(); i++) {
					fd = cwd.getDataList().get(i);					
					jsonObject = new JsonObject();
					jsonObject.addProperty("dia", fmtDia.format(fd.getDateTime()));
					jsonObject.addProperty("minima", fd.getTempData().getTempMin());
					jsonObject.addProperty("maxima", fd.getTempData().getTempMax());
					jsonObject.addProperty("imagem",
							wd.getIconLink(wd.getIconCode(fd.getWeatherList().get(0).getConditionId()), true));
					jsonObject.addProperty("umidade", fd.getHumidity());
					jsonObject.addProperty("info", fd.getWeatherList().get(0).getMoreInfo());
					jsonObject.addProperty("vento", Math.round((fd.getSpeed()) * 3.6));
					jsonArray.add(jsonObject);
				}
				return new Retorno(0, jsonArray);
			} catch (APIException e) {
				return new Retorno(1, "Ocorreu um erro ao buscar a previsão por dias!");
			}
		} catch (Exception e) {
			return new Retorno(1, "Ocorreu um erro na aplicação!");
		}
	}
	
	public Retorno buscarPrevisaoPorHora(String prmCidade) {
		try {
			OWM owm = new OWM("eb8b1a9405e659b2ffc78f0a520b1a46");
			HourlyWeatherForecast cwh;
			WeatherData wd;
			JsonObject jsonObject;
			JsonArray jsonArray = new JsonArray();
			Format fmtDia = new SimpleDateFormat("dd/MM/yyyy");
			Format fmtHora = new SimpleDateFormat("HH:mm:ss");

			owm.setUnit(Unit.METRIC);
			owm.setLanguage(Language.PORTUGUESE);
			try {
				cwh = owm.hourlyWeatherForecastByCityName(prmCidade, Country.BRAZIL);
				for (int i = 0; i < cwh.getDataList().size(); i++) {
					wd = cwh.getDataList().get(i);
					jsonObject = new JsonObject();
					jsonObject.addProperty("dia", fmtDia.format(wd.getDateTime()));
					jsonObject.addProperty("hora", fmtHora.format(wd.getDateTime()));
					jsonObject.addProperty("temperatura", wd.getMainData().getTemp());
					jsonObject.addProperty("info", wd.getWeatherList().get(0).getMoreInfo());
					jsonObject.addProperty("umidade", wd.getMainData().getHumidity());
					jsonObject.addProperty("vento",  Math.round(wd.getWindData().getSpeed()* 3.6));
					jsonArray.add(jsonObject);
				}
				return new Retorno(0, jsonArray);
			} catch (APIException e) {
				return new Retorno(1, "Ocorreu um erro ao buscar a previsão por horas!");
			}
		} catch (

		Exception e) {
			return new Retorno(1, "Ocorreu um erro na aplicação!");
		}
	}
}
