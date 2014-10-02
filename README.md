README
========

This is a sample project to demonstrate Scala and Java EE 7.



Requirements
-------------

* Scala 2.11
* GlassFish 4 application server
* Gradle 2.0



Installation
--------------

Download in stall GlassFish 4.0 some where


> unzip  ~/Downloads/glassfish-4.0.1-b05-20140427.zip  -d /Library/opt
  

Configure your IDE to installation directory for GlassFish 4. 


> echo $GLASSFISH_HOME
/Library/opt/glassfish-4.0.1-b05-20140427


Download Gradle 2.0 or on the Mac OS X, install using the GVM tool. With Gradle set up and configured to your PATH
environmment variable, the following commands should work:


> gradle clean

> gradle test

> gradle war

> gradle build





Tested and Verified
---------------------

Tested with WildFly 8.1 *DONE*
Tested with GlassFish 4.0 *DONE*


GlassFish 4.0 JMS Queue
-------------------------

JMS Set up


First, start the application in IDEA or Eclipse that will launch the GlassFish application server. Otherwise, start
GlassFish manually on your workstation. 

Navigate to the GlassFish Adminstration console http://localhost:4848/common/index.jsf

On the left hand pane, of the Admin page select the JMS node, then choose JMS Connection Factory. Create a new
connection factory with the following information:


> JNDI name:          jms/OrderQueueConnectionFactory
> Resource type:      javax.jms.QueueConnectionFactory
> Description:        


Save this new connection factory.


Now select JMS node again and then choose Destination Resources. Create a new JMS destination with the following 
properties: 

> JNDI name:                      jms/OrderQueue
> Physical Destination NAME:      OrderQueue
> Resource type:                  java.jms.Queue
> Description:


Save this new queue.


Redeploy the WAR file again and/or restart the GlassFish server. The application should now complete successfully 
when the customer declares yes on the final wizard step. 






Developing Java EE 7 Applications with Scala (CON2644)
-------------------------------------------------------

*Speakers: Peter Pilgrim*

Title can only be  80 characters long

Abstract: 750 words

Scala is an alternative JVM language with both object-oriented and functional programming paradigms. Scala development with the Java EE 7 platform is definitely possible and can be a pleasant experience. If you have uncertainty about how Scala can fit around the Java EE 7 platform, then this session aims to illustrate the huge benefit that Scala adoption can bring to the platform. Many other developers are taking advantage and the challenge of the JVM’s capability of being a vessel for multi-language programming. You no longer have to write every single project using Java, even if you like Lambdas experiences. 

For the developer and engineering terms that feeling a little braver than usual, Scala is attractive as it is strongly typed and lets you set the gauge on how object oriented or how functional you want to be. You will learn how to reuse the annotations and creating Scala plain object safely and concisely. 

This session will highlight and contrast the experience I had developing Scala solutions with Java EE, and there will be plenty of advice about using the functional programming features against the Java object oriented API. 

* Scala language overview
* Java EE 7 architecture and design
* WildFly 8 application server 
* Using Gradle as a build tool
* How to create beans in Scala with dependency injection
* JAX-RS endpoints
* Servlet Endpoints
* JMS Messaging (not enough time to show in the technical session)
* Scala adoption advice and hints for sustainable team development


Peter Pilgrim
September 2014
JavaOne 


Email: peter.pilgrim@gmail.com
Blog: www.xenonique.co.uk/blog





