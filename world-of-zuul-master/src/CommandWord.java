
public enum CommandWord {
	GO("go"), QUIT("quit"), HELP("help"), UNKNOWN(null), LOOK("look"), BACK("back"), TAKE("take"), DROP("drop"), ITEMS("items"), FINISH("finish");

	private String commandString;

	CommandWord(String commandString) {
		this.commandString = commandString;
	}

	public String toString(){
		return commandString;
	}
}
