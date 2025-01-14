package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the reglement database table.
 * 
 */
@Entity
@NamedQuery(name="Reglement.findAll", query="SELECT r FROM Reglement r")
public class Reglement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="montant_reglement")
	private BigDecimal montantReglement;

	@Column(name="montant_total")
	private BigDecimal montantTotal;

	@Column(name="prix_moyen")
	private BigDecimal prixMoyen;

	private String statut;

	@Column(name="taux_reel_devise")
	private BigDecimal tauxReelDevise;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur utilisateur1;

	//bi-directional many-to-one association to Fournisseur
	@ManyToOne
	@JoinColumn(name="id_fournisseur")
	private Fournisseur fournisseur;

	//bi-directional many-to-one association to RipFournisseur
	@ManyToOne
	@JoinColumn(name="id_rip")
	private RipFournisseur ripFournisseur;

	//bi-directional many-to-one association to Devise
	@ManyToOne
	@JoinColumn(name="id_devise")
	private Devise devise;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateur2;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="annuler_par")
	private Utilisateur utilisateur3;

	public Reglement() {
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

	public BigDecimal getMontantReglement() {
		return this.montantReglement;
	}

	public void setMontantReglement(BigDecimal montantReglement) {
		this.montantReglement = montantReglement;
	}

	public BigDecimal getMontantTotal() {
		return this.montantTotal;
	}

	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}

	public BigDecimal getPrixMoyen() {
		return this.prixMoyen;
	}

	public void setPrixMoyen(BigDecimal prixMoyen) {
		this.prixMoyen = prixMoyen;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public BigDecimal getTauxReelDevise() {
		return this.tauxReelDevise;
	}

	public void setTauxReelDevise(BigDecimal tauxReelDevise) {
		this.tauxReelDevise = tauxReelDevise;
	}

	public Utilisateur getUtilisateur1() {
		return this.utilisateur1;
	}

	public void setUtilisateur1(Utilisateur utilisateur1) {
		this.utilisateur1 = utilisateur1;
	}

	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public RipFournisseur getRipFournisseur() {
		return this.ripFournisseur;
	}

	public void setRipFournisseur(RipFournisseur ripFournisseur) {
		this.ripFournisseur = ripFournisseur;
	}

	public Devise getDevise() {
		return this.devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
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