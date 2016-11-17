type osoba = {nazwisko: string; wiek: int; ocena:float};;

let list = 
[   {nazwisko= "A"; wiek= 15; ocena=2.0};
    {nazwisko= "B"; wiek= 18; ocena=3.0};
    {nazwisko= "C"; wiek= 21; ocena=3.5};
    {nazwisko= "D"; wiek= 24; ocena=4.0};
    {nazwisko= "E"; wiek= 27; ocena=4.5};
    {nazwisko= "F"; wiek= 30; ocena=5.0};
    {nazwisko= "G"; wiek= 33; ocena=5.5};   ];;


let znajdz lista porw wik poro oc =
    let rec zn = function
        | [] -> failwith "Nie ma nikogo"
        | h::t ->   if porw h.wiek wik && poro h.ocena oc then h
                    else zn t
    in
    zn lista


let zad3 lista porw wik poro oc =
    try 
    (znajdz lista porw wik poro oc).nazwisko
    with
    | Failure a -> "Nie ma osoby"

znajdz list ( > ) 20 ( > ) 4.5;;
znajdz list ( > ) 25 ( > ) 5.5;;

zad3 list ( > ) 20 ( > ) 4.5;;
zad3 list ( > ) 25 ( > ) 5.5;;















type ('a,'b) ab = A of 'a | B of 'b;;

let naPrzemian lista = 
    let rec nP = function
        | [], _ -> true
        | A _==t, A _ -> false
        | B _==t, B _ -> false
        | A str==t, B num -> (true && (nP (t, A str)))
        | B num==t, A str -> (true && (nP (t, B num)))
    in 
    if lista == [] then true
    else let h==t = lista in nP (t, h);;

naPrzemian([A 1;B 2.2; B 3.3]);;
naPrzemian([A 1;B 2.2; A 3]);;











