// method 1:
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        Arrays.sort(citations);
        int i = 1;
        while (i <= citations.length && citations[citations.length - i] >= i) {
            i++;
        }
        return i - 1;
    }
}
// method 2:
class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int left = 0;
        int right = citations.length;
        while (left + 1 < right) {         
            int mid = left + (right - left) / 2;
            if (countBiggerAndEquals(citations, mid) == mid) {
                return mid;
            } else if (countBiggerAndEquals(citations, mid) < mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 1. why countBiggerAndEquals(citations, right) >= right not == ?
        // 2. why check right firstly?
        if (countBiggerAndEquals(citations, right) >= right) {
            return right;
        }
        return left;
    }
    private int countBiggerAndEquals(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num >= target) {
                count++;
            }
        }
        return count;
    }
}