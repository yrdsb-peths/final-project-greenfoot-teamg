import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class VolumeSlider extends Actor {
  private boolean isDragging = false;
  private int minX, maxX;
  
  public VolumeSlider(int initialVolume) {
      // Create slider bar image
      GreenfootImage sliderBar = new GreenfootImage(200, 30);
      sliderBar.setColor(new Color(200, 200, 200)); // Light gray background
      sliderBar.fill();
      sliderBar.setColor(Color.BLACK);
      sliderBar.drawRect(0, 0, sliderBar.getWidth()-1, sliderBar.getHeight()-1);
      
      // Create slider knob
      GreenfootImage knob = new GreenfootImage(20, 40);
      knob.setColor(Color.BLUE);
      knob.fillOval(0, 0, knob.getWidth()-1, knob.getHeight()-1);
      
      // Combine images
      GreenfootImage finalImage = new GreenfootImage(220, 40);
      finalImage.drawImage(sliderBar, 10, 5);
      finalImage.drawImage(knob, 100, 0); // Center position
      
      setImage(finalImage);
  }
  
  public void act() {
      if (Greenfoot.mousePressed(this)) {
          isDragging = true;
      }
      
      if (isDragging && Greenfoot.mouseDragged(null)) {
          MouseInfo mouse = Greenfoot.getMouseInfo();
          if (mouse != null) {
              // Calculate bounds if not set
              if (minX == 0) {
                  minX = getX() - 90; // Left bound
                  maxX = getX() + 90; // Right bound
              }
              
              int newX = Math.min(maxX, Math.max(minX, mouse.getX()));
              setLocation(newX, getY());
              
              // Calculate and update volume
              int volume = (int)(((double)(newX - minX) / (maxX - minX)) * 100);
              Settings.setVolume(volume);
          }
      }
      
      if (Greenfoot.mouseClicked(null)) {
          isDragging = false;
      }
  }
}