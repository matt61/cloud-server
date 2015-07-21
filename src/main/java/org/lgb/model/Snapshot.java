package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "snapshot")
public class Snapshot implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "snapshot_content", 
		joinColumns = {@JoinColumn(name = "file_content_id", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "snapshot_id", nullable = false, updatable = false)}
	)
	private Set<Content> contents = new HashSet<Content>(0);

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

	@JsonIgnore
	public Set<Content> getContents() {
		return this.contents;
	}
}
