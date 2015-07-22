package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "snapshot")
public class Snapshot implements Serializable {

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "snapshot_content", 
		joinColumns = {@JoinColumn(name = "file_content_id", nullable = false, updatable = false)},
		inverseJoinColumns = {@JoinColumn(name = "snapshot_id", nullable = false, updatable = false)}
	)
	private Set<Version> versions = new HashSet<Version>(0);

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "uuid", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "name")
	private String name;
	
	@JsonProperty("created")
	@Column(name = "created")
	private Date created;
	
	@javax.persistence.Version 
	@JsonProperty("modified")
	@Column(name = "modified")
	private Date modified;
	
	public Snapshot(){
		this.created = new Date();
	}

	@JsonProperty("id")
	public UUID getId() {
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
	public Set<Version> getVersions() {
		return this.versions;
	}
	
	public void addFile(File file){
		this.versions.add(file.getLastestVersion());
	}
}
