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

public class EtatVenteParFamilleParFacture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="dateFacture")
	private Date dateFacture;
	
	@Column(name="numFacture")
	private String numFacture;
	
	@Column(name="famille")
	private String famille;
	
	@Column(name="totalHT")
	private BigDecimal totalHT;
	
	@Column(name="totalHT")
	private BigDecimal totalTVA;
	
	@Column(name="totalHT")
	private BigDecimal totalTTC;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public BigDecimal getTotalHT() {
		return totalHT;
	}

	public void setTotalHT(BigDecimal totalHT) {
		this.totalHT = totalHT;
	}

	public BigDecimal getTotalTVA() {
		return totalTVA;
	}

	public void setTotalTVA(BigDecimal totalTVA) {
		this.totalTVA = totalTVA;
	}

	public BigDecimal getTotalTTC() {
		return totalTTC;
	}

	public void setTotalTTC(BigDecimal totalTTC) {
		this.totalTTC = totalTTC;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}