import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DFAReduction {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // initial state
        System.out.print("Enter the initial state: ");
        String initialState = scanner.nextLine();

        int temp;

        // final states
        System.out.print("Enter number of final states in the DFA: ");
        temp = scanner.nextInt();
        scanner.nextLine();

        Set<String> finalStates = new HashSet<>();
        for(int i=0;i<temp;i++){
            System.out.print((i+1) + " Enter final state: ");
            finalStates.add(scanner.nextLine());
        }

        // transitions
        Map<String,Map<String,String>> transitions = new HashMap<>();
        Set<String> tsym = new HashSet<>();
        Set<String> states = new HashSet<>();
        states.add(initialState);

        System.out.print("Enter number of transitions in the DFA: ");
        temp = scanner.nextInt();
        scanner.nextLine();

        for(int i=0;i<temp;i++){
            System.out.print(i+1 + " Enter state -1: ");
            String state1 = scanner.nextLine();
            System.out.print(i+1 + " Enter state -2: ");
            String state2 = scanner.nextLine();
            System.out.print(i+1 + " Enter transition: ");
            String symbol = scanner.nextLine();

            tsym.add(symbol);

            states.add(state1);
            states.add(state2);

            transitions.putIfAbsent(state1,new HashMap<>());
            transitions.get(state1).put(symbol,state2);
        }

        // Reducing DFA & printing
        DFAReductionBase.ReducedDFA reduced = DFAReductionBase.DFAReduction(initialState,finalStates,states,transitions,tsym);
        DFAReductionBase.printDFA(reduced.initialState,reduced.transitions,reduced.finalStates);

        scanner.close();
    }
}