import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private MenuScreen menuScreen;
    
    // Array of instruction strings
    private String[] instructions = {
        "Welcome to the game!",
        "Control your ship with WASD \n to avoid enemy attacks!",
        "You can only take X hits \n before exploding.",
        "Press and hold \"spacebar\" to \n fight back with your gun.",
        "Hold \"shift\" while moving \n to move slower.",
        "Different enemies and bullets \n have different attack patterns.",
        "Identify them by their ship \n design and bullet colour.",
        "Press \"escape\" at any time to \n pause and access the settings.",
        "Try to get the highest \n score possible!"
    };
    
    // Variables used in the class
    private Label instructionLabel;
    private int index = 0;
    private int buttonXPosition = 300;
    private int buttonYPosition = 330;

    public InstructionScreen(MenuScreen menuScreen)
    {    
        // Create a new world with 500x700 cells with a cell size of 1x1 pixels
        super(600, 750, 1);

        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        this.menuScreen = menuScreen;

        // Create a label with the first instruction
        instructionLabel = new Label(instructions[index], 40);
        Color offWhite = new Color(251, 247, 245);
        instructionLabel.setLineColor(offWhite);
        instructionLabel.setFillColor(offWhite);
        addObject(instructionLabel, buttonXPosition, 200);

        // Add buttons
        addButtons();

        // Add labels
        addLabels();
    }

    public void addButtons() {
        // Add "Next" and "Previous" buttons
        addObject(new Button(this::nextInstruction, "Next"), buttonXPosition, buttonYPosition);
        addObject(new Button(this::previousInstruction, "Previous"), buttonXPosition, buttonYPosition + 50);
    }

    public void addLabels() {
        addObject(new Label("ESC", 30), 40, 725);
        addObject(new Label("Back", 25), 100, 725);
    }

    public void act() {
        Util.handleEscapeKey(this, menuScreen);
    }

    // Method to display the next instruction
    public void nextInstruction() {
        if (index < instructions.length - 1) {
            index++;
            instructionLabel.setValue(instructions[index]); // Update the label with the next instruction
        }
    }

    // Method to display the previous instruction
    public void previousInstruction() {
        if (index > 0) {
            index--;
            instructionLabel.setValue(instructions[index]); // Update the label with the previous instruction
        }
    }

    /**
     * Transitions to the menu screen and handles music cleanup.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}