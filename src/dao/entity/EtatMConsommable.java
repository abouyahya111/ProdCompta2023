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
public class EtatMConsommable implements Serializable {
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
	

	
	//  Facture Achat PF
	
			@Column(name="quantite_achat")
			private BigDecimal quantiteAchat;

			@Column(name="prix_achat")
			private BigDecimal prixAchat;
					
			@Column(name="MONTANT_HT")
			private BigDecimal montantHT;
			
			
			@Column(name="MONTANT_TVA")
			private BigDecimal montantTVA;
			
			@Column(name="MONTANT_TTC")
			private BigDecimal montantTTC;
			
	// transfer PF Type Reste
			
			@Column(name="quantite_reste")
			private BigDecimal quantiteReste;	
			
	// Quantite Consomme		= achat - reste
			
			@Column(name="quantite_consomme")
			private BigDecimal quantiteConsomme;
			
			

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

			public BigDecimal getQuantiteAchat() {
				return quantiteAchat;
			}

			public void setQuantiteAchat(BigDecimal quantiteAchat) {
				this.quantiteAchat = quantiteAchat;
			}

			public BigDecimal getPrixAchat() {
				return prixAchat;
			}

			public void setPrixAchat(BigDecimal prixAchat) {
				this.prixAchat = prixAchat;
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

			public BigDecimal getQuantiteReste() {
				return quantiteReste;
			}

			public void setQuantiteReste(BigDecimal quantiteReste) {
				this.quantiteReste = quantiteReste;
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

			
			
			
			
			
			

}