package convertedRow;

public class ConvertedRowUserEquipment extends ConvertedRow{
	
	private int tac;
	private String marketingname;
	private String manufacturer;
	private String accesscapability;
	private String model;
	private String vendorname;
	private String uetype;
	private String os;
	private String input_mode;
	
	public ConvertedRowUserEquipment(int tac, String marketingname,
			String manufacturer, String accesscapability, String model,
			String vendorname, String uetype, String os, String input_mode) {
		
		this.tac = tac;
		this.marketingname = marketingname;
		this.manufacturer = manufacturer;
		this.accesscapability = accesscapability;
		this.model = model;
		this.vendorname = vendorname;
		this.uetype = uetype;
		this.os = os;
		this.input_mode = input_mode;
	}

	public int getTac() {
		return tac;
	}

	public void setTac(int tac) {
		this.tac = tac;
	}

	public String getMarketingname() {
		return marketingname;
	}

	public void setMarketingname(String marketingname) {
		this.marketingname = marketingname;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccesscapability() {
		return accesscapability;
	}

	public void setAccesscapability(String accesscapability) {
		this.accesscapability = accesscapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public String getUetype() {
		return uetype;
	}

	public void setUetype(String uetype) {
		this.uetype = uetype;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInput_mode() {
		return input_mode;
	}

	public void setInput_mode(String input_mode) {
		this.input_mode = input_mode;
	}

}
