package rusketh.Milk.Event.Listeners;

import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.WeatherListener;

import rusketh.Milk.MilkBukkit;

public class MilkWeatherListener extends WeatherListener {

	public MilkBukkit milkBukkit;
	
	public MilkWeatherListener(MilkBukkit Milk) {
		milkBukkit = Milk;
	}

    public void onWeatherChange(WeatherChangeEvent event) {
    	milkBukkit.CallEvent("WEATHER_CHANGE", event);
    }

    public void onThunderChange(ThunderChangeEvent event) {
    	milkBukkit.CallEvent("THUNDER_CHANGE", event);
    }

    public void onLightningStrike(LightningStrikeEvent event) {
    	milkBukkit.CallEvent("LIGHTNING_STRIKE", event);
    }
}