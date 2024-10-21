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
@NamedQuery(name="EtatPrixMoyen.findAll", query="SELECT f FROM EtatPrixMoyen f")
public class EtatPrixMoyen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	
	@Column(name="CODE_ARTICLE")
	private String codeArticle;

	@Column(name="ARTICLE")
	private String article;
	
	@Column(name="FAMILLE")
	private String famille ;
	
	@Column(name="SOUS_FAMILLE")
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

	public void setId(int id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String Article) {
		article = Article;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
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

	public String getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}


	
	

}