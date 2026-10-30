public class MaxWater {

    // ширина каждый шаг уменьшается, поэтому двигаем меньшую стенку
    // площадь до (n * 10^9) не влезает в int, поэтому long
    public static long maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        long best = 0;

        while (left < right) {
            long h = Math.min(height[left], height[right]);
            long area = h * (right - left);
            if (area > best)
                best = area;

            if (height[left] < height[right])
                left++;
            else
                right--;
        }

        return best;
    }
}
