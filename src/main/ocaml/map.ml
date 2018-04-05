
(* Simple version of intmap.ml, with regular maps *)

module StringMap = Map.Make(String);;

(* version 1 *)
let m = StringMap.empty;;
let m = StringMap.add "K1" 11 m;;
let m = StringMap.add "K2" 12 m;;
let m = StringMap.add "K3" 13 m;;

(* version 2 *)
let m = StringMap.add "X1" 21 (StringMap.add "X2" 22 (StringMap.add "X3" 33 m));;

(* version 3 *)
let m = StringMap.add "Z1" 31 @@ StringMap.add "Z2" 32 @@ StringMap.add "Z3" 33 m;;

(* version 4 *)
let m = m |> StringMap.add "O1" 41 |> StringMap.add "O2" 42;;

(* print map *)

let printMap m =
  StringMap.iter (fun key value -> Printf.printf "%s -> %d\n" key value) m;;

printMap m;;

(* sum all *)

let put_key_values_into_a_list c map =
    StringMap.filter (fun key _ -> String.contains key c) map
    |> StringMap.bindings
    |> List.split
    |> snd

let list = put_key_values_into_a_list 'K' m;;
let ll = List.length list;;

let sum_and_len xs = List.fold_left (fun (s,l) x -> s+x, l+1) (0,0) xs;;

print_int(ll);;
print_string "\n";;
print_int(sum_and_len list |> fst);;

print_string "\nDone\n";;