package model;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import dao.*;

import java.util.LinkedList;

public class Plataforma {
	
	ObjectContainer cursos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/cursos.db4o");
	ObjectContainer usuarios = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuarios.db4o");
	ObjectContainer usuariosCursos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuariosCursos.db4o");
		
	public List<Curso> listarCursos(){
		Query query = cursos.query();
		query.constrain(Curso.class);
		List<Curso> allCursos = query.execute();
		
		return allCursos;
	}
	
	public Curso buscarCursoCod(int codigo){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo) return curso;
		}
		
		return null;
	}
	
	public void cadastrarCurso(Curso curso){
		cursos.store(curso);
		cursos.commit();
	}
	
	public void excluirCurso(int codigo){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){ 
			if(curso.getCodigo() == codigo){ 
				cursos.delete(curso);
				cursos.commit();
				break;
			}
		}
	}
	
	public void alterarCurso(int codigo, String nome, String categoria, String descricao, String duracao){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				curso.setNome(nome);
				curso.setCategoria(categoria);
				curso.setDescricao(descricao);
				curso.setDuracao(duracao);
				
				cursos.store(curso);
				cursos.commit();
				break;
			}
		}
	}
	
	public List<Modulo> listarModulos(int codigo){
		List<Curso> allCursos = listarCursos();
		List<Modulo> allModulos = new LinkedList<Modulo>();
		
		for(Curso curso:allCursos){
			 if(curso.getCodigo() == codigo){
				 allModulos = curso.getModulos();  
				 break;
			 }
		}
		
		return allModulos;
	}
	
	public void cadastrarModulo(Modulo modulo, int codigo){ 
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				curso.getModulos().add(modulo);
				
				cursos.store(curso);
				cursos.commit();
				break;
			}
		}
	}
	
	public void excluirModulo(int codigo, int cod_modulo){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod_modulo){
						curso.getModulos().remove(modulo);
						
						cursos.store(curso);
						cursos.commit();
						break;
					}
				}
				break;
			}
		}
	}
	
	public void alterarModulo(int cod, String nome, int codigo){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				for (Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod){
						modulo.setNome(nome);
						
						cursos.store(curso);
						cursos.commit();
						break;
					}
				}
				break;
			}
		}
	}
	
	public List<Aula> listarAulas(int codigo, int cod_modulo){
		List<Modulo> allModulos = listarModulos(codigo);
		List<Aula> allAulas = new LinkedList<Aula>();
		
		for(Modulo modulo:allModulos){
			if(modulo.getCod() == cod_modulo){
				allAulas = modulo.getAulas();
				break;
			}
		}
		
		return allAulas;
	}
	
	public void cadastrarAula(Aula aula, int codigo, int cod_modulo){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos) {
			if(curso.getCodigo() == codigo) {
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){ 
						modulo.getAulas().add(aula);
						
						cursos.store(curso);
						cursos.commit();
						break;
					}
				}
				break;
			}
		}
	}
	
	public void excluirAula(int codigo, int cod_modulo, int cod_aula){
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod_aula){
								modulo.getAulas().remove(aula);
								
								cursos.store(curso);
								cursos.commit();
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
		List<Curso> allCursos = listarCursos();
		
		for(Curso curso:allCursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod){
								aula.setNome(nome);
								aula.setVideo(link);
								aula.setConteudo(conteudo);
								
								cursos.store(curso);
								cursos.commit();
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
	
	public List<Usuario> listarUsuarios() {
		Query query = usuarios.query();
		query.constrain(Usuario.class);
		List<Usuario> allUsuarios = query.execute();
		
		return allUsuarios;
	}
	
	public Usuario buscarUsuario(int codigo) {
		List<Usuario> allUsuarios = listarUsuarios();
		
		for(Usuario usuario:allUsuarios){
			 if(usuario.getCodigo() == codigo) return usuario;
		}
		
		return null;
	}
	
	public int autoincrement() {
		List<Usuario> allUsuarios = listarUsuarios();
		
		int maxCod = 0;
		
		for(Usuario usuario:allUsuarios) {
			maxCod = Math.max(maxCod, usuario.getCodigo());
		}
		
		return maxCod + 1;
	}
	
	public void cadastrarUsuario(Usuario usuario) { 
		usuarios.store(usuario);
		usuarios.commit();
	}
	
	public Usuario loginUsuario(String username, String password) {
		List<Usuario> allUsuarios = listarUsuarios();
		
		for(Usuario usuario:allUsuarios) {
			if(usuario.getLogin().equals(username) && usuario.getSenha().equals(password)){
				return usuario;
			}
		}
		return null;
	}
	
	public List<UsuarioCurso> listarUsuarioCursos() {
		Query query = usuariosCursos.query();
		query.constrain(UsuarioCurso.class);
		List<UsuarioCurso> allUsuariosCursos = query.execute();
		
		return allUsuariosCursos;
	}
	
	public List<Curso> buscarCursosInscritos(int cod_usuario) {
		List<UsuarioCurso> allUsuariosCursos = listarUsuarioCursos();		
		List<Curso> allCursos = listarCursos();
		List<Curso> cursosInscritos = new LinkedList<Curso>();
		
		for(UsuarioCurso usuarioCurso:allUsuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario) {
				for(Curso curso:allCursos) {
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
		List<UsuarioCurso> allUsuariosCursos = listarUsuarioCursos();
		
		for(UsuarioCurso usuarioCurso:allUsuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				return usuarioCurso;
			}
		}
		
		return null;
	}
	
	public boolean inscreverCurso(int cod_usuario, int cod_curso) {
		List<UsuarioCurso> allUsuariosCursos = listarUsuarioCursos();
		
		for(UsuarioCurso usuarioCurso:allUsuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				return true;
			}
		}
		
		usuariosCursos.store(new UsuarioCurso(cod_usuario, cod_curso, 1, 1));
		usuariosCursos.commit();
		
		return false;
	}
	
	public boolean alterarProgressoCurso(int cod_usuario, int cod_curso, int cod_modulo, int cod_aula) {
		List<UsuarioCurso> allUsuariosCursos = listarUsuarioCursos();
		
		for(UsuarioCurso usuarioCurso:allUsuariosCursos) {
			if(usuarioCurso.getCod_usuario() == cod_usuario && usuarioCurso.getCod_curso() == cod_curso) {
				usuarioCurso.setCod_modulo(cod_modulo);
				usuarioCurso.setCod_aula(cod_aula);
				
				usuariosCursos.store(usuarioCurso);
				usuariosCursos.commit();
				return true;
			}
		}
		
		return false;
	}
}
