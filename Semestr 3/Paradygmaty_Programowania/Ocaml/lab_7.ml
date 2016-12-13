module type POJ =
	sig
		type 'a t
		exception Empty of string
		exception Full of string
		val create: 'a -> 'a t
		val push: 'a * 'a t -> unit
		val pop: 'a t -> 'a 
		val isEmpty: 'a t -> bool
		val isFull: 'a t -> bool
	end;;

module POLE : POJ =
	struct
		
		type 'a t = {mutable war : 'a ; mutable is : bool}
		exception Empty of string
		exception Full of string
		
		let create e = {war = e; is = true}
		
		let push (x,p) = 
			if not p.is then (p.war <- x; p.is <- true)
			else raise (Full "push fail : FULL!")
			
		let pop p =
			if p.is then (p.is <- false; p.war )
			else raise (Empty "pop fail : EMPTY!")
			
		let isEmpty p = not p.is
		let isFull p = p.is
		
	end;;

	let x = POLE.create 3;;
	POLE.push (2,x);;
	POLE.isFull x;;
	POLE.isEmpty x;;
	POLE.pop x;;
	POLE.pop x;;
	POLE.push (2,x);;


module Wagon : POJ =
	struct
		type 'a t = {mutable lista : 'a list}
		exception Empty of string
		exception Full of string
		
		let create e = {lista = [e]}
		
		let push (x,p) = 
			if List.length p.lista = 0 then (p.lista <- [x])
			else raise (Full "push fail : FULL!")
			
		let pop p =
			if List.length p.lista = 1 then 
				let h::t = p.lista in p.lista <- []; h;
			else raise (Empty "pop fail : EMPTY!")
			
		let isEmpty p = List.length p.lista = 0
		let isFull p = List.length p.lista = 1
	end;;

	let x = Wagon.create 3;;
	Wagon.push (2,x);;
	Wagon.isFull x;;
	Wagon.isEmpty x;;
	Wagon.pop x;;
	Wagon.pop x;;
	Wagon.push (2,x);;

		
	