package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "file")
public class File implements Serializable {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "file")
	private Set<Version> versions = new HashSet<Version>(0);
	
	@OneToOne(cascade=CascadeType.ALL)  
    @JoinColumn(name="latest_version") 
	private Version latestVersion;

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
	
	public File(){
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
	
	public void setModified(Date date){
		this.modified = date;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	public Date getCreated(){
		return this.created;
	}

	@JsonProperty("versions")
	public Set<Version> getVersions() {
		return this.versions;
	}
	
	@JsonIgnore
	public Version getLastestVersion(){
		return this.latestVersion;
	}
	
	public void setLatestVersion(Version version){
		this.latestVersion = version;
	}
}
