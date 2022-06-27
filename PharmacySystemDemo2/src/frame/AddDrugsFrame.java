package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bean.Drugs;

import dao.DrugsDaoImpl;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class AddDrugsFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDrugsFrame window = new AddDrugsFrame();
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
	public AddDrugsFrame() {
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
		
		JLabel label = new JLabel("新添加药品");
		label.setBounds(159, 31, 86, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("国药准字：");
		label_1.setBounds(54, 88, 86, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("药品名：");
		label_2.setBounds(54, 139, 72, 18);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("治疗疾病科：");
		label_3.setBounds(54, 192, 95, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("治疗症状：");
		label_4.setBounds(54, 251, 75, 18);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("购置数量：");
		label_5.setBounds(54, 358, 80, 18);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("进货人员：");
		label_6.setBounds(54, 406, 80, 18);
		frame.getContentPane().add(label_6);
		
		textField = new JTextField();
		textField.setBounds(145, 85, 147, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 136, 147, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"呼吸内科", "神经内科", "心内科", "内分泌科", "骨科", "普外科", "神经外科", "心胸外科", "脑外科", "儿科", "妇产科", "肿瘤科"}));
		comboBox.setBounds(145, 189, 147, 24);
		frame.getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(136, 249, 156, 85);
		frame.getContentPane().add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(136, 249, 156, 85);
		frame.getContentPane().add(scrollPane);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 355, 86, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_7 = new JLabel("盒");
		label_7.setBounds(224, 358, 24, 18);
		frame.getContentPane().add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 403, 155, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Drugs drugs = new Drugs();

				JTextField[] jtf= {textField,textField_1,textField_2,textField_3,
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
					String department = comboBox.getSelectedItem()+"";//获得comboBox当前选中值
					String symptom = textArea.getText();
					String number = textField_2.getText();
					String staff = textField_3.getText();
					
					
					drugs.setGYZZ(GYZZ);
					drugs.setDname(Dname);
					drugs.setDepartment(department);
					drugs.setSymptom(symptom);
					drugs.setNumber(number);
					drugs.setStaff(staff);

					
					//检查isbn是否存在
					List<Drugs> check = new DrugsDaoImpl().getDrugsbySth("国药准字", GYZZ);
					if(check.size()!=0) {
						JOptionPane.showMessageDialog(frame, "该国药准字已存在！");
						textField.setBorder(BorderFactory.createLineBorder(Color.RED));  //标红
					}else {
						int j = new DrugsDaoImpl().insertDrugs(drugs);
						
						if(j!=0) {
							JOptionPane.showMessageDialog(frame, "添加成功！");
							//成功后关闭页面
							frame.setVisible(false);
						}
					}				
				}			
			}
		});
		button.setBounds(54, 465, 113, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textArea.setText("");
			}
		});
		button_1.setBounds(201, 465, 113, 27);
		frame.getContentPane().add(button_1);
		
		
	}
}
