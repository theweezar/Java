package app.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Collection<Song> songs;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Collection<PlayList> playlist;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Song> getSongs() {
		return songs;
	}
	public void setSongs(Collection<Song> songs) {
		this.songs = songs;
	}
	public Collection<PlayList> getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Collection<PlayList> playlist) {
		this.playlist = playlist;
	}
	
	
}
