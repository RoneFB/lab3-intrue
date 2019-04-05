package dao;

public class Aula {
	private int cod;
	private int cod_modulo;
	private String nome;
    private String video;
    private String conteudo;
    private boolean status;
    
    
    public Aula(int cod, int cod_modulo, String nome, String video, String conteudo, boolean status) {		
		this.cod = cod;
		this.cod_modulo = cod_modulo;
		this.nome = nome;
		this.video = video;
		this.conteudo = conteudo;
		this.status = status;
	}
    
    
    public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}


	public int getCod_modulo() {
		return cod_modulo;
	}


	public void setCod_modulo(int cod_modulo) {
		this.cod_modulo = cod_modulo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getConteudo() {
		return conteudo;
	}


	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
    
    
	
    //Métodos Próprios
    public boolean comparar(Aula aula){
        if(this.nome.equals(aula.nome)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comparar_fk(int cod_modulo){
        if(this.cod_modulo == cod_modulo){
            return true;
        }else{
            return false;
        }
    }
}
