/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PageProcessing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import util.DBConnManager;
import util.HTMLRdr;

/**
 *
 * @author danieldeak
 */
public class CoverLetter {

    public CoverLetter() {}

    public static HttpServletRequest processServletRequest(HttpServletRequest rtnRequest)
    {
        HttpSession session = rtnRequest.getSession();
        String user = "ddeak"; // change when session variable is implemented

        Connection newConn = DBConnManager.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserInfo('"+user+"')"); // call stored procedure

                rs.next(); // step into the first row returned ( there should only be one, usernames are unique )

                rtnRequest.setAttribute("fullName", rs.getObject(1).toString() + " "+ rs.getObject(2).toString());
                rtnRequest.setAttribute("email", rs.getObject(3).toString());
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

            return rtnRequest;
    }

    public static void saveLetter(HttpServletRequest request)
    {
            Connection newConn = DBConnManager.getConnection();
            HttpSession session = request.getSession();

            String userName = session.getAttribute("user").toString(); // Remove and replace with session.getAttribute("username"); when completed

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
                cs = newConn.prepareCall("CALL shamo.updateCoverLetter('"+id+"', '"+request.getParameter("saveText")+"')");

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

    public static String loadLetter(HttpServletRequest request)
    {
        Connection newConn = DBConnManager.getConnection();
            HttpSession session = request.getSession();

            String userName = session.getAttribute("user").toString();

            String rtnCoverLetter = "";

            Statement stmt = null;
            ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserIDFromUsername('"+userName+"')"); // call stored procedure
                rs.next();
                int id =  Integer.parseInt(rs.getObject(1).toString());

                rs = stmt.executeQuery("SELECT COVERLETTER from shamo.cv WHERE OWNER_ID = '"+id+"'");

                rs.next();

                rtnCoverLetter = rs.getObject(1).toString();

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

            return rtnCoverLetter;
    }

    public static String getCoverLetter(ServletContext context)
    {
        try {
            return HTMLRdr.readFile("/jsp/CoverLetter.jsp", context);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
