import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Converting the NFAtoDFA.ipynb to java
public class NFAToDFAConvert {
    public static Map<Set<String>,Map<String,Set<String>>> nfatodfa(String nfaStartState,Map<String,Map<String,Set<String>>> 
        nfaTransitions,Set<String> tsym){
        if (nfaStartState == null || nfaTransitions == null || tsym == null) {
            throw new IllegalArgumentException("Invalid input (null), input correct things");
        }
        Deque<Set<String>> temp = new ArrayDeque<>();
        Set<Set<String>> visited = new HashSet<>();
        Map<Set<String>,Map<String,Set<String>>> dfaTransitions = new HashMap<>();

        temp.push(new HashSet<>(Set.of(nfaStartState)));
        visited.add(new HashSet<>(Set.of(nfaStartState)));

        while (!temp.isEmpty()){
            Set<String> currentState = temp.pop();
            dfaTransitions.put(currentState, new HashMap<>());
            for(String symbol:tsym){
                Set<String> nextState = new HashSet<>();
                
                for(String state : currentState){
                    if (nfaTransitions.containsKey(state) && nfaTransitions.get(state).containsKey(symbol)){
                        nextState.addAll(nfaTransitions.get(state).get(symbol));
                    }
                }
                dfaTransitions.get(currentState).put(symbol, nextState);
                if (!visited.contains(nextState)){
                    visited.add(nextState);
                    temp.push(nextState);
                }
            }
        }
        return dfaTransitions;
    }

    // for printing the dfa transitions
    public static void printDFA(String initialState, Map<Set<String>,Map<String,Set<String>>> dfaTransitions, Set<String> finalStates){
        System.out.println("DFA Transitions:");

        System.out.println("Starting State: [" + initialState + "]");

        Set<Set<String>> dfaFinalStates = new HashSet<>();

        for (Set<String> state : dfaTransitions.keySet()){
            boolean isFinal = false;
            for (String s : state){
                if (finalStates.contains(s)){
                    isFinal = true;
                    break;
                }
            }
            if (isFinal){
                dfaFinalStates.add(state);
            }

            for (String symbol : dfaTransitions.get(state).keySet()){
                System.out.println(state + " --" + symbol + "--> " + dfaTransitions.get(state).get(symbol));
            }
        }

        System.out.println("DFA Final States:");
        for (Set<String> state : dfaFinalStates){
            System.out.println(state);
        }
    }
}
