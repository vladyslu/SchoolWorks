/*  Name: Vladyslav Luchenko 
 * Course:CNT 4714–Spring 2021–Project Four
 * Assignment title:A Three-Tier Distributed Web-Based Application
 * Date:Wednesday April 28, 2021
*/

package project4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.table.DefaultTableModel;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class servlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Connection connection;
	static ResultSetMetaData metaData;
	static String command;
	static String resulty;
	static String table;
	
	static ResultSet resultSet;
	static DefaultTableModel dataModel;
	static String resultConnecting = null;
	
	
	
	public void service( HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		
	
	      try {
	    	  Class.forName("com.mysql.cj.jdbc.Driver"); 
	    	  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4?useTimezone=true&serverTimezone=UTC", "root", "root");
	         System.out.println("Connection is successful !!!!!");
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		
		System.out.println(req.getParameter("command"));
	String inputPlace = "";
		if(req.getParameter("reset") == null) {
		command = req.getParameter("command");
		if(command!=null) {
		inputPlace = command;
		}
		}else {
			inputPlace = "";
		}
		
		if(command == null) {
			command = "";
		}
		
		
		
		 
		//ServletResponse response;
		res.setContentType("text/html");
        PrintWriter writer = res.getWriter();        
        writer.println("<html>");
        writer.println("<head><style>\r\n"
        		+ "table, th, td {\r\n"
        		+ "  border: 1px solid black;\r\n"
        		+ "}\r\n"
        		+ "\r\n tr {background-color: darkred;} body {color: yellow;} td {background-color: black;}"
        		+ "table {background-color: purple;}"
        		+ "</style>");
        writer.println("<title>CNT 4718 Remote Database Manager</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=blue>");

        writer.println("<center>");
        writer.println("<h1>Welcome to the Spring 2021 Project 4 Enterprise Database System <br>"
        		+ "A Servlet/JSP-based Multi-tiered Enterprise Application Utilizing A "
        		+ "Tomcat Container</h1>");
        writer.println("<h2>Developed by: Vladyslav Luchenko</h2><hr><br>You are connected to the Project 4 Enterprise"
        		+ "System database as an administrator. <br>"
        		+ "Please enter any valid SQL query or update command.");
        
        
        
        writer.println("<br>");
        req.setAttribute("command", inputPlace);
        //////////////////////////////////////////////////////////////////////////
        req.getRequestDispatcher("/input.jsp").include(req, res);
        //req.getRequestDispatcher("/index.jsp").forward(req, res);
        
      //  writer.println("    <table>  \r\n");
        		
        if(command != "") {
        try {
			//tableModel = new ResultSetTableModel(null);
        	Statement statement;
			statement = connection.createStatement();
			
			if ( command.toLowerCase().indexOf("select") != -1 ) {
				System.out.println("check");
				resultSet = statement.executeQuery(command);
				 metaData = resultSet.getMetaData();
				 
					int columnsNumber = metaData.getColumnCount();
					System.out.println(columnsNumber);
					


					///////////
				    //Vector<String> columnNames = new Vector<String>();
				    int columnCount = metaData.getColumnCount();
				     table = new String("");
				    
				    table = table + "    <table>  \r\n";
				    table = table + "    <tr>";
				    for (int column = 1; column <= columnCount; column++) {
				    	
				    	table = table + "<th>"+metaData.getColumnName(column)+"</th>";
				       // columnNames.add(metaData.getColumnName(column));
				    }
				    table = table + "</tr>";
				  
				   // Vector<Vector<Object>> data = new Vector<Vector<Object>>();
				    while (resultSet.next()) {
				        //Vector<Object> vector = new Vector<Object>();
				    	table = table + "    <tr>";
				        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				           // vector.add(resultSet.getObject(columnIndex));
				        	table = table + "<td>"+resultSet.getObject(columnIndex)+"</td>";
				        }
				        table = table + "</tr>";
				        //data.add(vector);
				    }
					
					//scrollPane.add(table);
				    table = table + " </table>";
					
				    req.setAttribute("table",table);
				    req.getRequestDispatcher("/index.jsp").include(req, res);
					
					
					
					System.out.println("done");
				} else {
					
					if(req.getParameter("reset") == null) {
						System.out.println("Execute Shipments here:" + command);
					int rows = statement.executeUpdate(command);
					Boolean business = false;
					resulty = "<div style=\"background-color:green !important;\"><p "
							+ "style=\\\"color:black !important;\\\">"
							+ "The statement executed successfuly.<br> \n "+ rows +" row(s) affected.</p>";
					
					if (command.toLowerCase().indexOf("shipments") != -1 ) {
						
						if(command.toLowerCase().indexOf("insert") != -1 ) {
						String values = command.substring(command.indexOf("(")+1, command.indexOf(")"));
						String[] arrayValues = values.split(",");
						System.out.println(arrayValues[arrayValues.length-1]);
						if(Integer.valueOf(arrayValues[arrayValues.length-1].replaceAll(" ", "")) >= 100) {
							business = true;
						}
						}
						
						if(command.toLowerCase().indexOf("update") != -1) {
							//String value = command.substring(command.indexOf("\'"), command.indexOf("\'"));
							//System.out.println("update this one: " + value);
							//resultSet = statement.executeQuery("select quantity from shipments where snum = '" + value + "';");
							
							//while(resultSet.next()) {
							       // System.out.println(suppliers[i] + " has " + resultSet.getInt(1));
							       // if(resultSet.getInt(1) >= 100) {
							business = true;
									//	break;
									//}
						}
						
						
							
						if(business) {
						System.out.println("Shipments here:");
						resultSet = statement.executeQuery("select snum from suppliers;");
						ResultSetMetaData rsmd = resultSet.getMetaData();
						int columnsNumber = rsmd.getColumnCount();
						
						resultSet = statement.executeQuery("select snum from suppliers;");
						
						String[] suppliers = new String[1000];
						int counter = 0;
						int countWinners = 0;
						while (resultSet.next()) {
							for (int i = 1; i <= columnsNumber; i++) {
								
						        if (i > 1) System.out.print(",  ");
						        String columnValue = resultSet.getString(i);
						        suppliers[counter] = columnValue;
						        counter++;
						    }

						}
						
						for (int i = 0; i < counter; i++) {
						 resultSet = statement.executeQuery("select quantity from shipments where snum = '" + suppliers[i] + "';");
						 while(resultSet.next()) {
						 
					       // System.out.println(suppliers[i] + " has " + resultSet.getInt(1));
					        if(resultSet.getInt(1) >= 100) {
								String businessLog2 = "update suppliers set status = status + 5 where snum = '" + suppliers[i] + "';";
								countWinners++;
								statement.executeUpdate(businessLog2);
								System.out.println("Executed " + suppliers[i]);
								break;
							}
						}
						}
						if(countWinners > 0) {
							resulty = resulty + "Business Logic Detected! - Updating Supplier Status<br>"
									+ "Business Logic updated "+ countWinners +" supplier status marks.";
							
						}
						}
						}
						
						/*
						String values = command.substring(command.indexOf("(")+1, command.indexOf(")"));
						String[] arrayValues = values.split(",");
						
						System.out.println(arrayValues[arrayValues.length-1]);
						if(Integer.valueOf(arrayValues[arrayValues.length-1].replaceAll(" ", "")) >= 100) {
							resulty = resulty + "Business Logic Detected! - Updating Supplier Status<br>"
									+ "Business Logic updated 1 supplier status marks.";
							int count = 0;
							for(int i=0; i< arrayValues.length-1; i++) {
								arrayValues[i] = arrayValues[i]. replaceAll("\\\"", "");
								System.out.println(arrayValues[i]);
								String businessLog = "update suppliers set status = status + 5 where snum = " + arrayValues[i] + ";";
								statement.executeUpdate(businessLog);
							}
							
						}else {
							
							int count = 0;
							for(int i=0; i< arrayValues.length-1; i++) {
								arrayValues[i] = arrayValues[i]. replaceAll("\\\"", "");
								//System.out.println(arrayValues[i]);
								String businessLog = "select sum(quantity) from shipments where snum = " + arrayValues[i] + ";";
								resultSet = statement.executeQuery(businessLog);
								metaData = resultSet.getMetaData();
									resultSet.next();
									if( resultSet.getInt(1) >= 100) {
										String businessLog2 = "update suppliers set status = status + 5 where snum = " + arrayValues[i] + ";";
										statement.executeUpdate(businessLog2);
									}

									    //  System.out.print(resultSet.getInt(1) + " "); //Print one element of a row

									
						
								
								}
						}
						*/
					}
					
					
					resulty = resulty + "</div>";
					req.setAttribute("table",resulty);
				 req.getRequestDispatcher("/index.jsp").include(req, res);
				}
			 
			
			 
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Exception:"+e);
	        //JOptionPane.showMessageDialog(null, e.getMessage());
			req.setAttribute("table",e.getMessage());
		    req.getRequestDispatcher("/index.jsp").include(req, res);
		    
		    
		    
			
		}
        }
        
        
        		
        writer.println("</center>");
        
        
        writer.println("</body>");
        writer.println("</html>");
	}
	
	
	
	
	public void destroy() {
        System.out.println("Servlet is being destroyed");
    }
	
	public void reset() {
		command = "";
	}
	

}
