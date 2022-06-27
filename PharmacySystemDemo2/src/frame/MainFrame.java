package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import bean.Drugs;
import bean.Sales;
import dao.DrugsDao;
import dao.DrugsDaoImpl;
import javax.swing.JCheckBox;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	private JTable salestable;
	DrugsDao drugsdao = new DrugsDaoImpl();	//连接数据库//
	
	CardLayout cl=null;      
	JPanel panel_2=null;        //提变量
	JPanel panel_3=null;
	JPanel panel_4=null;
	private JPanel panel_drugs;
	private JPanel panel_sales;
	
	///////////////////

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
private void initialize() {
		
		//以下完成对卡片布局的frame的初始化；
		frame = new JFrame();
		frame.setBounds(100, 100, 825, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("药店管理系统");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 38));
		label.setBounds(310, 13, 291, 43);
		frame.getContentPane().add(label);
		
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 61, 807, 516);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		JButton button = new JButton("药品管理界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_2, "s1");
			}
		});
		panel_1.add(button);
		
		JButton button_1 = new JButton("销售记录界面");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_2, "s2");
			}
		});
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("下载数据");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_2, "panel_download");
			}
		});
		panel_1.add(button_2);
		
		
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		cl=new CardLayout(0,0);
		panel_2.setLayout(cl);
		
		//-----------------------------封页面
		JPanel panel_cover_page = new JPanel();
		panel_2.add(panel_cover_page, "coverpage");
		panel_cover_page.setLayout(null);
		
		JLabel label_2 = new JLabel("封页面");
		label_2.setBounds(748, 448, 45, 18);
		panel_cover_page.add(label_2);
		
		JLabel label_3 = new JLabel("欢迎进入某某药店管理系统");
		label_3.setForeground(Color.black);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label_3.setBounds(256, 59, 300, 27);
		panel_cover_page.add(label_3);
		
		JLabel label_4 = new JLabel("请点击上方的按钮进入不同的系统界面");
		label_4.setForeground(Color.black);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(256, 212, 316, 33);
		panel_cover_page.add(label_4);
		
		
		
		//-------------------------退出系统
				JButton btnNewButton_14 = new JButton("退出系统");
				btnNewButton_14.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int a = JOptionPane.showConfirmDialog(frame, "确定要退出系统吗？");
						if(a == 0) {
							frame.setVisible(false);
					}
					}
				});
				panel_1.add(btnNewButton_14);
		
		
		//药品管理界面---------------------------------------------------------------------------------------------------
		panel_drugs = new JPanel();
		panel_2.add(panel_drugs, "s1");
		panel_drugs.setLayout(null);
		
		TableModel dataModel = getDataModel("药品名", "");
		
		textField = new JTextField();
		textField.setBounds(24, 27, 145, 24);
		panel_drugs.add(textField);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//如果搜索框的值清空，显示全部表 
				String keyWord = textField.getText();
				String bySth = "药品名";
					
				TableModel dataModel = getDataModel(bySth, keyWord);
				table.setModel(dataModel);	//传入表格
			}
			//↑上边这个中文输入没反应，英文好用
			//↓下边这个中文输入有反应，英文不太好使
			public void keyTyped(KeyEvent e) {	
				String keyWord = textField.getText();
				String bySth = "药品名";
				
				TableModel dataModel = getDataModel(bySth, keyWord);
				table.setModel(dataModel);	//传入表格
			}		
		});
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("药品名查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField.getText();
				String bySth = "药品名";
				
				//获得表格数据
				TableModel dataModel = getDataModel(bySth, keyWord);
				table.setModel(dataModel);	//传入表格
				
				int count = table.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton.setBounds(204, 26, 123, 27);
		panel_drugs.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("国药准字查询");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField.getText();
				String bySth = "国药准字";
				
				//获得表格数据
				TableModel dataModel = getDataModel(bySth, keyWord);
				table.setModel(dataModel);	//传入表格
				
				int count = table.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton_1.setBounds(341, 26, 123, 27);
		panel_drugs.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("疾病科查询");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField.getText();
				String bySth = "治疗疾病科";
				
				//获得表格数据
				TableModel dataModel = getDataModel(bySth, keyWord);
				table.setModel(dataModel);	//传入表格
				
				int count = table.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton_2.setBounds(478, 26, 123, 27);
		panel_drugs.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("新添加药品");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDrugsFrame af = new AddDrugsFrame();
			}
		});
		btnNewButton_3.setBounds(665, 40, 113, 27);
		panel_drugs.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("修改药品");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int frow = table.getSelectedRow();	//获得第一个选中行的索引
				
				if(frow == -1) {
					JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
					
				}else {
					//String[]中存入表格选中行的数据
					String[] sendData = new String[table.getColumnCount()];				
					for(int i=0; i<sendData.length; i++) {
						sendData[i] = table.getValueAt(frow, i)+"";
					}
					
					//打开修改页面，并传入信息
					UpdateDrugsFrame uf = new UpdateDrugsFrame(sendData);
					
				}
			}
		});
		btnNewButton_4.setBounds(665, 109, 113, 27);
		panel_drugs.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("删除药品");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int frow = table.getSelectedRow();	//获得第一个选中行的索引
				if(frow == -1) {
					JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
				}else {
					int b = JOptionPane.showConfirmDialog(frame, "确定删除吗？");
					if(b == 0) {
						//获得GYZZ
						String status = table.getValueAt(frow, table.getColumnCount()-1)+"";//获得在馆状态
						
						if(frow == -1) {
							JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
							
						}else {
							String GYZZ= table.getValueAt(frow, 0)+"";					
							drugsdao.delDrugs(GYZZ);	
							JOptionPane.showMessageDialog(frame, "删除成功！");
							
							//更新表格
							DefaultTableModel dtm = (DefaultTableModel) table.getModel();
							dtm.removeRow(frow);
							table.setModel(dtm);
						}								
					}
				}				
			}
		});
		btnNewButton_5.setBounds(665, 180, 113, 27);
		panel_drugs.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("更新表格");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//刷新表格
				TableModel dataModel = getDataModel("药品名", "");
				table.setModel(dataModel);	//传入表格
			}
		});
		btnNewButton_6.setBounds(665, 353, 113, 27);
		panel_drugs.add(btnNewButton_6);
		
		table = new JTable();
		tableInit(table);	//初始化表格样式大小
		table.setBounds(64, 137, 541, 281);
		panel_drugs.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(24, 70, 627, 334);
		panel_drugs.add(scrollPane);
		table.setModel(dataModel);
		
		//------------------------------------------------------------------------------------------------------------
		//销售记录界面
		panel_sales = new JPanel();
		panel_2.add(panel_sales, "s2");
		panel_sales.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(24, 27, 145, 24);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//如果搜索框的值清空，显示全部表 
				String keyWord = textField_2.getText();
				String bySth = "药品名";
					
				TableModel dataModel = getSalesDataModel(bySth, keyWord);
				salestable.setModel(dataModel);	//传入表格
			}
			//↑上边这个中文输入没反应，英文好用
			//↓下边这个中文输入有反应，英文不太好使
			public void keyTyped(KeyEvent e) {	
				String keyWord = textField_2.getText();
				String bySth = "药品名";
				
				TableModel dataModel = getSalesDataModel(bySth, keyWord);
				salestable.setModel(dataModel);	//传入表格
			}		
		});
		panel_sales.add(textField_2);
		textField.setColumns(10);
		
		JLabel label_1_1 = new JLabel("药品管理界面");
		label_1_1.setBounds(703, 434, 90, 18);
		panel_drugs.add(label_1_1);
		
		JButton btnNewButton_7 = new JButton("药品名查询");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField_2.getText();
				String bySth = "药品名";
				
				//获得表格数据
				TableModel dataModel = getSalesDataModel(bySth, keyWord);
				salestable.setModel(dataModel);	//传入表格
				
				int count = salestable.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton_7.setBounds(204, 26, 123, 27);
		panel_sales.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("国药准字查询");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField_2.getText();
				String bySth = "国药准字";
				
				//获得表格数据
				TableModel dataModel = getSalesDataModel(bySth, keyWord);
				salestable.setModel(dataModel);	//传入表格
				
				int count = salestable.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton_8.setBounds(341, 26, 123, 27);
		panel_sales.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("病人姓名查询");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框信息
				String keyWord = textField_2.getText();
				String bySth = "病人姓名";
				
				//获得表格数据
				TableModel dataModel = getSalesDataModel(bySth, keyWord);
				salestable.setModel(dataModel);	//传入表格
				
				int count = salestable.getRowCount();
				if(count == 0) {
					JOptionPane.showMessageDialog(frame, "没有查询到数据");
				}
			}
		});
		btnNewButton_9.setBounds(478, 26, 123, 27);
		panel_sales.add(btnNewButton_9);
		
		
		
		
		////////////////
		JButton btnNewButton_10 = new JButton("新添加销售记录");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSalesFrame af = new AddSalesFrame();
			}
		});
		btnNewButton_10.setBounds(665, 40, 137, 27);
		panel_sales.add(btnNewButton_10);
		
		
		/////
		JButton btnNewButton_11 = new JButton("修改销售记录");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int frow = salestable.getSelectedRow();	//获得第一个选中行的索引
				
				if(frow == -1) {
					JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
					
				}else {
					//String[]中存入表格选中行的数据
					String[] sendData = new String[salestable.getColumnCount()];				
					for(int i=0; i<sendData.length; i++) {
						sendData[i] = salestable.getValueAt(frow, i)+"";
					}
					
					//打开修改页面，并传入信息
					UpdateSalesFrame uf = new UpdateSalesFrame(sendData);
					
				}
			}
		});
		btnNewButton_11.setBounds(665, 109, 137, 27);
		panel_sales.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("删除销售记录");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int frow = salestable.getSelectedRow();	//获得第一个选中行的索引
				if(frow == -1) {
					JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
				}else {
					int b = JOptionPane.showConfirmDialog(frame, "确定删除吗？");
					if(b == 0) {
						//获得GYZZ
						String status = salestable.getValueAt(frow, salestable.getColumnCount()-1)+"";//获得在馆状态
						
						if(frow == -1) {
							JOptionPane.showMessageDialog(frame, "请先单击选择一条记录");
							
						}else {
							String GYZZ= salestable.getValueAt(frow, 0)+"";					
							drugsdao.delSales(GYZZ);	
							JOptionPane.showMessageDialog(frame, "删除成功！");
							
							//更新表格
							DefaultTableModel dtm = (DefaultTableModel) salestable.getModel();
							dtm.removeRow(frow);
							salestable.setModel(dtm);
						}								
					}
				}				
			}
		});
		btnNewButton_12.setBounds(665, 180, 137, 27);
		panel_sales.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("更新表格");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//刷新表格
				TableModel dataModel = getSalesDataModel("药品名", "");
				salestable.setModel(dataModel);	//传入表格
			}
		});
		btnNewButton_13.setBounds(665, 353, 137, 27);
		panel_sales.add(btnNewButton_13);
		
				
				
		TableModel dataModel2 = getSalesDataModel("药品名", "");
		
		salestable = new JTable();
		tableInit(salestable);	//初始化表格样式大小
		salestable.setBounds(64, 137, 541, 281);
		
		JScrollPane scrollPane2 = new JScrollPane(salestable);
		scrollPane2.setBounds(24, 70, 627, 334);
		panel_sales.add(scrollPane2);
		salestable.setModel(dataModel2);
		
		JLabel label_1 = new JLabel("销售记录界面");
		label_1.setBounds(703, 448, 90, 18);
		panel_sales.add(label_1);
		
		//--------------------------------------------下载数据窗口
		
		
		JPanel panel_download = new JPanel();
		panel_2.add(panel_download, "panel_download");
		panel_download.setLayout(null);
		
		JButton button_download_drugs = new JButton("下载药店药品信息");
		button_download_drugs.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				download1();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				button_download_drugs.setBounds(272, 84, 204, 27);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				button_download_drugs.setBounds(210, 84, 306, 40);
			}
		});
		button_download_drugs.setBounds(272, 84, 204, 27);
		button_download_drugs.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel_download.add(button_download_drugs);
		
		
		//-----------------------------------
		
		JButton button_download_sales = new JButton("下载销售记录信息");
		button_download_sales.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				download2();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				button_download_sales.setBounds(272, 197, 204, 27);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根
				button_download_sales.setBounds(210, 186, 306, 40);
			}
		});
		button_download_sales.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_download_sales.setBounds(272, 197, 204, 27);
		panel_download.add(button_download_sales);
		
		
				
				
				
				
				
				
				
				
	}
	//--------------------------------以上为initialize方法体------------------
	
	//------download页面的下载数据的方法体；
	protected void download1() {
	// TODO 自动生成的方法存根
	try {
		//选择文件夹		
		FileDialog fd = new FileDialog(frame, "另存为", FileDialog.SAVE);
		fd.setFile("药店药品信息表.txt");			
		fd.setVisible(true);
		
		if(fd.getDirectory()!=null) {
			String url = fd.getDirectory()+fd.getFile();
			
			FileWriter fw = new FileWriter(url,false);	//字符输出流
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());  //获得当前时间
			fw.write("更新日期："+ts.toString()+"\n\n");
			
			List<Drugs> drugs = drugsdao.getDrugsbySth("药品名", "");	//查询所有药品	
			for(int i=0; i<drugs.size(); i++) {
				fw.write(drugs.get(i).toString()+"\n");	//写入文件
			}
			
			JOptionPane.showMessageDialog(frame, "下载成功！");
			
			fw.flush();
			fw.close();				
		}		
		
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}
	
	protected void download2() {
		// TODO 自动生成的方法存根
		try {
			//选择文件夹		
			FileDialog fd = new FileDialog(frame, "另存为", FileDialog.SAVE);
			fd.setFile("销售记录信息表.txt");			
			fd.setVisible(true);
			
			if(fd.getDirectory()!=null) {
				String url = fd.getDirectory()+fd.getFile();
				
				FileWriter fw = new FileWriter(url,false);
				
				Timestamp ts = new Timestamp(System.currentTimeMillis());  //获得当前时间
				fw.write("更新日期："+ts.toString()+"\n\n");
				
				List<Sales> sales = drugsdao.getSalesbySth("药品名", "");		
				for(int i=0; i<sales.size(); i++) {
					fw.write(sales.get(i).toString()+"\n");	//写入文件
				}
				
				JOptionPane.showMessageDialog(frame, "下载成功！");
				
				fw.flush();
				fw.close();				
			}		
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}



	protected TableModel getSalesDataModel(String bySth, String keyWord) {
	
	String[] columnNames = drugsdao.getColumnNames("sales");
	
	//获得查询结果的list列表，转换为二维数组，做表中数据
			List<Sales> salesList =  drugsdao.getSalesbySth(bySth, keyWord);
			int row = salesList.size();			//获得行数，即数据个数
			int column = columnNames.length;	//获得列数，即字段个数
	
			String[][] data = new String[row][column];
			for(int i=0; i<salesList.size(); i++) {
				data[i][0] = salesList.get(i).getGYZZ();
				data[i][1] = salesList.get(i).getDname();
				data[i][2] = salesList.get(i).getPname();
				data[i][3] = salesList.get(i).getPsymptom();
				data[i][4] = salesList.get(i).getDate();
				data[i][5] = salesList.get(i).getDoctor();
			}
	TableModel dataModel = new DefaultTableModel(data, columnNames);	//建立表格数据模型
	return dataModel;
}

////

	protected TableModel getDataModel(String bySth, String keyWord) {
	
	String[] columnNames = drugsdao.getColumnNames("drugs");
	
	//获得查询结果的list列表，转换为二维数组，做表中数据
			List<Drugs> drugsList =  drugsdao.getDrugsbySth(bySth,keyWord);
			int row = drugsList.size();			//获得行数，即数据个数
			int column = columnNames.length;	//获得列数，即字段个数
	
			String[][] data = new String[row][column];
			for(int i=0; i<drugsList.size(); i++) {
				data[i][0] = drugsList.get(i).getGYZZ();
				data[i][1] = drugsList.get(i).getDname();
				data[i][2] = drugsList.get(i).getDepartment();
				data[i][3] = drugsList.get(i).getSymptom();
				data[i][4] = drugsList.get(i).getNumber();
				data[i][5] = drugsList.get(i).getStaff();
			}
	TableModel dataModel = new DefaultTableModel(data, columnNames);	//建立表格数据模型
	return dataModel;
}

//更改表格样式
	private void tableInit(JTable tb) {
	
	//单元格居中
	DefaultTableCellRenderer cr = new DefaultTableCellRenderer(); 
	cr.setHorizontalAlignment(JLabel.CENTER);
	tb.setDefaultRenderer(Object.class, cr);

	//调整行高
	tb.setRowHeight(60);
	
	//调整字体大小
	tb.setFont(new Font("微软雅黑", 0, 20));
	tb.getTableHeader().setFont(new Font("微软雅黑", 0, 20));
	
	//设置各列合适宽度
	//tb.getColumnModel().getColumn(0).setPreferredWidth(120);;
	
}
}
