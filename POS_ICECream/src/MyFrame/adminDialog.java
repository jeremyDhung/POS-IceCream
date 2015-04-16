package MyFrame;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import IceCream.decorator;
import IceCream.flavor;

public class adminDialog extends JDialog {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -603496989569306143L;
	private JTextField flavorname = new JTextField();
	private JTextField decoratorname = new JTextField();
	private JTextField flavorprice = new JTextField();
	private JTextField decoratorprice = new JTextField();
	MyFrame parent;
/*
 * While add new flavor or decorator, they are added to temporary queue
 * After click confirm button, the new flavors and decorator will be added to inventory
 */
	Queue<flavor> tempf= new LinkedList<flavor>();
	Queue<decorator> tempd= new LinkedList<decorator>();
	
	public adminDialog(MyFrame frame){
		super(frame,"System Administration",true);
		this.parent = frame;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container contentPane=this.getContentPane();
		contentPane.setLayout(new GridLayout(4,4));
		this.setSize(500,200);
		JButton addflavor = new JButton("Add");
		JButton adddecorator = new JButton("Add");
		JButton confirm = new JButton("Confirm");
		contentPane.add(new JButton());
		contentPane.add(new JButton("Name"));
		contentPane.add(new JButton("Price"));
		contentPane.add(new JButton());
		contentPane.add(new JButton("Flavor"));
		contentPane.add(flavorname);
		contentPane.add(flavorprice);
		contentPane.add(addflavor);
		contentPane.add(new JButton("Decorator"));
		contentPane.add(decoratorname);
		contentPane.add(decoratorprice);
		contentPane.add(adddecorator);
		contentPane.add(new JButton());
		contentPane.add(confirm);
		contentPane.add(new JButton());
		contentPane.add(new JButton());
		addflavor.addActionListener(new java.awt.event.ActionListener() {
   	            public void actionPerformed(java.awt.event.ActionEvent evt) {
   	                addflavorActionPerformed(evt);
   	            }
   	        });
		adddecorator.addActionListener(new java.awt.event.ActionListener() {
   	            public void actionPerformed(java.awt.event.ActionEvent evt) {
   	            	adddecoratorActionPerformed(evt);
   	            }
   	        });
		confirm.addActionListener(new java.awt.event.ActionListener() {
   	            public void actionPerformed(java.awt.event.ActionEvent evt) {
   	            	confirmActionPerformed(evt);
   	            }
   	        });
	 	this.setModal(true);
		this.setVisible(true);
	}
    
    /*
     * add new flavors and decorators to inventory and close dialog
     */
	protected void confirmActionPerformed(ActionEvent evt) {
        for(flavor fl:tempf){
        	this.parent.inventory.addFlavors(fl);
        }
        for(decorator de:tempd){
        	this.parent.inventory.addDecorators(de);
        }
		this.dispose();
	}
   
   
	 /* validate the input of price and name
     * name can not be pure numeric.
     * price can be integer or decimal
     */
	private boolean myvalidate(String name,String price){
		boolean na = name.matches("[0-9]+");
		boolean pri = Pattern.compile("[1-9]\\d*|[1-9]\\d*\\.\\d*").matcher(price).matches();
		return !na&&pri;
	}
	/*
	 * add new decorator to MyFrame
	 * not yet added to inventory
	 */
	private void adddecoratorActionPerformed(ActionEvent evt) {
		String name = this.decoratorname.getText();
	    String price = this.decoratorprice.getText();
		if(this.myvalidate(name,price)){
             decorator newone = new decorator(name,Double.parseDouble(price));
		     this.parent.adddecorator(newone);
		     this.tempd.offer(newone);
		}
		else
			JOptionPane.showMessageDialog(null, "Invalid Input!");
	}

	private void addflavorActionPerformed(ActionEvent evt) {
		String name = this.flavorname.getText();
	    String price = this.flavorprice.getText();
		if(this.myvalidate(name,price)){
			flavor newone = new flavor(name,Double.parseDouble(price));
			this.parent.addflavor(newone);
			this.tempf.offer(newone);
		}
		else
			JOptionPane.showMessageDialog(null, "Invalid Input!");
	}
}
