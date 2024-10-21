package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="listeArticleParSousFamilleFactureServiceProductionDetailAvecSousTautaux.findAll", query="SELECT c FROM listeArticleParSousFamilleFactureServiceProductionDetailAvecSousTautaux c")
public class ListeArticleParSousFamillePourEtatFactureServiceProductionAvecSousTautaux implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
 
	
	 
 
	@ManyToOne
	@JoinColumn(name = "article")
	private Articles article;
	
	@ManyToOne
	@JoinColumn(name = "sousfamille")
	private SousFamilleArticlePF sousfamille;

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

	public SousFamilleArticlePF getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(SousFamilleArticlePF sousfamille) {
		this.sousfamille = sousfamille;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 
	
	
}