package com.suresofttech.fitness.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import com.suresofttech.fitness.model.BodyPart;
import com.suresofttech.fitness.model.FitnessEntry;
import com.suresofttech.fitness.model.FitnessEntryList;

public class FitnessLabelProvider extends LabelProvider implements ITableLabelProvider {
	private TableViewer viewer;
	private FitnessEntry entryList;

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		FitnessEntry entry = (FitnessEntry) element;
		
		switch (columnIndex) {
		case 0:
            int index = FitnessEntryList.getInstance().getFitnessEntryList().indexOf(element);
            return Integer.toString(index+1);
		case 1:	
			return entry.getDate();
		case 2:
			return Integer.toString(entry.getTime())+Messages.FitnessLabelProvider_0;
		case 3:
			return entry.getPart().getKoreanName();
		case 4:
			return entry.getDescription();
		}

		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
