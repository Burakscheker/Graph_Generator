# 🚀 Graph Generator & Analyzer

This project is a Java-based console application developed to visualize, analyze, and perform operations on Graph Theory algorithms.

Developed using the **Enigma Console** library, this tool allows users to generate graphs, perform matrix calculations, and test the structural properties of graphs (isomorphism, bipartite, complete, etc.).

## 🌟 Featured Highlights

### 1. Graph Generation and Visualization
* **Degree Sequence:** Automatically generates graphs matching a user-input degree sequence (using the Havel-Hakimi algorithm or a randomized method).
* **Visual Drawing:** Draws vertices and edges on the console screen using ASCII/Character-based representations.
* **Dynamic Placement:** Vertices are placed randomly on a grid ensuring they do not overlap.

### 2. Graph Analysis (Graph Properties)
The following tests can be performed on generated or loaded graphs:
* **Connectivity:** Checks if the graph is connected (a single piece) using DFS.
* **Complete Graph:** Tests whether all vertices are connected to every other vertex.
* **Bipartite:** Checks if the graph can be divided into two independent sets.
* **Cycle, Wheel, and Star Graphs:** Analyzes whether the graph conforms to a `Cycle`, `Wheel`, or `Star` structure.
* **C3 Cycle:** Scans the graph for triangular (Triangle) structures.
* **Isolated Vertices:** Detects vertices that have no connections.

### 3. Graph Isomorphism
* Checks for structural equivalence between the **Main Graph** and a **Secondary Graph** currently in the system.
* If the graphs are isomorphic, it displays the mapping of vertices (e.g., `A <-- C`, `B <-- D`).

### 4. Matrix Operations
* **Adjacency Matrix:** Generates the matrix representation of the graph.
* **Power Matrices ($R^n$):** Calculates the $R^2, R^3 ... R^n$ reachability matrices of the graph.
* **Transitive Closure ($R^*$):** Derives the full reachability matrix of the graph based on the logic of the Floyd-Warshall algorithm.
* **Shortest Path Matrix ($R_{min}$):** Calculates the shortest distances between vertices.

### 5. Saving and Management (Depot System)
* Graphs can be saved to and loaded from `.txt` files.
* **9 Depots:** Enables quick switching and copying among 9 different graph slots held in memory.

## 🛠️ Installation and Execution

The project is dependent on the `Enigma-Edited2.jar` library.

1. Download the project.
2. Run the `GraphGeneratorTest.java` file located in the `src` folder.
3. **Note:** If you are using an IDE (IntelliJ, Eclipse), ensure that the `Enigma-Edited2.jar` file is added to the "Build Path" or "Library" section of the project.

## 💻 Keyboard Controls

The application is managed entirely via keyboard shortcuts:

| Key | Function |
| :--- | :--- |
| **Z** | **Graph Generation Menu:** Opens the menu for generating new graphs and matrix operations. |
| **X** | **Graph Test Menu:** Opens the menu that tests graph properties (Isomorphism, Bipartite, etc.). |
| **C** | **Graph Transfer Menu:** Opens the menu for copying, saving, and loading graphs. |
| **D** | **Drawing Mode:** Toggles the edge drawing style (Straight line / Character-based). |

### Transfer Menu Shortcuts (While C Menu is Open):
* **G:** Copies the Main graph to the Secondary graph.
* **H:** Copies the Secondary graph to the Main graph.
* **S / L:** Saves/Loads the Main graph to/from a file (`graph1.txt`).
* **Q - O (Q,W,E...):** Copies the Main graph to Depot 1-9.
* **1 - 9:** Loads the graph from Depot 1-9 to the Main graph.
* **D / F:** Batch saves/loads depots to/from files.

## 📂 File Structure

* `src/Graph.java`: Graph data structure, drawing algorithms, and Havel-Hakimi logic.
* `src/UIManager.java`: User interface, menus, and file operations.
* `src/MatrixCalculator.java`: Matrix multiplication and Floyd-Warshall algorithms.
* `src/GraphTestMenu.java`: Isomorphism and graph type analyses.
* `src/Game.java`: Additional module for testing mouse/keyboard input with the Enigma library.

---

### Developer : Ömür Burak Şeker
