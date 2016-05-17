package de.l3s.souza.url.analytics;

import java.io.IOException;
import java.net.MalformedURLException;

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
        UrlProcessing test = new UrlProcessing ();
        test.findEntities("https://de.wikipedia.org/wiki/Wahl_zum_19._Deutschen_Bundestag");
        System.out.println(test.getAnnotations());
        System.out.println(test.getAnnotationType());
        test.findEntities("https://en.wikipedia.org/wiki/Barack_Obama");
        System.out.println(test.getAnnotations());
        System.out.println(test.getAnnotationType());
    }
}
