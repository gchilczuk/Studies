(* zadanie 1 *)
let rec flatten list =
	if list = [] then []
	else List.hd list @ flatten (List.tl list)
	
(* zadanie 2 *)
let rec count (x, list) = 
	if list = [] then 0
	else if List.hd list = x then 1 + count (x, List.tl list)
	else count (x, List.tl list)
	
(* zadanie 3 *)
let rec replicate (a, x) = 
	if x < 0 then failwith "Niepoprawna liczba powtórzeń!"
	else if x = 0 then []
	else [a] @ replicate(a, x-1)
	
(* zadanie 4 *)	
let rec sqrList list =
	if list = [] then []
	else [List.hd list * List.hd list] @ sqrList(List.tl list)

(* zadanie 5 *)
let palindrome list =
	list = List.rev list
	
(* zadanie 6 *)
let rec listLength list = 
	if list = [] then 0
	else 1 + listLength(List.tl list)
	
	
