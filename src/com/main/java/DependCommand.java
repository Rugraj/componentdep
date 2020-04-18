package com.main.java;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class for depend command
 * @author Rugraj
 *
 */
public class DependCommand implements MyCommand {

	@Override
	public Map<String, Object> executeCommand(List<String> commandList) {
		String commandName = commandList.get(0);

        Component currentCommandName = Component.getInstance(commandName);

        for (String strDependency : commandList.subList(1, commandList.size())) {
        	Component dependency = Component.getInstance(strDependency);
            currentCommandName.addcomponentDependency(dependency);
            dependency.addcomponentDependent(currentCommandName);
        }
        return Collections.emptyMap();
	}

}
