/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.webapplicationservlet.main;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author fabix
 */
@ServerEndpoint("/endpoint")
public class WSEndpoint {

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

}
