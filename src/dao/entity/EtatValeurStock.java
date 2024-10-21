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
@NamedQuery(name="EtatValeurStock.findAll", query="SELECT f FROM EtatValeurStock f")
public class EtatValeurStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="codearticle")
	private String codearticle;
	
	@Column(name="article")
	private String article;
	
	@Column(name="famille")
	private String famille;
	
	@Column(name="sous_famille")
	private String sousfamille;
	
	// Stock Initial
	
	@Column(name="quantite_initial")
	private BigDecimal quantiteInitial;

	@Column(name="prix_initial")
	private BigDecimal prixInitial;
	
	@Column(name="montant_initial")
	private BigDecimal montantInitial;
	
	// Achat
	
	@Column(name="quantite_achat")
	private BigDecimal quantiteAchat;

	@Column(name="prix_achat")
	private BigDecimal prixAchat;
		
	@Column(name="montant_achat")
	private BigDecimal montantAchat;
	
	// Production Entrer
		
	@Column(name="quantite_production_entrer")
	private BigDecimal quantiteProductionEntrer;

	@Column(name="prix_production_entrer")
	private BigDecimal prixProductionEntrer;
			
	@Column(name="montant_production_entrer")
	private BigDecimal montantProductionEntrer;
	
	// Production Sortie
	
	@Column(name="quantite_production_sortie")
	private BigDecimal quantiteProductionSortie;

	@Column(name="prix_production_sortie")
	private BigDecimal prixProductionSortie;
			
	@Column(name="montant_production_sortie")
	private BigDecimal montantProductionSortie;
	
		// Vente
		
		@Column(name="quantite_vente")
		private BigDecimal quantiteVente;

		@Column(name="prix_vente")
		private BigDecimal prixVente;
						
		@Column(name="montant_vente")
		private BigDecimal montantVente;
				
		
		
		
		// Gratuité
		
		@Column(name="quantite_gratuite")
		private BigDecimal quantiteGratuite;

		@Column(name="prix_gratuite")
		private BigDecimal prixGratuite;
				
		@Column(name="montant_gratuite")
		private BigDecimal montantGratuite;

	
	//  Avoire 
	
		@Column(name="quantite_avoir")
		private BigDecimal quantiteAvoir;

		@Column(name="prix_avoire")
		private BigDecimal prixAvoir;
				
		@Column(name="montant_Avoir")
		private BigDecimal montantAvoir;
		
		
	
	// Stock Finale
	
		@Column(name="quantite_finale")
		private BigDecimal quantiteFinale;

		@Column(name="prix_Finale")
		private BigDecimal prixFinale;
		
		@Column(name="montant_Finale")
		private BigDecimal montantFinale;
		
		
		
		@ManyToOne
		@JoinColumn(name="ID_MAGASIN")
		private Magasin magasin;



		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public String getArticle() {
			return article;
		}



		public void setArticle(String article) {
			this.article = article;
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



		public BigDecimal getQuantiteInitial() {
			return quantiteInitial;
		}



		public void setQuantiteInitial(BigDecimal quantiteInitial) {
			this.quantiteInitial = quantiteInitial;
		}



		public BigDecimal getPrixInitial() {
			return prixInitial;
		}



		public void setPrixInitial(BigDecimal prixInitial) {
			this.prixInitial = prixInitial;
		}



		public BigDecimal getMontantInitial() {
			return montantInitial;
		}



		public void setMontantInitial(BigDecimal montantInitial) {
			this.montantInitial = montantInitial;
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



		public BigDecimal getMontantAchat() {
			return montantAchat;
		}



		public void setMontantAchat(BigDecimal montantAchat) {
			this.montantAchat = montantAchat;
		}



		public BigDecimal getQuantiteProductionEntrer() {
			return quantiteProductionEntrer;
		}



		public void setQuantiteProductionEntrer(BigDecimal quantiteProductionEntrer) {
			this.quantiteProductionEntrer = quantiteProductionEntrer;
		}



		public BigDecimal getPrixProductionEntrer() {
			return prixProductionEntrer;
		}



		public void setPrixProductionEntrer(BigDecimal prixProductionEntrer) {
			this.prixProductionEntrer = prixProductionEntrer;
		}



		public BigDecimal getMontantProductionEntrer() {
			return montantProductionEntrer;
		}



		public void setMontantProductionEntrer(BigDecimal montantProductionEntrer) {
			this.montantProductionEntrer = montantProductionEntrer;
		}



		public BigDecimal getQuantiteProductionSortie() {
			return quantiteProductionSortie;
		}



		public void setQuantiteProductionSortie(BigDecimal quantiteProductionSortie) {
			this.quantiteProductionSortie = quantiteProductionSortie;
		}



		public BigDecimal getPrixProductionSortie() {
			return prixProductionSortie;
		}



		public void setPrixProductionSortie(BigDecimal prixProductionSortie) {
			this.prixProductionSortie = prixProductionSortie;
		}



		public BigDecimal getMontantProductionSortie() {
			return montantProductionSortie;
		}



		public void setMontantProductionSortie(BigDecimal montantProductionSortie) {
			this.montantProductionSortie = montantProductionSortie;
		}



		public BigDecimal getQuantiteVente() {
			return quantiteVente;
		}



		public void setQuantiteVente(BigDecimal quantiteVente) {
			this.quantiteVente = quantiteVente;
		}



		public BigDecimal getPrixVente() {
			return prixVente;
		}



		public void setPrixVente(BigDecimal prixVente) {
			this.prixVente = prixVente;
		}



		public BigDecimal getMontantVente() {
			return montantVente;
		}



		public void setMontantVente(BigDecimal montantVente) {
			this.montantVente = montantVente;
		}



		public BigDecimal getQuantiteGratuite() {
			return quantiteGratuite;
		}



		public void setQuantiteGratuite(BigDecimal quantiteGratuite) {
			this.quantiteGratuite = quantiteGratuite;
		}



		public BigDecimal getPrixGratuite() {
			return prixGratuite;
		}



		public void setPrixGratuite(BigDecimal prixGratuite) {
			this.prixGratuite = prixGratuite;
		}



		public BigDecimal getMontantGratuite() {
			return montantGratuite;
		}



		public void setMontantGratuite(BigDecimal montantGratuite) {
			this.montantGratuite = montantGratuite;
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



		public BigDecimal getQuantiteFinale() {
			return quantiteFinale;
		}



		public void setQuantiteFinale(BigDecimal quantiteFinale) {
			this.quantiteFinale = quantiteFinale;
		}



		public BigDecimal getPrixFinale() {
			return prixFinale;
		}



		public void setPrixFinale(BigDecimal prixFinale) {
			this.prixFinale = prixFinale;
		}



		public BigDecimal getMontantFinale() {
			return montantFinale;
		}



		public void setMontantFinale(BigDecimal montantFinale) {
			this.montantFinale = montantFinale;
		}



		public Magasin getMagasin() {
			return magasin;
		}



		public void setMagasin(Magasin magasin) {
			this.magasin = magasin;
		}



		public static long getSerialversionuid() {
			return serialVersionUID;
		}



		public String getCodearticle() {
			return codearticle;
		}



		public void setCodearticle(String codearticle) {
			this.codearticle = codearticle;
		}
		

	
	

}