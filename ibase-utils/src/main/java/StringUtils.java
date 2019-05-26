public class StringUtils {
    public static String editDistance(String source, String target) {
        char[] sources = source.toCharArray();
        char[] targets = target.toCharArray();
        int sourceLen = sources.length;
        int targetLen = targets.length;
        int[][] d = new int[sourceLen + 1][targetLen + 1];
        for (int i = 0; i <= sourceLen; i++) {
            d[i][0] = i;
        }
        for (int i = 0; i <= targetLen; i++) {
            d[0][i] = i;
        }

        for (int i = 1; i <= sourceLen; i++) {
            for (int j = 1; j <= targetLen; j++) {
                if (sources[i - 1] == targets[j - 1]) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    int insert = d[i][j - 1] + 1;
                    int delete = d[i - 1][j] + 1;
                    int replace = d[i - 1][j - 1] + 1;
                    d[i][j] = Math.min(insert, delete) > Math.min(delete, replace)?Math.min(delete, replace):Math.min(insert, delete);
                }
            }
        }
        int distance = d[sourceLen][targetLen];
        int temp = sourceLen-distance < 0?targetLen-distance:sourceLen-distance;
        int temp2 = targetLen-distance < 0?sourceLen-distance:targetLen-distance;
        double percent1 = temp*10000/sourceLen*0.01;
        double percent2 = temp2*10000/sourceLen*0.01;
        return String.format("%.2f",percent1 > percent2?percent1:percent2);
    }

    public static void main(String[] args) {
        StringUtils.editDistance("中国人", "中国");
    }
}
