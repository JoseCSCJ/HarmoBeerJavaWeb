/**
 *
 */
package com.harmobeer.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 * 
 * Classe de acesso ao banco de dados da classe Harmonizacao.
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 *
 */

public class HarmonizacaoDAO {

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String LOCAL_HOST = "jdbc:oracle:thin:@//localhost:1521/xe";
	private static final String DB_USER = "harmobeer";
	private static final String DB_PASSWORD = "harmobeer";
	private static final String ERRO = "Nao foi possivel completar sua requisicao.";

	/**
	 * Metodo que verifica se a harmonizacao de uma cerveja com um prato ja
	 * existe no banco de dados. Caso exista, retorna a ID da harmonizacao do
	 * banco de dados. Caso não exista, retorna o valor -1. Caso haja algum
	 * erro de verificacao no banco, retorna -2.
	 *
	 * @param cerveja
	 * @param prato
	 * @return int
	 */

	private int verificarHarmonizacao(Cerveja cerveja, Prato prato) {
		Connection connection = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		boolean verificarCerveja = false;
		boolean verificarPrato = false;
		int idHarmo = -1;
		try {
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

			sttm = connection.prepareStatement("select p.id_prato, c.id_cerv, h.id_harmo from harmonizacao h "
					+ "left join prato p on p.id_prato = h.id_prato " + "left join cerveja c on c.id_cerv = h.id_cerv");
			rs = sttm.executeQuery();
			while (rs.next()) {
				verificarPrato = false;
				verificarCerveja = false;
				if (rs.getInt("id_prato") == prato.getId_prato()) {
					verificarPrato = true;
				} else {
					verificarPrato = false;
				}
				if (rs.getInt("id_cerv") == cerveja.getId_cerv()) {
					verificarCerveja = true;
				} else {
					verificarCerveja = false;
				}
				if (verificarPrato == true && verificarCerveja == true) {
					idHarmo = rs.getInt("id_harmo");
					return idHarmo;
				}
			}

		} catch (ClassNotFoundException e) {
			System.out.println(ERRO);
			e.printStackTrace();
			return -2;
		} catch (SQLException Except) {
			System.out.println(ERRO);
			Except.printStackTrace();
			return -2;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (sttm != null) {
					sttm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -2;
			}
		}
		return idHarmo;
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
		Connection connection = null;
		PreparedStatement sttm = null;
		int verificarHarmo = 0;

		try {
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

			verificarHarmo = verificarHarmonizacao(cerveja, prato);
			if (verificarHarmo == -1) {
				// Harmonizacao nao existe - criar nova harmonizacao
				sttm = connection.prepareStatement("insert into harmonizacao (id_harmo, id_cerv, id_prato, media) "
						+ "values (seqharmo.nextval,?,?,0)");
				sttm.setInt(1, cerveja.getId_cerv());
				sttm.setInt(2, prato.getId_prato());

				sttm.executeUpdate();

			} else if (verificarHarmo == -2) {
				// Erro de banco
				System.out.println("Harmonizacao nao incluida por ter dado erro no banco");
				return false;
			} else {
				// Harmonizacao ja existe, nao incluir nada
				System.out.println("Harmonizacao nao incluida por ja existir no banco de dados");
				return false;

			}

		} catch (ClassNotFoundException e) {
			System.out.println(ERRO);
			e.printStackTrace();
			return false;

		} catch (SQLException Except) {
			System.out.println(ERRO);
			Except.printStackTrace();
			return false;

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (sttm != null) {
					sttm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * Metodo para calcular a media de determinada harmonizacao baseada nas
	 * avaliacoes feitas por ela.
	 * 
	 * @param harmo
	 * @return boolean
	 */
	public boolean calcularMedia(Harmonizacao harmo) {
		int naval = 0;
		int nota = 0;
		int qtdnota = 0;
		double media = 0;
		Connection connection = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

			sttm = connection.prepareStatement("select nota from avaliacao where id_harmo =?");
			sttm.setInt(1, harmo.getId_harmo());
			rs = sttm.executeQuery();
			while (rs.next()) {
				naval = rs.getInt("nota");
				nota = nota + naval;
				qtdnota = qtdnota + 1;
			}
			try {
				media = nota / qtdnota;
			} catch (ArithmeticException e) {
				e.printStackTrace();
				System.out.println("Ainda nao foram feitas avaliacoes para essa harmonizacao");
				return false;
			}

			sttm.close();
			sttm = connection.prepareStatement("Update harmonizacao set media=? where id_harmo=?");
			sttm.setDouble(1, media);
			sttm.setInt(2, harmo.getId_harmo());
			sttm.executeUpdate();
			return true;

		} catch (ClassNotFoundException e) {
			System.out.println(ERRO);
			e.printStackTrace();
			return false;
		} catch (SQLException Except) {
			System.out.println(ERRO);
			Except.printStackTrace();
			return false;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (sttm != null) {
					sttm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

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
		ArrayList<Harmonizacao> listaHarmo = new ArrayList<Harmonizacao>();
		Connection connection = null;
		PreparedStatement sttm = null;
		try {
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

			sttm = connection.prepareStatement("select * from harmonizacao where id_cerv = ? order by media desc");
			sttm.setInt(1, cerveja.getId_cerv());
			ResultSet rs = sttm.executeQuery();

			while (rs.next()) {

				int id_harmo = rs.getInt("id_harmo");
				int id_cerv = rs.getInt("id_cerv");
				int id_prato = rs.getInt("id_prato");
				Double media = rs.getDouble("media");
				Harmonizacao harmo = new Harmonizacao(id_harmo, id_cerv, id_prato, media);

				listaHarmo.add(harmo);
			}

			return listaHarmo;

		} catch (ClassNotFoundException e) {
			System.out.println(ERRO);
			e.printStackTrace();
			return null;
		} catch (SQLException Except) {
			System.out.println(ERRO);
			Except.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (sttm != null) {
					sttm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
		ArrayList<Harmonizacao> listaHarmo = new ArrayList<Harmonizacao>();
		Connection connection = null;
		PreparedStatement sttm = null;
		try {
			Class.forName(JDBC_DRIVER);

			connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

			sttm = connection.prepareStatement("select * from harmonizacao where id_prato = ? order by media desc");
			sttm.setInt(1, prato.getId_prato());
			ResultSet rs = sttm.executeQuery();

			while (rs.next()) {

				int id_harmo = rs.getInt("id_harmo");
				int id_cerv = rs.getInt("id_cerv");
				int id_prato = rs.getInt("id_prato");
				Double media = rs.getDouble("media");
				Harmonizacao harmo = new Harmonizacao(id_harmo, id_cerv, id_prato, media);

				listaHarmo.add(harmo);
			}

			return listaHarmo;

		} catch (ClassNotFoundException e) {
			System.out.println(ERRO);
			e.printStackTrace();
			return null;
		} catch (SQLException Except) {
			System.out.println(ERRO);
			Except.printStackTrace();
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (sttm != null) {
					sttm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
