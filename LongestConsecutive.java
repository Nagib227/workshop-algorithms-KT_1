import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    // в множество кладём уникальные числа
    // цепочку растягиваем только с начала: если x - 1 уже есть, x не старт
    public static int length(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums)
            set.add(x);

        int best = 0;
        for (int x : set) {
            if (set.contains(x - 1))
                continue;

            int len = 1;
            int cur = x;
            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }

            if (len > best)
                best = len;
        }

        return best;
    }
}
