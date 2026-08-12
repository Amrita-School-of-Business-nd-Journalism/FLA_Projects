import java.util.Set;


public abstract class OverallThings {
    protected String initialState;
    protected Set<String> finalStates;

    public OverallThings(String initialState, Set<String> finalStates){
        this.initialState = initialState;
        this.finalStates = finalStates;
    }

    public abstract boolean FAcompute(String inputStr);
}
