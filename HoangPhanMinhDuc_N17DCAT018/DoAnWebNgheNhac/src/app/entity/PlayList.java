package app.entity;

import javax.persistence.*;

@Entity
@Table(name="playlist")
public class PlayList {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="isLater")
	private int isLater;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int isLater() {
		return isLater;
	}

	public void setLater(int isLater) {
		this.isLater = isLater;
	}
	
	
}
