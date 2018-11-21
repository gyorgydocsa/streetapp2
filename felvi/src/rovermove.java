import java.util.List;

public class rovermove {

    public int roverMove(int matrixSize, List<String> cmds){

        int position =0;
        int[][] matrix= new int[4][4];
        for (int i = 0; i < matrixSize ; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j]=j;
            }
        }

        for (String command : cmds) {
            switch (command.toUpperCase()){
                case "UP":{

                }
                case "DOWN":{

                }
                case "LEFT":{

                }
                case "RIGHT":{

                }
            }
        }
        return position;
    }
}
