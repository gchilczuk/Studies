(* zadanie 1 *)
let f1 (x,y) = x + y = 0

let f2 (x,y) = (y+.1.,x+.1.)

let rec f3 (list, x) =
	if x < 1 then list
	else f3(list@list,x-1)
	
	
(* zadanie 2 *)
let rec twoLast (list) =
	if list = [] || List.tl list = [] then failwith "Zbyt krótka lista"
	else if List.tl(List.tl list) = [] then (List.hd list,List.hd (List.tl list))
	else twoLast(List.tl list)
	
	
(* zadanie 3 *)
let rec dwaRowne list = 
	if list = [] || List.tl list = [] then false
	else if List.hd list = List.hd (List.tl list) then true
	else dwaRowne (List.tl list)
	
	
	
	
	
	 
	 