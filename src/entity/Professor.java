package entity;

public class Professor extends Usuario {
	
	private int id;
	private int id_usuario;

	public Professor() {
	
	}
	
	public Professor(int id_usuario){
		this.id_usuario = id_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return super.getNome();
	}
	
	@Override
	public void setNome(String nome) {
		super.setNome(nome);
	}
}
