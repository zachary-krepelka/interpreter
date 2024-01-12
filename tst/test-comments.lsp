; FILENAME: test-comments.lsp
; AUTHOR: Zachary Krepelka
; DATE: Wednesday, January 10th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

( assert
	( equal?
		( list
			;1
			2 ; two
			;3
			4 ; four
			;5
			6 ; six
			;7
			8 ; eight
			;9
		)
		( list 2 4 6 8 )
	)
)

( print 0 )

#| Here's a prologue comment.

Lorem ipsum dolor sit amet habitasse in ullamcorper molestie neque integer. Nisi
aliquam eros inceptos litora ridiculus imperdiet etiam augue. Ligula pretium ex
nunc malesuada ullamcorper laoreet pulvinar purus facilisi tortor potenti. Eget
aptent volutpat vitae porta vel enim facilisis risus ornare condimentum cursus.
Lacinia et nibh ullamcorper placerat neque mus vestibulum ex aliquam fames.

	Netus suscipit facilisis ligula hendrerit potenti quisque magna sodales
	augue.  Per efficitur venenatis tempor semper tristique nunc. Molestie
	duis nulla cubilia facilisis consectetuer ornare. Nunc hac tempor neque
	sollicitudin luctus donec dictum dictumst.

Ultrices lacinia netus vulputate posuere parturient taciti convallis. Mi
curabitur lectus quis lacus nullam rhoncus tempor ullamcorper proin vel.
Malesuada convallis magna vitae fringilla dignissim libero facilisis enim a
interdum. Parturient ridiculus libero gravida vivamus consectetur habitant nec
augue maximus.

Now let's get back to the code. |#

( assert
	( equal? ; This is an inline comment.
		( list ; This is also an inline comment.
			1
			( + 1 1 ) ; 2
			3
			4
			5 ; Inline comments are fun.
		)
		( list 1 2 3 4 5 )
	)
)

( print 1 )

#| It's probably a bad idea to nest prologue comments. |#

( assert
	( equal?
		( + ; addition

			( / 16 2 ) ; 8
			; ( ^ 2 3 )

			; This is a comment.
			; This is also a comment.
			; These comments are back-to-back.

			( * ; multiplication

				; This is a comment!
				( + 1 ( + 1 ( + 1 ( + 1 ( + 1 0 ) ) ) ) ) ; 5

				; Rome wasn't built in a day.

				( / 16 4 ) ; 4
			)
		)
		28
	)
)
