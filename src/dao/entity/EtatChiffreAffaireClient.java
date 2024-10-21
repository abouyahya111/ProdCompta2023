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





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity

public class EtatChiffreAffaireClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	 
	private String client;
	
	private String ice;
	
 
	private BigDecimal montantHT ;

	
	 
	private BigDecimal montantTTC ;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getClient() {
		return client;
	}



	public void setClient(String client) {
		this.client = client;
	}



	public String getIce() {
		return ice;
	}



	public void setIce(String ice) {
		this.ice = ice;
	}



	public BigDecimal getMontantHT() {
		return montantHT;
	}



	public void setMontantHT(BigDecimal montantHT) {
		this.montantHT = montantHT;
	}



	public BigDecimal getMontantTTC() {
		return montantTTC;
	}



	public void setMontantTTC(BigDecimal montantTTC) {
		this.montantTTC = montantTTC;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	 

}