package com.suresofttech.fitness.action;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;

import com.suresofttech.fitness.dialog.ExitDialog;

public class ExitAction extends Action {
	private final static String ID = Messages.ExitAction_0;
	private final IWorkbenchWindow window;
	
    public ExitAction(IWorkbenchWindow window) {
        this.window = window;        
    	setId(ID);
    	setText(Messages.ExitAction_1);
    }
    
    @Override
    public void run() {
    	new ExitDialog(Display.getCurrent().getActiveShell()).open();
    }
}
