import java.util.Map;
import java.util.Set;
import java.util.Vector;


public class NFA extends OverallThings{
    public Map<String,Vector<String>> transitions;

    public NFA(String initialState, Set<String> finalStates, Map<String,Vector<String>> transitions){
        super(initialState,finalStates);
        this.transitions = transitions;
    }

    @Override
    public boolean FAcompute(String inputStr){
        return callNFABT(0,inputStr,this.initialState);
    }

    public boolean callNFABT(int idx,String inputStr,String currentState){
        if (idx == inputStr.length()) {
            return this.finalStates.contains(currentState);
        }

        char symbol = inputStr.charAt(idx);
        String checkString = currentState + "," + symbol;
        if (this.transitions.containsKey(checkString)){
            Vector<String> temp = this.transitions.get(checkString);
            for (int i=0; i<temp.size();i++){
                String posState = temp.get(i);
                if (callNFABT(idx+ 1,inputStr,posState)){
                    return true;
                }
            }
        }
        return false;
    }
}
