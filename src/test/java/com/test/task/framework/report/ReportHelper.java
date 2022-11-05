package com.test.task.framework.report;

import com.test.task.test.stepdef.Hooks;
import org.testng.Assert;

public class ReportHelper {

    public static void PASS(String description) {
        Hooks.eachScenario.log(description);
    }
    public static void FAIL(String description) {
        Hooks.eachScenario.log(description);
        Assert.fail(description);
    }
    public static void WARN(String description) {
        Hooks.eachScenario.log(description);
    }
    public static void INFO(String description) {
        Hooks.eachScenario.log(description);
    }
}
