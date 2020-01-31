public final class TemplateBuilder {
	String name;
	String value;

	private TemplateBuilder() {
	}

	public static TemplateBuilder aTemplate() {
		return new TemplateBuilder();
	}

	public TemplateBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public TemplateBuilder withValue(String value) {
		this.value = value;
		return this;
	}

	public Template build() {
		return new Template(name, value);
	}
}
