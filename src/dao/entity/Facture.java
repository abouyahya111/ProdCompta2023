package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the facture database table.
 * 
 */
@Entity
@NamedQuery(name="Facture.findAll", query="SELECT f FROM Facture f")
public class Facture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_en_port")
	private Date dateEnPort;

	@Temporal(TemporalType.DATE)
	@Column(name="date_laivraison")
	private Date dateLaivraison;

	@Column(name="delai_paiement")
	private String delaiPaiement;

	private String etat;

	private BigDecimal montant;

	@Column(name="nim_fact_four")
	private String nimFactFour;

	@Column(name="num_bouquine")
	private String numBouquine;

	@Column(name="num_contenaire")
	private String numContenaire;

	private String satut;

	private String situation;

	//bi-directional many-to-one association to DetailFacture
	@OneToMany(mappedBy="facture")
	private List<DetailFacture> detailFactures;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_commande")
	private Commande commande;

	//bi-directional many-to-one association to Fournisseur
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private Fournisseur fournisseur;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateur2;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="annuler_par")
	private Utilisateur utilisateur3;

	public Facture() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateEnPort() {
		return this.dateEnPort;
	}

	public void setDateEnPort(Date dateEnPort) {
		this.dateEnPort = dateEnPort;
	}

	public Date getDateLaivraison() {
		return this.dateLaivraison;
	}

	public void setDateLaivraison(Date dateLaivraison) {
		this.dateLaivraison = dateLaivraison;
	}

	public String getDelaiPaiement() {
		return this.delaiPaiement;
	}

	public void setDelaiPaiement(String delaiPaiement) {
		this.delaiPaiement = delaiPaiement;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getNimFactFour() {
		return this.nimFactFour;
	}

	public void setNimFactFour(String nimFactFour) {
		this.nimFactFour = nimFactFour;
	}

	public String getNumBouquine() {
		return this.numBouquine;
	}

	public void setNumBouquine(String numBouquine) {
		this.numBouquine = numBouquine;
	}

	public String getNumContenaire() {
		return this.numContenaire;
	}

	public void setNumContenaire(String numContenaire) {
		this.numContenaire = numContenaire;
	}

	public String getSatut() {
		return this.satut;
	}

	public void setSatut(String satut) {
		this.satut = satut;
	}

	public String getSituation() {
		return this.situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public List<DetailFacture> getDetailFactures() {
		return this.detailFactures;
	}

	public void setDetailFactures(List<DetailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}

	public DetailFacture addDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().add(detailFacture);
		detailFacture.setFacture(this);

		return detailFacture;
	}

	public DetailFacture removeDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().remove(detailFacture);
		detailFacture.setFacture(null);

		return detailFacture;
	}

	public Utilisateur getUtilisateur1() {
		return this.utilisateur1;
	}

	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public Utilisateur getUtilisateur2() {
		return this.utilisateur2;
	}

	public void setUtilisateur2(Utilisateur utilisateur2) {
		this.utilisateur2 = utilisateur2;
	}

	public Utilisateur getUtilisateur3() {
		return this.utilisateur3;
	}

	public void setUtilisateur3(Utilisateur utilisateur3) {
		this.utilisateur3 = utilisateur3;
	}

}