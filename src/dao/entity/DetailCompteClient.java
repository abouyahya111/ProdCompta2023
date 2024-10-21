/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "DETAIL_COMPTE_CLIENT")
@NamedQuery(name = "DetailCompteClient.findAll", query = "SELECT u FROM DetailCompteClient u")
public class DetailCompteClient implements Serializable {
       private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    @Column(name="DATE_OPERATION")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateOperation;
     
    @Column(name="DESIGNATION")
    private String designation ;
    
    
     
    @Column(name="MONTANT_CREDIT")
    private BigDecimal montantCredit;
    
    @Column(name="MONTANT_DEBIT")
    private BigDecimal montantDebit;
    
    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
    
    @ManyToOne
    @JoinColumn(name="ID_FACTURE")
    private FacturePF facturepf;
    
    @ManyToOne
    @JoinColumn(name="ID_FACTURE_AVOIR")
    private FactureAvoirClientPF factureAvoirClientpf;
    
    
    @ManyToOne
    @JoinColumn(name="ID_FACTURE_AutresVente")
    private FactureAutresVente factureAutresVente;
    
    
    @ManyToOne
    @JoinColumn(name="ID_Fournisseur")
    private Client fournisseur;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;

    @ManyToOne
    @JoinColumn(name = "ID_COMPTE_Client")
    private CompteClient compteClient;
    
    //@OneToMany(cascade = CascadeType.ALL,mappedBy="detailCompteClient")
    // private List<Cheque> cheque;
    
    
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public BigDecimal getMontantCredit() {
		return montantCredit;
	}

	public void setMontantCredit(BigDecimal montantCredit) {
		this.montantCredit = montantCredit;
	}

	public BigDecimal getMontantDebit() {
		return montantDebit;
	}

	public void setMontantDebit(BigDecimal montantDebit) {
		this.montantDebit = montantDebit;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateMaj() {
		return dateMaj;
	}

	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}

	public Utilisateur getUtilisateurCreation() {
		return utilisateurCreation;
	}

	public void setUtilisateurCreation(Utilisateur utilisateurCreation) {
		this.utilisateurCreation = utilisateurCreation;
	}

	public Utilisateur getUtilisateurMAJ() {
		return utilisateurMAJ;
	}

	public void setUtilisateurMAJ(Utilisateur utilisateurMAJ) {
		this.utilisateurMAJ = utilisateurMAJ;
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

	public FacturePF getFacturepf() {
		return facturepf;
	}

	public void setFacturepf(FacturePF facturepf) {
		this.facturepf = facturepf;
	}

	public Client getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Client fournisseur) {
		this.fournisseur = fournisseur;
	}

	public FactureAvoirClientPF getFactureAvoirClientpf() {
		return factureAvoirClientpf;
	}

	public void setFactureAvoirClientpf(FactureAvoirClientPF factureAvoirClientpf) {
		this.factureAvoirClientpf = factureAvoirClientpf;
	}

	public FactureAutresVente getFactureAutresVente() {
		return factureAutresVente;
	}

	public void setFactureAutresVente(FactureAutresVente factureAutresVente) {
		this.factureAutresVente = factureAutresVente;
	}

   
   
       
   

    
}
