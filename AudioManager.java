import greenfoot.*;

public class AudioManager extends Actor{
    private static AudioManager instance;
    private int volume = 50;
    private boolean isMuted = false;

    private AudioManager() {}

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void setVolume(int newVolume) {
        volume = Math.min(100, Math.max(0, newVolume));
    }

    public int getVolume() {
        return volume;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public int getEffectiveVolume() {
        return isMuted ? 0 : volume;
    }
}
