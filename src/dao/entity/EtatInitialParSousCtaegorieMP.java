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

public class EtatInitialParSousCtaegorieMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="matierePremiere")
	private  MatierePremier mp;
	
	@Column(name="souscategorie")
	private CategorieMp sousCategorie;
	
	@Column(name="categorie")
	private SubCategorieMp categorie;
	
	@Column(name="sousfamileArticle")
	private String sousFamilleArticlePF;
	
	@Column(name="famileArticle")
	private String familleArticlePF;
	
	@Column(name="TotalInitial")
	private BigDecimal totalInitial ;

	
	@Column(name="PRIX_MOYEN")
	private BigDecimal prixMoyen ;

	@Column(name="Montant")
	private BigDecimal montant ;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public MatierePremier getMp() {
		return mp;
	}


	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}


	public CategorieMp getSousCategorie() {
		return sousCategorie;
	}


	public void setSousCategorie(CategorieMp sousCategorie) {
		this.sousCategorie = sousCategorie;
	}


	public SubCategorieMp getCategorie() {
		return categorie;
	}


	public void setCategorie(SubCategorieMp categorie) {
		this.categorie = categorie;
	}


	public BigDecimal getTotalInitial() {
		return totalInitial;
	}


	public void setTotalInitial(BigDecimal totalInitial) {
		this.totalInitial = totalInitial;
	}


	public BigDecimal getPrixMoyen() {
		return prixMoyen;
	}


	public void setPrixMoyen(BigDecimal prixMoyen) {
		this.prixMoyen = prixMoyen;
	}


	public BigDecimal getMontant() {
		return montant;
	}


	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getSousFamilleArticlePF() {
		return sousFamilleArticlePF;
	}


	public void setSousFamilleArticlePF(String sousFamilleArticlePF) {
		this.sousFamilleArticlePF = sousFamilleArticlePF;
	}


	public String getFamilleArticlePF() {
		return familleArticlePF;
	}


	public void setFamilleArticlePF(String familleArticlePF) {
		this.familleArticlePF = familleArticlePF;
	}


	
	

}