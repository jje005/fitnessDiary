package com.suresofttech.fitness.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.suresofttech.fitness.color.ColorManager;

public class ExitDialog extends Dialog {
	public ExitDialog(Shell shell){
		super(shell);
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(450, 200);
	}
	
	@Override
	public Control createDialogArea(Composite parent) {
		parent.setBackground(ColorManager.WHITE);
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(ColorManager.WHITE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label label = new Label(container, SWT.CENTER);
		label.setText(Messages.ExitDialog_0);
		GridData labelData = new GridData(SWT.CENTER | SWT.FILL, SWT.CENTER, true, true);
		labelData.verticalAlignment = SWT.CENTER;
		labelData.horizontalIndent = 5;
		label.setLayoutData(labelData);
		label.setBackground(ColorManager.WHITE);

		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(ColorManager.WHITE);
		createButton(parent, IDialogConstants.OK_ID, Messages.ExitDialog_1, false).setBackground(ColorManager.WHITE);
	
		createButton(parent, IDialogConstants.CANCEL_ID, Messages.ExitDialog_2, false).setBackground(ColorManager.WHITE);
	}
    
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ExitDialog_3);
	}
	    
	@Override
	protected void okPressed() {
		Display.getCurrent().close();
		super.okPressed();
		
	}
}
