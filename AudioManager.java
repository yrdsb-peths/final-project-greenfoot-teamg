import greenfoot.*;

/**
* AudioManager handles all game audio settings using the Singleton pattern.
* Manages volume levels and mute state across the entire game.
*/
public class AudioManager extends Actor {
   // Single instance for Singleton pattern
   private static AudioManager instance;
   
   // Current volume level (0-100)
   private int volume = 50;
   
   // Mute state flag
   private boolean isMuted = false;
   
   /**
    * Private constructor to prevent direct instantiation.
    * Part of Singleton pattern implementation.
    */
   private AudioManager() {}
   
   /**
    * Gets the singleton instance of AudioManager.
    * Creates new instance if none exists (lazy initialization).
    * 
    * @return The single AudioManager instance
    */
   public static AudioManager getInstance() {
       if (instance == null) {
           instance = new AudioManager();
       }
       return instance;
   }

   /**
    * Sets the volume level, clamped between 0 and 100.
    * 
    * @param newVolume The desired volume level
    */
   public void setVolume(int newVolume) {
       volume = Math.min(100, Math.max(0, newVolume));
   }

   /**
    * Gets the current volume setting.
    * 
    * @return The current volume level (0-100)
    */
   public int getVolume() {
       return volume;
   }

   /**
    * Sets the mute state of the audio.
    * 
    * @param muted True to mute audio, false to unmute
    */
   public void setMuted(boolean muted) {
       isMuted = muted;
   }

   /**
    * Checks if audio is currently muted.
    * 
    * @return True if muted, false otherwise
    */
   public boolean isMuted() {
       return isMuted;
   }

   /**
    * Gets the effective volume level considering mute state.
    * Returns 0 if muted, otherwise returns current volume.
    * 
    * @return The effective volume level
    */
   public int getEffectiveVolume() {
       return isMuted ? 0 : volume;
   }
}