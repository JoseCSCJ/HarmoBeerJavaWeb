/**
 * 
 */
package com.harmobeer.vo;

/** 
 * Classe que modela o objeto Avaliacao
 * 
 * @author Jose Carlos Soares da Cruz Junior / Luan Henrique Cunha Alves
 */
public class Avaliacao {
	
	private int id_aval;
	private int id_harmo;
	private int id_user;
	private int nota;
	private String comentario;
	
	
	/**
	 * @param id_aval
	 * @param id_harmo
	 * @param id_user
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao(int id_aval, int id_harmo, int id_user, int nota, String comentario) {
		this.id_aval = id_aval;
		this.id_harmo = id_harmo;
		this.id_user = id_user;
		this.nota = nota;
		this.comentario = comentario;
	}
	
	
	/**
	 * @param id_harmo
	 * @param id_user
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao(int id_harmo, int id_user, int nota, String comentario) {
		this.id_harmo = id_harmo;
		this.id_user = id_user;
		this.nota = nota;
		this.comentario = comentario;
	}


	/**
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao(int nota, String comentario) {
		this.nota = nota;
		this.comentario = comentario;
	}


	/**
	 * @return the id_aval
	 */
	public int getId_aval() {
		return id_aval;
	}
	/**
	 * @param id_aval the id_aval to set
	 */
	public void setId_aval(int id_aval) {
		this.id_aval = id_aval;
	}
	/**
	 * @return the id_harmo
	 */
	public int getId_harmo() {
		return id_harmo;
	}
	/**
	 * @param id_harmo the id_harmo to set
	 */
	public void setId_harmo(int id_harmo) {
		this.id_harmo = id_harmo;
	}
	/**
	 * @return the id_user
	 */
	public int getId_user() {
		return id_user;
	}
	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	/**
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	

}
