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
public class Work {

    public Work() {}

    public static String loadWork(HttpServletRequest request)
    {
        Connection newConn = DBConnManager.getConnection();
        HttpSession session = request.getSession();

        String userName = session.getAttribute("user").toString();

        String rtnWork = "";

            Statement stmt = null;
            ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserIDFromUsername('"+userName+"')"); // call stored procedure
                rs.next();
                int id =  Integer.parseInt(rs.getObject(1).toString());

                rs = stmt.executeQuery("SELECT JOBEXPERIENCE from shamo.cv WHERE OWNER_ID = '"+id+"'");

                rs.next();

                rtnWork = rs.getObject(1).toString();

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

            return rtnWork;
    }

    public static void saveWork(HttpServletRequest request)
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

                cs = newConn.prepareCall("CALL shamo.updateExperience('"+id+"', '"+request.getParameter("saveText")+"')");

                cs.executeUpdate();

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

}
