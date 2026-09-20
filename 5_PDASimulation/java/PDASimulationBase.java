import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PDASimulationBase {

    public static boolean pda(int index, String inputString, String currentState,Map<String, List<String[]>> transitions,Set<String> finalStates, List<String> stack) {
        if (index == inputString.length()) {
            if (stack.isEmpty()) {
                return true;
            }
            if (finalStates.contains(currentState)) {
                return true;
            }
        }
        if (stack.isEmpty()) {
            return false;
        }
        String top = stack.get(stack.size() - 1);
        if (index < inputString.length()) {
            String key = currentState + "," + inputString.charAt(index) + "," + top;
            if (transitions.containsKey(key)) {
                for (String[] transition : transitions.get(key)) {
                    List<String> updatedStack = updateStack(stack, transition[1]);
                    if (pda(index + 1, inputString, transition[0],
                            transitions, finalStates, updatedStack)) {
                        return true;
                    }
                }
            }
        }

        String epsilonKey = currentState + ",e," + top;
        if (transitions.containsKey(epsilonKey)) {
            for (String[] transition : transitions.get(epsilonKey)) {
                List<String> updatedStack = updateStack(stack, transition[1]);
                if (pda(index, inputString, transition[0],
                        transitions, finalStates, updatedStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> updateStack(List<String> stack, String pushSymbol) {
        List<String> updatedStack = new ArrayList<>(stack);
        updatedStack.remove(updatedStack.size() - 1);
        if (!pushSymbol.equals("e")) {
            for (int i = pushSymbol.length() - 1; i >= 0; i--) {
                updatedStack.add(String.valueOf(pushSymbol.charAt(i)));
            }
        }

        return updatedStack;
    }
}
