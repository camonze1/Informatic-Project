
public enum CommandWord {
	HELP("help"), MANUAL("manual"), LOOK("look"), ITEMS("items"), GO("go"), BACK("back"), TAKE("take"), DROP("drop"), QUIT("quit"), UNKNOWN(null);

	private String commandString;

	CommandWord(String commandString) {
		this.commandString = commandString;
	}

	public String toString(){
		return commandString;
	}


}
