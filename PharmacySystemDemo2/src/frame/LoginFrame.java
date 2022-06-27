package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bean.Managers;
import dao.DrugsDao;
import dao.DrugsDaoImpl;

public class LoginFrame {

	private JFrame frame;
	private JTextField idText;
	private JPasswordField pwdText;
	
	DrugsDao drugsdao = new DrugsDaoImpl();	//连接数据库//
	
	//访问权限提升区
	JButton btnLogin;
	JCheckBox checkBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		
		
		frame = new JFrame();
		
		frame.setTitle("\u7434\u5C9B\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		frame.setBounds(100, 100, 548, 621);
		frame.setLocationRelativeTo(null);		//默认居中显示
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.setVisible(true);
		
		JLabel title = new JLabel("药店管理系统");
		title.setForeground(Color.BLACK);
		title.setFont(new Font("微软雅黑", Font.PLAIN, 45));
		title.setBounds(118, 57, 281, 60);
		frame.getContentPane().add(title);
		
		JLabel id = new JLabel("账号：");
		id.setForeground(Color.BLACK);
		id.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		id.setBounds(106, 205, 90, 37);
		frame.getContentPane().add(id);
		
		idText = new JTextField();
		//键盘监听id文本框
		idText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				///按下回车键登录
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnLogin.doClick();	//“按下”按钮
				}
				
				//双框有值时，按钮可用，空时按钮不可用
				if(!idText.getText().equals("") && !pwdText.getText().equals("")) {
					btnLogin.setEnabled(true);
				}else {
					btnLogin.setEnabled(false);
				}				
			}			
		});
		idText.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		idText.setBounds(190, 205, 249, 37);
		frame.getContentPane().add(idText);
		idText.setColumns(10);
		
		JLabel pwd = new JLabel("密码：");
		pwd.setForeground(Color.BLACK);
		pwd.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		pwd.setBounds(106, 313, 90, 37);
		frame.getContentPane().add(pwd);
		
		pwdText = new JPasswordField();
		//键盘监听密码文本框
		pwdText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				///按下回车键登录
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnLogin.doClick();	//“按下”按钮
				}
				
				//双框有值时，按钮可用，空时按钮不可用
				if(!idText.getText().equals("") && !pwdText.getText().equals("")) {
					btnLogin.setEnabled(true);
				}else {
					btnLogin.setEnabled(false);
				}
			}			
		});
		pwdText.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		pwdText.setBounds(190, 313, 249, 37);
		frame.getContentPane().add(pwdText);	
		
		
		//创建login.txt文件，初始化时读取login.txt文件中的账号和密码
		File file = new File("login.txt");
		FileInputStream fis=null;
		try {
			file.createNewFile();
			fis=new FileInputStream(file);
			InputStreamReader reader=new InputStreamReader(fis);		
			LineNumberReader lnr=new LineNumberReader(reader);
			lnr.setLineNumber(0);//设置文件起始号
			String str=null;
					
			while((str=lnr.readLine())!=null) {	
				if(lnr.getLineNumber()==1) {
					idText.setText(str);
				}
				if((lnr.getLineNumber()==2)) {
					pwdText.setText(str);
				}						
			}
		} catch (FileNotFoundException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
		} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
	    }	
		
		btnLogin = new JButton("登录");
		btnLogin.setBackground(SystemColor.activeCaption);
		btnLogin.setEnabled(false);	//初始按钮不可用
		if(!idText.getText().equals("")&&!pwdText.getText().equals("")) {
			btnLogin.setEnabled(true);
		}
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得文本框里的值
				String mID = idText.getText();
				String pwd = pwdText.getText();
				
				boolean f = drugsdao.login(mID, pwd);
				if(f) {
					JOptionPane.showMessageDialog(frame, "登陆成功！欢迎！");
					
					//登陆成功后将账号密码存入文件login.txt中			
					FileOutputStream fos=null;			
					try {
						fos=new FileOutputStream("login.txt");
						if(checkBox.isSelected()) {	
							byte[] str1=mID.getBytes();
							byte[] str2=pwd.getBytes();
							
							//获得登录管理员信息
							Managers mg = new DrugsDaoImpl().getManager(mID);
//							PersonalCenterFrame.manager = mg;
		
							fos.write(str1);
							fos.write("\r\n".getBytes());
							fos.write(str2);					
						}else {
							fos.write("".getBytes());
						}
					} catch (FileNotFoundException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} finally {
						try {
							fos.close();
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}	
					}	
					
					//页面跳转
					MainFrame mf = new MainFrame();
					//关闭原页面
					frame.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(frame, "登陆失败！账号或密码错误！");
					//密码清空
					pwdText.setText("");
					btnLogin.setEnabled(false);
				}
			}
		});
		btnLogin.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBounds(190, 466, 147, 37);
		frame.getContentPane().add(btnLogin);
		
		checkBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		checkBox.setForeground(Color.BLACK);
		checkBox.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		checkBox.setBounds(169, 381, 195, 37);
		checkBox.setOpaque(false);	//透明
		checkBox.setFocusPainted(false); 	//去除焦点框
		checkBox.setSelected(true);
		frame.getContentPane().add(checkBox);
	}
}
