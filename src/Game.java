/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room entrance, library, computerlab, caffeteria, teacheroffice, hallway, studyarea, stairwayto2ndfloor, observatory, theater, hallway2ndfloor, studyarea2ndfloor, stairwayto1stfloor, trophyhall, biologylab, examroom;

        // create the rooms
        entrance = new Room("inside the main entrance of the university");
        theater = new Room("in the school theater");
        library = new Room("in the school library");
        computerlab = new Room("in a computing lab");
        caffeteria = new Room("in the school caffeteria");
        teacheroffice = new Room("in the teacher office");
        hallway = new Room("in the 1st floor hallway");
        studyarea = new Room("in the 1st floor study area");
        stairwayto2ndfloor = new Room("at the stairway to 2nd floor");
        observatory = new Room("in the school observatory");
        hallway2ndfloor = new Room("in the 2nd floor hallway");
        studyarea2ndfloor = new Room("in the 2nd floor study area");
        stairwayto1stfloor = new Room("at the stairway to 1st floor");
        trophyhall = new Room("in the school tropyhall");
        biologylab = new Room("in the school biologylab");
        examroom = new Room("in the exam room");


        // initialise room exits
        entrance.setExit("west", library);
        entrance.setExit("east", hallway);
        entrance.setExit("south", caffeteria);
        library.setExit("west", entrance);
        library.setExit("south", computerlab);
        computerlab.setExit("north", library);
        computerlab.setExit("east", caffeteria);
        caffeteria.setExit("west", computerlab);
        caffeteria.setExit("north", entrance);
        caffeteria.setExit("east", teacheroffice);
        teacheroffice.setExit("west", caffeteria);
        hallway.setExit("west", entrance);
        hallway.setExit("east", studyarea);
        studyarea.setExit("west", hallway);
        studyarea.setExit("south", stairwayto2ndfloor);
        stairwayto2ndfloor.setExit("up", stairwayto1stfloor);
        stairwayto2ndfloor.setExit("north", studyarea);
        stairwayto1stfloor.setExit("down", stairwayto2ndfloor);
        stairwayto1stfloor.setExit("north", studyarea2ndfloor);
        studyarea2ndfloor.setExit("south", stairwayto1stfloor);
        studyarea2ndfloor.setExit("west", hallway2ndfloor);
        hallway2ndfloor.setExit("east", studyarea2ndfloor);
        hallway2ndfloor.setExit("west", theater);
        theater.setExit("east", hallway2ndfloor);
        theater.setExit("south", trophyhall);
        theater.setExit("west", observatory);
        observatory.setExit("east", theater);
        observatory.setExit("south", biologylab);
        biologylab.setExit("north", observatory);
        biologylab.setExit("east", trophyhall);
        trophyhall.setExit("west", biologylab);
        trophyhall.setExit("north", theater);
        trophyhall.setExit("east", examroom);
        examroom.setExit("west", trophyhall);

        currentRoom = entrance;  // start game outside
    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }




    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();

    }

    /**
     * Command that prints out the description of the room
     * and which exits there are.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }
    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }
}
