package com.dendrobyte.randomtp;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Welcome!
 * Hopefully this plugin is a nice project to walk through. I generally try to
 * follow a flow with my comments,
 * but we'll see if that works.
 * 
 * This is the main initialization of the Java plugin. I choose to call my main
 * file "Main". Some choose "Core", etc.
 * It extends JavaPlugin as that is the "parent" object of our plugin.
 * This is the first and last time I will reiterate: these comments will not
 * teach Java, such as what a parent
 * object implies. See the README for more.
 */
public class Main extends JavaPlugin {

    /*
     * These are two variables we want to access throughout the plugin.
     * 
     * "instance" is going to be an accessible instance of this object. More on that
     * when we initialize it in the onEnable function below.
     * 
     * The "prefix" string is so that we can access our plugin's prefix throughout
     * all files and only having to change one string.
     */
    private static Main instance;
    public String prefix = "[RandomTP] "; // This should be private with a public get method, but eh.

    /*
     * This is our onEnable method. When the server starts up, it runs through the
     * onEnable() functions of eeeevvveerryyy plugin in your plugins folder. We're
     * going to print a log to show this, enable our command, and do what's called
     * "instantiating the main instance".
     */
    @Override
    public void onEnable() {

        // Here, we call the Logger object from Bukkit to send a message that our plugin
        // is starting up.
        Bukkit.getLogger().log(Level.INFO, "RandomTP plugin starting up...");

        /*
         * Let's go ahead and register a command! The "getCommand" function is
         * inherently something from Bukkit. We do not make this command, it already
         * exists in "JavaPlugin".
         * 
         * The name- "randomtp" in this case- MUST match the `plugin.yml` in the
         * resources folder! If you want to make more commands, you must define the name
         * in your plugin.yml. If you want to make a new command executor (see
         * RandomTpCommand.java for explanations), follow the same format as that file
         */
        getCommand("randomtp").setExecutor(new RandomTpCommand());

        /*
         * For reasons I won't go into, we go ahead and set our instance equal to
         * "this", which is effectively the current object our code is running in. This
         * allows us to then get the prefix and generally access other methods in this
         * file/class.
         */
        instance = this;

        // Let's go ahead and log that we've finished setting up the plugin
        // TODO: This is your turn! How can we log to the logger that we're done?
        // BONUS Make up a fake error, and print it out at the warn level (hint:
        // Level.????)

    }

    /*
     * Just like our onEnable() method, this one runs when the server is being shut
     * down.
     * We're just going to go ahead and log that we're shutting down the plugin, but
     * in more complex plugins you may want to save player data, gracefully exit
     * players from arenas, save world information, etc.
     */
    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "RandomTP plugin successfully disabled.");
        // TODO: Go into your server and shut it down. Can you find this message?
        // What about when you do /reload?
    }

    // This lets us access the static instance of our plugin. The keyword "static"
    // is kind of complicated, but you get used it the more you do. Don't let it
    // discourage you!
    public static Main getInstance() {
        return instance;
    }

    /*
     * You can do a lot of other things in this main file, but again, it's simply
     * meant as the starting point
     * for all your flows. Look up example plugins on registering events, and you'll
     * get a good idea of it.
     * 
     * Other things include creating configuration files, registering permissions
     * dynamically, etc.
     */
}
