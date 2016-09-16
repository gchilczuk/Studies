.data
	x: .asciiz "Podaj x: "
	a: .asciiz "Podaj wspolczynnik a: "
	b: .asciiz "Podaj wspolczynnik b: "
	c: .asciiz "Podaj wspolczynnik c: "
	d: .asciiz "Podaj wspolczynnik d: "
	e: .asciiz "Podaj wspolczynnik e: "
	w: .asciiz "Wynik: "
	
.text
	# $t0: x
	# $s0: wynik
	
	main:
		# wyzerowanie wyniku
		add $s0, $zero, $zero
	
		# wczytaj x
		li $v0, 4
		la $a0, x
		syscall
	
		li $v0, 5
		syscall
		add $t0, $zero, $v0
	
		# wczytaj a
		li $v0, 4
		la $a0, a
		syscall
	
		li $v0, 5
		syscall
		add $s0, $s0, $v0 # dodaje a do wyniku
		mul $s0, $s0, $t0 # mnoży wynik przez x
	
		# wczytaj b
		li $v0, 4
		la $a0, b
		syscall
	
		li $v0, 5
		syscall
		add $s0, $s0, $v0 # dodaje b do wyniku
		mul $s0, $s0, $t0 # mnoży wynik przez x
	
		# wczytaj c
		li $v0, 4
		la $a0, c
		syscall
	
		li $v0, 5
		syscall
		add $s0, $s0, $v0 # dodaje c do wyniku
		mul $s0, $s0, $t0 # mnoży wynik przez x
	
		# wczytaj d
		li $v0, 4
		la $a0, d
		syscall
	
		li $v0, 5
		syscall
		add $s0, $s0, $v0 # dodaje d do wyniku
		mul $s0, $s0, $t0 # mnoży wynik przez x
	
		# wczytaj e
		li $v0, 4
		la $a0, e
		syscall
	
		li $v0, 5
		syscall
		add $s0, $s0, $v0 # dodaje e do wyniku
	
		# wyświetl wynik
		li $v0, 4
		la $a0, w
		syscall
	
		li $v0, 1
		move $a0, $s0
		syscall
	
	exit:
		li $v0, 10
		syscall
	


