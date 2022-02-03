/*
 * Author: Effiea Ponniah 
 * Last Date Modified: March 31st, 2021
 * Create GUI class
 * Allows programmers to create swing components 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateGUIPonniah implements WindowListener{
	private JPanel panel; 
	private JFrame frame; 
	
	// Constructor for CreateGUIPonniah class
	public CreateGUIPonniah()
	{
		panel = new JPanel(); 
		panel.setLayout(null);
	}
	
	// Creates frame
	public void createFrame(JPanel panel, String img, String title, int x, int y, int l, int h)
	{
		frame = new JFrame(title);
		frame.setContentPane(panel);	
		frame.setBounds(x, y, l, h);
		frame.setResizable(false);
		panel.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(this);
	}
	
	// Creates panel
	public JPanel createPanel(JPanel panel, String text, int x, int y, int l, int h)
	{
		panel.setLayout(null);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createTitledBorder(text));
		panel.setBounds(x, y, l, h);
		return panel;
	}
	
	// Creates label
	public JLabel createLabel(JPanel panel, String url, String text, int x, int y, int l, int h)
	{
		panel.setLayout(null);
		ImageIcon myImage = new ImageIcon(url);
		JLabel label = new JLabel(myImage);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(x, y, l, h);
		panel.add(label);
		return label; 
	}

	// Creates label
	public JLabel createLabel(JPanel panel, String text, int x, int y, int l, int h)
	{
		panel.setLayout(null);
		JLabel label = new JLabel(text);
		label.setBounds(x, y, l, h);
		label.setHorizontalAlignment(JLabel.LEFT);
		panel.add(label);
		return label; 
	}

	// Creates label
	public JLabel createLabelBorder(JPanel panel, String text, int x, int y, int l, int h)
	{
		panel.setLayout(null);
		JLabel label = new JLabel(text);
		label.setOpaque(true);
		label.setBackground(Color.white);
		label.setBounds(x, y, l, h);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createTitledBorder(""));
		panel.add(label);
		return label; 
	}
	
	// Creates button
	public JButton createButton(JPanel panel, String text, int x, int y, int l, int h)
	{
		JButton button = new JButton(text); 
		button.setBounds(x, y, l, h);
		panel.add(button);
		return button; 
	}
	
	// Creates check box
	public JCheckBox createCheckbox(JPanel panel, String text, int x, int y, int l, int h)
	{
		panel.setLayout(null);
		JCheckBox checkbox = new JCheckBox(text);
		checkbox.setBounds(x, y, l, h);
		panel.add(checkbox);
		return checkbox; 
		
	}
	
	// Creates combo box
	public JComboBox<Integer> createIntCombobox(JPanel panel, Integer[] options, int x, int y, int l, int h)
	{
		JComboBox<Integer> combobox = new JComboBox<Integer>(options);
		combobox.setBounds(x, y, l, h);
		panel.add(combobox);
		return combobox;
	}

	public void windowClosed(WindowEvent we)
	{
	}
	
	public void windowOpened(WindowEvent we)
	{
	}	
	
	public void windowIconified(WindowEvent we)
	{
	}
	
	public void windowActivated(WindowEvent we)
	{
	}
	
	public void windowDeiconified(WindowEvent we)
	{
	}
	
	public void windowDeactivated(WindowEvent we)
	{
	}
	
	public void windowClosing(WindowEvent we)
	{
		int closing = JOptionPane.showConfirmDialog(panel, "Are you sure you want to close?", "Little Caesar's", JOptionPane.YES_NO_OPTION);
		
		// Depending on confirmation of customer, the window closing operation will be performed
		if(closing == 0)
		{
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
}
