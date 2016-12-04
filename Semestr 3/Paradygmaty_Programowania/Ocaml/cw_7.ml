(* zadanie 1 *)
module type QUEUE_FUN =
	sig
		type 'a t
		exception Empty of string
		val empty: unit -> 'a t
		val enqueue: 'a * 'a t -> 'a t
		val dequeue: 'a t -> 'a t
		val first: 'a t -> 'a
		val isEmpty: 'a t -> bool
	end;;

(* a *)
module QL : QUEUE_FUN =
	struct
		
		type 'a t = Queue of 'a list
		exception Empty of string
		
		let empty() = Queue []
		
		let enqueue = function
			| (e,Queue q) -> Queue (q@[e])

		let dequeue = function 
			| Queue [] -> Queue []
			| Queue (h::xs) -> Queue xs
		
		let first = function
			| Queue [] -> raise (Empty "module qL:first")
			| Queue (h::xs)  -> h
		
		let isEmpty = function
			| Queue list -> list = []
	
	end;;

(* b *)
module QDL : QUEUE_FUN =
	struct
		
		type 'a t = Queue of 'a list * 'a list
		exception Empty of string
		
		let empty() = Queue ([],[])
		
		let enqueue = function
			| (e,Queue ([],s)) -> Queue ([e], [])
			| (e,Queue (f,s)) -> Queue (f, e::s)
		
		let dequeue = function
			| Queue ([],[]) -> Queue ([],[])
			| Queue ([x],s) -> Queue (List.rev s, [])
			| Queue (h::xs,s) -> Queue (xs,s) 
		
		let first = function
			| Queue ([],[]) -> raise (Empty "module QL:first")
			| Queue (h::xs, s)  -> h
		
		let isEmpty = function
			| Queue (f,s) -> f = []
	
	end;;

(* zadanie 2 *)
module type QUEUE_MUT =
	sig
		type 'a t
		exception Empty of string
		exception Full of string
		val empty: int -> 'a t
		val enqueue: 'a * 'a t -> unit
		val dequeue: 'a t -> unit
		val first: 'a t -> 'a
		val isEmpty: 'a t -> bool
		val isFull: 'a t -> bool
	end;;

module QM : QUEUE_MUT =
	struct
		
		type 'a t = { arr : 'a option array; mutable f : int; mutable r : int}
		exception Empty of string
		exception Full of string
		
		let ( +@+ ) arr rf =
			if rf = (Array.length arr) -1 then 0
			else succ rf
		
		let empty size = 
			{arr = Array.make (size+1) None; f=0; r=0}
		
		let isEmpty q = q.f = q.r
		
		let isFull q = q.f = (q.arr +@+ q.r)
		
		let enqueue (e,q) =
			if isFull q then raise (Full "module QM:enqueue")
			else 
				Array.set q.arr q.r (Some e); 
				q.r <- q.arr +@+ q.r
				
		let dequeue q =
			if not (isEmpty q) then q.f <- q.arr +@+ q.f
				
		let first q =
			if isEmpty q then raise (Empty "module QM:first")
			else let Some v = Array.get q.arr q.f in v

	end;;

