import java.util.HashMap;
import java.util.Map;

public class SubarraySum {

    // сумма куска [l, r] равна k, когда prefix[r] - prefix[l - 1] == k
    // считаем в long: при нулях и k = 0 подмассивов больше, чем влезает в int
    public static long count(int[] nums, long k) {
        Map<Long, Long> seen = new HashMap<>();
        seen.put(0L, 1L);

        long sum = 0;
        long ans = 0;

        for (int x : nums) {
            sum += x;
            ans += seen.getOrDefault(sum - k, 0L);
            seen.put(sum, seen.getOrDefault(sum, 0L) + 1);
        }

        return ans;
    }
}
