package dao;

import java.util.List;

import bean.Drugs;
import bean.Managers;
import bean.Sales;


public interface DrugsDao {
	
	public String[] getColumnNames(String tableName);
	
	public List<Drugs> getDrugsbySth(String bySth, String keyWord);
	
	public int insertDrugs(Drugs drugs);
	
	public int updateDrugs(Drugs drugs);

	public int delDrugs(String GYZZ);
	
	
	//销售记录页面的实现
	public List<Sales> getSalesbySth(String bySth, String keyWord);
	
	public int insertSales(Sales sales);
	
	public int updateSales(Sales sales);

	public int delSales(String GYZZ);
	
	
	//登录界面的实现
	public boolean login(String sno, String pwd);
	
	public Managers getManager(String mId);
}
