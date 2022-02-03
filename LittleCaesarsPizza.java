/*
 * Author: Effiea Ponniah 
 * Last Date Modified: March 31st, 2021
 * Little Caesars Pizza Main program
 * This program will allow customers to order pizzas given a selection of sizes, toppings and beverages 
 */

import java.awt.Color;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.*;

public class LittleCaesarsPizza implements ActionListener {

	private CreateGUIPonniah cg = new CreateGUIPonniah();
	private JButton btnCalculate, btnClear, btnCheckout, btnExit;
	private NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.CANADA);
	private JLabel littleCaesarsLogo, paymentOptionsLogo, lblSubTotalLabel, lblSubTotalAmount, lblDeliveryFeeLabel, 
					lblDeliveryFeeAmount, lblHSTLabel, lblHSTAmount, lblGrandTotalLabel, lblGrandTotalAmount;
	private JPanel panel = new JPanel();

	private ImageIcon littleCaesarsIcon = new ImageIcon("LittleCaesarsIcon.png");

	private final double DELIVERY_FEE = 3.00, HST_RATE = 0.13;
	private double subtotal; 

	// Create objects of the following classes: PizzaSizePonniah, PizzaToppingsPonniah, PizzaBeveragesPonniah
	private PizzaSize pizzaSize = new PizzaSize();
	private PizzaToppings pizzaTopping = new PizzaToppings();
	private PizzaBeverages pizzaBeverage = new PizzaBeverages();

	// Main Method
	public static void main(String[] args) 
	{
		new LittleCaesarsPizza();
	}

	// Constructor for LittleCaesarsPizzaProgramPonniah Class
	public LittleCaesarsPizza() 
	{
		littleCaesarsLogo = cg.createLabel(panel, "C:\\Users\\Effiea\\eclipse-workspace\\LittleCaesars\\src\\LittleCaesarsLogo.png", null, 35, 30, 555, 92);
		paymentOptionsLogo = cg.createLabel(panel, "C:\\Users\\Effiea\\eclipse-workspace\\LittleCaesars\\src\\LittleCaesarsIcon.png", null, 35, 400, 70, 200);
		
		createPriceLabels();
		createButtons();

		pizzaSize.createPizzaSizePanel(panel, cg);
		pizzaTopping.createPizzaToppingsPanel(panel, cg);
		pizzaBeverage.createPizzaBeveragePanel(panel, cg);

		cg.createFrame(panel, null, "Little Caesar's", 200, 150, 620, 640);
	}

	// Action performed when an action event is triggered from any of the buttons 
	public void actionPerformed(ActionEvent ae) 
	{
		// Calculate order total (subtotal, delivery fee, hst, grand total) when Calculate Button is selected
		if(ae.getSource() == btnCalculate) 
		{
			calculateOrderTotal();
			btnCheckout.setEnabled(true);
		}
		// Clear all the selections and calculation labels when clear button is selected
		else if (ae.getSource() == btnClear) 
		{
			clearAllFields(); 
		}
		// Checkout confirmation when checkout button is selected
		else if (ae.getSource() == btnCheckout)
		{
			checkOutBtnAction();	
		}
		// Confirmation when exit button is selected
		else
		{
			int confirmExit = JOptionPane.showConfirmDialog(panel, "Are you sure you want to exit?", "Little Caesar's", JOptionPane.YES_NO_OPTION);
			if (confirmExit == 0)
			{
				JOptionPane.showMessageDialog(panel, "Thank you for choosing Little Caesar's!", "Little Caesar's", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}

	// Initialize label variables related to cost
	private void createPriceLabels() 
	{
		lblSubTotalLabel = cg.createLabel(panel, "SUBTOTAL:", 180, 425, 200, 30);
		lblSubTotalAmount = cg.createLabelBorder(panel, "$0.00", 290, 425, 110, 30);
		lblDeliveryFeeLabel = cg.createLabel(panel, "DELIVERY FEE:", 180, 465, 200, 30);
		lblDeliveryFeeAmount = cg.createLabelBorder(panel, "$0.00", 290, 465, 110, 30);
		lblHSTLabel = cg.createLabel(panel, "HST:", 180, 505, 100, 30);
		lblHSTAmount = cg.createLabelBorder(panel, "$0.00", 290, 505, 110, 30);
		lblGrandTotalLabel = cg.createLabel(panel, "GRAND TOTAL:", 180, 545, 200, 30);
		lblGrandTotalAmount = cg.createLabelBorder(panel, "$0.00", 290, 545, 110, 30);
	}
	
	// Calculate pizza order total
	private void calculateOrderTotal() 
	{
		subtotal = pizzaSize.getPizzaSizePrice() + pizzaTopping.getPizzaToppingsPrice() + pizzaBeverage.getPizzaBeveragePrice();
		double hstTotal; 

		lblSubTotalAmount.setText(currency.format(subtotal)  + "");
		
		hstTotal = subtotal * HST_RATE; 
		lblHSTAmount.setText(currency.format(hstTotal)  + "");			

		// Display corresponding delivery fees according to subtotal 
		if (subtotal  < 15)
		{
			lblDeliveryFeeAmount.setBackground(Color.white);
			lblDeliveryFeeAmount.setText(currency.format(DELIVERY_FEE) + "");  

			lblGrandTotalAmount.setText(currency.format(subtotal + hstTotal + DELIVERY_FEE)  + "");	
		}
		else 
		{
			lblDeliveryFeeAmount.setBackground(Color.green);
			lblDeliveryFeeAmount.setText("FREE"); 
			lblGrandTotalAmount.setText(currency.format(subtotal + hstTotal)  + "");	
		}		
	}

	// Create buttons for customer selection
	private void createButtons() 
	{
		btnCalculate = cg.createButton(panel, "CALCULATE", 450, 410, 125, 35);
		btnCalculate.addActionListener(this);
		btnClear = cg.createButton(panel, "CLEAR", 450, 460, 125, 35);
		btnClear.addActionListener(this);
		btnCheckout = cg.createButton(panel, "CHECKOUT", 450, 510, 125, 35);
		btnCheckout.addActionListener(this);
		btnCheckout.setEnabled(false);
		btnExit = cg.createButton(panel, "EXIT", 450, 560, 125, 35);
		btnExit.addActionListener(this);
	}

	// Clear all the selections and calculation labels
	private void clearAllFields() 
	{		
		pizzaSize.clear();
		pizzaTopping.clear();
		pizzaBeverage.clear();
		btnCheckout.setEnabled(false);
		
		lblSubTotalAmount.setText("$0.00");
		lblDeliveryFeeAmount.setText("$0.00");
		lblHSTAmount.setText("$0.00");
		lblGrandTotalAmount.setText("$0.00");
		lblDeliveryFeeAmount.setBackground(Color.white);
	}

	// Action performed when checkout button is selected
	private void checkOutBtnAction() 
	{
		// Check whether the user selected any Pizza sizes
		if (pizzaSize.getPizzaSizePrice() == 0) 
		{
			JOptionPane.showMessageDialog(panel, "Your order could not be completed!\nPlease select a pizza size", "Critical Error!", JOptionPane.OK_OPTION);
			btnCheckout.setEnabled(false);
		} 
		else 
		{
			// Display calculation before confirming check out of the pizza order
			if(subtotal != pizzaSize.getPizzaSizePrice() + pizzaTopping.getPizzaToppingsPrice() + pizzaBeverage.getPizzaBeveragePrice())
			{
				JOptionPane.showMessageDialog(panel, "Please calculate your order before check out!", "Error", JOptionPane.OK_OPTION);
				btnCheckout.setEnabled(false);
				panel.requestFocus();
			}
			else 
			{
				int confirmCheckout = JOptionPane.showConfirmDialog(panel, "Is this order correct?", "Little Caesar's", JOptionPane.YES_NO_OPTION);
				if (confirmCheckout == 0) {
					JOptionPane.showMessageDialog(panel, "Thank you for ordering from Little Caesar's!\nYour pizza will be delivered in 30 minutes or it's free", "Little Caesar's", JOptionPane.DEFAULT_OPTION, littleCaesarsIcon);
					System.exit(0);
				}
			}
		}
	}
}