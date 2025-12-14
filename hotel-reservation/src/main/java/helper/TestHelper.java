package helper;

import exception.BusinessException;

public class TestHelper {
    public static void runTest(String title, Runnable action) {
        System.out.println("\nTEST: " + title);
        try {
            action.run();
            System.out.println("PASSED!");
        } catch (BusinessException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}