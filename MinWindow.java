public class MinWindow {

    // справа добираем символы target, слева выкидываем лишнее
    // буквы разные по регистру, поэтому A и a считаем отдельно
    public static String shortest(String source, String target) {
        int[] need = new int[128];
        int kinds = 0;

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (need[c] == 0)
                kinds++;
            need[c]++;
        }

        int[] have = new int[128];
        int got = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0;
        int left = 0;

        for (int right = 0; right < source.length(); right++) {
            char c = source.charAt(right);
            have[c]++;
            if (need[c] > 0 && have[c] == need[c])
                got++;

            while (got == kinds && left <= right) {
                int len = right - left + 1;
                if (len < bestLen) {
                    bestLen = len;
                    bestStart = left;
                }

                char out = source.charAt(left);
                have[out]--;
                if (need[out] > 0 && have[out] < need[out])
                    got--;
                left++;
            }
        }

        if (bestLen == Integer.MAX_VALUE)
            return "";

        return source.substring(bestStart, bestStart + bestLen);
    }
}
