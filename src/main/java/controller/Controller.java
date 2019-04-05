package controller;

import static spark.Spark.get;

import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import model.Model;
import dao.*;

public class Controller {
	 
	private Model model;	
	
	public Controller(Model model){
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
			
			List<Aula> aulas = new LinkedList<Aula>();
			
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
	
	public void cadastrarUsuario()
	{
		get("/cadastrarUsuario/:login/:senha/:email/:nome", (req, res) -> {
			
			model.cadastrarUsuario(new Usuario(1, req.params(":login"),
					req.params(":senha"), req.params(":email"), req.params(":nome"), "admin", true));	
			
			List<Usuario> usuariosEncontrados = model.listarUsuarios();
			return new Gson().toJson(usuariosEncontrados);		
		});
	}
	
	public void login() {
		get("/login/:username/:password", (req, res) -> {
			Usuario usuarioEncontrado = model.loginUsuario(req.params(":username"), req.params(":password"));
			return new Gson().toJson(usuarioEncontrado);
		});
	}
	
	
	/*
	public void buscarCarroPlaca(){
		get("/carro/:placa", (req, res) -> {
		
			
			Carro carrosEncontrado = model.buscarPlaca(req.params(":placa"));	
			return new Gson().toJson(carrosEncontrado);
			
		});
	}
	
	public void buscarCarroModelo(){
		get("/carro/modelo/:modelo", (req, res) -> { 
		
			
			List<Carro> carrosEncontrado = model.buscarModelo(req.params(":modelo"));	
			return new Gson().toJson(carrosEncontrado);
			
		});
	}
	
	public void addCarro(){
		get("/cadastrar/:modelo/:placa/:marca/:cor", (req, res) -> {
			
			//Especificacao espec = new Especificacao(req.params(":modelo"), req.params(":marca"), req.params(":cor"));
		
			//Carro carro = new Carro(req.params(":placa"), espec); 			
			
			model.addCarro(new Carro(req.params(":placa"), new Especificacao(req.params(":modelo"), req.params(":marca"), req.params(":cor"))));	
			
			Carro carrosEncontrado = model.buscarPlaca(req.params(":placa"));	
			
			return new Gson().toJson(carrosEncontrado); 
		});
	}
	*/
}
