package com.suresofttech.fitness.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.suresofttech.fitness.dialog.FitnessAddDialog;
import com.suresofttech.fitness.model.FitnessEntry;
import com.suresofttech.fitness.model.FitnessEntryList;

public class AddFitnessAction extends Action implements ISelectionListener, IWorkbenchAction {
	private final static String ID = "com.suresofttech.fitness.action.addFitnessAction";
	private final IWorkbenchWindow window;
	private IStructuredSelection selection;
	
	public AddFitnessAction(IWorkbenchWindow iwindow){
        this.window = iwindow;
        window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		// TODO Auto-generated method stub
	}
	
    public void addFitnessEntry(FitnessEntry entry) {
        if(entry != null){
        	FitnessEntryList.getInstance().addFitnessEntry(entry);
        }
    }

	@Override
	public void dispose() {
        window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1 &&
					selection.getFirstElement() instanceof FitnessEntry);
		} else {
			setEnabled(false);
		}
	}
}
