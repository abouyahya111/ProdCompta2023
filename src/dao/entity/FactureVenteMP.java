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
@Table(name="facture_VenteMP")
@NamedQuery(name="FactureVenteMP.findAll", query="SELECT c FROM FactureVenteMP c")
public class FactureVenteMP implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@Column(name="NUM_BL")
	private String NumBl;
	
	@Column(name="NUM_FACTURE")
	private String NumFacture;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;

	
	
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
	
	@Column(name="CODE_TRANSFER")
	private String codeTransfer;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="MODIFIER_PAR")
	private Utilisateur modifierPar;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT_Source")
	private Depot depotSource;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT_Destination")
	private Depot depotDestination;
	
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN_Destination")
	private Magasin magasinDestination;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN_Source")
	private Magasin magasinSource;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private Client client;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="CREER_PAR")
	private Utilisateur creerPar;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ANNULER_PAR")
	private Utilisateur annulerPar;

	//bi-directional many-to-one association to DetailCommande
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="factureVenteMP")
	private List<DetailFactureVenteMP> detailFactureVenteMP ;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public FactureVenteMP() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumFacture() {
		return NumFacture;
	}

	public void setNumFacture(String numFacture) {
		NumFacture = numFacture;
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

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getCodeTransfer() {
		return codeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		this.codeTransfer = codeTransfer;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}



	public Magasin getMagasinDestination() {
		return magasinDestination;
	}

	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
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

	public List<DetailFactureVenteMP> getDetailFactureVenteMP() {
		return detailFactureVenteMP;
	}

	public void setDetailFactureVenteMP(
			List<DetailFactureVenteMP> detailFactureVenteMP) {
		this.detailFactureVenteMP = detailFactureVenteMP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMontantTTC(BigDecimal montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Magasin getMagasinSource() {
		return magasinSource;
	}

	public void setMagasinSource(Magasin magasinSource) {
		this.magasinSource = magasinSource;
	}

	public Depot getDepotSource() {
		return depotSource;
	}

	public void setDepotSource(Depot depotSource) {
		this.depotSource = depotSource;
	}

	public Depot getDepotDestination() {
		return depotDestination;
	}

	public void setDepotDestination(Depot depotDestination) {
		this.depotDestination = depotDestination;
	}

	public String getNumBl() {
		return NumBl;
	}

	public void setNumBl(String numBl) {
		NumBl = numBl;
	}

	
	
}