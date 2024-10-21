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
import javax.persistence.Transient;

/**
 *
 * @author Hp
 */
@Entity
@Table(name="COMPTE_CLIENT")
@NamedQuery(name="CompteClient.findAll", query="SELECT c FROM CompteClient c")
public class CompteClient implements Serializable {
      private static final long serialVersionUID = 1L;
      
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private int id;
    
    @Column(name="CODE")
    private String code;
    
    @Column(name="LIBELLE")
    private String libelle;
    
    @Column(name="SOLDE")
    private BigDecimal solde;

    @Column(name="DATE_CREATION")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
      
    @Column(name="DATE_MAJ")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateMaj;
     
    @ManyToOne
    @JoinColumn(name="ID_UTIL_CREATION")
    private Utilisateur utilisateurCreation;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;
    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="compteClient")
    private List<DetailCompteClient> detailCompteClient;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public BigDecimal getSolde() {
		return solde;
	}


	public void setSolde(BigDecimal solde) {
		this.solde = solde;
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


	public List<DetailCompteClient> getDetailCompteClient() {
		return detailCompteClient;
	}


	public void setDetailCompteClient(List<DetailCompteClient> detailCompteClient) {
		this.detailCompteClient = detailCompteClient;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


  
  
  
  
  
  
  
  
  
}
