import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimplePatternSearcher {
    public static void main(String[] args) throws IOException {
        SimplePatternSearcher searcher = new SimplePatternSearcher();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String text = reader.readLine();
            if (text.contains("exit")) {
                System.out.println(RED + "bye!");
                break;
            }
            String keyWord = reader.readLine();
            searcher.search(text, keyWord);
        }
    }

    private void search(String text, String key) {
        int txtLen = text.length();
        int keyLen = key.length();
        if (txtLen < keyLen) {
            return;
        }
        List<Integer> startIndexes = new ArrayList<>();
        for (int i = 0; i <= txtLen - keyLen; i++) {
            int j = 0;
            for (; j < keyLen; j++) {
                if (text.charAt(i + j) != key.charAt(j)) {
                    break;
                }
            }
            if (j == keyLen) {
                startIndexes.add(i);
            }
        }
        this.highLightOutput(text, startIndexes, key.length());
    }


    /**
     * @param text         text to output
     * @param startIndexes start index of key in text
     */
    private void highLightOutput(String text, List<Integer> startIndexes, int keyLength) {
        StringBuilder result = new StringBuilder();
        int from = 0;
        for (Integer index : startIndexes) {
            String left = text.substring(from, index);
            String highLight = text.substring(index, index + keyLength);
            result.append(BLACK).append(left).append(RED).append(highLight).append(BLACK);
            from = index + keyLength;
        }
        System.out.println(result);
    }

    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED

}
