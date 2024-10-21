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
@NamedQuery(name="EtatMargeStock.findAll", query="SELECT f FROM EtatMargeStock f")
public class EtatMargeStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="sousfamille")
	private String sousfamille;
	
	
	@Column(name="famille")
	private String famille;
	
	// Stock Initial

	@Column(name="montant_initial")
	private BigDecimal montantInitial;
	
	// Achat
	
		
	@Column(name="montant_achat")
	private BigDecimal montantAchat;
	
	// Production
		
			
	@Column(name="montant_production_entrer")
	private BigDecimal montantProductionEntrer;
	
	
		// RRR Annee derniere 
		

		@Column(name="montant_rrr")
		private BigDecimal montantRRR;
				
		
		
		// RRR Crédit
		
				
		@Column(name="montant_rrr_credit")
		private BigDecimal montantRRRCredit;

	
	//  SA 
	
				
		@Column(name="montant_Finale")
		private BigDecimal montantFinale;
		
		
		//  Achat Revendu 
		
		
		@Column(name="montant_Achat_Revendu")
		private BigDecimal montantAchatRevendu;
		
		
		//  CA
		
		
		@Column(name="montant_Chiffre_Affaire")
		private BigDecimal montantChiffreAffaire;
		
	//  Marge
	
		@Column(name="marge")
		private BigDecimal marge;
	
		//  % Pourcentage
		
		@Column(name="pourcentage")
		private BigDecimal pourcentage;
		
		
		@ManyToOne
		@JoinColumn(name="ID_MAGASIN")
		private Magasin magasin;


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


	

		public String getSousfamille() {
			return sousfamille;
		}


		public void setSousfamille(String sousfamille) {
			this.sousfamille = sousfamille;
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


		public BigDecimal getMontantProductionEntrer() {
			return montantProductionEntrer;
		}


		public void setMontantProductionEntrer(BigDecimal montantProductionEntrer) {
			this.montantProductionEntrer = montantProductionEntrer;
		}


		public BigDecimal getMontantRRR() {
			return montantRRR;
		}


		public void setMontantRRR(BigDecimal montantRRR) {
			this.montantRRR = montantRRR;
		}


		public BigDecimal getMontantRRRCredit() {
			return montantRRRCredit;
		}


		public void setMontantRRRCredit(BigDecimal montantRRRCredit) {
			this.montantRRRCredit = montantRRRCredit;
		}


		public BigDecimal getMontantFinale() {
			return montantFinale;
		}


		public void setMontantFinale(BigDecimal montantFinale) {
			this.montantFinale = montantFinale;
		}


		public BigDecimal getMontantAchatRevendu() {
			return montantAchatRevendu;
		}


		public void setMontantAchatRevendu(BigDecimal montantAchatRevendu) {
			this.montantAchatRevendu = montantAchatRevendu;
		}


		public BigDecimal getMontantChiffreAffaire() {
			return montantChiffreAffaire;
		}


		public void setMontantChiffreAffaire(BigDecimal montantChiffreAffaire) {
			this.montantChiffreAffaire = montantChiffreAffaire;
		}


		public BigDecimal getMarge() {
			return marge;
		}


		public void setMarge(BigDecimal marge) {
			this.marge = marge;
		}


		public BigDecimal getPourcentage() {
			return pourcentage;
		}


		public void setPourcentage(BigDecimal pourcentage) {
			this.pourcentage = pourcentage;
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


		public String getFamille() {
			return famille;
		}


		public void setFamille(String famille) {
			this.famille = famille;
		}



	

}