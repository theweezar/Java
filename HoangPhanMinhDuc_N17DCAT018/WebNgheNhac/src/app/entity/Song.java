package app.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="songs")
public class Song {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@OneToMany(mappedBy="song", fetch=FetchType.EAGER)
	private Collection<PlayListDetail> details;
	
	@Column(name="link")
	private String link;
	
//	@Column(name="userId")
//	private int userId;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="upload_at")
	private Date uploadAt;
	
	@Column(name="songName")
	private String songName;
	
	@Column(name="singerName")
	private String singerName;
	
	@Column(name="musicianName")
	private String musicianName;
	
//	@Column(name="kindId")
//	private int kindId;
	
	@ManyToOne
	@JoinColumn(name="kindId")
	private Kind kind;
	
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

/*	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

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

//	public int getKindId() {
//		return kindId;
//	}
//
//	public void setKindId(int kindId) {
//		this.kindId = kindId;
//	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Collection<PlayListDetail> getDetails() {
		return details;
	}

	public void setDetails(Collection<PlayListDetail> details) {
		this.details = details;
	}
	
	
	
}
