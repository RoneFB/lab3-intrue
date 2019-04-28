package model;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import dao.*;

import java.util.LinkedList;

public class Plataforma {
	
	private List<Curso> cursos = new LinkedList<Curso>(); 
	private List<Usuario> usuarios = new LinkedList<Usuario>();		 
	private List<UsuarioCurso> usuariosCursos = new LinkedList<UsuarioCurso>();
	
	ObjectContainer bdCursos = 
			Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/cursos.db4o");
	ObjectContainer bdUsuario = 
			Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuario.db4o");
	ObjectContainer bdUsuarioCurso = 
			Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuarioCurso.db4o");
	
	
	
	
	public List<Curso> listarCursos(){
		Query query = bdCursos.query();
		query.constrain(Curso.class);
		List<Curso> allCursos = query.execute();/*Transforma a lista em DATA*/ 
		return allCursos;
	}
	
	public Curso buscarCursoCod(int codigo){
		Query query  = bdCursos.query();
		query.constrain(Curso.class);
		ObjectSet<Curso> allCursos = query.execute();
		for(Curso curso: allCursos) {
			if(curso.getCodigo() == codigo) {
				return curso;
			}
		}
		return null;
	}
	
	
	public void cadastrarCurso(Curso curso){
		//cursos.add(curso);
		Query query =  bdCursos.query();
		query.constrain(Curso.class);
		List<Curso> allCursos = query.execute();
		curso.setCodigo(allCursos.size() +1 );
		bdCursos.store(curso);
		bdCursos.commit();
		
	}
	
	public void excluirCurso(int codigo){
		Query query = bdCursos.query();
		query.constrain(Curso.class);
		List<Curso> allCursos = query.execute();
		
		
		for(Curso curso:allCursos){ 
			if(curso.getCodigo() == codigo){ 
				bdCursos.delete(curso);
				bdCursos.commit();
				for(Curso i:allCursos) {
					/*Terminal o excluir*/
					bdCursos.store(i);
					bdCursos.commit();
				}
				break;
			}
		}
	}
	
	public void alterarCurso(int codigo, String nome, String categoria, String descricao, String duracao){
		Query query = bdCursos.query();
		query.constrain(Curso.class);
		List<Curso> allCursos = query.execute();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				curso.setNome(nome);
				curso.setCategoria(categoria);
				curso.setDescricao(descricao);
				curso.setDuracao(duracao);
				bdCursos.store(curso);
				bdCursos.commit();
				break;
			}
		}
	}
	
	public void cadastrarModulo(Modulo modulo, int codigo){ 
		for(Curso curso:listarCursos()){
			if(curso.getCodigo() == codigo){
				curso.getModulos().add(modulo);
				bdCursos.store(curso.getModulos());
				bdCursos.commit();
				break;
			}
		}
	}
	 
	public List<Modulo> listarModulos(int codigo){
		for(Curso curso:listarCursos()){
	    	if(curso.getCodigo() == codigo){
	    		return curso.getModulos();
	    	}
	    	break;
		 }
		
	    return null;
	}
	
	
	
	public void excluirModulo(int codigo, int cod_modulo){
		for(Curso curso:listarCursos()){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod_modulo){
						curso.getModulos().remove(modulo);
						bdCursos.store(curso.getModulos());
						bdCursos.commit();
						break;
					}
				}
				break;
			}
		}
	}
	
	public void alterarModulo(int cod, String nome, int codigo){
		for(Curso curso:listarCursos()){
			if(curso.getCodigo() == codigo){
				for (Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod){
						modulo.setNome(nome);
						bdCursos.store(modulo);
						bdCursos.commit();
						break;
					}
				}
				break;
			}
		}
	}
	
	public void cadastrarAula(Aula aula, int codigo, int cod_modulo){
	for(Modulo modulo:listarModulos(aula.getCod())){ 
			if(modulo.getCod() == cod_modulo){ 
				modulo.getAulas().add(aula);
				bdCursos.store(modulo.getAulas());
				bdCursos.commit();
				break;
			}
		}
	}
	public List<Aula> listarAulas(int codigo, int cod_modulo){
		List<Modulo> modulosEncontrados = listarModulos(codigo);
		List<Aula> aulasEncontradas = new LinkedList<Aula>();
		
		for(Modulo modulo:modulosEncontrados){
			if(modulo.getCod() == cod_modulo){
				aulasEncontradas = modulo.getAulas();
				break;
			}
		}
		
		return aulasEncontradas;
	}
	
	
	public void excluirAula(int codigo, int cod_modulo, int cod_aula){
		for(Curso curso:listarCursos()){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod_aula){
								modulo.getAulas().remove(aula);
								bdCursos.store(modulo.getAulas());
								bdCursos.commit();
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
	
	public void alterarAula(int cod, int cod_modulo, String nome, String link, String conteudo, int codigo){
		for(Curso curso:listarCursos()){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod){
								aula.setNome(nome);
								aula.setVideo(link);
								aula.setConteudo(conteudo);
								bdCursos.store(modulo.getAulas());
								bdCursos.commit();
								break;
							}
						}
						break;
					}
				}
				break;
			}
		}
	}
	public void cadastrarUsuario(Usuario usuario) {
		Query query =  bdUsuario.query();
		query.constrain(Usuario.class);
		List<Usuario> allUsuarios = query.execute();
		usuario.setCodigo(allUsuarios.size() +1 );
		bdUsuario.store(usuario);
		bdUsuario.commit();
	}
	

	
	public Usuario buscarUsuario(int codigo) {
		Query query =  bdUsuario.query();
		query.constrain(Usuario.class);
		List<Usuario> allUsuarios = query.execute();
		
		for(Usuario usuario:allUsuarios){
			 if(usuario.getCodigo() == codigo) return usuario;
		}
		
		return null;
	}
	
	
	public Usuario loginUsuario(String username, String password) {
		Query query =  bdUsuario.query();
		query.constrain(Usuario.class);
		List<Usuario> allUsuarios = query.execute();
		
		for(Usuario usuario:allUsuarios) {
			if(usuario.getLogin().equals(username) && usuario.getSenha().equals(password)){
				return usuario;
			}
		}
		return null;
	}
	
	public List<Curso> listarUsuarioCursos(int cod_usuario) {
		List<Curso> cursosInscritos = new LinkedList<Curso>();
		
		for(UsuarioCurso usuarioCurso:usuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario) {
				for(Curso curso:cursos) {
					if(curso.getCodigo() == usuarioCurso.getCod_curso()) {
						cursosInscritos.add(curso);
						break;
					}
				}
			}
		}
		
		return cursosInscritos;
	}
	
	public UsuarioCurso buscarUsuarioCurso(int cod_usuario, int cod_curso) {
		for(UsuarioCurso usuarioCurso:usuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				return usuarioCurso;
			}
		}
		
		return null;
	}
	
	public boolean inscreverCurso(int cod_usuario, int cod_curso) {
		for(UsuarioCurso usuarioCurso:usuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				return true;
			}
		}
		
		usuariosCursos.add(new UsuarioCurso(cod_usuario, cod_curso, 1, 1));
		
		return false;
	}
	
	public boolean alterarProgressoCurso(int cod_usuario, int cod_curso, int cod_modulo, int cod_aula) {
		for(UsuarioCurso usuarioCurso:usuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				usuarioCurso.setCod_modulo(cod_modulo);
				usuarioCurso.setCod_aula(cod_aula);
				
				return true;
			}
		}
		
		return false;
	}
}
