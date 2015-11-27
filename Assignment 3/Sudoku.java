/**
 *
 * @author Ali Murtaza Sharif
 */public class Sudoku {
	public static final int[][] sudokuSolution1 = {
				{5,3,4,6,7,8,9,1,2},
				{6,7,2,1,9,5,3,4,8},
				{1,9,8,3,4,2,5,6,7},
				{8,5,9,7,6,1,4,2,3},
				{4,2,6,8,5,3,7,9,1},
				{7,1,3,9,2,4,8,5,6},
				{9,6,1,5,3,7,2,8,4},
				{2,8,7,4,1,9,6,3,5},
				{3,4,5,2,8,6,1,7,9}
			};

	public static final int[][] sudokuSolution2 = {
				{6,8,4,9,5,7,2,3,1},
				{5,1,7,4,2,3,9,8,6},
				{9,2,3,6,8,1,5,7,4},
				{4,3,5,1,7,2,6,9,8},
				{1,7,8,5,6,9,4,2,3},
				{2,6,9,3,4,8,7,1,5},
				{8,4,1,2,9,5,3,6,7},
				{3,5,2,7,1,6,8,4,9},
				{7,9,6,8,3,4,1,5,2}
			};

	public static void main(String args[]) {
		//System.out.println(Arrays.deepToString(transposeTable(new int[][]{{1,2,3},{4,5,6}})));
		//System.out.println(Arrays.toString(getColumn(new int[][]{{1,2},{3,4}}, 1)));
		//System.out.println(Arrays.toString(flatten(new int[][]{{1,2},{4,5,6},{7,8,9},{5},{7,5,35,7,6,3,8,3}})));
		//System.out.println(Arrays.toString(sort(new int[]{4,34567,2,7,9,2,4,8,4,45,8})));
		//System.out.println(uniqueEntries(new int[]{1,2,3,5,6,7,8,9,2}));
		//System.out.println(Arrays.deepToString(subGrid(new int[][]{{1,2,3,4,5,6},{7,8,9,10,11,12},{13,14,15,16,17,18}}, 0, 0, 2)));
		System.out.println(isSudoku(sudokuSolution1));
		System.out.println(isSudoku(sudokuSolution2));
	}

	public static boolean isSudoku(int[][] sudokuTable) {
		// I'm going to assume all sudoku tables are 9x9
		// Checking if sudokuTable is a valid table
		if(sudokuTable.length != 9) return false;
		for(int[] row : sudokuTable) {
			if(row.length != 9) return false;
		}
		/*
		 * In a Sudoku solution each number from 1-9 repeats 9 times in the entire table
		 * That means the sum of all the numbers in any row, column or sub-grid is 45
		 * The sum of all the numbers in the table is 9 * 45 = 405
		 */
		int[] flattenedSudokuTable = flatten(sudokuTable);
		/*
		 * First flatten sudokuTable and check if the sum of the elements is 405
		 * This is a pretty easy check for the validity of the table
		 * I'm assuming less computations as well
		 * I haven't checked though
		 */
		if(sumOfElements(flattenedSudokuTable) != 405) return false;
		// Secondly, check if there are any numbers outside of the range 1-9
		for(int i = 0;i < flattenedSudokuTable.length;i++) {
			if(flattenedSudokuTable[i] > 9 || flattenedSudokuTable[i] < 1) return false;
		}
		// Now we could probably just check the sum of the elements of each row and each column to determine
		// whether or not the given table is a valid sudoku solution
		// Unfotunately that is not what this assignment is testing, so we will do it the hard way
		// Lets first check if there are any duplicates in any of the rows or columns
		for(int i = 0;i < sudokuTable.length;i++) {
			if(!uniqueEntries(sudokuTable[i])) return false;
			if(!uniqueEntries(getColumn(sudokuTable, i)))  return false;
		}
		// Check if every 3x3 sub-grid has unique entries
		for(int i = 0;i < sudokuTable.length;i += 3) {
			for(int j = 0;j < sudokuTable.length;j += 3) {
				if(!uniqueEntries(flatten(subGrid(sudokuTable, j, i, 3)))) return false;
			}
		}
		// sudokuTable has passed every requirement to be a valid solution
		return true;
	}

	// This is an implementation of the bubble sort algorithm
	public static int[] sort(int[] unsortedArray) {
		int[] sortedArray = new int[unsortedArray.length];
		System.arraycopy(unsortedArray, 0, sortedArray, 0, unsortedArray.length);
		boolean isOrdered = false;
		while(!isOrdered) {
			isOrdered = true;
			for(int i = 0;i < sortedArray.length - 1;i++) {
				if(sortedArray[i] > sortedArray[i + 1]) {
					int temp = sortedArray[i];
					sortedArray[i] = sortedArray[i + 1];
					sortedArray[i + 1] = temp;
					isOrdered = false;
				}
			}
		}
		return sortedArray;
	}

	// Sorting the array places all the duplicate entries adjacent to each other
	// Hence, simply checking if entires 'i' and 'i + 1' are the same is enough
	public static boolean uniqueEntries(int[] intArray) {
		int[] newIntArray = sort(intArray);
		for(int i = 0;i < newIntArray.length - 1;i++) {
			if(newIntArray[i] == newIntArray[i + 1]) return false;
		}
		return true;
	}

	// Transposing the 2d array lets me return the row of the transposed array
	// The row of a transposed array is the same as the column of a non-transposed array
	public static int[] getColumn(int[][] table, int j) {
		int[][] transposedTable = transposeTable(table);
		return transposedTable[j];
	}

	// Copy the 2d array's elements in to a new 1d array
	public static int[] flatten(int[][] squareArray) {
		int size = 0;
		for(int i = 0;i < squareArray.length;i++) {
			size += squareArray[i].length;
		}
		int[] flatTable = new int[size];
		int positioningHelper = 0;
		for(int i = 0;i < squareArray.length;i++) {
			// Just go through each row in squareArray and copy it into flatTable
			System.arraycopy(squareArray[i], 0, flatTable, positioningHelper, squareArray[i].length);
			positioningHelper += squareArray[i].length;
		}
		return flatTable;
	}

	public static int[][] subGrid(int[][] table, int row, int column, int size) {
		int[][] subTable = new int[size][size];
		// We want 'size' number of rows
		// We copy the arrays starting at index 'row' and ending at 'row + size'
		// In each row we only want to copy starting from 'column' until 'column + size'
		for(int i = 0;i < size;i++) {
			// This part of the code is really tricky and I don't really remember how I figured it out
			// My advice, just visualize the table and what each of the variables means in terms of rows and columns
			System.arraycopy(table[i + row], column, subTable[i], 0, size);
		}
		return subTable;
	}

	// Follows the same principle as matrix transposition
	// This basically does all the work in getColumn();
	public static int[][] transposeTable(int[][] table) {
		int[][] transposedTable = new int[table[0].length][table.length];
		for(int i = 0;i < table.length;i++) {
			for(int j = 0;j < table[0].length;j++) {
				transposedTable[j][i] = table[i][j];
			}
		}
		return transposedTable;
	}

	// This is a useful method
	// I'll get back to you when I remember why
	public static int sumOfElements(int[] a) {
		int sum = 0;
		for(int item : a) sum += item;
		return sum;
	}
}
