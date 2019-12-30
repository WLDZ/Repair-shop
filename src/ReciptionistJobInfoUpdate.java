import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * This class is responisible for showing the receptionist orders GUI
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class ReciptionistJobInfoUpdate extends javax.swing.JFrame {

	/**
     * Creates new form ReciptionistJobInfoUpdate
     */
    public ReciptionistJobInfoUpdate(RecipitionistManageJobsPanel p, int repairJobID, boolean flag) {

    	this.flagg=flagg;
    	System.out.println("falg                "+flag);
    	instance = p;
    	rID = repairJobID;
        initComponents();
        
    	this.setSize(800, 600);
    	this.setLocation(250, 30);
    	this.setTitle("Job Info Update");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * @throws SQLException 
     */                         
    private void initComponents() {

    	dB = new Database();
    	
    	
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        allTechList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        techName = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        specLabel = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        noJobs = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        bookTillLabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        assignBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 31, 123));
        jLabel1.setText("Select New Technician");

        allTechList.setBackground(new java.awt.Color(204, 204, 255));
        try {
			allTechList.setModel(new javax.swing.AbstractListModel() {
			    String[] strings = dB.get_All_TechID();
//            	{ "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
			    public int getSize() { return strings.length; }
			    public Object getElementAt(int i) { return strings[i]; }
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        allTechList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

				showTechnicainDetail();
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
        
        jScrollPane1.setViewportView(allTechList);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(9, 31, 123));
        jLabel2.setText("Technicain Details:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(9, 31, 123));
        jLabel25.setText("Name");

        techName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        techName.setText("");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(9, 31, 123));
        jLabel27.setText("Speciality");

        specLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        specLabel.setText("            ");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(9, 31, 123));
        jLabel29.setText("Current No. of Jobs:");

        noJobs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        noJobs.setText("            ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(9, 31, 123));
        jLabel31.setText("Booked till:");

        bookTillLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bookTillLabel.setText("            ");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(9, 31, 123));
        jLabel33.setText("Current Status:");

        statusLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(0, 153, 0));
        statusLabel.setText("            ");

        assignBtn.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        assignBtn.setForeground(new java.awt.Color(9, 31, 123));
        assignBtn.setText("Assign Job");
        assignBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bookTillLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(noJobs))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(specLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(techName))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104)
                .addComponent(assignBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(techName)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(specLabel)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(noJobs)
                        .addGap(24, 24, 24)
                        .addComponent(assignBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(bookTillLabel))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(statusLabel))))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    
    /**
     * showing the technician details
     */
    private void showTechnicainDetail() {
		String array[];
		int selectedValue = Integer.parseInt(allTechList.getSelectedValue().toString());
		
		try {
			
			String bkTill = dB.getBookTillDate(selectedValue);
			array=dB.getAllTechData(selectedValue);
			techName.setText( array[1]+" "+ array[2] + "");
			specLabel.setText(array[3]);
			noJobs.setText(array[4]);
//			
			bookTillLabel.setText(bkTill);
//			
			statusLabel.setText(array[5]);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
    
    
    /**
     * 
     * @param evt
     */
    private void assignBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    	
    	if(!allTechList.isSelectionEmpty())
    	{
    		int tID = Integer.parseInt(allTechList.getSelectedValue().toString());
    		try {
    			int lastAssignedTechnician = dB.getTID_by_RepairJob(rID);
    			
				dB.assignJob_to_Technician(rID,tID);
				dB.updateNoOfJObs(lastAssignedTechnician);
				dB.updateNoOfJObs(tID);
				showTechnicainDetail();
				
				
//				.ok
//				System.out.println("res1");
//				instance.resetGui();
//				System.out.println("res2");
				System.out.println("Sent to main flag before: "+instance.flag);
//				instance.flag= true;
//				flagg = true;
				instance.resetGui();
				
				System.out.println("Sent to main flag after: "+instance.flag);
				dispose();
				

		    	//..............................    	
//		    	System.out.println("reset main manage jobs gui");
//		    	System.out.println("Reset Gui");
//		    	  instance.allJobsList.setModel(new javax.swing.AbstractListModel() {
//		              String[] strings = dB.get_All_Jobs();
//		              public int getSize() { return strings.length; }
//		              public Object getElementAt(int i) { return strings[i]; }
//		          });
		    	
//		    	System.out.println("11111111111111111");
//		    	instance.allJobsList.setSelectedIndex(2);
//		    	System.out.println("222222222222222");
//		    	postsTextArea = new javax.swing.JTextArea();
//		    	postsTextArea.setText("");
		//  
//		    	techNameLabel = new JLabel();
//		    	techNameLabel.setText("");
//		    	deadLineData.setText("");
//		    	completedLabel.setText("");
//		    	isnallJobsList.setSelectedIndex(0);
		 //.....................
		    	
				
				
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    	
    	
    	else
    	{
    		setVisible(true);
    		JOptionPane.showMessageDialog(instance, "Technicain Not Selected!!!\nFirst Select Technician From List");
    	}
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReciptionistJobInfoUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReciptionistJobInfoUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReciptionistJobInfoUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReciptionistJobInfoUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					new ReciptionistJobInfoUpdate(new RecipitionistManageJobsPanel(),1,true).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//                ReciptionistJobInfoUpdate t = new ReciptionistJobInfoUpdate(1);
//                t.setVisible(true);
//                t.setSize(700, 480);
//                t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }

    // Variables declaration
    
    public boolean flagg;
    private int rID;
    private Database dB;
    private RecipitionistManageJobsPanel instance;
    
    private javax.swing.JButton assignBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel techName;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel specLabel;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel noJobs;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel bookTillLabel;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JList allTechList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
