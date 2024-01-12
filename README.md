# Lisp Interpreter

This was my data structures project from a year ago.  I'm refactoring it and
writing documentation.  It's still a work in progress.  To start a REPL, use the
command `java -jar Interpreter.jar [OPTIONS]`.  To execute a file, use the
command `java -jar Interpreter.jar [FILE] [OPTIONS]`. Examples of supported
functionality will be found in the `tst` folder.

# Options

| flag        | functionality                |
| ----------- | ---------------------------- |
| `--log`     | Prints diagnostic info       |
| `--color`   | ANSI Escape Sequences        |
| `--recover` | Tries to recover from errors |
