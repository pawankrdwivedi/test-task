package com.test.task.framework.datacontext;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> scenarioContext;
    public ScenarioContext() {
        scenarioContext = new HashMap<String, Object>();
    }
    public void setContext(DataContext key, Object value) {
        scenarioContext.put(key.toString(), value);
    }
    public Object getContext(DataContext key){
        return scenarioContext.get(key.toString());
    }
    public boolean isContains(DataContext key){
        return scenarioContext.containsKey(key.toString());
    }
}
