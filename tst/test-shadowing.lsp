; FILENAME: test-shadowing.lsp
; AUTHOR: Zachary Krepelka
; DATE: Wednesday, January 10th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

( define x 1 )

( define f

	( lambda ( x )

		( + x 0 )

	)
)

( assert ( equal? x 1 ) )

( assert ( equal? ( f 2 ) 2 ) )
