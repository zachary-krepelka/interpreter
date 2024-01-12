; FILENAME: not-working.lsp
; AUTHOR: Zachary Krepelka
; DATE: Friday, January 13th, 2023
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

	; This file contains functions that do *not* work with my interpreter.

( define splitIntoOddsAndEvens
	( lambda ( L result ) ; result is initially ' ( ( ) ( ) )
		( cond
			; base case
			( ( null? L ) result )

			; recursive steps

			( ( odd? ( car L ) )
				( splitIntoOddsAndEvens
					( cdr L )
					( list
						( cons
							( car L )
							( car result )
						)
						( car ( cdr result ) )
					)
				)
			)

			( #t ; even
				( splitIntoOddsAndEvens
					( cdr L )
					( list
						( car result )
						( cons
							( car L )
							( car ( cdr result ) )
						)
					)
				)
			)
		)
	)
)

( print
	( splitIntoOddsAndEvens
		( list 1 2 3 4 5 6 7 8 9 )
		( list ( list ) ( list ) )
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

( define mergeSortedLists
	( lambda ( L-I L-II )
		( cond
			; base case
			( ( null? L-I ) L-II )
			( ( null? L-II ) L-I )

			; recursive steps

			( ( <= ( car L-I ) ( car L-II ) )
				( cons
					( car L-I )
					( mergeSortedLists L-II ( cdr L-I ) )
				)
			)

			( #t
				( cons
					( car L-II )
					( mergeSortedLists L-I ( cdr L-II ) )
				)
			)
		)
	)
)

( print
	( mergeSortedLists
		( list 1 2 3 )
		( list 4 5 6 )
	)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

( define alternatingSplit

	( lambda ( L parity result ) ; result is initially ' ( ( ) ( ) )

	; The parameter 'parity' is a boolean, either true or false.
	; Courtesy of Merriam-Webster, parity is "the property of an
	; integer with respect to being odd or even." Here, parity
	; determines the order the resulting lists:

		; ( alternatingSplit
		; 	( list A B C D E F ) #t
		; 	( list ( list ) ( list ) )
		; )

			; --> ( ( E C A ) ( F D B ) )

		; ( alternatingSplit
		; 	( list A B C D E F ) #f
		; 	( list ( list ) ( list ) )
		; )

			; --> ( ( F D B ) ( E C A ) )

		( cond
			; base case
			( ( null? L ) result )

			; recursive steps

			( parity
				( alternatingSplit
					( cdr L )
					( not parity )
					( list
						( cons
							( car L )
							( car result )
						)
						( car ( cdr result ) )
					)
				)
			)

			( #t
				( alternatingSplit
					( cdr L )
					( not parity )
					( list
						( car result )
						( cons
							( car L )
							( car ( cdr result ) )
						)
					)
				)
			)
		)
	)
)

( print
	( alternatingSplit
		( list 1 2 3 4 5 6 7 8 9 )
		#t
		( list ( list ) ( list ) )
	)
)
