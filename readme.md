### Online Learning References:
[![SC2 Video](https://img.youtube.com/vi/i5OVcTdt_OU/0.jpg)](https://www.youtube.com/watch?v=i5OVcTdt_OU&t=611s)
[![SC2 Video](https://img.youtube.com/vi/u_InEBgRVcc/0.jpg)](https://www.youtube.com/watch?v=u_InEBgRVcc)
[![SC2 Video](https://img.youtube.com/vi/T4Df5_cojAs/0.jpg)](https://www.youtube.com/watch?v=T4Df5_cojAs&t=418s)

	https://www.codejava.net/java-se/networking/java-udp-client-server-program-example
	https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
	https://www.codejava.net/java-se/networking/java-urlconnection-and-httpurlconnection-examples
	https://www.codejava.net/java-se/networking/how-to-use-java-urlconnection-and-httpurlconnection

## Server Request/Response Model:
	1. Single Thread 
	2. Multiple Thread

## Algorithm: Server Request/Response Handling
	1. Start Server on system port.
	2. Accept Client Socket Connection.
	3. Read Client-Socket-InputStream and Transform into Request Object.
	4. Find Request Handler and Invoke Business Logic.
	5. Get Response Object and Write on Client-Socket-OutputStream.


### Tomcat Technology:
	Server:
		- port
		- address
		- Catalina
		- Service
		- Connector[]
		- ScheduledExecutorService
	LifecycleListener:
	NamingResourcesImpl:
	StandardEngine:
		- RealmBase -> UserDatabaseRealm
						- UserDatabase (tomcat-users.xml)
	StandardContext:
	Connector:
		- ProtocolHandler
		- SSLHostConfig
		- Adapter
		- Charset
		- Service
	ProtocolHandler: -> org.apache.coyote.http11.Http11NioProtocol;org.apache.coyote.ajp.AjpNioProtocol;


The valid state transitions for components that support Lifecycleare: 
```
            start()
  -----------------------------
  |                           |
  | init()                    |
 NEW -»-- INITIALIZING        |
 | |           |              |     ------------------«-----------------------
 | |           |auto          |     |                                        |
 | |          \|/    start() \|/   \|/     auto          auto         stop() |
 | |      INITIALIZED --»-- STARTING_PREP --»- STARTING --»- STARTED --»---  |
 | |         |                                                            |  |
 | |destroy()|                                                            |  |
 | --»-----«--    ------------------------«--------------------------------  ^
 |     |          |                                                          |
 |     |         \|/          auto                 auto              start() |
 |     |     STOPPING_PREP ----»---- STOPPING ------»----- STOPPED -----»-----
 |    \|/                               ^                     |  ^
 |     |               stop()           |                     |  |
 |     |       --------------------------                     |  |
 |     |       |                                              |  |
 |     |       |    destroy()                       destroy() |  |
 |     |    FAILED ----»------ DESTROYING ---«-----------------  |
 |     |                        ^     |                          |
 |     |     destroy()          |     |auto                      |
 |     --------»-----------------    \|/                         |
 |                                 DESTROYED                     |
 |                                                               |
 |                            stop()                             |
 ----»-----------------------------»------------------------------
```

