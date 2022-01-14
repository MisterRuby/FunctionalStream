package streamPackage;

import org.junit.Test;

import java.util.Arrays;

public class StreamMatch {

    int[] nums1 = {3, 7, 4, 13, 4};
    int[] nums2 = {3, 7, 4, 13, -4};

    @Test
    public void testAllMatch() {
        // allMatch - 스트림 안의 모든 데이터가 predicate 를 만족하면 true
        boolean allMatch1 = Arrays.stream(nums1)
                .allMatch(num -> num > 0);

        boolean allMatch2 = Arrays.stream(nums2)
                .allMatch(num -> num > 0);

        System.out.println("StreamMatch.testAllMatch");
        System.out.println("allMatch1: " + allMatch1);
        System.out.println("allMatch2: " + allMatch2);
    }

    @Test
    public void testAnyMatch() {
        // anyMatch - 스트림 안의 데이터 중 하나라도 predicate 를 만족하면 true
        boolean anyMatch1 = Arrays.stream(nums1)
                .anyMatch(num -> num < 0);

        boolean anyMatch2 = Arrays.stream(nums2)
                .anyMatch(num -> num < 0);

        System.out.println("StreamMatch.testAnyMatch");
        System.out.println("anyMatch1: " + anyMatch1);
        System.out.println("anyMatch2: " + anyMatch2);
    }
}
