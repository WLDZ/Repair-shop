import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is responisible for showing the receptionist orders GUI
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class RecipitionistOrdersPanel extends javax.swing.JPanel {

	
    /**
     * Creates new form RecipitionistOrdersPanel
     * @throws SQLException 
     */
    public RecipitionistOrdersPanel() throws SQLException {
        initComponents();
        setComponentsData();
        addListenersToLists();
        addListenertoSearchButton();
        
    }

    
    
    
    /**
     * adding listener
     */
    private void addListenertoSearchButton() {
    	searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				allOrdersList.clearSelection();
				completedOrdersList.clearSelection();
				String temp[] = null;
				try {
					temp = dBase.getOrd_Details(Integer.parseInt(searchTextField.getText().toString()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				
				orderIdLabel.setText(searchTextField.getText());
				
				completedItemsLabel.setText(temp[0]);
				totalItemsLabel.setText(temp[1]);
				customerFirstName.setText(temp[2]);
				customerLastName.setText(temp[3]);
				customerAddressTextArea.setText(temp[4]);
				
				contactNoLabel.setText(temp[5]);
				emailLabel.setText(temp[6]);
				
				orderEntryDateLabel.setText(temp[7]);
				
				
				
				
				
			}
		});
	}
    
    
    /**
     * setting the components data
     * @throws SQLException
     */
    private void setComponentsData() throws SQLException {

        
        completedOrdersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dBase.getCompletedOrders();
            	
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
        
        allOrdersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dBase.getAllOrders();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
	}



    /**
     *adding listeners to to list    
     */
    private void addListenersToLists() {

    	allOrdersList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				if(!allOrdersList.isSelectionEmpty())
				{
					searchTextField.setText("");
						String[] temp = null;
						try {
							temp =dBase.getOrd_Details(Integer.parseInt(allOrdersList.getSelectedValue().toString()));
							
							
						} catch (NumberFormatException e) {
							
							e.printStackTrace();
							
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						
						
						completedOrdersList.clearSelection();
						
						
						orderIdLabel.setText(allOrdersList.getSelectedValue().toString());
						completedItemsLabel.setText(temp[0]);
						totalItemsLabel.setText(temp[1]);
						customerFirstName.setText(temp[2]);
						customerLastName.setText(temp[3]);
						customerAddressTextArea.setText(temp[4]);
						
						contactNoLabel.setText(temp[5]);
						emailLabel.setText(temp[6]);
						
						orderEntryDateLabel.setText(temp[7]);
						
				}
			}
		});
    	
    	completedOrdersList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String[] temp = null;
				try {

					if(!completedOrdersList.isSelectionEmpty())
					{
						searchTextField.setText("");
						temp =dBase.getOrd_Details(Integer.parseInt(completedOrdersList.getSelectedValue().toString()));
						
						allOrdersList.clearSelection();
						
						
						orderIdLabel.setText(completedOrdersList.getSelectedValue().toString());
						completedItemsLabel.setText(temp[0]);
						totalItemsLabel.setText(temp[1]);
						customerFirstName.setText(temp[2]);
						customerLastName.setText(temp[3]);
						customerAddressTextArea.setText(temp[4]);
						
						contactNoLabel.setText(temp[5]);
						emailLabel.setText(temp[6]);
						
						orderEntryDateLabel.setText(temp[7]);
					}		
					
				} catch (NumberFormatException e) {
					
					e.printStackTrace();
				
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		});
    	
    	
	}
    





    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * @throws SQLException 
     */
    private void initComponents() {

    	dBase = new Database();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        completedOrdersList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allOrdersList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        orderIdLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        completedItemsLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        totalItemsLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        
       
        customerFirstName = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        customerLastName = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        customerAddressTextArea = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        contactNoLabel = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        orderEntryDateLabel = new javax.swing.JLabel();
        recieveNotifiButton = new javax.swing.JButton();
        deliveryButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Completed Orders");

        completedOrdersList.setBackground(new java.awt.Color(203, 226, 238));
        completedOrdersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(completedOrdersList);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 31, 123));
        jLabel2.setText("All Orders");

        allOrdersList.setBackground(new java.awt.Color(203, 226, 238));
        allOrdersList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(allOrdersList);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(9, 31, 123));
        jLabel3.setText("Search By Order ID");

        searchTextField.setBackground(new java.awt.Color(203, 226, 238));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 31, 123));
        jLabel5.setText("Order Details");

        jLabel6.setBackground(new java.awt.Color(203, 226, 238));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 31, 123));
        jLabel6.setText("Order ID:");

        orderIdLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        orderIdLabel.setText("");

        jLabel8.setBackground(new java.awt.Color(203, 226, 238));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 31, 123));
        jLabel8.setText("Completed Items:");

        completedItemsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        completedItemsLabel.setText("");

        jLabel10.setBackground(new java.awt.Color(203, 226, 238));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(9, 31, 123));
        jLabel10.setText("Total items:");

        totalItemsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalItemsLabel.setText("");

        jLabel12.setBackground(new java.awt.Color(203, 226, 238));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(9, 31, 123));
        jLabel12.setText("Customer's First Name:");

        customerFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerFirstName.setText("");

        jLabel14.setBackground(new java.awt.Color(203, 226, 238));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(9, 31, 123));
        jLabel14.setText("Customer's Last Name:");

        customerLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customerLastName.setText("");

        jLabel16.setBackground(new java.awt.Color(203, 226, 238));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(9, 31, 123));
        jLabel16.setText("Customer's Address:");

        customerAddressTextArea.setBackground(new java.awt.Color(203, 226, 238));
        customerAddressTextArea.setColumns(20);
        customerAddressTextArea.setForeground(new java.awt.Color(0,0,0));
        customerAddressTextArea.setRows(5);
        customerAddressTextArea.setEnabled(false);
        
        jScrollPane3.setViewportView(customerAddressTextArea);
        

        jLabel17.setBackground(new java.awt.Color(203, 226, 238));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(9, 31, 123));
        jLabel17.setText("Contact No:");

        contactNoLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contactNoLabel.setText("");

        jLabel19.setBackground(new java.awt.Color(203, 226, 238));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(9, 31, 123));
        jLabel19.setText("Email:");

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emailLabel.setText("");

        jLabel21.setBackground(new java.awt.Color(203, 226, 238));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(9, 31, 123));
        jLabel21.setText("Order's Entry Date:");

        orderEntryDateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        orderEntryDateLabel.setText("");

        recieveNotifiButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        recieveNotifiButton.setForeground(new java.awt.Color(153, 0, 0));
        recieveNotifiButton.setText("Recieve Notification");
        recieveNotifiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recieveNotifiButtonActionPerformed(evt);
            }
        });
        
        recieveNotifiButton.setVisible(false);
        
        

        deliveryButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        deliveryButton.setForeground(new java.awt.Color(204, 204, 0));
        deliveryButton.setText("Deliver");
        deliveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryButtonActionPerformed(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(9, 31, 123));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(customerLastName))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(customerFirstName))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalItemsLabel))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(completedItemsLabel))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(orderIdLabel)))
                                    .addGap(17, 17, 17)))
                            .addComponent(jLabel16))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel17))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(contactNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(orderEntryDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(deliveryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(recieveNotifiButton)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap(118, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(orderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(contactNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(completedItemsLabel)
                    .addComponent(jLabel19)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(totalItemsLabel)
                    .addComponent(jLabel21)
                    .addComponent(orderEntryDateLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(customerFirstName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(customerLastName))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(recieveNotifiButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliveryButton)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void recieveNotifiButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	    	
    	
    }                                        

    private void deliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        

    	
    	if(allOrdersList.isSelectionEmpty()==true && completedOrdersList.isSelectionEmpty()==true)
    	{
    		JOptionPane p=  new JOptionPane();
        	p.showMessageDialog(this, "Please Select an order first");
    		
    	}
    	else if (allOrdersList.isSelectionEmpty()==false) 
    	{
    		try {
    			
				dBase.UpdateOrderStatusAsYes(allOrdersList.getSelectedValue().toString());
			
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		JOptionPane j1=  new JOptionPane();
        	j1.showMessageDialog(this, "Order status has been set as delivered.");
    		
    	}
    	else if(completedOrdersList.isSelectionEmpty()==false)
    	{    		
           	try {
           		
				dBase.UpdateOrderStatusAsYes(completedOrdersList.getSelectedValue().toString());
			
           	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
           	JOptionPane j2=  new JOptionPane();
           	j2.showMessageDialog(this, "Order status has been set as delivered.");
    	}
    	
    }                                        

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        


    // Variables declaration 
    
    Database dBase;
    private javax.swing.JButton recieveNotifiButton;
    private javax.swing.JButton deliveryButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel totalItemsLabel;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel customerFirstName;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel customerLastName;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel contactNoLabel;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel orderEntryDateLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel orderIdLabel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel completedItemsLabel;
    private javax.swing.JList completedOrdersList;
    private javax.swing.JList allOrdersList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea customerAddressTextArea;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration            
    
    
	
}
