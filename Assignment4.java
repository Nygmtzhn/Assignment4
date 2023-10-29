import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface WeatherObserver {
    void update(String location, String weatherData);
}

// Concrete Observer
class Location implements WeatherObserver {
    private String location;

    public Location(String location) {
        this.location = location;
    }

    @Override
    public void update(String location, String weatherData) {
        if (location.equals(this.location)) {
            System.out.println(this.location + " - Weather updated: " + weatherData);
        }
    }
}

// Subject Interface
interface WeatherProvider {
    void registerObserver(WeatherObserver observer);

    void removeObserver(WeatherObserver observer);

    void notifyObservers();

    String getWeatherData();
}

// Concrete Subject
class OpenWeatherMapProvider implements WeatherProvider {
    private List<WeatherObserver> observers = new ArrayList<>();
    private String weatherData;
    private String location;

    public OpenWeatherMapProvider(String location) {
        this.location = location;
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(this.location, this.weatherData);
        }
    }

    @Override
    public String getWeatherData() {
        // Simulate fetching weather data from OpenWeatherMap
        // Replace this with actual API call
        this.weatherData = "Temperature: 20°C, Weather: Sunny";
        notifyObservers();
        return this.weatherData;
    }
}

public class WeatherDashboard {
    public static void main(String[] args) {
        // Create weather providers using the Factory pattern
        WeatherProvider openWeatherMapProvider1 = new OpenWeatherMapProvider("New York");
        WeatherProvider openWeatherMapProvider2 = new OpenWeatherMapProvider("Los Angeles");

        // Create observer locations
        WeatherObserver location1 = new Location("New York");
        WeatherObserver location2 = new Location("Los Angeles");

        // Register observers to providers
        openWeatherMapProvider1.registerObserver(location1);
        openWeatherMapProvider2.registerObserver(location2);

        // Simulate weather updates
        openWeatherMapProvider1.getWeatherData();
        openWeatherMapProvider2.getWeatherData();
    }
}
