package app.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="playlist")
public class PlayList {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
//	@Column(name="userId")
//	private int userId;
	
	@Column(name="plName")
	private String plName;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="isLater")
	private int isLater;
	
	@OneToMany(mappedBy="playlist",fetch=FetchType.EAGER)
	private Collection<PlayListDetail> plDetail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

	public int isLater() {
		return isLater;
	}

	public void setLater(int isLater) {
		this.isLater = isLater;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<PlayListDetail> getPlDetail() {
		return plDetail;
	}

	public void setPlDetail(Collection<PlayListDetail> plDetail) {
		this.plDetail = plDetail;
	}

	public String getPlName() {
		return plName;
	}

	public void setPlName(String plName) {
		this.plName = plName;
	}
	
	
}
