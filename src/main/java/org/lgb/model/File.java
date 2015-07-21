package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "file")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "file")
	private Set<Content> contents = new HashSet<Content>(0);

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "uuid", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "name")
	private String name;

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

	@JsonProperty("versions")
	public Set<Content> getContents() {
		return this.contents;
	}

	public Content addContent(InputStream input) throws FileNotFoundException, IOException {
		Content content = new Content(this);
		content.saveFile(input);
		return content;
	}
}
