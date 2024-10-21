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
@NamedQuery(name="EtatAvoirFinancePF.findAll", query="SELECT f FROM EtatAvoirFinancePF f")
public class EtatAvoirFinancePF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	@ManyToOne
	@JoinColumn(name="id_sousFamille")
	private SousFamilleArticlePF sousFamille;
	
	@ManyToOne
	@JoinColumn(name="id_Famille")
	private FamilleArticlePF familleArticle;
	

	
	// avoir (retour) Facture Avoire PF
	
			@Column(name="quantite_avoir")
			private BigDecimal quantiteAvoir;

			@Column(name="prix_avoire")
			private BigDecimal prixAvoir;
					
			@Column(name="total_avoir")
			private BigDecimal totalAvoir;
			
			
			
			// GRATUITE
			
			@Column(name="quantite_gratuit")
			private BigDecimal quantiteGratuit;

			@Column(name="prix_gratuit")
			private BigDecimal prixGratuit;
				
			@Column(name="total_gratuit")
			private BigDecimal totalGratuit;
			
			
			
	
	// Stock Finance
		
	@Column(name="quantite_finance")
	private BigDecimal quantitefinance;

	@Column(name="ecart")
	private BigDecimal ecart;
	
	@Column(name="prix_initial")
	private BigDecimal prixInitial;
	
	@Column(name="prix_achat")
	private BigDecimal prixAchat;
	
	
	
	

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

	public SousFamilleArticlePF getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleArticlePF sousFamille) {
		this.sousFamille = sousFamille;
	}

	public FamilleArticlePF getFamilleArticle() {
		return familleArticle;
	}

	public void setFamilleArticle(FamilleArticlePF familleArticle) {
		this.familleArticle = familleArticle;
	}

	public BigDecimal getQuantiteAvoir() {
		return quantiteAvoir;
	}

	public void setQuantiteAvoir(BigDecimal quantiteAvoir) {
		this.quantiteAvoir = quantiteAvoir;
	}

	public BigDecimal getPrixAvoir() {
		return prixAvoir;
	}

	public void setPrixAvoir(BigDecimal prixAvoir) {
		this.prixAvoir = prixAvoir;
	}

	public BigDecimal getTotalAvoir() {
		return totalAvoir;
	}

	public void setTotalAvoir(BigDecimal totalAvoir) {
		this.totalAvoir = totalAvoir;
	}

	public BigDecimal getQuantiteGratuit() {
		return quantiteGratuit;
	}

	public void setQuantiteGratuit(BigDecimal quantiteGratuit) {
		this.quantiteGratuit = quantiteGratuit;
	}

	public BigDecimal getPrixGratuit() {
		return prixGratuit;
	}

	public void setPrixGratuit(BigDecimal prixGratuit) {
		this.prixGratuit = prixGratuit;
	}

	public BigDecimal getTotalGratuit() {
		return totalGratuit;
	}

	public void setTotalGratuit(BigDecimal totalGratuit) {
		this.totalGratuit = totalGratuit;
	}

	public BigDecimal getQuantitefinance() {
		return quantitefinance;
	}

	public void setQuantitefinance(BigDecimal quantitefinance) {
		this.quantitefinance = quantitefinance;
	}

	public BigDecimal getEcart() {
		return ecart;
	}

	public void setEcart(BigDecimal ecart) {
		this.ecart = ecart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	

}