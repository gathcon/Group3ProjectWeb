package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Failure.findAll", query = "SELECT f FROM Failure f"),
		@NamedQuery(name = "Failure.findById", query = "SELECT f FROM Failure f where f.failureId=:failureId"), })
public class Failure extends TableRow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "failure_id")
	private int failureId;

	private String description;

	@OneToMany(mappedBy = "failure")
	private List<Base_Data> baseData;

	public Failure() {
	}

	public int getFailureId() {
		return this.failureId;
	}

	public void setFailureId(int failureId) {
		this.failureId = failureId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}