package service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import jpl.Atom;
import jpl.JPL;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class PrologService {

	private final static String PROLOG_PATH="c:/users/zlatko/eclipse-workspace/prologprojekt/src/prolog/";
	public final static String KEYS_ARRAY[]= {"A","B","C","D","E","F","G","H","I","J","K","L"};
	
	private boolean comparisonResult;
	
	static {
		JPL.loadNativeLibrary();
	}
	
	public boolean compareResults(final Map<String, Integer> userResults) {
		comparisonResult = true;
		final Map<String, Integer> solution = getSolution();

		userResults.forEach((key, value) -> {
			if (value != null && value != 0) {
				if (!solution.get(key).equals(value)) {
					comparisonResult = false;
				}
			}
		});
		
		return comparisonResult;
	}

	public HashMap<String, Integer> getSolution() {
		HashMap<String, Integer> resultMap=new HashMap<>();
		int counter=0;
		
		Query consultQuery = new Query("consult", new Term[] { new Atom(PROLOG_PATH + "kakuroSolver.pl") });
		
		System.out.println( "consult " + (consultQuery.hasSolution() ? "succeeded" : "failed"));

		Variable X = new Variable("X");
		Query solveQuery = new Query("kakuro_example", new Term[] { X });
		
		Hashtable solution = solveQuery.oneSolution();
		Term terms = (Term) solution.get("X");
		
		for (Term term : terms.toTermArray()) {
			for (Object object : term.toTermArray()) {
				resultMap.put(KEYS_ARRAY[counter++], Integer.valueOf( object.toString()));
			}
		}
		
		return resultMap;
	}
	
}
