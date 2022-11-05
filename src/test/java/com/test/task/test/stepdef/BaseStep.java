package com.test.task.test.stepdef;

import com.test.task.framework.datacontext.ScenarioContext;
import com.test.task.framework.datacontext.TestContext;
import com.test.task.framework.selenium.SeleniumActions;
import com.test.task.framework.api.RestApi;

public class BaseStep {
    private ScenarioContext scenarioContext;
    public RestApi api=new RestApi();
    public SeleniumActions selActions = new SeleniumActions();


    public BaseStep(TestContext testContext)
    {
        scenarioContext = testContext.getScenarioContext();
    }
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }


}
