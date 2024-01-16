; FILENAME: test-free-and-bound.lsp
; AUTHOR: Zachary Krepelka
; DATE: Tuesday, December 12th, 2023
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

( define free 3 )

( print 0 )

( define f ( lambda ( bound ) ( + bound free ) ) )

( assert ( equal? ( f 1 ) 4 ) )
