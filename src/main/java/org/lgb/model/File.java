package org.lgb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Entity
@Table(name = "file")
public class File implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "file_name")
	private String file_name;

	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	@JsonProperty("name")
	public String getFileName() {
		return file_name;
	}

	public void setFileName(String value) {
		this.file_name = value;
	}

	public void saveFile(InputStream input) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[8 * 1024];

		try {
			OutputStream output = new FileOutputStream("/tmp/"+this.getFileName());
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
