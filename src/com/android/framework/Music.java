package com.android.framework;

public interface Music {
    public void play();


	public boolean isMute();
	public void setMute(boolean isMute);
    
    public void stop();

    public void pause();
    
    public void initializeMusic();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();

	void seekBegin();
}
