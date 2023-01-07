package treeconstruct;

public class TreeConstruct {

    public static void main(String[] args) {
        int count = 1;
        int[] moves = new int[] {6, 4};
        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 2; j++) {

                for (int k = 0; k < 2; k++) {

                    for (int l = 0; l < 2; l++) {

                        for (int m = 0; m < 2; m++) {

                            for (int n = 0; n < 2; n++) {
                                int firstPlayer = moves[i] + moves[k] + moves[l];
                                int secondPlayer = moves[j] + moves[l] + moves[m];
                                System.out.println( count + "->" + i + " " + j + " " + k + " " + l + " " + m + " " + n);
                                count++;
                            }

                        }

                    }
                }
            }

        }

    }
}
