package com.main.java;

import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for remove command
 * @author Rugraj
 *
 */

public class RemoveCommand implements MyCommand {

	@Override
	public Map<String, Object> executeCommand(List<String> commandList) {
		Component comp = Component.getInstance(commandList.get(0));
        if(comp != null)
            return uninstall(comp);
        Map<String,Object> resultMap = new LinkedHashMap<>();
        resultMap.put(commandList.get(0),"is not installed");
        return resultMap;
    }

    private Map<String, Object> uninstall(Component parent) {
        Map<String, Object> resultMap = new HashMap<>();
        Set<Component> installedComponetDependents = parent.getComponentDependents().stream().filter(Component::isComponentInstalled).collect(toSet());
        if(installedComponetDependents.isEmpty()) {
            resultMap.put(parent.getComponentName(),"Removing");
            parent.setComponentInstalled(false);

            for (Component dependency : parent.getComponentDependencies()) {
                if(dependency.isComponentInstalled()) {
                	resultMap.putAll(uninstall(dependency));
                }
            }
        }
        else {

        	resultMap.put(parent.getComponentName(),"is still needed.");
        }
        return resultMap;
    }
}
