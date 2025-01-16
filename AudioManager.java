import greenfoot.*;

public class AudioManager extends Actor{
    private static AudioManager instance;
    private int volume = 50;
    private boolean isMuted = false;
    
    /**
     * Gets the same instance of audiomanager when called.
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /**
     * Changes the volume
     */
    public void setVolume(int newVolume) {
        volume = Math.min(100, Math.max(0, newVolume));
    }

    /**
     * Returns the volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Mutes the volume
     */
    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    /**
     * checks if instance is muted
     */
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Returns the effective volume
     */
    public int getEffectiveVolume() {
        return isMuted ? 0 : volume;
    }
}
