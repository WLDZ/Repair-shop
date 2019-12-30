import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;


/**
 * This class is responisible for showing the interface for addition of new model 
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 *
 */
public class TechnicianMainFrame extends javax.swing.JFrame {


	
	
	/**
     * Creates new form TechnicianMainFrame
     */
	
	private void addListenerToJobsList() {
		
		selectJobList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {

				try {
					faultsList.setListData(dBase.getFaultsbyRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
					
					String arr[] = dBase.getDetailsForTechFrame(Integer.parseInt(selectJobList.getSelectedValue().toString()));
				
					orderDataLabel.setText(arr[0]);
					dateLabelData.setText(arr[1]);
					
					String allMsgs = array_to_String(dBase.allMsgsByRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
			        msgsArea.setText(allMsgs);
				
				} catch (NumberFormatException | SQLException e) {
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
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	/**
	 * 
	 */
    public TechnicianMainFrame(int Tid) {
    	this.ID = Tid;
    	
        initComponents(Tid);
        addListenerToJobsList();
        this.setTitle("Technician");
        this.setLocation(280, 10);
    }

    /**
     * 
     * @param iD
     */
    public void setID(int iD) {
		ID = iD;
	}
    
    /**
     * 
     * @return
     */
    public int getID() {
		return ID;
	}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     *
     */
 
    private void initComponents(int TID)
    {
    	this.ID= TID;
    	
    	dBase = new  Database();
    	final TechnicianMainFrame tFrame = this;
    	
    	logOut = false;
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectJobList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        orderDataLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dateLabelData = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgsArea = new javax.swing.JTextArea();
//        msgsArea.setText("IST Filef");
        jScrollPane3 = new javax.swing.JScrollPane();
        postArea = new javax.swing.JTextArea();
        postBtn = new javax.swing.JButton();
        updateDetailsBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        faultsList = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        logoutButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 0, 0));

        selectJobList.setBackground(new java.awt.Color(203, 226, 238));
        try {
			selectJobList.setModel(new javax.swing.AbstractListModel() {
				
			    String[] strings = dBase.getJobsByTID(ID);
			    public int getSize() { return strings.length; }
			    public Object getElementAt(int i) { return strings[i]; 
			    
			    }
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        jScrollPane1.setViewportView(selectJobList);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Select Job");

        jLabel6.setBackground(new java.awt.Color(203, 226, 238));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(9, 31, 123));
        jLabel6.setText("Order ID:");

        orderDataLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        orderDataLabel.setText("               ");

        jLabel8.setBackground(new java.awt.Color(203, 226, 238));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(9, 31, 123));
        jLabel8.setText("Deadline:");

        dateLabelData.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dateLabelData.setText("                ");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(9, 31, 123));
        jLabel3.setText("Job Details");

        msgsArea.setBackground(new java.awt.Color(204, 204, 204));
        msgsArea.setColumns(20);
        msgsArea.setRows(5);
        jScrollPane2.setViewportView(msgsArea);

        postArea.setBackground(new java.awt.Color(204, 204, 204));
        postArea.setColumns(20);
        postArea.setRows(5);
        postArea.setText(" ");
        jScrollPane3.setViewportView(postArea);

        postBtn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        postBtn.setForeground(new java.awt.Color(153, 0, 0));
        postBtn.setText("POST");
        postBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postBtnActionPerformed(evt);
            }
        });

        updateDetailsBtn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        updateDetailsBtn.setText("Update Details");

        updateDetailsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(! selectJobList.isSelectionEmpty())
		    	{
					
					techUpdateFrame = new TechnicianJobInfoUpdateFrame
							(Integer.parseInt(selectJobList.getSelectedValue().toString()),tFrame);
		    		techUpdateFrame.setVisible(true);
//		    		techUpdateFrame.pack();
		    		techUpdateFrame.setLocationRelativeTo(null);
		    	}
		    	else
		    	{
		    		JOptionPane pane = new JOptionPane();
		    		pane.showMessageDialog(null, "First Select the Repair Job from List!!!");
		    		pane.show();
		    	}
			}
		});
        
        jLabel10.setBackground(new java.awt.Color(203, 226, 238));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(9, 31, 123));
        jLabel10.setText("Faults");

        faultsList.setBackground(new java.awt.Color(203, 226, 238));
        faultsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        
        
        jScrollPane4.setViewportView(faultsList);

        logoutButton.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(153, 0, 153));
        logoutButton.setText("Log out");
        logoutButton.setToolTipText("");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 31, 123));
        jLabel5.setText("Notifications:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                            .addComponent(dateLabelData))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(orderDataLabel))
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel3)
                        .addComponent(updateDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane4))
                    .addComponent(jLabel10))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(374, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addGap(293, 293, 293)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(orderDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(dateLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(postBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addComponent(updateDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jLabel5)
                    .addContainerGap(630, Short.MAX_VALUE)))
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
     * 
     * @param yesSignal
     */
    public void reSetGUI(boolean yesSignal) {
    	try {
    		
    		// Resetting the updated jobs for Technician
    		if(yesSignal){
    			
    			selectJobList.setModel(new javax.swing.AbstractListModel() {
    				
    				String[] strings = dBase.getJobsByTID(ID);
    				public int getSize() { return strings.length; }
    				public Object getElementAt(int i) { return strings[i]; 
    				
    				}
    			});
    		}

    		if(selectJobList.isSelectionEmpty())
    		{
    			faultsList.setListData(new String[1]);
    			orderDataLabel.setText("");
    			dateLabelData.setText("");
    			msgsArea.setText("");
    		}
    		else
    		{
    			faultsList.setListData(dBase.getFaultsbyRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
				
				String arr[] = dBase.getDetailsForTechFrame(Integer.parseInt(selectJobList.getSelectedValue().toString()));
			
				orderDataLabel.setText(arr[0]);
				dateLabelData.setText(arr[1]);
				
				String allMsgs = array_to_String(dBase.allMsgsByRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
		        msgsArea.setText(allMsgs);
    		}
		
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//    private void reSetforUpdatedFaults() {
//    	try {
//    		
//    		// Resetting the updated jobs for Technician
////    		selectJobList.setModel(new javax.swing.AbstractListModel() {
////    			
////    			String[] strings = dBase.getJobsByTID(ID);
////    			public int getSize() { return strings.length; }
////    			public Object getElementAt(int i) { return strings[i]; 
////    			
////    			}
////    		});
//
////    		 faultsList.setBackground(new java.awt.Color(203, 226, 238));
////    	        faultsList.setModel(new javax.swing.AbstractListModel() {
////    	            String[] strings = {  };
////    	            public int getSize() { return strings.length; }
////    	            public Object getElementAt(int i) { return strings[i]; }
////    	        });
//    		
//    	        faultsList.setListData(dBase.getFaultsbyRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
//				
//				String arr[] = dBase.getDetailsForTechFrame(Integer.parseInt(selectJobList.getSelectedValue().toString()));
//			
//				orderDataLabel.setText(arr[0]);
//				dateLabelData.setText(arr[1]);
//				
//				String allMsgs = array_to_String(dBase.allMsgsByRID(Integer.parseInt(selectJobList.getSelectedValue().toString())));
//		        msgsArea.setText(allMsgs);
//    	        
//    	} catch (NumberFormatException | SQLException e) {
//    		// TODO Auto-generated catch block
//    		e.printStackTrace();
//    	}
//		
//	}
    
    /**
     * 
     * @param arr
     * @return
     */
    private String array_to_String(String[] arr) {
//    	String[] strings = {"Java", "is", "cool"};
		
    	StringBuilder buff = new StringBuilder();
    	
    	for (String i : arr) {
    		if (buff.length() > 0) {
    			buff.append("\n");
    		}
    		buff.append(i);
    	}
    	
    	String str = buff.toString();
//    	System.out.println(string); // Java is cool
	
    	return str;
    }
    
    
    /**
     * 
     * @param evt
     */
    private void postBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	try {
    		if(! selectJobList.isSelectionEmpty())
    		{
	    		if((!postArea.toString().isEmpty() || !postArea.getText().contentEquals("")
	    				|| !postArea.getText().contentEquals(" ")))
	    		{
	    		    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		    Date date = new Date();
//	    		    System.out.println(dateFormat.format(date));

	    			String postMsg = "\n" + dateFormat.format(date)+ "\nTechnician: " + postArea.getText();
	    				int rID = Integer.parseInt(selectJobList.getSelectedValue().toString());
	    				
	    				dBase.addRepairJobMsg(postMsg, rID);
	    				
	    				String allMsgs = array_to_String(dBase.allMsgsByRID(rID));
	    				msgsArea.setText(allMsgs);
	    				postArea.setText("");
	    		}
	    		else
	    		{
	    			JOptionPane pane = new JOptionPane();
	    			pane.showMessageDialog(null, "Message Required!!!");
	    			pane.show();
	    		}
    		}
    		else
    		{
    			JOptionPane pane = new JOptionPane();
    			pane.showMessageDialog(null, "Repair Job Not Selected!!!");
    			pane.show();
    		}
    	} catch (NumberFormatException | SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		JOptionPane pane = new JOptionPane();
    		pane.showMessageDialog(null, e.getMessage());
    		pane.show();
    		
    	}
    }                                        

    /**
     * 
     * @param evt
     */
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	logOut= true;
    	System.out.println("technicnian  logged out");
    }                                        

    // Variables declaration - do not modify  

    private Database dBase;
    private int ID;
    
    private TechnicianJobInfoUpdateFrame techUpdateFrame;
    
    public boolean logOut;
    
    private javax.swing.JButton postBtn;
    private javax.swing.JButton updateDetailsBtn;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel orderDataLabel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel dateLabelData;
    private javax.swing.JList selectJobList;
    private javax.swing.JList faultsList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea msgsArea;
    private javax.swing.JTextArea postArea;
    // End of variables declaration                   
}
