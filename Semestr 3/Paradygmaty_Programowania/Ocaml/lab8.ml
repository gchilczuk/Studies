
let billy lol del =
	let i = ref ((Array.length lol) -2) and 
	j = ref 0 in
	let wyn =  Array.make (!i+2) 0 in
	wyn.(!i + 1) <- lol.(!i+1);
	while !i > -1 do
		j := lol.(!i) + (get wyn (!i + del.(!i)));
		if  !j > (get wyn (!i +1)) then
			wyn.(!i) <- !j
		else wyn.(!i) <- (get wyn (!i +1));
		i := !i - 1;
	done;
	wyn;
		

let get arr i =
	if i >= Array.length arr then 0
	else arr.(i)
	
	




let matrix x = 
	Array.init x (function i -> Array.make x 0);;

let drukuj arr =
	let i = ref 0 and j = ref 0 in
		while !i < Array.length arr do
			j := 0;
			while !j < Array.length (arr.(!i)) do
				print_int arr.(!i).(!j);
				j := !j + 1
				done;
			print_string "\n";
			i := !i + 1
			done
			
let wstaw1 arr =
	let i = ref 0  in
		while !i < Array.length arr.(0) do
			arr.(0).(!i) <- 1;
			i := !i + 1;
			done;
		i := 0;
		while !i < Array.length arr do
			arr.(!i).(0) <- 1;
			arr.(!i).(Array.length arr.(!i) -1) <- 1;
			i := !i + 1;
			done;
		i := 0;
		while !i < Array.length arr.(0) do
			arr.(Array.length arr -1).(!i) <- 1;
			i := !i + 1;
			done;
