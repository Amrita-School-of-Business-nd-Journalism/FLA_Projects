import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
}