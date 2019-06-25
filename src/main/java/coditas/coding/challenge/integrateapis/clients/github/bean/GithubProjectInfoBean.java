package coditas.coding.challenge.integrateapis.clients.github.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubProjectInfoBean implements Serializable {
	
	private Long id;
	
	@JsonProperty("full_name")
	private String repoName;
	private String language;
	private String description;
	private boolean fork;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}
	

	@Override
	public String toString() {
		return "ProjectInfoBean{" +
				"id='" + id + '\'' +
				", fullName='" + repoName + '\'' +
				", language='" + language + '\'' +
				", description='" + description + '\'' +
				", fork=" + fork +
				'}';
	}
}
