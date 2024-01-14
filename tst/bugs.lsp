; FILENAME: bugs.lsp
; AUTHOR: Zachary Krepelka
; DATE: Wednesday, January 10th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

	; This file contains unaddressed bugs.

; If a function call ends with a parenthesis tight up against, it crashes.

	( define f ( lambda ( x ) ( + x 1 ) ) )

		( f 1 )   -- works fine

		(f 1 )    -- works fine

		( f 1)    -- crashes

; These crash but should generate internal errors instead.

	( print ( / 1 0 ) )
	( print ( % 12 0 ) )

; Given this expression:

	( print ( length ( read ) ) )

; The following inputs give different results.

	' ( )         ->  0
	( list )      ->  1

	( 1 2 3 )     ->  3
	' ( 1 2 3 )   ->  0

; If the file ends in a comment, the program crashes.
; Try it. Comment out the following statement.

( assert #t )
