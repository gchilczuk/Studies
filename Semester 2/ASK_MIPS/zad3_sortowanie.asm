.data 
	array: .space 100
	quest1: .asciiz "Ile liczb chcesz sortowac? (max: 25): "
	quest2: .asciiz "Podaj liczbe: "
	start: .asciiz "Array: ["
	comma: .asciiz ", "
	end: .asciiz "]"
	newLine: .asciiz "\n"
	before: .asciiz "Przed sortowaniem "
	after: .asciiz "Po sortowaniu     "
	
.text 
	# $s0 adres tablicy
	# $s1 rozmiar tablicy
	# 
	main:
		la $s0, array
		jal read
		add $a0, $zero, $v0
		la $a0, before
		jal printString
		jal printArray
		jal sort
		la $a0, after
		jal printString
		jal printArray
	
		
	
	exit:
		li $v0, 10
		syscall
	
	read:
		li $v0, 4
		la $a0, quest1
		syscall 
		
		li $v0, 5
		syscall
		
		add $s1, $zero, $v0
		li $t0, 4
		mul $s1, $s1, $t0
		
		# $t0 licznik
		li $t0, 0
		
		readLoop:
			li $v0, 4
			la $a0, quest2
			syscall 
			
			li $v0, 5
			syscall
			
			sw $v0, array($t0)
			addi $t0, $t0, 4
			blt $t0, $s1, readLoop
			
		jr $ra
	
	sort:
		addi $sp, $sp, -4
		sw $ra, ($sp)
		
		li $s3, 0
		add $a3, $zero, $s1
		sortLoop:
			jal check
			addi $s3, $s3, 4
			addi $a3, $a3, -4
			blt $s3, $s1, sortLoop
		
		lw $ra, ($sp)
		addi $sp, $sp, 4
		jr $ra
		
		
	
	check:
		addi $sp, $sp, -4
		sw $ra, ($sp)
		li $a0, 0
		li $a1, 4
		checkLoop:
			lw $t0, array($a0)
			lw $t1, array($a1)
			bgt $t0, $t1, doswap
		back:	addi $a0, $a0, 4
			addi $a1, $a1, 4
			blt $a1, $a3, checkLoop
		lw $ra, ($sp)
		addi $sp, $sp, 4
		jr $ra
		doswap:
			jal swap
			j back
		
	swap:
		lw $t0, array($a0)
		lw $t1, array($a1)
		sw $t0, array($a1)
		sw $t1, array($a0)
		jr $ra
		
		
		
	printNum:
		li $v0, 1
		syscall
		jr $ra
		
	printString:
		li $v0, 4
		syscall
		jr $ra
		
	printArray:
		addi $sp, $sp, -4
		sw $ra, ($sp)
		li $t0, 0
		la $a0, start
		jal printString
		lw $a0, array($t0)
		jal printNum
		addi $t0, $t0, 4
		bge $t0, $s1, kon
		printLoop:
			la $a0, comma
			jal printString
			lw $a0, array($t0)
			jal printNum
			addi $t0, $t0, 4
			blt $t0, $s1, printLoop
		kon:
		la $a0, end
		jal printString
		la $a0, newLine
		jal printString
		lw $ra, ($sp)
		addi $sp, $sp, 4
		jr $ra
		
		
		
