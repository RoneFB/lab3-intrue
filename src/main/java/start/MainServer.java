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
		 
		controller.buscarUsuario();  
		controller.cadastrarUsuario();   
		controller.login(); 
		
		controller.inscreverCurso();
		controller.listarUsuarioCursos();   
		controller.alterarProgressoCurso();                    
		controller.buscarUsuarioCurso();
    }
      
    public static void inicializarPlataforma(){      
    	/*
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
    	
    	model.cadastrarCurso(new Curso(1,"Programação com Python","10h",
    			"Neste curso, você aprenderá os fundamentos da linguagem de programação Python, juntamente com as melhores práticas de programação. Você aprenderá a representar e armazenar dados usando tipos e variáveis ​​de dados do Python e usar condicionais e loops para controlar o fluxo de seus programas. Por fim, você aprenderá a encontrar e usar módulos na Biblioteca Padrão do Python e em outras bibliotecas de terceiros.",true,"Back-End",modulos));  
    	
    	model.cadastrarCurso(new Curso(2,"HTML, CSS e JavaScript","5h",
    			"A demanda por desenvolvedores front-end é generalizada em todos os setores e continua aumentando. Ao dominar as habilidades valiosas ensinadas neste programa, você estará preparado para funções em uma ampla variedade de empresas, de startups a organizações globais. Os projetos que você construirá e o portfólio que você desenvolverá fornecerão ampla evidência de seus conhecimentos.",true,"Front-End", modulos)); 
    	
    	model.cadastrarCurso(new Curso(3,"Aplicações com React e Redux","10h",
    			"O React é uma poderosa biblioteca JavaScript ideal para criar interfaces de usuário interativas orientadas a dados e é usada por algumas das marcas mais bem sucedidas do mundo, incluindo Facebook, Netflix, Airbnb e muito mais.",true,"Mobile", modulos)); 
    	
    	model.cadastrarCurso(new Curso(4,"Arqt e Modelagem de Dados","15h",
    			"Este curso é uma introdução rápida e divertida ao uso de um banco de dados relacional de seu código, usando exemplos em Python. Você aprenderá as noções básicas de SQL (a Linguagem de Consulta Estruturada) e o design do banco de dados, bem como a API do Python para conectar o código Python a um banco de dados. Você também aprenderá um pouco sobre como proteger seus aplicativos da Web baseados em banco de dados contra problemas comuns de segurança.",true,"Banco de Dados", modulos));  
    	
    	model.cadastrarCurso(new Curso(5,"Virtualização com AWS","10h",
    			"Neste curso, você aprenderá os fundamentos básicos do Linux que todos os desenvolvedores da Web precisam saber para compartilhar seus aplicativos da Web com o mundo! Você obterá um aplicativo Python WSGI básico instalado e em execução em uma máquina virtual do Vagrant que consulta dados de um banco de dados PostgreSQL.",true,"Cloud", modulos)); 
    	
    	model.cadastrarCurso(new Curso(6,"Bussiness Analytics c Power BI","10h",
    			"Analistas de negócios estão em alta demanda. Neste programa, você aprenderá a aplicar análises preditivas e business intelligence para resolver problemas comerciais do mundo real. Você fará isso fluindo em dois dos principais pacotes de software: o Alteryx, uma ferramenta que permite preparar e analisar dados rapidamente; e o Tableau, uma poderosa ferramenta de visualização de dados. Ao se formar no programa, você estará pronto para se candidatar a uma ampla variedade de funções de analista de negócios.",true,"Cloud", modulos)); 
    	
    	model.cadastrarUsuario(new Usuario(1,"admin","admin","admin@email.com","Othon Godoy","admin",
    			"img/users/personal-photo.jfif",true));
    	
    	model.cadastrarUsuario(new Usuario(2,"aluno","aluno","aluno@email.com","Rafael Ferreira","aluno",
    			"img/users/user.png",true));
    	*/
    }
}
