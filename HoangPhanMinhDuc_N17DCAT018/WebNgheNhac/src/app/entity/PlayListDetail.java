package app.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="playlist_detail")
public class PlayListDetail {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="plId")
	private PlayList playlist;
	
	@ManyToOne
	@JoinColumn(name="songId")
	private Song song;
	
//	@Column(name="songId")
//	private int songId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="add_at")
	private Date add_at;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getSongId() {
//		return songId;
//	}
//
//	public void setSongId(int songId) {
//		this.songId = songId;
//	}

	public Date getAdd_at() {
		return add_at;
	}

	public void setAdd_at(Date add_at) {
		this.add_at = add_at;
	}

	public PlayList getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlayList playlist) {
		this.playlist = playlist;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
	
}
