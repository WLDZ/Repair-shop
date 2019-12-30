import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * This class is responisible for showing the receptionist Equipment Details GUI
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class RecipitionistEquipmentDetailsPanel extends javax.swing.JPanel {




	/**
     * Creates new form RecipitionistEquipmentDetailsPanel
     * @throws SQLException 
     */
    public RecipitionistEquipmentDetailsPanel() throws SQLException {
        initComponents();
        this.noOfRecordsAlreadyAdded=0;
    }
    
    
    
    /**
     * adding faults in equi order
     * @param equi_Order
     * @throws SQLException
     */
    public void addFaultsInEquiOrder(int equi_Order) throws SQLException {
    	
    	int eq_Order = dataB.getNewEquiOrder();
    	int size =faultsList.getModel().getSize();
    	String type = equipTypeList.getSelectedValue().toString();
    	for(int i = 0 ; i <size ;i ++)
    	{
    		if(faultBool[i]==true)
    		{
    			String fault =faultsList.getModel().getElementAt(i).toString();
    			int fid =dataB.get_FID_ByName(fault, type);
    			dataB.saveGivenEquipFaults(equi_Order , fid);
    		}
    	}
	}

    
    
    /**
     * getting due Date
     * @param entryDate
     * @param tID
     * @return
     * @throws SQLException
     */
    private String getDueDate(String entryDate , int tID) throws SQLException {
    	System.out.println("Entry Date: " + entryDate);

    	String bookTillDate = dataB.getBookTillDate(tID); 
    	System.out.println("BookTillDAte : " + bookTillDate);
    	if(bookTillDate.equals("1111-11-11"))
    	{
    		bookTillDate = entryDate;
    		System.out.println("Equals hua tha!!!!!!!!!!!!!1");
    	}
    	long diff;
		try {
			diff = findDiff(entryDate, bookTillDate);
			if(diff < 0)
			{
				bookTillDate = entryDate;
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	String type = equipTypeList.getSelectedValue().toString();
    	int size =faultsList.getModel().getSize();
    	int fixdays = 0;
    	for(int i = 0 ; i <size ; i++)
    	{
    		System.out.println("faultBool["+i+"] : " + faultBool[i]);
    		if(faultBool[i]==true)
    		{
    			String fault = faultsList.getModel().getElementAt(i).toString();
    			int fixTime = dataB.getFixTimeofFaults(fault, type);
    			fixdays += fixTime;
    			System.out.println("fixTime specific : " +fixTime);
    		}
    	}
    	
    	//adding days into date and getting new
 	   String dt = bookTillDate;  // Start date
 	   SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
 	   Calendar c = Calendar.getInstance();
 	   try{
 		   c.setTime(dateFormate.parse(dt));
 	   }catch (Exception e) {
		// TODO: handle exception
	}
 	   c.add(Calendar.DATE, fixdays);  // number of days to add
 	   dt = dateFormate.format(c.getTime());  // dt is now the new date
 	   System.out.println("Due_Date : "+ dt);
 	   return dt;
    }
    
    
    
    
    /**
     * getting equipment ID
     * @return
     * @throws SQLException
     */
    public int getEID() throws SQLException {
    	
    	return dataB.getEId(equipTypeList.getSelectedValue().toString(), 
    			manufaturerList.getSelectedValue().toString(), modelList.getSelectedValue().toString());
	}
    
    
    
    /**
     * setting No of items left to be saved
     * @param i
     */
    public void  setNoItemsLeftToBeSaved(int i) {
    	noOfItemsLeftToBeSaved = i;
		
	}
    
    
    
    /**
     * getting no of items to be saved
     * @return
     */
    public int getNoOfItemsLeftToBeSaved() {
		return noOfItemsLeftToBeSaved;
	}
    
    
    
    
   /**
    * Adding in Repair_jobs Table
    * @param eq_order
    * @param entryDate
    * @throws SQLException
    */
   public void saveRecInRepairJob(int eq_order, String entryDate) throws SQLException {

   		int tid = Integer.parseInt(technicianList.getSelectedValue().toString());

   		String due_date = getDueDate(entryDate, tid);
		
   		
   		dataB.saveReapirJob(dataB.getNewRID(), eq_order, tid , due_date, entryDate, "No", descriptionTextArea.getText(), "Yes");
		
   		dataB.updateNoOfJObs(tid);
	
   }
   
   
   /**
    * setting order due date
    * @throws SQLException
    */
   public void setOrderDueDate() throws SQLException {
	   String ordDueDate = dataB.calcOrderDueDate(getOrderId());
	   
	   //As initially default Due Date value entered in the table so need to change it...
	   dataB.updateOrderDueDate(getOrderId(), ordDueDate);
   }
   
   
   /**
    * Resetting the entire GUI for next equip addition
    * @throws SQLException
    */
  public void reset() throws SQLException {
	  
	  equipTypeList.setModel(new javax.swing.AbstractListModel() {
          
          String[] strings = dataB.getEqui_Types();
          public int getSize() { return strings.length; }
          public Object getElementAt(int i) { return strings[i]; }
      });
	  
	  manufaturerList.setListData(new String[1]);
	  modelList.setListData(new String[1]);
	  
	  faultsList.setListData(new String[1]);
	  bGroup.clearSelection();
	  descriptionTextArea.setText("");
	  
	  techName.setText("");
	  techSpeciality.setText("");
	  currentNoOfJobs.setText("");
	  techBookedTill.setText("");
	  techStatus.setText("");
	  
	  technicianList.clearSelection();
	  
//	  selectEquipList.clearSelection();
	  
	  
		
  	
	}
  
  

  /**
   * checking for selection
   * @return
   */
  public boolean checkSelection() {
    	
		if(equipTypeList.isSelectionEmpty() || modelList.isSelectionEmpty() 
				|| manufaturerList.isSelectionEmpty() || technicianList.isSelectionEmpty()
				|| technicianList.isSelectionEmpty())
		{

			JOptionPane.showMessageDialog(null, "One or More Field's Selection Left Empty!!!");
			return false;
		}

		
		return true;
	}
  
  /**
   * checking faults selection
   * @return
   */
    public boolean faultsSelectionCheck() {
    	
    	if(! technicianList.isSelectionEmpty())
		{
			int count = 0;
			for(int i = 0; i < faultBool.length; i++)
			{
				if(faultBool[i] == true)
					count++;
			}
			if(count < 1)
			{
				JOptionPane.showMessageDialog(null, "Faults not Selected as Yes from Radio Button!!");
				return false;
			}
		}
    	
		return true;
	}
    
    

    /**
     * saving record for equipment
     * @param equiOrder
     * @param OrderId
     * @param EID
     * @throws SQLException
     */    
    public int saveRecordForEquipment() throws SQLException{// int OrderId, int EID) throws SQLException {
    	
    	int eq_order = dataB.getNewEquiOrder();
    	dataB.saveEquiInOrder(eq_order, this.getOrderId(), this.getEID());
	
    	return eq_order;
    }
    
    /**
     * setter for number of items
     * @param noOfItems
     */
    public void setItemsList(int noOfItems) {
    	
    	String list[] = new String[noOfItems];
    	for (int i = 0 ; i <list.length ; i++)
    	{
    		list[i] = (i+1)+"";
    	}
    	
    	selectEquipList.setListData(list);
    	System.out.println("nnnnnnnnnnnnnnnnn"+noOfRecordsAlreadyAdded);
    	selectEquipList.setSelectedIndex(noOfRecordsAlreadyAdded);
    	
    }
    
    
    
    /**
     * setting no of equipments
     */
    public void setNoOfEquipListSelection() {
    	System.out.println("no of records for listttttt"+ noOfRecordsAlreadyAdded);
    	selectEquipList.setSelectedIndex(noOfRecordsAlreadyAdded);
		
	}
    
    
    
    /**
     * setting customer ID
     * @throws SQLException
     */
    public void setCustomerId() throws SQLException
    {
   	 	this.customerId= dataB.getNewOrderId();
    }
    
    
    /**
     * setting order ID 
     * 
     * @throws SQLException
     */
    public void setOrderId() throws SQLException
    {
    	this.orderId = dataB.getNewOrderId();
    }
    
    
    
    
    /**
     * getting customer ID
     * @return
     */
    public int getCustomerId() {
		return customerId;
	}
    
    /**
     * getter for order Id
     * @return
     */
    public int getOrderId() {
		return orderId;
	}
    

    /**
     * some code is auto generated by Net Beans 8.0
     * This method is called from within the constructor to initialize the form.
     */                       
    private void initComponents() throws SQLException {
    	
    	instance = this;
    	
    	noOfRecordsAlreadyAdded = 0;
        jPanel1 = new javax.swing.JPanel();
        selectEquipListScroll = new javax.swing.JScrollPane();
        selectEquipList = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        equipTypeListScroll = new javax.swing.JScrollPane();
        equipTypeList = new javax.swing.JList();
        jLabel17 = new javax.swing.JLabel();
        manufaturerListScroll = new javax.swing.JScrollPane();
        manufaturerList = new javax.swing.JList();
        jLabel18 = new javax.swing.JLabel();
        modelListScroll = new javax.swing.JScrollPane();
        modelList = new javax.swing.JList();
        jLabel19 = new javax.swing.JLabel();
        faultsListScroll = new javax.swing.JScrollPane();
        faultsList = new javax.swing.JList();
        jLabel20 = new javax.swing.JLabel();
        descriptionTextAreaScroll = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        technicianListScroll = new javax.swing.JScrollPane();
        technicianList = new javax.swing.JList();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        techName = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        techSpeciality = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        currentNoOfJobs = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        techBookedTill = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        techStatus = new javax.swing.JLabel();
        addNewManufacturerButton = new javax.swing.JButton();
        addNewEquipmentButton = new javax.swing.JButton();
        addNewModelButton = new javax.swing.JButton();
        addNewFaultsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        faultYesButton = new javax.swing.JRadioButton();
        faultsNoButton = new javax.swing.JRadioButton();
        
        
        
        
        bGroup = new ButtonGroup();
        bGroup.add(faultYesButton);
        bGroup.add(faultsNoButton);
         
        
        
        

        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(new java.awt.Color(204, 204, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(204, 204, 255));

        
        
        selectEquipList.setBackground(new java.awt.Color(203, 226, 238));

        selectEquipList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {""};
//            	{ "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
        selectEquipList.setEnabled(false);
        
        
        selectEquipListScroll.setViewportView(selectEquipList);

        jLabel15.setForeground(new java.awt.Color(9, 31, 123));
        jLabel15.setText("Select Equipment");

        jLabel16.setForeground(new java.awt.Color(9, 31, 123));
        jLabel16.setText("Equipments");

        
        
        //type of equipmemnt's list
        equipTypeList.setBackground(new java.awt.Color(203, 226, 238));
        equipTypeList.setModel(new javax.swing.AbstractListModel() {
            
            String[] strings = dataB.getEqui_Types();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
        equipTypeList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

				try {
					manufaturerList.setModel(new javax.swing.AbstractListModel() {
					    String[] strings = dataB.getManuf(equipTypeList.getSelectedValue().toString());
						
					    public int getSize() { return strings.length; }
					    public Object getElementAt(int i) { return strings[i]; }
					    
					});
					
					
					modelList.setModel(new javax.swing.AbstractListModel() {
					    String[] strings ={""};
						
					    public int getSize() { return strings.length; }
					    public Object getElementAt(int i) { return strings[i]; }
					    
					});
					
					

					faultsList.setModel(new javax.swing.AbstractListModel() {
				    String[] strings =dataB.getEqui_Faults(equipTypeList.getSelectedValue().toString());
				   
				   	public int getSize() { return strings.length; }
				    public Object getElementAt(int i) { return strings[i]; }
				
					});
					
					faultBool = new Boolean[faultsList.getModel().getSize()];
					for (int i = 0 ; i <faultBool.length ;i++)
					{
						faultBool[i]= false;
					}
					faultsList.addMouseListener(new  MouseListener() {
						
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

							if(!faultsList.isSelectionEmpty())
							{
								int indx = faultsList.getSelectedIndex();
								if(faultBool[indx]== true)
								{
									faultYesButton.setSelected(true);
								}
								else 
								{
									faultsNoButton.setSelected(true);
									
								}
							}
						}
					});
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
				
			}
		});
        
        
        equipTypeListScroll.setViewportView(equipTypeList);

        
        
        
        
        jLabel17.setForeground(new java.awt.Color(9, 31, 123));
        jLabel17.setText("Manufacturer");

        
        
        
        
        // setting up the manufacturer list's model and listener
        manufaturerList.setBackground(new java.awt.Color(203, 226, 238));
        manufaturerList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        manufaturerList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
		        if(!manufaturerList.isSelectionEmpty()){
		        	
		        	modelList.setModel(new javax.swing.AbstractListModel() {
		        		String[] strings = dataB.getEqui_Models(manufaturerList.getSelectedValue().toString());
		        		public int getSize() { return strings.length; }
		        		public Object getElementAt(int i) { return strings[i]; }
		        	});				
		        }
			}
		});
        manufaturerListScroll.setViewportView(manufaturerList);

        
        
        
        jLabel18.setForeground(new java.awt.Color(9, 31, 123));
        jLabel18.setText("Model");

        
        
        
        
        
        
        modelList.setBackground(new java.awt.Color(203, 226, 238));
        modelList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });


        
        
        modelListScroll.setViewportView(modelList);

        
        
        jLabel19.setForeground(new java.awt.Color(9, 31, 123));
        jLabel19.setText("Faults");

        
        
        faultsList.setBackground(new java.awt.Color(203, 226, 238));
        faultsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {""};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        faultsListScroll.setViewportView(faultsList);


        
        
        
        jLabel20.setForeground(new java.awt.Color(9, 31, 123));
        jLabel20.setText("Description");

        descriptionTextArea.setBackground(new java.awt.Color(203, 226, 238));
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextAreaScroll.setViewportView(descriptionTextArea);

        jLabel21.setForeground(new java.awt.Color(9, 31, 123));
        jLabel21.setText("Assign Technician");

        technicianList.setBackground(new java.awt.Color(203, 226, 238));
        
        
        
        
        technicianList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dataB.get_All_TechID();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        technicianList.addMouseListener(new MouseListener() {
			
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

				int selectedValue;
				String array[];
				
				selectedValue=Integer.parseInt(technicianList.getSelectedValue().toString());
				
				try {
					
					String bkTill = dataB.getBookTillDate(selectedValue);
					array=dataB.getAllTechData(selectedValue);
					techName.setText( array[1]+" "+ array[2] + "");
					techSpeciality.setText(array[3]);
					currentNoOfJobs.setText(array[4]);
					
					techBookedTill.setText(bkTill);
					
					techStatus.setText(array[5]);
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        
        
        
        
        
        
        technicianListScroll.setViewportView(technicianList);

        jLabel22.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(9, 31, 123));
        jLabel22.setText("Technicain Details");
        jLabel22.setToolTipText("");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(9, 31, 123));
        jLabel25.setText("Name");

        techName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        techName.setText("");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(9, 31, 123));
        jLabel27.setText("Speciality");

        techSpeciality.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        techSpeciality.setText("");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 31, 123));
        jLabel8.setText("Current No. of Jobs:");

        currentNoOfJobs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        currentNoOfJobs.setText("");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(9, 31, 123));
        jLabel11.setText("Booked till:");

        techBookedTill.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        techBookedTill.setText("");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(9, 31, 123));
        jLabel13.setText("Current Status:");

        techStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        techStatus.setForeground(new java.awt.Color(51, 153, 0));
        techStatus.setText("");

        addNewManufacturerButton.setForeground(new java.awt.Color(9, 31, 123));
        addNewManufacturerButton.setText("Add New Manufacturer");
        addNewManufacturerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewManufacturerButtonActionPerformed(evt);
            }
        });

        addNewEquipmentButton.setForeground(new java.awt.Color(9, 31, 123));
        addNewEquipmentButton.setText("Add New Equipment");
        addNewEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewEquipButtonActionPerformed(evt);
            }
        });

        addNewModelButton.setForeground(new java.awt.Color(9, 31, 123));
        addNewModelButton.setText("Add New Model");
        addNewModelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewModelButtonActionPerformed(evt);
            }
        });

        addNewFaultsButton.setForeground(new java.awt.Color(9, 31, 123));
        addNewFaultsButton.setText("Add New Faults");
        addNewFaultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addNewFaultsButtonActionPerformed();
			}
		});

        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Add selected fault");

        faultYesButton.setText("yes");
        faultYesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int indx = faultsList.getSelectedIndex();
				faultBool[indx]= true;
			}
		});
        

        faultsNoButton.setText("no");
        faultsNoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	int indx = faultsList.getSelectedIndex();
				faultBool[indx]= false;
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20)
                    .addComponent(jLabel15)
                    .addComponent(descriptionTextAreaScroll)
                    .addComponent(selectEquipListScroll)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addNewEquipmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(equipTypeListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manufaturerListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(addNewManufacturerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewModelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(faultsListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addNewFaultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(faultYesButton)
                                .addGap(18, 18, 18)
                                .addComponent(faultsNoButton)))))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(technicianListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentNoOfJobs))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(techSpeciality))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(techName))
                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(techStatus))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(techBookedTill))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectEquipListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(equipTypeListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(manufaturerListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(techName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(techSpeciality))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(currentNoOfJobs))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(techBookedTill)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addNewManufacturerButton)
                                .addComponent(addNewEquipmentButton)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(modelListScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                    .addComponent(faultsListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addNewModelButton)
                                    .addComponent(addNewFaultsButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(faultYesButton)
                                    .addComponent(faultsNoButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionTextAreaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(technicianListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 90, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(techStatus))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    
    }
    

    
    
    /**
     * adding listener to add new equip
     * @param evt
     */
    private void addNewEquipButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
    
    	addEquiFrame = new AddEquipType(this);
    	addEquiFrame.setVisible(true);
    	
   	}
    
    
    
    
    /**
     * adding equipment
     * @param typ
     * @param manuf
     * @param mdl
     * @throws SQLException
     */
    public void addEquipment(String typ,String manuf, String mdl) throws SQLException
    {
    	dataB.addNewEquipInDataBase(typ, manuf, mdl);
    }
    
    
    /**
     * adding new manufacturer
     * @param evt
     */
    private void addNewManufacturerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:

    	if(!equipTypeList.isSelectionEmpty()){
    		
    		addManuFrame = new AddManufacturer(this, equipTypeList.getSelectedValue().toString());
    		addManuFrame.setVisible(true);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(this, "First Select the Equipment Type!!!");
    	}
    }                                        

    /**
     * adding listener to add new model button
     * @param evt
     */
    private void addNewModelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	if(!equipTypeList.isSelectionEmpty() && !manufaturerList.isSelectionEmpty()){

    		addModelFrame = new AddModel(this, equipTypeList.getSelectedValue().toString(), 
    			manufaturerList.getSelectedValue().toString());
    		addModelFrame.setVisible(true);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(this, "Equipment Type or Manufacturer Selection Left Empty!!!");
    	}
    }                                        

   
    
    /**
     * adding listener to faults no button
     * @param evt
     */
    private void faultsNoButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    	
    }                                             
    
    /**
     * adding new faults
     */
    private void addNewFaultsButtonActionPerformed() {
		
    	if( !equipTypeList.isSelectionEmpty())
    	{
    		addFaultFrame = new AddNewFault(this, equipTypeList.getSelectedValue().toString());
    		addFaultFrame.setVisible(true);
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(this, "First Select the Equipment Type!!!");
    	}
	}
    
    
    
    
    
    /**
     * adding faults in Database
     * @param fltName
     * @param approx
     * @param typ
     * @throws SQLException
     */
    public void AddFaultInDB(String fltName, String approx, String typ) throws SQLException {
		dataB.addFaultInDB(fltName, approx, typ);
	}
    
    
    /**
     * reseting fault's list
     * @throws SQLException
     */
    public void resetFaultList() throws SQLException
    {
    	faultsList.setModel(new javax.swing.AbstractListModel() {
		    String[] strings =dataB.getEqui_Faults(equipTypeList.getSelectedValue().toString());
		   
		   	public int getSize() { return strings.length; }
		    public Object getElementAt(int i) { return strings[i]; }
		
			});
			
    	Boolean[] tmp = faultBool;
    	
    	
    	faultBool = new Boolean[faultsList.getModel().getSize()];
		for (int i = 0 ; i <tmp.length ;i++)
		{
			faultBool[i]= tmp[i];
		}
		faultBool[tmp.length] = false;
    }
    

    
    /**
     * findng the difference between two dates
     * @param d1
     * @param d2
     * @return
     * @throws ParseException
     */
    private long findDiff(String d1, String d2) throws ParseException {

    //get difference in number of days between two dates
 	   String str_date1 = d1;
 	   String str_time1 = "12:00 AM";

 	   String str_date2 = d2;
 	   String str_time2 = "12:00 AM" ;

 	   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
 	   Date date1 = formatter.parse(str_date1 + " " + str_time1);
 	   Date date2 = formatter.parse(str_date2 + " " + str_time2);

 	   //Get msec from each, and subtract.
 	   long diff = date2.getTime() - date1.getTime();

 	   System.out.println("Difference In Days: " + (diff / (1000 * 60 * 60 * 24)));		
 	   long rzl = (diff / (1000 * 60 * 60 * 24));
 	   return rzl;
	}
    
//    ---------------------------------------------------------------------------------------------------
    // Variables declaration 
    
    private RecipitionistEquipmentDetailsPanel instance;
    
    int noOfRecordsAlreadyAdded;
    
    int customerId;
    int orderId;
    
    
    int noOfItemsLeftToBeSaved;
    
    private Database dataB = new Database();
    private Boolean[] faultBool;
    private ButtonGroup bGroup;

    
    private AddEquipType addEquiFrame;
    private AddManufacturer addManuFrame;
    private AddModel addModelFrame;
    private AddNewFault addFaultFrame;
    
    
    
    private javax.swing.JButton addNewManufacturerButton;
    private javax.swing.JButton addNewEquipmentButton;
    private javax.swing.JButton addNewModelButton;
    private javax.swing.JButton addNewFaultsButton;
    
    private javax.swing.JLabel currentNoOfJobs;
    private javax.swing.JLabel techBookedTill;
    private javax.swing.JLabel techStatus;
    private javax.swing.JLabel techName;
    private javax.swing.JLabel techSpeciality;

    public javax.swing.JList modelList;
    private javax.swing.JList faultsList;
    private javax.swing.JList technicianList;
    private javax.swing.JList selectEquipList;
    private javax.swing.JList equipTypeList;
    private javax.swing.JList manufaturerList;

    private javax.swing.JRadioButton faultYesButton;	
    private javax.swing.JRadioButton faultsNoButton;
    private javax.swing.JScrollPane manufaturerListScroll;
    private javax.swing.JScrollPane modelListScroll;

    
    private javax.swing.JScrollPane descriptionTextAreaScroll;
    private javax.swing.JScrollPane technicianListScroll;
    private javax.swing.JScrollPane selectEquipListScroll;
    private javax.swing.JScrollPane equipTypeListScroll;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JScrollPane faultsListScroll;

    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration     
    
    

    
}
