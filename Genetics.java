import java.util.Arrays;
import java.util.Scanner;

public class Genetics {

	public static void main(String[] args) {

		displayChart();
		
	}
	
	public static String[] getStrings() {
		Scanner input = new Scanner(System.in);
		String firstString = "";
		String secondString = "";
		String[] strings = new String[2];
		
		System.out.print("Please enter the first of two strings.");
		firstString = input.next();
		System.out.print("Please enter the second string.");
		secondString = input.next();
		
		strings[0] = firstString;
		strings[1] = secondString;
		
		input.close();
		return strings;
	}
	
	public static int[][] createStringArray(String firstString, String secondString) {
		
		int firstLength = 0;
		int secondLength = 0;
		int startingValue1 = 0;
		int startingValue2 = 2;
		
		firstLength = firstString.length();
		secondLength = secondString.length();
		
		int[][] stringArray = new int[firstLength + 1][secondLength + 1];
			
		for(int i = firstLength; i >= 0; i--) {
			for(int j = secondLength; j >= 0; j--) {
				if(i == firstLength) {
					stringArray[i][j] = startingValue1;
					startingValue1 += 2;
				} else {
					if(j == secondLength) {
						stringArray[i][j] = startingValue2;
						startingValue2 += 2;
					}
				}
			}
		}
		return stringArray;
	}
	
	public static int diagonalBox(Character firstChar, Character secondChar, int[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row + 1][column + 1];
	
		score += temp;
		
		return score;
	}
	
	public static int rightBox(Character secondChar, int[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		Character firstChar = ' ';
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row][column + 1];
	
		score += temp;
		
		return score;
	}
	
	public static int bottomBox(Character firstChar, int[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		Character secondChar = ' ';
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row + 1][column];
	
		score += temp;
		
		return score;
	}
	
	public static int minFunc(int a, int b, int c) {
		int min = 0;
		
		if(a <= b && a <= c) {
			min = a;
		} else if(b < a && b <= c) {
			min = b;
		} else {
			min = c;
		}
		
		return min;
	}
	
	public static int[][] finalArray() {
		
		String[] strings = getStrings();
		String firstString = strings[0];
		String secondString = strings[1];
		int[][] stringArray = createStringArray(firstString, secondString);
		int firstLength = firstString.length();
		int secondLength = secondString.length();
		int score = 0;
		
		for(int i = firstLength - 1; i >= 0; i--) {
			for(int j = secondLength - 1; j >= 0; j--) {
				int diagonal = diagonalBox(firstString.charAt(i), secondString.charAt(j), stringArray, i, j);
				int right = rightBox(firstString.charAt(i), stringArray, i, j);
				int bottom = bottomBox(secondString.charAt(j), stringArray, i, j);
				score = minFunc(diagonal, right, bottom);
				stringArray[i][j] = score;
			
			}
		}
		
		return stringArray;
	}
	
	public static void displayChart() {
		int[][] stringArray = finalArray();
		
		System.out.print("\nDynamic Programming Chart: \n" + Arrays.deepToString(stringArray) + "\n");
		
	}

}

