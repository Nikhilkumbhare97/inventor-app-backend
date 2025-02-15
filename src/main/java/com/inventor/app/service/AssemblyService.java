package com.inventor.app.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssemblyService {
    private boolean isAssemblyOpen = false;
    private ActiveXComponent app;

    public boolean isAssemblyOpen() {
        return isAssemblyOpen;
    }

    @Value("${file.destination-path}")
    private String DESTINATION_PATH;

    public void openAssembly(String assemblyPath) {
        try {
            if (app == null) {
                app = new ActiveXComponent("Inventor.Application");
                app.setProperty("Visible", new Variant(true));
            }
            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch.call(documents, "Open", assemblyPath);
            isAssemblyOpen = true;
            System.out.println("Opening assembly: " + assemblyPath);
        } catch (Exception e) {
            System.err.println("Error opening assembly: " + e.getMessage());
            throw new RuntimeException("Error opening assembly", e);
        }
    }

    public void closeAssembly() {
        try {
            if (isAssemblyOpen && app != null) {
                Dispatch activeDocument = app.getProperty("ActiveDocument").toDispatch();
                Dispatch.call(activeDocument, "Close", new Variant(false));
                isAssemblyOpen = false;
                System.out.println("Closing assembly...");
            } else {
                System.err.println("No active application instance to close.");
            }
        } catch (Exception e) {
            System.err.println("Error closing assembly: " + e.getMessage());
            throw new RuntimeException("Error closing assembly", e);
        } finally {
            if (app != null) {
                app.invoke("Quit");
                app = null; // Release the ActiveXComponent
            }
        }
    }

    public void changeParameters(String partFilePath, List<Map<String, Object>> parameters) {
        try {
            if (app == null) {
                app = new ActiveXComponent("Inventor.Application");
                app.setProperty("Visible", new Variant(true));
            }
            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch partDocument = Dispatch.call(documents, "Open", partFilePath, false).toDispatch();
            Dispatch componentDefinition = Dispatch.get(partDocument, "ComponentDefinition").toDispatch();
            Dispatch paramList = Dispatch.get(componentDefinition, "Parameters").toDispatch();

            for (Map<String, Object> param : parameters) {
                String parameterName = (String) param.get("parameterName");
                double newValue = ((Number) param.get("newValue")).doubleValue();
                Dispatch parameter = Dispatch.call(paramList, "Item", parameterName).toDispatch();
                Dispatch.put(parameter, "Expression", new Variant(newValue + " mm"));
            }

            Dispatch.call(partDocument, "Save");
            Dispatch.call(partDocument, "Close");
            System.out.println("Parameters updated successfully in " + partFilePath);
        } catch (Exception e) {
            System.err.println("Error changing parameters: " + e.getMessage());
            throw new RuntimeException("Error changing parameters", e);
        }
    }

    public void suppressComponent(String assemblyFilePath, String componentName, boolean suppress) {
        System.out.println("suppressComponent called with assemblyFilePath: " + assemblyFilePath + ", componentName: "
                + componentName + ", suppress: " + suppress);
        try {
            if (app == null) {
                app = new ActiveXComponent("Inventor.Application");
                app.setProperty("Visible", new Variant(true)); // Ensure Inventor is visible
            }

            // Open the assembly document
            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch assemblyDocument = Dispatch.call(documents, "Open", assemblyFilePath, false).toDispatch();
            Dispatch componentDefinition = Dispatch.get(assemblyDocument, "ComponentDefinition").toDispatch();
            Dispatch occurrences = Dispatch.get(componentDefinition, "Occurrences").toDispatch();

            boolean componentFound = false;
            int count = Dispatch.get(occurrences, "Count").getInt();

            // Iterate through occurrences to find the target component
            for (int i = 1; i <= count; i++) {
                Dispatch occurrence = Dispatch.call(occurrences, "Item", i).toDispatch();
                String occurrenceName = Dispatch.get(occurrence, "Name").toString();

                if (occurrenceName.equalsIgnoreCase(componentName)) {
                    System.out.println("Found component: " + occurrenceName);

                    // Suppress or unsuppress based on the input
                    if (suppress) {
                        Dispatch.call(occurrence, "Suppress");
                        System.out.println("Component " + componentName + " suppressed.");
                    } else {
                        Dispatch.call(occurrence, "Unsuppress");
                        System.out.println("Component " + componentName + " unsuppressed.");
                    }

                    componentFound = true;
                    break;
                }
            }

            if (!componentFound) {
                System.err.println("Component " + componentName + " not found in " + assemblyFilePath);
            } else {
                // Save changes
                Dispatch.call(assemblyDocument, "Save");
            }

            // Close the assembly document
            Dispatch.call(assemblyDocument, "Close");

        } catch (Exception e) {
            System.err.println("Error suppressing component: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error suppressing component", e);
        }
    }

    public Map<String, Object> suppressMultipleComponents(List<Map<String, Object>> suppressActions) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> results = new ArrayList<>();

        for (Map<String, Object> action : suppressActions) {
            String assemblyFilePath = Paths
                    .get(DESTINATION_PATH, "PC0300949_01_01", "MODEL", (String) action.get("assemblyFilePath"))
                    .toString();
            List<String> components = (List<String>) action.get("components");
            boolean suppress = (Boolean) action.get("suppress");

            List<String> suppressedComponents = new ArrayList<>();
            List<String> notFoundComponents = new ArrayList<>();

            for (String componentName : components) {
                boolean result = suppressSingleComponent(assemblyFilePath, componentName, suppress);
                if (result) {
                    suppressedComponents.add(componentName);
                } else {
                    notFoundComponents.add(componentName);
                }
            }

            Map<String, Object> assemblyResult = new HashMap<>();
            assemblyResult.put("assemblyFilePath", assemblyFilePath);
            assemblyResult.put("suppressedComponents", suppressedComponents);
            assemblyResult.put("notFoundComponents", notFoundComponents);
            results.add(assemblyResult);
        }

        response.put("results", results);
        return response;
    }

    private boolean suppressSingleComponent(String assemblyFilePath, String componentName, boolean suppress) {
        System.out.println("suppressComponent called with assemblyFilePath: " + assemblyFilePath + ", componentName: "
                + componentName + ", suppress: " + suppress);
        try {
            if (app == null) {
                app = new ActiveXComponent("Inventor.Application");
                app.setProperty("Visible", new Variant(true));
            }

            Dispatch documents = app.getProperty("Documents").toDispatch();
            Dispatch assemblyDocument = Dispatch.call(documents, "Open", assemblyFilePath, false).toDispatch();
            Dispatch componentDefinition = Dispatch.get(assemblyDocument, "ComponentDefinition").toDispatch();

            boolean isPartFile = assemblyFilePath.toLowerCase().endsWith(".ipt");

            if (isPartFile) {
                // Suppress a Feature (Extrusion) inside a Part (.ipt)
                Dispatch features = Dispatch.get(componentDefinition, "Features").toDispatch();
                Dispatch feature = Dispatch.call(features, "Item", componentName).toDispatch();
                if (feature != null) {
                    Dispatch.put(feature, "Suppressed", new Variant(suppress));
                    System.out.println("Feature " + componentName + " set to suppress: " + suppress);
                    Dispatch.call(assemblyDocument, "Save");
                    Dispatch.call(assemblyDocument, "Close");
                    return true;
                }
            } else {
                // Suppress a Component in an Assembly (.iam)
                Dispatch occurrences = Dispatch.get(componentDefinition, "Occurrences").toDispatch();

                boolean componentFound = false;
                int count = Dispatch.get(occurrences, "Count").getInt();

                // Iterate through occurrences to find the target component
                for (int i = 1; i <= count; i++) {
                    Dispatch occurrence = Dispatch.call(occurrences, "Item", i).toDispatch();
                    String occurrenceName = Dispatch.get(occurrence, "Name").toString();

                    if (occurrenceName.equalsIgnoreCase(componentName)) {
                        System.out.println("Found component: " + occurrenceName);

                        // Suppress or unsuppress based on the input
                        if (suppress) {
                            Dispatch.call(occurrence, "Suppress");
                            System.out.println("Component " + componentName + " suppressed.");
                        } else {
                            Dispatch.call(occurrence, "Unsuppress");
                            System.out.println("Component " + componentName + " unsuppressed.");
                        }

                        componentFound = true;
                        break;
                    }
                }

                if (!componentFound) {
                    System.err.println("Component " + componentName + " not found in " + assemblyFilePath);
                } else {
                    // Save changes
                    Dispatch.call(assemblyDocument, "Save");
                }

                // Close the assembly document
                Dispatch.call(assemblyDocument, "Close");

                return componentFound;

            }

            System.out.println("Component/Feature " + componentName + " not found in " + assemblyFilePath);
            return false;
        } catch (Exception e) {
            System.err.println("Error suppressing component/feature: " + e.getMessage());
            return false;
        }
    }
}
