package model;

import java.util.List;
import dao.*;
import java.util.LinkedList;

public class Plataforma {
	
	private List<Curso> cursos = new LinkedList<Curso>();
	private List<Usuario> usuarios = new LinkedList<Usuario>();		
	
	
	public List<Curso> listarCursos(){
		return cursos;
	}
	
	public Curso buscarCursoCod(int codigo){
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo) return curso;
		}
		
		return null;
	}
	
	public void cadastrarCurso(Curso curso){
		cursos.add(curso);
	}
	
	public void excluirCurso(int codigo){
		for(Curso curso:cursos){ 
			if(curso.getCodigo() == codigo){ 
				cursos.remove(curso);
				break;
			}
		}
	}
	
	public void alterarCurso(int codigo, String nome, String categoria, String descricao, String duracao){
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				curso.setNome(nome);
				curso.setCategoria(categoria);
				curso.setDescricao(descricao);
				curso.setDuracao(duracao);
				
				break;
			}
		}
	}
	
	public List<Modulo> listarModulos(int codigo){
		List<Modulo> modulosEncontrados = new LinkedList<Modulo>();
		
		for(Curso curso:cursos){
			 if(curso.getCodigo() == codigo){
				 modulosEncontrados = curso.getModulos();  
				 break;
			 }
		}
		
		return modulosEncontrados;
	}
	
	public void cadastrarModulo(Modulo modulo, int codigo){ 
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				curso.getModulos().add(modulo);
				break;
			}
		}
	}
	
	public void excluirModulo(int codigo, int cod_modulo){
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod_modulo){
						curso.getModulos().remove(modulo);
						break;
					}
				}
				break;
			}
		}
	}
	
	public void alterarModulo(int cod, String nome, int codigo){
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				for (Modulo modulo:curso.getModulos()){
					if(modulo.getCod() == cod){
						modulo.setNome(nome);
						break;
					}
				}
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
	
	public void cadastrarAula(Aula aula, int codigo, int cod_modulo){
		 List<Modulo> modulosEncontrados = listarModulos(codigo);
		
		for(Modulo modulo:modulosEncontrados){ 
			if(modulo.getCod() == cod_modulo){ 
				modulo.getAulas().add(aula);
				break;
			}
		}
	}
	
	public void excluirAula(int codigo, int cod_modulo, int cod_aula){
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod_aula){
								modulo.getAulas().remove(aula);
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
		for(Curso curso:cursos){
			if(curso.getCodigo() == codigo){
				for(Modulo modulo:curso.getModulos()){ 
					if(modulo.getCod() == cod_modulo){
						for(Aula aula:modulo.getAulas()){
							if(aula.getCod() == cod){
								aula.setNome(nome);
								aula.setVideo(link);
								aula.setConteudo(conteudo);
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
		usuarios.add(usuario);
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuariosEncontrados = new LinkedList<Usuario>();
		
		for(Usuario usuario:usuarios){
			 if(usuario.getNome() != null) usuariosEncontrados.add(usuario);
		}
		
		return usuariosEncontrados;
	}
	
	public Usuario loginUsuario(String username, String password) {
		for(Usuario usuario: usuarios) {
			if(usuario.getLogin().equals(username) && usuario.getSenha().equals(password)){
				return usuario;
			}
		}
		return null;
	}
	
}
