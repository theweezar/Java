package app.entity;

import javax.persistence.*;

@Entity
@Table(name="kind")
public class Kind {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="kindName")
	private String kindName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	
	
}
