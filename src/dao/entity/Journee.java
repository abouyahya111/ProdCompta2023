package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * The persistent class for the journee database table.
 * 
 */
@Entity
@Table(name = "Journee")
@NamedQuery(name = "Journee.findAll", query = "SELECT c FROM Journee c")
public class Journee implements Serializable {
    
    
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
      
     @Column(name="DATE_JOURNEE")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateJournee;
      
     @Column(name="STATUE")
     private String statue;
     
    @Column(name="DATE_OVERTURE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateOvertute;
          
    @Column(name="DATE_FERMETURE")
    @Temporal(javax.persistence.TemporalType.DATE)
     private Date dateFermeture;
                  
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
    @JoinColumn(name="ID_Depot")
    private Depot depot;
         
    @ManyToOne
    @JoinColumn(name="ID_UTIL_maj")
    private Utilisateur utilisateurMAJ;

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDateJournee() {
        return dateJournee;
    }

    public void setDateJournee(Date dateJournee) {
        this.dateJournee = dateJournee;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public Date getDateOvertute() {
        return dateOvertute;
    }

    public void setDateOvertute(Date dateOvertute) {
        this.dateOvertute = dateOvertute;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
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

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}
    
    
    
}
