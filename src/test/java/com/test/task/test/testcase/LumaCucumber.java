package com.test.task.test.testcase;
import courgette.api.*;
import courgette.api.testng.TestNGCourgette;
import org.testng.annotations.Test;

@Test
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = false,
        rerunAttempts = 1,
        testOutput = CourgetteTestOutput.CONSOLE,
        reportTitle = "Test Automation Execution Report",
        reportTargetDir = "results/reports/",
        environmentInfo = "browser=Chrome",

        plugin = {CourgettePlugin.EXTENT_REPORTS},
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/features/Luma.feature",
                glue = "com/test/task/test/stepdef",
                tags = "@luma-task",
                publish = true
        ))

public class LumaCucumber extends TestNGCourgette {
}
