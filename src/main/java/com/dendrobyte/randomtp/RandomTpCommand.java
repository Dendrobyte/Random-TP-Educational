package com.dendrobyte.randomtp;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * This command implements "CommandExecutor", which just enforces our method onCommand to have the proper arguments.
 * The "implements" keyword refers to an interface in Java.
 * TODO: Look up the difference between Interfaces and Parent classes (if you don't know already)
 */
public class RandomTpCommand implements CommandExecutor {

    /*
     * This is our general command class. Because we've defined it in the
     * setExecutor function in our Main class file, then whenever someone types
     * "/randomtp", the server is automatically going to run this "onCommand"
     * function
     * 
     * ARGUMENTS
     * This function takes arguments. Given that this is automatically run, we have
     * a few things predetermined.
     * 
     * CommandSender: This is going to be the player, the console, etc. Whatever
     * entity sends the command.
     * Command: This is the command itself! We don't use this much, or at least I
     * don't, but you can access the command object generated with this part.
     * TODO: Print out "command" in the logger. See what it has on it! You may need
     * a toString() function.
     * String: This is the command we type, for example "/randomtp" means the label
     * is going to be "randomtp". Useful if you have multiple commands in one
     * executor.
     * 
     * String[] args: This is an array of strings for arguments. If I type
     * "/tp Mobkinz Dendrobyte", then "args[0]" is going to be "Mobkinz" and
     * "args[1]" is going to be "Dendrobyte".
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = Main.getInstance().prefix;

        /*
         * Let's make sure our commandsender is a player! We can't teleport a console
         * around. What other entities could be command senders?
         */
        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "You should be a player to use this command!");
            return true; // End the function so we don't proceed
        }

        /*
         * Let's go ahead and cast our CommandSender into a Player object.
         * This allows us to use all the functions available to the Player object. Take
         * a look at hte documentation to see all you can do!
         */
        Player player = (Player) sender;
        // TODO: Log the player's name who is sending the message. Hint:
        // player.getName()
        // BONUS: Can you log their UUID? (check documentation for function, or your
        // IDE/editor may be able to help)

        /*
         * For sake of example, we can use the length of arguments to enforce some
         * things.
         * NOTE: I may also come back and add teleporting to a specific biome to this
         * plugin... or someone else will :D
         * 
         * If you want to message a player, you can enforce there are the proper number
         * of arguments, etc.
         * 
         * To see an example with arguments, feel free to let me know- or check out the
         * Build My Thing repository for example.
         * BMT command class:
         * https://github.com/Dendrobyte/Minecraft-BuildMyThing
         */
        if (args.length > 0) {
            player.sendMessage(prefix + "This command takes no arguments.");
            return true;
        }

        // Maximum bounds for our random teleportation coordinates
        // TODO: Change 'em up!
        int maxLocX = 1000;
        int maxLocZ = 1000;
        // Ignore Y since that's vertical

        /*
         * Let's generate some random numbers.
         * This is fairly standard Java random number generation, but pretty much we're
         * about to teleport the player to a random location. Minecraft goes negative as
         * well, so our bounds are going to be negative to positive of the above
         * numbers.
         * 
         * We multiply the max locations by two for this reason exactly. Generate a
         * number between 0 and 2000, and if we get 500, then we end up with -500 for
         * the result. The lower bound of nextInt is 0 by default
         */
        Random rand = new Random();
        int newRandLocX = rand.nextInt(maxLocX * 2) - maxLocX;
        int newRandLocZ = rand.nextInt(maxLocZ * 2) - maxLocZ;

        /*
         * Now we teleport the Player! We use a method from the Player class (see
         * documentation) to simply change their location.
         * 
         * However, we can't just input the coordinates. We need to make a new Location
         * object, which is taken from bukkit/spigot.
         * Make sure you don't import Location from another library!
         */
        Location newLoc = new Location(player.getWorld(), newRandLocX, 67, newRandLocZ);
        player.teleport(newLoc);
        player.sendMessage(prefix + "You have been teleported randomly!");

        /*
         * TODO! We use 100 because I think that will teleport the player above land.
         * How can you calculate Y?
         * Your task now is to write some code above that calculates a good Y value.
         * 
         * Suggested approach:
         * 1. Get the new location as we did above
         * 2. Check what block it is
         * 3. If it is air, teleport the player
         * 4. If not, increase Y by 5 and check if it's air again (HINT: Loops)
         * 
         * You may reach a cave this way, but feel free to work with whatever you want!
         */

        // This function expects to return a boolean. We always return true to avoid
        // unwanted messages. Returning false prints its usage as defined in the
        // plugin.yml
        return true;
    }

}
