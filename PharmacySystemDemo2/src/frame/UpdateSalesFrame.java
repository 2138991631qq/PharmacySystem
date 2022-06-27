package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bean.Drugs;
import bean.Sales;
import dao.DrugsDaoImpl;

public class UpdateSalesFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	String[] selectedData = null;	//接收表格中选中的数据
	

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateSalesFrame window = new UpdateSalesFrame();
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
	public UpdateSalesFrame() {
		initialize();
	}
	
	public UpdateSalesFrame(String[] receiveData) {
		this.selectedData = new String[100];
		for(int i=0; i<receiveData.length; i++) {
			this.selectedData[i] = receiveData[i];
		}
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 609);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		frame.setVisible(true);
		
		JLabel label = new JLabel("更新药品销售记录");
		label.setBounds(145, 31, 147, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("国药准字：");
		label_1.setBounds(54, 88, 86, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("药品名：");
		label_2.setBounds(54, 139, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("病人姓名：");
		label_3.setBounds(54, 192, 95, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("病人症状：");
		label_4.setBounds(54, 251, 75, 18);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("买药日期：");
		label_5.setBounds(54, 358, 80, 18);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("开药医生姓名：");
		label_6.setBounds(54, 407, 105, 18);
		frame.getContentPane().add(label_6);
		
		textField = new JTextField();
		textField.setBounds(145, 85, 147, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(selectedData[0]);
		textField.disable();	//编号不可修改
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 136, 147, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(selectedData[1]);
		
		textField_4 = new JTextField();
		textField_4.setBounds(145, 192, 147, 24);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(selectedData[2]);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(136, 249, 156, 85);
		frame.getContentPane().add(textArea);
		textArea.setText(selectedData[3]);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(136, 249, 156, 85);
		frame.getContentPane().add(scrollPane);
		
		textField_2 = new JTextField();
		textField_2.setBounds(145, 355, 147, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(selectedData[4]);
		
		textField_3 = new JTextField();
		textField_3.setBounds(159, 404, 136, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(selectedData[5]);
		
		JButton button = new JButton("修改");
		button.setBounds(54, 465, 113, 27);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d =JOptionPane.showConfirmDialog(frame, "确认修改吗？");
				if(d == 0) {
					Sales sales = new Sales();

					JTextField[] jtf= {textField,textField_1,textField_4,textField_2,textField_3,
					};
					
					//检测空的文本框，将空的标红,并提示输入完整
					int i = 0;
					for(; i<jtf.length; i++) {
						if(jtf[i].getText().equals("")) {
							jtf[i].setBorder(BorderFactory.createLineBorder(Color.RED)); //将边框标红
							break;
						}
					}
					if(i == jtf.length) {
						String GYZZ = textField.getText();
						String Dname = textField_1.getText();
						String Pname = textField_4.getText();
						String Psymptom = textArea.getText();
						String date = textField_2.getText();
						String doctor = textField_3.getText();
						
						sales.setGYZZ(GYZZ);
						sales.setDname(Dname);
						sales.setPname(Pname);
						sales.setPsymptom(Psymptom);
						sales.setDate(date);
						sales.setDoctor(doctor);
						
						int j = new DrugsDaoImpl().updateSales(sales);
						
						if(j!=0) {
							JOptionPane.showMessageDialog(frame, "修改成功！");
							//成功后关闭页面
							frame.setVisible(false);
						}
					}
				}
			}
		});
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(selectedData[0]);
				textField_1.setText(selectedData[1]);
				textField_4.setText(selectedData[2]);
				textArea.setText(selectedData[3]);
				textField_2.setText(selectedData[4]);
				textField_3.setText(selectedData[5]);
			}
		});
		button_1.setBounds(201, 465, 113, 27);
		frame.getContentPane().add(button_1);
		
		
		
		
		
		
		
		
		
		
	}
	//---------------------------以上为initialize的方法体---------------------

}
