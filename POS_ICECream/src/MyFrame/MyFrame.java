package MyFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.swing.*;
import IceCream.decorator;
import IceCream.flavor;
import IceCream.inventory;

/**
 * 
 * @author Jeremy
 *
 */
public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820857441378842465L;
	protected inventory inventory = new inventory();
	private JPanel decoratorpanel = new JPanel();
	private JPanel flavorpanel = new JPanel();
	private ButtonGroup BG1;
	private JPanel botton, right, top;
	private JButton admin, newicecream;
	private JLabel total;

	public MyFrame() {
		super("Point Of Sales");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());

		top = new JPanel();
		JLabel title = new JLabel("Point-Of-Sale for Ice-Cream Shop");
		title.setFont(new Font("New Roman", Font.BOLD, 24));
		top.add(title);

		botton = new JPanel();
		botton.setBackground(Color.blue);
		admin = new JButton("System Administrator");
		admin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adminActionPerformed(evt);
			}
		});
		botton.add(admin);

		right = new JPanel();
		right.setBackground(Color.orange);
		right.setLayout(new BorderLayout());
		newicecream = new JButton("New IceCream");

		newicecream.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NEWactionPerformed(e);
			}

		});
		total = new JLabel("Total:$"
				+ this.inventory.getFlavors()[0].getPrice());
		total.setName("total");
		right.add(newicecream, BorderLayout.PAGE_START);
		right.add(total, BorderLayout.PAGE_END);

		flavorpanel.setBackground(Color.gray);
		flavorpanel.setLayout(new BoxLayout(flavorpanel, BoxLayout.Y_AXIS));
		JLabel flavor = new JLabel("ICE-CREAM Flavors", JLabel.CENTER);
		flavor.setFont(new Font("New Roman", Font.BOLD, 16));
		flavorpanel.add(flavor);
		BG1 = new ButtonGroup();

		for (int i = 0; i < this.inventory.getFlavors().length; i++) {
			this.addflavor(this.inventory.getFlavors()[i]);
		}
		
		Component tempcomp = this.flavorpanel.getComponent(1);
		if (tempcomp instanceof JRadioButton)
			((JRadioButton) tempcomp).setSelected(true);
		this.flavorpanel.repaint();

		decoratorpanel.setBackground(Color.pink);
		decoratorpanel.setLayout(new BoxLayout(decoratorpanel, BoxLayout.Y_AXIS));
		JLabel decorator = new JLabel("Decorators", JLabel.CENTER);
		decorator.setFont(new Font("New Roman", Font.BOLD, 16));
		decorator.setAlignmentX(CENTER_ALIGNMENT);
		decoratorpanel.add(decorator);

		for (int i = 0; i < this.inventory.getDecorators().length; i++) {
			this.adddecorator(this.inventory.getDecorators()[i]);
		}

		this.add(top, BorderLayout.PAGE_START);
		this.add(flavorpanel, BorderLayout.LINE_START);
		this.add(decoratorpanel, BorderLayout.CENTER);
		this.add(right, BorderLayout.LINE_END);
		this.add(botton, BorderLayout.PAGE_END);

		this.setSize(600, 500);
		this.setLocation(100, 100);
		this.setVisible(true);
	}

	private void adminActionPerformed(ActionEvent evt) {
		new adminDialog(MyFrame.this);
	}

	private void calculationActionPerformed(ActionEvent evt) {
		double price;
		String flavor = null;
		String decorator = null;
		//cost of flavor
		Enumeration<AbstractButton> radioBtns = this.BG1.getElements();
		while (radioBtns.hasMoreElements()) {
			AbstractButton btn = radioBtns.nextElement();
			if (btn.isSelected()) {
				flavor = btn.getText();
				break;
			}
		}
		int index = flavor.lastIndexOf("$");
		flavor = flavor.substring(index + 1, flavor.length());
		price = Double.parseDouble(flavor);
        
		//cost of decorators
		for (int i = 1; i < this.decoratorpanel.getComponentCount(); i++) {
			Component comp = this.decoratorpanel.getComponents()[i];
			if (comp instanceof JCheckBox) {
				JCheckBox temp = (JCheckBox) comp;
				if (temp.isSelected()) {
					decorator = temp.getText();
					int j = decorator.lastIndexOf("$");
					decorator = decorator.substring(j + 1, decorator.length());
					double decor = Double.parseDouble(decorator);
					price += decor;
				}
			}
		}
		//只要两位小数
		/*int temp = (int) (price*100);
		price = temp/100;*/
		DecimalFormat df = new DecimalFormat("#.##");
		this.total.setText("Total:$" + df.format(price));
		this.right.repaint();
	}

	private void NEWactionPerformed(ActionEvent e) {
		String total = this.total.getText();
		JOptionPane.showConfirmDialog(this, "Pay " + total
				+ " for this Ice-Cream");

		// reset default flavor
		this.BG1.clearSelection();
		Component tempcomp = this.flavorpanel.getComponent(1);
		if (tempcomp instanceof JRadioButton)
			((JRadioButton) tempcomp).setSelected(true);
		this.flavorpanel.repaint();

		// clear previous decorators setting
		for (int i = 1; i < this.decoratorpanel.getComponentCount(); i++) {
			Component comp = this.decoratorpanel.getComponents()[i];
			if (comp instanceof JCheckBox) {
				JCheckBox temp = (JCheckBox) comp;
				if (temp.isSelected()) {
					temp.setSelected(false);
					this.decoratorpanel.repaint();
				}
			}
		}
		// reset total
		this.total.setText("Total: $"
				+ this.inventory.getFlavors()[0].getPrice());
		this.right.repaint();

	}

	protected void addflavor(flavor fl){
		JRadioButton JB = new JRadioButton();
		JB.setText(fl.getName() + " $"
				+ fl.getPrice());
		JB.setName(fl.getName());
		JB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				calculationActionPerformed(evt);
			}
		});
		JB.setFont(new Font("New Roman", Font.ITALIC, 14));
		this.BG1.add(JB);
		this.flavorpanel.add(JB);
		this.flavorpanel.revalidate();
		this.flavorpanel.repaint();
	}
	
	protected void adddecorator(decorator de){
		JCheckBox JC = new JCheckBox();
		JC.setText(de.getName() + " $"
				+ de.getPrice());
		JC.setName(de.getName());
		JC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				calculationActionPerformed(evt);
			}
		});
		JC.setFont(new Font("New Roman", Font.ITALIC, 14));
		this.decoratorpanel.add(JC);
		this.decoratorpanel.revalidate();
		this.decoratorpanel.repaint();
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
