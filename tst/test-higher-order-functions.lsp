; FILENAME: test-higher-order-function.lsp
; AUTHOR: Zachary Krepelka
; DATE: Saturday, January 13th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

	; Currently doesn't work
	; Need to add support for this

( define inc ( lambda ( x ) ( + x 1 ) ) )
( define dec ( lambda ( x ) ( - x 1 ) ) )

( define f ( lambda ( g ) ( g 3 )  ) )

( assert ( equal? ( g inc ) 4 ) )
( assert ( equal? ( g dec ) 2 ) )
