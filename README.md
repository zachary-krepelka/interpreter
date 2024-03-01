
<!--
	FILENAME: README.md
	AUTHOR: Zachary Krepelka
	DATE: Friday, February 9th, 2024
-->

# Lisp Interpreter

A lisp interpreter in java.  This was my data structures project from a year
ago.  I'm refactoring it and writing documentation.  It's still a work in
progress.  Examples of supported functionality will be found in the `tst`
folder.

## Usage

To start a REPL:

```shell
$ java -jar Interpreter.jar [OPTIONS]
```

To execute a file:

```shell
$ java -jar Interpreter.jar [FILE] [OPTIONS]
```

## Options

| flag        | functionality                |
| ----------- | ---------------------------- |
| `--log`     | Prints diagnostic info       |
| `--color`   | ANSI Escape Sequences        |
| `--recover` | Tries to recover from errors |
