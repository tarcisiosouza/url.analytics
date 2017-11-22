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
String url = "http://www.spiegel.de/politik/deutschland/news-christian-lindner-angela-merkel-jamaika-frank-walter-steinmeier-a-1179461.html";
    
UrlProcessing test = new UrlProcessing ();
test.findEntities(url);
       
//Arraylist containing Entity objects with the Text and Type of Entity (Person, Location, Organization)
ArrayList<Entity> results = new ArrayList<Entity>();  
results = test.getEntities();
     
for (int i=0;i<results.size();i++)
{
   System.out.print ("Entity: "+ results.get(i).getText());
   System.out.print (" Type: "+ results.get(i).getType() + "\n");
}
```
