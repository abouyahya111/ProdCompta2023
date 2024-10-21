package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the fournisseur database table.
 * 
 */
@Entity
@NamedQuery(name="ClientFP.findAll", query="SELECT f FROM ClientPF f")
public class ClientPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	private String adresse;
	private String adresse2;
	private String adresse3;
	private String adresse4;
	private String patente;
	
	private String code ;

	private String email;

	private String nom;

	private String numTel;
	private String delaiPaiementParBlOuFacture;
	
	private BigDecimal delaiPaiement;
	
	@Column(name="CODE_DEPOT")
	private String codeDepot;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	

	@ManyToOne
	@JoinColumn(name="id_COMPTE_CLIENT")
	private CompteClient compteClient;
	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;

	@JoinColumn(name="TYPE_CLIENT")
	private String typeClient;
	private String ice;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public ClientPF() {
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

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Utilisateur getUtilCreation() {
		return utilCreation;
	}

	public void setUtilCreation(Utilisateur utilCreation) {
		this.utilCreation = utilCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public String getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}

	public String getCodeDepot() {
		return codeDepot;
	}

	public void setCodeDepot(String codeDepot) {
		this.codeDepot = codeDepot;
	}

	public CompteClient getCompteClient() {
		return compteClient;
	}

	public void setCompteClient(CompteClient compteClient) {
		this.compteClient = compteClient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getAdresse3() {
		return adresse3;
	}

	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}

	public String getAdresse4() {
		return adresse4;
	}

	public void setAdresse4(String adresse4) {
		this.adresse4 = adresse4;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public BigDecimal getDelaiPaiement() {
		return delaiPaiement;
	}

	public void setDelaiPaiement(BigDecimal delaiPaiement) {
		this.delaiPaiement = delaiPaiement;
	}

	public String getDelaiPaiementParBlOuFacture() {
		return delaiPaiementParBlOuFacture;
	}

	public void setDelaiPaiementParBlOuFacture(String delaiPaiementParBlOuFacture) {
		this.delaiPaiementParBlOuFacture = delaiPaiementParBlOuFacture;
	}

	

}