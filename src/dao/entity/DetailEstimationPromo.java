package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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





/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@Table(name="DETAIL_ESTIMATION_PROMO")
@NamedQuery(name="DetailEstimationPromo.findAll", query="SELECT d FROM DetailEstimationPromo d")
public class DetailEstimationPromo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_MAT_PREM")
	private MatierePremier matierePremiere;
	
	@Column(name="QUANTITE")
	private BigDecimal quantite ;
	
	@ManyToOne
	@JoinColumn(name="id_promotion")
	private Promotion promotion=new Promotion();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MatierePremier getMatierePremiere() {
		return matierePremiere;
	}

	public void setMatierePremiere(MatierePremier matierePremiere) {
		this.matierePremiere = matierePremiere;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

}