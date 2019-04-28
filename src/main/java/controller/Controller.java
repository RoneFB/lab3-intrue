package controller;

import static spark.Spark.get;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import model.Plataforma;
import dao.*;

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
					req.params(":duracao"), req.params(":descricao"), true, req.params(":categoria")));	
			
			List<Curso> cursosEncontrados = model.listarCursos();	
			return new Gson().toJson(cursosEncontrados);		
		});
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
			
			model.cadastrarUsuario(new Usuario(2, req.params(":login"), req.params(":senha"), req.params(":email"), 
					req.params(":nome"), "admin", "img/users/user.png", true));	
			
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
			List<Curso> cursosInscritos = model.listarUsuarioCursos(Integer.parseInt(req.params(":cod_usuario")));
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
		get("/inscreverCurso/:cod_usuario/:cod_curso/:cod_modulo/:cod_aula", (req, res) -> {
			Boolean progressoAlterado = model.alterarProgressoCurso(Integer.parseInt(req.params(":cod_usuario")), 
					Integer.parseInt(req.params(":cod_curso")), Integer.parseInt(req.params(":cod_modulo")), 
					Integer.parseInt(req.params(":cod_aula")));
			return new Gson().toJson(progressoAlterado);
		});
	}
}
