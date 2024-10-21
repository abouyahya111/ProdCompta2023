package dao.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.List;


/**
 * The persistent class for the fournisseur database table.
 * 
 */
@Entity
@NamedQuery(name="Fournisseur.findAll", query="SELECT f FROM Fournisseur f")
public class Fournisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	private String adresse;
	
	private String code ;


	private String email;

	private String nom;

	private String tel;
	
	@ManyToOne
	@JoinColumn(name="id_depot")
	private Depot depot;

	/*//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="fournisseur")
	private List<Commande> commandes;

	//bi-directional many-to-one association to Facture
	@OneToMany(mappedBy="fournisseur")
	private List<Facture> factures;

	//bi-directional many-to-one association to Reglement
	@OneToMany(mappedBy="fournisseur")
	private List<Reglement> reglements;*/

	//bi-directional many-to-one association to RipFournisseur
	/*
	@OneToMany(cascade = CascadeType.ALL, mappedBy="fournisseur")
	private List<RipFournisseur> ripFournisseurs;

*/
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Fournisseur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	/*public List<Commande> getCommandes() {
		return this.commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public Commande addCommande(Commande commande) {
		getCommandes().add(commande);
		commande.setFournisseur(this);

		return commande;
	}

	public Commande removeCommande(Commande commande) {
		getCommandes().remove(commande);
		commande.setFournisseur(null);

		return commande;
	}

	public List<Facture> getFactures() {
		return this.factures;
	}

	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}

	public Facture addFacture(Facture facture) {
		getFactures().add(facture);
		facture.setFournisseur(this);

		return facture;
	}

	public Facture removeFacture(Facture facture) {
		getFactures().remove(facture);
		facture.setFournisseur(null);

		return facture;
	}

	public List<Reglement> getReglements() {
		return this.reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public Reglement addReglement(Reglement reglement) {
		getReglements().add(reglement);
		reglement.setFournisseur(this);

		return reglement;
	}

	public Reglement removeReglement(Reglement reglement) {
		getReglements().remove(reglement);
		reglement.setFournisseur(null);

		return reglement;
	}*/
/*
	public List<RipFournisseur> getRipFournisseurs() {
		return this.ripFournisseurs;
	}

	public void setRipFournisseurs(List<RipFournisseur> ripFournisseurs) {
		this.ripFournisseurs = ripFournisseurs;
	}

	public RipFournisseur addRipFournisseur(RipFournisseur ripFournisseur) {
		getRipFournisseurs().add(ripFournisseur);
		ripFournisseur.setFournisseur(this);

		return ripFournisseur;
	}

	public RipFournisseur removeRipFournisseur(RipFournisseur ripFournisseur) {
		getRipFournisseurs().remove(ripFournisseur);
		ripFournisseur.setFournisseur(null);

		return ripFournisseur;
	}
*/
	
}