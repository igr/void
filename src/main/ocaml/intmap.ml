
(* Defines IntPairs - a par of ints. *)
module IntPairs =
   struct
     type t = int * int
     let compare (x0,y0) (x1,y1) =
       match Pervasives.compare x0 x1 with
           0 -> Pervasives.compare y0 y1
         | c -> c
   end

(* Defines a map type that has IntPars as keys *)
module PairsMap = Map.Make(IntPairs)

(* Create an instance of map and add two elements *)
let m = PairsMap.(empty |> add (0,1) "hello" |> add (1,0) "world")
(* alternative, w/o the pipe operator *)
let m = PairsMap.(add (1,1) "WORLD" (add (2,2) "HELLO" m))


(* Iterate map *)

let print_data key value =
    print_string(string_of_int (fst key) ^ ":" ^ string_of_int (snd key) ^ " " ^ value ^ "\n");;

PairsMap.iter print_data m;;
