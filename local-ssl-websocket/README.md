# how to create keystore locally
keytool -genkeypair -alias mascix -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore mascix.p12 -validity 3650

# how to start locally
```shell
./gradlew bootRun
```
then go to https://localhost:8080/ accept the self signed certificate.
click connect button first, then write name in the text box and click on send button 
which will send the message to all connected clients.