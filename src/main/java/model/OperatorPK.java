package model;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class OperatorPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int mcc;

	private int mnc;

	public OperatorPK() {
	}

	public OperatorPK(int mcc, int mnc) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
	}

	public int getMcc() {
		return this.mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return this.mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OperatorPK)) {
			return false;
		}
		OperatorPK castOther = (OperatorPK) other;
		return (this.mcc == castOther.mcc) && (this.mnc == castOther.mnc);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mcc;
		hash = hash * prime + this.mnc;

		return hash;
	}
}