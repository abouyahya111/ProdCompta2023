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





/**
 * The persistent class for the artiles database table.
 * 
 */

@Entity
@Table(name="Sous_Famille_EnVrac")
@NamedQuery(name="SousFamilleEnVrac.findAll", query="SELECT f FROM SousFamilleEnVrac f")
public class SousFamilleEnVrac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_sous_famille")
	private SousFamilleArticlePF sousfamile;	

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier matierePremier;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public SousFamilleArticlePF getSousfamile() {
		return sousfamile;
	}

	public void setSousfamile(SousFamilleArticlePF sousfamile) {
		this.sousfamile = sousfamile;
	}
	
	
	

	

	
	
}