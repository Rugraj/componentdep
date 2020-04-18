package com.main.java;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * class for Install command
 * @author Rugraj
 *
 */
public class InstallCommand implements MyCommand {

	@Override
	public Map<String, Object> executeCommand(List<String> commandList) {
		Map<String, Object> resultMap = new LinkedHashMap<>();
		for (String depName : commandList) {

			Component dep = Component.getInstance(depName);
			install(dep, resultMap);
		}
		return resultMap;
	}

	private Map<String, Object> install(Component current, Map<String, Object> resultMap) {
		if (!current.isComponentInstalled()) {
			current.setComponentInstalled(true);

			for (Component dependency : current.getComponentDependencies()) {
				if (!dependency.isComponentInstalled()) { // not entirely necessary
					install(dependency, resultMap);
				}

			}
			resultMap.put(current.getComponentName(), "Installing");

		} else {
			resultMap.put(current.getComponentName(), "is already installed");

		}
		return resultMap;
	}

}
