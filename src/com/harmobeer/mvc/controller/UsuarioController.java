/**
 *
 */
package com.harmobeer.mvc.controller;

import java.util.List;

import com.harmobeer.interfaces.IUsuarioDAO;
import com.harmobeer.mvc.model.UsuarioModel;
import com.harmobeer.vo.Usuario;

/**
 * Classe responsavel pelo controller do objeto Usuario
 *
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 *
 */
public class UsuarioController implements IUsuarioDAO {
private UsuarioModel usuarioModel;

	/**
	 * Construtor de UsuarioController utilizando a classe UsuarioModel
	 */
	public UsuarioController(){
		usuarioModel = new UsuarioModel();
	}
	/**
	 * Inclui um usuario no banco de dados.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean incluir(Usuario user) {
		return usuarioModel.incluir(user);
	}

	/**
	 * Edita um usuario no banco de dados, podendo atualizar as seguintes
	 * informacoes: username, email, info e senha.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean editar(Usuario user) {
		return usuarioModel.editar(user);
	}

	/**
	 * Deleta um usuario do banco de dados, atualizando as avaliacoes feitas por
	 * ele para um usuario pre-cadastrado Anonimo.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean deletar(Usuario user) {
		return usuarioModel.deletar(user);
	}

	/**
	 * Metodo responsavel por buscar e retornar uma lista de usuariso que contem
	 * uma string pre-determinada.
	 *
	 * @param String
	 *            busca
	 * @return List<Usuario> com usuarios que contem busca no username
	 */
	@Override
	public List<Usuario> buscarUser(String busca) {
		return usuarioModel.buscarUser(busca);

	}

	/**
	 * Metodo responsavel por retornar um usuario cujas Strings para username e
	 * senha tenham um match no banco de dados
	 *
	 * @param Usuario usuario
	 * @return Usuario com username e senha correspondentes ou null se nao
	 *         houver correspondente
	 */
	@Override
	public Usuario logar(String username, String senha) {
		return usuarioModel.logar(username, senha);
	}

	/**
	 * Verifica se o usuario recebido possui privilegio de administrador.
	 *
	 * @param usuario
	 * @return boolean
	 */
	@Override
	public boolean verificarPrivilegio(Usuario usuario) {
		return usuarioModel.verificarPrivilegio(usuario);
	}
}
