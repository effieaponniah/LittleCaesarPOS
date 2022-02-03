/*
 * Author: Effiea Ponniah 
 * Last Date Modified: March 31st, 2021
 * Little Caesars Pizza Size class
 * This class displays the pizza size panel and calculate the price of a pizza according to the size selected
 */

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class PizzaSize implements ActionListener
{
	private double pizzaSizeCost;
	
	private JRadioButton[] rbPizzaSizes = {new JRadioButton("Small"), new JRadioButton("Medium"), new JRadioButton("Large"), new JRadioButton("Party")};
	private final double[] PIZZA_PRICES = {7.99, 8.99, 9.99, 10.99};
	
	private JPanel pizzaSizePanel;
	private JLabel lblSizePrice;
	private ButtonGroup btnPizzaSizeGroup;
	private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);

	// Constructor for PizzaSizePonniah class
	public PizzaSize()
	{
		pizzaSizeCost = 0;
		pizzaSizePanel = new JPanel();
	}
	
	// Create panel for pizza size options 
	public void createPizzaSizePanel(JPanel panel, CreateGUIPonniah cg)
	{
		cg.createPanel(pizzaSizePanel, "SIZE", 13, 171, 111, 141); 
		
		btnPizzaSizeGroup = new ButtonGroup();	
		
		// Add radio buttons to group to allow customer to only choose one pizza size
		for (int i = 0; i < 4; i++) 
		{
			rbPizzaSizes[i].addActionListener(this);
			pizzaSizePanel.add(rbPizzaSizes[i]);
			btnPizzaSizeGroup.add(rbPizzaSizes[i]);
		}
		
		lblSizePrice = cg.createLabelBorder(panel, "$0.00", 10, 350, 110, 30); 
		panel.add(pizzaSizePanel);
	}
	
	// Returns pizza size cost
	public double getPizzaSizePrice()
	{
		return pizzaSizeCost; 	
	}
	
	// Calculate the cost of the pizza when customer selection triggered
	public void actionPerformed(ActionEvent ae) 
	{
		// Calculate pizza price when Radio Button is pressed
		if(ae.getSource() instanceof JRadioButton) 
		{  
			// Find cost of corresponding pizza size selection 
			for(int i = 0; i < rbPizzaSizes.length; i++)
			{
				if(rbPizzaSizes[i].isSelected())
				{
					pizzaSizeCost = PIZZA_PRICES[i];
					break; 
				}
			}
			lblSizePrice.setText(currency.format(pizzaSizeCost) + "");
		}
	}
	
	// Reset variables and label related to pizza sizes
	public void clear()
	{
		btnPizzaSizeGroup.clearSelection();
		pizzaSizeCost = 0;
		lblSizePrice.setText("$0.00");
	}
}
