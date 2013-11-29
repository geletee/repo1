package controllers;

import models.Favorito;
import models.Usuario;

import org.codehaus.jackson.JsonNode;

import play.mvc.Controller;
import play.mvc.Result;

public class Favoritos extends Controller {
	
	
	public static Result create(Long uId) {
		Result res = null;
		
		Usuario usuario = Usuario.finder.byId(uId);
		if (usuario == null) {
			res = notFound();
		}
		else {
			JsonNode input = request().body().asJson();
			Favorito url = new Favorito(input);
			url.usuario = usuario;
			url.save();
			res = ok();
		}
		
		return res;
	}
	

}