package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.ConstraintViolation;

import org.codehaus.jackson.JsonNode;
import org.w3c.dom.Document;

import play.data.validation.Constraints.Required;
import play.data.validation.Validation;
import play.db.ebean.Model;

@Entity
public class Usuario extends Model {

	@Id
	public Long id;

	@Required
	public String nick;
	public String nombre;
	public String telefono;
	public String email;
	public String direccion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	public List<Favorito> favs = new ArrayList<Favorito>();

	public static Finder<Long, Usuario> finder = new Finder<Long, Usuario>(
			Long.class, Usuario.class);

	public Usuario(JsonNode input) {
		super();

		this.nick = input.get("nick").asText();
		this.nombre = input.get("nombre").asText();
		this.telefono = input.get("telefono").asText();
		this.email = input.get("email").asText();
		this.direccion = input.get("direccion").asText();
	}

	public Usuario(Document input) {
		super();
		this.nick = input.getElementsByTagName("nick").item(0).getTextContent();
	}

	public static List<Usuario> findPagina() {
		return finder.findList();
	}

	public boolean changeData(Usuario newData) {
		boolean changed = false;

		if (newData.nick != null) {
			this.nick = newData.nick;
			changed = true;
		}

		return changed;
	}

	public List<String> directValidate() {
		Set<ConstraintViolation<Usuario>> violations = Validation
				.getValidator().validate(this);
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<Usuario> cv : violations) {
			errors.add(cv.getMessage());
		}
		String violation = this.validate();
		if (violation != null) {
			errors.add(violation);
		}
		return errors;
	}

	public String validate() {
		return null;
	}

	public List<String> validateAndSave() {
		List<String> errors = directValidate();
		if (errors.size() == 0) {
			this.save();
		}
		return errors;
	}

}
