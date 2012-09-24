/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PageProcessing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.DBConnManager;

/**
 *
 * @author danieldeak
 */
public class Interest {

    public Interest() {}

    public static void saveInterests(HttpServletRequest request)
    {
        Connection newConn = DBConnManager.getConnection();
            HttpSession session = request.getSession();

            String userName = session.getAttribute("user").toString();

            Statement stmt = null;
            ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserIDFromUsername('"+userName+"')"); // call stored procedure
                rs.next();
                int id =  Integer.parseInt(rs.getObject(1).toString());

                CallableStatement cs = null;

                //Create an instance of the CallableStatement
                cs = newConn.prepareCall("CALL shamo.updateInterest('"+id+"', '"+request.getParameter("saveText")+"')");

                //Call the inherited PreparedStatement.executeUpdate() method
                cs.executeUpdate();

                //rs.next(); // step into the first row returned ( there should only be one, usernames are unique )

                //out.println(rs.getObject(1).toString());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            /* CLOSE CONN */
            try
            {
                newConn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
    }

    public static String loadInterests(HttpServletRequest request)
    {
        Connection newConn = DBConnManager.getConnection();
        HttpSession session = request.getSession();

        String userName = session.getAttribute("user").toString();

        String rtnInterests = "";

            Statement stmt = null;
            ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserIDFromUsername('"+userName+"')"); // call stored procedure
                rs.next();
                int id =  Integer.parseInt(rs.getObject(1).toString());

                rs = stmt.executeQuery("SELECT INTERESTS from shamo.cv WHERE OWNER_ID = '"+id+"'");

                rs.next();

                rtnInterests = rs.getObject(1).toString();

                // END TRY
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            /* CLOSE CONN */
            try
            {
                newConn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return rtnInterests;
    }

}
