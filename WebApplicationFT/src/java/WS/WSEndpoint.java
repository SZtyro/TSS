/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author fabix
 */
@ServerEndpoint("/endpoint")
public class WSEndpoint {

    Session session;

    @OnOpen
    public void openSession(Session session) {
        this.session = session;
        Timer timer = new Timer(true);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Gson gsonb = new GsonBuilder().create();

                try {
                    send(gsonb.toJson(randomInts()));
                } catch (IOException ex) {
                    Logger.getLogger(WSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    @OnMessage

    public String onMessage(String message) {

        Gson gsonb = new GsonBuilder().create();

        return gsonb.toJson(randomInts());

    }

    private int[] randomInts() {
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            int n = (int) (Math.random() * 9 + 1);
            array[i] = n;
        }
        return array;
    }

    private void send(String json) throws IOException {

        session.getBasicRemote().sendText(json.toString());

    }
}
