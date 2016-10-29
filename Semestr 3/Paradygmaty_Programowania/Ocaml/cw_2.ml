(* zadanie 2 *)
let rec fib1 x =
	match x with
	| y when y < 0 -> failwith "Podana liczba jest ujemna!" 
	| 0 -> 0
	| 1 -> 1
	| n -> (fib1 (n-1) + fib1 (n-2))

let fib2 x =
	let rec fibi (n, a, b) =
		match n with
		| 1 -> b
		| x -> fibi(n-1, b, a+b)
		in fibi(y, 0,1)

let fib3 x =
	if x = 0 then 0
	else
	let rec fibi(n, a, b) =
		match n with
		| p when p = x -> b
		| p -> fibi (n+1, b, a+b)
	in fibi (1,0,1)

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
	| (h1::t1,h2::t2) -> if h1 = h2 then initSegment(t1,t2) else false

(* zadanie 6 *)
let rec replaceNth (list, n, el) = 
	match n with
	| x when x < 0 -> failwith "Nope!"
	| 0 -> let h::t = list in el::t
	| x -> let h::t = list in h::replaceNth (t,n-1,el)





		
