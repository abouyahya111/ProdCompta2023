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
@Table(name="facture_Achat")
@NamedQuery(name="FactureAchat.findAll", query="SELECT c FROM FactureAchat c")
public class FactureAchat implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	
	@Column(name="NUM_FACTURE")
	private String numFacture;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_CREER")
	private Date dateCreer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIER")
	private Date dateModifier;
	
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
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin;
	
	
	
	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Fournisseur fournisseur;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="CREER_PAR")
	private Utilisateur creerPar;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="ANNULER_PAR")
	private Utilisateur annulerPar;

	//bi-directional many-to-one association to DetailCommande
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="factureAchat")
	private List<DetailFactureAchat> detailFactureAchat ;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public FactureAchat() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String NumFacture) {
		numFacture = NumFacture;
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

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur Fournisseur) {
		fournisseur = Fournisseur;
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

	public List<DetailFactureAchat> getDetailFactureAchat() {
		return detailFactureAchat;
	}

	public void setDetailFactureAchat(List<DetailFactureAchat> detailFactureAchat) {
		this.detailFactureAchat = detailFactureAchat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodeTransfer() {
		return codeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		this.codeTransfer = codeTransfer;
	}

	public Date getDateCreer() {
		return dateCreer;
	}

	public void setDateCreer(Date dateCreer) {
		this.dateCreer = dateCreer;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
	}

	

	
	
}