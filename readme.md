## requirements:
- java 17
- docker (for dev provile)

## Run with kafka and db (dev profile):
# reconfiure docker-compose.yml :

Windows:
Run in WSL
1. get WSL ip address for example run from terminal ->  
  `hostname -I | cut -d' ' -f1`
2. change the IP section to your host for:
  KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://HOST_IP_THERE:9092
  KAFKA_BOOTSTRAP_SERVERS: HOST_IP_THERE:9092

3. open cmd as administrator and create a proxy -> `netsh interface portproxy add v4tov4 listenport=9092 listenaddress=0.0.0.0 connectport=9092 connectaddress=HOST_IP_THERE`

4. ./run.sh start

Linux:
Should work with localhost for HOST_IP.

## Run on local
1. Start the spring boot for AISCollector and RSEngine with active profile "local". 
-Dspring.active.profile=local


## Endpoint RSE

curl 'http://localhost:8081/api/routes/representative?from=DEHAM&to=DEBRV'