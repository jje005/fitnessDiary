package com.suresofttech.fitness.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;


public class FitnessEntryList {
	private List<FitnessEntry> fitnessEntryList;
	
	private List<IModelChangeListener> iModelChangedListenerList = new ArrayList<>();

	
    private static FitnessEntryList instance = null;

	
	public FitnessEntryList(){
		fitnessEntryList = new ArrayList<>();

	}
	
    public static FitnessEntryList getInstance() {
        if (instance == null) {
            instance = new FitnessEntryList();
        }
        return instance;
    }
    

    public FitnessEntry getEntry(int index) {
        return fitnessEntryList.get(index);
    }
    
    public List<FitnessEntry> getFitnessEntryList() {
        return fitnessEntryList;
    }

	public void setFitnessEntry(List<FitnessEntry> fitnessEntry) {
		this.fitnessEntryList = fitnessEntry;
	}
	
	public void removeFitnessEntry(FitnessEntry fitnessEntry){
		if(fitnessEntry != null){
			this.fitnessEntryList.remove(fitnessEntry);
		}
	}
		
	public void addFitnessEntry(FitnessEntry fitnessEntry){
		this.fitnessEntryList.add(0, fitnessEntry);
	}
	
	public void addListener(IModelChangeListener listener) {
		iModelChangedListenerList.add(listener);
	}
	
	public void removeListener(IModelChangeListener listener) {
		iModelChangedListenerList.remove(listener);
	}
	
	public void fireChangedEvent(FitnessEntry entry) {
		for(IModelChangeListener listener : iModelChangedListenerList) {
			listener.fireChangedEvent(entry);
		}
	}
	
	

}
