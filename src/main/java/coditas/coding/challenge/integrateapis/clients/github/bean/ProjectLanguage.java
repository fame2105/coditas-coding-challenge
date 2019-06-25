package coditas.coding.challenge.integrateapis.clients.github.bean;

import java.io.Serializable;

public enum ProjectLanguage implements Serializable {
	C("C"),
	JAVA("Java"),
	JAVASCRIPT("JavaScript"),
	RUBY("Ruby"),
	PYTHON("Python"),
	CSS("CSS"),
	SHELL("Shell"),
	GO("Go"),
	SCALA("Scala"),
	ERLANG("Erlang");

	private String language;

	ProjectLanguage(String language) {

		this.language = language;

	}

	public String getAbbreviation() {
		return this.language;
	}
}
