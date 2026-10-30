import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int failed = 0;

        failed += checkConsecutive();
        failed += checkThreeSum();
        failed += checkTwoTypes();
        failed += checkSubarrays();
        failed += checkWater();
        failed += checkWindow();

        if (failed == 0)
            System.out.println("Все проверки прошли");
        else
            System.out.println("Ошибок: " + failed);
    }

    private static int checkConsecutive() {
        int failed = 0;
        failed += expectInt("последовательность [100, 4, 200, 1, 3, 2]",
                LongestConsecutive.length(new int[]{100, 4, 200, 1, 3, 2}), 4);
        failed += expectInt("последовательность [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]",
                LongestConsecutive.length(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}), 9);
        failed += expectInt("последовательность []",
                LongestConsecutive.length(new int[]{}), 0);
        failed += expectInt("последовательность [7, 7, 7]",
                LongestConsecutive.length(new int[]{7, 7, 7}), 1);
        return failed;
    }

    private static int checkThreeSum() {
        int failed = 0;
        failed += expectTriples("тройки [-1, 0, 1, 2, -1, -4]",
                ThreeSum.find(new int[]{-1, 0, 1, 2, -1, -4}),
                Arrays.asList(
                        Arrays.asList(-1, -1, 2),
                        Arrays.asList(-1, 0, 1)));
        failed += expectTriples("тройки [0, 0, 0, 0]",
                ThreeSum.find(new int[]{0, 0, 0, 0}),
                Arrays.asList(Arrays.asList(0, 0, 0)));
        failed += expectTriples("тройки [1, 2, -2, -1]",
                ThreeSum.find(new int[]{1, 2, -2, -1}),
                new ArrayList<>());
        return failed;
    }

    private static int checkTwoTypes() {
        int failed = 0;
        failed += expectInt("два типа [1, 2, 1]",
                TwoTypes.maxLen(new int[]{1, 2, 1}), 3);
        failed += expectInt("два типа [0, 1, 2, 2]",
                TwoTypes.maxLen(new int[]{0, 1, 2, 2}), 3);
        failed += expectInt("два типа [1, 2, 3, 2, 2]",
                TwoTypes.maxLen(new int[]{1, 2, 3, 2, 2}), 4);
        failed += expectInt("два типа []",
                TwoTypes.maxLen(new int[]{}), 0);
        return failed;
    }

    private static int checkSubarrays() {
        int failed = 0;
        failed += expectLong("подмассивы [1, 1, 1], k = 2",
                SubarraySum.count(new int[]{1, 1, 1}, 2), 2);
        failed += expectLong("подмассивы [1, -1, 0], k = 0",
                SubarraySum.count(new int[]{1, -1, 0}, 0), 3);

        int[] zeros = new int[100000];
        failed += expectLong("подмассивы из нулей, k = 0",
                SubarraySum.count(zeros, 0), 100000L * 100001 / 2);
        return failed;
    }

    private static int checkWater() {
        int failed = 0;
        failed += expectLong("вода [1, 8, 6, 2, 5, 4, 8, 3, 7]",
                MaxWater.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
        failed += expectLong("вода [1, 1]",
                MaxWater.maxArea(new int[]{1, 1}), 1);
        failed += expectLong("вода большие стенки",
                MaxWater.maxArea(new int[]{1000000000, 0, 0, 1000000000}), 3000000000L);
        return failed;
    }

    private static int checkWindow() {
        int failed = 0;
        failed += expectStr("окно ADOBECODEBANC / ABC",
                MinWindow.shortest("ADOBECODEBANC", "ABC"), "BANC");
        failed += expectStr("окно a / aa",
                MinWindow.shortest("a", "aa"), "");
        failed += expectStr("окно AAABBC / AABC",
                MinWindow.shortest("AAABBC", "AABC"), "AABBC");
        return failed;
    }

    private static int expectInt(String name, int got, int expected) {
        if (got != expected) {
            System.out.println(name + ": " + got + ", ждал " + expected);
            return 1;
        }
        System.out.println(name + " -> " + got);
        return 0;
    }

    private static int expectLong(String name, long got, long expected) {
        if (got != expected) {
            System.out.println(name + ": " + got + ", ждал " + expected);
            return 1;
        }
        System.out.println(name + " -> " + got);
        return 0;
    }

    private static int expectStr(String name, String got, String expected) {
        if (!got.equals(expected)) {
            System.out.println(name + ": \"" + got + "\", ждал \"" + expected + "\"");
            return 1;
        }
        System.out.println(name + " -> \"" + got + "\"");
        return 0;
    }

    private static int expectTriples(String name, List<List<Integer>> got, List<List<Integer>> expected) {
        List<String> a = canon(got);
        List<String> b = canon(expected);
        if (!a.equals(b)) {
            System.out.println(name + ": " + a + ", ждал " + b);
            return 1;
        }
        System.out.println(name + " -> " + a);
        return 0;
    }

    private static List<String> canon(List<List<Integer>> triples) {
        List<String> rows = new ArrayList<>();
        for (List<Integer> t : triples)
            rows.add(t.get(0) + " " + t.get(1) + " " + t.get(2));
        Collections.sort(rows);
        return rows;
    }
}
