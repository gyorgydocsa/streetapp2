import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int m = 5;
        List<String> data = new ArrayList<>();
        data.add("YNYYN");
        data.add("YYYYN");
        data.add("YYYYN");
        data.add("YYYYY");
        data.add("YYNYN");
        data.add("NYYNN");
        data.add("NYYNY");
        data.add("NYYNY");
        data.add("NYYNY");
        data.add("NYYNY");
        data.add("yYYyY");
        data.add("yYYyY");
        System.out.println(maxStreak(m, data));

    }

    public static int maxStreak(int m, List<String> data) {

        int dayCounter = 0;
        int attendanceCounter = 0;

        for (String attendance : data) {

            for (int j = 0; j < m; j++) {
                if (attendance.toUpperCase().charAt(j) == 'Y') {
                    attendanceCounter++;
                }
            }
            if (attendanceCounter == m) {
                dayCounter++;
            }
            attendanceCounter = 0;
        }
        return dayCounter;
    }
}
