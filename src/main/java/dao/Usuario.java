package dao;

public class Usuario {
	private int codigo;
	private String login;
    private String senha;
    private String email;
    private String nome;
    private String tipo;
    private String foto;
	private boolean status;    

    public Usuario(int codigo, String login, String senha, String email, String nome, String tipo, String foto, boolean status) {		
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.tipo = tipo;
		this.foto = foto;
		this.status = status;
	}
    
    
    public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
}