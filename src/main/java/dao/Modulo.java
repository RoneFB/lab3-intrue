package dao;

import java.util.LinkedList;
import java.util.List;

public class Modulo {
	private int cod;
	private int cod_curso;
	private String nome;
    private boolean status;
    private List<Aula> aulas = new LinkedList<Aula>();
    
    
    public Modulo(int cod, int cod_curso, String nome, boolean status, List<Aula> aulas) {
		super();
		this.cod = cod;
		this.cod_curso = cod_curso;
		this.nome = nome;
		this.status = status;
		this.aulas = aulas;
	}
    
    
    public int getCod() {
		return cod;
	}
    

	public void setCod(int cod) {
		this.cod = cod;
	}
	

	public int getCod_curso() {
		return cod_curso;
	}


	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	
	
	public String getNome() {
		return nome;
	}
	

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
    //M�todos Pr�prios
    public List<Aula> getAulas(){
        return aulas;
    }
    

    public void cadastrarAula(Aula aula){
        aulas.add(aula);
    }
    

    public Aula pesquisarAulaNome(String nome){
        for(Aula aula:aulas){
            if(aula.getNome().equals(nome)) return aula;
        }
        return null;
    }
    

    public boolean comparar(Modulo modulo){
        if(this.nome.equals(modulo.nome)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comparar_fk(int cod_curso){
        if(this.cod_curso == cod_curso){
            return true;
        }else{
            return false;
        }
    }
}
