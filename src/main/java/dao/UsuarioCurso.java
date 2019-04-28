package dao;

public class UsuarioCurso {
	int cod_usuario;
	int cod_curso;
	int cod_modulo;
	int cod_aula;
	
	public UsuarioCurso(int cod_usuario, int cod_curso, int cod_modulo, int cod_aula) {
		this.cod_usuario = cod_usuario;
		this.cod_curso = cod_curso;
		this.cod_modulo = cod_modulo;
		this.cod_aula = cod_aula;
	}
	
	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public int getCod_curso() {
		return cod_curso;
	}

	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}

	public int getCod_modulo() {
		return cod_modulo;
	}

	public void setCod_modulo(int cod_modulo) {
		this.cod_modulo = cod_modulo;
	}

	public int getCod_aula() {
		return cod_aula;
	}

	public void setCod_aula(int cod_aula) {
		this.cod_aula = cod_aula;
	}
}
