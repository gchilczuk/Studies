type 'a llist = 
	LNil 
	| LCons of 'a * (unit -> 'a llist);;

let rec ltake = function
	| (0, _) -> []
	| (_, LNil) -> []
	| (n, LCons(x,xf)) -> x::ltake(n-1, xf());;

let rec lfilter pred = function
	LNil -> LNil
	| LCons(x,xf) -> if pred x then 
		LCons(x, function () -> lfilter pred (xf()) )
	else lfilter pred (xf());;
	
let rec lmap f = function
LNil -> LNil
| LCons(x,xf) -> LCons(f x, function () -> lmap f (xf()) );;

let rec lfrom k = LCons (k, function () -> lfrom (k+1));;

(* zadanie 1 *)
let pot acc x = acc*x;;

let lpow n =
	let rec liter f x = LCons(x, function () -> liter f (f x n))
	in liter pot 1;;

(* zadanie 3 *)
let ff n x = x mod n;;

let doModulo lista n = lmap (ff n) lista;;


(* zadanie 4 *)
let modulo n x = x mod n = 0;;
 
let onlyModulo lista n = lfilter (modulo n) lista;; 


(* zadanie 5 *)

let rec lshuffle la lb =
	match la with 
	| LNil-> lb
	| LCons(h, xs) -> LCons(h, function() -> lshuffle lb (xs()));;


















	