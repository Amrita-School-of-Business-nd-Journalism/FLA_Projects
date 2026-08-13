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
                current_state = transitions.get(check_string);
            } else {
                return false;
            }
        }
        return finalStates.contains(current_state);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the inital state: ");
        String initial_state = scanner.nextLine();
        System.out.print("Enter number of final states: ");
        int temp = Integer.parseInt(scanner.nextLine());
        Set<String> final_state = new HashSet<>();
        for(int i=0;i<temp;i++){
            System.out.print(i+1 + " Enter final state: ");
            final_state.add(scanner.nextLine());
        }

        Map<String, String> transitions = new HashMap<>();
        System.out.print("Enter number of transitions: ");
        int temp2 = Integer.parseInt(scanner.nextLine());
        for(int i=0;i<temp2;i++){
            System.out.print(i+1 + " Enter state -1: ");
            String state1 = scanner.nextLine();
            System.out.print(i+1 + " Enter state -2: ");
            String state2 = scanner.nextLine();
            System.out.print(i+1 + " Enter transition: ");
            String tsym = scanner.nextLine();
            transitions.put(state1 + "," + tsym, state2);
        }

        System.out.print("Enter input string: ");
        String inputstr = scanner.nextLine();
        boolean result = DFA_Automata(inputstr, initial_state, transitions, final_state);
        if (result) {
            System.out.println(inputstr +" is accepted by the DFA.");
        } else {
            System.out.println(inputstr +" is not accepted by the DFA.");
        }   
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
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        // Common things 
        System.out.print("Enter the inital state: ");
        String initialState = scanner.nextLine();
    
        System.out.print("Enter number of final states: ");
        int temp = Integer.parseInt(scanner.nextLine());
        Set<String> finalStates = new HashSet<>();
        for(int i=0;i<temp;i++){
            System.out.print(i+1 + " Enter final state: ");
            finalStates.add(scanner.nextLine());
        }
        //Common thigns over 

        System.out.println("For DFA things: ");
        // DFA inputs
        Map<String, String> DFAtransitions = new HashMap<>();
        System.out.print("Enter number of transitions: ");
        int DFAtemp1 = Integer.parseInt(scanner.nextLine());
        for(int i=0;i<DFAtemp1;i++){
            System.out.print(i+1 + " Enter state -1: ");
            String DFAstate1 = scanner.nextLine();
            System.out.print(i+1 + " Enter state -2: ");
            String DFAstate2 = scanner.nextLine();
            System.out.print(i+1 + " Enter transition Symbol: ");
            String DFAtsym = scanner.nextLine();
            DFAtransitions.put(DFAstate1 + "," + DFAtsym, DFAstate2);
        }

        OverallThings DFAobj = new DFA(initialState,finalStates,DFAtransitions);

        // NFA inputs
        System.out.println("For NFA things: ");
        Map<String, Vector<String>> NFAtransitions = new HashMap<>();
        System.out.print("Enter number of transitions: ");
        int NFAtemp1 = Integer.parseInt(scanner.nextLine());
        for(int i=0;i<NFAtemp1;i++){
            System.out.print(i+1 + " Enter state -1: ");
            String NFAstate1 = scanner.nextLine();
            System.out.print(i+1 + " Enter transition Symbol: ");
            String NFAtsym = scanner.nextLine();
            
            System.out.println("Enter the number of state(s) it can transistion to: ");
            int NFAtemp2 = Integer.parseInt(scanner.nextLine());
            Vector<String> tostatestemp = new Vector<>();
            for(int j=0;j<NFAtemp2;j++){
                System.out.print(j+1 + " Enter the state to which it can transition: ");
                tostatestemp.add(scanner.nextLine());
            }

            NFAtransitions.put(NFAstate1 + "," + NFAtsym, tostatestemp);
        }

        OverallThings NFAobj = new NFA(initialState,finalStates,NFAtransitions);

        // Input string

        System.out.print("Enter the string to be checked: ");
        String inputStr = scanner.nextLine();

        // Calling and printing the output
        System.out.println("DFA result is: " + DFAobj.FAcompute(inputStr));
        System.out.println("NFA result is: " +  NFAobj.FAcompute(inputStr));
    }

}