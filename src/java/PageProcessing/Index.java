/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PageProcessing;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danieldeak
 */
public class Index {

    public Index() {}

    public static HttpServletRequest processIndexRequest(HttpServletRequest request, String contextPath)
    {
        // Default page specific
            request.setAttribute("css", contextPath + "/css/Index.css");

            List<String> jsFiles = new ArrayList<String>();
            jsFiles.add(contextPath + "/js/Index.js");

            request.setAttribute("js", jsFiles);

            return request;
    }

    public static HttpServletResponse processIndexResponse(HttpServletResponse response)
    {
        return response;
    }

}
