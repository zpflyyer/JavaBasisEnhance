public class SimplePatternSearcher {
    public static void main(String[] args) {
        SimplePatternSearcher searcher = new SimplePatternSearcher();
        String textToSearchIn = "nffv2 r 934ytrwefhjsbandkfsdgbsfl;rdjg984hgjfdmv;sldgl0rit48ryt9rqfnv jsdkfe98bhnms\n" +
                "[pti938ur20-kgmbjngwe9kr0-34yjh98nwerk9rfgj7934tj590jhybfndfkj  buyrueingjdknfhdgbfsmgn4hegffnurjt\n" +
                "78hgtgkiodghuisgfasuifsiodhfusgduhdguiuidgyufg78eh";
        searcher.search(textToSearchIn, "fg");
    }

    private void search(String txt, String pat) {
        int txtLen = txt.length();
        int patLen = pat.length();
        if (txtLen < patLen) {
            return;
        }

        for (int i = 0; i <= txtLen - patLen; i++) {
            int j = 0;
            for (; j < patLen; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == patLen) {
                this.highLightOutput(txt, i, i + patLen - 1);
            }
        }
    }


    /**
     * @param txt        text to output
     * @param startIndex inclusive
     * @param endIndex   inclusive
     */
    private void highLightOutput(String txt, int startIndex, int endIndex) {
        String left = txt.substring(0, startIndex);
        String highLight = txt.substring(startIndex, endIndex + 1);
        String right = txt.substring(endIndex + 1, txt.length());
        System.out.println(left + RED + highLight + BLACK + right);
        System.out.println("-------------------------------");
    }

    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED

}
