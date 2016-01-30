package entity;

public class Usuario {
	private int id;
	private String nome;
	private String login;
	private String email;
	private String link_foto;
	private String curso;
	
	public Usuario() {

	}
	
	public Usuario(String nome, String login, String email, String link_foto, String curso){
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.link_foto = link_foto;
		this.curso = curso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLink_foto() {
		return link_foto;
	}

	public void setLink_foto(String link_foto) {
		this.link_foto = link_foto;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}
