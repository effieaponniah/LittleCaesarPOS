/*
 * Author: Effiea Ponniah 
 * Last Date Modified: March 31st, 2021
 * Little Caesars Pizza Size class
 * This class displays the pizza beverage panel and calculate the price of beverages according to the total number of beverages selected
 */

import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class PizzaBeverages implements ActionListener
{
	private double totalBeverageCost;

	private final double BEVERAGE_COST = 0.99;
	private final int NUMBER_OF_BEVERAGES = 4;

	private double[] beveragesCost;
	private JLabel[] lblBeverages = {new JLabel("Coke"), new JLabel("Sprite"), new JLabel("Orange"), new JLabel("Root Beer")}; 
	private JComboBox<Integer>[] cboBeverages = new JComboBox[NUMBER_OF_BEVERAGES];
	
	private JPanel beverageSelectionPanel;
	private JLabel lblBeveragePrice;
	private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);

	// Constructor for PizzaBeveragesPonniah class
	public PizzaBeverages()
	{
		totalBeverageCost = 0;
		beveragesCost = new double[NUMBER_OF_BEVERAGES];
		beverageSelectionPanel = new JPanel();
	}
	
	// Create panel for total beverage selection
	public void createPizzaBeveragePanel(JPanel panel, CreateGUIPonniah cg)
	{
		Integer[] beverageCount = { 0, 1, 2, 3, 4, 5, 6 };
		cg.createPanel(beverageSelectionPanel, "BEVERAGES", 445, 171, 160, 141); 

		
		// Add labels of beverage name to the beverage panel
		for (int i = 0; i < lblBeverages.length; i++)
		{
			lblBeverages[i] = cg.createLabel(beverageSelectionPanel,lblBeverages[i].getText(), 6, 25 + (30 * i), 85, 20);
		}
		
		// Add number of beverages combo box to the beverage panel
		for(int i = 0; i < cboBeverages.length; i++)
		{
			cboBeverages[i] = cg.createIntCombobox(beverageSelectionPanel, beverageCount, 95, 20 + (30 * i), 60, 30);
			cboBeverages[i].addActionListener(this);
		}
		
		lblBeveragePrice = cg.createLabelBorder(panel, "$0.00", 470, 350, 110, 30); 
		panel.add(beverageSelectionPanel);
	}
	
	// Returns total beverage cost
	public double getPizzaBeveragePrice()
	{
		return totalBeverageCost; 	
	}
	
	// Calculate the cost of total beverages according to customer selection
	public void actionPerformed(ActionEvent ae) 
	{
		// Calculate total cost of beverages when Combo Box is triggered
		if(ae.getSource() instanceof JComboBox<?>) 
		{ 
			// Check selected combo box and calculate beverage cost per beverage
			for (int i = 0; i < cboBeverages.length; i++)
			{
				if (ae.getSource() == cboBeverages[i]) 
				{
					beveragesCost[i] = (Integer)cboBeverages[i].getSelectedItem() * BEVERAGE_COST;
					break; 
				} 
			}
			  
			totalBeverageCost = 0; 
			 
			// Add cost per beverage for total beverage cost 
			for (int i = 0; i < cboBeverages.length; i++)
			{
				totalBeverageCost += beveragesCost[i];
			}
			 
			totalBeverageCost = Math.round(totalBeverageCost*100)/100.0; 
			lblBeveragePrice.setText(currency.format(totalBeverageCost) + "");
		}
	}
	
	// Reset variables and label related to beverage
	public void clear()
	{
		for (int i = 0; i < cboBeverages.length; i++) 
		{
			cboBeverages[i].setSelectedItem(0);
		}
		totalBeverageCost = 0;
		lblBeveragePrice.setText("$0.00");
	}
}
