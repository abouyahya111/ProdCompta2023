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
@NamedQuery(name="EtatStockPFComparerAvoir.findAll", query="SELECT f FROM EtatStockPFComparerAvoir f")
public class EtatStockPFComparerAvoir implements Serializable {
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
					
			@Column(name="montant_Avoir")
			private BigDecimal montantAvoir;
			
			
			// avoir (retour) Facture Avoire PF Annee Precedente 
			
					@Column(name="quantite_avoir_annee_precedente")
					private BigDecimal quantiteAvoirAnneePrecedente;

					@Column(name="prix_avoire_annee_precedente")
					private BigDecimal prixAvoirAnneePrecedente;
							
					@Column(name="montant_Avoir_annee_precedente")
					private BigDecimal montantAvoirAnneePrecedente;
			
			
			// GRATUITE
			
			@Column(name="quantite_gratuit")
			private BigDecimal quantiteGratuit;

			@Column(name="prix_gratuit")
			private BigDecimal prixGratuit;
				
			@Column(name="montant_gratuit")
			private BigDecimal montantGratuit;
			
			
			// avoir (retour) Facture Avoire PF Comparaison
			
					@Column(name="quantite_avoir_comparaison_Normal")
					private BigDecimal quantiteAvoirComparaisonNormal;

					@Column(name="prix_avoire_comparaison_Normal")
					private BigDecimal prixAvoirComparaisonNormal;
							
					@Column(name="montant_Avoir_comparaison_Normal")
					private BigDecimal montantAvoirComparaisonNormal;		
			
					// Avoir Marjan
					
					@Column(name="quantite_avoir_comparaison_Marajn")
					private BigDecimal quantiteAvoirComparaisonMarjan;

					@Column(name="prix_avoir_comparaison_Marajn")
					private BigDecimal prixAvoirComparaisonMarjan;
						
					@Column(name="montant_avoir_comparaison_Marajn")
					private BigDecimal montantAvoirComparaisonMarjan;	
			
			
			
	
	// Difference Gratuite
		
	@Column(name="quantite_difference_gratuite")
	private BigDecimal quantiteDifferenceGratuite;

	@Column(name="prix_difference_gratuite")
	private BigDecimal prixDifferenceGratuite;
	
	@Column(name="montant_difference_gratuite")
	private BigDecimal montantDifferenceGratuite;
	
	
	// Difference Marajan
	
	@Column(name="quantite_difference_marjan")
	private BigDecimal quantiteDifferenceMarjan;

	@Column(name="prix_difference_marjan")
	private BigDecimal prixDifferenceMarjan;
	
	@Column(name="montant_difference_marjan")
	private BigDecimal montantDifferenceMarjan;
	
	
	// Difference Total
	
		@Column(name="quantite_difference_total")
		private BigDecimal quantiteDifferenceTotal;

		@Column(name="prix_difference_total")
		private BigDecimal prixDifferenceTotal;
		
		@Column(name="montant_difference_total")
		private BigDecimal montantDifferenceTotal;

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

		public BigDecimal getMontantAvoir() {
			return montantAvoir;
		}

		public void setMontantAvoir(BigDecimal montantAvoir) {
			this.montantAvoir = montantAvoir;
		}

		public BigDecimal getQuantiteAvoirAnneePrecedente() {
			return quantiteAvoirAnneePrecedente;
		}

		public void setQuantiteAvoirAnneePrecedente(BigDecimal quantiteAvoirAnneePrecedente) {
			this.quantiteAvoirAnneePrecedente = quantiteAvoirAnneePrecedente;
		}

		public BigDecimal getPrixAvoirAnneePrecedente() {
			return prixAvoirAnneePrecedente;
		}

		public void setPrixAvoirAnneePrecedente(BigDecimal prixAvoirAnneePrecedente) {
			this.prixAvoirAnneePrecedente = prixAvoirAnneePrecedente;
		}

		public BigDecimal getMontantAvoirAnneePrecedente() {
			return montantAvoirAnneePrecedente;
		}

		public void setMontantAvoirAnneePrecedente(BigDecimal montantAvoirAnneePrecedente) {
			this.montantAvoirAnneePrecedente = montantAvoirAnneePrecedente;
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

		public BigDecimal getMontantGratuit() {
			return montantGratuit;
		}

		public void setMontantGratuit(BigDecimal montantGratuit) {
			this.montantGratuit = montantGratuit;
		}

		public BigDecimal getQuantiteAvoirComparaisonNormal() {
			return quantiteAvoirComparaisonNormal;
		}

		public void setQuantiteAvoirComparaisonNormal(BigDecimal quantiteAvoirComparaisonNormal) {
			this.quantiteAvoirComparaisonNormal = quantiteAvoirComparaisonNormal;
		}

		public BigDecimal getPrixAvoirComparaisonNormal() {
			return prixAvoirComparaisonNormal;
		}

		public void setPrixAvoirComparaisonNormal(BigDecimal prixAvoirComparaisonNormal) {
			this.prixAvoirComparaisonNormal = prixAvoirComparaisonNormal;
		}

		public BigDecimal getMontantAvoirComparaisonNormal() {
			return montantAvoirComparaisonNormal;
		}

		public void setMontantAvoirComparaisonNormal(BigDecimal montantAvoirComparaisonNormal) {
			this.montantAvoirComparaisonNormal = montantAvoirComparaisonNormal;
		}

		public BigDecimal getQuantiteAvoirComparaisonMarjan() {
			return quantiteAvoirComparaisonMarjan;
		}

		public void setQuantiteAvoirComparaisonMarjan(BigDecimal quantiteAvoirComparaisonMarjan) {
			this.quantiteAvoirComparaisonMarjan = quantiteAvoirComparaisonMarjan;
		}

		public BigDecimal getPrixAvoirComparaisonMarjan() {
			return prixAvoirComparaisonMarjan;
		}

		public void setPrixAvoirComparaisonMarjan(BigDecimal prixAvoirComparaisonMarjan) {
			this.prixAvoirComparaisonMarjan = prixAvoirComparaisonMarjan;
		}

		public BigDecimal getMontantAvoirComparaisonMarjan() {
			return montantAvoirComparaisonMarjan;
		}

		public void setMontantAvoirComparaisonMarjan(BigDecimal montantAvoirComparaisonMarjan) {
			this.montantAvoirComparaisonMarjan = montantAvoirComparaisonMarjan;
		}

		public BigDecimal getQuantiteDifferenceGratuite() {
			return quantiteDifferenceGratuite;
		}

		public void setQuantiteDifferenceGratuite(BigDecimal quantiteDifferenceGratuite) {
			this.quantiteDifferenceGratuite = quantiteDifferenceGratuite;
		}

		public BigDecimal getPrixDifferenceGratuite() {
			return prixDifferenceGratuite;
		}

		public void setPrixDifferenceGratuite(BigDecimal prixDifferenceGratuite) {
			this.prixDifferenceGratuite = prixDifferenceGratuite;
		}

		public BigDecimal getMontantDifferenceGratuite() {
			return montantDifferenceGratuite;
		}

		public void setMontantDifferenceGratuite(BigDecimal montantDifferenceGratuite) {
			this.montantDifferenceGratuite = montantDifferenceGratuite;
		}

		public BigDecimal getQuantiteDifferenceMarjan() {
			return quantiteDifferenceMarjan;
		}

		public void setQuantiteDifferenceMarjan(BigDecimal quantiteDifferenceMarjan) {
			this.quantiteDifferenceMarjan = quantiteDifferenceMarjan;
		}

		public BigDecimal getPrixDifferenceMarjan() {
			return prixDifferenceMarjan;
		}

		public void setPrixDifferenceMarjan(BigDecimal prixDifferenceMarjan) {
			this.prixDifferenceMarjan = prixDifferenceMarjan;
		}

		public BigDecimal getMontantDifferenceMarjan() {
			return montantDifferenceMarjan;
		}

		public void setMontantDifferenceMarjan(BigDecimal montantDifferenceMarjan) {
			this.montantDifferenceMarjan = montantDifferenceMarjan;
		}

		public BigDecimal getQuantiteDifferenceTotal() {
			return quantiteDifferenceTotal;
		}

		public void setQuantiteDifferenceTotal(BigDecimal quantiteDifferenceTotal) {
			this.quantiteDifferenceTotal = quantiteDifferenceTotal;
		}

		public BigDecimal getPrixDifferenceTotal() {
			return prixDifferenceTotal;
		}

		public void setPrixDifferenceTotal(BigDecimal prixDifferenceTotal) {
			this.prixDifferenceTotal = prixDifferenceTotal;
		}

		public BigDecimal getMontantDifferenceTotal() {
			return montantDifferenceTotal;
		}

		public void setMontantDifferenceTotal(BigDecimal montantDifferenceTotal) {
			this.montantDifferenceTotal = montantDifferenceTotal;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	
		
		
		
	
}