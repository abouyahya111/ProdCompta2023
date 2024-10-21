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
@NamedQuery(name="EtatMontantFacturePF.findAll", query="SELECT f FROM EtatMontantFacturePF f")
public class EtatMontantFacturePF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="MODE_REGLEMENT")
	private String modereglement;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantht;
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montanttva ;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantttc ;

	@Column(name="MONTANT_TIMBRE")
	private BigDecimal montanttimbre ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getMontantht() {
		return montantht;
	}

	public void setMontantht(BigDecimal montantht) {
		this.montantht = montantht;
	}

	public BigDecimal getMontanttva() {
		return montanttva;
	}

	public void setMontanttva(BigDecimal montanttva) {
		this.montanttva = montanttva;
	}

	public BigDecimal getMontantttc() {
		return montantttc;
	}

	public void setMontantttc(BigDecimal montantttc) {
		this.montantttc = montantttc;
	}

	public BigDecimal getMontanttimbre() {
		return montanttimbre;
	}

	public void setMontanttimbre(BigDecimal montanttimbre) {
		this.montanttimbre = montanttimbre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getModereglement() {
		return modereglement;
	}

	public void setModereglement(String modereglement) {
		this.modereglement = modereglement;
	}
	
	
	

}