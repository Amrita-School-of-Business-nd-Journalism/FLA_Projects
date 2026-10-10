import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class NFAToDFA {
    public static void main(String[] args) throws FileNotFoundException{
        try (Scanner scanner = args.length > 0? new Scanner(new File(args[0])): new Scanner(System.in)) {
        
        //inital state
        System.out.println("Enter the initial state of the NFA:");
        String initialState = scanner.nextLine().trim();

        int temp;
        //final states
        System.out.println("Enter number of final states in the NFA:");
        temp = Integer.parseInt(scanner.nextLine().trim());

        Set<String> finalStates = new HashSet<>();
        for(int i=0;i<temp;i++){
            System.out.println(i+1 + " Enter final state:");
            finalStates.add(scanner.nextLine().trim());
        }

        //transitions
        Map<String,Map<String,Set<String>>> nfaTransitions = new HashMap<>();
        Set<String> tsym = new HashSet<>();
        System.out.println("Enter number of transitions in the NFA:");
        temp = Integer.parseInt(scanner.nextLine().trim());

        for (int i=0;i<temp;i++){
            System.out.println(i+1 + " Enter state -1:");
            String state1 = scanner.nextLine().trim();
            System.out.println(i+1 + " Enter state -2:");
            String state2 = scanner.nextLine().trim();
            System.out.println(i+1 + " Enter transition:");
            String symbol = scanner.nextLine().trim();

            tsym.add(symbol);
            nfaTransitions.putIfAbsent(state1, new HashMap<>());
            nfaTransitions.get(state1).putIfAbsent(symbol, new HashSet<>());
            nfaTransitions.get(state1).get(symbol).add(state2);
        }

        // Converting NFA to DFA
        Map<Set<String>,Map<String,Set<String>>> dfaTransitions = NFAToDFAConvert.nfatodfa(initialState, nfaTransitions, tsym);
        //printing the states and transistions of the DFA
        NFAToDFAConvert.printDFA(initialState, dfaTransitions, finalStates);

        }
    }
}