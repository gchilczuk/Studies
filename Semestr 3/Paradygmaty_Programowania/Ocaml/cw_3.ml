(* zadanie 2 *)
let curry3 f x y z = f(x,y,z)
let c3 = fun f -> fun x -> fun y z -> f (x,y,z)
let plus (x,y,z) = x+y+z

let uncurry3 f (x,y,z) = f x y z
let uc3 = function f -> function (x,y,z) -> f x y z

(* zadanie 3 *) 

let sumProd = List.fold_left (fun acc h -> (h + fst acc, h * snd acc)) (0,1)

(* zadanie 4 *)
let rec quicksort' = function
[] -> []
| x::xs -> let small = List.filter (fun y -> y < x ) xs
and large = List.filter (fun y -> y >= x ) xs
in quicksort' small @ (x :: quicksort' large);;

(* zadanie 5 *)

let insertsort f list = 
	let rec loop li sorted = 
		match li with
		| [] -> sorted
		| h::t -> let rec insert el l = 
			match l with
			| [] -> [el]
			| x::xs -> if f x el then el::l else x::insert el xs
			in 
			insert h (loop t sorted)
		in 
		loop list []
	
let rec insertionsort f list =
	match list with
	| [] -> []
	| h::t -> let rec insert el l =
		match l with
		| [] -> [el]
		| x::xs -> if f x el then el::l else x::insert el xs
		in insert h (insertionsort f t) 
		
(* merge *)
	
let rec halve list = 
	match list with
	| [] | [_] -> (list, [])
	| h::t -> let l1, l2 = halve t in h::l2, l1

let rec merge f l1 l2 = 
	match l1, l2 with
	| [],[]
	| [],_ -> l2
	| _,[] -> l1
	| h1::t1, h2::t2 -> 
		if f h1 h2 then 
			h1::merge f t1 l2
		else h2::merge f l1 t2	
		 
let rec merge_sort f list = 
	match list with
	| [] | [_] -> list
	| l -> let l1,l2 = halve list in merge f (merge_sort f l1) (merge_sort f l2)



