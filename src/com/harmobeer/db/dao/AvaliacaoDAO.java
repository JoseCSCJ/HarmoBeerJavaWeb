/**
 *
 */
package com.harmobeer.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.harmobeer.vo.Avaliacao;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Usuario;

/**
 * Classe responsavel pelo acesso ao banco de dados para Avaliacao
 *
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 *
 */
public class AvaliacaoDAO
{
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String LOCAL_HOST  = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String DB_USER     = "harmobeer";
    private static final String DB_PASSWORD = "harmobeer";
    private static final String ERRO        = "Nao foi possivel completar sua requisicao.";

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
        Connection connection = null;
        PreparedStatement sttm = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

            sttm = connection.prepareStatement(
                            "insert into avaliacao(id_aval,id_user, id_harmo, nota, comentario) values (seqaval.nextval,?,?,?,?)");
            sttm.setInt(1, user.getId_user());
            sttm.setInt(2, harmo.getId_harmo());
            sttm.setInt(3, aval.getNota());
            sttm.setString(4, aval.getComentario());

            sttm.executeUpdate();

            return true;

        }
        catch (ClassNotFoundException e)
        {
            System.out.println(ERRO);
            e.printStackTrace();
            return false;
        }
        catch (SQLException Except)
        {
            System.out.println(ERRO);
            Except.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
                if (sttm != null)
                {
                    sttm.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo para editar uma avaliacao nos itens de nota e comentario.
     * 
     * @param aval
     * @return boolean
     */
    public boolean editarAvaliacao (Avaliacao aval) {
        Connection connection = null;
        PreparedStatement sttm = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);

            sttm = connection.prepareStatement(
                            "UPDATE avaliacao set nota=?, comentario=? where id_aval=?");
            sttm.setInt(1, aval.getNota());
            sttm.setString(2, aval.getComentario());
            sttm.setInt(3, aval.getId_aval());

            sttm.executeUpdate();

            return true;

        }
        catch (ClassNotFoundException e)
        {
            System.out.println(ERRO);
            e.printStackTrace();
            return false;
        }
        catch (SQLException Except)
        {
            System.out.println(ERRO);
            Except.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
                if (sttm != null)
                {
                    sttm.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /** 
     * Metodo para deletar uma avaliacao. 
     * @param aval
     * @return boolean
     */
    public boolean deletarAvaliacao (Avaliacao aval) {
        Connection connection = null;
        PreparedStatement sttm = null;
        try
        {
            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(LOCAL_HOST, DB_USER, DB_PASSWORD);
            sttm = connection.prepareStatement("DELETE from avaliacao where id_aval = ?");
            sttm.setInt(1, aval.getId_aval());
            sttm.executeUpdate();
            return true;

        }
        catch (ClassNotFoundException e)
        {
            System.out.println(ERRO);
            e.printStackTrace();
            return false;
        }
        catch (SQLException Except)
        {
            System.out.println(ERRO);
            Except.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
                if (sttm != null)
                {
                    sttm.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }



}
