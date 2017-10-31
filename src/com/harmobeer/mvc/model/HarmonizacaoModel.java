/**
 *
 */
package com.harmobeer.mvc.model;

import java.util.ArrayList;

import com.harmobeer.db.dao.HarmonizacaoDAO;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 *
 * Classe responsavel pelo model do objeto Harmonizacao
 *
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 *
 */
public class HarmonizacaoModel {
	private HarmonizacaoDAO harmonizacaoDAO;

	/**
	 * Construtor da classe HarmonizacaoModel utilizando a classe
	 * HarmonizacaoDAO
	 */
	public HarmonizacaoModel() {
		harmonizacaoDAO = new HarmonizacaoDAO();
	}

	/**
	 * Metodo que inclui uma harmonizacao no banco de dados caso ela ja nao
	 * exista.
	 *
	 * @param cerveja
	 * @param prato
	 * @return boolean
	 */
	public boolean incluirHarmonizacao(Cerveja cerveja, Prato prato) {
		return harmonizacaoDAO.incluirHarmonizacao(cerveja, prato);
	}

	/**
	 * Metodo para calcular a media de determinada harmonizacao baseada nas
	 * avaliacoes feitas por ela.
	 * 
	 * @param harmo
	 * @return boolean
	 */
	public boolean calcularMedia(Harmonizacao harmo) {
		return harmonizacaoDAO.calcularMedia(harmo);
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes
	 * para determinada cerveja.
	 * 
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes
	 *         pela media
	 */
	public ArrayList<Harmonizacao> gerarRanking(Cerveja cerveja) {
		return harmonizacaoDAO.gerarRanking(cerveja);
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes
	 * para determinado prato.
	 * 
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes
	 *         pela media
	 */
	public ArrayList<Harmonizacao> gerarRanking(Prato prato) {
		return harmonizacaoDAO.gerarRanking(prato);
	}
}
