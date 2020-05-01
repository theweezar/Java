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
}
