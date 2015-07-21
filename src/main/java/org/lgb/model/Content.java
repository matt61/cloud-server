package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.HashSet;
import java.util.Set;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Entity
@Table(name = "file_content")
public class Content implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "contents")
	private Set<Snapshot> snapshots = new HashSet<Snapshot>(0);
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "created")
	private Date created;
	
	public Content(){
	}
	
	public Content(File file){
		this.setFile(file);
	}
	
	@PrePersist
	protected void onCreate() {
	  this.created = new Date();
	}
	
	@JsonProperty("id")
	public Long getId() {
		return id;
	}
	
	@JsonProperty("created")
	public Date getDate() {
		return created;
	}
	 
	public void setFile(File file) {
		this.file = file;
	}
	
	@JsonIgnore
	public File getFile() {
		return this.file;
	}

	@JsonIgnore
	public Set<Snapshot> getSnapshots() {
		return this.snapshots;
	}

	public void saveFile(InputStream input) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[8 * 1024];

		try {
			OutputStream output = new FileOutputStream("/tmp/"+this.getFile().getName());
			try {
				int bytesRead;
				while ((bytesRead = input.read(buffer)) != -1) {
					output.write(buffer, 0, bytesRead);
				}
			} finally {
				output.close();
			}
		} finally {
			input.close();
		}
	}
}
