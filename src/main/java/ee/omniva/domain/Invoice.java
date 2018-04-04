package ee.omniva.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name="INVOICES")
@Check(constraints="length(ID) >= 10")
public class Invoice {
	
	@Id
	@Column(name="ID", length=25)
	private String id;

	@Column(name="NAME", length=100)
	private String name;

	@Column(name="DESCRIPTION", length=1000)
	private String description;

	@Column(name="PAID")
	private boolean paid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
