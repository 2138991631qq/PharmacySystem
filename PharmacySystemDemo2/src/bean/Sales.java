package bean;

public class Sales {
	
	private String GYZZ;
	private String dname;
	private String pname;
	private String psymptom;
	private String date;
	private String doctor;
	
	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sales(String gYZZ, String dname, String pname, String psymptom, String date, String doctor) {
		super();
		GYZZ = gYZZ;
		this.dname = dname;
		this.pname = pname;
		this.psymptom = psymptom;
		this.date = date;
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Sales [GYZZ=" + GYZZ + ", dname=" + dname + ", pname=" + pname + ", psymptom=" + psymptom + ", date="
				+ date + ", doctor=" + doctor + "]";
	}

	public String getGYZZ() {
		return GYZZ;
	}

	public void setGYZZ(String gYZZ) {
		GYZZ = gYZZ;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPsymptom() {
		return psymptom;
	}

	public void setPsymptom(String psymptom) {
		this.psymptom = psymptom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	

}
