package ptithcm.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class Users {
	
	@Id
	private String username;
	private String password;
	private String fullname;
	
	public Users() {}
	public Users(String username, String password, String fullname) {
		this.setUsername(username);
		this.setPassword(password);
		this.setFullname(fullname);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getFullname() {
		return fullname;
	}

	
	
}
