public class Template implements ITemplate {

	String name;
	String value;

	private static Template instance = null;

	//Lazy loading
	public static Template getInstance() {
		if (instance == null) {
			instance = new Template();
		}
		return instance;
	}
	private Template() {}


	public Template(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void showContent() {
		System.out.println("This is simple template");
	}


}

