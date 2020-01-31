public class TemplateExtended implements ITemplate {

	private ITemplate iTemplate;

	public TemplateExtended(ITemplate iTemplate){
		this.iTemplate = iTemplate;
	}

	@Override
	public void showContent() {
		System.out.println("This is extended content of template");
		this.iTemplate.showContent();
		System.out.println("Decorator is here");
	}
}
