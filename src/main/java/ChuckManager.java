import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class ChuckManager {




    public static void joke()
    {
        int maxlen = 0;
        String maxJoke = null;

        int minlen = 0;
        String minJoke = null;

        String rawResponse = null;

        try{
            /*System.out.println(getJokeOnly( getResponse("https://api.chucknorris.io/jokes/random")));*/
            rawResponse =  getResponse("https://api.chucknorris.io/jokes/search?query=cheese");


        }catch (Exception e){
            e.printStackTrace();
        }

        List<NorrisJoke> list = getJokeList(rawResponse);
        System.out.println(list.get(0).getValue());



        for(NorrisJoke nor : list)
        {
            if (nor.getValue().length() > maxlen) {
                maxlen = nor.getValue().length();
                maxJoke = nor.getValue();
            };

            if (nor.getValue().length() < maxlen) {
                minlen = nor.getValue().length();
                minJoke = nor.getValue();
            };
        }

        System.out.println();
        System.out.println("Najdłuższy kawał o serze z Chuckiem Norrisem to: ");
        System.out.println(maxJoke);

        System.out.println();
        System.out.println("Najkrutszy kawał o serze z Chuckiem Norrisem to: ");
        System.out.println(minJoke);


    }





    private static String getResponse(String urlQueryString) throws Exception{
        URL url = new URL(urlQueryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76");
        connection.connect();
        InputStream inStream = connection.getInputStream();
        return new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
    }

    private static String getJokeOnly(String rawJSON) {
        Gson gson = new Gson();
        NorrisJoke norrisJoke = gson.fromJson(rawJSON, NorrisJoke.class);
        return norrisJoke.getValue();
    }

    private static List<NorrisJoke> getJokeList(String rawJson){
        Gson gson = new Gson();
        NorrisJokeList norrisJokeList = gson.fromJson(rawJson, NorrisJokeList.class);
        return norrisJokeList.getResult();
    }

}
