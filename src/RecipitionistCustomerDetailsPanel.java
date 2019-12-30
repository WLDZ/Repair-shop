import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultEditorKit.CutAction;



/**
 * This class is responisible for showing the receptionist customer GUI
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class RecipitionistCustomerDetailsPanel extends javax.swing.JPanel {

  
	/**
     * Creates new form RecipitionistCustomerDetailsPanel
     */
    public RecipitionistCustomerDetailsPanel() {
    	
    	
        initComponents();
        setDateComboBoxes();
        
        
    }

	
    
    /**
     * adding order data to the data base
     * @throws SQLException
     */
    public void saveOrderRecord() throws SQLException {
    	
    	String dayy = day.getSelectedItem().toString();
    	String monthh = month.getSelectedItem().toString();
    	String yearr = year.getSelectedItem().toString();
    	
    	String entryDateString=yearr+"-"+monthh + "-"+dayy;
    	
    	
    	dataB.saveOrder(orderId, customerId, "No", "No", entryDateString, "1111-11-11", getNoOfItems(), 1);
    	

	}
    
    
    
    /**
     * getting entry date
     * @return
     */
    public String getEntryDate()
    {

    	String dayy = day.getSelectedItem().toString();
    	String monthh = month.getSelectedItem().toString();
    	String yearr = year.getSelectedItem().toString();
    	
    	String entryDateString=yearr+"-"+monthh + "-"+dayy;
    	
    	return entryDateString;
    }
    
    
    
    /**
     * reseting customer panel
     */
    public void resetCustPanel() {
		
    	firstNameField.setText("");
    	
    	lastNameField.setText("");
		addressField.setText("");
		contactNoField.setText("");
		emailField.setText("");
		noOfItemsField.setText("");
		
		day.setSelectedIndex(0);
		month.setSelectedIndex(0);
		year.setSelectedIndex(0);
	}
    
    
    
    /**
     * adding customer data to the data base
     * @throws NumberFormatException
     * @throws SQLException
     */
    public void saveCustomerRecord() throws NumberFormatException, SQLException {
    	

    	String dayy = day.getSelectedItem().toString();
    	String monthh = month.getSelectedItem().toString();
    	String yearr = year.getSelectedItem().toString();
    	
    	String entryDateString=yearr+"-"+monthh + "-"+dayy;
    	System.out.println("data being added");
    	dataB.saveCustomerRecord(customerId,firstNameField.getText(),lastNameField.getText(),
    			addressField.getText(),contactNoField.getText(), emailField.getText(),entryDateString);
    	System.out.println("data adddedddddddddd");
		
	}
    
    
    /**
     *getting no ot items 
     * @return
     */
    public int getNoOfItems() {
    	
    	return  Integer.parseInt(noOfItemsField.getText());
		
	}
    
    
    /**
     * validating all data fields
     * @return
     */
	public boolean checkAllFields() {

		Pattern name = Pattern.compile("[A-Za-z]+");
		
		Matcher mFName = name.matcher(firstNameField.getText());
		Matcher mLName = name.matcher(lastNameField.getText());

		Pattern name1 = Pattern.compile("[0-9]+");
		Matcher noOfItems = name1.matcher(noOfItemsField.getText());
		
		JOptionPane p = new JOptionPane();
		
		if(firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
				|| addressField.getText().isEmpty() || contactNoField.getText().isEmpty() ||
				emailField.getText().isEmpty() || noOfItemsField.getText().isEmpty()
				 
				)
		{
			
			p.showMessageDialog(null, "One or more field left empty in Customer Form.");
			return false;
		}
		
		if(day.getSelectedIndex()== 0 || month.getSelectedIndex()== 0 || year.getSelectedIndex()== 0)
		{
			p.showMessageDialog(null, "Date not valid. ");
			return false;
		}
		
		if(! mFName.matches() || !mLName.matches() )
		{
			p.showMessageDialog(null, "First Name or Last Name Either Contain Invalid Characters or Digits");
			return false;
		}
		
		if(firstNameField.getText().length() > 29)
		{
			p.showMessageDialog(null, "First Name should be less than 30 Characters");
			return false;
		}
		if(lastNameField.getText().length() > 29)
		{
			p.showMessageDialog(null, "Last Name should be less than 30 Characters");
			return false;
		}
		if(addressField.getText().length() > 79)
		{
			p.showMessageDialog(null, "Address should be less than 80 Characters");
			return false;
		}
		
		if(contactNoField.getText().length() > 29)
		{
			p.showMessageDialog(null, "Contact No. should be less than 30 Characters");
			return false;
		}
		
		if(emailField.getText().length() > 49)
		{
			p.showMessageDialog(null, "Email address should be less than 50 Characters");
			return false;
		}
		
		if(noOfItemsField.getText().length() > 3 || !noOfItems.matches())
		{
			p.showMessageDialog(null, "No of items can only be a decimal of 3 decimal points");
			return false;
		}
	
		
		return true;
	}
    
    
	/**
	 * setting date combo boxes
	 */
	private void setDateComboBoxes() 
	{
		
		month.addItem(0);
		
		for(int i = 0 ; i <12; i++)
		{
			month.addItem(i+1);
		}
		
		year.addItem(0);
		
		for(int i = 2013 ; i<2050; i++)
		{
			year.addItem(i);
		}
		
		day.addItem(0);
		
		for(int i = 0 ; i <31; i++)
		{
			day.addItem(i+1);
		}
		
		year.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
//				Integer value =(Integer) month.getSelectedItem();
				
				int day_selected  = ((Integer)  day.getSelectedItem()).intValue();
				int month_selected = ((Integer) month.getSelectedItem()).intValue();
				int year_selected  = ((Integer)  year.getSelectedItem()).intValue();
				
				int day_selected_index  =  day.getSelectedIndex();
				int month_selected_index = month.getSelectedIndex();
				int year_selected_index  =  year.getSelectedIndex();
				
				
				if(year_selected%4==0)
				{
					if(((Integer) month.getSelectedItem()).intValue() == 2)
					{
						
						if(((Integer) day.getSelectedItem()).intValue()<=29 )
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day.addItem(j);
							}
							day.setSelectedIndex(day_selected_index);
							
						}
						else 
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day.addItem(j);
							}
							
						}
						
						
					}
				}
				else
				{
					if(((Integer) month.getSelectedItem()).intValue() == 2)
					{
						if(((Integer) day.getSelectedItem()).intValue()<=28 )
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day.addItem(j);
							}
							day.setSelectedIndex(day_selected_index);
							
						}
						else 
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day.addItem(j);
							}
							
						}
					}
					
				}
				
			}
		});
		
		
		month.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent eve) {
				
				
				
				Integer[] arrayFor30Days = {4,6,9,11};
				
				int day_selected  = ((Integer) day.getSelectedItem()).intValue();
				int month_selected = ((Integer) month.getSelectedItem()).intValue();
				int year_selected  = ((Integer) year.getSelectedItem()).intValue();
				
				int day_selected_index  =  day.getSelectedIndex();
				int month_selected_index = month.getSelectedIndex();
				int year_selected_index  =  year.getSelectedIndex();
				
				boolean upday = false;
				
				for(int i = 0 ;i <arrayFor30Days.length; i++)
				{
					if(month_selected==arrayFor30Days[i].intValue())
					{
						if(day_selected<=30 )
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day.addItem(j);
							}
							day.setSelectedIndex(day_selected_index);
							upday=true;
							break;
						}
						else
						{
							day.setModel(new DefaultComboBoxModel<Integer>());
							day.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day.addItem(j);
							}
							upday=true;
							break;
							
						}
					}
					
				}
				if(month_selected==2 && upday==false)
				{
					if( day_selected<=29 )
					{
						day.setModel(new DefaultComboBoxModel<Integer>());
						day.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day.addItem(j);
						}
						day.setSelectedIndex(day_selected_index);
						upday=true;
					}
					else
					{
						day.setModel(new DefaultComboBoxModel<Integer>());
						day.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day.addItem(j);
						}
						upday=true;
					}
				}
				else if(upday==false)
				{
					day.setModel(new DefaultComboBoxModel<Integer>());
					day.addItem(0);
					for(int j = 1;j<=31 ;j++)
					{
						day.addItem(j);
					}
					day.setSelectedIndex(day_selected_index);
				}
				
				
				
			}
			});
		
		
		
			

	
	}    

    
    
	/**
	 * setting customer ID
	 * @throws SQLException
	 */
	public void setCustomerId() throws SQLException {
		this.customerId = dataB.getNewCustomerId();
	}

	
	/**
	 * setting order ID
	 * @throws SQLException
	 */
	public void setOrderId() throws SQLException {
		this.orderId = dataB.getNewOrderId();
		
		orderIdField.setText(orderId + "");
		orderIdField.setEnabled(false);
	}
	
	
	
	/**
	 * getting customer ID
	 * @return
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	
	
	/**
	 * getting order ID
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}
	
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * 
     */
    private void initComponents() {
    	
    	
    	
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        orderIdField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        contactNoField = new javax.swing.JTextField();
        day = new javax.swing.JComboBox();
        month = new javax.swing.JComboBox();
        year = new javax.swing.JComboBox();
        canvas1 = new java.awt.Canvas();
        emailField = new javax.swing.JTextField();
        noOfItemsField = new javax.swing.JTextField();

        
        
        
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Order ID:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 31, 123));
        jLabel2.setText("First Name:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(9, 31, 123));
        jLabel3.setText("Last Name:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(9, 31, 123));
        jLabel4.setText("Address:");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 31, 123));
        jLabel5.setText("Contact No.:");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 31, 123));
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(9, 31, 123));
        jLabel7.setText("No. of Items:");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 31, 123));
        jLabel8.setText("Order Entry day:");

        orderIdField.setBackground(new java.awt.Color(203, 226, 238));

        firstNameField.setBackground(new java.awt.Color(203, 226, 238));

        lastNameField.setBackground(new java.awt.Color(203, 226, 238));

        addressField.setBackground(new java.awt.Color(203, 226, 238));

        contactNoField.setBackground(new java.awt.Color(203, 226, 238));


        emailField.setBackground(new java.awt.Color(203, 226, 238));

        noOfItemsField.setBackground(new java.awt.Color(203, 226, 238));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(orderIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(firstNameField)
                    .addComponent(lastNameField)
                    .addComponent(addressField)
                    .addComponent(contactNoField)
                    .addComponent(emailField)
                    .addComponent(noOfItemsField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(orderIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(contactNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(noOfItemsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(226, Short.MAX_VALUE))
        );
    }                     


    // Variables declaration 
    
    Database dataB = new Database();
    
    boolean check = false;
    
    public int customerId;
    public int orderId;
    
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox day;
    private javax.swing.JComboBox month;
    private javax.swing.JComboBox year;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField orderIdField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField contactNoField;
    private javax.swing.JTextField noOfItemsField;
    private javax.swing.JTextField emailField;
    // End of variables declaration                   
}
