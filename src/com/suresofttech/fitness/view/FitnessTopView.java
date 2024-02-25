package com.suresofttech.fitness.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.suresofttech.fitness.color.ColorManager;

public class FitnessTopView extends ViewPart {
	public static final String ID = Messages.FitnessTopView_0;
	private String IMAGE_PATH = Messages.FitnessTopView_1;


	public FitnessTopView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(ColorManager.WHITE);
		GridData titleGridData = new GridData();
		titleGridData.grabExcessHorizontalSpace = true;
		titleGridData.grabExcessVerticalSpace = true;
		
		Composite titleComposite = new Composite(parent, SWT.FILL | SWT.CENTER | SWT.CENTER);
		titleComposite.setLayout(new GridLayout(1, false));
		titleComposite.setLayoutData(titleGridData); 
		Image titleImage = new Image(titleComposite.getDisplay(), IMAGE_PATH); 
        Label imageLabel = new Label(titleComposite, SWT.CENTER | SWT.CENTER);
        imageLabel.setSize(200, 850);
        imageLabel.setImage(titleImage);
        
        GridData labelData = new GridData(SWT.FILL, SWT.FILL, true, true);
        imageLabel.setLayoutData(labelData);
        }

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}