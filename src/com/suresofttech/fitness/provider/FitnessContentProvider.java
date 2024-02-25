package com.suresofttech.fitness.provider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import com.suresofttech.fitness.model.FitnessEntry;
import com.suresofttech.fitness.model.FitnessEntryList;

public class FitnessContentProvider implements IStructuredContentProvider {
	private TableViewer viewer;
	private FitnessEntryList entryList;
	
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TableViewer) viewer;
		if(entryList != null){
			entryList.removeFitnessEntry((FitnessEntry)oldInput);
		}
	}

	@Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof List) {
            List<?> list = (List<?>) inputElement;
            return list.toArray();
        }
        return new Object[0];
    }

}
