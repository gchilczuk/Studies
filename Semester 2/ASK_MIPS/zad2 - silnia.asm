.data
	n: .asciiz "Podaj liczbe: "
	w: .asciiz "n! = "
	space: .asciiz "\n"
	
.text
	main:
	
		# wczytanie liczby
		li $v0, 4
		la $a0, n
		syscall
		li $v0, 5
		syscall
		move $s0, $v0
		
		li $t1, 1 # wynik
		li $t2, 1 # licznik
		
		loop:
			bgt $t2, $s0, print
			mul $t1, $t1, $t2
			addi $t2, $t2, 1
			j loopz
		
		print:
			li $v0, 4
			la $a0, w
			syscall
			li, $v0, 1
			add $a0, $zero, $t1
			syscall
	
		exit:
			li $v0, 10
			syscall
		
	
		
		
