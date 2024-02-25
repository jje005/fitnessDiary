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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.suresofttech.fitness.color.ColorManager;

public class AboutDialog extends Dialog {
	public AboutDialog(Shell shell){
		super(shell);
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(450, 300);
	}
	
	@Override
	public Control createDialogArea(Composite parent) { 
		GridLayout parentLayout = new GridLayout();
		parentLayout.numColumns = 1;
		parent.setBackground(ColorManager.WHITE);
		parent.setLayout(new GridLayout(1, false));

		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(ColorManager.WHITE);
		container.setLayout(new GridLayout(2, false));
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		
		GridLayout sideLayout = new GridLayout();
		sideLayout.horizontalSpacing = 5;
		
		Composite leftComposite = new Composite(container, SWT.NONE);
		leftComposite.setBackground(ColorManager.WHITE);
		leftComposite.setLayout(sideLayout);
		leftComposite.setLayoutData(new GridData(SWT.NONE, SWT.FILL, false, true));

		Label titleLabel = new Label(leftComposite, SWT.NONE);
		titleLabel.setText(Messages.AboutDialog_0);
		titleLabel.setBackground(ColorManager.WHITE);
				
		Label versionLabel = new Label(leftComposite, SWT.NONE);
		versionLabel.setText(Messages.AboutDialog_1);
		versionLabel.setBackground(ColorManager.WHITE);
		
		Label makerLabel = new Label(leftComposite, SWT.NONE);
		makerLabel.setText(Messages.AboutDialog_2);
		makerLabel.setBackground(ColorManager.WHITE);
		
		Label purposeLabel = new Label(leftComposite, SWT.NONE);
		purposeLabel.setText(Messages.AboutDialog_3);
		purposeLabel.setBackground(ColorManager.WHITE);
		
		Label noteLabel = new Label(leftComposite, SWT.NONE);
		noteLabel.setText(Messages.AboutDialog_4);
		noteLabel.setBackground(ColorManager.WHITE);
		
		Composite rightComposite = new Composite(container, SWT.NONE);
		rightComposite.setBackground(ColorManager.WHITE);
		rightComposite.setLayout(sideLayout);
		rightComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label titleValueLabel = new Label(rightComposite, SWT.NONE);
		titleValueLabel.setText(Messages.AboutDialog_5);
		titleValueLabel.setBackground(ColorManager.WHITE);
		
		Label versionValueLabel = new Label(rightComposite, SWT.NONE);
		versionValueLabel.setText(Messages.AboutDialog_6);
		versionValueLabel.setBackground(ColorManager.WHITE);

		Label makerValueLabel = new Label(rightComposite, SWT.NONE);
		makerValueLabel.setText(Messages.AboutDialog_7);
		makerValueLabel.setBackground(ColorManager.WHITE);

		Label purposeValueLabel = new Label(rightComposite, SWT.NONE);
		purposeValueLabel.setText(Messages.AboutDialog_8);
		purposeValueLabel.setBackground(ColorManager.WHITE);
		
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(rightComposite, SWT.BORDER | SWT.V_SCROLL);
		Text noteValueLabel = new Text(scrolledComposite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
		noteValueLabel.setText(Messages.AboutDialog_9 + Messages.AboutDialog_10 + Messages.AboutDialog_11 + Messages.AboutDialog_12 + Messages.AboutDialog_13);
		scrolledComposite.setContent(noteValueLabel);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setMinSize(noteValueLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT));


		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(ColorManager.WHITE);
		createButton(parent, IDialogConstants.OK_ID, Messages.AboutDialog_14, false).setBackground(ColorManager.WHITE);
	}
    
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.AboutDialog_15);
	}
	    
	@Override
	protected void okPressed() {
		super.okPressed();
	}
}
