package app.entity;

import java.util.Collection;

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
	
//	@OneToMany(mappedBy="kind", fetch=FetchType.EAGER)
//	private Collection<Kind> songs;

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

//	public Collection<Kind> getSongs() {
//		return songs;
//	}
//
//	public void setSongs(Collection<Kind> songs) {
//		this.songs = songs;
//	}
//	
//	
	
}
