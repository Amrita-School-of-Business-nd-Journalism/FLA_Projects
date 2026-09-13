import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class IntersectionDFABase {

    public static class IntersectionResult {
        String initialState;
        Map<String, Map<String, String>> transitions;
        Set<String> finalStates;

        IntersectionResult(String initialState,Map<String, Map<String, String>> transitions,Set<String> finalStates) {
            this.initialState = initialState;
            this.transitions = transitions;
            this.finalStates = finalStates;
        }
    }

    public static IntersectionResult intersectionDFA(String initialState1, Set<String> finalState1,Map<String, Map<String, String>> transitions1,
            String initialState2, Set<String> finalState2,Map<String, Map<String, String>> transitions2,Set<String> tsym) {

        if (initialState1 == null || finalState1 == null || transitions1 == null ||
            initialState2 == null || finalState2 == null || transitions2 == null || tsym == null) {
                throw new IllegalArgumentException("Invalid input (null), input correct things");
        }

        String initialState = initialState1 + "," + initialState2;
        Set<String> finalStates = new HashSet<>();
        Map<String, Map<String, String>> transitions = new HashMap<>();

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.add(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            String currentState = queue.poll();  
            String[] states = currentState.split(",", 2);  
            String state1 = states[0];
            String state2 = states[1];

            if (finalState1.contains(state1) && finalState2.contains(state2)) {
                finalStates.add(currentState);
            }

            for (String symbol : tsym) {
                String nextState1 = null;
                String nextState2 = null;

                if (transitions1.containsKey(state1)) {
                    nextState1 = transitions1.get(state1).get(symbol);
                }
                if (transitions2.containsKey(state2)) {
                    nextState2 = transitions2.get(state2).get(symbol);
                }

                if (nextState1 == null || nextState2 == null) {
                    continue;
                }

                String nextState = nextState1 + "," + nextState2;
                transitions.putIfAbsent(currentState, new HashMap<>());
                transitions.get(currentState).put(symbol, nextState);

                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    queue.add(nextState);
                }
            }
        }

        return new IntersectionResult(initialState, transitions, finalStates);
    }

    public static void printDFA(String initialState,
                                Map<String, Map<String, String>> transitions,
                                Set<String> finalStates) {
        System.out.println("Initial State: (" + initialState + ")");
        System.out.println("Final States: " + finalStates);
        System.out.println("Transitions: ");
        for (String state : transitions.keySet()) {
            for (String symbol : transitions.get(state).keySet()) {
                String nextState = transitions.get(state).get(symbol);
                System.out.println("(" + state + ")---" + symbol + " -> (" + nextState + ")");
            }
        }
    }
}
