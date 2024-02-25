package com.suresofttech.fitness.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;

import com.suresofttech.fitness.dialog.AboutDialog;

public class AboutAction extends Action {
	private final static String ID = Messages.AboutAction_0;
	private final IWorkbenchWindow window;
	
    public AboutAction(IWorkbenchWindow window) {
        this.window = window;        
    	setId(ID);
    	setText(Messages.AboutAction_1);
    }
    
    @Override
    public void run() {
    	new AboutDialog(Display.getCurrent().getActiveShell()).open();
    }
}
