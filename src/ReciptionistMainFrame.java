import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is responisible for showing the main interface of Receptionist
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 *
 */
public class ReciptionistMainFrame extends JFrame
{

	
	boolean newCustOrderIdGot;
	JPanel MainOuterPanel ;
	
	
	
	RecipitionistCustomerDetailsPanel customerPanel;
	RecipitionistEquipmentDetailsPanel equipmentPanel;
	RecipitionistManageJobsPanel manageJobsPanel;
	RecipitionistOrdersPanel ordersPanel;
	JPanel cardPanel ;
	

	JButton customerDetailsButton ;
	JButton equipmentDetailsButton ;
	JButton manageJobsButton  ;
	JButton ordersButton  ;

	CardLayout c;
	
	JButton saveButton ;
	JButton resetButton ;
	
	JPanel lbl1;
	JPanel lbl2;
	JPanel lbl3;
	JPanel lbl4;
	
	JButton logoutButton ;
	
	boolean logOut = false;
	
	String reciptionistId; 
	
	boolean customerDataAdded;
	
	
	ReciptionistMainFrame(String idForMainGui) throws SQLException
	{
	
		newCustOrderIdGot= true;
		
		customerDataAdded= false;
		
		this.reciptionistId =  idForMainGui;
		
		c = new CardLayout();
		
		cardPanel= new JPanel(c);
		
		customerPanel = new RecipitionistCustomerDetailsPanel();

//		customerPanel.setOrderId(dBase.getNewOrderId());
		customerPanel.setOrderId();

//		customerPanel.setCustomerId(dBase.getNewCustomerId());
		customerPanel.setCustomerId();
		
		
		equipmentPanel = new RecipitionistEquipmentDetailsPanel();

		equipmentPanel.setOrderId();
//		equipmentPanel.setOrderId(customerPanel.getOrderId());

		equipmentPanel.setCustomerId();
//		equipmentPanel.setCustomerId(customerPanel.getCustomerId());
		
		
		
		
		manageJobsPanel = new RecipitionistManageJobsPanel();
		ordersPanel = new RecipitionistOrdersPanel();
		
		
		
		MainOuterPanel= new JPanel(null);
		
		customerDetailsButton  = new JButton("Customer Details");
		customerDetailsButton.setActionCommand("cButton");
		equipmentDetailsButton  = new JButton("Equipment Details");
		equipmentDetailsButton.setActionCommand("eButton");
		manageJobsButton  = new JButton("Manage Jobs");
		manageJobsButton.setActionCommand("jButton");
		ordersButton  = new JButton("Orders");
		ordersButton.setActionCommand("oButton");
		
		saveButton = new JButton("Save");
		saveButton.setVisible(false);
		
		resetButton = new JButton("Reset");
		
		
		//adding listeners to buttons
		addlistenersToButtons();
		
		
//		JLabel lbl =  new JLabel("adsklfjal;jk;ahasljkfhaskf");
//		save.setVisible(true);
		
//		JPanel pp = new JPanel();
//		pp.setPreferredSize(new Dimension(100,100));
//		pp.setBackground(Color.pink);
		
		//adding buttons in main panel
		MainOuterPanel.setPreferredSize(new Dimension(500,600));
		
		MainOuterPanel.add(customerDetailsButton);
		customerDetailsButton.setBounds(20, 150-50, 190 , 50);
		
		MainOuterPanel.add(equipmentDetailsButton);
		equipmentDetailsButton.setBounds(20, 210-50, 190 , 50);
		
		MainOuterPanel.add(manageJobsButton);
		manageJobsButton.setBounds(20, 270-50, 190 , 50);
		
		MainOuterPanel.add(ordersButton);
		ordersButton.setBounds(20, 330-50, 190 , 50);
		
		MainOuterPanel.add(saveButton);
		saveButton.setBounds(830, 640, 120 , 30);
		MainOuterPanel.add(resetButton);
		resetButton.setBounds(700, 640, 120 , 30);
		
		
//		MainOuterPanel.repaint();
//		MainOuterPanel.revalidate();
//		MainOuterPanel.setVisible(true);
		
//		MainOuterPanel.setForeground(Color.red);
//		MainOuterPanel.setBackground(Color.red);
		
		
		
		
		//adding cards
//		JPanel startDisplayPanel = new JPanel();
//		startDisplayPanel.setBackground(new java.awt.Color(204, 204, 255));

//		cardPanel.add(startDisplayPanel,"start");
		cardPanel.add(customerPanel,"customer");
		cardPanel.add(equipmentPanel,"equipment");
		cardPanel.add(manageJobsPanel,"jobs");
		cardPanel.add(ordersPanel,"orders");
		
		
		cardPanel.setBackground(Color.black);
		MainOuterPanel.add(cardPanel);
		cardPanel.setBounds(220, 45, 800 , 580);
		c.show(cardPanel, "start");
		
		//color panel behind button
		JPanel buttonsPanel = new JPanel(null);
//		buttonsPanel.setBackground(Color.black);
		MainOuterPanel.add(buttonsPanel);
		buttonsPanel.setBounds(20, 45, 190, 580);
		buttonsPanel.setBackground(new java.awt.Color(204, 204, 255));
		
		//selected button identifier
		lbl1=  new JPanel();
		lbl1.setBackground(Color.darkGray);
		MainOuterPanel.add(lbl1);
		lbl1.setBounds(210,100,10,50);
		
		lbl2=  new JPanel();
		lbl2.setBackground(Color.DARK_GRAY);
		MainOuterPanel.add(lbl2);
		lbl2.setBounds(210,160,10,50);
		
		lbl3=  new JPanel();
		lbl3.setBackground(Color.darkGray);
		MainOuterPanel.add(lbl3);
		lbl3.setBounds(210,160+60,10,50);
//		
		lbl4=  new JPanel();
		lbl4.setBackground(Color.darkGray);
		MainOuterPanel.add(lbl4);
		lbl4.setBounds(210,160+60+60,10,50);
		
		lbl1.setVisible(false);
		lbl2.setVisible(false);
		lbl3.setVisible(false);
		lbl4.setVisible(false);
		
		
		
		//logout Button instance creation and placing it on the main GUI
		logoutButton = new JButton();
			
		MainOuterPanel.add(logoutButton);
		logoutButton.setBounds(950, 15, 65 , 25);
				
		
		
        logoutButton.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(153, 0, 153));
        logoutButton.setText("Log out");
        logoutButton.setToolTipText("");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
		
		

//		20, 150-50, 190 , 50);
		
		
		
//		MainOuterPanel.setForeground(new java.awt.Color(204, 204, 255));
//		MainOuterPanel.setBackground(new java.awt.Color(204, 204, 255));
		
		
		
		
		add(MainOuterPanel);
		
//        setForeground(new java.awt.Color(204, 204, 255));
//        setBackground();
		
		
		
		this.setSize(new Dimension(1100,740));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        this.setTitle("Receptionist");
        this.setLocation(100, 12);
		
		
		
		
		
//		MainOuterPanel.setBounds( , arg1, arg2, arg3);
		
        
		
	}
	
	
	protected void logoutButtonActionPerformed(ActionEvent evt) {
		
		logOut = true;
		System.out.println("receptionist logged out");
		
	}


	
	//-------------------------------------------------------------------------------------------------------------------//
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------//
	
	
	
	
	/**
	 * adding listeners
	 */
	private void addlistenersToButtons() {
		
		
		customerDetailsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				JButton b  = (JButton) e.getSource();
				
				
					c.show(cardPanel, "customer");
					saveButton.setVisible(false);
					resetButton.setVisible(true);
					
					lbl1.setVisible(true);
					lbl2.setVisible(false);
					lbl3.setVisible(false);
					lbl4.setVisible(false);
					
				
			}
		});
		
		equipmentDetailsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				 
				if (customerPanel.checkAllFields() )
				{
					c.show(cardPanel, "equipment");
					saveButton.setVisible(true);
					resetButton.setVisible(true);
					
					lbl1.setVisible(false);
					lbl2.setVisible(true);
					lbl3.setVisible(false);
					lbl4.setVisible(false);
					
					
					equipmentPanel.setNoItemsLeftToBeSaved(customerPanel.getNoOfItems());

					equipmentPanel.setItemsList(customerPanel.getNoOfItems());
					
					
					
					System.out.println("real no of records in the customer form... "+ customerPanel.getNoOfItems());

					
					try {
						equipmentPanel.setOrderId();
//					equipmentPanel.setOrderId(customerPanel.orderId);
						
						equipmentPanel.setCustomerId();
//					equipmentPanel.setCustomerId(customerPanel.customerId);
						
					} catch (Exception e) {
						// TODO: handle exception
						e.getMessage();
					}
				}
			}
		});
		
		manageJobsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				c.show(cardPanel, "jobs");
				saveButton.setVisible(false);
				resetButton.setVisible(false);
					
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				lbl3.setVisible(true);
				lbl4.setVisible(false);
				
			}
		});
		ordersButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				c.show(cardPanel, "orders");
				saveButton.setVisible(false);
				resetButton.setVisible(false);
				
				lbl1.setVisible(false);
				lbl2.setVisible(false);
				lbl3.setVisible(false);
				lbl4.setVisible(true);
				
				
				
			}
		});
		
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				customerPanel.resetCustPanel();
				try {
					equipmentPanel.reset();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(equipmentPanel.checkSelection() && equipmentPanel.faultsSelectionCheck())
				{
					//****** Customer Record And Order ID's Assigned *************
					System.out.println("Customer DAta : " + customerDataAdded);
					// Adding Customer data
					if (customerPanel.checkAllFields() && customerDataAdded==false)
					{
							try {
								customerPanel.saveCustomerRecord();
								customerPanel.saveOrderRecord();

								
							} catch (NumberFormatException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					customerDataAdded = true;
							                                                                                                                                                               
					}
					
					System.out.println("Customer DAta : " + customerDataAdded);
			//---------------------------------------------------------------------\\
					
					//***********here Starts the Entries of Equipments ************
					
					// adding equipment data one by one
					if(equipmentPanel.getNoOfItemsLeftToBeSaved()!=0)
					{
						equipmentPanel.noOfRecordsAlreadyAdded+=1;
						equipmentPanel.setNoOfEquipListSelection();
							try {
								int equiOrder = equipmentPanel.saveRecordForEquipment();
								equipmentPanel.addFaultsInEquiOrder(equiOrder);
								
								//adding record in repair job
								equipmentPanel.saveRecInRepairJob(equiOrder, customerPanel.getEntryDate());
								equipmentPanel.setNoItemsLeftToBeSaved(equipmentPanel.getNoOfItemsLeftToBeSaved()-1);
								equipmentPanel.reset();
								
								JOptionPane j = new JOptionPane();
								j.showMessageDialog(null, "Equipment "+equipmentPanel.noOfRecordsAlreadyAdded+" successfully added");
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				
				
					//reset the Gui for next equipment entry
					if(equipmentPanel.noOfItemsLeftToBeSaved < 1)
					{
					
						try {
							
							//As in this Block we reached after completing all the repair_jobs entries so
							//before setting the new ID's we have to update the Order Due Date
							equipmentPanel.setOrderDueDate();
							
						
							//getting new customerId and OrderId for a new order
							customerPanel.setOrderId();
							customerPanel.setCustomerId();

							equipmentPanel.setOrderId();
							equipmentPanel.setCustomerId();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//Resetting Customer Panel
						customerPanel.resetCustPanel();
						
						//Showing customer Panel Again			
						c.show(cardPanel, "customer");
						saveButton.setVisible(false);
						resetButton.setVisible(true);
						
						lbl1.setVisible(true);
						lbl2.setVisible(false);
						lbl3.setVisible(false);
						lbl4.setVisible(false);
//			------------------------------------------------------------------------\\
						System.out.println("no more records to be added ");
						newCustOrderIdGot=false;
						customerDataAdded=false;
						
					}

				}
			}
		});
	}
				
				
	
}
