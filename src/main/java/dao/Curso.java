package dao;

import java.util.LinkedList;
import java.util.List;

public class Curso {
	private int codigo;
	private String nome;
    private String duracao;
    private String descricao; 
	private boolean status; 
    private String categoria;    
    private List<Modulo> modulos = new LinkedList<Modulo>(); 

    public Curso(int codigo, String nome, String duracao, String descricao, boolean status, String categoria) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.duracao = duracao;
		this.descricao = descricao;
		this.status = status;
		this.categoria = categoria;
		
	}   
    
    
    public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDuracao() {
		return duracao;
	}


	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
    
	
	
	//Mï¿½todos Prï¿½prios
    public List<Modulo> getModulos() {
        return modulos;
    }
    

    public void cadastrarModulo(Modulo modulo){
        modulos.add(modulo);
    }
    

    public Modulo pesquisarModuloNome(String nome){
        for(Modulo modulo:modulos){
            if(modulo.getNome().equals(nome)) return modulo;
        }
        return null;
    }
    

    public boolean comparar(Curso curso){
        if(this.nome.equals(curso.nome)){
            return true;
        }else{
            return false;
        }
    }
}
