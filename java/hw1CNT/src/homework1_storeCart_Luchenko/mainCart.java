/*
 *Name:  <Vladyslav Luchenko>
 *Course:CNT 4714 –Spring 2021
 *Assignment title:Project1 –Event-driven Enterprise Simulation
 *Date:Sunday January 31, 2021
*/

package homework1_storeCart_Luchenko;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class mainCart {

	
	static String[][] item = new String[100][];
	static String[] orderView = new String[100];
	static String[] orderFinal = new String[100];
	private static DecimalFormat df2 = new DecimalFormat("#.00");
	private static DecimalFormat df1 = new DecimalFormat("#.0#");
	private static DecimalFormat df0 = new DecimalFormat("#");
	static int itemNumContent = 0;
	static int itemNumber = 1;
    static double priceSum = 0;
	static double overalPriceSum = 0;
	
	    public static void main(String args[]) throws FileNotFoundException{
	    	mainCart fileSearch = new mainCart();
	    	
	        //Creating the Frame
	        JFrame frame = new JFrame("Nile Dot Com - Srping 2021");
	        JFrame messageFrame = new JFrame("");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(750, 200);

	        //Creating the panel at bottom and adding components
	        JPanel panel = new JPanel(); // the panel is not visible in output
	       
	        
	        JButton process = new JButton("Process Item #1");
	        JButton confirm = new JButton("Confirm Item #1");
	        confirm.setEnabled(false);
	        JButton viewOrder = new JButton("View Order");
	        viewOrder.setEnabled(false);
	        JButton finishOrder = new JButton("Finish Order");
	        finishOrder.setEnabled(false);
	        JButton newOrder = new JButton("New Order");
	        JButton exit = new JButton("Exit");
	      
	        
	        panel.add(process);
	        panel.add(confirm);
	        panel.add(viewOrder);
	        panel.add(finishOrder);
	        panel.add(newOrder);
	        panel.add(exit);
	        
	        
	        
	        
	        // Text Area at the Center
	        JPanel inputArea = new JPanel();
	      // inputArea.setLayout(new GridLayout(3, 2, 5, 5)); 
	        GridBagLayout gridbag = new GridBagLayout();
	         GridBagConstraints c = new GridBagConstraints();
	         c.anchor = GridBagConstraints.EAST;
	         inputArea.setLayout(gridbag); 
	        
	        
	        //items Number
	      
	        JLabel itemNumLabel = new JLabel("Enter number of items in this order:");
	        itemNumLabel.setForeground(Color.yellow);
	        JTextField itemNumTF = new JTextField(40); // accepts upto 10 characters
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        gridbag.setConstraints(itemNumTF, c);
	        inputArea.add(itemNumLabel); // Components Added using Flow Layout
	        inputArea.add(itemNumTF);
	        
	        //new line id
	        c.weightx = 0.0; 
	        JLabel itemsIDLabel = new JLabel("Enter item ID for item #" + itemNumber + ":");
	        itemsIDLabel.setForeground(Color.yellow);
	        JTextField itemsIDTF = new JTextField(40); // accepts upto 40 characters
	       
	        c.gridwidth = GridBagConstraints.RELATIVE;
	        gridbag.setConstraints(itemsIDLabel, c);
	        inputArea.add(itemsIDLabel); // Components Added using Flow Layout
	        
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        gridbag.setConstraints(itemsIDTF, c);
	        inputArea.add(itemsIDTF);
	        
	        
	        //new line quantity
	        c.weightx = 0.0; 
	        JLabel quantityLabel = new JLabel("Enter quantity for item #" + itemNumber + ":");
	        quantityLabel.setForeground(Color.yellow);
	        JTextField quantityTF = new JTextField(40); // accepts up to 40 characters
	       
	        c.gridwidth = GridBagConstraints.RELATIVE;
	        gridbag.setConstraints(quantityLabel, c);
	        inputArea.add(quantityLabel); // Components Added using Flow Layout
	        
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        gridbag.setConstraints(quantityTF, c);
	        inputArea.add(quantityTF);
	        
	        
	      //new line info
	        c.weightx = 0.0; 
	        JLabel infoLabel = new JLabel("Item #" + itemNumber + " info:");
	        infoLabel.setForeground(Color.yellow);
	        JTextField infoTF = new JTextField(40); // accepts upto 40 characters
	        infoTF.setEditable(false);
	        c.gridwidth = GridBagConstraints.RELATIVE;
	        gridbag.setConstraints(infoLabel, c);
	        inputArea.add(infoLabel); // Components Added using Flow Layout
	        
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        gridbag.setConstraints(infoTF, c);
	        inputArea.add(infoTF);
	        
	        
	        
	      //new line subtotal
	        c.weightx = 0.0; 
	        JLabel subtotalLabel = new JLabel("Order subtotal for " + (itemNumber - 1) + " item(s):");
	        subtotalLabel.setForeground(Color.yellow);
	        JTextField subtotalTF = new JTextField(40); // accepts upto 40 characters
	        subtotalTF.setEditable(false);
	        
	        c.gridwidth = GridBagConstraints.RELATIVE;
	        gridbag.setConstraints(subtotalLabel, c);
	        inputArea.add(subtotalLabel); // Components Added using Flow Layout
	        
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        gridbag.setConstraints(subtotalTF, c);
	        inputArea.add(subtotalTF);
	        
	        
	        
	       
	        //Adding Components to the frame.
	        panel.setBackground(Color.blue);
	        frame.getContentPane().add(BorderLayout.SOUTH, panel);
	        //frame.getContentPane().add(BorderLayout.NORTH, mb);
	        frame.getContentPane().setBackground(Color.black);
	        inputArea.setBackground(Color.black);
	        frame.getContentPane().add(BorderLayout.LINE_END, inputArea);
	        frame.setVisible(true);
	        
	        
	        
	        //button functions
	        //exit
	        exit.addActionListener(e -> {
	        	   frame.dispose();
	        	});
	        
	        
	        //process button
	        process.addActionListener(e -> {
	        	
	        	String idContent = itemsIDTF.getText();
	        	
	        	
	        	 itemNumContent = Integer.parseInt(itemNumTF.getText());
	        	 
	        	
	        	
	        	
	        	
	        	
	        	//read and process file
	        	try {
					fileSearch.parseFile("inventory.txt", idContent + ",");	
				//	JOptionPane.showMessageDialog(messageFrame, "Check:!" + item[itemNumber][2] + "!");
					if(item[itemNumber] != null && itemNumber <= itemNumContent && item[itemNumber][2].equalsIgnoreCase(" true")) {
					
					
					//count percentage
		        	double percentage = 0;
		        	int quantityContent = Integer.parseInt(quantityTF.getText());
		        	if(quantityContent <= 4) {
		        	 percentage = 0;
		        	}else if(quantityContent <= 9 && quantityContent > 4) {
		        		 percentage = 0.10;
		        	}else if(quantityContent <= 14 && quantityContent > 9) {
	        		 percentage = 0.15;
	        	}else if(quantityContent >= 15) {
	        		 percentage = 0.20;
	        	}	 	
					
					
					
				    priceSum = quantityContent * Double.parseDouble(item[itemNumber][3]) - ((quantityContent * Double.parseDouble(item[itemNumber][3]) * percentage));
				    infoLabel.setText("Item #" + (itemNumber) + " info:");
				    orderView[itemNumber] = item[itemNumber][0] + item[itemNumber][1] + item[itemNumber][3].replaceAll(" ", " \\$") + " " + quantityContent + " " + df0.format(percentage * 100) + "% $" + df2.format(priceSum);
				    orderFinal[itemNumber] =item[itemNumber][0] + ", " + item[itemNumber][1] + ", " + df1.format(Double.parseDouble(item[itemNumber][3]))+ ", " + quantityContent+ ", " + percentage + ", $" + df2.format(priceSum);
				    infoTF.setText(orderView[itemNumber]); 
				    
				    itemNumber = itemNumber + 1;
					
					process.setEnabled(false);
					 confirm.setEnabled(true);
					 
				     itemNumTF.setEditable(false);
				     
					}else{
						//error: did not find the item
					        if(item[itemNumber] == null) {    	
			            	JOptionPane.showMessageDialog(messageFrame, "item ID " + idContent + " not in file");
					        }else if (item[itemNumber][2].equalsIgnoreCase(" false")) {
					        	JOptionPane.showMessageDialog(messageFrame, "Sorry... that item is out of stock, please try another item");
					        
					}else {
					        	JOptionPane.showMessageDialog(messageFrame, "Error");
					        }
					}
					} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	});
	      
	        confirm.addActionListener(e -> {
	        	JOptionPane.showMessageDialog(messageFrame, "Item #" + (itemNumber-1) + " accepted");
	        	if(itemNumber <= itemNumContent) {
	        	//updating GUI
	        		process.setEnabled(true);
					 confirm.setEnabled(false);
					 viewOrder.setEnabled(true);
				     finishOrder.setEnabled(true);
					  process.setText("Process Item #" + itemNumber);
				      confirm.setText("Confirm Item #" + itemNumber);
					 itemsIDLabel.setText("Enter item ID for item #" + itemNumber + ":");
	        	quantityLabel.setText("Enter quantity for item #" + itemNumber + ":");
	        	subtotalLabel.setText("Order subtotal for " + (itemNumber - 1) + " item(s):");	        
	        	itemsIDTF.setText("");
				quantityTF.setText("");
				
				overalPriceSum = overalPriceSum + priceSum;
				subtotalTF.setText("$" + df2.format(overalPriceSum));
	        	}else {
	        		process.setEnabled(false);
					 confirm.setEnabled(false);
					 viewOrder.setEnabled(true);
				     finishOrder.setEnabled(true);
					  process.setText("Process Item");
				      confirm.setText("Confirm Item");
				      itemsIDTF.setEditable(false);
				      quantityTF.setEditable(false);
				      itemsIDLabel.setText("");
			        	quantityLabel.setText("");
			        	subtotalLabel.setText("Order subtotal for " + (itemNumber - 1) + " item(s):");
			        	itemsIDTF.setText("");
						quantityTF.setText("");
						
						overalPriceSum = overalPriceSum + priceSum;
						subtotalTF.setText("$" + df2.format(overalPriceSum));
	        	}
	        	});
	        
	        viewOrder.addActionListener(e -> {
	        	String orderLabel = new String();
	        	orderLabel = "";
	        	for(int i = 0; i < orderView.length; i++) {
	        		if(orderView[i] != null) {
	        			orderLabel = orderLabel + i + ". " + orderView[i] + "\n";
	        		}
	        		
	  	        }
	        	JOptionPane.showMessageDialog(messageFrame, orderLabel);
	        	});
	      
	        
	        newOrder.addActionListener(e -> {
	        	frame.dispose();
	        	 item = new String[100][];
	        	 orderView = new String[100];
	        	 orderFinal = new String[100];
	        	 itemNumContent = 0;
	        	 itemNumber = 1;
	             priceSum = 0;
	        	  overalPriceSum = 0;
	        	try {
					mainCart.main(args);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	
	        	});
	        
	        
	        finishOrder.addActionListener(e -> {
	        	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yy, HH:mm:ss a z"); 
	        	 DateTimeFormatter ft = DateTimeFormatter.ofPattern("ddMMyyyyhhmm");	
	        	 DateTimeFormatter fte = DateTimeFormatter.ofPattern("HH:mm:ss a z");
	        	 ZonedDateTime now = ZonedDateTime.now();  
	        	 String dateID = ft.format(now);
	        	 String dateEnd = fte.format(now);
	        	 String date = dtf.format(now);
	        	 System.out.println(dtf.format(now));  
	        	
	        	String orderLabel = new String();
	        	orderLabel = "";
	        	for(int i = 0; i < orderView.length; i++) {
	        		if(orderView[i] != null) {
	        			orderLabel = orderLabel + i + ". " + orderView[i] + "\n";
	        		}
	        		
	  	        }
	        	
	        	JOptionPane.showMessageDialog(messageFrame,"Date: " +  date + "\n\nNumber of line items: " + 
	        	(itemNumber-1) + "\n\nItem# / ID / Title / Price / Qty / Disc% / Subtotal:\n\n" + orderLabel + "\n\nOrder subtotal: $" +
	        	df2.format(overalPriceSum) + "\n\nTax rate: 6%\n\nTax amount: $" + df2.format(overalPriceSum*0.06) + "\n\nOrder total: $"+ 
	        	df2.format(overalPriceSum + (overalPriceSum*0.06)) + "\n\nThanks for shopping at Nile Dot Com!");
	        	
	        	//create a file if it does not exist
	        	
		      	 try {
		      	      File myObj = new File("transactions.txt");
		      	      if (myObj.createNewFile()) {
		      	        System.out.println("File created: " + myObj.getName());
		      	      } else {
		      	        System.out.println("File already exists.");
		      	      }
		      	    } catch (IOException e1) {
		      	      System.out.println("An error occurred.");
		      	      e1.printStackTrace();
		      	    }
		      	
		      	 //write to a file
		      	 try {
					FileWriter myWriter = new FileWriter("transactions.txt", true);
					String orderFinalFull = new String();
					orderFinalFull = "";
		        	for(int i = 0; i < orderFinal.length; i++) {
		        		if(orderFinal[i] != null) {
		        			orderFinalFull = orderFinalFull + dateID +", " +orderFinal[i] + ", " + dateEnd + "\n";
		        		}
		        		
		  	        }
					myWriter.append(orderFinalFull);
				    myWriter.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	//restart
	        	frame.dispose();
	        	 item = new String[100][];
	        	 orderView = new String[100];
	        	 orderFinal = new String[100];
	        	 itemNumContent = 0;
	        	 itemNumber = 1;
	             priceSum = 0;
	        	  overalPriceSum = 0;
	        	try {
					mainCart.main(args);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	
	        	});
	        
	        
	     
	    }
	    
	    
	    
	    public void parseFile(String fileName,String searchStr) throws FileNotFoundException{
	        Scanner scan = new Scanner(new File(fileName));
	        
	        while(scan.hasNext()){
	            String line = scan.nextLine().toString();
	            if(line.startsWith(searchStr)){
	            	item[itemNumber] = line.split("[,]", 0);
	            	
	            	break;
	            }
	            }
	        }
	    
	    
	   
	    
	    
	    
	
}
