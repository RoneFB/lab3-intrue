package start;

import static spark.Spark.*;

import java.util.LinkedList;
import java.util.List;

import controller.Controller;
import dao.*;
import model.Plataforma;

public class MainServer {
	
	final static Plataforma model = new Plataforma();	
	
	private static List<Modulo> modulos = new LinkedList<Modulo>();	
	
	private static List<Aula> aulas1 = new LinkedList<Aula>();
	private static List<Aula> aulas2 = new LinkedList<Aula>();
	private static List<Aula> aulas3 = new LinkedList<Aula>();
	private static List<Aula> aulas4 = new LinkedList<Aula>();
	private static List<Aula> aulas5 = new LinkedList<Aula>(); 
		
    public static void main(String[] args) {
		// Get port config of heroku on environment variable
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));    
        } else {
            port = 8083;        
        }
        port(port);

		//conteudo html, css e javascript                   
		staticFileLocation("/static");    

		inicializarPlataforma();

		Controller controller = new Controller(model);                                    
		 
		controller.listarCursos();
		controller.buscarCurso();  
		controller.cadastrarCurso();  
		controller.excluirCurso();
		controller.alterarCurso();
		 
		controller.cadastrarModulo();  
		controller.excluirModulo();
		controller.alterarModulo();
		
		controller.cadastrarAula();
		controller.excluirAula();
		controller.alterarAula();
		
		controller.cadastrarUsuario();
		
		controller.login();
    }
       
    public static void inicializarPlataforma(){      
    	aulas1.add(new Aula(1,1,"Bem-Vindo","link","Bem-Vindo",true));    
    	aulas1.add(new Aula(2,1,"Variaveis e Tipos de Dados","link","Variaveis e Tipos de Dados",true));
    	aulas1.add(new Aula(3,1,"Sintaxe","link","Sintaxe",true));        
    	
    	modulos.add(new Modulo(1,1,"Introdução",true, aulas1));  
    	
    	aulas2.add(new Aula(4,2,"If-Else","link","If-Else",true));
    	aulas2.add(new Aula(5,2,"Switch-Case","link","Switch-Case",true));       
    	aulas2.add(new Aula(6,2,"Try-Catch","link","Try-Catch",true)); 
    	 	
    	modulos.add(new Modulo(2,1,"Estrutura de Decisão",true, aulas2)); 
    	
    	aulas3.add(new Aula(7,3,"For","link","For",true));
    	aulas3.add(new Aula(8,3,"While","link","While",true));
    	aulas3.add(new Aula(9,3,"Do While","link","Do While",true));  
    	 	
    	modulos.add(new Modulo(3,1,"Estrutura de Repetição",true, aulas3));  
    	
    	aulas4.add(new Aula(10,4,"Public","link","Public",true));
    	aulas4.add(new Aula(11,4,"Private","link","Private",true));
    	aulas4.add(new Aula(12,4,"Protected","link","Protected",true));                 
    	 	
    	modulos.add(new Modulo(4,1,"Funções",true, aulas4));  
    	
    	aulas5.add(new Aula(13,5,"Atributos","link","Atributos",true));                                    
    	aulas5.add(new Aula(14,5,"Métodos","link","Métodos",true));  
    	aulas5.add(new Aula(15,5,"Herança","link","Herança",true));  
    	 	
    	modulos.add(new Modulo(5,1,"Orientação à Objetos",true, aulas5));  
    	
    	model.cadastrarCurso(new Curso(1,"C","10h","Curso de Programção em linguagem C",true,"Back-End",modulos));  
    	
    	model.cadastrarCurso(new Curso(2,"Java","8h","Curso de Programação em linguagem Java",true,"Front-End", modulos)); 
    	
    	model.cadastrarCurso(new Curso(3,"Python","6h","Curso de Programação em linguagem Python",true,"Mobile", modulos)); 
    	
    	model.cadastrarCurso(new Curso(4,"PHP","5h","Curso de Programação em linguagem PHP",true,"Banco de Dados", modulos)); 
    	
    	model.cadastrarCurso(new Curso(5,"Ruby","4h","Curso de Programação em linguagem Ruby",true,"Cloud", modulos)); 
    	
    	model.cadastrarUsuario(new Usuario(1,"admin","admin","Administrador","Administrador","admin",true));
    }
}
