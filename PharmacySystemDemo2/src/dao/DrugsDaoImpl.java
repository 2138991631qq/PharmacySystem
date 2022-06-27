package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Drugs;
import bean.Managers;
import bean.Sales;


public class DrugsDaoImpl extends DBtools implements DrugsDao {
	
	Connection conn = this.getConn();
	
	PreparedStatement pstmt = null;
	
	

	@Override
	public String[] getColumnNames(String tableName) {
		// TODO Auto-generated method stub
		
		String[] columnNames = null;
		
		try {
			String sql = "select * from "+tableName;
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();	
			ResultSetMetaData rsmd = rs.getMetaData();	//此类中含有得到所有字段名的方法
			
			columnNames = new String[rsmd.getColumnCount()];
			for(int i=0; i<columnNames.length; i++) {
				columnNames[i] = rsmd.getColumnLabel(i+1);
			}
			
			return columnNames;
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Drugs> getDrugsbySth(String nat, String belement) {
		// TODO Auto-generated method stub
		
		List<Drugs> drugsList = null;
		String sql = "select * from drugs where "+ nat +" like concat('%',?,'%')";
		if(nat.equals("GYZZ")) {
			sql = "select * from drugs where "+ nat +"=?";
		}
		
		try {
			//定义要执行的prepareStamtent类对象
			pstmt = conn.prepareStatement(sql);
			
			//给？赋值
			pstmt.setString(1, belement);
			
			ResultSet rs = pstmt.executeQuery();		
			drugsList = new ArrayList<Drugs>();
			Drugs drugs = null;
			
			//将查询到所有结果保存到drugsList
			while(rs.next()) {
				drugs = new Drugs();
				drugs.setGYZZ(rs.getString(1));
				drugs.setDname(rs.getString(2));
				drugs.setDepartment(rs.getString(3));
				drugs.setSymptom(rs.getString(4));
				drugs.setNumber(rs.getString(5));
				drugs.setStaff(rs.getString(6));
				drugsList.add(drugs);	//将这条记录添入列表
			}	
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		return drugsList;
	}

	@Override
	public int insertDrugs(Drugs drugs) {
		// TODO Auto-generated method stub
		int i=0;
		String sql = "insert into drugs(国药准字,药品名,治疗疾病科,治疗症状,购置数量,进货人员)"
				+ " values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, drugs.getGYZZ());
			pstmt.setString(2, drugs.getDname());
			pstmt.setString(3, drugs.getDepartment());
			pstmt.setString(4, drugs.getSymptom());
			pstmt.setString(5, drugs.getNumber());
			pstmt.setString(6, drugs.getStaff());
			
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int updateDrugs(Drugs drugs) {
		// TODO Auto-generated method stub
		int i=0;
		
		String sql = "update drugs "
				+ "set 药品名=?,治疗疾病科=?,治疗症状=?,购置数量=?,进货人员=?"
				+ "where 国药准字=?";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, drugs.getDname());
			pstmt.setString(2, drugs.getDepartment());
			pstmt.setString(3, drugs.getSymptom());
			pstmt.setString(4, drugs.getNumber());
			pstmt.setString(5, drugs.getStaff());
			pstmt.setString(6,drugs.getGYZZ());
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int delDrugs(String GYZZ) {
		// TODO Auto-generated method stub
		int i=0;
		
		String sql = "delete from drugs where 国药准字=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, GYZZ);
			
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	
	
	//--------------------sales窗口功能的实现-----------------------
	
	@Override
	public List<Sales> getSalesbySth(String nat, String belement) {
		// TODO Auto-generated method stub
		List<Sales> salesList = null;
		String sql = "select * from sales where "+ nat +" like concat('%',?,'%')";
		if(nat.equals("GYZZ")) {
			sql = "select * from sales where "+ nat +"=?";
		}
		
		try {
			//定义要执行的prepareStamtent类对象
			pstmt = conn.prepareStatement(sql);
			
			//给？赋值
			pstmt.setString(1, belement);
			
			ResultSet rs = pstmt.executeQuery();		
			salesList = new ArrayList<Sales>();
			Sales sales = null;
			
			//将查询到所有结果保存到drugsList
			while(rs.next()) {
				sales = new Sales();
				sales.setGYZZ(rs.getString(1));
				sales.setDname(rs.getString(2));
				sales.setPname(rs.getString(3));
				sales.setPsymptom(rs.getString(4));
				sales.setDate(rs.getString(5));
				sales.setDoctor(rs.getString(6));
				salesList.add(sales);	//将这条记录添入列表
			}	
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return salesList;
	}

	@Override
	public int insertSales(Sales sales) {
		// TODO Auto-generated method stub
		int i=0;
		String sql = "insert into sales(国药准字,药品名,病人姓名,病人症状,买药日期,开药医生姓名)"
				+ " values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sales.getGYZZ());
			pstmt.setString(2, sales.getDname());
			pstmt.setString(3, sales.getPname());
			pstmt.setString(4, sales.getPsymptom());
			pstmt.setString(5, sales.getDate());
			pstmt.setString(6, sales.getDoctor());
			
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int updateSales(Sales sales) {
		// TODO Auto-generated method stub
		int i=0;
		
		String sql = "update sales "
				+ "set 药品名=?,病人姓名=?,病人症状=?,买药日期=?,开药医生姓名=?"
				+ "where 国药准字=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sales.getDname());
			pstmt.setString(2, sales.getPname());
			pstmt.setString(3, sales.getPsymptom());
			pstmt.setString(4, sales.getDate());
			pstmt.setString(5, sales.getDoctor());
			pstmt.setString(6, sales.getGYZZ());
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public int delSales(String GYZZ) {
		// TODO Auto-generated method stub
		int i=0;
		
		String sql = "delete from sales where 国药准字=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, GYZZ);
			
			i = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public boolean login(String mID, String pwd) {
		// TODO Auto-generated method stub
		boolean flag = false;
		
		//定义要执行的sql语句
		String sql = "select * from managers where 管理员工作号=? and 管理员登录密码=?";
		
		try {
			//定义要执行的prepareStamtent类对象
			pstmt = conn.prepareStatement(sql);
			
			//给？赋值
			pstmt.setString(1, mID);
			pstmt.setString(2, pwd);

			//调用方法执行查询，将查询结果保存到ResulSet中
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public Managers getManager(String mId) {
		// TODO Auto-generated method stub
		Managers mg = new Managers();
		String sql = "select * from managers where 管理员工作号=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mg.setJobID(rs.getString(1));
				mg.setName(rs.getString(2));
				mg.setGender(rs.getString(3));
				mg.setTel(rs.getString(4));
				mg.setOfficeID(rs.getString(5));
				mg.setPwd(rs.getString(6));
			}
			
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return mg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
