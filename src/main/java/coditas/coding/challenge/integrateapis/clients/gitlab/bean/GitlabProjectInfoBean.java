package coditas.coding.challenge.integrateapis.clients.gitlab.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitlabProjectInfoBean {

	private String id;
	
	private String description;

	@JsonProperty("name")
	private String projectName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "GitlabProjectInfoBean{" +
				"id='" + id + '\'' +
				", description='" + description + '\'' +
				", projectName='" + projectName + '\'' +
				'}';
	}
}
