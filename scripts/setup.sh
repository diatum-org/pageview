sudo apt-get install tomcat9

mysql -ughost -phost_password ghost < pageview.sql

sudo sed -i '480i <filter><filter-name>CorsFilter</filter-name><filter-class>org.apache.catalina.filters.CorsFilter</filter-class><init-param><param-name>cors.allowed.headers</param-name><param-value>Accept,Accept-Encoding,Accept-Language,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,Connection,Content-Type,Host,Origin,Referer,Token-Id,User-Agent, X-Requested-With</param-value></init-param><init-param><param-name>cors.allowed.origins</param-name><param-value>*</param-value></init-param><init-param><param-name>cors.allowed.methods</param-name><param-value>GET, POST, PUT, DELETE, OPTIONS, HEAD</param-value></init-param></filter><filter-mapping><filter-name>CorsFilter</filter-name><url-pattern>/*</url-pattern></filter-mapping>' /var/lib/tomcat9/conf/web.xml

sudo sed -i "87i <Connector port=\"8443\" protocol=\"org.apache.coyote.http11.Http11NioProtocol\" maxThreads=\"150\" SSLEnabled=\"true\"><SSLHostConfig><Certificate certificateFile=\"conf/ghost.cer\" certificateKeyFile=\"conf/ghost.key\" certificateChainFile=\"conf/chain.cer\" /></SSLHostConfig></Connector>" /var/lib/tomcat9/conf/server.xml

echo "NAME=`/etc/letsencrypt/acme.sh --list | tail -n +2 | awk '{print $1;}'`\ncp /etc/letsencrypt/${NAME}/${NAME}.cer /var/lib/tomcat9/conf/ghost.cer\ncp /etc/letsencrypt/${NAME}/${NAME}.key /var/lib/tomcat9/conf/ghost.key\ncp /etc/letsencrypt/${NAME}/${NAME}.key /var/lib/tomcat9/conf/chain.cer\n" > renew_tomcat.sh
chmod +x renew_tomcat.sh
sudo mv renew_tomcat.sh /etc/letsencrypt/renewal-hooks/post/

sudo cp page.war /var/lib/tomcat9/webapps/
sudo service tomcat9 restart

