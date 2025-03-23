import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CurrencyConverter extends JFrame{
	
	private JComboBox<String> fromCurrency,toCurrency;
	private JTextField amountField, resultField;
	private JButton convertButton;
	private Map<String, Double> exchangeRates;
	private boolean isDarkMode = false;
	private JButton nightModeButton;


	public CurrencyConverter() {
		nightModeButton = new JButton("Night Mode");
		nightModeButton.addActionListener(e -> toggleNightMode());

		
		setTitle("Currency Converter");
		setSize(400,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(6,2,10,10));
		
		exchangeRates =new HashMap<String, Double>();
		exchangeRates.put("USD", 1.0);
		exchangeRates.put("EUR", 0.85);
		exchangeRates.put("GBP", 0.75);
		exchangeRates.put("INR", 74.5);
		exchangeRates.put("JPY", 110.0);
		
		String[] currencies= exchangeRates.keySet().toArray(new String[0]);
		
		fromCurrency =new JComboBox<String>(currencies);
		toCurrency =new JComboBox<String>(currencies);
		
		amountField = new JTextField();
		resultField = new JTextField();
		resultField.setEditable(false);
		
		convertButton = new JButton("Convert");
		convertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = Double.parseDouble(amountField.getText());
					String from = (String)fromCurrency.getSelectedItem();
					String to = (String)toCurrency.getSelectedItem();
					
					double fromRate = exchangeRates.get(from);
					double toRate = exchangeRates.get(to);
					
					double convertedAmount = (amount/fromRate)*toRate;
					resultField.setText(String.format("%.2f", convertedAmount));
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Enter a Valid Number","Error",JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		add(nightModeButton);
		add(new JLabel(""));

		add(new JLabel("From"));
		add(fromCurrency);
		add(new JLabel("To"));
		add(toCurrency);
		add(new JLabel("Amount:"));
		add(amountField);
		add(new JLabel("Converted Amount:"));
		add(resultField);
		add(new JLabel(""));
		add(convertButton);
		
		setVisible(true);
	}
	
	private void toggleNightMode() {
	    isDarkMode = !isDarkMode;

	    Color bgColor = isDarkMode ? Color.DARK_GRAY : Color.WHITE;
	    Color fgColor = isDarkMode ? Color.WHITE : Color.BLACK;
	    Color buttonBgColor = isDarkMode ? Color.GRAY : Color.LIGHT_GRAY;

	    // Set background color for the main frame
	    getContentPane().setBackground(bgColor);

	    // Set colors for dropdowns (JComboBox)
	    fromCurrency.setBackground(bgColor);
	    fromCurrency.setForeground(fgColor);
	    toCurrency.setBackground(bgColor);
	    toCurrency.setForeground(fgColor);

	    // Set colors for text fields
	    amountField.setBackground(bgColor);
	    amountField.setForeground(fgColor);
	    resultField.setBackground(bgColor);
	    resultField.setForeground(fgColor);

	    // Set colors for buttons
	    convertButton.setBackground(buttonBgColor);
	    convertButton.setForeground(fgColor);
	    nightModeButton.setBackground(buttonBgColor);
	    nightModeButton.setForeground(fgColor);

	    // Set colors for labels
	    for (java.awt.Component component : getContentPane().getComponents()) {
	        if (component instanceof JLabel) {
	            component.setForeground(fgColor);
	        }
	    }
	}


	
}
