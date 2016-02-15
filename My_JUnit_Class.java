package Test_Program;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class My_JUnit_Class {

/*	public int add(int a, int b)
	{
		if(a==0 && b==0)
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return a+b;
	}
*/
	
	
	public  void matrix_multiplication()
	   {
	      int m, n, p, q, sum = 0, c, d, k;
	 
	      Scanner in = new Scanner(System.in);
	      System.out.println("Enter the number of rows and columns of matrix#1");
	      m = in.nextInt();
	      n = in.nextInt();
	 	
	      Random randomGenerator = new Random();
		
	      int first[][] = new int[m][n];
	 
	      for ( c = 0 ; c < m ; c++ )
	         for ( d = 0 ; d < n ; d++ )
	         {
	        	 first[c][d] = randomGenerator.nextInt(100);
	         }
	      System.out.println("Enter the number of rows and columns of second matrix");
	      p = in.nextInt();
	      q = in.nextInt();
	      
	      long lStartTime = new Date().getTime();
	      
	      if ( n != p )
	         System.out.println("Multiplication can't be done as the dimensions are not correct for multiplication");
	      else
	      {
	         int second[][] = new int[p][q];
	         int multiply[][] = new int[m][q];
	 	 
	         for ( c = 0 ; c < p ; c++ )
	            for ( d = 0 ; d < q ; d++ )
	               second[c][d] = randomGenerator.nextInt(100);
	 
	         for ( c = 0 ; c < m ; c++ )
	         {
	            for ( d = 0 ; d < q ; d++ )
	            {   
	               for ( k = 0 ; k < p ; k++ )
	               {
	                  sum = sum + first[c][k]*second[k][d];
	               }
	 
	               multiply[c][d] = sum;
	               sum = 0;
	            }
	         }
	 
	        long lEndTime = new Date().getTime(); // end time
			long difference = lEndTime - lStartTime; // check different
			
				         
	         
	         System.out.println("Result:-");
	 
	         for ( c = 0 ; c < m ; c++ )
	         {
	            for ( d = 0 ; d < q ; d++ )
	               System.out.print(multiply[c][d]+"\t");
	 
	            System.out.print("\n");
	         }
	         System.out.println("Time taken by Multiplication: "+difference);
	      }
	   }
	
	 public  void strassen_multiplication()
	 {
		 	Scanner scan = new Scanner(System.in);
	        System.out.println("Enter size of matrix :");
	        
	        int matrix_size = scan.nextInt();
	        /** Accept two 2d matrices **/
	        int[][] m1 = new int[matrix_size][matrix_size];
	        
	        Random randomGenerator = new Random();

	        for (int i = 0; i < matrix_size; i++)
	            for (int j = 0; j < matrix_size; j++)
	                m1[i][j] = 	randomGenerator.nextInt(100);
	 
	        int[][] m2 = new int[matrix_size][matrix_size];
	        for (int i = 0; i < matrix_size; i++)
	            for (int j = 0; j < matrix_size; j++)
	                m2[i][j] = randomGenerator.nextInt(100);
	 
	        long lStartTime = new Date().getTime();
	        
	        int[][] C = multiply(m1, m2);
	        
	        long lEndTime = new Date().getTime(); // end time
			long difference = lEndTime - lStartTime; // check different
			
			
			
	        System.out.println("\nProduct of matrices A and  B : ");
	        for (int i = 0; i < matrix_size; i++)
	        {
	            for (int j = 0; j < matrix_size; j++)
	            	System.out.format("%10d", C[i][j]);
	                //System.out.print(C[i][j] +" ");
	            System.out.println();
	        }		 
	        System.out.println("Time taken by Multiplication: "+difference);
	 	}

	    public  int[][] multiply(int[][] A, int[][] B)
	    {        
	        int n = A.length;
	        int[][] R = new int[n][n];
	        /** base case **/
	        if (n == 1)
	            R[0][0] = A[0][0] * B[0][0];
	        else
	        {
	        	//1. Partition A, B into 4 equal parts:
	            int[][] A11 = new int[n/2][n/2];
	            split(A, A11, 0 , 0);
	            
	            int[][] A12 = new int[n/2][n/2];
	            split(A, A12, 0 , n/2);
	            
	            int[][] A21 = new int[n/2][n/2];
	            split(A, A21, n/2, 0);
	            
	            int[][] A22 = new int[n/2][n/2];
	            split(A, A22, n/2, n/2);
	            
	            int[][] B11 = new int[n/2][n/2];
	            split(B, B11, 0 , 0);
	            
	            int[][] B12 = new int[n/2][n/2];
	            split(B, B12, 0 , n/2);
	            
	            int[][] B21 = new int[n/2][n/2];
	            split(B, B21, n/2, 0);
	            
	            int[][] B22 = new int[n/2][n/2];
	            split(B, B22, n/2, n/2);
	            
	            //2. Evaluate the intermediate matrices:

	            int [][] M1 = multiply(add(A11, A22), add(B11, B22));
	            int [][] M2 = multiply(add(A21, A22), B11);
	            int [][] M3 = multiply(A11, sub(B12, B22));
	            int [][] M4 = multiply(A22, sub(B21, B11));
	            int [][] M5 = multiply(add(A11, A12), B22);
	            int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
	            int [][] M7 = multiply(sub(A12, A22), add(B21, B22));
	 
	            //3. Construct C using the intermediate matrices:

	            int [][] C11 = add(sub(add(M1, M4), M5), M7);
	            int [][] C12 = add(M3, M5);
	            int [][] C21 = add(M2, M4);
	            int [][] C22 = add(sub(add(M1, M3), M2), M6);
	 
	            // join 4 halves into one result matrix 
	            join(C11, R, 0 , 0);
	            join(C12, R, 0 , n/2);
	            join(C21, R, n/2, 0);
	            join(C22, R, n/2, n/2);
	        }
	        /** return result **/    
	        return R;
	    }
	    
	    public  int[][] sub(int[][] A, int[][] B)
	    {
	        int n = A.length;
	        int[][] C = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] - B[i][j];
	        return C;
	    }

	    public  int[][] add(int[][] A, int[][] B)
	    {
	        int n = A.length;
	        int[][] C = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	                C[i][j] = A[i][j] + B[i][j];
	        return C;
	    }

	    public  void split(int[][] P, int[][] C, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                C[i1][j1] = P[i2][j2];
	    }

		    /** join child matrices into parent matrix **/
	    public  void join(int[][] C, int[][] P, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                P[i2][j2] = C[i1][j1];
	    }    

		 public static void main(String args[])
		 {
			 My_JUnit_Class mc = new My_JUnit_Class();
			 //mc.strassen_multiplication();
			 mc.matrix_multiplication();
		 }				 
}
