:- use_module(library(clpfd)).

kakuro(Ranges, Sums) :-
	kakuro_all(Ranges, Sums),
	!.

kakuro_all(Ranges, Sums) :-
	maplist(kakuro_1D, Ranges, Sums),
	maplist(label, Ranges).

kakuro_1D(Range, Sum) :-
	Range ins 1..9,
	sum(Range, #=, Sum),
	all_distinct(Range).


kakuro_test(L) :-
	% http://www.atksolutions.com/games/kakuro.gif
	L = [
		[A, B],
		[C, D, E],
		   [F, G, _H],
		[A, C],
		[B, D, F],
		[E, G]

	],
	Sums = [3, 17, 18, 4, 9, 17],
	kakuro(L, Sums).

kakuro_example(Result) :-
	% http://www.atksolutions.com/games/kakuro.gif
	List = [
		[A, B],
		[C, D, E, F],
		   [G, H, I, J],
		[K, L],
		[C, G],
		[A, D, H, K],
		[B, E, I, L],
	        [F,J]

	],
	Sums = [12, 11, 27, 13, 11, 13, 26, 13],
	kakuro(List, Sums),
	take(4, List, Result).

take(N, L1, Front):-
	length(Front, N),
	append(Front, _, L1).
