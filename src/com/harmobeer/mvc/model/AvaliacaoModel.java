/**
 * 
 */
package com.harmobeer.mvc.model;

import com.harmobeer.db.dao.AvaliacaoDAO;
import com.harmobeer.vo.Avaliacao;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Usuario;

/**
 * Classe responsavel pelo modelo para o objeto Avaliacao
 *
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 *
 */
public class AvaliacaoModel {
	
	private AvaliacaoDAO avaliacaoDAO;
	
	public AvaliacaoModel(){
		avaliacaoDAO=new AvaliacaoDAO();
	}
    /**
     * 
     * Metodo para incluir uma avaliacao no banco de dados, de um determinado usuario em uma determinada harmonizacao.
     * @param aval
     * @param user
     * @param harmo
     * @return boolean
     */
    public boolean incluirAvaliacao(Avaliacao aval, Usuario user, Harmonizacao harmo)
    {
        return avaliacaoDAO.incluirAvaliacao(aval, user, harmo);
    }

    /**
     * Metodo para editar uma avaliacao nos itens de nota e comentario.
     * 
     * @param aval
     * @return boolean
     */
    public boolean editarAvaliacao (Avaliacao aval) {
        return avaliacaoDAO.editarAvaliacao(aval);
    }

    /** 
     * Metodo para deletar uma avaliacao. 
     * @param aval
     * @return boolean
     */
    public boolean deletarAvaliacao (Avaliacao aval) {
        return avaliacaoDAO.deletarAvaliacao(aval);
    }



}
