import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private MenuScreen menuScreen;
    SimpleTimer cooldown = new SimpleTimer();
    
    // Array of instruction strings
    private String[] instructions = {
        "Welcome to the game!",
        "Control your ship with WASD \n to avoid enemy attacks!",
        "You can only take ONE hit \n before exploding.",
        "Press and hold \"spacebar\" to \n fight back with your gun.",
        "Hold \"shift\" while moving \n to move slower.",
        "press \"v\" to activate each \n character's powerup",
        "Different enemies and bullets \n have different attack patterns.",
        "Identify them by their ship \n design and bullet colour.",
        "Press \"escape\" at any time to \n pause and access the settings.",
        "Try to beat the game \n as fast as possible. \n Good luck!"
    };
    
    // Variables used in the class
    private Label instructionLabel;
    private int index = 0;
    private int buttonXPosition = 300;
    private Label pageNum;

    public InstructionScreen(MenuScreen menuScreen)
    {    
        // Create a new world with 500x700 cells with a cell size of 1x1 pixels
        super(600, 750, 1);

        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        this.menuScreen = menuScreen;

        // Create a label with the first instruction
        instructionLabel = new Label(instructions[index], 50);
        Color offWhite = new Color(251, 247, 245);
        Color black = new Color(0,0,0);
        instructionLabel.setLineColor(black);
        instructionLabel.setFillColor(offWhite);
        addObject(instructionLabel, buttonXPosition, 375);

        // Add labels
        addLabels();

        cooldown.mark();
    }

    // Instruction navigation with arrow keys
    public void arrowNav() {
        // Prevents accidentally changing screens too fast
        if(cooldown.millisElapsed() > 300) {

            if(Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("a")) {
                previousInstruction();
                cooldown.mark();
            }
            else if(Greenfoot.isKeyDown("Right") || Greenfoot.isKeyDown("d")){
                nextInstruction();
                cooldown.mark();
            }
        }
    }

    public void addLabels() {
        addObject(new Label("ESC", 30), 40, 725);
        addObject(new Label("Back", 25), 100, 725);
        addObject(new Label("Use left and right arrow keys \n to read through instructions", 30), 300, 70);

        pageNum = new Label(1 + "/" + instructions.length, 40);
        addObject(pageNum, 300, 450);
    }

    // Change page number when user uses arrow keys
    public void updatePageNum() {
        pageNum.setValue(index + 1 + "/" + instructions.length);
    }

    public void act() {
        Util.handleEscapeKey(this, menuScreen);
        arrowNav();
    }

    // Method to display the next instruction
    public void nextInstruction() {
        if (index < instructions.length - 1) {
            index++;
            instructionLabel.setValue(instructions[index]); // Update the label with the next instruction
            updatePageNum();
        }
    }

    // Method to display the previous instruction
    public void previousInstruction() {
        if (index > 0) {
            index--;
            instructionLabel.setValue(instructions[index]); // Update the label with the previous instruction
            updatePageNum();
        }
    }

    /**
     * Transitions to the menu screen and handles music cleanup.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}