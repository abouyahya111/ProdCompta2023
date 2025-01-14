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
 * The persistent class for the employe database table.
 * 
 */
@Entity
@NamedQuery(name="Employe.findAll", query="SELECT e FROM Employe e")
public class Employe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="cout_horaire")
	private BigDecimal coutHoraire;

	private String matricule;

	private String nom;
	
	private String numDossier;

	private BigDecimal remise;

	private String responsabilite;
	
	private String adresse;
	
	private String service;
	
	private String lieuNaissance;
	
	@Column(name="NUM_CNSS")
	private String NumCNSS;
	
	@Column(name="SALARIE")
	private boolean salarie;
	
	private boolean actif;
	
	@Column(name="NUM_TEL")
	private String numTel;
	
	@Column(name="BLOCAGE_EMPLOYE")
	private String blocageEmploye;
	
	@Column(name="DATE_CREATION")
	private Date dateCreation;
	
	@ManyToOne
	@JoinColumn(name="UTIL_CREATION")
	private Utilisateur utilCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Date dateModification;
	
	@Column(name="DATE_NAISSANCE")
	private Date dateNaissance;
	
	@Column(name="DATE_ENTRE")
	private Date dateEntre;
	
	@ManyToOne
	@JoinColumn(name="UTIL_MAJ")
	private Utilisateur utilisateurMAJ;

	@ManyToOne
	@JoinColumn(name="ID_EQUIPE")
	private Equipe equipe;
	
	@ManyToOne
	@JoinColumn(name="REF_TYPE_RES")
	private TypeResEmploye typeResEmploye;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;

	public Employe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCoutHoraire() {
		return this.coutHoraire;
	}

	public void setCoutHoraire(BigDecimal coutHoraire) {
		this.coutHoraire = coutHoraire;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getRemise() {
		return this.remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	public String getResponsabilite() {
		return this.responsabilite;
	}

	public void setResponsabilite(String responsabilite) {
		this.responsabilite = responsabilite;
	}

	
	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getBlocageEmploye() {
		return blocageEmploye;
	}

	public void setBlocageEmploye(String blocageEmploye) {
		this.blocageEmploye = blocageEmploye;
	}



	public Utilisateur getUtilCreation() {
		return utilCreation;
	}

	public void setUtilCreation(Utilisateur utilCreation) {
		this.utilCreation = utilCreation;
	}


	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateModification() {
		return dateModification;
	}

	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}

	public TypeResEmploye getTypeResEmploye() {
		return typeResEmploye;
	}

	public void setTypeResEmploye(TypeResEmploye typeResEmploye) {
		this.typeResEmploye = typeResEmploye;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getNumCNSS() {
		return NumCNSS;
	}

	public void setNumCNSS(String numCNSS) {
		NumCNSS = numCNSS;
	}

	public Date getDateEntre() {
		return dateEntre;
	}

	public void setDateEntre(Date dateEntre) {
		this.dateEntre = dateEntre;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public boolean isSalarie() {
		return salarie;
	}

	public void setSalarie(boolean salarie) {
		this.salarie = salarie;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	
}