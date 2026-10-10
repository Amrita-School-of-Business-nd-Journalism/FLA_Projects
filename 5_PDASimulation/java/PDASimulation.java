import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PDASimulation {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = args.length > 0? new Scanner(new File(args[0])): new Scanner(System.in);
        try (scanner) {
            List<String> stack = new ArrayList<>();
            stack.add("z");

            System.out.println("Enter the initial state:");
            String initialState = scanner.nextLine().trim();

            System.out.println("Enter the number of final states:");
            int temp = Integer.parseInt(scanner.nextLine().trim());

            Set<String> finalStates = new HashSet<>();
            for (int i = 0; i < temp; i++) {
                System.out.println((i + 1) + "-Enter the final state:");
                finalStates.add(scanner.nextLine().trim());
            }

            Map<String, List<String[]>> transitions = new HashMap<>();
            System.out.println("Enter the number of transitions:");
            temp = Integer.parseInt(scanner.nextLine().trim());

            for (int i = 0; i < temp; i++) {
                System.out.println((i + 1) + "-Enter the current state:");
                String state1 = scanner.nextLine().trim();
                System.out.println((i + 1) + "-Enter the transition symbol:");
                String transitionSymbol = scanner.nextLine().trim();
                System.out.println((i + 1) + "-Enter the top of stack symbol:");
                String topStack = scanner.nextLine().trim();
                System.out.println((i + 1) + "-Enter the next state:");
                String state2 = scanner.nextLine().trim();
                System.out.println((i + 1) + "-Enter the symbol to push onto stack:");
                String pushSymbol = scanner.nextLine().trim();

                String key = state1 + "," + transitionSymbol + "," + topStack;
                transitions.putIfAbsent(key, new ArrayList<>());
                transitions.get(key).add(new String[]{state2, pushSymbol});
            }

            System.out.println("Enter the input string:");
            String inputString = scanner.nextLine();

            if (PDASimulationBase.pda(0, inputString, initialState,
                    transitions, finalStates, stack)) {
                System.out.println(inputString + " is accepted by the given PDA");
            } else {
                System.out.println(inputString + " is rejected by the given PDA");
            }
        }
    }
}
