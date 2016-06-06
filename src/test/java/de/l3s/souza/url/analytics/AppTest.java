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
        test.findEntities("http://edition.cnn.com/2016/04/24/politics/michael_jackson/angela_merkel/barack_obama/dilma_rousseff");
        System.out.println(test.getAnnotations());
        System.out.println(test.getAnnotationType());
    
    }
}
