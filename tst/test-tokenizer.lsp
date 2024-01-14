; FILENAME: test-tokenizer.lsp
; AUTHOR: Zachary Krepelka
; DATE: Saturday, January 13th, 2024
; CLASS: Introduction to Data Structures
; PROJECT: Lisp Interpreter

	; The tokenizer allows parenthesis accumulation.
	; We test that it works.

(assert #t)
(assert(equal?(car(list 1 2 3))1))
(assert(equal?(cons 1(cons 2(cons 3(list))))(list 1 2 3)))

(assert(equal?(cond(#f #f)(#f #f)(#t(cond(#f #f)(#f #f)(#t(cond(#f #f)(#f
#f)(#t 1)))(#t 2)(#f 3)))(#t 2)(#f 3))1))

(define f(lambda(x)(+ x 1)))
