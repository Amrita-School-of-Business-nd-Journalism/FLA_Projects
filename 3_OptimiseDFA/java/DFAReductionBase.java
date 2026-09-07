import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


public class DFAReductionBase {
    static class ReducedDFA{
        Set<String> initialState;
        Map<Set<String>,Map<String,Set<String>>> transitions;
        Set<Set<String>> finalStates;

        ReducedDFA(Set<String> initialState,Map<Set<String>,Map<String,Set<String>>> transitions,Set<Set<String>> finalStates){
            this.initialState = initialState;
            this.transitions = transitions;
            this.finalStates = finalStates;
        }
    }

    public static ReducedDFA DFAReduction(String initialState,Set<String> finalStates,Set<String> states,Map<String,Map<String,String>> transitions,Set<String> tsym){
        Set<String> reachableStates = new HashSet<>();
        Queue<String> temp = new ArrayDeque<>();
        temp.add(initialState);
        reachableStates.add(initialState);

        while(!temp.isEmpty()){
            String currentState = temp.poll();
            for(String symbol : tsym){
                if(transitions.containsKey(currentState) &&
                        transitions.get(currentState).containsKey(symbol)){
                    String nextState =
                            transitions.get(currentState).get(symbol);
                    if(!reachableStates.contains(nextState)){
                        reachableStates.add(nextState);
                        temp.add(nextState);

                    }
                }
            }
        }

        states.retainAll(reachableStates);
        finalStates.retainAll(reachableStates);

        Map<String,Map<String,String>> tempTransitions = new HashMap<>();
        for(String state : transitions.keySet()){
            if(!reachableStates.contains(state))
                continue;
            for(String symbol : transitions.get(state).keySet()){
                String nextState = transitions.get(state).get(symbol);
                if(reachableStates.contains(nextState)){
                    tempTransitions.putIfAbsent(state,new HashMap<>());
                    tempTransitions.get(state).put(symbol,nextState);
                }
            }
        }
        transitions = tempTransitions;
        //split part

        Set<String> p1 = new HashSet<>(finalStates);
        Set<String> p2 = new HashSet<>(states);
        p2.removeAll(p1);

        List<Set<String>> temp1 = new ArrayList<>();

        if(!p1.isEmpty()){
            temp1.add(new HashSet<>(p1));
        }

        if(!p2.isEmpty()){
            temp1.add(new HashSet<>(p2));
        }

        while(true){
            List<Set<String>> temp2 = new ArrayList<>();
            for(Set<String> i : temp1){
                if(i.size()==1){
                    temp2.add(i);
                    continue;
                }
                Map<List<Integer>,Set<String>> sg = new HashMap<>();
                for(String state : i){
                    List<Integer> temp3 = new ArrayList<>();
                    for(String symbol : new TreeSet<>(tsym)){ // order
                        String nextState = null;
                        if(transitions.containsKey(state) && transitions.get(state).containsKey(symbol)){
                            nextState = transitions.get(state).get(symbol);
                        }
                        Integer nextGroupidx = null;
                        if(nextState != null){
                            for(int j=0;j<temp1.size();j++){
                                if(temp1.get(j).contains(nextState)){
                                    nextGroupidx = j;
                                    break;
                                }
                            }
                        }
                        temp3.add(nextGroupidx);
                    }
                    sg.putIfAbsent(temp3,new HashSet<>());
                    sg.get(temp3).add(state);
                }
                temp2.addAll(sg.values());
            }
            if(new HashSet<>(temp2).equals(new HashSet<>(temp1))){
                break;
            }
            temp1 = temp2;
        }

        Map<String,Set<String>> groupMap = new HashMap<>();
        for(Set<String> i : temp1){
            for(String j : i){
                groupMap.put(j,i);
            }
        }
        Set<String> reducedInitialState =groupMap.get(initialState);
        Set<Set<String>> reducedFinalStates = new HashSet<>();
        for(String state : finalStates){
            reducedFinalStates.add(groupMap.get(state));
        }
        Map<Set<String>,Map<String,Set<String>>> reducedTransitions = new HashMap<>();
        for(String state : transitions.keySet()){
            for(String symbol : transitions.get(state).keySet()){
                String nextState = transitions.get(state).get(symbol);
                Set<String> fromState = groupMap.get(state);
                Set<String> toState = groupMap.get(nextState);
                reducedTransitions.putIfAbsent(fromState,new HashMap<>());
                reducedTransitions.get(fromState).put(symbol,toState);
            }
        }
        return new ReducedDFA(reducedInitialState,reducedTransitions,reducedFinalStates);
    }

    // printing
    public static void printDFA(Set<String> Startstate,Map<Set<String>,Map<String,Set<String>>> Transitions,Set<Set<String>> FinalStates){
        System.out.println("DFA Transitions:");
        System.out.println("Starting State: " + Startstate);
        Set<Set<String>> tempfinal = new HashSet<>();
        for(Set<String> state : Transitions.keySet()){
            for(String symbol : Transitions.get(state).keySet()){
                System.out.println(state + " --" + symbol + "--> "+ Transitions.get(state).get(symbol));
                if(FinalStates.contains(state)){
                    tempfinal.add(state);
                }
            }
        }
        System.out.println("DFA Final States:");
        for(Set<String> state : tempfinal){
            System.out.println(state);
        }
    }
}