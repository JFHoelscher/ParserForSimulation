package helloWorld;

/**
 * This class stores information about arguments that are parsed from the command line.
 * 
 * @author johannes
 *
 */

public class CommandLineArgument {
	
	private String description;
	private String value;
	private boolean required;
	
	public CommandLineArgument(String description, boolean required) {
		super();
		this.description = description;
		this.required = required;
		this.value = null;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return this.value != null;
	}
	
	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRequired() {
		return required;
	}
	
}
