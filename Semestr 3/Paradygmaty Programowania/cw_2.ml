(* zadanie 2 *)

let rec fib1 x =
	match x with
	| y when y < 0 -> failwith "Podana liczba jest ujemna!" 
	| 0 -> 0
	| 1 -> 1
	| n -> (fib1 (n-1) + fib1 (n-2))

let fib2 x =
	match x with
	| y when y < 0 -> failwith "Podana liczba jest ujemna!" 
	| 0 -> 0
	| y -> let rec fibi (n, a, b) =
		match z with
		| 1 | 2 -> b
		| x -> fibi(n-1, b, a+b)
		in fibi(y, 1,1)

(* zadanie 3 *)
let root3 a = 
	let x0 = if a <= 1. then a else a/.3. in
	let rec root3r acc = 
		if isEnough (acc, a) then acc else root3r (acc +. (a/.(acc**2.) -. acc)/.3.)
	in root3r x0
	
let isEnough (x, a) = abs_float(x**3. -. a) < 10.**(-15.) *. abs_float a;;



(* zadanie 4 *)
let [_; _; x; _; _] = [-2; -1; 0; 1; 2]

let [_;(x,_)] = [(1,2);(0,1)]

(* zadanie 5 *)
let rec initSegment (l1, l2) =
	match (l1, l2) with
	| ([],[]) | ([],_) -> true
	| (_,[]) -> false
	| (x,y) -> let h1::t1 = x and h2::t2 = y in 
		if h1 = h2 then initSegment(t1,t2) else false

(* zadanie 6 *)

let rec replaceNth (list, n, el) = 
	match n with
	| x when x < 0 -> failwith "Nope!"
	| 0 -> let h::t = list in el::t
	| x -> let h::t = list in h::replaceNth (t,n-1,el)





		
