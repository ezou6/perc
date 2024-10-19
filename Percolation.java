import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int[] sets;
    private int gridLength;
    private int openSites;
    private int virtualTop;
    private int virtualBottom;
    // private QuickFindUF QU;
    private WeightedQuickUnionUF WQU;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive.");
        }
        // QU = new QuickFindUF(n * n + 2);
        WQU = new WeightedQuickUnionUF(n * n + 2);
        gridLength = n;
        openSites = 0;
        grid = new boolean[n][n];
        virtualTop = gridLength * gridLength;
        virtualBottom = gridLength * gridLength + 1;
        /*for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                grid[r][c] = false; // initialize all sites as blocked
            }
        }*/
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkGrid(row, col);
        if (grid[row][col] == false) {
            grid[row][col] = true;
            openSites++;
        }
        if (row == 0) {
            WQU.union(virtualTop, conversion(row, col));
        }
        if (row == gridLength - 1) {
            WQU.union(virtualBottom, conversion(row, col));
        }
        if (row < gridLength - 1 && isOpen(row + 1, col)) { // bottom
            WQU.union(conversion(row, col), conversion(row + 1, col));
        }
        if (col < gridLength - 1 && isOpen(row, col + 1)) {
            WQU.union(conversion(row, col), conversion(row, col + 1));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            WQU.union(conversion(row, col), conversion(row - 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            WQU.union(conversion(row, col), conversion(row, col - 1));
        }
    }

    private void checkGrid(int row, int col) {
        if (row >= gridLength || row < 0) {
            throw new IllegalArgumentException("Must be valid row and/or column.");
        }
        if (col >= gridLength || col < 0) {
            throw new IllegalArgumentException("Must be valid row and/or column.");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkGrid(row, col);
        if (grid[row][col] == true) {
            return true;
        }
        else {
            return false;
        }
    }

    /*public void assemble() {
        sets = new int[gridLength * gridLength + 2];
        sets[0] = virtualTop;
        sets[gridLength + gridLength + 1] = virtualBottom;
        int count = 1;
        for (int r = 0; r < gridLength; r++) {
            for (int c = 0; c < gridLength; c++) {
                sets[count] = conversion(r, c);
                count++;
            }
        }
    }*/

    private int conversion(int row, int col) {
        checkGrid(row, col);
        return row * gridLength + col;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkGrid(row, col);
        return (WQU.connected(virtualTop, conversion(row, col)));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return WQU.connected(virtualTop, virtualBottom);
    }

    /*private int calculateP() {
        if (percolates()) {
            return openSites / (gridLength * gridLength);
        }
        else {
            return 0;
        }
    }*/

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        Percolation test = new Percolation(size);
        int count = args.length;
        for (int i = 1; count >= 2; i += 2) {
            int open_row = Integer.parseInt(args[i]);
            int open_col = Integer.parseInt(args[i + 1]);
            count -= 2;
        }


        // Unit-tests

        // Constructor Percolation()
        int sz = 10;
        Percolation test1 = new Percolation(sz);
        System.out.println("Constructor Test 1:" + (test1.gridLength == sz));
        System.out.println("Constructor Test 2:" + (test1.virtualBottom == sz * sz + 1));

        // open(row, col)
        test1.open(2, 2);
        System.out.println("open() test: " + (test1.openSites == 1));

        // isOpen(row, col)
        System.out.println("isOpen() test: " + (test1.grid[2][2] == true));

        // isFull(row, col)
        test1.open(0, 2);
        System.out.println("isFull() test 1: " + (test1.isFull(2, 2) == true));
        // should evaluate to false
        test1.open(1, 2);
        System.out.println("isFull() test 2: " + (test1.isFull(2, 2) == true));

        // numberOfOpenSites()
        System.out.println("numberOfOpenSites(): " + (test1.numberOfOpenSites() == 3));

        // percolates()
        System.out.println("percolates() test1:" + (test1.percolates() == true));
        // should evaluate to false
        for (int r = 3; r < sz; r++) {
            test1.open(r, 2);
        }
        System.out.println("percolates() test2:" + (test1.percolates() == true));
    }

}

