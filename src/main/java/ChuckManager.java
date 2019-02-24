import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class ChuckManager {


    public static void joke()
    {
        try{
            System.out.println(getJokeOnly( getResponse("https://api.chucknorris.io/jokes/random")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getResponse(String urlQueryString) throws Exception{
        URL url = new URL(urlQueryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76");
        connection.connect();
        InputStream inStream = connection.getInputStream();
        return new Scanner(inStream, "UTF-8").useDelimiter("\\z").next();
    }

    private static String getJokeOnly(String rawJSON) {
        Gson gson = new Gson();
        NorrisJoke norrisJoke = gson.fromJson(rawJSON, NorrisJoke.class);
        return norrisJoke.getValue();
    }

}
