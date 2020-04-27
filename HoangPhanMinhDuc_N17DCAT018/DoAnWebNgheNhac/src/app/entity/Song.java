package app.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="songs")
public class Song {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="link")
	private String link;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="upload_at")
	private Date uploadAt;
	
	@Column(name="songName")
	private String songName;
	
	@Column(name="singerName")
	private String singerName;
	
	@Column(name="musicianName")
	private String musicianName;
	
	@Column(name="kindId")
	private int kindId;
	
	@Column(name="_view")
	private int view;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getUploadAt() {
		return uploadAt;
	}

	public void setUploadAt(Date uploadAt) {
		this.uploadAt = uploadAt;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getMusicianName() {
		return musicianName;
	}

	public void setMusicianName(String musicianName) {
		this.musicianName = musicianName;
	}

	public int getKindId() {
		return kindId;
	}

	public void setKindId(int kindId) {
		this.kindId = kindId;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}
	
	
}
