package dao;

import java.util.LinkedList;
import java.util.List;

public class Plataforma {
    private List<Curso> cursos = new LinkedList<Curso>();
    private List<Usuario> usuarios = new LinkedList<Usuario>();

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void cadastrarCurso(Curso curso){
        cursos.add(curso);
    }

    public Curso pesquisarCursoNome(String nome){
        for(Curso curso:cursos){
            if(curso.getNome().equals(nome)) return curso;
        }
        return null;
    }

    public void cadastrarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public Usuario pesquisarUsuarioLogin(String login){
        for(Usuario usuario:usuarios){
            if(usuario.getLogin().equals(login)) return usuario;
        }
        return null;
    }
}
