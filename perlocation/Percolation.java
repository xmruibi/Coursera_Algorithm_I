
import java.util.Random;

<<<<<<< HEAD
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

=======
>>>>>>> 1c9592926244055f0fc9ac791c98bc7b5210061d

public class Percolation {
	private boolean[] grid;
	private boolean BLOCKED = false;
	private boolean OPEN = true;
	private int side;
	private WeightedQuickUnionUF cellTree;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		side = N;
		// +2 are for additional top and bottom cells
		cellTree = new WeightedQuickUnionUF(N * N + 2);
		grid = new boolean[N * N + 2];
		for (int i = 0; i < N * N; i++) {
			grid[i] = BLOCKED;
		}
		grid[N * N] = OPEN;
		grid[N * N + 1] = OPEN;
	}

	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		if (!isInside(i, j))
			return;
		grid[getPosition(i, j)] = OPEN;

		if (i != 1 && isOpen(i - 1, j)) {
			cellTree.union(getPosition(i - 1, j), getPosition(i, j));
		} else if (i == 1) {
			cellTree.union(side * side, getPosition(i, j));
		}
		if (i != side && isOpen(i + 1, j)) {
			cellTree.union(getPosition(i + 1, j), getPosition(i, j));
		} else if (i == side) {
			cellTree.union(side * side + 1, getPosition(i, j));
		}
		if (j != 1 && isOpen(i + 1, j)) {
			cellTree.union(getPosition(i, j - 1), getPosition(i, j));
		}
		if (j != side && isOpen(i + 1, j)) {
			cellTree.union(getPosition(i, j + 1), getPosition(i, j));
		}
	}

	// check in this gird range
	private boolean isInside(int i, int j) {
		if (i < 0 || j < 0 || i > side || j > side)
			return false;
		return true;
	}

	// transfer from 1-d to 2-d
	private int getPosition(int row, int col) {
		return (side * (row - 1)) + (col - 1);
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if (!isInside(i, j))
			return false;
		return grid[getPosition(i, j)];

	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		isInside(i, j);
		return cellTree.connected(side * side, getPosition(i, j));
	}

	// does the system percolate?
	public boolean percolates() {
		return cellTree.connected(side * side, side * side + 1);
	}

	// test client, optional
	public static void main(String[] args) {
		int N = 5;
		Percolation perc = new Percolation(N);
		Random random = new Random();
		while (!perc.percolates()) {
			int row = random.nextInt(N) + 1;
			int column = random.nextInt(N) + 1;
			if (!perc.isOpen(row, column)) {
				perc.open(row, column);
			}
		}
	}
}
