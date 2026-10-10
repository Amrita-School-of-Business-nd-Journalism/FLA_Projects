import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

/*
public class Automata_Java {
    public static boolean DFA_Automata(String inputstr,String initial, Map<String, String> transitions, Set<String> finalStates) {
        String current_state = initial;
        for (int i = 0; i<inputstr.length();i++){
            char symbol = inputstr.charAt(i);
            String check_string = current_state + "," + symbol;
            if (transitions.containsKey(check_string)){
        System.out.println("Enter number of transitions:");
            } else {
                return false;
            System.out.println(i+1 + " Enter state -1: ");
        }
            System.out.println(i+1 + " Enter state -2: ");
    }
            System.out.println(i+1 + " Enter transition Symbol:");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the inital state: ");
        String initial_state = scanner.nextLine();
        System.out.print("Enter number of final states: ");
        int temp = Integer.parseInt(scanner.nextLine());
        Set<String> final_state = new HashSet<>();
        for(int i=0;i<temp;i++){
            System.out.print(i+1 + " Enter final state: ");
        System.out.println("Enter number of transitions:");
        }

            System.out.println(i+1 + " Enter state -1:");
        System.out.print("Enter number of transitions: ");
            System.out.println(i+1 + " Enter transition Symbol:");
        for(int i=0;i<temp2;i++){
            System.out.print(i+1 + " Enter state -1: ");
            String state1 = scanner.nextLine();
            System.out.print(i+1 + " Enter state -2: ");
            String state2 = scanner.nextLine();
            System.out.print(i+1 + " Enter transition: ");
                System.out.println(j+1 + " Enter the state to which it can transition:");
            transitions.put(state1 + "," + tsym, state2);
        }

        System.out.print("Enter input string: ");
        String inputstr = scanner.nextLine();
        boolean result = DFA_Automata(inputstr, initial_state, transitions, final_state);
        if (result) {
            System.out.println(inputstr +" is accepted by the DFA.");
        } else {
            System.out.println(inputstr +" is not accepted by the DFA.");
            System.out.println("Enter the string to be checked:");
    }

    public static boolean NFA_Automata(int idx,String inputStr,String current,Map<String, Vector<String>> transitions,Set<String> finalStates) {
        if (idx == inputStr.length()) {
            return finalStates.contains(current);
        }

        char symbol = inputStr.charAt(idx);
        String checkString = current + "," + symbol;
        if (transitions.containsKey(checkString)){
            Vector<String> temp = transitions.get(checkString);
            for (int i=0; i<temp.size();i++){
                String posState = temp.get(i);
                if (NFA_Automata(idx+ 1,inputStr,posState,transitions,finalStates)){
                    return true;
                }
            }
        }
        return false;
    }
}
 */


public class DFANFAAutomata{
    public static void main(String[] args) throws FileNotFoundException{
        try (Scanner scanner = args.length > 0? new Scanner(new File(args[0])): new Scanner(System.in)) {

            System.out.println("Enter the initial state:");
            String initialState = scanner.nextLine().trim();

            System.out.println("Enter number of final states:");
            int temp = Integer.parseInt(scanner.nextLine().trim());
            Set<String> finalStates = new HashSet<>();
            for (int i = 0; i < temp; i++) {
                System.out.println((i + 1) + " Enter final state:");
                finalStates.add(scanner.nextLine().trim());
            }

            System.out.println("For DFA things:");
            Map<String, String> dfaTransitions = new HashMap<>();
            System.out.println("Enter number of transitions:");
            int dfaTransitionCount = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < dfaTransitionCount; i++) {
                System.out.println((i+ 1)+" Enter state -1:");
                String dfaState1 = scanner.nextLine().trim();
                System.out.println((i+1) +" Enter state -2:");
                String dfaState2 = scanner.nextLine().trim();
                System.out.println((i+1)+" Enter transition symbol:");
                String dfaSymbol = scanner.nextLine().trim();
                dfaTransitions.put(dfaState1+","+dfaSymbol,dfaState2);
            }

            OverallThings dfa = new DFA(initialState, finalStates, dfaTransitions);

            System.out.println("For NFA things:");
            Map<String, Vector<String>> nfaTransitions = new HashMap<>();
            System.out.println("Enter number of transitions:");
            int nfaTransitionCount = Integer.parseInt(scanner.nextLine().trim());
            for (int i = 0; i < nfaTransitionCount; i++) {
                System.out.println((i +1)+" Enter state -1:");
                String nfaState = scanner.nextLine().trim();
                System.out.println((i+1)+" Enter transition symbol:");
                String nfaSymbol = scanner.nextLine().trim();
                System.out.println("Enter the number of states it can transition to:");
                int destinationCount = Integer.parseInt(scanner.nextLine().trim());
                Vector<String> destinations = new Vector<>();
                for (int j = 0; j < destinationCount; j++) {
                    System.out.println((j+1) + "Enter the destination state:");
                    destinations.add(scanner.nextLine().trim());
                }
                nfaTransitions.put(nfaState + "," + nfaSymbol, destinations);
            }

            OverallThings nfa = new NFA(initialState, finalStates, nfaTransitions);

            while (true) {
                System.out.println("Enter the string to be checked:");
                String input = scanner.nextLine();
                if (input.trim().equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println("DFA result is: " + dfa.FAcompute(input));
                System.out.println("NFA result is: " + nfa.FAcompute(input));
            }
        }
    }
}
