let rec fold_left f acc l =
	match l with
		| h::t -> fold_left f (f acc h) t
		| [] -> acc;;

let rec fold_right f l acc =
	match l with
		| h::t -> f h (fold_right f t acc)
		| [] -> acc;;

(* zadanie 1 *)
let pier elem accum =
	let (aktualne, listaList) = accum in
	if aktualne == [] then ([elem], listaList) else
		let h::t = aktualne in
			if h >= elem then (elem::aktualne, listaList) 
			else ([elem], aktualne::listaList)
			
let niem lista = 
	if lista = [] then [] else
	let a, b = fold_right pier lista ([], []) in a::b 
			




(* zadanie 3 *)

let rest (reszta, wydane) x =
	if x > reszta then (reszta, wydane)
	else let re, xs = (rest (reszta-x, x::wydane) x) in 
			(re, xs);;
	 
let wydaj kwota denom =
	let a, b = fold_left rest (kwota, []) denom in List.rev b;;

	