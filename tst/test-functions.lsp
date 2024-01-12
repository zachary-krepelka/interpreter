; FILENAME: test-functions.lsp
; AUTHOR: Zachary Krepelka
; DATE: Wednesday, January 4, 2023
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

( define factorial
	( lambda ( n )
		( cond
			; base case
			( ( = n 0 ) 1 )

			; recursive step
			( #t ( * n ( factorial ( - n 1 ) ) ) )
		)
	)
)

( assert
	( equal?
		( list
			( factorial 0 )
			( factorial 1 )
			( factorial 2 )
			( factorial 3 )
			( factorial 4 )
			( factorial 5 )
			( factorial 6 )
			( factorial 7 )
			( factorial 8 )
			( factorial 9 )
			( factorial 10 )
			( factorial 11 )
			( factorial 12 )
		)
		( list
			1
			1
			2
			6
			24
			120
			720
			5040
			40320
			362880
			3628800
			39916800
			479001600
		)
	)
)

( define power-of-two
	( lambda ( n )
		( cond
			; base case
			( ( = n 0 ) 1 )

			; recursive step
			( #t ( * 2 ( power-of-two ( - n 1 ) ) ) )
		)
	)
)

( assert
	( equal?
		( list
			( power-of-two 0 )
			( power-of-two 1 )
			( power-of-two 2 )
			( power-of-two 3 )
			( power-of-two 4 )
			( power-of-two 5 )
			( power-of-two 6 )
			( power-of-two 7 )
			( power-of-two 8 )
			( power-of-two 9 )
			( power-of-two 10 )
			( power-of-two 11 )
			( power-of-two 12 )
		)
		( list 1 2 4 8 16 32 64 128 256 512 1024 2048 4096 )
	)
)

( define triangular-number
	( lambda ( n )
		( cond
			; base case
			( ( = n 0 ) 0 )

			; recursive step
			( #t ( + n ( triangular-number ( - n 1 ) ) ) )
		 )
	)
)

( assert
	( equal?
		( list
			( triangular-number 0 )
			( triangular-number 1 )
			( triangular-number 2 )
			( triangular-number 3 )
			( triangular-number 4 )
			( triangular-number 5 )
			( triangular-number 6 )
			( triangular-number 7 )
			( triangular-number 8 )
			( triangular-number 9 )
			( triangular-number 10 )
			( triangular-number 11 )
			( triangular-number 12 )
		)
		( list 0 1 3 6 10 15 21 28 36 45 55 66 78 )
	)
)

( define fibonacci
	( lambda ( n )
		( cond
			; base case
			( ( <= n 1 ) n )

			; recursive step
			( #t
				( +
					( fibonacci ( - n 1 ) )
					( fibonacci ( - n 2 ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( list
			( fibonacci 0 )
			( fibonacci 1 )
			( fibonacci 2 )
			( fibonacci 3 )
			( fibonacci 4 )
			( fibonacci 5 )
			( fibonacci 6 )
			( fibonacci 7 )
			( fibonacci 8 )
			( fibonacci 9 )
			( fibonacci 10 )
			( fibonacci 11 )
			( fibonacci 12 )
		)
		( list 0 1 1 2 3 5 8 13 21 34 55 89 144 )
	)
)

( define absolute-value
	( lambda ( x )
		( cond
			( ( >= x 0 ) x )
			( #t ( - 0 x ) )
		)
	)
)

( assert ( equal? ( absolute-value 0 ) 0 ) )
( assert ( equal? ( absolute-value 1 ) 1 ) )
( assert ( equal? ( absolute-value -1 ) 1 ) )

( define reverse
	( lambda ( L )
		( cond
			; base case
			( ( null? L ) L )

			; recursive step
			( #t
				( append
					( reverse ( cdr L ) )
					( list ( car L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( reverse ( list 1 2 3 4 5 6 7 8 9 ) )
		( list 9 8 7 6 5 4 3 2 1 )
	)
)

( assert
	( equal?
		( reverse ( list ( list 1 2 3 ) 2 3 4 5 6 7 8 9 ) )
		( list 9 8 7 6 5 4 3 2 ( list 1 2 3 ) )
	)
)

( define flatten
	( lambda ( L )
		( cond
			( ( null? L ) L )
			( ( not ( list? L ) ) ( list L ) )
			( #t
				( append
					( flatten ( car L ) )
					( flatten ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( flatten ( list ( list 1 ) ( list 2 ) ( list 3 ) ) )
		( list 1 2 3 )
	)
)

( assert
	( equal?
		( flatten ( list ( list ) ( list ) ( list ) ) )
		( list )
	)
)

( define add
	( lambda ( a b )
		( cond
			( ( zero? a ) b )
			( ( zero? b ) a )

			( ( and ( > a 0 ) ( > b 0 ) )

				; adds two negatives recursively
				( + 2 ( add ( - a 1 ) ( - b 1 ) ) ) )

			( ( and ( < a 0 ) ( < b 0 ) )

				; adds two positives recursively
				( - ( add ( + a 1 ) ( + b 1 ) ) 2 ) )

			( ( and ( > a 0 ) ( < b 0 ) )

				; adds a positive to a negative recursively
				( add ( - a 1 ) ( + b 1 ) ) )

			( ( and ( < a 0 ) ( > b 0 ) ) ; #t would also work

				; adds a negative to a positive recursively
				( add ( + a 1 ) ( - b 1 ) ) )
		)
	)
)

( assert ( equal? ( add  43  72 )  115 ) )
( assert ( equal? ( add  43 -72 )  -29 ) )
( assert ( equal? ( add -43  72 )   29 ) )
( assert ( equal? ( add -43 -72 ) -115 ) )

( define len ; because 'length' is already taken
	( lambda ( L )
		( cond
			( ( null? L ) 0 )
			( #t ( + 1 ( len ( cdr L ) ) ) )
		)
	)
)

( assert ( equal? ( len ( list ) ) 0 ) )
( assert ( equal? ( len ( list 1 ) ) 1 ) )
( assert ( equal? ( len ( list 1 2 ) ) 2 ) )
( assert ( equal? ( len ( list 1 2 3 ) ) 3 ) )
( assert ( equal? ( len ( list 1 2 3 4 ) ) 4 ) )
( assert ( equal? ( len ( list 1 2 3 4 5 ) ) 5 ) )
( assert ( equal? ( len ( list 1 2 3 4 5 6 ) ) 6 ) )
( assert ( equal? ( len ( list 1 2 3 4 5 6 7 ) ) 7 ) )
( assert ( equal? ( len ( list 1 2 3 4 5 6 7 8 ) ) 8 ) )
( assert ( equal? ( len ( list 1 2 3 4 5 6 7 8 9 ) ) 9 ) )
( assert ( equal? ( len ( list ( list ) ( list ) ( list ) ) ) 3 ) )

( define hasTarget
	( lambda ( target L )
		( cond
			( ( null? L ) #f )
			( ( = target ( car L ) ) #t )
			( #t ( hasTarget target ( cdr L ) ) )
		)
	)
)

( assert ( hasTarget 3 ( list 1 2 3 ) ) )
( assert ( not ( hasTarget 4 ( list 1 2 3 ) ) ) )

( define insert
	( lambda ( number L ) ; L is a *sorted* list of numbers
		( cond

			( ( null? L ) ( list number ) )

			( ( <= number ( car L ) ) ( cons number L ) )

			( ( > number ( car L ) )
				( cons
					( car L )
					( insert number ( cdr L ) ) ) )
		)
	)
)

( assert
	( equal?
		( insert 5 ( list 1 2 3 4 6 7 8 9  ) )
		( list 1 2 3 4 5 6 7 8 9 )
	)
)

( define insertion-sort
	( lambda ( L )
		( cond
			( ( null? L ) L )
			( #t
				( insert ; defined above
					( car L )
					( insertion-sort ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( insertion-sort ( list 3 1 2 5 4 ) )
		( list 1 2 3 4 5 )
	)
)

( define max
	( lambda ( L ) ; L is a list of positive numbers
		( cond
			( ( null? L ) 0 )
			( ( > ( car L ) ( max ( cdr L ) ) ) ( car L ) )
			( #t ( max ( cdr L ) ) )
		)
	)
)

( assert
	( equal?
		( max ( list 2 5 9 3 12 10 4 6 7 1 11 8 ) )
		12
	)
)

( define doubleListElements
	( lambda ( L ) ; L is a list of numbers
		( cond
			( ( null? L ) L )
			( #t
				( cons
					( * 2 ( car L ) )
					( doubleListElements ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( doubleListElements ( list 1 2 3 ) )
		( list 2 4 6 )
	)
)

( define removeEvens
	( lambda ( L ) ; L is a list of numbers
		( cond
			; base case
			( ( null? L ) L )

			; recursive steps

			( ( even? ( car L ) )
				( removeEvens ( cdr L ) ) )

			( #t
				( append
					( list ( car L ) )
					( removeEvens ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( removeEvens ( list 1 2 3 4 ) )
		( list 1 3 )
	)
)

( define removeOdds
	( lambda ( L ) ; L is a list of numbers
		( cond
			; base case
			( ( null? L ) L )

			; recursive steps

			( ( odd? ( car L ) )
				( removeOdds ( cdr L ) ) )
			( #t
				( append
					( list ( car L ) )
					( removeOdds ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( removeOdds ( list 1 2 3 4 ) )
		( list 2 4 )
	)
)

( define countEvens
	( lambda ( L ) ; L is a list of numbers
		( cond
			; base case
			( ( null? L ) 0 )

			; recursive steps

			( ( even? ( car L ) )
				( + 1 ( countEvens ( cdr L ) ) ) )

			( #t
				( countEvens ( cdr L ) )
			)
		)
	)
)

( assert
	( equal?
		( countEvens ( list 1 2 3 4 5 6 7 8 9 ) )
		4
	)
)

( define countOdds
	( lambda ( L ) ; L is a list of numbers
		( cond
			; base case
			( ( null? L ) 0 )

			; recursive steps

			( ( odd? ( car L ) )
				( + 1 ( countOdds ( cdr L ) ) ) )

			( #t
				( countOdds ( cdr L ) )
			)
		)
	)
)

( assert
	( equal?
		( countOdds ( list 1 2 3 4 5 6 7 8 9 ) )
		5
	)
)

( define removeNegatives
	( lambda ( L )
		( cond
			; base case
			( ( null? L ) L )

			; recursive steps

			( ( > 0 ( car L ) )
				( removeNegatives ( cdr L ) ) )

			( #t
				( cons
					( car L )
					( removeNegatives ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( removeNegatives ( list -3 -2 -1 0 1 2 3 ) )
		( list 0 1 2 3 )
	)
)

( define removePositives
	( lambda ( L )
		( cond
			; base case
			( ( null? L ) L )

			; recursive steps

			( ( < 0 ( car L ) )
				( removePositives ( cdr L ) ) )

			( #t
				( cons
					( car L )
					( removePositives ( cdr L ) )
				)
			)
		)
	)
)

( assert
	( equal?
		( removePositives ( list -3 -2 -1 0 1 2 3 ) )
		( list -3 -2 -1 0 )
	)
)

( define mystery-function
	( lambda ( x y )
		( append
			( append x y )
			( append y x )
		)
	)
)


( assert
	( equal?
		( mystery-function
			( list 1 2 3 )
			( list 4 5 6 )
		)
		( list 1 2 3 4 5 6 4 5 6 1 2 3 )
	)
)

( assert
	( equal?
		( mystery-function ( list 1 ) ( list 2 ) )
		( list 1 2 2 1 )
	)
)

; Sanity check:

	( define number-of-ways-to-arrange-a-deck-of-cards ( factorial 52 ) )

	( print number-of-ways-to-arrange-a-deck-of-cards )

( memory )

( assert #t )
