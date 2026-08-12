import java.util.Map;
import java.util.Set;

public class DFA extends OverallThings{
    public Map<String, String> transitions;

    public DFA(String initialState, Set<String> finalStates, Map<String,String> transitions){
        super(initialState,finalStates);
        this.transitions = transitions;
    }

    @Override
    public boolean FAcompute(String inputString){
        String currentState = this.initialState;

        for (int i = 0; i<inputString.length();i++){
            char symbol = inputString.charAt(i);
            String checkString = currentState + "," + symbol;
            if (this.transitions.containsKey(checkString)){
                currentState = this.transitions.get(checkString);
            } else {
                return false;
            }
        }
        return this.finalStates.contains(currentState);
    }
}