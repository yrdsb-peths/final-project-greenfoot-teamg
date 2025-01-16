import greenfoot.*;

/**
* VolumeSlider represents a draggable slider for controlling audio volume.
* Provides visual feedback and updates the AudioManager with volume changes.
*/
public class VolumeSlider extends Actor {
   // Dragging state flag
   private boolean isDragging = false;
   
   // Bounds for slider movement
   private int minX = 170; // Minimum x position
   private int maxX = 430; // Maximum x position
   
   // Visual elements
   private GreenfootImage sliderBar;
   private int sliderWidth;
   private int sliderHeight;
   
   // Audio management reference
   private AudioManager audioManager;

   /**
    * Constructor initializes the slider with a starting volume.
    * 
    * @param initialVolume The starting volume level (0-100)
    */
   public VolumeSlider(int initialVolume) {
       audioManager = AudioManager.getInstance();
       setupSliderBar();
       // Initial position is set in addedToWorld to ensure correct positioning
   }

   /**
    * Sets up the visual appearance of the slider.
    */
   private void setupSliderBar() {
       sliderBar = new GreenfootImage("VolumeSlider.png");
       sliderBar.scale(60, 60);
       sliderWidth = sliderBar.getWidth();
       sliderHeight = sliderBar.getHeight();
       setImage(sliderBar);
   }

   /**
    * Called when the slider is added to a world.
    * Sets the initial position based on current volume.
    * 
    * @param world The world this slider is added to
    */
   @Override
   protected void addedToWorld(World world) {
       setInitialPosition(audioManager.getVolume());
   }
   
   /**
    * Calculates and sets the initial position of the slider.
    * 
    * @param initialVolume The volume to base position on (0-100)
    */
   private void setInitialPosition(int initialVolume) {
       int newX = minX + (int)((maxX - minX) * (initialVolume / 100.0));
       setLocation(newX, getY());
   }

   /**
    * Called every frame to handle slider interactions.
    */
   public void act() {
       handleDragging();
   }

   /**
    * Handles mouse interactions for dragging the slider.
    */
   private void handleDragging() {
       if (Greenfoot.mousePressed(this)) {
           isDragging = true;
       }

       if (isDragging && Greenfoot.mouseDragged(this)) {
           MouseInfo mouse = Greenfoot.getMouseInfo();
           if (mouse != null) {
               // Clamp mouseX to slider bounds
               int mouseX = Math.max(minX, Math.min(maxX, mouse.getX()));
               updateVolumeFromPosition(mouseX);
           }
       }

       if (Greenfoot.mouseClicked(null)) {
           isDragging = false;
       }
   }

   /**
    * Updates the volume based on slider position.
    * 
    * @param mouseX The x-coordinate of the mouse
    */
   private void updateVolumeFromPosition(int mouseX) {
       setLocation(mouseX, getY());

       // Convert position to volume (0-100)
       int volume = (int)(((double)(mouseX - minX) / (maxX - minX)) * 100);
       
       // Update volume through AudioManager
       audioManager.setVolume(volume);

       // Update the Settings world display if present
       World world = getWorld();
       if (world instanceof Settings) {
           ((Settings)world).updateVolume(volume);
       }
   }

   /**
    * Sets the slider value and updates related components.
    * 
    * @param volume The new volume value (0-100)
    */
   public void setValue(int volume) {
       // Clamp volume to valid range
       volume = Math.min(100, Math.max(0, volume));

       // Update AudioManager
       audioManager.setVolume(volume);

       // Update slider position
       int newX = minX + (int)((maxX - minX) * (volume / 100.0));
       setLocation(newX, getY());

       // Update the Settings world display if present
       World world = getWorld();
       if (world instanceof Settings) {
           ((Settings)world).updateVolume(volume);
       }
   }
}