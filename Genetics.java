import java.util.Arrays;
import java.util.Scanner;

public class Genetics {

	public static void main(String[] args) {

		display();
		
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
	
	public static Data[][] createArray(String firstString, String secondString) {
		
		int firstLength = 0;
		int secondLength = 0;
		int startingValue1 = 0;
		int startingValue2 = 2;
		
		firstLength = firstString.length();
		secondLength = secondString.length();
		
		Data[][] stringArray = new Data[firstLength + 1][secondLength + 1];
			
		for(int i = firstLength; i >= 0; i--) {
			for(int j = secondLength; j >= 0; j--) {
				if(i == firstLength) {
					int value = startingValue1;
					char direction = '-';
					stringArray[i][j] = new Data(value, direction);
					startingValue1 += 2;
				} else {
					if(j == secondLength) {
						int value = startingValue2;
						char direction = '-';
						stringArray[i][j] = new Data(value, direction);
						startingValue2 += 2;
					} else {
						stringArray[i][j] = new Data(0, '-');
					}
				}
			}
		}
		
		return stringArray;
	}
	
	public static int diagonalBox(Character firstChar, Character secondChar, Data[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row + 1][column + 1].getValue();
	
		score += temp;
		
		return score;
	}
	
	public static int rightBox(Character secondChar, Data[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		Character firstChar = ' ';
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row][column + 1].getValue();
	
		score += temp;
		
		return score;
	}
	
	public static int bottomBox(Character firstChar, Data[][] stringArray, int row, int column) {
		int score = 0;
		int temp = 0;
		Character secondChar = ' ';
		
		if(!firstChar.equals(' ') && firstChar != secondChar && !secondChar.equals(' ')) {
			score = 1;
		} else if(!firstChar.equals(' ') && secondChar.equals(' ') || firstChar.equals(' ') && !secondChar.equals(' ')) {
			score = 2;
		}
		
		temp = stringArray[row + 1][column].getValue();
	
		score += temp;
		
		return score;
	}
	
	public static Data minFunc(int a, int b, int c) {
		int min = 0;
		char direction;
		
		if(a <= b && a <= c) {
			min = a;
			direction = 'D';
		} else if(b < a && b <= c) {
			min = b;
			direction = 'R';
		} else {
			min = c;
			direction = 'B';
		}
		
		Data data = new Data(min, direction);
		
		return data;
	}
	
	public static Data[][] finalArray(String[] strings) {
		
		String firstString = strings[0];
		String secondString = strings[1];
		Data[][] stringArray = createArray(firstString, secondString);
		int firstLength = firstString.length();
		int secondLength = secondString.length();
		Data score;
		
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
	
	public static String addSpace(char original) {
		String newString = " " + original;
		
		return newString;
	}
	
	public static void display() {
		String[] strings = getStrings();
		Data[][] stringArray = finalArray(strings);
		String firstString = strings[0];
		int firstLength = firstString.length();
		String secondString = strings[1];
		int secondLength = secondString.length();
		String answer1 = "";
		String answer2 = "";
		char currentSquareDirection = stringArray[0][0].getDirection();
		int g = 0;
		int h = 0;
		int i;
		int j;
		
		System.out.print("\nDynamic Programming Chart: \n\nD = Diagonal\nR = Right\nB = Bottom\n\n");
		
		for(i = 0; i <= firstLength; i++) {
			for(j = 0; j <= secondLength; j++) {
				System.out.print(stringArray[i][j].getValue() + ":" + stringArray[i][j].getDirection() + " ");
			}
			
			System.out.print("\n");
		}
		
		i = 0;
		j = 0;
		
		while(currentSquareDirection != '-') {
			if(currentSquareDirection == 'D') {
				answer1 = answer1 + firstString.charAt(g);
				answer2 = answer2 + secondString.charAt(h);
				i++;
				j++;
				g++;
				h++;
				currentSquareDirection = stringArray[i][j].getDirection();
			} else if(currentSquareDirection == 'R') {
				answer1 = answer1 + addSpace(firstString.charAt(g));
				answer2 = answer2 + secondString.charAt(h);
				i++;
				h++;
				g++;
				currentSquareDirection = stringArray[i][j].getDirection();
			} else if(currentSquareDirection == 'B' ) {
				answer1 = answer1 + firstString.charAt(g);
				answer2 = answer2 + addSpace(secondString.charAt(h));
				j++;
				g++;
				h++;
				currentSquareDirection = stringArray[i][j].getDirection();
			}
		}
		
		System.out.print("\nFirst String: " + answer1 + "\nSecondString: " + answer2 + "\nOptimal score: " + stringArray[0][0].getValue());
		
	}

}

