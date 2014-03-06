package model;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Event_CausePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cause_code")
	private int causeCode;

	@Column(name = "event_id")
	private int eventId;

	public Event_CausePK() {
	}

	public Event_CausePK(int cause_code, int event_id) {
		this.causeCode = cause_code;
		this.eventId = event_id;
	}

	public int getCauseCode() {
		return this.causeCode;
	}

	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Event_CausePK)) {
			return false;
		}
		Event_CausePK castOther = (Event_CausePK) other;
		return (this.causeCode == castOther.causeCode)
				&& (this.eventId == castOther.eventId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.causeCode;
		hash = hash * prime + this.eventId;

		return hash;
	}
}