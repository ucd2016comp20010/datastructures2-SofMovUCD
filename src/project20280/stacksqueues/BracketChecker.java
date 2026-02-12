package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public static boolean check(String input) {
        // TODO
        LinkedStack<String> ls = new LinkedStack<String>();
        for (int i = 0; i < input.length(); i++) {
            char lett = input.charAt(i);
            if (lett == '(' || lett == '{' || lett == '[') {
                ls.push(String.valueOf(lett));
            } else if (lett == ')' || lett == '}' || lett == ']') {
                if (ls.top() == null) {
                    return false;
                } else if (lett == ')' && ls.top().equals("(")) {
                    ls.pop();
                } else if (lett == '}' && ls.top().equals("{")) {
                    ls.pop();
                } else if (lett == ']' && ls.top().equals("[")) {
                    ls.pop();
                } else {
                    return false;
                }
            }
        }
        return ls.isEmpty();
    }


    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            boolean checker = BracketChecker.check(input);
            System.out.println("checking: " + input);
            System.out.println(checker ? "yes " : "no ");
        }
    }
}
