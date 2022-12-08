package org.example;

import org.springframework.stereotype.Service;

@Service
public class LoginSender {
    public boolean login(String server, String login, String password, String build, String webManager){
        Connector rest_client = new Connector();
        rest_client.initConnection(server);
        return rest_client.sendAuth(login, password, build, webManager);
    }
}
