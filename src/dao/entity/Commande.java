package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="date_laivraison")
	private Date dateLaivraison;

	private BigDecimal montant;

	private String statut;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;

	

	//bi-directional many-to-one association to Fournisseur
	@ManyToOne
	@JoinColumn(name="id_four")
	private Fournisseur fournisseur;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="annuler_par")
	private Utilisateur annulerPar;

	//bi-directional many-to-one association to DetailCommande
	@OneToMany(cascade = CascadeType.ALL,mappedBy="commande")
	private List<DetailCommande> detailCommandes;

	/*//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="commande")
	private List<Facture> factures;*/

	public Commande() {
	}
	
	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
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

	public Date getDateLaivraison() {
		return this.dateLaivraison;
	}

	public void setDateLaivraison(Date dateLaivraison) {
		this.dateLaivraison = dateLaivraison;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}



	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	

	

	public List<DetailCommande> getDetailCommandes() {
		return this.detailCommandes;
	}

	public void setDetailCommandes(List<DetailCommande> detailCommandes) {
		this.detailCommandes = detailCommandes;
	}

	public DetailCommande addDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().add(detailCommande);
		detailCommande.setCommande(this);

		return detailCommande;
	}

	public DetailCommande removeDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().remove(detailCommande);
		detailCommande.setCommande(null);

		return detailCommande;
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

	/*public List<Facture> getFactures() {
		return this.factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Facture addFacture(Facture facture) {
		getFactures().add(facture);
		facture.setCommande(this);

		return facture;
	}

	public Facture removeFacture(Facture facture) {
		getFactures().remove(facture);
		facture.setCommande(null);

		return facture;
	}*/

}