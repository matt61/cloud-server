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
import java.util.HashSet;
import java.util.Set;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "file_content")
public class Version implements Serializable {

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "versions")
	private Set<Snapshot> snapshots = new HashSet<Snapshot>(0);
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {javax.persistence.CascadeType.PERSIST})
	@JoinColumn(name = "file_id", nullable = false)
	private File file;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "uuid", columnDefinition = "BINARY(16)")
	private UUID id;
	
	@Column(name = "created")
	private Date created;
	
	public Version(){
		this.created = new Date();
	}
	
	public Version(File file){
		this.setFile(file);
		this.created = new Date();
	}
	
	@JsonProperty("id")
	public UUID getId() {
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
