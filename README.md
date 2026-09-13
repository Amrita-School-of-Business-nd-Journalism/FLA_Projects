# FLA — Formal Languages & Automata Theory (Multi-language)

![FLA](https://img.shields.io/badge/FLA-multi--language-6f42c1) ![Java](https://img.shields.io/badge/Java-Ready-e11f21) ![Python](https://img.shields.io/badge/Python-Jupyter%20Notebooks-ffd343) ![License](https://img.shields.io/badge/license-MIT-blue)

A practical collection of core **Formal Languages and Automata Theory (FLA)** algorithms implemented in both **Java** and **Python**. Each project is self-contained with modular base logic and interactive CLI / notebook runners.

---

## Projects Overview

| # | Project | Description | Java | Python |
|---|---|---|---|---|
| **1** | [1_AutomataSimulation/](1_AutomataSimulation/) | DFA & NFA string acceptance simulation | [Java](1_AutomataSimulation/java/) | [Jupyter](1_AutomataSimulation/python/Automata_Python.ipynb) |
| **2** | [2_NFAtoDFA/](2_NFAtoDFA/) | NFA to DFA conversion (Subset Construction) | [Java](2_NFAtoDFA/java/) | [Jupyter](2_NFAtoDFA/python/NFAtoDFA.ipynb) |
| **3** | [3_OptimiseDFA/](3_OptimiseDFA/) | DFA Minimisation (Unreachable elimination + Partition refinement) | [Java](3_OptimiseDFA/java/) | [Jupyter](3_OptimiseDFA/python/DFAReduction.ipynb) |
| **4** | [4_IntersectionDFA/](4_IntersectionDFA/) | Product DFA Intersection ($M_1 \cap M_2$) | [Java](4_IntersectionDFA/java/) | [Jupyter](4_IntersectionDFA/python/intersectionDFA.ipynb) |

---

## Repository Structure

```text
├── 1_AutomataSimulation/
│   ├── java/                   # DFA & NFA simulation base classes & runner
│   ├── python/                 # Automata simulation Jupyter notebook
│   └── compiledFinalJava/      # Compiled Java bytecode (.class)
├── 2_NFAtoDFA/
│   ├── java/                   # Subset construction base & runner
│   ├── python/                 # NFA to DFA conversion Jupyter notebook
│   └── compiledFinalJava/      # Compiled Java bytecode (.class)
├── 3_OptimiseDFA/
│   ├── java/                   # DFA reduction base & runner
│   ├── python/                 # DFA reduction Jupyter notebook
│   └── compiledFinalJava/      # Compiled Java bytecode (.class)
├── 4_IntersectionDFA/
│   ├── java/                   # DFA intersection product base & runner
│   ├── python/                 # DFA intersection Jupyter notebook
│   └── compiledFinalJava/      # Compiled Java bytecode (.class)
└── README.md                   # Repository documentation
```

---

## Quick Start (Java)

### 1. Compile all projects
You can compile all Java modules into their respective output folders from the repository root:

```bash
# 1. Automata Simulation
javac -d 1_AutomataSimulation/compiledFinalJava 1_AutomataSimulation/java/*.java

# 2. NFA to DFA
javac -d 2_NFAtoDFA/compiledFinalJava 2_NFAtoDFA/java/*.java

# 3. DFA Optimisation
javac -d 3_OptimiseDFA/compiledFinalJava 3_OptimiseDFA/java/*.java

# 4. DFA Intersection
javac -d 4_IntersectionDFA/compiledFinalJava 4_IntersectionDFA/java/*.java
```

### 2. Run any project

- **Run Automata Simulation:**
  ```bash
  java -cp 1_AutomataSimulation/compiledFinalJava DFANFAAutomata
  ```

- **Run NFA to DFA Converter:**
  ```bash
  java -cp 2_NFAtoDFA/compiledFinalJava NFAToDFA
  ```

- **Run DFA Reduction :**
  ```bash
  java -cp 3_OptimiseDFA/compiledFinalJava DFAReduction
  ```

- **Run DFA Intersection:**
  ```bash
  java -cp 4_IntersectionDFA/compiledFinalJava IntersectionDFA
  ```

---

## Quick Start (Python)

All Python implementations are provided as interactive Jupyter Notebooks:

```bash
jupyter notebook
```
Navigate to any project directory and open the corresponding notebook:
- `1_AutomataSimulation/python/Automata_Python.ipynb`
- `2_NFAtoDFA/python/NFAtoDFA.ipynb`
- `3_OptimiseDFA/python/DFAReduction.ipynb`
- `4_IntersectionDFA/python/intersectionDFA.ipynb`

---

## What's Included

### 1. Automata Simulation (`1_AutomataSimulation`)
- **DFA Simulator**: Evaluates deterministic string transitions and checks final state membership.
- **NFA Simulator**: Evaluates non-deterministic transitions using recursive backtracking across transition branches.
- **Interactive Testing**: Continuously test test strings (including empty string $\lambda$) until typing `exit`.

### 2. NFA to DFA (`2_NFAtoDFA`)
- Implements the classic **Subset Construction (Powerset)** algorithm.
- Computes transition tables for compound subset states and automatically handles trap states.
- Identifies and outputs starting state, full transition table, and DFA final states.

### 3. DFA Optimisation (`3_OptimiseDFA`)
- Eliminates all dead/unreachable states from the initial state and then partitions states into accepting ($P_1$) and non-accepting ($P_2$) equivalence groups, iteratively refining until minimal equivalent states are obtained.
- Generates the minimal reduced DFA transitions and final state mappings.

### 4. DFA Intersection (`4_IntersectionDFA`)
- Computes the synchronous product automaton $M_1 \times M_2$ for the intersection language $L(M_1) \cap L(M_2)$.
- Identifies product state reachability via BFS queue traversal.
- Defines accepting states as $(q_1, q_2)$ where $q_1 \in F_1 \land q_2 \in F_2$.

---

## License
This repository is licensed under the MIT License.
