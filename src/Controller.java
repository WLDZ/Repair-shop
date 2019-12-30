import java.awt.Dialog;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Controller {
			
	
	public static void insertStyle() {
	
		try {
			
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception ex) {
				
		}
		
	}
	
	public static void  main (String [] asdasd) throws SQLException
	{
		
		Controller.insertStyle();
		Controller.showRelevevantGui();
		
		
	}
	
	
	
	
	public static void showRelevevantGui() throws SQLException {
		
		String TID= null;
		String RID= null;
		String MID= null;
		
		Database d = new Database();

		boolean b= true;
		
				b=false;
				 LoginGui login = new  LoginGui();
				login.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			    
				
				login.setVisible(true);
				String loginData[] = null;
		while(true)
		{
//				String loginData[] = null;
				System.out.println("Loop Strt Again........ ");

				while(login.logged==false)
				{
					if(login.logged==true )
					{
						loginData= login.getLoginInfo();
						
						//addition
//						{
//							
//						}
						login.logged = false;
						break;
						
					}
					
						
				}
				
				
		    	
		    	String accountType = loginData[2];
		    	
//				System.out.println(accountType);
				System.out.println("Entered Info in Login Gui: " + "user: "+loginData[0]+" " +"pass: "+ loginData[1]+" " +"button: "+ loginData[2] );
				
				
				String IdForMainGui = d.getLoginUserId(loginData[0], loginData[1], loginData[2]);
		    	 
		    	if(!IdForMainGui.equals("nil") && loginData[0]!="nil" && loginData[1]!="nil" && loginData[2]!="nil" ){
			    	switch (accountType) {
					case "receptionist":
					{
						
						ReciptionistMainFrame r =new ReciptionistMainFrame(IdForMainGui);
						r.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
					    
						//test
						System.out.println(IdForMainGui);
						
	
						while(true)
						{
							if(r.logOut==true)
							{
								login.setVisible(true);
								System.out.println("receptionist logout true ho gya..!!");
								login.logged = false;
//								
								System.out.println(r.logOut);
//								System.out.println(login.logged);

								
								login.bGroup.clearSelection();
								login.passwordField.setText("");
								login.userNameTextField.setText("");
								r.dispose();
								
								
//								loginData= null;
	
								
								break;
							}
							
						}
	
						login.logged = false;
						break;
					}
					case "manager":
					{
						ManagerMainFrame m =new ManagerMainFrame(IdForMainGui);
						m.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
					    
						//test
						System.out.println(IdForMainGui);
						while(true)
						{
							if(m.logOut==true)
							{
								login.setVisible(true);
								System.out.println("manager logout true ho gya..!!");
								login.logged = false;
//								System.out.println(m.logOut);
//								System.out.println(login.logged);

								login.bGroup.clearSelection();
								login.passwordField.setText("");
								login.userNameTextField.setText("");
								m.dispose();
								
//								loginData= null;
								
								break;
							}
							
						}
						login.logged = false;
						break;
						
					}
					case "technician":
					{
						TechnicianMainFrame t= new TechnicianMainFrame(Integer.parseInt(IdForMainGui));
						t.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
						t.setVisible(true);
					    
						//test
//						System.out.println(IdForMainGui);
						
						while(true)
						{
							if(t.logOut==true)
							{
								login.setVisible(true);
								System.out.println("technician logout true ho gya..!!");
								login.logged = false;
//								
//								System.out.println(t.logOut);
//								System.out.println(login.logged);

								login.bGroup.clearSelection();
								login.passwordField.setText("");
								login.userNameTextField.setText("");
								t.dispose();
								
//								loginData= null;
								
								break;
							}
							
						}
						login.logged = false;
						break;
						
					}
			
			
					default:{
						login.setVisible(true);
						login.logged = false;
						break;
					}
						
					}
		    	}
		    	else
		    	{
		    		
		    		
		    		login.setVisible(true);
		    		System.out.println();
//					if(loginData[0]=="nil" || loginData[1]=="nil" || loginData[2]=="nil" ||
//							login.managerRadioButton.isSelected()==false ||
//							login.technicianRadioButton.isSelected()==false || login.receptionistRadioButton.isSelected()==false)
//					{
					
		    		
		    		
		    		
		    		
		    		
//					if(loginData[0]=="nil" )
//					{
//						System.out.println("user name is empty");
////						if()
//					}
//					else if(loginData[1]=="nil" )
//					{
//						System.out.println("password is empty");
//					}
//					else if(loginData[2]=="nil" )
//					{
//						System.out.println("loginData[2]==nil");
//					}
					
						//counting the buttons selected
//						int buttonsSelectedCount=0;
//					
//						if(login.managerRadioButton.isSelected()==true)
//						{
//							buttonsSelectedCount++;
//						}
//						if(login.receptionistRadioButton.isSelected()==true)
//						{
//							buttonsSelectedCount++;
//						}
//						if(login.technicianRadioButton.isSelected()==true)
//						{
//							buttonsSelectedCount++;
//						}
//						
						if(loginData[0]=="nil" )
						{
							
							JOptionPane.showMessageDialog(login, "User Name field cannot be left empty!");
						}
						else if(loginData[1]=="nil" )
						{
							JOptionPane.showMessageDialog(login, "Password field cannot be left empty!");
							
						}
						
						else if(loginData[2]=="nil" )
						{
							JOptionPane.showMessageDialog(login, "Please select any  designation");
							
						}
						
							
//						else if(buttonsSelectedCount==0 )
//						{
//							
//							System.out.println("Radio buttons must be selected");
//						}
					
					
					
					
//					if(login.technicianRadioButton.isSelected()==false)
//					{
//						System.out.println("Tech.isSelected() "+ login.technicianRadioButton.isSelected());
//					}
//					if(login.receptionistRadioButton.isSelected()==false)
//					{
//						System.out.println("recp.isSelected() "+ login.receptionistRadioButton.isSelected());
//					}
					
					
					
//		    	}
					
					else 
					{
						
						JOptionPane.showMessageDialog(null, "Wrong Entry!!!");
					}
					
					
//					
					
					
					login.bGroup.clearSelection();
					login.passwordField.setText("");
					login.userNameTextField.setText("");
					
		    		login.logged = false;
		    	}
		  }
//        ReciptionistMainFrame j = new ReciptionistMainFrame();
		    	
		
		

		
		
	}

}
