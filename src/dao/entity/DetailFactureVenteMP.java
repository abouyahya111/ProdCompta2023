package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the detail_facture database table.
 * 
 */
@Entity
@Table(name="detail_facture_VenteMP")
@NamedQuery(name="DetailFactureVenteMP.findAll", query="SELECT d FROM DetailFactureVenteMP d")
public class DetailFactureVenteMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//bi-directional many-to-one association to Facture
	@ManyToOne
	@JoinColumn(name="id_MP")
	private MatierePremier matierePremiere;
	
	@Column(name="quantite")
	private BigDecimal quantite;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;


	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	@Column(name="TVA")
	private BigDecimal tva;
	
	@Column(name="REDUCTION")
	private BigDecimal reduction;
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC;
	
	
	//bi-directional many-to-one association to Facture
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="id_factureMP")
	private FactureVenteMP factureVenteMP ;
	
	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateur;
	
	
	


	public DetailFactureVenteMP() {
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public MatierePremier getMatierePremiere() {
		return matierePremiere;
	}





	public void setMatierePremiere(MatierePremier matierePremiere) {
		this.matierePremiere = matierePremiere;
	}





	public BigDecimal getQuantite() {
		return quantite;
	}





	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}





	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}





	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}





	public BigDecimal getMontantHT() {
		return montantHT;
	}





	public void setMontantHT(BigDecimal montantHT) {
		this.montantHT = montantHT;
	}





	public BigDecimal getTva() {
		return tva;
	}





	public void setTva(BigDecimal tva) {
		this.tva = tva;
	}





	public BigDecimal getReduction() {
		return reduction;
	}





	public void setReduction(BigDecimal reduction) {
		this.reduction = reduction;
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








	public FactureVenteMP getFactureVenteMP() {
		return factureVenteMP;
	}





	public void setFactureVenteMP(FactureVenteMP factureVenteMP) {
		this.factureVenteMP = factureVenteMP;
	}





	public Utilisateur getUtilisateur() {
		return utilisateur;
	}





	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}