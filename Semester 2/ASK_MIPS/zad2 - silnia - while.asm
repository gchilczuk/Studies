.data
	q1:	.asciiz "Podaj liczbe obrotów petli: "
	q2:	.asciiz "Czy kontynuować petle? (tak/nie): "
	ans:	.asciiz "tak\n"
	wyn:	.asciiz "\nWynik: n! = "
	inf1:	.asciiz "\nNastapilo przepelnienie!" 
	inf2:	.asciiz "\nZostala obliczona silnia dla n = "
	odp:	.space 5
	one: 	.double 1
	
.text
	ldc1 $f0, one		# wynik
	add $s1, $zero, $zero 	# liczba obrotow
	addi $s2, $zero, 1	# licznik
	ldc1 $f2, one		# mnożnik
	ldc1 $f4, one 		# jeden
	
	# zapytanie 1
	read:
		li $v0, 4
		la $a0, q1
		syscall
	
		# pobranie 
		li $v0, 5
		syscall
		add $s1, $s1, $v0
	
	while:
		bgt $s2, $s1, end
		mul.d $f0, $f0, $f2
		addi $s2, $s2, 1
		add.d $f2, $f2, $f4
		j while
	
	# zapytanie 2
	end:
		li $v0, 4
		la $a0, q2
		syscall
		li $v0, 8
		la $a0, odp
		addi $a1, $zero, 5
		syscall
		jal strcmp
		seq $t3, $v0, $zero
		bnez $t3, read
	
	print:
		li $v0, 4
		la $a0, inf2
		syscall
		li $v0, 1
		add $a0, $zero, $s1
		syscall
		li $v0, 4
		la $a0, wyn
		syscall
		li, $v0, 3
		mov.d $f12, $f0
		syscall
		
		# addi $t0, $s1, -12 
		# bgtz $t0, overflow
		
	exit:
		li $v0, 10
		syscall
	
	overflow:
		li $v0, 4
		la $a0, inf1
		syscall
		j exit
		
	strcmp:
		la $t8, odp
		la $t9, ans
	loop:
		lb $t3($t8)  # load byte
		lb $t4($t9)
		beqz $t3, checkt2 #str1 end
		beqz $t4, miss
		slt $t5, $t3, $t4  #compare two bytes
		bnez $t5, miss
		addi $t8, $t8, 1  #t1 points to the next byte of str1
		addi $t9, $t9, 1
		j loop

	miss: 
		addi $v0,$zero,1
		j endfunction
		
	checkt2:
		bnez $t4, miss
		add $v0, $zero, $zero
		
	endfunction:
		jr $ra
		
		