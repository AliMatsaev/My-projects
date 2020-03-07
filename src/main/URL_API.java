package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
//Class for connection to free-Api
public class URL_API {
    private String propertytoken = "X-RapidAPI-Key";
    private String token = "dfb1a6b2096c5a900b700b3015f439c5";
    private String adress = "http://v2.api-football.com";

    //Request and response
    public String getData(String link) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(adress + (link.isEmpty() ? "/" : link));
            HttpURLConnection httpurl = (HttpURLConnection)url.openConnection();
            httpurl.setRequestProperty(propertytoken, token);
            Scanner sc = new Scanner(httpurl.getInputStream());
            while (sc.hasNext()) {
                result.append(sc.next());
            }
            sc.close();
            httpurl.disconnect();
        } catch (IOException e) {
            result.delete(0, result.length());
            result.append("{\"error\":404}");
        }
        return result.toString();
    }
}
