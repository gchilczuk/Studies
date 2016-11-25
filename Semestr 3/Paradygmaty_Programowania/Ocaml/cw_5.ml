type 'a llist = 
	LNil 
	| LCons of 'a * (unit -> 'a llist);;

let ltl = function
	| LNil -> failwith "ltl"
	| LCons (_, xf) -> xf();;

let rec ltake = function
	| (0, _) -> []
	| (_, LNil) -> []
	| (n, LCons(x,xf)) -> x::ltake(n-1, xf());;

let rec lfrom k = LCons (k, function () -> lfrom (k+1));;

let rec toLazyList = function
	| [] -> LNil
	| h::t -> LCons(h, function () -> toLazyList t);;

let rec (@$) ll1 ll2 =
	match ll1 with
		| LNil -> ll2
		| LCons(x, xf) -> LCons(x, function () -> (xf()) @$ ll2);;

(* zadanie 1 *)
let lrepeat k lli =
	let rec rep m llista =
		match m, llista with
		| _, LNil -> LNil
		| 0, LCons(x, xf) -> rep k (xf())
		| n, LCons(x, _) -> LCons(x, ( function () -> rep (n-1) llista))
	in rep k lli;;

(* zadanie 2 *)
let lfib = 
	let rec fibo (a,b) = LCons(a, function () -> fibo(b,a+b))
	in fibo(0,1);;
	

(* zadanie 3 *)
type 'a lBT = 
	LEmpty 
	| LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;


let rec lTree n = 
	LNode (n, (function() -> lTree(2*n)), (function() -> lTree(2*n+1)));;

let breadth lbt =
	let rec bb = function 
		| [] -> LNil
		| LNode (v, l, r)::t -> LCons(v,fun () -> bb(t@[l();r()]))
		| LEmpty::t -> bb t
	in bb [lbt];;

  
	


