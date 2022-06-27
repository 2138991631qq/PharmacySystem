package bean;

public class Drugs {

	private String GYZZ;
	private String dname;
	private String department;
	private String symptom;
	private String number;
	private String staff;
	public Drugs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Drugs(String gYZZ, String dname, String department, String symptom, String number, String staff) {
		super();
		GYZZ = gYZZ;
		this.dname = dname;
		this.department = department;
		this.symptom = symptom;
		this.number = number;
		this.staff = staff;
	}
	@Override
	public String toString() {
		return "Drugs [GYZZ=" + GYZZ + ", dname=" + dname + ", department=" + department + ", symptom=" + symptom
				+ ", number=" + number + ", staff=" + staff + "]";
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	
	
	
}
