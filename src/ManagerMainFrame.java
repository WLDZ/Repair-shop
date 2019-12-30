import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;




	/**
	 * This class is responisible for showing the manager's main GUI
	 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
	 *
	 */
	public class ManagerMainFrame extends javax.swing.JFrame {

		
    /**
		 * 
		 */
		private static final long serialVersionUID = 4271519942117706683L;

	private String Id;
	
	/**
     * Creates new form ManagerMainFrame
     */
    public ManagerMainFrame(String ID) {
    	this.Id = ID;
        initComponents();
    	this.setVisible(true);
    	this.setSize(800, 600);
    	this.setTitle("Manager");
    	
        this.setLocation(260,100);
    	
    	addListenersToDateComboxes();
    	populateTechList();
    	addListenerToTechList();
    	addListenerToButtons();
    	
    }
    
    
    
    /**
     * Adding listeners to Buttons
     */
    private void addListenerToButtons() {
    	
    	//yes button
    	yesButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!techList.isSelectionEmpty())
				{
					int selectedIndex = techList.getSelectedIndex();
					try {
				
						
						dBase.updateTechnicianStatus("available", techList.getSelectedValue().toString());
					
						populateTechList();
						techList.setSelectedIndex(selectedIndex);
						availableLabel.setText("Available:   YES");
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				}
				else
				{
					JOptionPane j = new JOptionPane();
					j.setMessage("Select a technician First.");
				}
				
			}
		});
    	
    	
    	//no button
    	noButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!techList.isSelectionEmpty())
				{

					int selectedIndex = techList.getSelectedIndex();
					
					try {
				
						if(day1.getSelectedIndex()!=0 && day2.getSelectedIndex()!=0 &&
								month1.getSelectedIndex()!=0 && month2.getSelectedIndex()!=0 &&
								year1.getSelectedIndex()!=0 && year2.getSelectedIndex()!=0)
						{
							String fromDayy = day1.getSelectedItem().toString();
							String fromMonthh = month1.getSelectedItem().toString();
							String fromYearr = year1.getSelectedItem().toString();
						
							String toDayy = day2.getSelectedItem().toString();
							String toMonthh = month2.getSelectedItem().toString();
							String toYearr = year2.getSelectedItem().toString();
				    	
							String fromDateString = fromYearr+"-"+fromMonthh + "-"+fromDayy;
							String toDateString   = toYearr+"-"+toMonthh + "-"+toDayy;
					    	
							
							
							
							dBase.updateTechnicianStatus("unavailable", techList.getSelectedValue().toString());
							dBase.updateTechLeave(fromDateString, toDateString, techList.getSelectedValue().toString());
							
							int tid = Integer.parseInt(techList.getSelectedValue().toString());
							String[] dates = dBase.getJobsDueDate(tid);
							
							for(int i = 0; i < dates.length; i++){
									try {
										
										long rezult = findDiff( dates[i] , fromDateString);
										if(rezult <= 0)
										{
											rezult = findDiff(dates[i], toDateString);
											if(rezult > 0 ){
												dBase.updateAssignmentAsNo(tid, dates[i]);
											}
										}
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							}
							
							
							
							populateTechList();
							techList.setSelectedIndex(selectedIndex);
							availableLabel.setText("Available:   NO");
							
							day1.setSelectedIndex(0);
							day2.setSelectedIndex(0); 
							month1.setSelectedIndex(0);
							month2.setSelectedIndex(0) ;
							
							year1.setSelectedIndex(0);
							year2.setSelectedIndex(0);
							
						}
						else
						{
							JOptionPane j = new JOptionPane();
							j.showMessageDialog(null,"Not a valid date");
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				}
				else
				{
					JOptionPane j = new JOptionPane();
					j.setMessage("Select a technician First.");
				}
				
				
			}
		});

	}
    
    
    
    
    /**
     * adding listeners to Tech lists
     */
    private void addListenerToTechList() {
    	
    	techList.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
			public void mousePressed(MouseEvent arg0) {
		
				String techData[] = null;
				
				int selected = Integer.parseInt(techList.getSelectedValue().toString());
				try {
					
					techData= dBase.getAllTechData(selected);
					
				} catch (SQLException e) {
					
					System.out.println(e.getMessage());
				}
				
				nameLabel.setText(techData[1]);
				specialityLabel.setText(techData[3]);
				noOfJobsLabel.setText(techData[4]);
				
				if(techData[5].equals("available") )
				{
					
					availableLabel.setText("Available:   YES");
					
					System.out.println("avail");
				}
				else
				{
					availableLabel.setText("Available:   NO");
					System.out.println("not avail");
					
				}
				
				

		    	
		 
				
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
    	
    	
	}
    
    
    
    /**
     * populating tech list
     */
    private void populateTechList() {
    	
    	
        try {
				
        	techList.setModel(new javax.swing.AbstractListModel() {
			    String[] strings = dBase.get_All_TechID();
			    
			    public int getSize() { return strings.length; }
			    public Object getElementAt(int i) { return strings[i]; }
			
        	});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
	}
    
    
    
    /**
     * adding listeners to date comboboxes
     */
    private void addListenersToDateComboxes() {
    	
    	
    	//---------------------------------------
    	
    	month1.addItem(0);
		
		for(int i = 0 ; i <12; i++)
		{
			month1.addItem(i+1);
		}
		
		year1.addItem(0);
		
		for(int i = 2013 ; i<2050; i++)
		{
			year1.addItem(i);
		}
		
		day1.addItem(0);
		
		for(int i = 0 ; i <31; i++)
		{
			day1.addItem(i+1);
		}
		
		year1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				int day1_selected  = ((Integer)  day1.getSelectedItem()).intValue();
				int month1_selected = ((Integer) month1.getSelectedItem()).intValue();
				int year1_selected  = ((Integer)  year1.getSelectedItem()).intValue();
				
				int day1_selected_index  =  day1.getSelectedIndex();
				int month1_selected_index = month1.getSelectedIndex();
				int year1_selected_index  =  year1.getSelectedIndex();
				
				
				if(year1_selected%4==0)
				{
					if(((Integer) month1.getSelectedItem()).intValue() == 2)
					{
						
						if(((Integer) day1.getSelectedItem()).intValue()<=29 )
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day1.addItem(j);
							}
							day1.setSelectedIndex(day1_selected_index);
							
						}
						else 
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day1.addItem(j);
							}
							
						}
						
						
					}
				}
				else
				{
					if(((Integer) month1.getSelectedItem()).intValue() == 2)
					{
						if(((Integer) day1.getSelectedItem()).intValue()<=28 )
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day1.addItem(j);
							}
							day1.setSelectedIndex(day1_selected_index);
							
						}
						else 
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day1.addItem(j);
							}
							
						}
					}
					
				}
				
			}
		});
		
		
		month1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent eve) {
				
				
				
				Integer[] arrayFor30Day1s = {4,6,9,11};
				
				int day1_selected  = ((Integer) day1.getSelectedItem()).intValue();
				int month1_selected = ((Integer) month1.getSelectedItem()).intValue();
				int year1_selected  = ((Integer) year1.getSelectedItem()).intValue();
				
				int day1_selected_index  =  day1.getSelectedIndex();
				int month1_selected_index = month1.getSelectedIndex();
				int year1_selected_index  =  year1.getSelectedIndex();
				
				boolean upday1 = false;
				
				for(int i = 0 ;i <arrayFor30Day1s.length; i++)
				{
					if(month1_selected==arrayFor30Day1s[i].intValue())
					{
						if(day1_selected<=30 )
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day1.addItem(j);
							}
							day1.setSelectedIndex(day1_selected_index);
							upday1=true;
							break;
						}
						else
						{
							day1.setModel(new DefaultComboBoxModel<Integer>());
							day1.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day1.addItem(j);
							}
							upday1=true;
							break;
							
						}
					}
					
				}
				if(month1_selected==2 && upday1==false)
				{
					if( day1_selected<=29 )
					{
						day1.setModel(new DefaultComboBoxModel<Integer>());
						day1.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day1.addItem(j);
						}
						day1.setSelectedIndex(day1_selected_index);
						upday1=true;
					}
					else
					{
						day1.setModel(new DefaultComboBoxModel<Integer>());
						day1.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day1.addItem(j);
						}
						upday1=true;
					}
				}
				else if(upday1==false)
				{
					day1.setModel(new DefaultComboBoxModel<Integer>());
					day1.addItem(0);
					for(int j = 1;j<=31 ;j++)
					{
						day1.addItem(j);
					}
					day1.setSelectedIndex(day1_selected_index);
				}
				
				
				
			}
			});
		
		
		
		
		
		
		
		
		
		
		
	month2.addItem(0);
		
		for(int i = 0 ; i <12; i++)
		{
			month2.addItem(i+1);
		}
		
		year2.addItem(0);
		
		for(int i = 2013 ; i<2050; i++)
		{
			year2.addItem(i);
		}
		
		day2.addItem(0);
		
		for(int i = 0 ; i <31; i++)
		{
			day2.addItem(i+1);
		}
		
		year2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
//				Integer value =(Integer) month2.getSelectedItem();
				
				int day2_selected  = ((Integer)  day2.getSelectedItem()).intValue();
				int month2_selected = ((Integer) month2.getSelectedItem()).intValue();
				int year2_selected  = ((Integer)  year2.getSelectedItem()).intValue();
				
				int day2_selected_index  =  day2.getSelectedIndex();
				int month2_selected_index = month2.getSelectedIndex();
				int year2_selected_index  =  year2.getSelectedIndex();
				
				
				if(year2_selected%4==0)
				{
					if(((Integer) month2.getSelectedItem()).intValue() == 2)
					{
						
						if(((Integer) day2.getSelectedItem()).intValue()<=29 )
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day2.addItem(j);
							}
							day2.setSelectedIndex(day2_selected_index);
							
						}
						else 
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=29 ;j++)
							{
								day2.addItem(j);
							}
							
						}
						
						
					}
				}
				else
				{
					if(((Integer) month2.getSelectedItem()).intValue() == 2)
					{
						if(((Integer) day2.getSelectedItem()).intValue()<=28 )
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day2.addItem(j);
							}
							day2.setSelectedIndex(day2_selected_index);
							
						}
						else 
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=28 ;j++)
							{
								day2.addItem(j);
							}
							
						}
					}
					
				}
				
			}
		});
		
		
		month2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent eve) {
				
				
				
				Integer[] arrayFor30Day2s = {4,6,9,11};
				
				int day2_selected  = ((Integer) day2.getSelectedItem()).intValue();
				int month2_selected = ((Integer) month2.getSelectedItem()).intValue();
				int year2_selected  = ((Integer) year2.getSelectedItem()).intValue();
				
				int day2_selected_index  =  day2.getSelectedIndex();
				int month2_selected_index = month2.getSelectedIndex();
				int year2_selected_index  =  year2.getSelectedIndex();
				
				boolean upday2 = false;
				
				for(int i = 0 ;i <arrayFor30Day2s.length; i++)
				{
					if(month2_selected==arrayFor30Day2s[i].intValue())
					{
						if(day2_selected<=30 )
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day2.addItem(j);
							}
							day2.setSelectedIndex(day2_selected_index);
							upday2=true;
							break;
						}
						else
						{
							day2.setModel(new DefaultComboBoxModel<Integer>());
							day2.addItem(0);
							for(int j = 1;j<=30 ;j++)
							{
								day2.addItem(j);
							}
							upday2=true;
							break;
							
						}
					}
					
				}
				if(month2_selected==2 && upday2==false)
				{
					if( day2_selected<=29 )
					{
						day2.setModel(new DefaultComboBoxModel<Integer>());
						day2.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day2.addItem(j);
						}
						day2.setSelectedIndex(day2_selected_index);
						upday2=true;
					}
					else
					{
						day2.setModel(new DefaultComboBoxModel<Integer>());
						day2.addItem(0);
						for(int j = 1;j<=29 ;j++)
						{
							day2.addItem(j);
						}
						upday2=true;
					}
				}
				else if(upday2==false)
				{
					day2.setModel(new DefaultComboBoxModel<Integer>());
					day2.addItem(0);
					for(int j = 1;j<=31 ;j++)
					{
						day2.addItem(j);
					}
					day2.setSelectedIndex(day2_selected_index);
				}
				
				
				
			}
			});
		

		
		
		
		
		

		
		
		
    	
    	
    	
    	//========================================
    	
    	
    	
    	
    	

	}
    
    
    
    
    

    /**
     * some code is auto generated by Net Beans 8.0
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {

    	dBase = new Database();
    	msgString = "";
    	logOut= false;
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        techList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        specialityLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        noOfJobsLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bookedTillLabel = new javax.swing.JLabel();
        availableLabel = new javax.swing.JLabel();
        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewMsgArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        wrtMsgArea = new javax.swing.JTextArea();
        postBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        day1 = new javax.swing.JComboBox();
        month1 = new javax.swing.JComboBox();
        year1 = new javax.swing.JComboBox();
        day2 = new javax.swing.JComboBox();
        month2 = new javax.swing.JComboBox();
        year2 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Select Technician");

        techList.setBackground(new java.awt.Color(203, 226, 238));
        techList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(techList);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(9, 31, 123));
        jLabel3.setText("Technician Details");

        jLabel6.setBackground(new java.awt.Color(203, 226, 238));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 31, 123));
        jLabel6.setText("Name");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameLabel.setText("");

        jLabel8.setBackground(new java.awt.Color(203, 226, 238));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 31, 123));
        jLabel8.setText("Specialilty:");

        specialityLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        specialityLabel.setText("Laptops");

        jLabel10.setBackground(new java.awt.Color(203, 226, 238));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(9, 31, 123));
        jLabel10.setText("No. of jobs assigned:");

        noOfJobsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        noOfJobsLabel.setText("3");

        jLabel12.setBackground(new java.awt.Color(203, 226, 238));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(9, 31, 123));
        jLabel12.setText("Booked till:");

        bookedTillLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bookedTillLabel.setText("24-2-2014");

        availableLabel.setFont(new java.awt.Font("Calibri", 1, 19)); // NOI18N
        availableLabel.setForeground(new java.awt.Color(9, 31, 123));
        availableLabel.setText("Available:");

        yesButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        yesButton.setForeground(new java.awt.Color(0, 102, 0));
        yesButton.setText("set Yes");

        noButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        noButton.setForeground(new java.awt.Color(255, 0, 0));
        noButton.setText("set No");

        viewMsgArea.setBackground(new java.awt.Color(204, 204, 204));
        viewMsgArea.setColumns(20);
        viewMsgArea.setRows(5);
        jScrollPane2.setViewportView(viewMsgArea);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 31, 123));
        jLabel2.setText("Reason for absence");

        wrtMsgArea.setBackground(new java.awt.Color(204, 204, 204));
        wrtMsgArea.setColumns(20);
        wrtMsgArea.setRows(5);
        wrtMsgArea.setText("hello");
        jScrollPane3.setViewportView(wrtMsgArea);

        postBtn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        postBtn.setForeground(new java.awt.Color(153, 0, 0));
        postBtn.setText("POST");
        postBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Day");

        jLabel14.setText("Month");

        jLabel15.setText("Year");

//        day1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

//        month1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

//        year1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

//        day2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

//        month2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

//        year2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 19)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(9, 31, 123));
        jLabel17.setText("Unavailability:");

        jLabel16.setText("From:");

        jLabel18.setText("To:");

        logoutButton.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(153, 0, 153));
        logoutButton.setText("Log out");
        logoutButton.setToolTipText("");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(availableLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(specialityLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameLabel))
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                                .addComponent(noOfJobsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bookedTillLabel)))))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(postBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(month1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel15))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(month2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(year2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel17)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(postBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(availableLabel)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(specialityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(noOfJobsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(bookedTillLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(month1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(month2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                      

    
    
    
    
    /**
     * finding the difference between two dates
     * @return
     * @throws ParseException
     */
    public long findDiff(String d1, String d2) throws ParseException {

 	   String str_date1 = d1;
 	   String str_time1 = "12:00 AM";

 	   String str_date2 = d2;
 	   String str_time2 = "12:00 AM" ;

 	   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
 	   Date date1 = formatter.parse(str_date1 + " " + str_time1);
 	   Date date2 = formatter.parse(str_date2 + " " + str_time2);

 	   long diff = date2.getTime() - date1.getTime();

 	   System.out.println("Difference In Days: " + (diff / (1000 * 60 * 60 * 24)));		
 	   long rzl = (diff / (1000 * 60 * 60 * 24));
 	   return rzl;
	}
    
    
    /**
     * adding action listener to post button
     * @param evt
     */
    private void postBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         

    	if(availableLabel.getText().equals("Available:   NO"))
    	{
    		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    		Date date = new Date();
    		
    		String postMsg = "\n" + dateFormat.format(date)+ "\nManager:  " + wrtMsgArea.getText();
    		
    		try {
    			
    			String[] arr =  dBase.techUnAssignedJobs(Integer.parseInt(techList.getSelectedValue().toString()));
    			
    			for(int i =0 ; i< arr.length; i++)
    			{
    				dBase.addRepairJobMsg(postMsg, Integer.parseInt(arr[i]));
    			}
    			if(arr.length != 0){
    				
    				StringBuilder buff = new StringBuilder();
    				buff.append(msgString + "\n" + postMsg);
    				String str = buff.toString();
    				viewMsgArea.setText(str);
    			}
    			
    		} catch (NumberFormatException | SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "Technician is Available\nYou can't Post about his Jobs");
    		
    	}
    }                                        

    
    
    
    
    /**
     * adding listener to logout button
     * @param evt
     */
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    
    	System.out.println("manager logged out");
    	logOut= true;

    }                                        
    


    // Variables declaration - do not modify     
    
    
    private Database dBase;
    
    private String msgString;
    
    private javax.swing.JButton yesButton;
    private javax.swing.JButton noButton;
    private javax.swing.JButton postBtn;
    private javax.swing.JButton logoutButton;
    private javax.swing.JComboBox day1;
    private javax.swing.JComboBox month1;
    private javax.swing.JComboBox year1;
    private javax.swing.JComboBox day2;
    private javax.swing.JComboBox month2;
    private javax.swing.JComboBox year2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel noOfJobsLabel;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel bookedTillLabel;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel availableLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel specialityLabel;
    private javax.swing.JList techList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea viewMsgArea;
    private javax.swing.JTextArea wrtMsgArea;

    public boolean logOut;
    // End of variables declaration                   
}
;