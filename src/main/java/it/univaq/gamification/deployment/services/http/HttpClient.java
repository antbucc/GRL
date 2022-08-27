package it.univaq.gamification.deployment.services.http;


import okhttp3.OkHttpClient;

public class HttpClient {

    private static OkHttpClient client;

    private HttpClient() {
        client = new OkHttpClient();
    }

    public static OkHttpClient getInstance() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    public void closeConnections() {
        client.connectionPool().evictAll();
    }

}
