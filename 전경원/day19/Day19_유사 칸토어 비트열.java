class Solution {
    public int solution(int n, long l, long r) {
        int cnt = 0;
        for (long pos = l - 1; pos <= r - 1; pos++) {
            if (isOne(pos)) cnt++;
        }
        return cnt;
    }

    private boolean isOne(long pos) {
        while (pos > 0) {
            if (pos % 5 == 2) return false;
            pos /= 5;
        }
        return true;
    }
}
