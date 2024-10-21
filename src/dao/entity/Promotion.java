package dao.entity;

import java.io.Serializable;
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





/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	@Column(name="CODE")
	private String code;
	
	@ManyToOne
	@JoinColumn(name="ID_ARTICLE")
	private Articles article;
	
	@Column(name="ACTIF")
	private boolean actif ;
	
	
	//bi-directional many-to-one association to MatierePromiere
	@OneToMany(mappedBy="promotion")
	//@OneToMany(cascade = CascadeType.ALL, mappedBy="promotion")
	private List<DetailEstimationPromo> detailEstimationPromo=new ArrayList<>();


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


	public Articles getArticle() {
		return article;
	}


	public void setArticle(Articles article) {
		this.article = article;
	}


	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public List<DetailEstimationPromo> getDetailEstimationPromo() {
		return detailEstimationPromo;
	}


	public void setDetailEstimationPromo(
			List<DetailEstimationPromo> detailEstimationPromo) {
		this.detailEstimationPromo = detailEstimationPromo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
	
}