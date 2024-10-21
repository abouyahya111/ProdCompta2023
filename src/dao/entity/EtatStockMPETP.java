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
@NamedQuery(name="EtatStockMP.findAll", query="SELECT f FROM EtatStockMP f")
public class EtatStockMPETP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
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
		

	public BigDecimal getMarge() {
			return marge;
		}

		public void setMarge(BigDecimal marge) {
			this.marge = marge;
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public MatierePremier getMp() {
		return mp;
	}

	public void setMp(MatierePremier mp) {
		this.mp = mp;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}
	
	
		
	
	

}