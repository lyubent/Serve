/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author danieldeak
 */
public class HeaderAssembler {
    
    private String rtnHTMLHeader;

    public HeaderAssembler()
    {
        rtnHTMLHeader = "<html> \n <head>\n";
    }

    public void addJavaScriptFile(String fileName)
    {
        rtnHTMLHeader += "<script type='text/javaScript' src='js/"+fileName+".js' />\n";
    }

    public void addCSSFile(String filePath)
    {
        rtnHTMLHeader += "<link rel='stylesheet' type='text/css' href='"+filePath+".css' />\n";
    }

    public String returnHeader()
    {
        rtnHTMLHeader += "</head> \n <body>";

        return rtnHTMLHeader;
    }
}
