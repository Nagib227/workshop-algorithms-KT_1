import java.util.HashMap;
import java.util.Map;

public class TwoTypes {

    // окно расширяется вправо
    // если разных типов стало больше двух, левый край сдвигаем
    public static int maxLen(int[] items) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < items.length; right++) {
            int value = items[right];
            count.put(value, count.getOrDefault(value, 0) + 1);

            while (count.size() > 2) {
                int out = items[left];
                int c = count.get(out) - 1;
                if (c == 0)
                    count.remove(out);
                else
                    count.put(out, c);
                left++;
            }

            int len = right - left + 1;
            if (len > best)
                best = len;
        }

        return best;
    }
}
