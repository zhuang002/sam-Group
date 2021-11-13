import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		while (n!=0) {
			int[][] opTable = readIn(n,sc);
			if (isGroup(opTable)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
			
			n=sc.nextInt();
		}
	}

	private static boolean isGroup(int[][] opTable) {
		// TODO Auto-generated method stub
		if (!checkAssociativity(opTable)) return false;
		int i = getIdentity(opTable);
		if (i<0) return false;
		return checkInverse(opTable, i);
		
	}

	private static boolean checkInverse(int[][] opTable, int identity) {
		// TODO Auto-generated method stub
		
		for (int x=1;x<=opTable.length;x++) {
			boolean foundInverse=false;
			for (int y=1;y<=opTable.length;y++) {
				if (opTable[x-1][y-1]==identity && opTable[y-1][x-1]==identity) {
					foundInverse=true;
					break;
				}
			}
			if (!foundInverse) {
				return false;
			}
		}
		return true;
	}

	private static int getIdentity(int[][] opTable) {
		// TODO Auto-generated method stub
		int i=0;
		int n=opTable.length;
		for (i=1;i<=n;i++) {
			boolean isLeftIdentity=true;
			for (int j=1;j<=n;j++) {
				int a=opTable[i-1][j-1]; //(i*j)
				if (a!=j) { // (i*j!=j), i is not identity;
					isLeftIdentity=false;
					break;
				}
			}
			if (!isLeftIdentity) continue;
			boolean isRightIdentity = true;
			for (int j=1;j<=n;j++) {
				if (opTable[j-1][i-1]!=j) {
					isRightIdentity = false;
					break;
				}
			}
			if (isRightIdentity) {
				return i;
			}
		}
		return -1;
	}

	private static boolean checkAssociativity(int[][] opTable) {
		// TODO Auto-generated method stub
		int n=opTable.length;
		for (int x=1;x<=n;x++) {
			for (int y=1;y<=n;y++) {
				for (int z=1;z<=n;z++) {
					int a = opTable[x-1][y-1]; //(x*y)
					a = opTable[a-1][z-1]; // (x*y)*z
					
					int b = opTable[y-1][z-1]; //(y*z)
					b = opTable[x-1][b-1]; // x*(y*z)
					
					if (a!=b) return false;
				}
			}
		}
		return true;
	}

	private static int[][] readIn(int n, Scanner sc) {
		// TODO Auto-generated method stub
		int[][] table = new int[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				table[i][j] = sc.nextInt();
			}
		}
		return table;
	}

}
