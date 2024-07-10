import java.util.*;
import java.io.*;

public class Quiz2 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java Quiz2 input_filename");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        String[] firstLine = br.readLine().split(" ");
        int M = Integer.parseInt(firstLine[0]); // spacecraft capacity
        int n = Integer.parseInt(firstLine[1]); // number of resources

        String[] secondLine = br.readLine().split(" ");
        int[] resources = new int[n];
        for (int i = 0; i < n; i++) {
            resources[i] = Integer.parseInt(secondLine[i]);
        }

        br.close();

        boolean[][] dp = new boolean[M + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        for (int m = 1; m <= M; m++) {
            for (int i = 1; i <= n; i++) {
                if (resources[i - 1] > m) {
                    dp[m][i] = dp[m][i - 1];
                } else {
                    dp[m][i] = dp[m][i - 1] || dp[m - resources[i - 1]][i - 1];
                }
            }
        }

        int maxMass = 0;
        for (int m = M; m >= 0; m--) {
            if (dp[m][n]) {
                maxMass = m;
                break;
            }
        }

        System.out.println(maxMass);

        // Print the L(m, i) matrix
        for (int m = 0; m <= M; m++) {
            for (int i = 0; i <= n; i++) {
                System.out.print(dp[m][i] ? 1 : 0);
            }
            System.out.println();
        }
    }
}

