##Search aggregator
Alice is your colleague in the marketing team. She is not satisfied with using any one search engine for her research.
She usually tries the same search phrase on multiple search engines and the process is tedious for her. 
You insist on automating this manual process and make life easier for her. i.e you decide to build a service that 
aggregates search results from multiple search services. She wants to have different strategies to combine the results, 
and the ability to add more ways later.

Use at least two search services (Google and Bing are probably good choices). 
Implement two different ways of sorting the aggregate result set. 
For the first strategy, a simple round robin on the search engines result sets would suffice. 
For an additional strategy, use your imagination.

Target any platform you are comfortable with. Java or Scala is preferable as that's that we use here. 
If you plan to use any other language, you will have to port the base code as appropriate. 
Focus should be on demonstrating your ability to write quality software (readability, extensibilty, modurality, testability etc). 
Your thought process, design decisions and priorities should be clear when we look at the code.

The deliverable should be some form of a language api (An interface or a function etc) that takes at least the following as input
1. The search phrase
2. List of search engines
3. Results aggregation strategy

Please add any other additional optional parameters as suitable. 
The goal is to produce a library/sdk that can be wrapped to create a http web service at a later point.

###Launching
Program runs from IDE or with Maven.

To run program from IDE you need to run main method in Aggregator class.

To run with Maven enter next commands in app directory:

'mvn install'
'mvn exec:java -Dexec.mainClass="com.mzherdev.searchaggregator.Aggregator"'
