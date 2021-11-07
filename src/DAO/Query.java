package DAO;

import java.sql.ResultSet;
import java.sql.Statement;

import static DAO.JDBC.connection;

/**
 *This class is a helper class that helps make efficient database SQL commands by executing queries, updating them.
 */

public class Query {

    private static ResultSet result;

        public static void makeQuery(String query){
            try{
                Statement stmt = connection.createStatement();
                if(query.toLowerCase().startsWith("select"))
                    result= stmt.executeQuery(query);
                if(query.toLowerCase().startsWith("delete")|| query.toLowerCase().startsWith("insert")|| query.toLowerCase().startsWith("update"))
                    stmt.executeUpdate(query);

            }
            catch(Exception ex){
                System.out.println("Error: "+ex.getMessage());
            }
        }
        public static ResultSet getResult(){
            return result;
        }
    }

