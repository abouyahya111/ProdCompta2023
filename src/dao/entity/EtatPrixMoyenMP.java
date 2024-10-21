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
@NamedQuery(name="EtatPrixMoyenMP.findAll", query="SELECT f FROM EtatPrixMoyenMP f")
public class EtatPrixMoyenMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="MP")
	private String mp;
	
	@Column(name="souscategorie")
	private String souscategorie ;
	
	@Column(name="categorie")
	private String categorie ;
	
	@Column(name="famille")
	private String famille ;
	
	@Column(name="sousfamille")
	private String sousfamille ;
	
	@Column(name="QUANTITE")
	private BigDecimal quantite ;

	@Column(name="PRIX")
	private BigDecimal prix ;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantHT ;
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA ;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC ;

	public int getId() {
		return id;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public String getSouscategorie() {
		return souscategorie;
	}

	public void setSouscategorie(String souscategorie) {
		this.souscategorie = souscategorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(BigDecimal montantHT) {
		this.montantHT = montantHT;
	}

	public BigDecimal getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(BigDecimal montantTVA) {
		this.montantTVA = montantTVA;
	}

	public BigDecimal getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(BigDecimal montantTTC) {
		this.montantTTC = montantTTC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}

	
	
	
	
}