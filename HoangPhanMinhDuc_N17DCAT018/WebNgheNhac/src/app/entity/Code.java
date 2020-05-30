package app.entity;

import javax.persistence.*;

@Entity
@Table(name="Code")
public class Code {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="code")
	private String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
