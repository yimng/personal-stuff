open Core.Std

(** A collection of string frequency counts *)
type t

(** The empty set of frequency counts *)
val empty : t

(** Converts the set fo frequency counts to an association list. A string shows up at most once, and the counts are >= 1. *)
val to_list : t -> (string * int) list

