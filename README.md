Tantalum
========

Tantalum is an open source, Java based web application builder that allows business analysts to rapidly create and deploy fully functional enterprise web applications.

Repository
----------
Currently this project's source code is being hosted at http://github.com/trevorallred/Tantalum

Contributing
------------
Want to contribute? Contact Trevor Allred for details.

License
-------
Tantalum is license under the Eclipse Public License (EPL). In short, the EPL is a business-friendly free software license. You can use, modify, copy and distribute the work and modified versions, however, any changes should be released.

Installation
------------
Add this to your local tomcat server.xml file

	<Context docBase="Tantalum" path="/Tantalum" reloadable="true"
		source="org.eclipse.jst.jee.server:Tantalum">
		<Resource name="jdbc/tantalumDB" auth="Container" type="javax.sql.DataSource"
			maxActive="60" maxIdle="30" maxWait="10000" removeAbandoned="true"
			removeAbandonedTimeout="20" driverClassName="com.mysql.jdbc.Driver"
			logAbandoned="true" username="root" password=""
			url="jdbc:mysql://localhost:3306/tantalum_dict" />
	</Context>

