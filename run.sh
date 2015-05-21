#!/bin/bash
echo "start server on 9090" 
mvn -Djetty.port=9090 jetty:run -Dserver=http://localhost:9090 -Dme=http://localhost:9090 > server.txt &
echo "start client 9091"
mvn -Djetty.port=9091 jetty:run -Dserver=http://localhost:9090 -Dme=http://localhost:9091 > client1.txt & 
echo "start client 9092"
mvn -Djetty.port=9091 jetty:run -Dserver=http://localhost:9090 -Dme=http://localhost:9092 > client2.txt &
