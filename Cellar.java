package proairetikh_ergasia4;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Cellar {
	
	static final String JDBC_SERVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/cellardb?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
	
	static final String USER = "super_server";
	static final String PASS = "parabelos@444";
	
	static void ToString(String pId, String color, double price, String origin, double alcohol, int quantity, String subcat) {
		
		if(subcat.equals("")) {
			System.out.println("\n---PRODUCT_ID: "+pId+" - COLOR: "+color+" - PRICE: "+price+"\u20ac - ORIGIN: "+origin+" - ALCOHOL_LEVEL: "+alcohol+" - QUANTITY: "+quantity);
		} else {
			System.out.println("\n---PRODUCT_ID: "+pId+" - COLOR: "+color+" - PRICE: "+price+"\u20ac - ORIGIN: "+origin+" - ALCOHOL_LEVEL: "+alcohol+" - QUANTITY: "+quantity+" - WINE_SUBCATEGORY: "+subcat);
		}
		
	}
	
	static void compareAlcoholLevels(HashMap<String, Double> max_alc) {
		
		String max_drink = null;
		double max_alco = 0;
		
		for(String i : max_alc.keySet()) {
	    	
	    	if(max_alc.get(i) > max_alco) {
	    		max_alco = max_alc.get(i);
	    		max_drink = i;
	    	}
	    	
	    }
		
		System.out.println("\nThe drink with the highest alcohol level of " + max_alco + "% is the product with ID " + max_drink);
		
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		
		Connection conn = null;
		PreparedStatement ps;
		ResultSet rs;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		System.out.println("- Connecting to database...\n");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
		/*System.out.println("- Creating database cellarDB...\n");			//CREATING DATABASE
		ps = conn.prepareStatement("CREATE DATABASE cellarDB");
		ps.executeUpdate();*/
		
		/*System.out.println("- Creating tables Beers and Wines...\n");		//CREATING TABLES
		ps = conn.prepareStatement("CREATE TABLE Beers (productId VARCHAR(10), color VARCHAR(10), price FLOAT, origin VARCHAR(50), alcohol FLOAT, quantity INT)");
		ps.executeUpdate();
		ps = conn.prepareStatement("CREATE TABLE Wines (productId VARCHAR(10), color VARCHAR(10), price FLOAT, origin VARCHAR(50), alcohol FLOAT, quantity INT, subcategory VARCHAR(1))");
		ps.executeUpdate();
			
		System.out.println("- Inserting data...\n");							//INSERTING DATA
	    ps = conn.prepareStatement("INSERT INTO Beers VALUES ('A00', 'blonde', 3.0, 'Greece', 5.0, 50)");
	    ps.executeUpdate();
	    ps = conn.prepareStatement("INSERT INTO Beers VALUES ('W01', 'weiss', 5.0, 'Germany', 7.5, 30)");
	    ps.executeUpdate();
	    ps = conn.prepareStatement("INSERT INTO Wines VALUES ('A000', 'leuko', 18.0, 'Greece', 13.0, 30, 'X')");
	    ps.executeUpdate();
	    ps = conn.prepareStatement("INSERT INTO Wines VALUES ('K001', 'roze', 16.0, 'Greece', 12.5, 40, 'H')");
	    ps.executeUpdate();*/
		
	    /*System.out.println("- Printing data from Beers table unorganised...\n");			//PRINTING UNORGANISED INFO
	    ps = conn.prepareStatement("SELECT * FROM Beers");
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	String color = rs.getString("color");
	    	double price = rs.getDouble("price");
	    	String origin = rs.getString("origin");
	    	double alcohol = rs.getDouble("alcohol");
	    	int quantity = rs.getInt("quantity");
	    	
	    	System.out.println(".."+productId+".."+color+".."+price+".."+origin+".."+alcohol+".."+quantity);
	    	
	    }
	    
	    System.out.println("\n- Printing data from Wines table unorganised...\n");
	    ps = conn.prepareStatement("SELECT * FROM Wines");
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	String color = rs.getString("color");
	    	double price = rs.getDouble("price");
	    	String origin = rs.getString("origin");
	    	double alcohol = rs.getDouble("alcohol");
	    	int quantity = rs.getInt("quantity");
	    	String subcat = rs.getString("subcategory");
	    	
	    	System.out.println(".."+productId+".."+color+".."+price+".."+origin+".."+alcohol+".."+quantity+".."+subcat);
	    	
	    }
	    
	    System.out.println("\n- Printing data from Beers table organised...");			//PRINTING ORGANISED INFO WITH TOSTRING METHOD
	    ps = conn.prepareStatement("SELECT * FROM Beers");	
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	String color = rs.getString("color");
	    	double price = rs.getDouble("price");
	    	String origin = rs.getString("origin");
	    	double alcohol = rs.getDouble("alcohol");
	    	int quantity = rs.getInt("quantity");
	    	String subcat = "";
	    	
	    	ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    	
	    }
	    
	    System.out.println("\n- Printing data from Wines table organised...");
	    ps = conn.prepareStatement("SELECT * FROM Wines");
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	String color = rs.getString("color");
	    	double price = rs.getDouble("price");
	    	String origin = rs.getString("origin");
	    	double alcohol = rs.getDouble("alcohol");
	    	int quantity = rs.getInt("quantity");
	    	String subcat = rs.getString("subcategory");
	    	
	    	ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    	
	    }
	    
	    System.out.println("\n- Printing drink with the highest alcohol level...");			//CREATING HASHMAP TABLE WITH PID AND ALCOHOL LEVELS
	    HashMap<String, Double> max_alc = new HashMap<String, Double>();					//AND USING METHOD COMPAREALCOHOLLEVELS FINDING MAX AND PRINTING IT
	    
	    ps = conn.prepareStatement("SELECT productId, alcohol FROM Beers");
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	double alcohol = rs.getDouble("alcohol");
	    	
	    	max_alc.put(productId, alcohol);
	    	
	    }
	    
	    ps = conn.prepareStatement("SELECT productId, alcohol FROM Wines");
	    rs = ps.executeQuery();
	    
	    while(rs.next()) {
	    	
	    	String productId = rs.getString("productId");
	    	double alcohol = rs.getDouble("alcohol");
	    	
	    	max_alc.put(productId, alcohol);
	    	
	    }
	    
	    compareAlcoholLevels(max_alc);*/
		
		Scanner sc = new Scanner(System.in);
	    
	    System.out.println("\nAvailable choices:\n\n- Add Beer\n- Remove Beer\n- Add Wine\n- Remove Beer\n- Show All\n- Search Beers\n- Search Wines\n- Alcohol Beers\n- Alcohol Wines\n- Alcohol All\n- Exit\n");
	    String choice = sc.nextLine();
	    
	    while(true) {
	    	
	    	if(choice.equals("Add Beer")) {				//ADD BEER CHOICE
	    		
	    		System.out.println("\nInsert the productId of the Beer");
	    		String pId = sc.nextLine();
	    		System.out.println("\nInsert the color of the Beer(weiss, blonde, black, red)");
	    		String color = sc.nextLine();
	    		System.out.println("\nInsert the price of the Beer");
	    		double price = sc.nextDouble();
	    		System.out.println("\nInsert the origin of the Beer");
	    		String origin = sc.nextLine();
	    		System.out.println("\nInsert the alcohol level of the Beer");
	    		double alc_level = sc.nextDouble();
	    		System.out.println("\nInsert the number of Beers in the cellar");
	    		int quan = sc.nextInt();
	    		
	    		ps = conn.prepareStatement("INSERT INTO Beers (productId, color, price, origin, alcohol, quantity) VALUES (?,?,?,?,?,?)");
	    		
	    		ps.setString(1, pId);
	    		ps.setString(2, color);
	    		ps.setDouble(3, price);
	    		ps.setString(4, origin);
	    		ps.setDouble(5, alc_level);
	    		ps.setInt(6, quan);
	    		
	    	    ps.executeUpdate();
	    	    
	    	    System.out.println("\nBeer inserted successfully...");
	    	    
	    	} else if(choice.equals("Remove Beer")) {			//REMOVE BEER CHOICE
	    		
	    		System.out.println("\nInsert the productId of the Beer you want to delete");
	    		String pId = sc.nextLine();
	    		
	    		ps = conn.prepareStatement("DELETE FROM Beers WHERE productId=?");
	    		
	    		ps.setString(1, pId);
	    		ps.executeUpdate();
	    		
	    		System.out.println("\nBeer deleted successfully...");
	    		
	    	} else if(choice.equals("Add Wine")) {				//ADD WINE CHOICE
	    		
	    		System.out.println("\nInsert the productId of the Wine");
	    		String pId = sc.nextLine();
	    		System.out.println("\nInsert the color of the Wine(leuko, kokkino, roze)");
	    		String color = sc.nextLine();
	    		System.out.println("\nInsert the price of the Wine");
	    		double price = sc.nextDouble();
	    		System.out.println("\nInsert the origin of the Wine");
	    		String origin = sc.nextLine();
	    		System.out.println("\nInsert the alcohol level of the Wine");
	    		double alc_level = sc.nextDouble();
	    		System.out.println("\nInsert the number of Wines in the cellar");
	    		int quan = sc.nextInt();
	    		System.out.println("\nInsert the subcategory of the Wine(G for sweet, H for semisweet, X for dry)");
	    		String subcat = sc.nextLine();
	    		
	    		ps = conn.prepareStatement("INSERT INTO Beers (productId, color, price, origin, alcohol, quantity, subcategory) VALUES (?,?,?,?,?,?,?)");
	    		
	    		ps.setString(1, pId);
	    		ps.setString(2, color);
	    		ps.setDouble(3, price);
	    		ps.setString(4, origin);
	    		ps.setDouble(5, alc_level);
	    		ps.setInt(6, quan);
	    		ps.setString(7, subcat);
	    		
	    	    ps.executeUpdate();
	    	    
	    	    System.out.println("\nWine inserted successfully...");
	    		
	    	} else if(choice.equals("Remove Wine")) {			//REMOVE WINE CHOICE
	    		
	    		System.out.println("\nInsert the productId of the Wine you want to delete");
	    		String pId = sc.nextLine();
	    		
	    		ps = conn.prepareStatement("DELETE FROM Wines WHERE productId=?");
	    		
	    		ps.setString(1, pId);
	    		ps.executeUpdate();
	    		
	    		System.out.println("\nWine deleted successfully...");
	    		
	    	} else if(choice.equals("Show All")) {				//SHOW ALL DRINKS CHOICE
	    		
	    		System.out.println("\nBeers:");
	    		ps = conn.prepareStatement("SELECT * FROM Beers");
	    		rs = ps.executeQuery();
	    		    
	    		while(rs.next()) {
	    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = "";
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    		System.out.println("\nWines:");
	    		ps = conn.prepareStatement("SELECT * FROM Wines");
	    		rs = ps.executeQuery();
	    		    
	    		while(rs.next()) {
	    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = rs.getString("subcategory");
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Search Beers")) {				//SEARCH FOR SPECIFIC BEERS CHOICE
	    		
	    		System.out.println("\nPlease insert the color of the beer");
	    		String colour = sc.nextLine();
	    		System.out.println("\nPlease insert the origin of the beer");
	    		String orig = sc.nextLine();
	    		System.out.println("\nThe beers matching your choices are:");
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Beers WHERE color=? AND origin=?");
	    		ps.setString(1, colour);
	    		ps.setString(2, orig);
	    		rs = ps.executeQuery();
	    		    
	    		while(rs.next()) {
	    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = "";
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Search Wines")) {				//SEARCH FOR SPECIFIC WINES CHOICE
	    		
	    		System.out.println("\nPlease insert the subcategory(H, X, G) of the wine");
	    		String sub_cat = sc.nextLine();
	    		System.out.println("\nPlease insert the origin of the wine");
	    		String orig = sc.nextLine();
	    		System.out.println("\nThe wines matching your choices are:");
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Wines WHERE subcategory=? AND origin=?");
	    		ps.setString(1, sub_cat);
	    		ps.setString(2, orig);
	    		rs = ps.executeQuery();
	    		    
	    		while(rs.next()) {
	    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = rs.getString("subcategory");
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Alcohol Beers")) {				//ORDER AND PRINT BEERS BY ALCOHOL LEVELS CHOICE
	    		
	    		System.out.println("\nOrdering beers by alcohol levels in ascending order...");
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Beers ORDER BY alcohol");
	    		rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = "";
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Alcohol Wines")) {				//ORDER AND PRINT WINES BY ALCOHOL LEVELS CHOICE
	    		
	    		System.out.println("\nOrdering wines by alcohol levels in ascending order...");
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Wines ORDER BY alcohol");
	    		rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = rs.getString("subcategory");
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Alcohol All")) {				//PRINT ALL DRINKS WITH ALCOHOL LEVEL ABOVE 5 CHOICE
	    		
	    		System.out.println("\nPrinting drinks that have alcohol level above 5...");
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Beers WHERE alcohol > 5");
	    		rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = "";
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    		ps = conn.prepareStatement("SELECT * FROM Wines WHERE alcohol > 5");
	    		rs = ps.executeQuery();
	    		
	    		while(rs.next()) {
    		    	
	    			String productId = rs.getString("productId");
	    		    String color = rs.getString("color");
	    		    double price = rs.getDouble("price");
	    		    String origin = rs.getString("origin");
	    		    double alcohol = rs.getDouble("alcohol");
	    		    int quantity = rs.getInt("quantity");
	    		    String subcat = rs.getString("subcategory");
	    		    	
	    		    ToString(productId,color,price,origin,alcohol,quantity,subcat);
	    		    	
	    		}
	    		
	    	} else if(choice.equals("Exit")) {					//EXIT PROGRAM
	    		
	    		break;
	    		
	    	} else {											//WRONG CHOICE
	    		
	    		System.out.println("\nWrong choice. Try again");
	    		
	    	}
	    	
	    	System.out.println("\nAvailable choices:\n\n- Add Beer\n- Remove Beer\n- Add Wine\n- Remove Beer\n- Show All\n- Search Beers\n- Search Wines\n- Alcohol Beers\n- Alcohol Wines\n- Alcohol All\n- Exit\n");
		    choice = sc.nextLine();
	    	
	    }
	    
	}
	
}
