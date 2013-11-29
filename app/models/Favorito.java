package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.JsonNode;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Favorito extends Model{

	@Id
	public Long id;
	
	@Required
	public String direccion;
	
	@ManyToOne
	public Usuario usuario;
	
	public Favorito(JsonNode input) {
		super();
		
		this.direccion = input.get("direccion").asText();
	}
}
