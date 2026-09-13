import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IntersectionDFA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int temp;

        System.out.println("---------- For DFA - 1 ----------");

        System.out.print("Enter the initial state: ");
        String initialState1 = scanner.nextLine().trim();

        System.out.print("Enter the number of final states: ");
        temp = Integer.parseInt(scanner.nextLine().trim());

        Set<String> finalState1 = new HashSet<>();
        for (int i = 0; i < temp; i++) {
            System.out.print((i + 1) + " Enter final state: ");
            finalState1.add(scanner.nextLine().trim());
        }

        Set<String> tsym = new HashSet<>();
        Map<String, Map<String, String>> transitions1 = new HashMap<>();

        System.out.print("Enter the number of transitions: ");
        temp = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < temp; i++) {
            System.out.print(i + 1 + " Enter source state: ");
            String state1 = scanner.nextLine().trim();
            System.out.print(i + 1 + " Enter destination state: ");
            String state2 = scanner.nextLine().trim();
            System.out.print(i + 1 + " Enter the transition symbol: ");
            String symbol = scanner.nextLine().trim();

            tsym.add(symbol);
            transitions1.putIfAbsent(state1, new HashMap<>());
            transitions1.get(state1).put(symbol, state2);
        }

        System.out.println("---------- For DFA - 2 ----------");

        System.out.print("Enter the initial state: ");
        String initialState2 = scanner.nextLine().trim();

        System.out.print("Enter the number of final states: ");
        temp = Integer.parseInt(scanner.nextLine().trim());

        Set<String> finalState2 = new HashSet<>();
        for (int i = 0; i < temp; i++) {
            System.out.print((i + 1) + " Enter final state: ");
            finalState2.add(scanner.nextLine().trim());
        }

        Map<String, Map<String, String>> transitions2 = new HashMap<>();

        System.out.print("Enter the number of transitions: ");
        temp = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < temp; i++) {
            System.out.print(i + 1 + " Enter source state: ");
            String state1 = scanner.nextLine().trim();
            System.out.print(i + 1 + " Enter destination state: ");
            String state2 = scanner.nextLine().trim();
            System.out.print(i + 1 + " Enter the transition symbol: ");
            String symbol = scanner.nextLine().trim();

            tsym.add(symbol);
            transitions2.putIfAbsent(state1, new HashMap<>());
            transitions2.get(state1).put(symbol, state2);
        }

            IntersectionDFABase.IntersectionResult result = IntersectionDFABase.intersectionDFA(
                initialState1, finalState1, transitions1,
                initialState2, finalState2, transitions2, tsym);

            IntersectionDFABase.printDFA(result.initialState, result.transitions, result.finalStates);

            scanner.close();
    }
}
