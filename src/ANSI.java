// FILENAME: ANSI.java
// AUTHOR: Zachary Krepelka
// DATE: Saturday, October 9, 2021
// UPDATED: Tuesday, January 16th, 2024   12:42 AM

public class ANSI {

	public static final int

	// Colors

		BLACK   = 0,
		RED     = 1,
		GREEN   = 2,
		YELLOW  = 3,
		BLUE    = 4,
		MAGENTA = 5,
		CYAN    = 6,
		WHITE   = 7,

	// Ground

		FOREGROUND = 0,
		BACKGROUND = 1,

	// Intensity

		BRIGHT = 1,
		DULL   = 0;

	private        boolean  localToggle = true;
	private static boolean globalToggle = true;

	public        void enable()   { localToggle = true; }
	public static void powerOn()  {globalToggle = true; }
	public        void disable()  { localToggle = false;}
	public static void powerOff() {globalToggle = false;}

	public String colorize(

			int ground,     // forground or background
			int intensity,  // bright or dull
			int color,      // one of our eight constants
			String str      // the string to colorize
	) {

		if (!localToggle || !globalToggle) return str;

		color += 30 + 10 * ground + 60 * intensity;

		return "\033[" + color + "m" + str + "\033[0m";

	} // method

} // class
