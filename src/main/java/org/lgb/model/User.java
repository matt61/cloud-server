package org.lgb.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
    private Long id;
    
	@Column(name = "name")
	private String name;
	
	@JsonProperty("id")
    public Long getId() {
        return id;
    }
	
	@JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
	
	public String toString() {
        return "TEST";
    }
}