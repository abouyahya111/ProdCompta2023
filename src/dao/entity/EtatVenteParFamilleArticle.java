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

public class EtatVenteParFamilleArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="article")
	private Articles article;
	
	@Column(name="sousfamileArticle")
	private SousFamilleArticlePF sousFamilleArticlePF;
	
	@Column(name="famileArticle")
	private FamilleArticlePF familleArticlePF;
	
	@Column(name="TotalVente")
	private BigDecimal totalVente ;

	
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


	


	public SousFamilleArticlePF getSousFamilleArticlePF() {
		return sousFamilleArticlePF;
	}


	public void setSousFamilleArticlePF(SousFamilleArticlePF sousFamilleArticlePF) {
		this.sousFamilleArticlePF = sousFamilleArticlePF;
	}


	public BigDecimal getTotalVente() {
		return totalVente;
	}


	public void setTotalVente(BigDecimal totalVente) {
		this.totalVente = totalVente;
	}


	public BigDecimal getPrixMoyen() {
		return prixMoyen;
	}


	public void setPrixMoyen(BigDecimal prixMoyen) {
		this.prixMoyen = prixMoyen;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public BigDecimal getMontant() {
		return montant;
	}


	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}


	public Articles getArticle() {
		return article;
	}


	public void setArticle(Articles article) {
		this.article = article;
	}


	public FamilleArticlePF getFamilleArticlePF() {
		return familleArticlePF;
	}


	public void setFamilleArticlePF(FamilleArticlePF familleArticlePF) {
		this.familleArticlePF = familleArticlePF;
	}
	
	

}