Programming Assignment 1: Percolation

Answer these questions after you implement your solution.

/* *****************************************************************************
 *  Describe the data structures (i.e., instance variables) you used to
 *  implement the Percolation API.
 **************************************************************************** */

grid is a 2D array of boolean values that represent the n by n grid where all values
are initialized to false (blocked site). if a value is true, it corresponds to an empty site.

sets is a 1D array of integer values that represent the 2D grid converted into sets of unions distinguished
by leaders alongside the theoretical virtualTop and virtualBottom sites. It follows the definition of
QuickUnion and QuickFind algorithim data structure where sites[] is of specified length and sites[i] is the leader of
the set containing element i.

gridLength is the specified dimension of the grid, measured as gridLength * gridLength.

openSites is the integer value representing the number of sites that are empty and evaluate to true within the grid.

virtualTop is an integer value that represents a unique ID of a theoretical set existing above
the first row of grid[][] that will be implemented and included in the conversion of
our 2D array of boolean values to a 1D array of integer values sets[]. virtualTop is assigned a unique ID as by the definition
of QuickUnion, which cannot union sets with elements of the same value.

virtualBottom is similar to virtualTop except it exists below the last row of grid[][].

QU and WQU represent my QuickUnionUF and WeightedQuickUnionUF objects respectively, which come in handy
when calling their methods to union open sites together.

/* *****************************************************************************
 *  Briefly describe the algorithms you used to implement each method in
 *  the Percolation API.
 **************************************************************************** */
open(): checks if a site is empty by referring to the 2D boolean array. A site is marked empty if its corresponding
row and column aligns with a true value.

isOpen(): Accomplishes three tasks: 1.) checks if a site exists within the grid according to a valid row and bottom.
2.) Opens a site if it wasn't already opened before by setting its corresponding boolean value in the 2D grid from false to true.
3.) Unions direct neighbors of the site that are to the left, right, north and south, and the virtual top and the virtual bottom if they
are open sites

isFull(): checks if an open site is connected to a site in the first row, and thus, the virtual Top.

numberOfOpenSites(): counts the number of empty sites by returning instance variable openSites, which is modified
by the open() method checking for a particular site to be empty.

percolates(): checks if the grid percolates, or can conduct from first row to last row through directly connected open sites
to the left, right, north and south. Checks if virtualTop and virtualBottom are connected, which by inference, means that a site in the
first row and a site in the last row are connected.

/* *****************************************************************************
 *  First, implement Percolation using QuickFindUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
5           0.002
10          0.004
100         1.072
200         16.873
250         40.281
275         54.933
300         82.554

/* *****************************************************************************
 *  Describe the strategy you used for selecting the values of n.
 **************************************************************************** */

According to the Doubling Law, we expect that the runtime will follow T(n) = a * n^b and we
can isolate a ratio with log. When I approached n = 200 trials, I instinctively tried to
double the trials to 400. However, my computer refused to compile the trails in an adequate time
and thus, I resorted to 300, which still ran over a minute. We can estimate that the max
number of trials ran by my computer in under a minute is n = ~280.

/* *****************************************************************************
 *  Next, implement Percolation using WeightedQuickUnionUF.
 *  What is the largest value of n that PercolationStats can handle in
 *  less than one minute on your computer when performing T = 100 trials?
 *
 *  Fill in the table below to show the values of n that you used and the
 *  corresponding running times. Use at least 5 different values of n.
 **************************************************************************** */

 T = 100

 n          time (seconds)
--------------------------
5           0.002
10          0.003
100         0.047
200         0.155
400         0.661
1600        19.16
2000        35.697
2400        56.49

Max n trials under a minute: ~2450


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */

I didn't account for the backwash and thus, sites that are connected to the virtual bottom
and virtual top, which themselves are connected to an open full site that percolates, will be marked
as part of the existing solution or blue.



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */

I struggled to figure out a way to get PercolationVisualizer and PercolationStats to run at first since
the java files both refused to build and my Percolation java file contained errors, such as
defining virtualTop and virtualBottom before the constructor. In addition, I missed the key detail of
declaring grid as an array of double values rather than integer values, which thus, led my percolationStats
to floor to 0. Lastly, I wanted to mention how I failed to union the virtualtop and virtual bottom to open sites
in the first or last row respectively in my open() method. Thus, anything I tested the percolates() method, it would
always return as false.


/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */

I haven't programmed for a bit since AP CSA before the summer, so the Percolation assignment
was a fast-paced introduction back into coding. I think the implementation of the project itself
wasn't terribly hard, but understanding the underlying theory and concepts behind
Percolation and QuickUnion led me to temper around unnecessarily with the code. I appreciate the
example text files offered however and the Percolation Visualizer. They were incredibly helpful.
