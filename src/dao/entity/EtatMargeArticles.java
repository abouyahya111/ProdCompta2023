package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@NamedQuery(name="EtatMargeArticles.findAll", query="SELECT f FROM EtatMargeArticles f")
public class EtatMargeArticles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier matierePremier;
	
	

	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles article;
	
	@ManyToOne
	@JoinColumn(name="id_sousFamille")
	private SousFamilleArticlePF sousFamille;
	
	@ManyToOne
	@JoinColumn(name="id_Famille")
	private FamilleArticlePF familleArticle;
	
	// Stock Initial
	
	
	
	@Column(name="montant_initial")
	private BigDecimal montantInitial;
	
	// Achat
	

		
	@Column(name="montant_achat")
	private BigDecimal montantAchat;
	
	// Production
	
	
			
		@Column(name="montant_production")
		private BigDecimal montantProduction;
		
		
		// RRR2020
		
		
		
		@Column(name="rrr_2020")
		private BigDecimal montantRRR2020;
		
		
		// Stock Finale
		

		
		@Column(name="montant_Finale")
		private BigDecimal montantFinale;
		
		
		// Achat Revendu
		@Column(name="achat_revendu")
		private BigDecimal achatRevendu;
		
		// Chiffre d'Affaire
		
		
		
		@Column(name="montant_vente")
		private BigDecimal montantVente;
		
		
		
		
		
		
		// Marge par Article
						
		@Column(name="marge_article")
		private BigDecimal margeArticle;
		
	
		// Marge par Article
		@Column(name="la_marge_pourcentage")
		private BigDecimal margePourcentage;
		
		
		// Total Initla
		
		@Column(name="total_initial")
		private BigDecimal totalInitial;
		
		// Total Achat
		

			
		@Column(name="total_achat")
		private BigDecimal totalAchat;
		
		// Total Production
		
		
				
			@Column(name="total_production")
			private BigDecimal totalProduction;
			
			
			// Total RRR2020
			
			
			
			@Column(name="total_rrr_2020")
			private BigDecimal totalRRR2020;
			
		
			// Total RRR Credit
			
			
			
						@Column(name="total_rrr_credit")
						private BigDecimal totalRRRCredit;
						
						//Total  Stock Finale
						

						
						@Column(name="total_Finale")
						private BigDecimal totalFinale;
		
						// Total Achat Revendu
						@Column(name="total_achat_revendu")
						private BigDecimal totalAchatRevendu;
						
						// total Chiffre d'Affaire
						
						
						
						@Column(name="total_vente")
						private BigDecimal totalVente;
						
						
						// total Marge
						
						@Column(name="total_marge")
						private BigDecimal totalMarge;
						
// total Marge pourcentage
						
						@Column(name="total_marge_pourcentage")
						private BigDecimal totalMargePourcentage;
						
						
						

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


		public BigDecimal getMontantInitial() {
			return montantInitial;
		}


		public void setMontantInitial(BigDecimal montantInitial) {
			this.montantInitial = montantInitial;
		}


		public BigDecimal getMontantAchat() {
			return montantAchat;
		}


		public void setMontantAchat(BigDecimal montantAchat) {
			this.montantAchat = montantAchat;
		}


		public BigDecimal getMontantProduction() {
			return montantProduction;
		}


		public void setMontantProduction(BigDecimal montantProduction) {
			this.montantProduction = montantProduction;
		}


		public BigDecimal getMontantRRR2020() {
			return montantRRR2020;
		}


		public void setMontantRRR2020(BigDecimal montantRRR2020) {
			this.montantRRR2020 = montantRRR2020;
		}


		public BigDecimal getMontantFinale() {
			return montantFinale;
		}


		public void setMontantFinale(BigDecimal montantFinale) {
			this.montantFinale = montantFinale;
		}


		public BigDecimal getAchatRevendu() {
			return achatRevendu;
		}


		public void setAchatRevendu(BigDecimal achatRevendu) {
			this.achatRevendu = achatRevendu;
		}


		public BigDecimal getMontantVente() {
			return montantVente;
		}


		public void setMontantVente(BigDecimal montantVente) {
			this.montantVente = montantVente;
		}


	


		public BigDecimal getMargeArticle() {
			return margeArticle;
		}


		public void setMargeArticle(BigDecimal margeArticle) {
			this.margeArticle = margeArticle;
		}


		public BigDecimal getMargePourcentage() {
			return margePourcentage;
		}


		public void setMargePourcentage(BigDecimal margePourcentage) {
			this.margePourcentage = margePourcentage;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}


		public BigDecimal getTotalInitial() {
			return totalInitial;
		}


		public void setTotalInitial(BigDecimal totalInitial) {
			this.totalInitial = totalInitial;
		}


		public BigDecimal getTotalAchat() {
			return totalAchat;
		}


		public void setTotalAchat(BigDecimal totalAchat) {
			this.totalAchat = totalAchat;
		}


		public BigDecimal getTotalProduction() {
			return totalProduction;
		}


		public void setTotalProduction(BigDecimal totalProduction) {
			this.totalProduction = totalProduction;
		}


		public BigDecimal getTotalRRR2020() {
			return totalRRR2020;
		}


		public void setTotalRRR2020(BigDecimal totalRRR2020) {
			this.totalRRR2020 = totalRRR2020;
		}


		public BigDecimal getTotalRRRCredit() {
			return totalRRRCredit;
		}


		public void setTotalRRRCredit(BigDecimal totalRRRCredit) {
			this.totalRRRCredit = totalRRRCredit;
		}


		public BigDecimal getTotalFinale() {
			return totalFinale;
		}


		public void setTotalFinale(BigDecimal totalFinale) {
			this.totalFinale = totalFinale;
		}


		public BigDecimal getTotalAchatRevendu() {
			return totalAchatRevendu;
		}


		public void setTotalAchatRevendu(BigDecimal totalAchatRevendu) {
			this.totalAchatRevendu = totalAchatRevendu;
		}


		public BigDecimal getTotalVente() {
			return totalVente;
		}


		public void setTotalVente(BigDecimal totalVente) {
			this.totalVente = totalVente;
		}


		public BigDecimal getTotalMarge() {
			return getTotalVente().subtract(getTotalAchatRevendu());
		}


		public void setTotalMarge(BigDecimal totalMarge) {
			this.totalMarge = totalMarge;
		}


		public BigDecimal getTotalMargePourcentage() {
			return getTotalMarge().divide(getTotalVente(),6, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		}


		public void setTotalMargePourcentage(BigDecimal totalMargePourcentage) {
			this.totalMargePourcentage = totalMargePourcentage;
		}


		public MatierePremier getMatierePremier() {
			return matierePremier;
		}


		public void setMatierePremier(MatierePremier matierePremier) {
			this.matierePremier = matierePremier;
		}
	

	
	
	
	

	

}