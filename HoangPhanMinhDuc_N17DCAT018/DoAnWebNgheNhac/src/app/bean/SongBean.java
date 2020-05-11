package app.bean;

import app.entity.Song;

public class SongBean {
	
	private Song song;
	private boolean added;
	public SongBean(Song song, boolean added) {
		this.song = song;
		this.added = added;
	}
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public boolean isAdded() {
		return added;
	}
	public void setAdded(boolean added) {
		this.added = added;
	}
	
}
