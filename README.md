URLAnalitcs@L3S
=================

A tool to extract entities from URLs:

1. If you want to add it as a dependency in your maven project:
-----------------------------------

		<dependency>
			<groupId>de.l3s.souza</groupId>
			<artifactId>url.analytics</artifactId>
			<version>1.0-SNAPSHOT</version>
			<scope>compile</scope>
		</dependency>
    
1.1. In the client project
-----------------------------------
```
mvn clean install
```

1.2. In your application folder
-----------------------------------
```
mvn eclipse:eclipse
```
2. Example of using the client in your java code:
-----------------------------------
```
//For every query it returns a HashMap with the Articles and the BM25 scores
UrlProcessing test = new UrlProcessing ();
test.findEntities("http://edition.cnn.com/2016/04/24/politics/michael_jackson/angela_merkel/barack_obama/dilma_rousseff");
System.out.println(test.getAnnotations());
System.out.println(test.getAnnotationType());
```
