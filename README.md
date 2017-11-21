ElasticSearchClient@L3S
=================

Elastic Search client at L3S for Java applications

1. If you want to add it as a dependency in your maven project:
-----------------------------------

		<dependency>
			<groupId>de.l3s</groupId>
			<artifactId>elasticquery</artifactId>
			<version>0.0.1-SNAPSHOT</version>
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
String query = "Angela Merkel";
int limit = 1000; //total number of documents to retrieve
Map<Article, Double> documents = new HashMap<Article, Double>();
		new ElasticMain(query, limit, "url");
		ElasticMain.run();
		documents = ElasticMain.getResult();
		System.out.println("Total documents: " + documents.size());
		for (Entry<Article, Double> s : documents.entrySet())
			System.out.print(s.getKey().getTimestamp() + " " + s.getKey().getUrl() + " " + s.getKey().getScore() + " \n");

```
2. If you want full-text search:
-----------------------------------
```
Change the 'url' field to 'text'
new ElasticMain(query, limit, "text");
