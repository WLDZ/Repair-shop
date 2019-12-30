import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * This class is responisible for connection to the database and all the functions necessary for insertions, updation and querying 
 * @author Shehzad Iqbal,Mazhar Shah, Muhammad Uzair and Waleed Zia 
 * @version 1.0
 */
public class Database {

	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String connStr = "jdbc:mysql://localhost/nml";
	
	
	/**
	 * constructor of the Database class
	 */
	public Database()
	{
		connect();
	}
	
	/**
	 * This method establishes the connection with the database
	 */
	public void connect()
	{
		try {
			conn =  DriverManager.getConnection(connStr, "root", "");
			st =  conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (SQLException e) {
			
			System.err.println("Run Wamp Server First");
		}
	}
	
	
	
	
	
	/**
	 * This method is used for getting Equipment Models from Database
	 * @param String
	 * @return String[]
	 * @throws SQLException
	*/	
	public String[] getEqui_Models(String manufName)
	{
		try {
			rs = st.executeQuery("Select model from equipment where manufacturer ='"+ manufName+ "';");
			rs.last();
			String[] arr = new String[rs.getRow()];
			
			rs.beforeFirst();
			
			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("model").toString();
				arr[i] = tmp;
				System.out.println(arr[i]);
				i++;
			}
		
			return arr;
		
		} catch (SQLException e) {
			System.out.println("Problem!!!");
			System.err.println(e.getMessage());
			
			return null;
		}

	}
	
	
	
	
	
	/**
	 * This method is used for getting all Equipment Types database
	 * @param String
	 * @return String[]
	 * @throws SQLException
	*/
	public String[] getEqui_Types() throws SQLException
	{
		rs = st.executeQuery("Select Distinct type from equipment");

		rs.last();
		String[] arr = new String[rs.getRow()];
		rs.beforeFirst();

		int i=0;
		while(rs.next())
		{
			String tmp = rs.getString("type").toString();
			arr[i] = tmp;
			i++;
		}
		return arr;
	}


	
	
		/**
		 * This method is used for getting all Equipment Faults from database
		 * @param String
		 * @return String[]
		 * @throws SQLException
		*/
		public String[] getEqui_Faults(String equiName) throws SQLException
		{
			rs = st.executeQuery("Select description from faults where type = '" + equiName + "';");

			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();

			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("description").toString();
				arr[i] = tmp;
				System.out.println(arr[i]);
				i++;
			}
			return arr;
		}
		
	
	

	/**
	 * 
	 * This method is used for getting Manufacturer's name according to the selected equipment
	 * @param String
	 * @return String[]
	 * @throws SQLException
	 * The purpose of this method is to provide all Manufacturers of specific type of Equipment.
	 */
	public String[] getManuf(String equi_type) throws SQLException
	{
		rs = st.executeQuery("Select Distinct manufacturer from equipment where type = '"+ equi_type +"'");

		rs.last();
		String[] mnfctr = new String[rs.getRow()];
		rs.beforeFirst();

		int i=0;
		while(rs.next())
		{
			String tmp = rs.getString("manufacturer").toString();
			mnfctr[i] = tmp;
//			System.out.println(mnfctr[i]);
			i++;
		}
		return mnfctr;
	}


	
		/**
		 * This method is used for getting all Technician ID's from database
		 * @param String
		 * @return String[]
		 * @throws SQLException
		*/
		public String[] get_All_TechID() throws SQLException
		{
			rs = st.executeQuery("Select TID from technician");

			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();

			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("TID").toString();
				arr[i] = tmp;
				i++;
			}
			return arr;
		}	
	

		
		
		/**
		 * This method is used for getting all Technician data by tech_ID from database 
		 * @param String
		 * @return String[]
		 * @throws SQLException
		*/
		public String[] getAllTechData(int tid) throws SQLException
		{
			rs = st.executeQuery("Select * from technician where TID = '" + tid + "'");

			ResultSetMetaData rsmd = rs.getMetaData();
			int cc = rsmd.getColumnCount();

			String[] arr = new String[cc];
			rs.next();

			for(int i = 1; i <= cc; i++)
			{
				String tmp = rs.getString(i);
				arr[i-1] = tmp;
				System.out.print(tmp + "  ");
			}
			return arr;
		}	



		
		/**
		 * This method is used for updating no of jobs of a given TID
		 * @param tid
		 * @throws SQLException
		 */
		public void updateNoOfJObs(int tid) throws SQLException {
			rs = st.executeQuery("select rid from repair_jobs where tid = "+ tid + " AND completion = 'No'");
			rs.last();
			
			int jobs = rs.getRow();
			System.out.println("jobs :" + jobs);
			int res = st.executeUpdate("update technician set Current_no_of_jobs = " + jobs + " where tid = " + tid + "");
			
		}
		

		
		
		/** This method is used for getting All Repair Jobs from database
		 * @return String[]
		 * @throws SQLException
		*/
		public String[] get_All_Jobs() throws SQLException
		{
			rs = st.executeQuery("Select RID from repair_jobs");

			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();

			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("RID").toString();
				arr[i] = tmp;
				System.out.println(arr[i]);
				i++;
			}
			return arr;
		}	
		
		

		
		/**This method is used for getting All Repair Jobs from database 
		 * @param String
		 * @return String[]
		 * @throws SQLException
		*/
		public String[] getJobsByOrderId(String orderId) throws SQLException
		{
			String querry = "select RID ,repair_jobs.equi_Order, equipment_in_order.OID from repair_jobs Inner Join "
			+ "equipment_in_order where repair_jobs.equi_order = equipment_in_order.equi_Order AND equipment_in_order.OID ="+ orderId+";";
			
			rs = st.executeQuery(querry);

			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();

			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("RID").toString();
				arr[i] = tmp;
				System.out.println(arr[i]);
				i++;
			}
			return arr;
		}	

			
			/**getting All UnAssigned Jobs from database 
			 * @param 
			 * @return String[]
			 * @throws SQLException
			*/
			
			public String[] get_UnAssign_Jobs() throws SQLException
			{
				rs = st.executeQuery("Select RID from repair_jobs where assignment = 'No' AND completion = 'No'");

				rs.last();
				String[] arr = new String[rs.getRow()];
				rs.beforeFirst();

				int i=0;
				while(rs.next())
				{
					String tmp = rs.getString("RID").toString();
					arr[i] = tmp;
					System.out.println(arr[i]);
					i++;
				}
				return arr;
			}	
			
			
			/**
			 * get All Completed Jobs from database
			 * @param 
			 * @return String[]
			 * @throws SQLException
			*/
			public String[] get_completed_Jobs() throws SQLException
			{
				rs = st.executeQuery("Select RID from repair_jobs where completion = 'Yes'");

				rs.last();
				String[] arr = new String[rs.getRow()];
				rs.beforeFirst();

				int i=0;
				while(rs.next())
				{
					String tmp = rs.getString("RID").toString();
					arr[i] = tmp;
					System.out.println(arr[i]);
					i++;
				}
				return arr;
			}
			

			
			/**
			 * get new order ID
			 * @return
			 * @throws SQLException
			 */
			public int getNewOrderId() throws SQLException
			{
				rs = st.executeQuery("Select * from orders");

				rs.last();
				int newID= rs.getRow();
				System.out.println(newID);

				return newID+1;
			}		
			
			
			
			
			
			/**
			 * get new order ID
			 * @return
			 * @throws SQLException
			 */
			public int getNewRID() throws SQLException
			{
				rs = st.executeQuery("Select * from repair_jobs");

				rs.last();
				int newID= rs.getRow();

				
				return newID+1;
			}		

			
			
			
	
			/**
			 * get new customer
			 * @return
			 * @throws SQLException
			 */
			public int getNewCustomerId() throws SQLException
			{
				rs = st.executeQuery("Select * from customer;");

				rs.last();
				int newID= rs.getRow();
				System.out.println(newID);

				return newID+1;
			}
			
			


			/**
			 * getting new customer
			 * @return
			 * @throws SQLException
			 */
			public int getNewEquiOrder() throws SQLException
			{
				rs = st.executeQuery("Select * from equipment_in_order");

				rs.last();
				int newID= rs.getRow();
				System.out.println(newID);

				return newID+1;
			}
			
			
			
			
			
			/**
			 * Saving customer data
			 * @param customerID
			 * @param fName
			 * @param LName
			 * @param address
			 * @param contactNo
			 * @param emailAdd
			 * @param entryDate
			 * @throws SQLException
			 */
			public void saveCustomerRecord(int customerID, String fName,String LName, String address, 
					String contactNo, String emailAdd ,String entryDate) throws SQLException
			{
				

				String updateQuery = "INSERT INTO customer VALUES("+customerID+","+"'"+fName+"','"+LName+"','"+	
				address+"','"+	contactNo+"','"+	emailAdd+ "','" +entryDate +"')";

				int resultSet = st.executeUpdate(updateQuery);
				
				
			}
			

			
			
			/**
			 * saving order data
			 * @param oID
			 * @param customerID
			 * @param completed
			 * @param delivered
			 * @param entryDate
			 * @param completedDate
			 * @param noOfItems
			 * @param shopNo
			 * @throws SQLException
			 */
			public void saveOrder(int oID, int customerID,String  completed, 
					String delivered, String entryDate, String completedDate , int noOfItems, int shopNo ) throws SQLException
			{
				
				

				String updateQuery = "INSERT INTO orders VALUES("+oID+","+customerID+",'"+completed+"','"+	
				delivered+"','"+	entryDate+"','"+completedDate +"',"+noOfItems+","+shopNo+")";

				int resultSet = st.executeUpdate(updateQuery);
				
			}
			
			
			
			/**
			 * saving Equipment_In_Order data
			 * @param equi_order
			 * @param OID
			 * @param EID
			 * @throws SQLException
			 */
			public void saveEquiInOrder(int equi_order , int OID, int EID) throws SQLException
			{
				
				String updateQuery = "INSERT INTO equipment_in_order VALUES("+ equi_order+","+OID+","+ EID +")";

				int resultSet = st.executeUpdate(updateQuery);
				
				
				
			}
			
			
			/**
			 * Saving  given_equip_faults DATA
			 * @param equi_order
			 * @param FID
			 * @throws SQLException
			 */
			public void saveGivenEquipFaults( int equi_order, int FID) throws SQLException
			{	
				String updateQuery = "INSERT INTO given_equip_faults (equi_order, FID) VALUES("+ equi_order +","+ FID +")";

				int resultSet = st.executeUpdate(updateQuery);
				
			}
			
			
			
			
			
			/**
			 * Saving given_equip_faults DATA
			 * @param RID
			 * @param equi_order
			 * @param TID
			 * @param dueDate
			 * @param entryDate
			 * @param completion
			 * @param description
			 * @param assignment
			 * @throws SQLException
			 */
			public void saveReapirJob(int RID , int equi_order, int TID, String dueDate, 
					String entryDate, String completion,String description, String assignment) throws SQLException
			{
				

				String updateQuery = "INSERT INTO repair_jobs VALUES("+ RID+","+equi_order+","+ TID+ 
						",'" +dueDate+"','"+entryDate+"','"+completion +"','"+description+"','"+assignment + "')";

				int resultSet = st.executeUpdate(updateQuery);
				
			}
			
			
			
			/**
			 * adding new equipment in database
			 * @param typ
			 * @param manuf
			 * @param mdl
			 * @throws SQLException
			 */
			public void addNewEquipInDataBase(String typ , String manuf , String mdl) throws SQLException {
				int res = st.executeUpdate("Insert into equipment (type, manufacturer , Model) values " +
						"('"+ typ +"','"+ manuf +"', '"+ mdl +"')");
				System.out.println(res);
			}
			
			
			
			

			/**
			 * getting faults by repair ID
			 * @param String
			 * @return String[]
			 * @throws SQLException
			*/			
			public String[] getFaultsbyRID(int repair_ID) throws SQLException
			{
				rs = st.executeQuery("Select faults.description from given_equip_faults inner join faults where given_equip_faults.equi_order" +
						" = (Select equi_order from repair_jobs where RID = '"+ repair_ID +"') AND given_equip_faults.fid = faults.fid");

				rs.last();
				String[] arr = new String[rs.getRow()];
				rs.beforeFirst();

				int i=0;
				while(rs.next())
				{
					String tmp = rs.getString("description").toString();
					arr[i] = tmp;
					System.out.println(arr[i]);
					i++;
				}
				return arr;
			}	


			

			
			
			/**
			 * getting repair job details
			 * @param repair_ID
			 * @return
			 * @throws SQLException
			 */
			public String[] getRID_Details( int repair_ID) throws SQLException {
				
				
				rs = st.executeQuery("select first_name, last_name from technician where tid = " +
						"(select tid from repair_jobs where rid = '"+ repair_ID +"')");

				ResultSetMetaData rsmd = null;
				String[] arr = null;

				rsmd = rs.getMetaData();
				
				arr = new String[rsmd.getColumnCount()];
					
				rs.next();
				for(int i=1 ; i<=rsmd.getColumnCount(); i++)
				{
					arr[i-1] = rs.getString(i);
				}
			
				rs = st.executeQuery("select due_date, completion, description from repair_jobs where rid = '"+ repair_ID +"'");
				rsmd = rs.getMetaData();
				rs.next();
				String[] data = new String[rsmd.getColumnCount() + 1];
				
				data[0] = arr[0] + " " + arr[1];
				
				System.out.println(data[0]);
				for(int j = 1; j <= rsmd.getColumnCount(); j++)
				{
					data[j] = rs.getString(j);
					System.out.println(data[j]);
				}
				
				rs = st.executeQuery("select assignment from repair_jobs where rid = '" + repair_ID + "'");

				rs.next();
				String assignCheck = rs.getString("assignment");
				System.out.println(assignCheck);
				if(assignCheck.contentEquals("No") || assignCheck.contentEquals("no") 
						|| assignCheck.contentEquals("N0")|| assignCheck.contentEquals("n0"))
				{
					data[0] = "Not Assigned Yet";
				}
					
				return data;
				
			}
			
			
			
			/**
			 * getting all orders
			 * @return
			 * @throws SQLException
			 */
			public String[] getAllOrders() throws SQLException {
							
							rs = st.executeQuery("select OID from orders");
							
							rs.last();
							String[] arr = new String[rs.getRow()];
							rs.beforeFirst();

							int i=0;
							while(rs.next())
							{
								String tmp = rs.getString("OID").toString();
								arr[i] = tmp;
								System.out.println(arr[i]);
								i++;
							}
							return arr;
			}

			
			/**
			 * getting completed orders
			 * @return
			 * @throws SQLException
			 */
			public String[] getCompletedOrders() throws SQLException {
				
				rs = st.executeQuery("select OID from orders where Completed = 'yes' And deliverd = 'No'");
				
				rs.last();
				String[] arr = new String[rs.getRow()];
				rs.beforeFirst();

				int i=0;
				while(rs.next())
				{
					String tmp = rs.getString("OID").toString();
					arr[i] = tmp;
					System.out.println(arr[i]);
					i++;
				}
				return arr;
			}

			
			



	/**
	 * getting login user ID
	 * @param username
	 * @param pasword
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public String getLoginUserId(String username, String pasword,String type) throws SQLException {
		
		int a,b,c;
		String query =  "Select login_Id from login where "
				+ "user_name='"+ username +"'" +" and password='"+pasword + "' and type='"+type +"';" ;
		
		rs = st.executeQuery(query);
		rs.getMetaData();
		
		
		if(rs.next()){
			String id = rs.getString("login_Id");
			System.out.println("Login ID "+ id);
			
			
			if(type.equals("receptionist"))
			{
				query = "Select recept_Id from receptionist where login_Id='"+id+"';";
				rs = st.executeQuery(query);
				rs.getMetaData();
				rs.next();
				String recpId = rs.getString("recept_Id");
				
				System.out.println("recep ID: "+recpId);
				
				return recpId;
			}
			else if(type.equals("manager"))
			{
				query = "Select manager_id from manager where login_Id='"+id+"';";
				rs = st.executeQuery(query);
				rs.getMetaData();
				rs.next();
				String managId = rs.getString("manager_Id");
				
				System.out.println("manag ID: "+managId);
				
				return managId;
				
				
			}
			else if (type.equals("technician"))
			{
				System.out.println("...................");
				query = "Select TID from technician where login_Id='"+id+"';";
				rs = st.executeQuery(query);
				rs.getMetaData();
				rs.next();
				String techId = rs.getString("TID");
				
				System.out.println("tech ID: "+techId);
				
				return techId;
				
				
				
			}
		}
		else
		{
				return "nil";
			
		}
		
	
		return "nil";
		
	}
	
	
	/**
	 * 	getting equip ID
	 * @param equipType
	 * @param manufacturer
	 * @param model
	 * @return int
	 * @throws SQLException
	 */	
	public int getEId(String equipType, String manufacturer, String model) throws SQLException {
				
		
		rs = st.executeQuery("Select EID from equipment where type ='"+ equipType+ "' and "  +"manufacturer='"+manufacturer+
				"' and model='"+model+"'");
		
		rs.last();
		
		
		System.out.println(rs.getString("EID"));
		return Integer.parseInt(rs.getString("EID"));
		
	}

	
	
	/**
	 * getting order details
	 * @param order_ID
	 * @return
	 * @throws SQLException
	 */
	public String[] getOrd_Details(int order_ID) throws SQLException {
		
		rs = st.executeQuery("select * from customer where customer_id = " +
				"(Select customer_id from orders where oid = '"+ order_ID +"')");
		
		
		// Initialising ResultSetMetaData to get Column count 
		ResultSetMetaData rsmd = rs.getMetaData();
		
		
		int cc = rsmd.getColumnCount();
		
		// Initialising Array to store query data
		String[] arr = new String[cc-1];
		rs.next();

		for(int i = 2; i <= cc ; i++ )
		{
			arr[i-2] = rs.getString(i).toString();
		}
		
		
		rs = st.executeQuery("select equi_order from equipment_in_order where oid = '"+ order_ID + "'");
		
		rs.last();
		String tmp[] = new String[rs.getRow()];
		rs.beforeFirst();
		
		int i=0;
		while(rs.next())
		{
			tmp[i] = rs.getString("equi_order").toString();
			i++;
		}
		
		int count = 0;
		for(int j = 0; j < tmp.length; j++)
		{
			rs = st.executeQuery("select rid from repair_jobs where equi_order = '" + tmp[j] + "' AND completion  = 'yes'");
			
			if(rs.next())
				count ++; 
			
		}
		
		//Final Array to store all details
		String details[] = new String[arr.length + 2];
		rs = st.executeQuery("select no_of_items from orders where oid = '" + order_ID +"'");
		
		rs.next();
		String items = rs.getString("no_of_items");
		details[0] = count + "";
		details[1] = items;
		for(int j = 0; j < arr.length; j++)
		{
			details[j+2] = arr[j];
		}

		
		return details;
	}
	

	
	
	
	/**
	 * 	getting approximate fix time of faults
	 * @param name
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public int getFixTimeofFaults(String name, String type) throws SQLException {
		
		rs = st.executeQuery("select Approx_fix_time from faults where description = '"+ name + "' AND type = '" + type + "'");
		rs.next();
		
		String tmp = rs.getString("Approx_fix_time").toString();
		System.out.println(tmp);
		return Integer.parseInt(tmp);
		
	}

	
	/**
	 * getting Faults Id by name
	 * @param name
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public int get_FID_ByName(String name,String type) throws SQLException {
		
		rs = st.executeQuery("select fid from faults where description = '"+ name + "' AND type = '" + type +"'");
		rs.next();
		
		String tmp = rs.getString("fid").toString();
		return Integer.parseInt(tmp);
		
	}

	

	
	/**
	 * getting jobs by tech ID
	 * @param TID
	 * @return
	 * @throws SQLException
	 */
	public String [] getJobsByTID(int TID) throws SQLException {
		
		rs = st.executeQuery("select RID from repair_jobs where TID = '"+ TID + "' And completion = 'No' AND assignment = 'Yes'");
		
		rs.last();
		String[] arr = new String[rs.getRow()];
		rs.beforeFirst();

		int i=0;
		while(rs.next())
		{
			String tmp = rs.getString("RID").toString();
			arr[i] = tmp;
			System.out.println(arr[i]);
			i++;
		}
		return arr;
		
	}

	
	
	/**
	 * update order status as yes
	 * @param OID
	 * @throws SQLException
	 */
	public void UpdateOrderStatusAsYes(String OID) throws SQLException {
		
		int res = st.executeUpdate("update orders set deliverd = 'Yes' where OID = "+OID+"");
		
	}
	
	
	
	
	/**
	 * updating job completion as 'Yes'
	 * @param rID
	 * @throws SQLException
	 */
	public void UpdateCompletionAsYes(int rID) throws SQLException {
		
		int res = st.executeUpdate("update repair_jobs set completion = 'Yes' where RID = "+rID+"");
		
	}	

	
	
	
	
	
	
	/**
	 * getting booked till date
	 * @param tID
	 * @return
	 * @throws SQLException
	 */
	public String getBookTillDate(int tID) throws SQLException {

		rs = st.executeQuery("select due_date from repair_jobs where TID = '"+ tID + "' And completion = 'No' order by due_date desc limit 1");
		if(rs.next()){
			System.out.println(rs.getString(1));
			// returning the last date till he booked
			return rs.getString(1).toString();
			
		}
		return "1111-11-11";
	}

	
	
	
	/**
	 * getting all unassigned technicians jobs
	 * @param tid
	 * @return
	 * @throws SQLException
	 */
	public String[] techUnAssignedJobs(int tid) throws SQLException {
		
		rs = st.executeQuery("select RID from repair_jobs where tid = "+ tid + " AND " +
				"assignment = 'No' AND completion = 'No'");

		rs.last();
		String[] arr = new String[rs.getRow()];
		rs.beforeFirst();

		int i=0;
		while(rs.next())
		{
			String tmp = rs.getString("RID").toString();
			arr[i] = tmp;
			System.out.println(arr[i]);
			i++;
		}
		System.out.println(arr.length);
		return arr;

	}

	
	

	
	/**
	 * getting equipment type by repair ID
	 * @param repairID
	 * @return String
	 * @throws SQLException
	 */
	public String getEquiTypebyRID(int repairID) throws SQLException {
		
		rs = st.executeQuery("select type from equipment where EId = " +
				"(select EID from equipment_in_order where equi_order = " +
				"(select equi_order from repair_jobs where RID = '"+ repairID + "'))");

		rs.next();
		
		return rs.getString("type").toString();
		
		
	}

	
	
	
	
	
	/**
	 * getting faults ID by description
	 * @param desc
	 * @param type
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public int getFIDbyDescription(String desc, String type) throws NumberFormatException, SQLException {
		
		rs = st.executeQuery("select FId from Faults where description = '" + desc + "' And type = '" + type + "'");
		
		rs.next();
		return Integer.parseInt(rs.getString("FID").toString());
		
	}

	
	
	/**
	 * getting equipment order by repair ID
	 * @param rid
	 * @return Integer Value
	 * @throws SQLException
	 */
	public int getEquiOrderbyRID(int rid) throws SQLException {
		
		rs = st.executeQuery("select equi_order from repair_jobs where rid = '" + rid + "'");
		
		rs.next();
		return Integer.parseInt(rs.getString("equi_Order").toString());
		
	}

	
	
	/**
	 * deleting any fault
	 * @param eqOrder
	 * @param fID
	 * @throws SQLException
	 */
	public void deleteFault(int eqOrder, int fID) throws SQLException {
		int reslt = st.executeUpdate("delete from given_Equip_faults where equi_order = '" + eqOrder + "' And Fid = '" + fID + "'");
	
	}
	

	
	
	/**
	 * adding new given equipment faults
	 * @param eqOrder
	 * @param fID
	 * @return
	 * @throws SQLException
	 */
	public int addNewGivenEquipFault(int eqOrder, int fID) throws SQLException {
		
		int rzlt = st.executeUpdate("Insert into Given_equip_faults (equi_order, FID) values ('"+ eqOrder +"', '"+ fID +"')");
	
		return lastInsertedID();
	}

	
	
	
	
	/**
	 * getting details for technician
	 * @param rID
	 * @return
	 * @throws SQLException
	 */
	public String[] getDetailsForTechFrame(int rID) throws SQLException {
		
		rs = st.executeQuery("select OID from equipment_in_order where equi_order = " +
				"(select equi_order from repair_jobs where rid = '" + rID + "')");
		
		String[] arr = new String[2];
		rs.next();
		arr[0] = rs.getString("OID").toString();
		
		rs = st.executeQuery("select due_date from repair_jobs where RID = '" + rID + "'");
		rs.next();
		arr[1] = rs.getString("due_date");
		
		return arr;
	}
	
	
	
	/**
	 * updating technicain status
	 * @param status
	 * @param TID
	 * @throws SQLException
	 */
	public void updateTechnicianStatus(String status, String TID) throws SQLException {
		
		
		int res = st.executeUpdate("update technician set current_status ='"+ status
		+ "' where TID = "+TID+ "");
		
	}
	
	
	
	/**
	 * updating technician's leave
	 * @param dateFrom
	 * @param dateTo
	 * @param TID
	 * @throws SQLException
	 */
	public void updateTechLeave(String dateFrom,String dateTo, String TID) throws SQLException {
		
		
		int res = st.executeUpdate("update technician set leave_from ='"+ dateFrom
			+ "' where TID = "+TID+ "");
		int res2 = st.executeUpdate("update technician set leave_to ='"+ dateTo
			+ "' where TID = "+TID+ "");
	
			
}	
	
	
	
	
	/**
	 * getting last inserted ID
	 * @return
	 * @throws SQLException
	 */
	public int lastInsertedID() throws SQLException {
		
		rs = st.executeQuery("select last_Insert_ID()");
		
		rs.next();
		int id = Integer.parseInt(rs.getString(1).toString());
		
		return id;
	}
	
	
		/**
		 * adding new general faults
		 * @param descrip
		 * @param time
		 * @param type
		 * @return
		 * @throws SQLException
		 */
		public int addGenerlFault(String descrip, int time, String type) throws SQLException {
			
			int rzlt = st.executeUpdate("Insert into faults (description, approx_fix_time, type) " +
					"values ('"+ descrip +"', '"+ time +"', '"+ type +"')");
			
			return lastInsertedID();
		}
		
		
		
		/**
		 * getting all messages my repair ID
		 * @param rID
		 * @return
		 * @throws SQLException
		 */
		public String[] allMsgsByRID(int rID) throws SQLException {
			
			rs = st.executeQuery("select Post from messages where rid = '" + rID +"'");
			
			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();
	
			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("post").toString();
				arr[i] = tmp;
				i++;
			}
			return arr;
		}
		
		
		/**
		 * adding repair job's message
		 * @param text
		 * @param rID
		 * @throws SQLException
		 */
		public void addRepairJobMsg(String text, int rID) throws SQLException {
			int rzlt = st.executeUpdate("Insert Into messages (Post, RID) values ('"+ text +"', '" + rID + "')");
			
		}

		
		
		
		/**
		 * calculate order Due Date
		 * @param oID
		 * @return String
		 * @throws SQLException
		 */
		public String calcOrderDueDate(int oID) throws SQLException {
			rs = st.executeQuery("select repair_jobs.due_date from repair_jobs inner join equipment_in_order where " +
					"repair_jobs.equi_order = equipment_in_order.equi_order AND equipment_in_order.OID = '" + oID + "' " +
							"order by due_date desc limit 1");
		
			if(rs.next()){
				return rs.getString(1);
			}
			else
				return "";
		}		

		
		
		
		/**
		 * update order due date
		 * @param oID
		 * @param orDueDate
		 * @throws SQLException
		 */
		public void updateOrderDueDate(int oID, String orDueDate) throws SQLException {
			
			int res = st.executeUpdate("update Orders set completed_date = '"+ orDueDate + "' where OID = "+ oID+"");
			
		}
		
		
		

		/**
		 * 	assignment of jobs to technician
		 * @param rID
		 * @param tID
		 * @throws SQLException
		 */
		public void assignJob_to_Technician(int rID, int tID) throws SQLException {
			
			int res = st.executeUpdate("update repair_jobs set tid = "+ tID +" where RID = '"+ rID +"'");
			
			int res2 = st.executeUpdate("update repair_jobs set assignment = 'Yes' where RID = '"+ rID +"'");
		}
		
		
		
		/**
		 * getting Tech ID by repair job ID
		 * @param rID
		 * @return
		 * @throws SQLException
		 */
		public int getTID_by_RepairJob(int rID) throws SQLException {
			
			rs = st.executeQuery("select tid from repair_jobs where RId = '" + rID +"'");
		
			rs.next();
			return Integer.parseInt(rs.getString(1).toString());
		}
		
		
		
		/**
		 * getting job's due date
		 * @param tID
		 * @return
		 * @throws SQLException
		 */
		public String[] getJobsDueDate(int tID) throws SQLException {
			
			rs = st.executeQuery("select due_date from repair_jobs where tId = '" + tID +"' AND completion = 'No'");

			rs.last();
			String[] arr = new String[rs.getRow()];
			rs.beforeFirst();
	
			int i=0;
			while(rs.next())
			{
				String tmp = rs.getString("due_date").toString();
				arr[i] = tmp;
				System.out.println(arr[i]);
				i++;
			}
			
			return arr;
		}
		
		
		
		
		/**
		 * update assigment as no
		 * @param tid
		 * @param dueD
		 * @throws SQLException
		 */
		public void updateAssignmentAsNo(int tid, String dueD) throws SQLException {
			int res = st.executeUpdate("update repair_jobs set assignment = 'No' where tid = "+ tid +" AND due_date = '" + dueD +"'");
			System.out.println(res);
		}
		

		/**
		 * adding faults in database 
		 * @param fltName
		 * @param approx_days
		 * @param type
		 * @throws SQLException
		 */
		public void addFaultInDB(String fltName, String approx_days, String type) throws SQLException
		{
			int res = st.executeUpdate("insert into faults (description, approx_fix_time,type) values " +
					"('"+ fltName +"','"+ approx_days +"','"+ type +"')");
			System.out.println(res);
		}
		
		
		public static void main(String[] args) throws SQLException {
		
		Database db = new Database();
//		db.addNewFaultInDataBase("Fridge", "PEL", "MH009");
		db.addFaultInDB("Moter Damage", "4", "Fan");
//		db.techUnAssignedJobs(1);

//		db.updateAssignmentAsNo(3, "2014-02-16");
		
//		db.getJobsDueDate(2);
//		db.getBookTillDate(1);
//		db.OrderDueDate(17);
//		db.getFixTimeofFaults("Fan Problem", "Laptop");
//		db.updateNoOfJObs(4);
		
		//db.getManuf("Laptop");
		//db.getEqui_Types();
		//db.getEqui_Models("Dell");
		//db.getEqui_Faults("Laptop");
		//db.get_All_TechID();
		//db.getAllTechData(4);
		//db.get_All_Jobs();
		//db.get_UnAssign_Jobs();
		//db.get_completed_Jobs();
		
		//db.getJobsByOrderId("1");
//		db.getNewOrderId();
//		db.getNewCustomerId();
		
//		System.out.println(db.lastInsertedID());
		
//		db.getEquiTypebyRID(1);
//		db.getFaultsbyRID(1);
//		db.getfaults(1);
		
//		db.getJobsByOrderId(25+"");
//		db.getRID_Details(1);
		
		
//		db.getEId("chimni", "Uzair", "Uzair_Dhooowa_d500");
	
//		System.out.println(db.getNewSNoForFaults());
	}
	
//		-----------------------------------------------------------------------------------------
//	 //adding days into date and getting new 
//	   String dt = "2008-01-01";  // Start date
//	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	   Calendar c = Calendar.getInstance();
//	   c.setTime(sdf.parse(dt));
//	   c.add(Calendar.DATE, 1);  // number of days to add
//	   dt = sdf.format(c.getTime());  // dt is now the new date
//	   
//	   System.out.println(dt);
   
//	   //get difference in number of days between two dates
//	   String str_date1 = "20/02/2011";
//	   String str_time1 = "11:00 AM";
//
//	   String str_date2 = "30/02/2011";
//	   String str_time2 = "11:00 AM" ;
//
//	   DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
//	   Date date1 = formatter.parse(str_date1 + " " + str_time1);
//	   Date date2 = formatter.parse(str_date2 + " " + str_time2);
//
//	   // Get msec from each, and subtract.
//	   long diff = date2.getTime() - date1.getTime();
//
//	   System.out.println("Difference In Days: " + (diff / (1000 * 60 * 60 * 24)));		





}
