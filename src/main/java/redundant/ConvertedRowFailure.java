package redundant;

public class ConvertedRowFailure extends ConvertedRow {
	
	private int failureid;
	private String description;
	
	public ConvertedRowFailure(int failureid, String description) {
	
		this.failureid = failureid;
		this.description = description;
	}

	public int getFailureid() {
		return failureid;
	}

	public void setFailureid(int failureid) {
		this.failureid = failureid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
