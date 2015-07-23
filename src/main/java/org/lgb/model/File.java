package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class File implements Serializable {

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "file")
	private Set<Version> versions = new HashSet<Version>(0);
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
    @JoinColumn(name="latest_version") 
	private Version latestVersion;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "uuid", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "type")
	private String type;
	
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
	
	@JsonProperty("path")
	public String getPath() {
		return path;
	}
	
	@JsonProperty("content-type")
	public String getType() {
		return type;
	}
	
	public void setModified(Date date){
		this.modified = date;
	}
	
	public void setPath(String value) {
		this.path = value;
	}
	
	public void setType(String value) {
		this.type = value;
	}

	public void setName(String value) {
		this.name = value;
	}
	
	@JsonProperty("created")
	public Date getCreated(){
		return this.created;
	}

	@JsonIgnore
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
