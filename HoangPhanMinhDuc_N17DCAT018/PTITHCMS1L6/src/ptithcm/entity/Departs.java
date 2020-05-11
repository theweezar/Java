package ptithcm.entity;

import java.util.Collection;

import javax.persistence.*;


@Entity
@Table(name="Departs")
public class Departs {
	@Id
	private String id;
	private String name;
	@OneToMany(mappedBy="depart", fetch=FetchType.EAGER)
	private Collection<Staffs> staffs;
	
	public Departs() {}
	public Departs(String id, String name, Collection<Staffs> staffs) {
		this.setId(id);
		this.setName(name);
		this.setStaffs(staffs);
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setStaffs(Collection<Staffs> staffs) {
		this.staffs=staffs;
	}
	public Collection<Staffs> getStaffs(){
		return staffs;
	}
}
