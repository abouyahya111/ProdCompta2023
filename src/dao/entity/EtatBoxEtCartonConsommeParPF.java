package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="EtatBoxEtCartonConsommeParPF.findAll", query="SELECT f FROM EtatBoxEtCartonConsommeParPF f")
public class EtatBoxEtCartonConsommeParPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	
	@Column(name="quantite_realise")
	private BigDecimal quantiteRealiser;
	
	@ManyToOne
	@JoinColumn(name="id_mp_box")
	private MatierePremier mpBox;
	
	@ManyToOne
	@JoinColumn(name="id_mp_carton")
	private MatierePremier mpCarton;

	@Column(name="SOUS_FAMILLE")
	private String sousFamille;

	
	
	 
	
	@Column(name="quantite_box_consomme")
	private BigDecimal quantiteBoxConsomme;

	@Column(name="prix_box")
	private BigDecimal prixBox;
	
	@Column(name="montant_box")
	private BigDecimal montantBox;
	
	
	@Column(name="quantite_carton_consomme")
	private BigDecimal quantiteCartonConsomme;

	@Column(name="prix_carton")
	private BigDecimal prixCarton;
	
	@Column(name="montant_carton")
	private BigDecimal montantCarton;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public BigDecimal getQuantiteRealiser() {
		return quantiteRealiser;
	}

	public void setQuantiteRealiser(BigDecimal quantiteRealiser) {
		this.quantiteRealiser = quantiteRealiser;
	}

	public MatierePremier getMpBox() {
		return mpBox;
	}

	public void setMpBox(MatierePremier mpBox) {
		this.mpBox = mpBox;
	}

	public MatierePremier getMpCarton() {
		return mpCarton;
	}

	public void setMpCarton(MatierePremier mpCarton) {
		this.mpCarton = mpCarton;
	}

	public String getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public BigDecimal getQuantiteBoxConsomme() {
		return quantiteBoxConsomme;
	}

	public void setQuantiteBoxConsomme(BigDecimal quantiteBoxConsomme) {
		this.quantiteBoxConsomme = quantiteBoxConsomme;
	}

	public BigDecimal getPrixBox() {
		return prixBox;
	}

	public void setPrixBox(BigDecimal prixBox) {
		this.prixBox = prixBox;
	}

	public BigDecimal getMontantBox() {
		return montantBox;
	}

	public void setMontantBox(BigDecimal montantBox) {
		this.montantBox = montantBox;
	}

	public BigDecimal getQuantiteCartonConsomme() {
		return quantiteCartonConsomme;
	}

	public void setQuantiteCartonConsomme(BigDecimal quantiteCartonConsomme) {
		this.quantiteCartonConsomme = quantiteCartonConsomme;
	}

	public BigDecimal getPrixCarton() {
		return prixCarton;
	}

	public void setPrixCarton(BigDecimal prixCarton) {
		this.prixCarton = prixCarton;
	}

	public BigDecimal getMontantCarton() {
		return montantCarton;
	}

	public void setMontantCarton(BigDecimal montantCarton) {
		this.montantCarton = montantCarton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	

}