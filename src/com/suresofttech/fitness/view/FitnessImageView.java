package com.suresofttech.fitness.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.suresofttech.fitness.color.ColorManager;
import com.suresofttech.fitness.model.FitnessEntry;
import com.suresofttech.fitness.model.FitnessEntryList;
import com.suresofttech.fitness.model.IModelChangeListener;

public class FitnessImageView extends ViewPart implements IModelChangeListener {
	final String CHEST_IMAGE = Messages.FitnessImageView_0;
	final String SHOULDER_IMAGE = Messages.FitnessImageView_1;	
	final String LAT_IMAGE = Messages.FitnessImageView_2;
	final String ABS_IMAGE = Messages.FitnessImageView_3;
	final String LEG_IMAGE = Messages.FitnessImageView_4;
	final String ARMS_IMAGE = Messages.FitnessImageView_5;
		
	public static final String ID = Messages.FitnessImageView_6;
	private Label imageLabel;
    private Image image;
	
	public FitnessImageView() {
		FitnessEntryList.getInstance().addListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite backGroundComposite = new Composite(parent, SWT.NONE);
		backGroundComposite.setLayout(new GridLayout(1, false));
		backGroundComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); 
		backGroundComposite.setBackground(ColorManager.WHITE);
		
		Composite imageComposite = new Composite(backGroundComposite, SWT.FILL | SWT.CENTER | SWT.CENTER);
		imageComposite.setLayout(new GridLayout(1, false));
		imageComposite.setLayoutData(new GridData(SWT.CENTER | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL | GridData.FILL_BOTH));
	    imageLabel = new Label(imageComposite, SWT.NONE | SWT.CENTER);
	    imageLabel.setLayoutData(new GridData(SWT.CENTER | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL | GridData.FILL_BOTH));
	}
	
	@Override
	public void setFocus() {
	}

	@Override
	public void fireChangedEvent(FitnessEntry entry) {
        updateImage(entry);
	}
	
	public void updateImage(FitnessEntry entry){	
		if(!FitnessEntryList.getInstance().getFitnessEntryList().contains(entry)){
			imageLabel.setImage(null);
			image.dispose();
			return;
		}
		
		switch(entry.getPart()){
		case CHEST :
			this.image = new Image(Display.getCurrent(), CHEST_IMAGE);
			break;
		case LAT :
			this.image = new Image(Display.getCurrent(), LAT_IMAGE);
			break;
		case ABS :
			this.image = new Image(Display.getCurrent(), ABS_IMAGE);
			break;
		case SHOULDER :
			this.image = new Image(Display.getCurrent(), SHOULDER_IMAGE);
			break;
		case ARMS :
			this.image = new Image(Display.getCurrent(), ARMS_IMAGE);
			break;
		case LEG :
			this.image = new Image(Display.getCurrent(), LEG_IMAGE);
			break;
		}
		
		imageLabel.setImage(image);
	}
}
