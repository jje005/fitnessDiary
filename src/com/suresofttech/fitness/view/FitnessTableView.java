package com.suresofttech.fitness.view;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.suresofttech.fitness.action.AddFitnessAction;
import com.suresofttech.fitness.color.ColorManager;
import com.suresofttech.fitness.dialog.FitnessAddDialog;
import com.suresofttech.fitness.model.BodyPart;
import com.suresofttech.fitness.model.FitnessEntry;
import com.suresofttech.fitness.model.FitnessEntryList;
import com.suresofttech.fitness.model.IModelChangeListener;
import com.suresofttech.fitness.provider.FitnessContentProvider;
import com.suresofttech.fitness.provider.FitnessLabelProvider;

public class FitnessTableView extends ViewPart  implements IModelChangeListener {
    private List<IModelChangeListener> listeners = new ArrayList<>();

	public static final String ID = Messages.FitnessTableView_0;
    private Text searchText;
    private Text countValueText;
    private Text timeValueText;
	private TableViewer tableViewer;
	private TableFilter tableFilter;

	public FitnessTableView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {	
		init();
        List<FitnessEntry> fitnessList = FitnessEntryList.getInstance().getFitnessEntryList();

		Composite tableComposite = new Composite(parent, SWT.NONE);
		GridLayout tableGridLayout = new GridLayout(1, false);
		tableComposite.setLayout(tableGridLayout);
		tableComposite.setBackground(ColorManager.WHITE);

	    searchText = new Text(tableComposite, SWT.BORDER | SWT.SEARCH);
	    searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); 
	    
	    tableViewer = new TableViewer(tableComposite, SWT.H_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
	    tableViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
	    
	    //테이블 생성
	    createColumns();
	    
	    
	    //검색 기능
	    tableFilter = new TableFilter();
	    tableViewer.addFilter(tableFilter);

        searchText.addModifyListener(e -> {
            String search = searchText.getText();
            tableFilter.setSearchText(search);
            tableViewer.refresh();
        });

	    //테이블  provider
	    tableViewer.setContentProvider(new FitnessContentProvider());
	    tableViewer.setLabelProvider(new FitnessLabelProvider());
	    tableViewer.setInput(fitnessList);
    	
	    Composite bottomComposite = new Composite(tableComposite, SWT.NONE);
	    bottomComposite.setLayout(new GridLayout(2, false));
        bottomComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
	    Composite btnComposite = new Composite(bottomComposite, SWT.NONE);
	    btnComposite.setLayout(new GridLayout(2, false));

		Button addBtn = new Button(btnComposite, SWT.PUSH);
		addBtn.setText(Messages.FitnessTableView_1); 
		addBtn.setBackground(ColorManager.WHITE);
		//항목 추가 이벤트 처리
		addBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
		        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		        AddFitnessAction addAction = new AddFitnessAction(window);
				new FitnessAddDialog(parent.getShell(), addAction).open();
		        
		        if (tableViewer != null) {
		            tableViewer.refresh();
		            refreshCountText();
		        }
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}
		});
		
        // 항목 클릭 이벤트 처리
        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (!selection.isEmpty()) {
                	FitnessEntry selectedEntry = (FitnessEntry) selection.getFirstElement();
                    FitnessEntryList.getInstance().fireChangedEvent(selectedEntry);
                }
            }
        });
		
		Button deleteBtn = new Button(btnComposite, SWT.PUSH);
		deleteBtn.setBackground(ColorManager.WHITE);
		deleteBtn.setText(Messages.FitnessTableView_3);
		
		 //삭제 버튼 클릭 이벤트 처리
		deleteBtn.addSelectionListener(new SelectionListener() {
			@Override
            public void widgetSelected(SelectionEvent e) {
                IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
                if (!selection.isEmpty()) {
                    boolean confirm = MessageDialog.openConfirm(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.FitnessTableView_4, Messages.FitnessTableView_5);
                    if (confirm) {
                    	FitnessEntry selectedEntry = (FitnessEntry) selection.getFirstElement();
                        FitnessEntryList.getInstance().removeFitnessEntry(selectedEntry);
                        FitnessEntryList.getInstance().fireChangedEvent(selectedEntry);
                    }
                }
		        if (tableViewer != null) {
		            tableViewer.refresh();
		            refreshCountText();
		        }
            }
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
	    Composite textComposite = new Composite(bottomComposite, SWT.NONE);
	    textComposite.setLayout(new GridLayout(4, false));
	    textComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
	    
		 
		Text countText = new Text(textComposite, SWT.NONE | SWT.END  | SWT.READ_ONLY);
		countText.setLayoutData(new GridData(SWT.RIGHT, SWT.RIGHT, true, false));
		countText.setText(Messages.FitnessTableView_6);
		
		countValueText = new Text(textComposite, SWT.NONE | SWT.END | SWT.READ_ONLY);
		countValueText.setText(Integer.toString(FitnessEntryList.getInstance().getFitnessEntryList().size())+ Messages.FitnessTableView_7);
		
		GridData countValueTextData = new GridData(SWT.RIGHT, SWT.RIGHT, true, false);
		countValueText.setLayoutData(countValueTextData);
				
		Text timeText = new Text(textComposite, SWT.NONE | SWT.END | SWT.READ_ONLY);
		timeText.setText(Messages.FitnessTableView_8);
		GridData timeTextData = new GridData(SWT.RIGHT, SWT.RIGHT, true, false);
		timeTextData.horizontalIndent = 40; // 간격 조절
		timeText.setLayoutData(timeTextData);
		
		timeValueText = new Text(textComposite, SWT.NONE | SWT.END  | SWT.READ_ONLY);
		timeValueText.setLayoutData(new GridData(SWT.RIGHT, SWT.RIGHT, true, false));

		refreshCountText();
	}

	//Table 생성
    private void createColumns() {	
        String[] titles = { Messages.FitnessTableView_9, Messages.FitnessTableView_10, Messages.FitnessTableView_11, Messages.FitnessTableView_12, Messages.FitnessTableView_13 };
        int[] bounds = { 50, 200, 100, 100, 300 };

        for (int i = 0; i < titles.length; i++) {
            TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
            column.getColumn().setWidth(bounds[i]);
            column.getColumn().setText(titles[i]);
        } 
        
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.getTable().setLinesVisible(true);
    }
    
    
    // 총 운동시간 
    private void refreshCountText(){
		countValueText.setText(Integer.toString(FitnessEntryList.getInstance().getFitnessEntryList().size())+ Messages.FitnessTableView_14);
		int time = 0;
		for(FitnessEntry entry : FitnessEntryList.getInstance().getFitnessEntryList()){
			time += entry.getTime();
		}
		
		if(time>61){
			int hour = 0;
			int sec = 0;
			hour = time/60; 
			sec = time%60;
			timeValueText.setText(Integer.toString(hour)+ Messages.FitnessTableView_15 + Integer.toString(sec) + Messages.FitnessTableView_16);
			
		} else{
			timeValueText.setText(Integer.toString(time)+ Messages.FitnessTableView_17);
		}
		
    }
    
    
    private void init(){
    	for(int i =1; i<31; i+=7){
    		FitnessEntry day1 = new FitnessEntry(Messages.FitnessTableView_18+(i)+Messages.FitnessTableView_19, 60, BodyPart.CHEST);
    		day1.setDescription(Messages.FitnessTableView_20);
    		FitnessEntryList.getInstance().addFitnessEntry(day1);
    		FitnessEntry day2 = new FitnessEntry(Messages.FitnessTableView_21+(i+1) +Messages.FitnessTableView_22, 40, BodyPart.LAT);
    		day2.setDescription(Messages.FitnessTableView_23);
    		FitnessEntryList.getInstance().addFitnessEntry(day2);
    		FitnessEntry day3 = new FitnessEntry(Messages.FitnessTableView_24+(i+2) +Messages.FitnessTableView_25, 30, BodyPart.LEG);
    		day3.setDescription(Messages.FitnessTableView_26);
    		FitnessEntryList.getInstance().addFitnessEntry(day3);
    	}
    	
    	for(int i =1; i<31; i+=7){
    		FitnessEntry day1 = new FitnessEntry(Messages.FitnessTableView_27+(i)+Messages.FitnessTableView_28, 60, BodyPart.CHEST);
    		FitnessEntryList.getInstance().addFitnessEntry(day1);
    		day1.setDescription(Messages.FitnessTableView_29);
    		FitnessEntry day2 = new FitnessEntry(Messages.FitnessTableView_30+(i+1) +Messages.FitnessTableView_31, 40, BodyPart.LAT);
    		FitnessEntryList.getInstance().addFitnessEntry(day2);
    		day2.setDescription(Messages.FitnessTableView_32);
    		FitnessEntry day3 = new FitnessEntry(Messages.FitnessTableView_33+(i+2) +Messages.FitnessTableView_34, 30, BodyPart.ARMS);
    		day3.setDescription(Messages.FitnessTableView_35);
    		
    		FitnessEntryList.getInstance().addFitnessEntry(day3);
    	}
    	
    	for(int i =1; i<28; i+=7){
    		FitnessEntry day1 = new FitnessEntry(Messages.FitnessTableView_36+(i)+Messages.FitnessTableView_37, 60, BodyPart.ABS);
    		day1.setDescription(Messages.FitnessTableView_38);
    		FitnessEntryList.getInstance().addFitnessEntry(day1);
    		FitnessEntry day2 = new FitnessEntry(Messages.FitnessTableView_39+(i+1) +Messages.FitnessTableView_40, 40, BodyPart.ARMS);
    		day2.setDescription(Messages.FitnessTableView_41);
    		FitnessEntryList.getInstance().addFitnessEntry(day2);
    		FitnessEntry day3 = new FitnessEntry(Messages.FitnessTableView_42+(i+2) +Messages.FitnessTableView_43, 30, BodyPart.SHOULDER);
    		FitnessEntryList.getInstance().addFitnessEntry(day3);
    		day3.setDescription(Messages.FitnessTableView_44);

    	}
    	
    }
	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}
	
    public void addColumnSelectionListener(IModelChangeListener listener) {
        listeners.add(listener);
    }

    public void removeColumnSelectionListener(IModelChangeListener listener) {
        listeners.remove(listener);
    }
    
    @Override
    public void fireChangedEvent(FitnessEntry entry) {
        for (IModelChangeListener listener : listeners) {
            listener.fireChangedEvent(entry);
        }
    }
}
