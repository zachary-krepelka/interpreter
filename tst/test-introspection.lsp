; FILENAME: test-introspection.lsp
; AUTHOR: Zachary Krepelka
; DATE: Thursday, January 11th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

( tree 1 )

( tree #f )

( tree #t )

( tree ( list 1 ) )
( tree ( list 1 2 ) )
( tree ( list 1 2 3 ) )

; truth table

( tree
	( list
		( list #f #f )
		( list #f #t )
		( list #t #f )
		( list #t #t )
	)
)

( define f ( lambda ( x ) ( + x 1 ) ) )

( define x 1 )

( memory )

( reset )

( memory )
