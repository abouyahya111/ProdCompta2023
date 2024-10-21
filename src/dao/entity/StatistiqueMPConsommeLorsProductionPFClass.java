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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="StatistiqueMPConsommeLorsProductionPFClass.findAll", query="SELECT f FROM StatistiqueMPConsommeLorsProductionPFClass f")
public class StatistiqueMPConsommeLorsProductionPFClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	


	
	@Column(name="ARTICLE")
	private Articles articles ;
	
	@Column(name="MATIEREPREMIERE")
	private MatierePremier matierePremier ;
	
	@Column(name="SOUS_FAMILLE")
	private SousFamilleArticlePF sousFamille ;
	
	@Column(name="QuantiteConsomme")
	private BigDecimal quantiteConsomme ;

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

 

	public BigDecimal getQuantiteConsomme() {
		return quantiteConsomme;
	}

	public void setQuantiteConsomme(BigDecimal quantiteConsomme) {
		this.quantiteConsomme = quantiteConsomme;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SousFamilleArticlePF getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleArticlePF sousFamille) {
		this.sousFamille = sousFamille;
	}
	
	 
	

	

}