#!/usr/bin/env sh

# FILENAME: master-test.sh
# AUTHOR: Zachary Krepelka
# DATE: Sunday, January 14th, 2024
# CLASS: Introduction to Data Structures
# PROJECT: Lisp Interpreter

for test in test-*.lsp
do java -jar Interpreter.jar $test --color --recover
done | less -Sr
