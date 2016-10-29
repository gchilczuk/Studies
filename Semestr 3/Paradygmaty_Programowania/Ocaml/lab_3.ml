let moduloList (list, m) = List.map (function x -> x mod m) list
	
let ( ^^ ) a b =
	match a,b with 
	| true, false 
	| false, true -> true
	| _ -> false

moduloList ([1;2;3;4;5;6;7;8], 2);;
moduloList ([1;3;5;7;9], 3);;

true ^^ true;;
true ^^ false;;
false ^^ true;;
false ^^ false;;