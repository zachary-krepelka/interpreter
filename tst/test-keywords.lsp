; FILENAME: test-keywords.lsp
; AUTHOR: Zachary Krepelka
; DATE: Wednesday, January 10th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

; TEST KEYWORD assert

	( assert #t )
	( assert ( not #f ) )

; TEST KEYWORD car

	( assert ( equal? ( car ( list 1 2 3 ) ) 1 ) )
	( assert ( equal? ( car ( list ( list 1 ) 2 3 ) ) ( list 1 ) ) )

; TEST KEYWORD cdr

	( assert ( equal? ( cdr ( list 1 2 3 ) ) ( list 2 3 ) ) )
	( assert ( equal? ( cdr ( list ( list 1 ) 2 3 ) ) ( list 2 3 ) ) )

; TEST KEYWORD null?

	( assert ( null? ( list ) ) )

	( assert ( not ( null? 1 ) ) )
	( assert ( not ( null? #t ) ) )
	( assert ( not ( null? ( list 1 2 3 ) ) ) )

; TEST KEYWORD atom?

	( assert ( atom? #t ) )
	( assert ( atom? #f ) )
	( assert ( atom?  1 ) )
	( assert ( not ( atom? ( list ) ) ) )
	( assert ( not ( atom? ( list 1 2 3 ) ) ) )

; TEST KEYWORD read & print

; TEST KEYWORD =

	( assert ( = 1 1 ) )

	( assert ( = #f #f ) )
	( assert ( = #t #t ) )

	( assert ( not ( = #f #t ) ) )
	( assert ( not ( = #t #f ) ) )

	( assert ( = ( list ) ( list ) ) )

	; This keyword only works with atoms.

; TEST KEYWORD equal?

	( assert ( equal? 1 1 ) )
	( assert ( equal? #f #f ) )
	( assert ( equal? #t #t ) )
	( assert ( not ( equal? #f #t ) ) )
	( assert ( not ( equal? #t #f ) ) )
	( assert ( equal? ( list ) ( list ) ) )

	( assert ( equal? ( list 1 ) ( list 1 ) ) )

	( assert
		( equal?
			( list 1 ( list 2 ( list 3 ) ) )
			( list 1 ( list 2 ( list 3 ) ) )
		)
	)

; TEST KEYWORD list-ref

	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 0 ) 1 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 1 ) 2 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 2 ) 3 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 3 ) 4 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 4 ) 5 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 5 ) 6 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 6 ) 7 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 7 ) 8 ) )
	( assert ( equal? ( list-ref ( list 1 2 3 4 5 6 7 8 9 ) 8 ) 9 ) )

; TEST KEYWORD length

	( assert ( equal? ( length ( list ) ) 0 ) )
	( assert ( equal? ( length ( list 1 ) ) 1 ) )
	( assert ( equal? ( length ( list 1 2 ) ) 2 ) )
	( assert ( equal? ( length ( list 1 2 3 ) ) 3 ) )
	( assert ( equal? ( length ( list 1 2 3 4 ) ) 4 ) )
	( assert ( equal? ( length ( list 1 2 3 4 5 ) ) 5 ) )
	( assert ( equal? ( length ( list 1 2 3 4 5 6 ) ) 6 ) )
	( assert ( equal? ( length ( list 1 2 3 4 5 6 7 ) ) 7 ) )
	( assert ( equal? ( length ( list 1 2 3 4 5 6 7 8 ) ) 8 ) )
	( assert ( equal? ( length ( list 1 2 3 4 5 6 7 8 9 ) ) 9 ) )

	( assert ( equal? ( length ( list ( list ) ( list ) ( list ) ) ) 3 ) )

; TEST KEYWORD cons

	; I chose examples from here:

		; https://medium.com/@aleksandrasays/
		; my-other-car-is-a-cdr-3058e6743c15

	( assert
		( equal?
			( cons 1
				( cons 2
					( cons 3
						( list )
					)
				)
			)
			( list 1 2 3 )
		)
	)

	( assert
		( equal?
			( cons 1 ( list 2 3 4 ) )
			( list 1 2 3 4 )
		)
	)

	( assert
		( equal?
			( cons ( list 1 2 3 ) ( list ) )
			( list ( list 1 2 3 ) )

		)
	)

	( assert
		( equal?
			( cons
				( list 1 2 3 )
				( list 4 )
			)
			( list ( list 1 2 3 ) 4 )
		)
	)

; TEST KEYWORD append

	( assert
		( equal?
			( append
				( list 1 2 3 )
				( list 4 5 6 )
			)
			( list 1 2 3 4 5 6 )
		)
	)

	( assert
		( equal?
			( append
				( list ( list 1 2 ) 3 )
				( list 4 ( list 5 6 ) )
			)
			( list ( list 1 2 ) 3 4 ( list 5 6 ) )
		)
	)

	( assert
		( equal?
			( append
				( list ( list 1 2 ) ( list 3 4 ) )
				( list ( list 5 6 ) ( list 7 8 ) )
			)
			( list
				( list 1 2 )
				( list 3 4 )
				( list 5 6 )
				( list 7 8 )
			)
		)
	)

; TEST KEYWORD list

	; already tested extensively throughout

	; ( print ( list 1 2 3 ) )

	;( print
	;	( list
	;		1
	;		2
	;		3
	;		4
	;	)
	;)

	;( print
	;	( list
	;		( read )
	;		( read )
	;		( read )
	;		( read )
	;		( read )
	;	)
	;)

; TEST KEYWORD list?

	( assert ( list? ( list ) ) )
	( assert ( list? ( list 1 2 3 ) ) )

	( assert ( not ( list? 1 ) ) )
	( assert ( not ( list? #t ) ) )
	( assert ( not ( list? #f ) ) )

; TEST KEYWORD boolean?

	( assert ( boolean? #t ) )
	( assert ( boolean? #f ) )

	( assert ( not ( boolean? 1 ) ) )
	( assert ( not ( boolean? ( list ) ) ) )
	( assert ( not ( boolean? ( list 1 2 3 ) ) ) )

; TEST KEYWORD number?

	( assert ( number? 1 ) )
	( assert ( not ( number? #t ) ) )
	( assert ( not ( number? #f ) ) )
	( assert ( not ( number? ( list ) ) ) )
	( assert ( not ( number? ( list 1 2 3 ) ) ) )

; TEST KEYWORD zero?

	( assert ( zero? 0 ) )

	( assert ( not ( zero? 1 ) ) )
	( assert ( not ( zero? 2 ) ) )
	( assert ( not ( zero? 3 ) ) )

	; EXPECTANCY ERROR:
	; ( assert ( not ( zero? #t ) ) )
	; ( assert ( not ( zero? #f ) ) )
	; ( assert ( not ( zero? ( list ) ) ) )
	; ( assert ( not ( zero? ( list 1 2 3 ) ) ) )

; TEST KEYWORD even?

	( assert ( even? -4 ) )
	( assert ( even? -2 ) )
	( assert ( even?  0 ) )
	( assert ( even?  2 ) )
	( assert ( even?  4 ) )

	( assert ( not ( even? -5 ) ) )
	( assert ( not ( even? -3 ) ) )
	( assert ( not ( even? -1 ) ) )
	( assert ( not ( even?  1 ) ) )
	( assert ( not ( even?  3 ) ) )
	( assert ( not ( even?  5 ) ) )

	; EXPECTANCY ERROR:
	; ( assert ( not ( even? #t ) ) )
	; ( assert ( not ( even? #f ) ) )
	; ( assert ( not ( even? ( list ) ) ) )
	; ( assert ( not ( even? ( list 1 2 3 ) ) ) )

; TEST KEYWORD odd?

	( assert ( odd? -5 ) )
	( assert ( odd? -3 ) )
	( assert ( odd? -1 ) )
	( assert ( odd?  1 ) )
	( assert ( odd?  3 ) )
	( assert ( odd?  5 ) )

	( assert ( not ( odd? -4 ) ) )
	( assert ( not ( odd? -2 ) ) )
	( assert ( not ( odd?  0 ) ) )
	( assert ( not ( odd?  2 ) ) )
	( assert ( not ( odd?  4 ) ) )

	; EXPECTANCY ERROR:
	; ( assert ( not ( odd? #t ) ) )
	; ( assert ( not ( odd? #f ) ) )
	; ( assert ( not ( odd? ( list ) ) ) )
	; ( assert ( not ( odd? ( list 1 2 3 ) ) ) )

; TEST KEYWORD and

	( assert ( equal? ( and #f #f ) #f ) )
	( assert ( equal? ( and #f #t ) #f ) )
	( assert ( equal? ( and #t #f ) #f ) )
	( assert ( equal? ( and #t #t ) #t ) )

; TEST KEYWORD or

	( assert ( equal? ( or #f #f ) #f ) )
	( assert ( equal? ( or #f #t ) #t ) )
	( assert ( equal? ( or #t #f ) #t ) )
	( assert ( equal? ( or #t #t ) #t ) )

; TEST KEYWORD not

	; already tested extensively throughout

; TEST KEYWORD *

	( assert ( equal? ( * 2 2 ) 4 ) )

; TEST KEYWORD +

	( assert ( equal? ( + 2 2 ) 4 ) )

; TEST KEYWORD -

	( assert ( equal? ( - 2 2 ) 0 ) )

; TEST KEYWORD /

	( assert ( equal? ( / 2 2 ) 1 ) )

; TEST KEYWORD %

	( equal? ( % 12  1 )  0 )
	( equal? ( % 12  2 )  0 )
	( equal? ( % 12  3 )  0 )
	( equal? ( % 12  4 )  0 )
	( equal? ( % 12  5 )  2 )
	( equal? ( % 12  6 )  0 )
	( equal? ( % 12  7 )  5 )
	( equal? ( % 12  8 )  4 )
	( equal? ( % 12  9 )  8 )
	( equal? ( % 12 10 )  2 )
	( equal? ( % 12 11 )  1 )
	( equal? ( % 12 12 )  0 )
	( equal? ( % 12 13 ) 12 )

	; ( equal? ( % 12  -1 )   0 )
	; ( equal? ( % 12  -2 )   0 )
	; ( equal? ( % 12  -3 )   0 )
	; ( equal? ( % 12  -4 )   0 )
	; ( equal? ( % 12  -5 )  -3 )
	; ( equal? ( % 12  -6 )   0 )
	; ( equal? ( % 12  -7 )  -2 )
	; ( equal? ( % 12  -8 )  -4 )
	; ( equal? ( % 12  -9 )  -6 )
	; ( equal? ( % 12 -10 )  -8 )
	; ( equal? ( % 12 -11 ) -10 )
	; ( equal? ( % 12 -12 )   0 )
	; ( equal? ( % 12 -13 )  -1 )

	; Gives java.lang.ArtihmeticException: BigInteger: modulus not positive

; TEST KEYWORD ^

	( assert ( equal? ( ^ 2 0 ) 1 ) )
	( assert ( equal? ( ^ 2 1 ) 2 ) )
	( assert ( equal? ( ^ 2 2 ) 4 ) )
	( assert ( equal? ( ^ 2 3 ) 8 ) )
	( assert ( equal? ( ^ 2 4 ) 16 ) )
	( assert ( equal? ( ^ 2 5 ) 32 ) )
	( assert ( equal? ( ^ 2 6 ) 64 ) )

	; ( print ( ^ 2 -1 ) )
	; ( print ( ^ 2 -2 ) )
	; ( print ( ^ 2 -3 ) )
	; ( print ( ^ 2 -4 ) )

	; Gives java.lang.ArithmeticException: Negative exponent

; TEST KEYWORD copy

	( assert
	  	( equal?
			( list 1 ( list 2 ( list 3 ) ) )
			( copy ( list 1 ( list 2 ( list 3 ) ) ) )
		)
	)

; TEST KEYWORD <

	( assert ( < 0 1 ) )
	( assert ( not ( < 1 0 ) ) )
	( assert ( not ( < 0 0 ) ) )

; TEST KEYWORD >

	( assert ( > 1 0 ) )
	( assert ( not ( > 0 1 ) ) )
	( assert ( not ( > 0 0 ) ) )

; TEST KEYWORD <=

	( assert ( <= 0 1 ) )
	( assert ( not ( <= 1 0 ) ) )
	( assert ( <= 0 0 ) )

; TEST KEYWORD >=

	( assert ( >= 1 0 ) )
	( assert ( not ( >= 0 1 ) ) )
	( assert ( >= 0 0 ) )

; TEST KEYWORD !=

	; HOMOGENEOUS

	( assert ( != 1 0 ) )
	( assert ( not ( != 1 1 ) ) )

	( assert ( != #f #t ) )
	( assert ( != #t #f ) )

	( assert ( not ( != #f #f ) ) )
	( assert ( not ( != #t #t ) ) )

	( assert ( not ( != ( list ) ( list ) ) ) )

	; HETEROGENEOUS

	( assert ( != #t 0 ) )
	( assert ( != #t 1 ) ) ; No implicit conversion, strongly typed!

	( assert ( != #f 1 ) )
	( assert ( != #f 0 ) ) ; No implicit conversion, strongly typed!

	( assert ( != #f ( list ) ) ) ; No implicit conversion, strongly typed!

	; This keyword only works with atoms or null.

; TEST KEYWORD cond

	( assert
		( equal?
			( cond
				( #f 1 )
				( #f 2 )
				( #t 3 )
				( #t 4 )
				( #f 5 )
				( #f 6 )
			)
			3
		)
	)

	( assert
	  	( equal?
			( cond
				( #f 1 )
				( #t 2 )
				( #t  ( + ( * 11 11 ) 3 ) )
				( #f 4 )
			)
			2
		)
	)

	( assert
		( equal?
			( cond
				( #f 1 )
				;( #t 2 )
				( #f 3 )
				( #f 4 )
				; ( #t 5 )
				( ( zero? 0 ) ( + 5 1 ) )
				( #t ( + 6 1 ) )
			)
			6
		)
	)

	( assert
		( cond
			( #f 5 )
			(
				 ( = 5 6 )
				 ( + 2 1 )
				 ; Comments are fun!
			)
			(
				( = 0 ( % 8 2 ) )
				; This is a comment.
				( zero? ( % 8 2 ) )
			)
		)
	)

	( assert  ; that we can next cond blocks.
		( equal?
			( cond
				( #f #f )
				( #f #f )
				( #t
					( cond
						( #f #f )
						( #f #f )
						( #t
							( cond
								( #f #f )
								( #f #f )
								( #t 1 )
							)
						)
						( #t 2 )
						( #f 3 )
					)
				)
				( #t 2 )
				( #f 3 )
			)
			1
		)
	)

	; EXPECTANCY ERROR
	; ( print ( cond ( 1 #t ) ) )

; TEST KEYWORD '

	;( print
	;	' (
	;		1
	;		#f
	;		#t
	;		3
	;	)
	;)

	( assert ( equal? ' ( ) ( list ) ) )
	( assert ( equal? ( list ) ' ( )  ) )

	; DOESN'T WORK

		; ( print ' ( ' ( ) ) )
		; ( print ( list ( list ) ) )
		; ( assert ( equal? ' ( ' ( ) )  ( list ( list ) ) ) )
		; ( print ( car ( list ( list 1 ) 2 3 ) ) )
		; ( print ( car ' ( ' ( 1 ) 2 3 ) ) )

; TEST KEYWORD define

	; We can define a variable.
	( define love #t )
	( assert love )

	; DOESN'T WORK:

		; We can reassign to a variable.
		; ( define love #f )
		; ( assert ( not love ) )

	( define three 3 )
	( define five 5 )
	( define eight 8 )
	( assert ( equal? ( + three five ) eight ) )

	( define primes ( list 2 3 5 7 11 13 17 19 23 29 31 37 ) )
	( print primes )

( assert #t )
