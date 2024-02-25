package com.suresofttech.fitness.application;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.suresofttech.fitness.action.AboutAction;
import com.suresofttech.fitness.action.ExitAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private ExitAction exitAction;
	private AboutAction helpAction;


    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	exitAction = new ExitAction(window);
    	register(exitAction);
    	    	
    	helpAction = new AboutAction(window);
    	register(helpAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager(
    			Messages.ApplicationActionBarAdvisor_0, Messages.ApplicationActionBarAdvisor_1);
    	fileMenu.add(exitAction);
    	menuBar.add(fileMenu);
    	
    	MenuManager helpMenu = new MenuManager(
    			Messages.ApplicationActionBarAdvisor_2, Messages.ApplicationActionBarAdvisor_3);
    	helpMenu.add(helpAction);
    	menuBar.add(helpMenu);
    }
    
}
