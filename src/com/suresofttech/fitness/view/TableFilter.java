package com.suresofttech.fitness.view;

import java.util.Optional;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.suresofttech.fitness.model.FitnessEntry;

public class TableFilter extends ViewerFilter  {
    private String searchString;
    
    public void setSearchText(String s) {
        this.searchString = Messages.TableFilter_0 + s + Messages.TableFilter_1;
    }
    
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (searchString == null || searchString.length() == 0) {
            return true;
        }
        FitnessEntry entry = (FitnessEntry) element;
        if(entry.getDate().matches(searchString)){
        	return true;
        }
        
        if (Optional.ofNullable(entry.getDescription()).orElse(Messages.TableFilter_2).matches(searchString)) {
            return true;
        }
        
        if(entry.getPart().getKoreanName().toString().matches(searchString)){
        	return true;
        }
        
        if(String.valueOf(entry.getTime()).matches(searchString)){
        	return true;
        }
        
        return false;
    }
}
