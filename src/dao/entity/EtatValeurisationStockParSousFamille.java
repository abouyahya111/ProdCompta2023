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
@NamedQuery(name="EtatValeurisationStockParSousFamille.findAll", query="SELECT f FROM EtatValeurisationStockParSousFamille f")
public class EtatValeurisationStockParSousFamille implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="ARTICLE")
	private String article;
	
	@Column(name="ARTICLE")
	private String matierePremiere;
	
	
	@Column(name="CODE_ARTICLE")
	private String codeArticle;

	@Column(name="SOUS_FAMILLE")
	private String sousFamille;
	
	@Column(name="FAMILLE")
	private String famille;
	
	
	@Column(name="type")
	private String type;
	
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
	
	// Sortie
		
	@Column(name="quantite_sortie")
	private BigDecimal quantiteSortie;

	@Column(name="prix_sortie")
	private BigDecimal prixSortie;
			
	@Column(name="montant_sortie")
	private BigDecimal montantSortie;
	
	
	// vente
	
		@Column(name="quantite_vente")
		private BigDecimal quantitevente;

		@Column(name="prix_vente")
		private BigDecimal prixvente;
				
		@Column(name="montant_vente")
		private BigDecimal montantvente;
	
	
	// Dechet Production
	
		@Column(name="quantite_dechet")
		private BigDecimal quantiteDechet;

		@Column(name="prix_dechet")
		private BigDecimal prixDechet;
				
		@Column(name="montant_dechet")
		private BigDecimal montantDechet;
	
		// Offre Production 
		
		@Column(name="quantite_offre_production")
		private BigDecimal quantiteOffreProduction;

		@Column(name="prix_offre_production")
		private BigDecimal prixOffreProduction;
						
		@Column(name="montant_offre_production")
		private BigDecimal montantOffreProduction;
				
		
		
		
		// Production SERVICE
		
		@Column(name="quantite_service")
		private BigDecimal quantiteService;

		@Column(name="prix_service")
		private BigDecimal prixService;
				
		@Column(name="montant_service")
		private BigDecimal montantService;
		
	
		// Dechet Production Service
		
		@Column(name="quantite_dechet_service")
		private BigDecimal quantiteDechetService;

		@Column(name="prix_dechet_service")
		private BigDecimal prixDechetService;
				
		@Column(name="montant_dechet_service")
		private BigDecimal montantDechetService;
		
	// Offre Production Service
		
		@Column(name="quantite_offre_service")
		private BigDecimal quantiteOffreService;

		@Column(name="prix_offre_service")
		private BigDecimal prixOffreService;
				
		@Column(name="montant_offre_service")
		private BigDecimal montantOffreService;
		
	
	
	// avoir (retour) Facture Avoire MP
	
		@Column(name="quantite_avoir")
		private BigDecimal quantiteAvoir;

		@Column(name="prix_avoire")
		private BigDecimal prixAvoir;
				
		@Column(name="montant_Avoir")
		private BigDecimal montantAvoir;
		
		
		// avoir Client PF
		
			@Column(name="quantite_avoir_clientPF")
			private BigDecimal quantiteAvoirClientPF;

			@Column(name="prix_avoir_clientPF")
			private BigDecimal prixAvoirClientPF;
					
			@Column(name="montant_Avoir_clientPF")
			private BigDecimal montantAvoirClientPF;
		
		
		
		
		// Transfert MP En PF
		
		@Column(name="quantite_transfer_mp_pf")
		private BigDecimal quantiteTransfertMPPF;

		@Column(name="prix_transfer_mp_pf")
		private BigDecimal prixTransfertMPPF;
				
		@Column(name="montant_transfer_mp_pf")
		private BigDecimal montantTransfertMPPF;
	
	
	// Stock Finale
	
		@Column(name="quantite_finale")
		private BigDecimal quantiteFinale;

		@Column(name="prix_Finale")
		private BigDecimal prixFinale;
		
		@Column(name="montant_Finale")
		private BigDecimal montantFinale;
		
		@Column(name="la_Marge")
		private BigDecimal marge;
		
		
		@ManyToOne
		@JoinColumn(name="ID_MAGASIN")
		private Magasin magasin;
		
		
		
		// Production
		
			@Column(name="quantite_production")
			private BigDecimal quantiteProduction;

			@Column(name="prix_production")
			private BigDecimal prixProduction;
				
			@Column(name="montant_production")
			private BigDecimal montantProduction;
			
			
			// Transfer Entrer
			
			@Column(name="quantite_entrer")
			private BigDecimal quantiteEntrer;

			@Column(name="prix_entrer")
			private BigDecimal prixEntrer;
					
			@Column(name="montant_entrer")
			private BigDecimal montantEntrer;
			
		
					
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
				
		
		
		@Column(name="achat_revendu")
		private BigDecimal achatRevendu;



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



		public String getSousFamille() {
			return sousFamille;
		}



		public void setSousFamille(String sousFamille) {
			this.sousFamille = sousFamille;
		}



		public String getFamille() {
			return famille;
		}



		public void setFamille(String famille) {
			this.famille = famille;
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



		public BigDecimal getQuantiteSortie() {
			return quantiteSortie;
		}



		public void setQuantiteSortie(BigDecimal quantiteSortie) {
			this.quantiteSortie = quantiteSortie;
		}



		public BigDecimal getPrixSortie() {
			return prixSortie;
		}



		public void setPrixSortie(BigDecimal prixSortie) {
			this.prixSortie = prixSortie;
		}



		public BigDecimal getMontantSortie() {
			return montantSortie;
		}



		public void setMontantSortie(BigDecimal montantSortie) {
			this.montantSortie = montantSortie;
		}



		public BigDecimal getQuantiteDechet() {
			return quantiteDechet;
		}



		public void setQuantiteDechet(BigDecimal quantiteDechet) {
			this.quantiteDechet = quantiteDechet;
		}



		public BigDecimal getPrixDechet() {
			return prixDechet;
		}



		public void setPrixDechet(BigDecimal prixDechet) {
			this.prixDechet = prixDechet;
		}



		public BigDecimal getMontantDechet() {
			return montantDechet;
		}



		public void setMontantDechet(BigDecimal montantDechet) {
			this.montantDechet = montantDechet;
		}



		public BigDecimal getQuantiteOffreProduction() {
			return quantiteOffreProduction;
		}



		public void setQuantiteOffreProduction(BigDecimal quantiteOffreProduction) {
			this.quantiteOffreProduction = quantiteOffreProduction;
		}



		public BigDecimal getPrixOffreProduction() {
			return prixOffreProduction;
		}



		public void setPrixOffreProduction(BigDecimal prixOffreProduction) {
			this.prixOffreProduction = prixOffreProduction;
		}



		public BigDecimal getMontantOffreProduction() {
			return montantOffreProduction;
		}



		public void setMontantOffreProduction(BigDecimal montantOffreProduction) {
			this.montantOffreProduction = montantOffreProduction;
		}



		public BigDecimal getQuantiteService() {
			return quantiteService;
		}



		public void setQuantiteService(BigDecimal quantiteService) {
			this.quantiteService = quantiteService;
		}



		public BigDecimal getPrixService() {
			return prixService;
		}



		public void setPrixService(BigDecimal prixService) {
			this.prixService = prixService;
		}



		public BigDecimal getMontantService() {
			return montantService;
		}



		public void setMontantService(BigDecimal montantService) {
			this.montantService = montantService;
		}



		public BigDecimal getQuantiteDechetService() {
			return quantiteDechetService;
		}



		public void setQuantiteDechetService(BigDecimal quantiteDechetService) {
			this.quantiteDechetService = quantiteDechetService;
		}



		public BigDecimal getPrixDechetService() {
			return prixDechetService;
		}



		public void setPrixDechetService(BigDecimal prixDechetService) {
			this.prixDechetService = prixDechetService;
		}



		public BigDecimal getMontantDechetService() {
			return montantDechetService;
		}



		public void setMontantDechetService(BigDecimal montantDechetService) {
			this.montantDechetService = montantDechetService;
		}



		public BigDecimal getQuantiteOffreService() {
			return quantiteOffreService;
		}



		public void setQuantiteOffreService(BigDecimal quantiteOffreService) {
			this.quantiteOffreService = quantiteOffreService;
		}



		public BigDecimal getPrixOffreService() {
			return prixOffreService;
		}



		public void setPrixOffreService(BigDecimal prixOffreService) {
			this.prixOffreService = prixOffreService;
		}



		public BigDecimal getMontantOffreService() {
			return montantOffreService;
		}



		public void setMontantOffreService(BigDecimal montantOffreService) {
			this.montantOffreService = montantOffreService;
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



		public BigDecimal getQuantiteTransfertMPPF() {
			return quantiteTransfertMPPF;
		}



		public void setQuantiteTransfertMPPF(BigDecimal quantiteTransfertMPPF) {
			this.quantiteTransfertMPPF = quantiteTransfertMPPF;
		}



		public BigDecimal getPrixTransfertMPPF() {
			return prixTransfertMPPF;
		}



		public void setPrixTransfertMPPF(BigDecimal prixTransfertMPPF) {
			this.prixTransfertMPPF = prixTransfertMPPF;
		}



		public BigDecimal getMontantTransfertMPPF() {
			return montantTransfertMPPF;
		}



		public void setMontantTransfertMPPF(BigDecimal montantTransfertMPPF) {
			this.montantTransfertMPPF = montantTransfertMPPF;
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



		public BigDecimal getMarge() {
			return marge;
		}



		public void setMarge(BigDecimal marge) {
			this.marge = marge;
		}



		public Magasin getMagasin() {
			return magasin;
		}



		public void setMagasin(Magasin magasin) {
			this.magasin = magasin;
		}



		public BigDecimal getQuantiteProduction() {
			return quantiteProduction;
		}



		public void setQuantiteProduction(BigDecimal quantiteProduction) {
			this.quantiteProduction = quantiteProduction;
		}



		public BigDecimal getPrixProduction() {
			return prixProduction;
		}



		public void setPrixProduction(BigDecimal prixProduction) {
			this.prixProduction = prixProduction;
		}



		public BigDecimal getMontantProduction() {
			return montantProduction;
		}



		public void setMontantProduction(BigDecimal montantProduction) {
			this.montantProduction = montantProduction;
		}



		public BigDecimal getQuantiteEntrer() {
			return quantiteEntrer;
		}



		public void setQuantiteEntrer(BigDecimal quantiteEntrer) {
			this.quantiteEntrer = quantiteEntrer;
		}



		public BigDecimal getPrixEntrer() {
			return prixEntrer;
		}



		public void setPrixEntrer(BigDecimal prixEntrer) {
			this.prixEntrer = prixEntrer;
		}



		public BigDecimal getMontantEntrer() {
			return montantEntrer;
		}



		public void setMontantEntrer(BigDecimal montantEntrer) {
			this.montantEntrer = montantEntrer;
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



		public BigDecimal getAchatRevendu() {
			return achatRevendu;
		}



		public void setAchatRevendu(BigDecimal achatRevendu) {
			this.achatRevendu = achatRevendu;
		}



		public static long getSerialversionuid() {
			return serialVersionUID;
		}



		public BigDecimal getQuantitevente() {
			return quantitevente;
		}



		public void setQuantitevente(BigDecimal quantitevente) {
			this.quantitevente = quantitevente;
		}



		public BigDecimal getPrixvente() {
			return prixvente;
		}



		public void setPrixvente(BigDecimal prixvente) {
			this.prixvente = prixvente;
		}



		public BigDecimal getMontantvente() {
			return montantvente;
		}



		public void setMontantvente(BigDecimal montantvente) {
			this.montantvente = montantvente;
		}



		public String getMatierePremiere() {
			return matierePremiere;
		}



		public void setMatierePremiere(String matierePremiere) {
			this.matierePremiere = matierePremiere;
		}



		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		public String getCodeArticle() {
			return codeArticle;
		}



		public void setCodeArticle(String codeArticle) {
			this.codeArticle = codeArticle;
		}



		public BigDecimal getQuantiteAvoirClientPF() {
			return quantiteAvoirClientPF;
		}



		public void setQuantiteAvoirClientPF(BigDecimal quantiteAvoirClientPF) {
			this.quantiteAvoirClientPF = quantiteAvoirClientPF;
		}



		public BigDecimal getPrixAvoirClientPF() {
			return prixAvoirClientPF;
		}



		public void setPrixAvoirClientPF(BigDecimal prixAvoirClientPF) {
			this.prixAvoirClientPF = prixAvoirClientPF;
		}



		public BigDecimal getMontantAvoirClientPF() {
			return montantAvoirClientPF;
		}



		public void setMontantAvoirClientPF(BigDecimal montantAvoirClientPF) {
			this.montantAvoirClientPF = montantAvoirClientPF;
		}
		

	
	

}