package dao.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="DetailArticlesClient.findAll", query="SELECT f FROM DetailArticlesClient f")
public class DetailArticlesClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	

	@ManyToOne
	@JoinColumn(name="id_articlesclient")
	private ArticlesClient articlesclient ;
	
	
	
	@ManyToOne
	@JoinColumn(name="SOUS_FAMILLE")
	private SousFamilleArticlePF sousFamille;
	

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

	public ArticlesClient getArticlesclient() {
		return articlesclient;
	}

	public void setArticlesclient(ArticlesClient articlesclient) {
		this.articlesclient = articlesclient;
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