package bean;

public class Managers {
	
	private String jobID;
	private String name;
	private String gender;
	private String tel;
	private String officeID;
	private String pwd;
	public Managers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Managers(String jobID, String name, String gender, String tel, String officeID, String pwd) {
		super();
		this.jobID = jobID;
		this.name = name;
		this.gender = gender;
		this.tel = tel;
		this.officeID = officeID;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "Managers [jobID=" + jobID + ", name=" + name + ", gender=" + gender + ", tel=" + tel + ", officeID="
				+ officeID + ", pwd=" + pwd + "]";
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOfficeID() {
		return officeID;
	}
	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
