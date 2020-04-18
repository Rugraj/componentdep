package com.main.java;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Singleton pattern for Component
 * @auther: Rugraj
 */
public class Component {

	private String componentName;

	private Set<Component> componentDependencies = new HashSet<>();

	private Set<Component> componentDependents = new HashSet<>();

	public static Map<String, Component> dependencyMap = new HashMap<>();

	private boolean componentInstalled;

	private Component(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentName() {
		return componentName;
	}

	public Set<Component> getComponentDependencies() {
		return componentDependencies;
	}

	public Set<Component> getComponentDependents() {
		return componentDependents;
	}

	public static Map<String, Component> getDependencyMap() {
		return dependencyMap;
	}

	public boolean isComponentInstalled() {
		return componentInstalled;
	}

	
	public void setComponentInstalled(boolean componentInstalled) {
		this.componentInstalled = componentInstalled;
	}

	public static Component getInstance(String componentName) {
		Component targetComponent = dependencyMap.get(componentName);
		if (targetComponent == null) {
			targetComponent = new Component(componentName);
			dependencyMap.put(componentName, targetComponent);
		}
		return targetComponent;
	}

	public boolean iscomponentDependentsEmpty() {
		return !componentDependents.isEmpty();
	}

	public boolean iscomponentDependenciesEmpty() {
		return !componentDependencies.isEmpty();
	}

	public boolean addcomponentDependency(Component component) {
		return componentDependencies.add(component);
	}

	public boolean addcomponentDependent(Component component) {
		return componentDependents.add(component);
	}

	public static Collection<Component> getAllComponent() {
		return dependencyMap.values();
	}

	public static Set<Component> getInstalledComponent() {
		Set<Component> installedComponet = new HashSet<>();
		for (Component Component : dependencyMap.values()) {
			if (Component.isComponentInstalled())
				installedComponet.add(Component);
		}
		return installedComponet;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Component comp = (Component) obj;

		if (componentName != null ? !componentName.equals(comp.componentName) : comp.componentName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return componentName != null ? componentName.hashCode() : 0;
	}

	@Override
	public String toString() {
		return componentName;
	}

}
