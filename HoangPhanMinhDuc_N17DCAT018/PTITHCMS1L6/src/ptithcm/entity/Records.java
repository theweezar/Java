package ptithcm.entity;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Records")
public class Records {
	@Id
	@GeneratedValue
	private Integer id;
	private Byte type;
	private String reason;
	private Date date;
	@ManyToOne
	@JoinColumn(name="staffid")
	private Staffs staff;
	
	public Records() {}
	public Records(Integer id, Byte type, String reason, Date date, Staffs staff) {
		this.setId(id);
		this.setType(type);
		this.setReason(reason);
		this.setDate(date);
		this.setStaffs(staff);
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setType(Byte type) {
		this.type=type;
	}
	public Byte getType() {
		return type;
	}
	public void setReason(String reason) {
		this.reason=reason;
	}
	public String getReason() {
		return reason;
	}
	public void setDate(Date date) {
		this.date=date;
	}
	public Date getDate() {
		return date;
	}
	public void setStaffs(Staffs staff) {
		this.staff=staff;
	}
	public Staffs getStaff() {
		return staff;
	}
}
