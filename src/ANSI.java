// FILENAME: ANSI.java
// AUTHOR: Zachary Krepelka
// DATE: Saturday, October 9, 2021

	// needs SERIOUS refactoring
	// wrote this when I was first learning

public class ANSI {

	private static boolean globalToggle = true;

	private boolean localToggle;

	private String foreground, background;

	private static String[] colors = {

			"black",
			"red",
			"green",
			"yellow",
			"blue",
			"magenta",
			"cyan",
			"white",
			"brightBlack",
			"brightRed",
			"brightGreen",
			"brightYellow",
			"brightBlue",
			"brightMagenta",
			"brihtCyan",
			"brightWhite"};

	public ANSI() {localToggle = true;}
	public ANSI(boolean input) {localToggle = input;}

	public ANSI(
		boolean toggle,
		String foreground,
		String background
	) {
		localToggle = toggle;
		this.foreground = foreground;
		this.background = background;

	} // constructor

	//Getters
	public boolean getLocalState() {return localToggle;}
	public static boolean getGlobalState() {return globalToggle;}
	public static String[] getColors() {return colors;}

	//Setters
	public void setForeground(String color) {foreground = color;}
	public void setBackground(String color) {background = color;}

	public void setLocalState(boolean input) {localToggle = input;}
	public static void setGlobalState(boolean input) {globalToggle = input;}

	public static void turnOnGlobally() {globalToggle = true;}
	public void turnOnLocally() {localToggle = true;}

	public static void turnOffGlobally() {globalToggle = false;}
	public void turnOffLocally() {localToggle = false;}

	public String colorize(String str) {

		str = colorizeFG(str, foreground);
		str = colorizeBG(str, background);

		return str;

	} //method

	public String colorize(

		String str,
		String foreground,
		String background
	) {

		str = colorizeFG(str, foreground);
		str = colorizeBG(str, background);

		return str;

	} //method

	public String colorizeFG(String str, String color) {

		if (color == null) return str;

		switch (color) {

			case "black"         :return blackFG         (str);
			case "brightBlack"   :return brightBlackFG   (str);
			case "red"           :return redFG           (str);
			case "brightRed"     :return brightRedFG     (str);
			case "green"         :return greenFG         (str);
			case "brightGreen"   :return brightGreenFG   (str);
			case "yellow"        :return yellowFG        (str);
			case "brightYellow"  :return brightYellowFG  (str);
			case "blue"          :return blueFG          (str);
			case "brightBlue"    :return brightBlueFG    (str);
			case "magenta"       :return magentaFG       (str);
			case "brightMagenta" :return brightMagentaFG (str);
			case "cyan"          :return cyanFG          (str);
			case "brightCyan"    :return brightCyanFG    (str);
			case "white"         :return whiteFG         (str);
			case "brightWhite"   :return brightWhiteFG   (str);

			default: return str;

		} // switch

	} // method

	public String colorizeBG(String str, String color) {

		if (color == null) return str;

		switch (color) {

			case "black"         :return blackBG         (str);
			case "brightBlack"   :return brightBlackBG   (str);
			case "red"           :return redBG           (str);
			case "brightRed"     :return brightRedBG     (str);
			case "green"         :return greenBG         (str);
			case "brightGreen"   :return brightGreenBG   (str);
			case "yellow"        :return yellowBG        (str);
			case "brightYellow"  :return brightYellowBG  (str);
			case "blue"          :return blueBG          (str);
			case "brightBlue"    :return brightBlueBG    (str);
			case "magenta"       :return magentaBG       (str);
			case "brightMagenta" :return brightMagentaBG (str);
			case "cyan"          :return cyanBG          (str);
			case "brightCyan"    :return brightCyanBG    (str);
			case "white"         :return whiteBG         (str);
			case "brightWhite"   :return brightWhiteBG   (str);

			default: return str;

		} // switch

	} // method

	public String blackFG(String str) {

		if (localToggle && globalToggle)
			return "\033[30m" + str + "\033[0m";

		return str;

	} // method

	public String brightBlackFG(String str) {

		if (localToggle && globalToggle)
			return "\033[90m" + str + "\033[0m";

		return str;

	} //method

	public String blackBG(String str) {

		if (localToggle && globalToggle)
			return "\033[40m"  + str + "\033[0m";

		return str;

	} //method

	public String brightBlackBG(String str) {

		if (localToggle && globalToggle)
			return "\033[100m"  + str + "\033[0m";

		return str;

	} //method

	public String redFG(String str) {

		if (localToggle && globalToggle)
			return "\033[31m" + str + "\033[0m";

		return str;

	} //method

	public String brightRedFG(String str) {

		if (localToggle && globalToggle)
			return "\033[91m" + str + "\033[0m";

		return str;

	} //method

	public String redBG(String str) {

		if (localToggle && globalToggle)
			return "\033[41m"  + str + "\033[0m";

		return str;

	} //method

	public String brightRedBG(String str) {

		if (localToggle && globalToggle)
			return "\033[101m"  + str + "\033[0m";

		return str;

	} //method

	public String greenFG(String str) {

		if (localToggle && globalToggle)
			return "\033[32m" + str + "\033[0m";

		return str;

	} //method

	public String brightGreenFG(String str) {

		if (localToggle && globalToggle)
			return "\033[92m" + str + "\033[0m";

		return str;

	} //method

	public String greenBG(String str) {

		if (localToggle && globalToggle)
			return "\033[42m"  + str + "\033[0m";

		return str;

	} //method

	public String brightGreenBG(String str) {

		if (localToggle && globalToggle)
			return "\033[102m"  + str + "\033[0m";

		return str;

	} //method

	public String yellowFG(String str) {

		if (localToggle && globalToggle)
			return "\033[33m" + str + "\033[0m";

		return str;

	} //method

	public String brightYellowFG(String str) {

		if (localToggle && globalToggle)
			return "\033[93m" + str + "\033[0m";

		return str;

	} //method

	public String yellowBG(String str) {

		if (localToggle && globalToggle)
			return "\033[43m"  + str + "\033[0m";

		return str;

	} //method

	public String brightYellowBG(String str) {

		if (localToggle && globalToggle)
			return "\033[103m"  + str + "\033[0m";

		return str;

	} //method

	public String blueFG(String str) {

		if (localToggle && globalToggle)
			return "\033[34m" + str + "\033[0m";

		return str;

	} //method

	public String brightBlueFG(String str) {

		if (localToggle && globalToggle)
			return "\033[94m" + str + "\033[0m";

		return str;

	} //method

	public String blueBG(String str) {

		if (localToggle && globalToggle)
			return "\033[44m"  + str + "\033[0m";

		return str;

	} //method

	public String brightBlueBG(String str) {

		if (localToggle && globalToggle)
			return "\033[104m"  + str + "\033[0m";

		return str;

	} //method

	public String magentaFG(String str) {

		if (localToggle && globalToggle)
			return "\033[35m" + str + "\033[0m";

		return str;

	} //method

	public String brightMagentaFG(String str) {

		if (localToggle && globalToggle)
			return "\033[95m" + str + "\033[0m";

		return str;

	} //method

	public String magentaBG(String str) {

		if (localToggle && globalToggle)
			return "\033[45m"  + str + "\033[0m";

		return str;

	} //method

	public String brightMagentaBG(String str) {

		if (localToggle && globalToggle)
			return "\033[105m"  + str + "\033[0m";

		return str;

	} //method

	public String cyanFG(String str) {

		if (localToggle && globalToggle)
			return "\033[36m" + str + "\033[0m";

		return str;

	} //method

	public String brightCyanFG(String str) {

		if (localToggle && globalToggle)
			return "\033[96m" + str + "\033[0m";

		return str;

	} //method

	public String cyanBG(String str) {

		if (localToggle && globalToggle)
			return "\033[46m"  + str + "\033[0m";

		return str;

	} //method

	public String brightCyanBG(String str) {

		if (localToggle && globalToggle)
			return "\033[106m"  + str + "\033[0m";

		return str;

	} //method

	public String whiteFG(String str) {

		if (localToggle && globalToggle)
			return "\033[37m" + str + "\033[0m";

		return str;

	} //method

	public String brightWhiteFG(String str) {

		if (localToggle && globalToggle)
			return "\033[97m" + str + "\033[0m";

		return str;

	} //method

	public String whiteBG(String str) {

		if (localToggle && globalToggle)
			return "\033[47m"  + str + "\033[0m";

		return str;

	} //method

	public String brightWhiteBG(String str) {

		if (localToggle && globalToggle)
			return "\033[107m"  + str + "\033[0m";

		return str;

	} //method

	public String eightbitFG(int code, String str) {

		if (localToggle && globalToggle && (code >= 0 && code <= 255))
			return "\033[38;5;" + code + "m" + str + "\033[0m";

		return str;

	} //method

	public String eightbitBG(int code, String str) {

		if (localToggle && globalToggle && (code >= 0 && code <= 255))
			return "\033[48;5;" + code + "m" + str + "\033[0m";

		return str;

	} //method

	public static boolean isValidRGB(int r, int g, int b) {

		return
			(r >= 0 && r <= 255) &&
			(g >= 0 && g <= 255) &&
			(b >= 0 && b <= 255);

	} // method

	public String RGB_FG(int r, int g, int b, String str) {

		if (localToggle && globalToggle && isValidRGB(r,g,b))

			return
				"\033[38;2;" +
				r + ";" + g + ";" + b + "m" +
				str + "\033[0m";

		return str;

	} //method

	public String RGB_BG(int r, int g, int b, String str) {

		if (localToggle && globalToggle && isValidRGB(r,g,b))

			return
				"\033[48;2;" +
				r + ";" + g + ";" + b + "m" +
				str + "\033[0m";

		return str;

	} //method

	public String bold(String str) {

		if (localToggle && globalToggle)
			return "\033[1m" + str + "\033[0m";

		return str;

	} //method

	public String dim(String str) {

		if (localToggle && globalToggle)
			return  "\033[2m" + str + "\033[0m";

		return str;

	} //method

	public String italicize(String str) {

		if (localToggle && globalToggle)
			return  "\033[3m" + str + "\033[0m";

		return str;

	} //method

	public String underline(String str) {

		if (localToggle && globalToggle)
			return "\033[4m" + str + "\033[0m";

		return str;

	} //method

	public String reverse(String str) {

		if (localToggle && globalToggle)
			return "\033[7m" + str + "\033[0m";

		return str;

	} //method

	public String titleize(String str) {

		if (localToggle && globalToggle)
			return bold(reverse(str));

		return str;

	} //method

	public void clr() {

		System.out.print("\033[H\033[2J");
		System.out.flush();

	} //method

	public void reset() {

		System.out.print("\033c");

	} //method

} //class
