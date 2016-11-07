
(* 2 *)
let f1 x = failwith "asd";;
let rec f2 x = f2 x;;

(* 3 *)
type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;

let breadthBT bt =
	let rec bb = function 
		| [] -> []
		| Node (v, l, r)::t -> v::bb(t@[l;r])
		| Empty::t -> bb t
	in bb [bt];;

(* 4 *)
let wew bt =
	let rec ww (node, x) =
		match node with
		| Empty -> 0
		| Node(v,l,r) -> x + ww(l, x+1) + ww(r, x+1)
	in ww (bt, 0);;

let zew bt =
	let rec zz (node, x) =
		match node with
		| Empty -> x
		| Node(v,l,r) -> zz(l, x+1) + zz(r, x+1)
	in zz (bt, 0);;

let zew2 bt =
	let rec zz2 (i,n) = function
		| Empty -> (0,0)
		| Node (_, tl, tr) ->
			let (a,c) = zz2 (i+1,0) tl 
			and (b,d) = zz2 (i+1,0) tr 
			in (i+a+b, 1+c+d)
	in let (i,n) = zz2 (0,0) bt 
	in i+2*n;;

(* 5 *)
type 'a graph = Graph of ('a -> 'a list);;

let dS (Graph succ) startNode =
	let rec dd visited = function
		| [] -> []
		| h::t -> 
			if List.mem h visited then dd visited t
			else h::(dd(h::visited)((succ h)@t))
	in dd [] [startNode];;
