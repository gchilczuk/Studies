.data
	vector: .space 100
	value: .space 100
	order: .space 100
	v1: .space 100
	o1: .space 100
	v2: .space 100
	o2: .space 100
	quest1: .asciiz "Podaj dlugosc wektora (max: 25): "
	vec1: .asciiz "Wprowadz wektor 1"
	vec2: .asciiz "Wprowadz wektor 2"
	quest2: .asciiz "Podaj liczbe: "
	start: .asciiz "Wektor: ("
	comma: .asciiz ", "
	end: .asciiz ")"
	newLine: .asciiz "\n"
	wyn: .asciiz "Iloczyn skalarny wynosi: "
	
	
	
	
.text
	# $s0 adres wektora order
	# $s5 adres wektora value
	# $s1 rozmiar wektora
	# $s3 pierwszy wolny indeks value
	# $s4 pierwszy wolny indeks order
	main:	
		
		jal readLen
		la $s0, o1
		la $s5, v1
		add $s3, $zero, $zero
		add $s4, $zero, $zero
		la $a0, vec1
		jal printString
		la $a0, newLine
		jal printString
		jal read
		
		la $s0, o2
		la $s5, v2
		add $s3, $zero, $zero
		add $s4, $zero, $zero
		la $a0, vec2
		jal printString
		la $a0, newLine
		jal printString
		jal read
		
		# la $a0, v1
		# jal printVector
		# la $a0, v2
		# jal printVector
		
		la $a0, wyn
		jal printString
		jal multiply
		add $a0, $zero, $v0
		jal printNum

	exit:
		li $v0, 10
		syscall
	
			
	readLen:	# czyta długość wektora
		li $v0, 4
		la $a0, quest1
		syscall 
		
		li $v0, 5
		syscall
		
		add $s1, $zero, $v0
		li $t0, 4
		mul $s1, $s1, $t0
		jr $ra
		
	read:	# wczytuje wektor
		
		# $t8 licznik
		li $t8, 0
		
		readLoop:
			li $v0, 4
			la $a0, quest2
			syscall 
			
			li $v0, 5
			syscall
			
			add $a0, $zero, $v0
			
			beqz $a0, write0
			j write1
		wroc1:	j writeValue
		wroc0:	addi $t8, $t8, 4
			blt $t8, $s1, readLoop
			
		jr $ra
		
		write0:	# zapisuje 0 do wektora porządku
			li $t0, 0
			add $t1, $s0, $s4
			sb $t0, ($t1)
			addi $s4, $s4, 1
			j wroc0
		
		write1:	# zapisuje 1 do wektora porządku
			li $t0, 1
			add $t1, $s0, $s4
			sb $t0, ($t1)
			addi $s4, $s4, 1
			j wroc1
			
		writeValue: #zapisuje liczbę do wektora wartości
			add $t1, $s5, $s3
			sw $a0, ($t1)
			addi $s3, $s3, 4
			j wroc0
												
	printNum: # wypisuje liczbę z $a0
		li $v0, 1
		syscall
		jr $ra
		
	printString:	# wyświetla napis z adresu $a0
		li $v0, 4
		syscall 
		jr $ra
		
	printVector: # wyświetla Vector
		add $t0, $zero, $a0
		add $t1, $t0, $s1
		addi $sp, $sp, -4
		sw $ra, ($sp)
		la $a0, start
		jal printString
		lw $a0, ($t0)
		jal printNum
		addi $t0, $t0, 4
		bge $t0, $t1, kon
		printLoop:
			la $a0, comma
			jal printString
			lw $a0, ($t0)
			jal printNum
			addi $t0, $t0, 4
			blt $t0, $t1, printLoop
		kon:
		la $a0, end
		jal printString
		la $a0, newLine
		jal printString
		lw $ra, ($sp)
		addi $sp, $sp, 4
		jr $ra
		
	multiply: # mnoży wektory
		addi $sp, $sp, -4
		sw $ra, ($sp)
		add $v0, $zero, $zero # ten rejestr zwracam
		# $t0 licznik bajtow
		li $t0, 0
		# $t1 licznik wartosci v1
		# $t8 licznik wartosci v2
		li $t1, 0
		li $t8, 0
		# $t3 order 
		# $t4 value
		# $t5 il1 pierwszy czynnik ilocznu
		# $t6 il2 drugi czynnik ilocznu
		# $t7 ile wynik mnożenia
		
		multloop:
			lb $t3, o1($t0)
			beqz $t3, dalej
			lw $t4, v1($t1)
			addi $t1, $t1, 4
			mul $t5, $t3, $t4
			b bliz
			# beqz $t5, zero 
		dalej:	add $t5, $zero, $zero
		bliz:	lb $t3, o2($t0)
			beqz $t3, zero
			lw $t4, v2($t8)
			addi $t8, $t8, 4
			mul $t6, $t3, $t4
			beqz $t6, zero
			mul $t7, $t5, $t6
			add $v0, $v0, $t7
		
		zero:	addi $t0, $t0, 1
			# move $a0, $v0
			# jal printNum
			
			ble $t1, $s1, multloop
		
		lw $ra, ($sp)
		addi $sp, $sp, 4
		jr $ra
			
			
		
