/*+=================================================================+
  | Student: Jonathan Hilgendorf
  | Subject: Programming Languages, Homework 7
  | Duedate: November 22, 2019
  | 
  | Description: This is a triangle peg puzzle solver written in java
  |   that recursively checks ALL possible moves when cycling through
  |   each intermediate and initial board's empty spaces. For example,
  |   the Solve() runs through the initial board (only 0 = free), then
  |   checks all possible moves that move a peg to fill this space
  |   using a switch statement on '0' inside Mover(). From here, a new
  |   board is generated with the adjusted empty/filled spaces, and
  |   another Move() is used on this new board, which in turn checks
  |   for all possible moves that fill ONE of the new empty spaces...
  |   In this way, all legal moves are evaluated recursively, while a
  |   path string and counter is used to output the final set of moves
  |   to each solution, and to set when the solution is found (counter
  |   for remaining pegs = 1).
  |
  |   A Board class is created so that new objects can be generated
  |   to represent each initial, intermediate, and final board state
  |   and to hold a few revalvent variables and functions.
  |
  | * Line 364 can be commented out if the board final state is not
  |   desired; it serves more use when looking at incomplete states.
  | * In addition, incomplete states can be considered end-points if
  |   the check value is modified in Line 362 (1->x; 1 < x < 14).
  +=================================================================+
  | Board setup:                     >0<
  |                                1     2
  | * only 0 is free             3    4    5
  |  at game start *          6    7     8   9
  |                        10   11   12   13   14
  +=================================================================+
 */

// PROGRAM BODY
public class JMH_solver {
    // class for board with spaces and functions
    public static class Board {
        // board class variables
        boolean[] Spaces;
           
        // constructor
        public Board(boolean[] input) {
            boolean[] sep = new boolean[15];
            for(int i = 0; i <= 14; i++)
                sep[i] = input[i];
            Spaces = sep;
        }
        
        // functions to modify spaces for board
        void freeSpace(int i) {
            this.Spaces[i] = false;
        }
        void fillSpace(int i) {
            this.Spaces[i] = true;
        }
        boolean[] getSpaces() {
            return Spaces;
        }
        
        // print board
        void printBoard() {
            char[] marks; //temporary storage for spaces (easier to browse)
            marks = new char[15];
            for(int k = 0; k <= 14; k++) { //intialize to match spaces, but true=x, false=o
                if(Spaces[k])
                    marks[k] = 'X'; //true, or "filled"
                else
                    marks[k] = 'O'; //false, or "free"
            }
            System.out.println("    " + marks[0]);
            System.out.println("   " + marks[1] + " " + marks[2]);
            System.out.println("  " + marks[3] + " " + marks[4] + " " + marks[5]);
            System.out.println(" " + marks[6] + " " + marks[7] + " " + marks[8]
                    + " " + marks[9]);
            System.out.println(marks[10] + " " + marks[11] + " " + marks[12]
                    + " " + marks[13] + " " + marks[14]);
        }
    }
    
    // Switch over open space with all possible moves (HUGE)
    // This checks for moves possible according to the located empty space on the board
    // For each case #, # = the final, empty space to be filled (if possible, "X->X->O") 
    public static void Mover(int remaining, Board board, String route, int check) {
        switch(check) {
            // x->0
            case 0 :    if(board.Spaces[3]&&board.Spaces[1]) { //if occupied adjacents
                            Board boardn; //new board object
                            boolean[] inputSet = board.getSpaces(); //get setup from old board
                            boardn = new Board(inputSet); //initialize new board to match old board
                            boardn.freeSpace(3); boardn.freeSpace(1); boardn.fillSpace(0); //modify with movement
                            Solve(remaining-1, boardn, route + "[3->0]"); //check for more moves on the new board; add path to route
                        }
                        if(board.Spaces[5]&&board.Spaces[2]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(5); boardn.freeSpace(2); boardn.fillSpace(0);
                            Solve(remaining-1, boardn, route + "[5->0]");
                        }
                        break;
            // x->1
            case 1 :    if(board.Spaces[6]&&board.Spaces[3]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(6); boardn.freeSpace(3); boardn.fillSpace(1);
                            Solve(remaining-1, boardn, route + "[6->1]");
                        }
                        if(board.Spaces[8]&&board.Spaces[4]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(8); boardn.freeSpace(4); boardn.fillSpace(1);
                            Solve(remaining-1, boardn, route + "[8->1]");
                        }
                        break;
            // x->2
            case 2 :    if(board.Spaces[7]&&board.Spaces[4]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(7); boardn.freeSpace(4); boardn.fillSpace(2);
                            Solve(remaining-1, boardn, route + "[7->2]");
                        }
                        if(board.Spaces[9]&&board.Spaces[5]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(9); boardn.freeSpace(5); boardn.fillSpace(2);
                            Solve(remaining-1, boardn, route + "[9->2]");
                        }
                        break;
            // x->3
            case 3 :    if(board.Spaces[10]&&board.Spaces[6]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(10); boardn.freeSpace(6); boardn.fillSpace(3);
                            Solve(remaining-1, boardn, route + "[10->3]");
                        }
                        if(board.Spaces[12]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(12); boardn.freeSpace(7); boardn.fillSpace(3);
                            Solve(remaining-1, boardn, route + "[12->3]");
                        }
                        if(board.Spaces[0]&&board.Spaces[1]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(0); boardn.freeSpace(1); boardn.fillSpace(3);
                            Solve(remaining-1, boardn, route + "[0->3]");
                        }
                        break;
            // x->4
            case 4 :    if(board.Spaces[11]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(11); boardn.freeSpace(7); boardn.fillSpace(4);
                            Solve(remaining-1, boardn, route + "[11->4]");
                        }
                        if(board.Spaces[13]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(13); boardn.freeSpace(8); boardn.fillSpace(4);
                            Solve(remaining-1, boardn, route + "[13->4]");
                        }
                        break;
            // x->5
            case 5 :    if(board.Spaces[12]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(12); boardn.freeSpace(8); boardn.fillSpace(5);
                            Solve(remaining-1, boardn, route + "[12->5]");
                        }
                        if(board.Spaces[14]&&board.Spaces[9]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(14); boardn.freeSpace(9); boardn.fillSpace(5);
                            Solve(remaining-1, boardn, route + "[14->5]");
                        }
                        if(board.Spaces[0]&&board.Spaces[2]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(0); boardn.freeSpace(2); boardn.fillSpace(5);
                            Solve(remaining-1, boardn, route + "[0->5]");
                        }
                        break;
            // x->6
            case 6 :    if(board.Spaces[1]&&board.Spaces[3]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(1); boardn.freeSpace(3); boardn.fillSpace(6);
                            Solve(remaining-1, boardn, route + "[1->6]");
                        }
                        if(board.Spaces[8]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(8); boardn.freeSpace(7); boardn.fillSpace(6);
                            Solve(remaining-1, boardn, route + "[8->6]");
                        }
                        break;
            // x->7
            case 7 :    if(board.Spaces[2]&&board.Spaces[4]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(2); boardn.freeSpace(4); boardn.fillSpace(7);
                            Solve(remaining-1, boardn, route + "[2->7]");
                        }
                        if(board.Spaces[9]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(9); boardn.freeSpace(8); boardn.fillSpace(7);
                            Solve(remaining-1, boardn, route + "[9->7]");
                        }
                        break;
            // x->8
            case 8 :    if(board.Spaces[1]&&board.Spaces[4]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(1); boardn.freeSpace(4); boardn.fillSpace(8);
                            Solve(remaining-1, boardn, route + "[1->8]");
                        }
                        if(board.Spaces[6]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(6); boardn.freeSpace(7); boardn.fillSpace(8);
                            Solve(remaining-1, boardn, route + "[6->8]");
                        }
                        break;
            // x->9
            case 9 :    if(board.Spaces[2]&&board.Spaces[5]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(2); boardn.freeSpace(5); boardn.fillSpace(9);
                            Solve(remaining-1, boardn, route + "[2->9]");
                        }
                        if(board.Spaces[7]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(7); boardn.freeSpace(8); boardn.fillSpace(9);
                            Solve(remaining-1, boardn, route + "[7->9]");
                        }
                        break;
            // x->10
            case 10:    if(board.Spaces[3]&&board.Spaces[6]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(3); boardn.freeSpace(6); boardn.fillSpace(10);
                            Solve(remaining-1, boardn, route + "[3->10]");
                        }
                        if(board.Spaces[12]&&board.Spaces[11]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(12); boardn.freeSpace(11); boardn.fillSpace(10);
                            Solve(remaining-1, boardn, route + "[12->10]");
                        }
                        break;
            // x->11
            case 11:    if(board.Spaces[4]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(4); boardn.freeSpace(7); boardn.fillSpace(11);
                            Solve(remaining-1, boardn, route + "[4->11]");
                        }
                        if(board.Spaces[13]&&board.Spaces[12]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(13); boardn.freeSpace(12); boardn.fillSpace(11);
                            Solve(remaining-1, boardn, route + "[13->11]");
                        }
                        break;
            // x->12
            case 12:    if(board.Spaces[10]&&board.Spaces[11]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(10); boardn.freeSpace(11); boardn.fillSpace(12);
                            Solve(remaining-1, boardn, route + "[10->12]");
                        }
                        if(board.Spaces[3]&&board.Spaces[7]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(3); boardn.freeSpace(7); boardn.fillSpace(12);
                            Solve(remaining-1, boardn, route + "[3->12]");
                        }
                        if(board.Spaces[5]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(5); boardn.freeSpace(8); boardn.fillSpace(12);
                            Solve(remaining-1, boardn, route + "[5->12]");
                        }
                        if(board.Spaces[14]&&board.Spaces[13]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(14); boardn.freeSpace(13); boardn.fillSpace(12);
                            Solve(remaining-1, boardn, route + "[14->12]");
                        }
                        break;
            // x->13
            case 13:    if(board.Spaces[4]&&board.Spaces[8]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(4); boardn.freeSpace(8); boardn.fillSpace(13);
                            Solve(remaining-1, boardn, route + "[4->13]");
                        }
                        if(board.Spaces[11]&&board.Spaces[12]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(11); boardn.freeSpace(12); boardn.fillSpace(13);
                            Solve(remaining-1, boardn, route + "[11->13]");
                        }
                        break;
            // x->14
            case 14:    if(board.Spaces[5]&&board.Spaces[9]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(5); boardn.freeSpace(9); boardn.fillSpace(14);
                            Solve(remaining-1, boardn, route + "[5->14]");
                        }
                        if(board.Spaces[12]&&board.Spaces[13]) {
                            Board boardn;
                            boolean[] inputSet = board.getSpaces();
                            boardn = new Board(inputSet);
                            boardn.freeSpace(12); boardn.freeSpace(13); boardn.fillSpace(14);
                            Solve(remaining-1, boardn, route + "[12->14]");
                        }
                        break;
        }
    }
    
    // Iterative solve step to be called for next set of branches (Solve > Mover > Solve)
    public static void Solve(int remaining, Board board, String route) {
        // check if at the end (only 1 peg left)
        if(remaining == 1) { // CAN CHANGE '1' TO GET INCOMPLETE STATES (values 2-13)
            System.out.println(route); // output route to get to end THIS iteration
            board.printBoard(); // output board final state (INCOMPLETE STATES make good use of this, can comment out for space
        }
        
        // otherwise, cycle for more moves
        else for(int i = 0; i <= 14; i++) {
            if(board.Spaces[i] == false) { //found a blank space...?
                Board boardn; //create new board to iterate on
                boolean[] inputSet = board.getSpaces();
                boardn = new Board(inputSet);
                Mover(remaining, boardn, route, i); //check if there are moves to fill it
            }
        }
    }
    
    // initialization and start of program
    public static void main(String[] args) {
        System.out.println("Starting..."); //program starting!
        
        // Initialize board
        Board playboard;
        boolean[] initialState = {false, true, true, true, true, true,
            true,true, true, true, true, true, true, true, true}; //true = filled, false = free
        playboard = new Board(initialState);
        
        Solve(14, playboard, "S> "); //start solving from this board, initialize path with 'S> '
        
        System.out.println("Finished!"); //program is finished!
    }
}