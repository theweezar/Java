package app.entity;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="playlist_detail")
public class PlayListDetail {
	@Id
	@Column(name="plId")
	private int plId;
	
//	@Id
//	@ManyToOne
//	@JoinColumn(name="plId")
//	private PlayList playlist;
	
	@Column(name="songId")
	private int songId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="add_at")
	private Date add_at;

	public int getPlId() {
		return plId;
	}

	public void setPlId(int plId) {
		this.plId = plId;
	}

	public int getSongId() {
		return songId;
	}

	public void setSongId(int songId) {
		this.songId = songId;
	}

	public Date getAdd_at() {
		return add_at;
	}

	public void setAdd_at(Date add_at) {
		this.add_at = add_at;
	}
	
}
