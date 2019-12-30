import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * This class is responisible for showing the interface for addition of new model 
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 *
 */
public class TechnicianJobInfoUpdateFrame extends javax.swing.JFrame {

    /**
     * Creates new form TechnicianJobInfoUpdateFrame
     */
    public TechnicianJobInfoUpdateFrame(int repairJobID, TechnicianMainFrame objct) {
        try {
        	
        	rID = repairJobID;
        	tFrame = objct;
			initComponents();
			
			this.setTitle("Job Info Update");
	        this.setLocation(250, 60);
			

			
			//			System.out.println("RID :" + rID);
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * 
     */ 
    private void initComponents() throws SQLException {

    	dB = new Database();
    	
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        faultsInEqList = new javax.swing.JList();
        removeBtn = new javax.swing.JButton();
        addFromFieldsBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        faultNameField = new javax.swing.JTextField();
//        faultNameField.setText("hello");
        approxDateField = new javax.swing.JTextField();
//        approxDateField.setText("hello");
        
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        yesLabeledRadioBtn = new javax.swing.JRadioButton();
//        yesLabeledRadioBtn.setSelected(true);
        noLabeledRadioBtn = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        allFaultsList = new javax.swing.JList();
        jLabel10 = new javax.swing.JLabel();
        addFromListBtn = new javax.swing.JButton();

        bG = new ButtonGroup();
        bG.add(yesLabeledRadioBtn);
        bG.add(noLabeledRadioBtn);
        
        noLabeledRadioBtn.setSelected(true);
        yesLabeledRadioBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				JOptionPane pane = new JOptionPane();
//				pane.showConfirmDialog(null, "Are you Sure Job Completed?");
				
    			try {
    			
//    				String eqType = dB.getEquiTypebyRID(rID);
//					int fID = dB.getFIDbyDescription(faultsInEqList.getSelectedValue().toString(), eqType);
//					int eqOrder = dB.getEquiOrderbyRID(rID);
					
					int dialogresult =JOptionPane.showConfirmDialog(null,"Are you Sure Job Completed?");
					if (dialogresult == JOptionPane.YES_OPTION)
					{
						yesSignal = true;
						dB.UpdateCompletionAsYes(rID);
						tFrame.reSetGUI(yesSignal);
						yesSignal = false;
//						setDefaultCloseOperation()
						dispose();
					}
					else
						yesSignal = false;
					
    			} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Faults in Equipment");

        faultsInEqList.setBackground(new java.awt.Color(203, 226, 238));
        faultsInEqList.setModel(new javax.swing.AbstractListModel() {
        	
            String[] strings = dB.getFaultsbyRID(rID);
//            	{ "000000", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(faultsInEqList);

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                removeBtnActionPerformed(e);
            }
        });

        addFromFieldsBtn.setText("Add");
        addFromFieldsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFromFieldsBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Fault Name:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Approximate fix time in days:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(9, 31, 123));
        jLabel4.setText("Add New");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Job completion details");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Add/Remove faults");

        jLabel7.setForeground(new java.awt.Color(153, 0, 51));
        jLabel7.setText("Job Completed:");

        jLabel8.setForeground(new java.awt.Color(0, 0, 204));
        jLabel8.setText("Yes");

        jLabel9.setForeground(new java.awt.Color(0, 0, 204));
        jLabel9.setText("No");

        allFaultsList.setBackground(new java.awt.Color(203, 226, 238));
        allFaultsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = dB.getEqui_Faults(dB.getEquiTypebyRID(rID));
            
//            	{ "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(allFaultsList);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(9, 31, 123));
        jLabel10.setText("All Faults");

        addFromListBtn.setText("Add");
        addFromListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addFromListBtnActionPerformed(e);
                
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(yesLabeledRadioBtn))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(noLabeledRadioBtn)
                                    .addComponent(jLabel9))))
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(removeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addFromListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(faultNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(approxDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(addFromFieldsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faultNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(approxDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addFromListBtn)
                                    .addComponent(addFromFieldsBtn))))))
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yesLabeledRadioBtn)
                    .addComponent(noLabeledRadioBtn)
                    .addComponent(jLabel7))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    
//    public void 
    
    
    /**
     * 
     * @param evt
     */
    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	try {
    		if(! faultsInEqList.isSelectionEmpty()){
    			
    			String eqType = dB.getEquiTypebyRID(rID);
    			int fID = dB.getFIDbyDescription(faultsInEqList.getSelectedValue().toString(), eqType);
    			int eqOrder = dB.getEquiOrderbyRID(rID);
    			
    			System.out.println("FID : " + fID);
    			System.out.println("EQORDER: " + eqOrder);
    			
    			dB.deleteFault(eqOrder, fID);

    			faultsInEqList.setListData(dB.getFaultsbyRID(rID));
    
    			// Resetting the Parent GUI
    			tFrame.reSetGUI(yesSignal);
    			
    			JOptionPane pane = new JOptionPane();
    			pane.showMessageDialog(null, "Fault Deleted Successfully!!!");
    			pane.show();
//    			System.out.println("Fault Deleted Successfully!!!");
    		}
    		else
    		{
    			JOptionPane pane = new JOptionPane();
        		pane.showMessageDialog(null, "First Select the Fault from Equipment Fault List");
        		pane.show();
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }                                        
    /**
     * 
     * @param evt
     */
    private void addFromFieldsBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	try {
	    	if(! faultNameField.toString().isEmpty() &&  !approxDateField.toString().isEmpty())
	    	{
	   			String eqType = dB.getEquiTypebyRID(rID);
				String faultName = faultNameField.getText();
				int fixInDays = Integer.parseInt(approxDateField.getText().toString());

				int fID = dB.addGenerlFault(faultName, fixInDays, eqType);				
				int eqOrder = dB.getEquiOrderbyRID(rID);
				
				dB.addNewGivenEquipFault(eqOrder, fID);
				
				// ReInitialising the Whole GUI ---------------------------------------------
    			faultsInEqList.setListData(dB.getFaultsbyRID(rID));
    			allFaultsList.setListData(dB.getEqui_Faults(dB.getEquiTypebyRID(rID)));
    			faultNameField.setText("");
				approxDateField.setText("");
    			
				
				//Resetting the Parent GUI
				tFrame.reSetGUI(yesSignal);
				
    			//--------------------------------------------------------------------------
				System.out.println("FID : " + fID);
				System.out.println("EQORDER: " + eqOrder);
				
				
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null, "Fault Added Successfully!!!");
				pane.show();

    		}
	    	else
			{
				JOptionPane pane = new JOptionPane();
	    		pane.showMessageDialog(null, "Fields Left Empty!!!");
	    		pane.show();
			}
    	} 
    	catch (SQLException e) {
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
    private void addFromListBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	
    	try {
	    	if(! allFaultsList.isSelectionEmpty()){
				
				String eqType = dB.getEquiTypebyRID(rID);
				
				int	fID = dB.getFIDbyDescription(allFaultsList.getSelectedValue().toString(), eqType);
				int eqOrder = dB.getEquiOrderbyRID(rID);
				
				System.out.println("FID : " + fID);
				System.out.println("EQ_ORDER: " + eqOrder);
				
				dB.addNewGivenEquipFault(eqOrder, fID);

				// ReInitialising the Whole GUI ---------------------------------------------
    			faultsInEqList.setListData(dB.getFaultsbyRID(rID));
//    			allFaultsList.setListData(dB.getEqui_Faults(dB.getEquiTypebyRID(rID)));
    			faultNameField.setText("");
				approxDateField.setText("");

				//Resetting th Parent GUI
				tFrame.reSetGUI(yesSignal);
				
				JOptionPane pane = new JOptionPane();
				pane.showMessageDialog(null, "Fault Added Successfully!!!");
				pane.show();
	//			System.out.println("Fault Deleted Successfully!!!");
			}
			else
			{
				JOptionPane pane = new JOptionPane();
	    		pane.showMessageDialog(null, "First Select the Fault from ALL FAULTS LIST");
	    		pane.show();
			}
    	} catch (NumberFormatException | SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }                                        

   
    // Variables declaration - do not modify
    
    private int rID;
    private Database dB;
    private ButtonGroup bG;
    private boolean yesSignal = false;
    private TechnicianMainFrame tFrame;
    
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton addFromFieldsBtn;
    private javax.swing.JButton addFromListBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList faultsInEqList;
    private javax.swing.JList allFaultsList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton yesLabeledRadioBtn;
//    yesLabeledRadioBtn.
    private javax.swing.JRadioButton noLabeledRadioBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField faultNameField;
    private javax.swing.JTextField approxDateField;
    // End of variables declaration                   

    


}
