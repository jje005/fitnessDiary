package com.suresofttech.fitness.application;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.suresofttech.fitness.view.FitnessImageView;
import com.suresofttech.fitness.view.FitnessTableView;
import com.suresofttech.fitness.view.FitnessTopView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {	
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);
		
        String editorArea = layout.getEditorArea();

		layout.addStandaloneView(FitnessTopView.ID, false, IPageLayout.TOP, 0.12f, editorArea);
		layout.addView(FitnessTableView.ID, IPageLayout.LEFT, 0.5f, editorArea);
		layout.addView(FitnessImageView.ID,IPageLayout.RIGHT, 0.5f, editorArea);
		layout.getViewLayout(FitnessTableView.ID).setCloseable(false);
		layout.getViewLayout(FitnessImageView.ID).setCloseable(false);
				
		layout.getViewLayout(FitnessTableView.ID).setMoveable(false);
		layout.getViewLayout(FitnessImageView.ID).setMoveable(false);
		layout.getViewLayout(editorArea).setMoveable(false);		
	}
}
