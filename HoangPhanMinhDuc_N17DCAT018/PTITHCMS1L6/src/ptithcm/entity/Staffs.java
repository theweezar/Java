package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Staffs")
public class Staffs {
	@Id
	private String id;
	private String name;
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date birthday;
	private String photo;
	private String email;
	private String phone;
	private Double salary;
	private String notes;
	@ManyToOne
	@JoinColumn(name="departid")
	private Departs depart;
	@OneToMany(mappedBy="staff", fetch=FetchType.EAGER)
	private Collection<Records> records;
	
	public Staffs() {}
	public Staffs(String id, String name, Boolean gender, Date birthday, String photo, 
			String email, String phone, Double salary, String notes, Departs depart,
			Collection<Records> records) {
		
		this.setId(id);
		this.setName(name);
		this.setGender(gender);
		this.setBirthday(birthday);
		this.setPhoto(photo);
		this.setEmail(email);
		this.setPhone(phone);
		this.setSalary(salary);
		this.setNotes(notes);
		this.setDeparts(depart);
		this.setRecords(records);
	}
	
	public void setId(String id) {
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setGender(Boolean gender) {
		this.gender=gender;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setBirthday(Date birthday) {
		this.birthday=birthday;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setPhoto(String photo) {
		this.photo=photo;
	}
	public String getPhoto() {
		return photo;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setSalary(Double salary) {
		this.salary=salary;
	}
	public Double getSalary() {
		return salary;
	}
	public void setNotes(String notes) {
		this.notes=notes;
	}
	public String getNotes() {
		return notes;
	}
	public void setDeparts(Departs depart) {
		this.depart=depart;
	}
	public Departs getDepart() {
		return depart;
	}
	public void setRecords(Collection<Records> records) {
		this.records=records;
	}
	public Collection<Records> getRecords(){
		return records;
	}
}
