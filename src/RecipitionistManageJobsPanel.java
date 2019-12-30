import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 * This class is responisible for showing the receptionist manage jobs
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class RecipitionistManageJobsPanel extends javax.swing.JPanel {

    /**
     * Creates new form RecipitionistManageJobsPanel
     * @throws SQLException 
     */
    public RecipitionistManageJobsPanel() throws SQLException {
        initComponents();
    	this.flag= false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * @throws SQLException 
     */
    private void initComponents() throws SQLException {

    	
    	dataB = new Database();
    	
        jLabel1 = new javax.swing.JLabel();
        filterJobsField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unAssignedJobsList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        completedJobsList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        allJobsList = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        postsTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        deadlineLabel = new javax.swing.JLabel();
        completedLabel = new javax.swing.JLabel();
        techNameLabel = new javax.swing.JLabel();
        deadLineData = new javax.swing.JLabel();
        equiTypeLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        faultList = new javax.swing.JList();
        assignJobBtn = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Filter list by Order ID");

        filterJobsField.setBackground(new java.awt.Color(203, 226, 238));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Unassigned Jobs");

        
        
        
        
        
        
        
        
        unAssignedJobsList.setBackground(new java.awt.Color(203, 226, 238));
        unAssignedJobsList.setModel(new javax.swing.AbstractListModel() {
        	
            String[] strings = dataB.get_UnAssign_Jobs();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(unAssignedJobsList);
        
        unAssignedJobsList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				String [] details = null;
				if(!unAssignedJobsList.isSelectionEmpty())
				{
					try {
						details = dataB.getRID_Details(Integer.parseInt(unAssignedJobsList.getSelectedValue().toString()));
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
//					e.printStackTrace();
					}

//					unAssignedJobsList.clearSelection();
					completedJobsList.clearSelection();
					allJobsList.clearSelection();
					techNameLabel.setText(details[0]);
					deadLineData.setText(details[1]);
					equiTypeLabel.setText(details[2]);

					descriptionTextArea.setText(details[3]);

					try {
						faultList.setListData(dataB.getFaultsbyRID(Integer.parseInt(unAssignedJobsList.getSelectedValue().toString())));
						
						String allMsgs = array_to_String(dataB.allMsgsByRID(Integer.parseInt(unAssignedJobsList.getSelectedValue().toString())));
						postsTextArea.setText(allMsgs);
						
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		    	
//		    	techNameLabel.setText("nill Allselecewgtad");
//		    	deadlineLabel.setText("All sel.. dead");
//		    	completedLabel.setText("all .. no");

		    	
//		    	String[] a = {"a","b"};
		    	
//		    	faultList.setModel(new DefaultListModel<>());
//		    	faultList.setModel(new)
//		    	postsTextArea.setText("asdfasfdfksfhsakjfhsdjkfsdkafadh \n asdsjlask \n sadfdhdlskd f");
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
				
//				unAssignedJobsList.clearSelection();
//		    	completedJobsList.clearSelection();
//		    	allJobsList.clearSelection();
		    	
//		    	techNameLabel.setText("Tech Name");
		    	
				
			}
		});
        
        
        
        
        
        
        
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Completed Jobs");

        
        
        
        
        
        
        completedJobsList.setBackground(new java.awt.Color(203, 226, 238));
        completedJobsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dataB.get_completed_Jobs();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(completedJobsList);

        completedJobsList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
//		    	-------------------------------------------------------------
				String [] details = null;
				if(!completedJobsList.isSelectionEmpty())
				{
					
					try {
						details = dataB.getRID_Details(Integer.parseInt(completedJobsList.getSelectedValue().toString()));
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
//					e.printStackTrace();
					}
					unAssignedJobsList.clearSelection();
//		    	completedJobsList.clearSelection();
					allJobsList.clearSelection();
					
					techNameLabel.setText(details[0]);
					deadLineData.setText(details[1]);
					equiTypeLabel.setText(details[2]);
//		    	techNameLabel.setText("nill Allselecewgtad");
//		    	deadlineLabel.setText("All sel.. dead");
//		    	completedLabel.setText("all .. no");
					
					descriptionTextArea.setText(details[3]);
					
//		    	String[] a = {"a","b"};
					
//		    	faultList.setModel(new DefaultListModel<>());
//		    	faultList.setModel(new)
					try {
						faultList.setListData(dataB.getFaultsbyRID(Integer.parseInt(completedJobsList.getSelectedValue().toString())));
						
						String allMsgs = array_to_String(dataB.allMsgsByRID(Integer.parseInt(completedJobsList.getSelectedValue().toString())));
						postsTextArea.setText(allMsgs);
						
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
//				postsTextArea.setText("asdfasfdfksfhsakjfhsdjkfsdkafadh \n asdsjlask \n sadfdhdlskd f");
//		    	----------------------------------------------------------------------------------------------------
				
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
				
//				unAssignedJobsList.clearSelection();
//		    	completedJobsList.clearSelection();
//		    	allJobsList.clearSelection();

			}
		});
        
        
        
        
        
        
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(9, 31, 123));
        jLabel4.setText("Select Job");

        
        
        
        
        
        
        allJobsList.setBackground(new java.awt.Color(203, 226, 238));
        
        allJobsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dataB.get_All_Jobs();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(allJobsList);

        allJobsList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String [] details = null;
				if(!allJobsList.isSelectionEmpty())
				{
					try {
						details = dataB.getRID_Details(Integer.parseInt(allJobsList.getSelectedValue().toString()));
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
//					e.printStackTrace();
					}
					unAssignedJobsList.clearSelection();
					completedJobsList.clearSelection();
//		    	allJobsList.clearSelection();
					
					techNameLabel.setText(details[0]);
					deadLineData.setText(details[1]);
					equiTypeLabel.setText(details[2]);
//		    	techNameLabel.setText("nill Allselecewgtad");
//		    	deadlineLabel.setText("All sel.. dead");
//		    	completedLabel.setText("all .. no");
					
					descriptionTextArea.setText(details[3]);
					
//		    	String[] a = {"a","b"};
					
//		    	faultList.setModel(new DefaultListModel<>());
//		    	faultList.setModel(new)
					try {
						faultList.setListData(dataB.getFaultsbyRID(Integer.parseInt(allJobsList.getSelectedValue().toString())));
						
						String allMsgs = array_to_String(dataB.allMsgsByRID(Integer.parseInt(allJobsList.getSelectedValue().toString())));
						postsTextArea.setText(allMsgs);
						
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
//				postsTextArea.setText("asdfasfdfksfhsakjfhsdjkfsdkafadh \n asdsjlask \n sadfdhdlskd f");
				
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
        
        
        
        
        
        
        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 31, 123));
        jLabel5.setText("Repair Job Details:");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 31, 123));
        jLabel6.setText("Posts:");

        postsTextArea.setBackground(new java.awt.Color(203, 226, 238));
        postsTextArea.setColumns(20);
        postsTextArea.setRows(5);
        jScrollPane4.setViewportView(postsTextArea);

        jLabel7.setForeground(new java.awt.Color(9, 31, 123));
        jLabel7.setText("Tech Name: ");

        deadlineLabel.setForeground(new java.awt.Color(9, 31, 123));
        deadlineLabel.setText("Deadline: ");

        completedLabel.setForeground(new java.awt.Color(9, 31, 123));
        completedLabel.setText("Completed: ");

        techNameLabel.setForeground(new java.awt.Color(244, 0, 0));
        techNameLabel.setText("unavailable");

        deadLineData.setText("13-2-2014");

        equiTypeLabel.setText("-nil");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(9, 31, 123));
        jLabel13.setText("Description:");

        descriptionTextArea.setBackground(new java.awt.Color(203, 226, 238));
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane5.setViewportView(descriptionTextArea);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(9, 31, 123));
        jLabel14.setText("Faults:");

        faultList.setBackground(new java.awt.Color(203, 226, 238));
        faultList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(faultList);

//      -----------------------------------------------------------------------------------
        
        assignJobBtn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        assignJobBtn.setForeground(new java.awt.Color(9, 31, 123));
        
        assignJobBtn.setText("Assign Job");
        
        assignJobBtn.addActionListener(new java.awt.event.ActionListener() {
            
        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		try {
					assignJobBtnActionPerformed(e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
            }
        });

//      ------------------------------------------------------------------------------------
        
        filterButton.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        filterButton.setForeground(new java.awt.Color(9, 31, 123));
        filterButton.setText("Filter");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					filterButtonActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(techNameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deadlineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deadLineData))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(completedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(equiTypeLabel))
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterJobsField, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterButton)))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(assignJobBtn))
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(assignJobBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filterJobsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(techNameLabel)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(deadLineData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(equiTypeLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(deadlineLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(completedLabel))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    
    /**
     * for reseting the Gui
     * @throws SQLException
     */
    public void resetGui() throws SQLException {

    	//..............................    	
    	System.out.println("reset main manage jobs gui");
    	System.out.println("Reset Gui");
    	  allJobsList.setModel(new javax.swing.AbstractListModel() {
              String[] strings = dataB.get_All_Jobs();
              public int getSize() { return strings.length; }
              public Object getElementAt(int i) { return strings[i]; }
          });
    	completedJobsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dataB.get_completed_Jobs();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    	unAssignedJobsList.setModel(new javax.swing.AbstractListModel() {
        	
            String[] strings = dataB.get_UnAssign_Jobs();
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    	
    	System.out.println("11111111111111111");
    	allJobsList.setSelectedIndex(2);
    	System.out.println("222222222222222");
//    	postsTextArea = new javax.swing.JTextArea();
//    	postsTextArea.setText("");
//  
//    	techNameLabel = new JLabel();
//    	techNameLabel.setText("");
//    	deadLineData.setText("");
//    	completedLabel.setText("");
    	allJobsList.setSelectedIndex(0);
 //.....................
    	
	}
    
    
    /**
     * array to string
     * @param arr
     * @return
     */
    private String array_to_String(String[] arr) {
		
    	StringBuilder builder = new StringBuilder();
    	
    	for (String i : arr) {
    		if (builder.length() > 0) {
    			builder.append("\n");
    		}
    		builder.append(i);
    	}
    	
    	String str = builder.toString();
	
    	return str;
    }
    
    
    
    /**
     * addinng listener to assign job
     * @param e
     * @throws SQLException
     */
    private void assignJobBtnActionPerformed(java.awt.event.ActionEvent e) throws SQLException {                                         
        // TODO add your handling code here:
    	if( !unAssignedJobsList.isSelectionEmpty())
    	{
    		
    		
    		jobUpdate = new ReciptionistJobInfoUpdate
    				(this, Integer.parseInt(unAssignedJobsList.getSelectedValue().toString()),this.flag);
    		jobUpdate.setVisible(true);
    		jobUpdate.pack();
    		jobUpdate.setLocationRelativeTo(null);
    		
//    		while( flag==false)
//    		{
//    			System.out.println("asfadsdsfasdf");
//    			if(flag= true)
//    			{
//    				System.out.println("flag true");
//    				resetGui();
//    				flag= false;
//    				break;
//    			}
//    		}
    	}
    	else if( !allJobsList.isSelectionEmpty())
    	{
    		System.out.println();
    		System.out.println("All jobs ");
    		System.out.println();    		
    		jobUpdate = new ReciptionistJobInfoUpdate
    				(this,Integer.parseInt(allJobsList.getSelectedValue().toString()),this.flag);
    		jobUpdate.setVisible(true);
    		jobUpdate.pack();
    		jobUpdate.setLocationRelativeTo(null);
    		
    		
//    		while( flag==false)
//    		{
//    			System.out.println("asfadsdsfasdf");
//    			if(flag= true)
//    			{
//    				System.out.println("flag loop main "+flag);
//    				resetGui();
//    				flag= false;
//    				break;
//    			}
//    		}
    	}
    	else
    	{
    		JOptionPane pane = new JOptionPane();
    		pane.showMessageDialog(null, "First Select Job from UnAssigned or All Jobs List!!!");
    		pane.show();
    	}
    
    }                                        

    
    
    /**
     * adding listener to filter button
     * @param evt
     * @throws SQLException
     */
    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {        
    	
    	String filterFieldTxt = filterJobsField.getText();
    	
    	
//    	unAssignedJobsList.clearSelection();
//    	completedJobsList.clearSelection();
//    	allJobsList.clearSelection();
    	
    	
    	if(filterFieldTxt.isEmpty())
    	{
      	  allJobsList.setModel(new javax.swing.AbstractListModel() {
              String[] strings = dataB.get_All_Jobs();
              public int getSize() { return strings.length; }
              public Object getElementAt(int i) { return strings[i]; }
          });
    	}
    	else
    	{
    
    	  allJobsList.setModel(new javax.swing.AbstractListModel() {
              String[] strings = dataB.getJobsByOrderId(filterJobsField.getText());
              public int getSize() { return strings.length; }
              public Object getElementAt(int i) { return strings[i]; }
          });
    	}
    	
    	
    }                                        


    // Variables declaration 
    
    private javax.swing.JButton assignJobBtn;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel techNameLabel;
    private javax.swing.JLabel deadLineData;
    private javax.swing.JLabel equiTypeLabel;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel deadlineLabel;
    private javax.swing.JLabel completedLabel;
    private javax.swing.JList unAssignedJobsList;
    private javax.swing.JList completedJobsList;
    public javax.swing.JList allJobsList;
    private javax.swing.JList faultList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea postsTextArea;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JTextField filterJobsField;
    
    private Database dataB;
    private ReciptionistJobInfoUpdate jobUpdate; 
    public boolean flag; 
    
  
    // End of variables declaration            
    
    
    
    
}
