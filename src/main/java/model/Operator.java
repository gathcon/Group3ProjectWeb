package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Operator.findAll", query = "SELECT o FROM Operator o"),
		@NamedQuery(name = "Operator.findById", query = "SELECT o FROM Operator o where o.id=:id"), })
public class Operator extends TableRow implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OperatorPK id;

	private String country;

	@Column(name = "operator_name")
	private String operatorName;

	@OneToMany(mappedBy = "operator")
	private List<Base_Data> baseData;

	public Operator() {
	}

	public OperatorPK getId() {
		return this.id;
	}

	public void setId(OperatorPK id) {
		this.id = id;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}