package covers1624.jarrm.handler;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

import static covers1624.jarrm.reference.Reference.*;

/**
 * Created by covers1624 on 2/5/2016.
 */
public class ConfigurationHandler {

	// Game Tweeks
	public static boolean spreadMoss;
	public static boolean craftCircle;
	public static boolean unBricks;

	// Encahntments
	public static int disjunctionId;
	public static int vorpalId;

	// World
	public static boolean generateRuby;
	public static boolean generateGreenSapphire;
	public static boolean generateSapphire;
	public static boolean generateSilver;
	public static boolean generateTin;
	public static boolean generateCopper;
	public static boolean generateNikolite;
	public static boolean generateTungsten;

	// Features
	public static boolean splashScreenImplementation;

	public static Configuration configuration;

	public static void init(File config) {
		if (configuration == null) {
			configuration = new Configuration(config);
		}
		loadConfiguration();
	}

	public static void loadConfiguration() {

		//World
		generateRuby = configuration.getBoolean("GenerateRuby", CATEGORY_WORLD, true, "Allows Ruby to spawn in the world.");
		generateGreenSapphire = configuration.getBoolean("GenerateGreenSapphire", CATEGORY_WORLD, true, "Allows Green Sapphire to spawn in the world.");
		generateSapphire = configuration.getBoolean("GenerateSapphire", CATEGORY_WORLD, true, "Allows Sapphire to spawn in the world.");
		generateSilver = configuration.getBoolean("GenerateSilver", CATEGORY_WORLD, true, "Allows Silver to spawn in the world.");
		generateTin = configuration.getBoolean("GenerateTin", CATEGORY_WORLD, true, "Allows Tin to spawn in the world.");
		generateCopper = configuration.getBoolean("GenerateCopper", CATEGORY_WORLD, true, "Allows Copper to spawn in the world.");
		generateNikolite = configuration.getBoolean("GenerateNikolite", CATEGORY_WORLD, true, "Allows Nikolite to spawn in the world.");
		generateTungsten = configuration.getBoolean("GenerateTungsten", CATEGORY_WORLD, true, "Allows Tungsten to spawn in the world.");
		//Features
		splashScreenImplementation = configuration.getBoolean("Splash Screen Implementation", CATEGORY_FEATURES, true, "Shows the progress of some loading events on the FML Splash Screen.");
		configuration.save();
	}
}
