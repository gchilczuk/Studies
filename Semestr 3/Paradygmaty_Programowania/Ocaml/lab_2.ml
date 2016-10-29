(* zadanie 1 *)
let rec nTy (list, index) =
	match index with
	| x when x < 1 || list = [] -> failwith "Niepoprawny index" 
	| 1 -> List.hd list
	| x -> nTy(List.tl list, index -1)
	
	
	
	
	
	
(* zadanie 3*)
	let rec podzielPoN (lista, dlugosc) =
		match (lista, dlugosc) with
		| ([],x) when x > 0 -> failwith "Niepoprawna dlugosc" 
		| (_, x) when x < 0 -> failwith "Niepoprawna dlugosc"
		| (l, x) when x = 0 -> ([],l)
		| (h::t,x) -> let (a,b) = podzielPoN(t, x-1) in (h::a, b)



let rec podzielPoN (lista, dlugosc) =
	let rec pocz(l,x) = 
		match (l,x) with
		| (_, y) when y <= 0 -> []
		| ([], y) when y >0 -> []
		| (h::t, y) -> h::pocz(t,y-1)
	in
	let rec kon(list,x) =
		match (list,x) with
		| (l, x) when x <= 0 -> l
		| ([], x) when x > 0 -> []
		| (h::t, y) -> kon(t, y-1) 
	in
	(pocz(lista, dlugosc), kon(lista,dlugosc))

podzielPoN([1;2;3;4],(-1));;
podzielPoN([1;2;3;4],0);;
podzielPoN([1;2;3;4],1);;
podzielPoN([1;2;3;4],2);;
podzielPoN([1;2;3;4],3);;
podzielPoN([1;2;3;4],4);;
podzielPoN([1;2;3;4],5);;

		
		
		
		