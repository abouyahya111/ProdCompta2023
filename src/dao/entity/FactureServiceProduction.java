package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


	
	@Entity
	@NamedQuery(name="FactureServiceProduction.findAll", query="SELECT c FROM FactureServiceProduction c")
	public class FactureServiceProduction implements Serializable {
		

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;


		@Column(name="NUM_FACTURE")
		private String numFacture;
		
		
		@Temporal(TemporalType.DATE)
		@Column(name="DATE_FACTURE")
		private Date dateFacture;

		@Column(name="TYPE")
		private String type;
		
		@Column(name="ETAT")
		private String etat;
		
		@Column(name="MODE_REGLEMENT")
		private String modeReglement;

		@Column(name="MONTANT_TTC")
		private BigDecimal montantTTC;
		
		@Column(name="MONTANT_HT")
		private BigDecimal montantHT;
		
		@Column(name="MONTANT_TVA")
		private BigDecimal montantTVA;
		
		@Column(name="NUM_CHEQUE")
		private String numCheque;
		
		@Column(name="NUM_OF")
		private String numOF;
		
		
		//bi-directional many-to-one association to Utilisateur
		@ManyToOne
		@JoinColumn(name="MODIFIER_PAR")
		private Utilisateur modifierPar;

		@ManyToOne
		@JoinColumn(name="ID_DEPOT")
		private Depot depot;
		
		@ManyToOne
		@JoinColumn(name="ID_MAGASIN")
		private Magasin magasin;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="DATE_SAISI")
		private Date dateSaisi;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="DATE_MODIFIER")
		private Date dateModifier;
		
		public Magasin getMagasin() {
			return magasin;
		}

		public void setMagasin(Magasin magasin) {
			this.magasin = magasin;
		}

		@ManyToOne
		@JoinColumn(name="ID_CLIENT")
		private Client client;

		
		public Depot getDepot() {
			return depot;
		}

		public void setDepot(Depot depot) {
			this.depot = depot;
		}

		//bi-directional many-to-one association to Utilisateur
		@ManyToOne
		@JoinColumn(name="CREER_PAR")
		private Utilisateur creerPar;

		//bi-directional many-to-one association to Utilisateur
		@ManyToOne
		@JoinColumn(name="ANNULER_PAR")
		private Utilisateur annulerPar;
		
		//bi-directional many-to-one association to DetailCommande


		//bi-directional many-to-one association to DetailCommande
		//@OneToMany(cascade = CascadeType.ALL,mappedBy="facturePF")
		@OneToMany(mappedBy="factureService")
		private List<DetailFactureServiceProduction> detailFactureService;

		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNumFacture() {
			return numFacture;
		}

		public void setNumFacture(String numFacture) {
			this.numFacture = numFacture;
		}

		public Date getDateFacture() {
			return dateFacture;
		}

		public void setDateFacture(Date dateFacture) {
			this.dateFacture = dateFacture;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

		public String getModeReglement() {
			return modeReglement;
		}

		public void setModeReglement(String modeReglement) {
			this.modeReglement = modeReglement;
		}

		public BigDecimal getMontantTTC() {
			return montantTTC;
		}

		public void setMontantTTC(BigDecimal montantTTC) {
			this.montantTTC = montantTTC;
		}

		public String getNumCheque() {
			return numCheque;
		}

		public void setNumCheque(String numCheque) {
			this.numCheque = numCheque;
		}

		public Utilisateur getModifierPar() {
			return modifierPar;
		}

		public void setModifierPar(Utilisateur modifierPar) {
			this.modifierPar = modifierPar;
		}

		public Date getDateSaisi() {
			return dateSaisi;
		}

		public void setDateSaisi(Date dateSaisi) {
			this.dateSaisi = dateSaisi;
		}

		public Date getDateModifier() {
			return dateModifier;
		}

		public void setDateModifier(Date dateModifier) {
			this.dateModifier = dateModifier;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public Utilisateur getCreerPar() {
			return creerPar;
		}

		public void setCreerPar(Utilisateur creerPar) {
			this.creerPar = creerPar;
		}

		public Utilisateur getAnnulerPar() {
			return annulerPar;
		}

		public void setAnnulerPar(Utilisateur annulerPar) {
			this.annulerPar = annulerPar;
		}

		public List<DetailFactureServiceProduction> getDetailFactureService() {
			return detailFactureService;
		}

		public void setDetailFactureService(
				List<DetailFactureServiceProduction> detailFactureService) {
			this.detailFactureService = detailFactureService;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
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

		public String getNumOF() {
			return numOF;
		}

		public void setNumOF(String numOF) {
			this.numOF = numOF;
		} 

		
	
	
	
	
}