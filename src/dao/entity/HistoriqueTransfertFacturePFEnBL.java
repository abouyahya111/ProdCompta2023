package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="HistoriqueTransfertFacturePFEnBL.findAll", query="SELECT c FROM HistoriqueTransfertFacturePFEnBL c")
public class HistoriqueTransfertFacturePFEnBL implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="NUM_BL")
	private String numBl ;
	
	@Column(name="NUM_FACTURE")
	private String numFacture;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_BL")
	private Date dateBl;
	

	@Column(name="TYPE")
	private String type;
	
	@Column(name="ETAT")
	private String etat;
	
	@Column(name="MODE_REGLEMENT")
	private String modeReglement;

	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC;
	
	
	
	@Column(name="NUM_CHEQUE")
	private String numCheque;
	
	@Column(name="NUM_TRAITE")
	private String numtraite;
	
	@Column(name="NUM_VERSEMENT")
	private String numVersement;
	
	@Column(name="CODE_TRANSFER")
	private String codeTransfer;

	
	@Column(name="REGLEMENT_espece")
	private BigDecimal espece;
	
	@Column(name="REGLEMENT_versement")
	private BigDecimal versement;

	@Column(name="REGLEMENT_virement")
	private BigDecimal virement;
	
	@Column(name="REGLEMENT_cheque")
	private BigDecimal cheque;
	
	@Column(name="REGLEMENT_traite")
	private BigDecimal traite;
	
	@Column(name="REGLEMENT_credit")
	private BigDecimal credit;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_TRANSFERTBL")
	private Date datetransfertenbl;
	
	@ManyToOne
	@JoinColumn(name="transfert_PAR")
	private Utilisateur transfertPar;
	
	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientPF clientPF;

	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Client Fournisseur;
	
	
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
	//@OneToMany(cascade = CascadeType.ALL,mappedBy="facturePF")
	@OneToMany(cascade = CascadeType.ALL,mappedBy="facturePF")
	private List<DetailFacturePF> detailFacturePF; 

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public HistoriqueTransfertFacturePFEnBL() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String NumFacture) {
		numFacture = NumFacture;
	}



	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	

	

	public String getCodeTransfer() {
		return codeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		this.codeTransfer = codeTransfer;
	}

	public ClientPF getClientPF() {
		return clientPF;
	}

	public void setClientPF(ClientPF ClientPF) {
		clientPF = ClientPF;
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

	public List<DetailFacturePF> getDetailFacturePF() {
		return detailFacturePF;
	}

	public void setDetailFacturePF(List<DetailFacturePF> detailFacturePF) {
		this.detailFacturePF = detailFacturePF;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public Client getFournisseur() {
		return Fournisseur;
	}

	public void setFournisseur(Client fournisseur) {
		Fournisseur = fournisseur;
	}

	public String getNumBl() {
		return numBl;
	}

	public void setNumBl(String NumBl) {
		numBl = NumBl;
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

	public Date getDateBl() {
		return dateBl;
	}

	public void setDateBl(Date dateBl) {
		this.dateBl = dateBl;
	}

	public BigDecimal getEspece() {
		return espece;
	}

	public void setEspece(BigDecimal espece) {
		this.espece = espece;
	}

	public BigDecimal getVirement() {
		return virement;
	}

	public void setVirement(BigDecimal virement) {
		this.virement = virement;
	}

	public BigDecimal getCheque() {
		return cheque;
	}

	public void setCheque(BigDecimal cheque) {
		this.cheque = cheque;
	}

	public BigDecimal getTraite() {
		return traite;
	}

	public void setTraite(BigDecimal traite) {
		this.traite = traite;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getNumtraite() {
		return numtraite;
	}

	public void setNumtraite(String numtraite) {
		this.numtraite = numtraite;
	}

	public String getNumVersement() {
		return numVersement;
	}

	public void setNumVersement(String numVersement) {
		this.numVersement = numVersement;
	}

	public BigDecimal getVersement() {
		return versement;
	}

	public void setVersement(BigDecimal versement) {
		this.versement = versement;
	}

	public Date getDatetransfertenbl() {
		return datetransfertenbl;
	}

	public void setDatetransfertenbl(Date datetransfertenbl) {
		this.datetransfertenbl = datetransfertenbl;
	}

	public Utilisateur getTransfertPar() {
		return transfertPar;
	}

	public void setTransfertPar(Utilisateur transfertPar) {
		this.transfertPar = transfertPar;
	}

	
	


	

	
	
}