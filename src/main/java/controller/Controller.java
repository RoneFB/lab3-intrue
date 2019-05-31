package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import dao.Aula;
import dao.Curso;
import dao.Modulo;
import dao.Usuario;
import dao.UsuarioCurso;
import model.Plataforma;
import spark.Request;
import spark.Response;
import spark.Route;

public class Controller {
	 
	private Plataforma model;	
	
	public Controller(Plataforma model){
		this.model = model;
	}
	
	public void listarCursos(){
		get("/cursos/", (req, res) -> {	
			List<Curso> cursosEncontrados = model.listarCursos();	   
			return new Gson().toJson(cursosEncontrados);			
		});
	}
	
	public void buscarCurso(){
		get("/curso/:codigo", (req, res) -> {			
			Curso cursosEncontrados = model.buscarCursoCod(Integer.parseInt(req.params(":codigo")));	
			return new Gson().toJson(cursosEncontrados);			
		});
	}
	
	public void cadastrarCurso(){
		get("/cadastrarCurso/:codigo/:nome/:categoria/:descricao/:duracao", (req, res) -> {
			
			List<Modulo> modulos = new LinkedList<Modulo>();
			
			model.cadastrarCurso(new Curso(Integer.parseInt(req.params(":codigo")), req.params(":nome"), 
					req.params(":duracao"), req.params(":descricao"), true, req.params(":categoria"), modulos));	
			
			List<Curso> cursosEncontrados = model.listarCursos();	
			return new Gson().toJson(cursosEncontrados);		
		});
		
		/*
		post("/cadastrarCurso/", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	           response.header("Access-Control-Allow-Origin", "*");
	        	
	           JSONObject json = new JSONObject(request.body());
	        	
	           Integer codigo = Integer.parseInt(json.getString("codigo"));
	           String nome = json.getString("nome");
	           String categoria = json.getString("categoria");
	           String descricao = json.getString("descricao");
	           String duracao = json.getString("duracao");
	           List<Modulo> modulos = new LinkedList<Modulo>();
	           
	           JSONArray jsonResult = new JSONArray();
         	    
         	   try {
         		   
         		  model.cadastrarCurso(new Curso(codigo, nome, duracao, descricao, true, 
         				  categoria, modulos));
         		  
         		  List<Curso> cursos = model.listarCursos();
         		  
         		  for(Curso curso:cursos){
         			  JSONObject jsonObj = new JSONObject();
         			  jsonObj.put("codigo", curso.getCodigo());
         			  jsonObj.put("nome", curso.getNome());
         			  jsonObj.put("duracao", curso.getDuracao());
         			  jsonObj.put("descricao", curso.getDescricao());
         			  jsonObj.put("categoria", curso.getCategoria());
         			  jsonObj.put("modulos", curso.getModulos());
            		
         			  jsonResult.put(jsonObj);
         		  }
	             	
	         	  return jsonResult; 
	             	
         	   } catch (JSONException e) {	        				
	        		e.printStackTrace();
         	   }
         	    
         	  return null;
		   }
		});  
		*/  
	}
	
	public void excluirCurso(){
		get("/excluirCurso/:codigo", (req, res) -> {
			
			model.excluirCurso(Integer.parseInt(req.params(":codigo")));	
			
			List<Curso> cursosEncontrados = model.listarCursos();	   
			return new Gson().toJson(cursosEncontrados);		
		});
	}
	
	public void alterarCurso(){
		get("/alterarCurso/:codigo/:nome/:categoria/:descricao/:duracao", (req, res) -> {
			
			model.alterarCurso(Integer.parseInt(req.params(":codigo")), req.params(":nome"), req.params(":categoria"), 
					req.params(":descricao"), req.params(":duracao"));	
			
			List<Curso> cursosEncontrados = model.listarCursos();	
			return new Gson().toJson(cursosEncontrados);		
		});
		
		/*
		post("/alterarCurso/", new Route() {
			@Override
            public Object handle(final Request request, final Response response){
	        	
	           response.header("Access-Control-Allow-Origin", "*");
	        	
	           JSONObject json = new JSONObject(request.body());
	           
	           Integer codigo = Integer.parseInt(request.params("codigo"));
	           String nome = json.getString("nome");
	           String categoria = json.getString("categoria");
	           String descricao = json.getString("descricao");
	           String duracao = json.getString("duracao");
	           
	           JSONArray jsonResult = new JSONArray();
         	    
         	   try {
         		   
         		  model.alterarCurso(codigo, nome, categoria, 
      					descricao, duracao);
         		  
         		  List<Curso> cursos = model.listarCursos();
         		  
         		  for(Curso curso:cursos){
         			  JSONObject jsonObj = new JSONObject();
         			  jsonObj.put("codigo", curso.getCodigo());
         			  jsonObj.put("nome", curso.getNome());
         			  jsonObj.put("duracao", curso.getDuracao());
         			  jsonObj.put("descricao", curso.getDescricao());
         			  jsonObj.put("categoria", curso.getCategoria());
         			  jsonObj.put("modulos", curso.getModulos());
            		
         			  jsonResult.put(jsonObj);
         		  }
	             	
	         	  return jsonResult; 
	             	
         	   } catch (JSONException e) {	        				
	        		e.printStackTrace();
         	   }
         	    
         	  return null;
		   }
		}); 
		*/
	}
	
	public void cadastrarModulo(){
		get("/cadastrarModulo/:cod/:cod_curso/:nome", (req, res) -> {
			
			List<Aula> aulas = new LinkedList<Aula>();
			
			model.cadastrarModulo(new Modulo(Integer.parseInt(req.params(":cod")), Integer.parseInt(req.params(":cod_curso")), 
					req.params(":nome"), true, aulas), Integer.parseInt(req.params(":cod_curso")));	
			
			List<Modulo> modulosEncontrados = model.listarModulos(Integer.parseInt(req.params(":cod_curso")));	
			return new Gson().toJson(modulosEncontrados);		
		});
	}
	
	public void excluirModulo(){
		get("/excluirModulo/:cod/:cod_curso", (req, res) -> {
			
			model.excluirModulo(Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod")));	
			
			List<Modulo> modulosEncontrados = model.listarModulos(Integer.parseInt(req.params(":cod_curso")));	   
			return new Gson().toJson(modulosEncontrados);		
		});
	}
	
	public void alterarModulo(){
		get("/alterarModulo/:cod/:cod_curso/:nome", (req, res) -> {
			
			model.alterarModulo(Integer.parseInt(req.params(":cod")), req.params(":nome"), Integer.parseInt(req.params(":cod_curso")));	
			
			List<Modulo> modulosEncontrados = model.listarModulos(Integer.parseInt(req.params(":cod_curso")));	
			return new Gson().toJson(modulosEncontrados);		
		});
	}
	
	public void cadastrarAula(){
		get("/cadastrarAula/:cod/:cod_modulo/:nome/:video/:conteudo/:cod_curso", (req, res) -> {
			
			model.cadastrarAula(new Aula(Integer.parseInt(req.params(":cod")), Integer.parseInt(req.params(":cod_modulo")), 
					req.params(":nome"), req.params(":video"), req.params(":conteudo"), true), Integer.parseInt(req.params(":cod_curso")), 
					Integer.parseInt(req.params(":cod_modulo")));	
			
			List<Aula> aulasEncontradas = model.listarAulas(Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")));	
			return new Gson().toJson(aulasEncontradas);		
		});
	}
	
	public void excluirAula(){
		get("/excluirAula/:cod/:cod_modulo/:cod_curso", (req, res) -> {
			
			model.excluirAula(Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")), Integer.parseInt(req.params(":cod")));	
			
			List<Aula> aulasEncontradas = model.listarAulas(Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")));	
			return new Gson().toJson(aulasEncontradas);	
		});
	}
	
	public void alterarAula(){
		get("/alterarAula/:cod/:cod_modulo/:nome/:video/:conteudo/:cod_curso", (req, res) -> {
			
			model.alterarAula(Integer.parseInt(req.params(":cod")), Integer.parseInt(req.params(":cod_modulo")), 
					req.params(":nome"), req.params(":video"), req.params(":conteudo"), Integer.parseInt(req.params(":cod_curso")));	
			
			List<Aula> aulasEncontradas = model.listarAulas(Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")));	
			return new Gson().toJson(aulasEncontradas);		
		});
	}
	
	public void buscarUsuario() {
		get("/buscarUsuario/:codigo", (req, res) -> {
			
			Usuario usuarioEncontrado = model.buscarUsuario(Integer.parseInt(req.params(":codigo")));	
			return new Gson().toJson(usuarioEncontrado);		
		});
	}
	
	public void cadastrarUsuario()	{
		get("/cadastrarUsuario/:login/:senha/:email/:nome", (req, res) -> {
			
			model.cadastrarUsuario(new Usuario(model.listarUsuarios().size()+1, req.params(":login"), req.params(":senha"), req.params(":email"), 
					req.params(":nome"), "aluno", "img/users/user.png", true));	
			
			Usuario usuarioCadastrado = model.buscarUsuario(2);
			return new Gson().toJson(usuarioCadastrado);		
		});
	}
	
	public void login() {
		get("/login/:username/:password", (req, res) -> {
			Usuario usuarioEncontrado = model.loginUsuario(req.params(":username"), req.params(":password"));
			return new Gson().toJson(usuarioEncontrado);
		});
	}
	
	public void listarUsuarioCursos() {
		get("/listarUsuarioCursos/:cod_usuario", (req, res) -> {
			List<Curso> cursosInscritos = model.buscarCursosInscritos(Integer.parseInt(req.params(":cod_usuario")));
			return new Gson().toJson(cursosInscritos);
		});
	}
	
	public void buscarUsuarioCurso() {
		get("/usuarioCurso/:cod_usuario/:cod_curso", (req, res) -> {
			UsuarioCurso usuarioCurso = model.buscarUsuarioCurso(Integer.parseInt(req.params(":cod_usuario")), 
					Integer.parseInt(req.params(":cod_curso")));
			return new Gson().toJson(usuarioCurso);
		});
	}
	
	public void inscreverCurso() {
		get("/inscreverCurso/:cod_usuario/:cod_curso", (req, res) -> {
			Boolean usuarioInscrito = model.inscreverCurso(Integer.parseInt(req.params(":cod_usuario")), 
					Integer.parseInt(req.params(":cod_curso")));
			return new Gson().toJson(usuarioInscrito);
		});
	}
	
	public void alterarProgressoCurso() {
		get("/alterarProgressoCurso/:cod_usuario/:cod_curso/:cod_modulo/:cod_aula", (req, res) -> {
			Boolean progressoAlterado = model.alterarProgressoCurso(Integer.parseInt(req.params(":cod_usuario")), 
					Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")), 
					Integer.parseInt(req.params(":cod_aula")));
			return new Gson().toJson(progressoAlterado);
		});
	}
}
