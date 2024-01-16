// FILENAME: ANSI.java
// AUTHOR: Zachary Krepelka
// DATE: Saturday, October 9, 2021
// UPDATED: Monday, January 15th, 2024   11:34 PM

public class ANSI {

	public static final int

		BLACK   = 30,
		RED     = 31,
		GREEN   = 32,
		YELLOW  = 33,
		BLUE    = 34,
		MAGENTA = 35,
		CYAN    = 36,
		WHITE   = 37;

	public static final boolean

		// Plane
		FOREGROUND = true,
		BACKGROUND = false,

		// Intensity
		BRIGHT = true,
		DULL   = false;

	private        boolean  localToggle = true;
	private static boolean globalToggle = true;

	public        void enable()   { localToggle = true; }
	public static void powerOn()  {globalToggle = true; }
	public        void disable()  { localToggle = false;}
	public static void powerOff() {globalToggle = false;}

	public String colorize(

			boolean plane,     // forground or background
			boolean intensity, // bright or dull
			int color,         // one of our constants
			String str         // the string to colorize
	) {

		if (!localToggle || !globalToggle) return str;

		if (plane == BACKGROUND) color += 10;

		if (intensity == BRIGHT) color += 60;

		return "\033[" + color + "m" + str + "\033[0m";

	} // method

	// NOTE:
	// https//english.stackexchange.com/a/123245

} // class
