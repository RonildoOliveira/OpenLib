package entity;

public class Usuario {
	private int id;
	private String nome;
	private String senha;
	private String email;
	private String link_foto;
	private String curso;
	
	public Usuario() {

	}
	
	public Usuario(String nome, String login, String email, String link_foto, String curso){
		this.nome = nome;
		this.senha = login;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String login) {
		this.senha = login;
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
