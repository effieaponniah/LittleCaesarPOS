/*
 * Author: Effiea Ponniah 
 * Last Date Modified: March 31st, 2021
 * Little Caesars Pizza Size class
 * This class displays the pizza toppings panel and calculate the price of a 
 * toppings according to the total number of toppings selected
 */

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class PizzaToppings implements ActionListener
{
	private double toppingsCost;
	private int toppingsCount;
	
	private final double TOPPINGS_COST = 1.00;
	private final int NUMBER_OF_FREE_TOPPINGS = 3; 

	private JCheckBox[] chkToppings = {new JCheckBox("Mushrooms"), new JCheckBox("Green Peppers"), new JCheckBox("Onions"), new JCheckBox("Hot Peppers"), new JCheckBox("Pepperoni"), new JCheckBox("Bacon"), new JCheckBox("Tomatoes"), new JCheckBox("Extra Cheese")};
	
	private JPanel toppingSelectionPanel;
	private JLabel lblToppingsPrice, lblFreeToppingLabel;
	private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);

	// Constructor for PizzaToppingsPonniah class
	public PizzaToppings()
	{
		toppingsCost = 0;
		toppingsCount = 0;
		toppingSelectionPanel = new JPanel();
	}
	
	// Create panel for pizza topping options 
	public void createPizzaToppingsPanel(JPanel panel, CreateGUIPonniah cg)
	{
		cg.createPanel(toppingSelectionPanel, "TOPPINGS", 141, 171, 287, 141); 

		// Create column of toppings on the left side of panel
		for (int i = 0; i < chkToppings.length/2; i++)
		{
			chkToppings[i] = cg.createCheckbox(toppingSelectionPanel, chkToppings[i].getText(), 3, 25 * (i+1), 130, 25);
			chkToppings[i].addActionListener(this);
		}
		
		// Create column of toppings on the right side of panel
		for (int i = chkToppings.length/2; i < chkToppings.length; i++)
		{
			chkToppings[i] = cg.createCheckbox(toppingSelectionPanel, chkToppings[i].getText(), 150, 25 * (i-3), 130, 25);
			chkToppings[i].addActionListener(this);
		}
		
		lblFreeToppingLabel = cg.createLabel(panel, "First three (3) toppings are free!",  190, 315, 200, 30);
		lblToppingsPrice = cg.createLabelBorder(panel, "$0.00", 220, 350, 110, 30); 
		panel.add(toppingSelectionPanel);
	}
	
	// Returns total toppings cost
	public double getPizzaToppingsPrice()
	{
		return toppingsCost; 	
	}
	
	// Calculate the total topping cost according to customer selection
	public void actionPerformed(ActionEvent ae) 
	{
		// Calculate total cost of toppings when Check Box is triggered
		if (ae.getSource() instanceof JCheckBox) 
		{
			// Count number of toppings selected
			for (int i = 0; i < chkToppings.length; i++)
			{
				if (ae.getSource() == chkToppings[i]) 
				{
					if (chkToppings[i].isSelected()) 
					{
						toppingsCount++; 
					}
					else
					{
						toppingsCount--;
					}
					break;
				} 
			}
			
			// Calculate corresponding cost of additional toppings (more than three toppings)
			if (toppingsCount > NUMBER_OF_FREE_TOPPINGS)
			{
				toppingsCost = (TOPPINGS_COST * (toppingsCount - NUMBER_OF_FREE_TOPPINGS));
			}
			else 
			{
				toppingsCost = 0;
			}
			lblToppingsPrice.setText(currency.format(toppingsCost) + "");
		}
	}
	
	// Reset variables and label related to toppings
	public void clear()
	{
		for (int i = 0; i < chkToppings.length; i++) 
		{
			chkToppings[i].setSelected(false);
		}
		toppingsCost = 0;
		toppingsCount = 0;
		lblToppingsPrice.setText("$0.00");
	}
}
