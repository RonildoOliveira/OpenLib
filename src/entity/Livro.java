package entity;

public class Livro {
	private int id;
	private int id_editora;
	private String titulo;
	private int num_edicao;
	private int ano;
	private String link_capa;
	private String link_down;
	private String observacao;
	
	public Livro() {
	
	}
	
	public Livro(String titulo, int ano, int id_editora, 
			int num_edicao, String link_capa, String link_down, String observacao) {
		this.titulo = titulo;
		this.ano = ano;
		this.id_editora = id_editora;
		this.num_edicao = num_edicao;
		this.link_capa = link_capa;
		this.link_down = link_down;
		this.observacao = observacao;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getId_editora() {
		return id_editora;
	}

	public void setId_editora(int id_editora) {
		this.id_editora = id_editora;
	}

	public int getNum_edicao() {
		return num_edicao;
	}

	public void setNum_edicao(int num_edicao) {
		this.num_edicao = num_edicao;
	}

	public String getLink_capa() {
		return link_capa;
	}

	public void setLink_capa(String link_capa) {
		this.link_capa = link_capa;
	}

	public String getLink_down() {
		return link_down;
	}

	public void setLink_down(String link_down) {
		this.link_down = link_down;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
