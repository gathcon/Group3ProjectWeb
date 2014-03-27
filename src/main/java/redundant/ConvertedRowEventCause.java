package redundant;

public class ConvertedRowEventCause extends ConvertedRow {

	private int causecode;
	private int eventid;
	private String decription;
	
	public ConvertedRowEventCause(int causecode, int eventid, String decription) {
		this.causecode = causecode;
		this.eventid = eventid;
		this.decription = decription;
	}

	public int getCausecode() {
		return causecode;
	}

	public void setCausecode(int causecode) {
		this.causecode = causecode;
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	
	
	
	
}
