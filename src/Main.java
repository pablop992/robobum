
public class Main {

	public static void main(String[] args) {
		String[][] matrix = new String[4][4];
		
		matrix[0][0] = "0,0";
		matrix[0][1] = "1,0";
		matrix[0][2] = "2,0";
		matrix[0][3] = "3,0";
		
		matrix[1][0] = "0,1";
		matrix[1][1] = "1,1";
		matrix[1][2] = "2,1";
		matrix[1][3] = "3,1";
		
		matrix[2][0] = "0,2";
		matrix[2][1] = "1,2";
		matrix[2][2] = "2,2";
		matrix[2][3] = "3,2";
		
		matrix[3][0] = "0,3";
		matrix[3][1] = "1,3";
		matrix[3][2] = "2,3";
		matrix[3][3] = "3,3";
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(""+ matrix[i][j] + "  ");
			}
			System.out.print("\n");
		}
		
		

	}

}
