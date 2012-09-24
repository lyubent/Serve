/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PageProcessing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBConnManager;

/**
 *
 * @author danieldeak
 */
public class CV {

    public CV() {}

    public static HttpServletRequest processCVRequest(HttpServletRequest rtnRequest, String contextPath)
    {
            /* CSS AND JAVASCRIPT FILES */

            // This is likely not needed. Since we are using jsp, we can attach the js files each page needs directly.
            rtnRequest.setAttribute("css", contextPath + "/css/CV.css");

            List<String> jsFiles = new ArrayList<String>();
            jsFiles.add(contextPath + "/js/CV.js");

            rtnRequest.setAttribute("js", jsFiles);

            HttpSession session = rtnRequest.getSession();
            String userName = session.getAttribute("user").toString();

            /** END FILES **/

            Connection newConn = DBConnManager.getConnection();
            Statement stmt = null;
            ResultSet rs = null;

            try
            {
                stmt = newConn.createStatement();
                rs = stmt.executeQuery("CALL shamo.getUserInfo('"+userName+"')"); // call stored procedure

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

    public static HttpServletResponse processCVResponse (HttpServletResponse response)
    {
        return response;
    }

}
