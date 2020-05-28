package qmpro;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class ProjectGui {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectGui window = new ProjectGui();
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
	public ProjectGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JLabel lblName = new JLabel("Automation Tool for OMNI QMPRO");
		lblName.setBounds(95, 11, 446, 34);
		lblName.setFont(new Font("Serif", Font.BOLD, 28));
		frame.getContentPane().add(lblName);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(312, 687, 89, 23);
		btnClear.setFont(new Font("Serif", Font.BOLD, 20));
		frame.getContentPane().add(btnClear);
		
		JLabel lblAll = new JLabel("Run All Test cases");
		lblAll.setBounds(125, 80, 246, 24);
		lblAll.setFont(new Font("Serif", Font.PLAIN, 20));
		frame.getContentPane().add(lblAll);
		
		JLabel lblPre = new JLabel("Run Preliminary Test cases");
		lblPre.setBounds(125, 140, 246, 24);
		lblPre.setFont(new Font("Serif", Font.PLAIN, 20));
		frame.getContentPane().add(lblPre);
		
		JLabel lblDes = new JLabel("Run Destructive Test cases");
		lblDes.setBounds(125, 200, 246, 24);
		lblDes.setFont(new Font("Serif", Font.PLAIN, 20));
		frame.getContentPane().add(lblDes);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBounds(367, 80, 109, 23);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(367, 140, 109, 23);
		frame.getContentPane().add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(367, 200, 109, 23);
		frame.getContentPane().add(radioButton_2);
		
		JLabel lblModules = new JLabel("Select by Modules");
		lblModules.setBounds(65, 286, 157, 16);
		lblModules.setFont(new Font("Serif", Font.PLAIN, 20));
		frame.getContentPane().add(lblModules);
		
		 DefaultListModel<String> l1 = new DefaultListModel<>();  
         l1.addElement("Administration");  
         l1.addElement("Dashboardn");  
         l1.addElement("Evaluation");  
         l1.addElement("Evaluation Search");  
         l1.addElement("Extraction");  
         l1.addElement("Froms");  
         l1.addElement("Integration");  
         l1.addElement("Network");  
         l1.addElement("Playback");  
         l1.addElement("Recording");  
         l1.addElement("Recording Search");  
         l1.addElement("Reports"); 
         l1.addElement("Security");  
         l1.addElement("Storage");
         l1.addElement("Blank");
         JList<String> list = new JList<>(l1);  
         list.setFont(new Font("Serif", Font.BOLD, 20));
         list.setBounds(300,286, 200,375);  
         frame.add(list);  


		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(85, 687, 109, 23);
		btnSubmit.setFont(new Font("Serif", Font.BOLD, 20));
		frame.getContentPane().add(btnSubmit);
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().isEmpty()||(textField_1.getText().isEmpty())||(textField_2.getText().isEmpty())||((radioButton_1.isSelected())&&(radioButton.isSelected()))||(l1.firstElement().equals("Select")))
					JOptionPane.showMessageDialog(null, "Data Missing");
				else		
				JOptionPane.showMessageDialog(null, "Data Submitted");
				
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				textField_2.setText(null);
				textField.setText(null);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
			
				
				
			}
		});
		
	}
}
