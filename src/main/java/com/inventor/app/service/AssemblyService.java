package com.inventor.app.service;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.stereotype.Service;

@Service
public class AssemblyService {
    private boolean isAssemblyOpen = false;
    private ActiveXComponent app;

    public boolean isAssemblyOpen() {
        return isAssemblyOpen;
    }

    public void openAssembly(String assemblyPath) {
        try {
            app = new ActiveXComponent("Inventor.Application");
            app.setProperty("Visible", new Variant(true));
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
            if (app != null) {
                Dispatch activeDocument = app.getProperty("ActiveDocument").toDispatch();
                Dispatch.call(activeDocument, "Close", new Variant(false));
                isAssemblyOpen = false;
                System.out.println("Closing assembly...");
                app = null; // Release the ActiveXComponent
            } else {
                System.err.println("No active application instance to close.");
            }
        } catch (Exception e) {
            System.err.println("Error closing assembly: " + e.getMessage());
            throw new RuntimeException("Error closing assembly", e);
        }
    }
}
