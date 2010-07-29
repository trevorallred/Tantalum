Tantalum
========

Tantalum is an open source, Java based web application builder that allows business analysts to rapidly create and deploy fully functional enterprise web applications.

It is inspired by the work done by [TenFold Corporation](http://www.tenfold.com/) in the 1990s and Microsoft Access. Think of Tantalum as a web-based Microsoft Access on steroids.

Contributing
------------
Our biggest need right now is a public server we can use to host the applications development effort. If you have a server we can use, then please contact Trevor Allred.

There are two areas of work that need to be done.
- Tantalum Core - Trevor is looking for Java coders, experts in jQuery, and graphic designers to help build war file.
- Tantalum Tools - John Davenport and John Weymouth are leading the effort in building the new "TenFold Tools" for Tantalum.

License
-------
Tantalum is license under the Eclipse Public License (EPL). In short, the EPL is a business-friendly free software license. You can use, modify, copy and distribute the work and modified versions, however, any changes should be released.

Installation
------------
1. Download source from GitHub
2. Create and load dictionary using install.sql
3. Deploy war to your tomcat server
4. Add this to your local tomcat server.xml file


	<Context docBase="Tantalum" path="/Tantalum" reloadable="true"
		source="org.eclipse.jst.jee.server:Tantalum">
		<Resource name="jdbc/tantalumDB" auth="Container" type="javax.sql.DataSource"
			maxActive="60" maxIdle="30" maxWait="10000" removeAbandoned="true"
			removeAbandonedTimeout="20" driverClassName="com.mysql.jdbc.Driver"
			logAbandoned="true" username="root" password=""
			url="jdbc:mysql://localhost:3306/tantalum_meta" />
	</Context>

Repository
----------
Currently this project's source code is being hosted at http://github.com/trevorallred/Tantalum

