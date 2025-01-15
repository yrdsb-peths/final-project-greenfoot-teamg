import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StageSelection extends World
{
    MenuScreen menuScreen;
    GreenfootImage selectedImage;
    int whichCharacter;
    int wave = 1;
    int level = 1;
    private boolean checker = true;
    boolean isSelectLevel = true;
    CharacterSelection characterSelection;
    Label levelLabel;
    Label waveLabel;
    
    public StageSelection(GreenfootImage selectedImage, MenuScreen menuScreen, int whichCharacter, CharacterSelection characterSelection)
    {    
        super(600, 750, 1); 
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);
        this.selectedImage = selectedImage;
        this.menuScreen = menuScreen;
        this.whichCharacter = whichCharacter;
        this.characterSelection = characterSelection;
        levelLabel = new Label("Level: " + level, 50);
        waveLabel = new Label("Wave: " + wave, 50);
        Label instructionsLabel = new Label("Press Enter To Select \n Level And Wave. \n Use arrow keys or \n WASD to change values.", 50);
        Label warningLabel = new Label("*Games will not count towards \n leaderboard unless game \n is started at level 1 & wave 1", 30);
        addObject(levelLabel, getWidth() / 2, getHeight() / 2);
        addObject(waveLabel, getWidth()/2, getHeight()/2 + 50);
        addObject(instructionsLabel, getWidth() / 2, getHeight() / 6);
        addObject(warningLabel, getWidth()/2, 5 * getHeight()/6);
        updateLabel();
    }
    
    public void act()
    {
        handleUpDown();
        if(!handleRightLeft() && !handleEnter())
        {
            checker = false;
        }
    }
    
    public boolean handleEnter()
    {
        if(Greenfoot.isKeyDown("enter"))
        {
            if(checker == false)
            {
                menuScreen.menuMusic.stop();
                Game game;
                if(level == 1)
                {
                    game = new Level1(selectedImage, menuScreen, whichCharacter);
                }
                else if(level == 2)
                {
                    game = new Level2(selectedImage, menuScreen, whichCharacter);
                }
                else
                {
                    game = new Level3(selectedImage, menuScreen, whichCharacter);
                }
                game.waveNumber = wave;
                Greenfoot.setWorld(game);
            }
            else
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean handleRightLeft()
    {
        if(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left"))
        {
            if(checker == false)
            {
                if(isSelectLevel == true)
                {
                    level--;
                    if(level < 1)
                    {
                        level++;
                    }
                }
                else
                {
                    wave--;
                    if(wave < 1)
                    {
                        wave++;
                    }
                }
                updateLabel();
            }
            checker = true;
            return true;
        }
        else if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right"))
        {
            if(checker == false)
            {
                if(isSelectLevel == true)
                {
                    level++;
                    if(level > 3)
                    {
                        level--;
                    }
                }
                else
                {
                    wave++;
                    if(wave > 5)
                    {
                        wave--;
                    }
                }
                updateLabel();
            }
            checker = true;
            return true;
        }
        return false;
    }
    
    public void handleUpDown()
    {
        if(Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up"))
        {
            isSelectLevel = true;
            updateLabel();
        }
        else if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down"))
        {
            isSelectLevel = false;
            updateLabel();
        }
    }
    
    public void updateLabel()
    {
        if(isSelectLevel)
        {
            levelLabel.setLineColor(Color.YELLOW);
            waveLabel.setLineColor(Color.BLACK);
        }
        else
        {
            waveLabel.setLineColor(Color.YELLOW);
            levelLabel.setLineColor(Color.BLACK);
        }
        levelLabel.setValue("Level: " + level);
        waveLabel.setValue("Wave: " + wave);
    }
}
