; FILENAME: test-errors.lsp
; AUTHOR: Zachary Krepelka
; DATE: Saturday, January 8, 2023
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

	; This file is to demonstrate some of my
	; interpreter's error testing capabilities.

( not ' ( 1 2 3 ) )

( list-ref ' ( 1 2 3 ) 4 )

( cond ( #f 1 ) ( #f 2 ) ( #f 3 ) )

( print z@ch@ry )

( define z@ch@ry #t )

; Keyword '=' is non-recursive.

( = 123 ( list 1 2 3 ) )
