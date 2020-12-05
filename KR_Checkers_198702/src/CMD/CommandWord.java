//package CMD;
//
//public enum CommandWord {
//
//    QUIT("quit"),
//    MOVE("move"),
//    NEW("new"),
//    UNKNOWN("unkownn");
//
//    private String word;
//
//    CommandWord(String word) {
//        this.word = word;
//    }
//
//    public static CommandWord getCommandWord(String s) {
//        for(CommandWord c : CommandWord.values()) {
//            if(c.getWord().startsWith(s.toLowerCase())) {
//                return c;
//            }
//        }
//        return UNKNOWN;
//    }
//
//    public String getWord() {
//        return this.word;
//    }
//}