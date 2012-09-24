package util;

//@author lyubentodorov

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletContext;

public class HTMLRdr
{
    //requires the html string and the servlate contest (usage example below)
    // String temp = HTMLReader.readFile("/index.html", getServletContext());
    public static String readFile(String HTMLDir, ServletContext context ) throws IOException
    {
        try
        {
            String retFile = "", data = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream(HTMLDir)));

            while((data = reader.readLine()) != null)
                retFile += data;

            return retFile;
        }
        catch(Exception ex){ex.printStackTrace();}

        return "";
    }
}