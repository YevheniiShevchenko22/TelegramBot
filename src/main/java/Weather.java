import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //146eff8ab606b3b8f242121cf1db032e

    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=146eff8ab606b3b8f242121cf1db032e");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        model.setPressure(main.getDouble("pressure"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon(obj.get("icon").toString());
            model.setMain(obj.get("main").toString());
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + " C" + "\n" +
                "Humidity: " + model.getHumidity() + " %" + "\n" +
                "Pressure: " + model.getPressure() + " mbar" + "\n" +
                "https://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }

}
