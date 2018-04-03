package ee.omniva.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


//@Document(collection = Invoice.COLLECTION_NAME)
@Entity
@Table(name="INVOICES")
public class Invoice {
	
	//public static final String COLLECTION_NAME = "invoices";

	@Id
	//@GeneratedValue(generator="system-uuid")
	//@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;

	private String name;

	private String description;

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
