package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import javax.persistence.FetchType;

@Entity
@Table(name = "content")
public class Content implements Serializable {
	
	protected static Logger logger = Logger.getLogger("service");

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "content")
	private Set<Version> versions = new HashSet<Version>(0);

	@Id
	@Column(name = "id", columnDefinition = "CHAR(32)")
	private String id;

	@Column( name = "file_image" )
	@Lob
	private byte[] fileImage;
		
	@JsonProperty("created")
	@Column(name = "created")
	private Date created;
	
	public Content(){
		this.created = new Date();
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	public void setId(String md5){
		this.id = md5;
	}
	
	public void setFile(byte[] blob){
		this.fileImage = blob;
	}
	
	@JsonProperty("size")
	public Integer getSize(){
		return this.fileImage.length;
	}
	
	@JsonIgnore
	public byte[] getFile(){
		return fileImage;
	}
}
