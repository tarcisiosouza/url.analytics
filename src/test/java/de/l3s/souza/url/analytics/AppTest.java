package de.l3s.souza.url.analytics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws Exception 
     */
    public void testApp() throws Exception
    {
    	
    	String url = "http://www.spiegel.de/politik/deutschland/news-christian-lindner-angela-merkel-jamaika-frank-walter-steinmeier-a-1179461.html";
    
        UrlProcessing test = new UrlProcessing ();
        test.findEntities(url);
       
        ArrayList<Entity> results = new ArrayList<Entity>();  
        results = test.getEntities();
     
        for (int i=0;i<results.size();i++)
        {
        	System.out.print ("Entity: "+ results.get(i).getText());
        	System.out.print (" Type: "+ results.get(i).getType() + "\n");
        }
    }
}
