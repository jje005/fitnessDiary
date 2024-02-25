package 	com.suresofttech.fitness.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.suresofttech.fitness.action.AddFitnessAction;
import com.suresofttech.fitness.color.ColorManager;
import com.suresofttech.fitness.model.BodyPart;
import com.suresofttech.fitness.model.FitnessEntry;

public class FitnessAddDialog extends Dialog {
    private static final int ADD_ID = IDialogConstants.CLIENT_ID + 1;
    private AddFitnessAction addAction;
    private DateTime datePicker;
    private Spinner   timeSpinner;
    private Combo  partCombo;
    private Text noteText;
    private FitnessEntry entry;
    
	public FitnessAddDialog(Shell shell, AddFitnessAction addAction) {
		super(shell);
		this.addAction = addAction;
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(350, 220);
	}
	
    @Override
    public Control createDialogArea(Composite parent) {
    	GridLayout compositeLayout = new GridLayout();
    	compositeLayout.numColumns = 1;
    	compositeLayout.marginWidth = 0; 
    	compositeLayout.marginHeight = 0; 
    	
    	parent.setBackground(ColorManager.WHITE);
    	parent.setLayout(compositeLayout);
    	
    	GridLayout contentsLayout = new GridLayout();
    	contentsLayout.numColumns = 2;
    	contentsLayout.horizontalSpacing = 25;
    	contentsLayout.marginWidth = 5; 

    	GridData contentsData = new GridData();
    	contentsData.horizontalAlignment = GridData.FILL;
    	contentsData.grabExcessHorizontalSpace = true;
    	
        Composite container = new Composite(parent, SWT.BORDER);
    	
        container.setLayout(contentsLayout);
        container.setLayoutData(contentsData);
        container.setBackground(ColorManager.WHITE);


        Label dateLabel= new Label(container, SWT.NONE);
        dateLabel.setText(Messages.FitnessAddDialog_0);
        dateLabel.setBackground(ColorManager.WHITE);

        datePicker = new DateTime(container, SWT.DATE | SWT.DROP_DOWN); 
        datePicker.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        
        Label timeLabel = new Label(container, SWT.NONE);
        timeLabel.setText(Messages.FitnessAddDialog_1);
        timeLabel.setBackground(ColorManager.WHITE);
        
        Composite timeComposite = new Composite(container, SWT.NONE);
        timeComposite.setBackground(ColorManager.WHITE);
        GridLayout timeLayout = new GridLayout(2, false);
        timeLayout.marginWidth = 5;
        timeLayout.marginHeight = 5;
        timeLayout.verticalSpacing = 0;
        timeLayout.horizontalSpacing = 0;
		timeComposite.setLayout(timeLayout);
		
        timeSpinner = new Spinner (timeComposite, SWT.BORDER);  
        timeSpinner.setMinimum(0);
        timeSpinner.setMaximum(120);
        timeSpinner.setSelection(0);
        timeSpinner.setIncrement(10);
        
        Label timeSpinnerLabel = new Label(timeComposite, SWT.NONE);
        timeSpinnerLabel.setText(Messages.FitnessAddDialog_2);
        timeSpinnerLabel.setBackground(ColorManager.WHITE);
        
        Label partLabel = new Label(container, SWT.NONE);
        partLabel.setText(Messages.FitnessAddDialog_3);
        partLabel.setBackground(ColorManager.WHITE);
        partCombo = new Combo (container, SWT.NONE);      
        partCombo.setSize(50, 10);
        for (BodyPart part : BodyPart.values()) {
            partCombo.add(part.getKoreanName());
        }      
        
        Label noteLabel = new Label(container, SWT.NONE | SWT.FILL);
        noteLabel.setText(Messages.FitnessAddDialog_4);
        noteLabel.setBackground(ColorManager.WHITE);
        noteText = new Text(container, SWT.BORDER);
        noteText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
       
        

        return container;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
    	GridLayout compositeLayout = new GridLayout();
    	compositeLayout.marginWidth = 0; // 위 공백 제거

    	parent.setBackground(ColorManager.WHITE);
    	parent.setLayout(compositeLayout);
        createButton(parent, ADD_ID, Messages.FitnessAddDialog_5, true).setBackground(ColorManager.WHITE);
        createButton(parent, IDialogConstants.CANCEL_ID, Messages.FitnessAddDialog_6, false).setBackground(ColorManager.WHITE);
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == ADD_ID) { // "추가" 버튼이 눌렸을 때
            okPressed(); // okPressed() 호출
        } else {
            super.buttonPressed(buttonId); // 그 외의 버튼이 눌렸을 때는 기본 처리
        }
    }
    
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.FitnessAddDialog_7);
    }
    
	@Override
	protected void okPressed() {
		FitnessEntry entry = new FitnessEntry(
				datePicker.getYear() + Messages.FitnessAddDialog_8 + (datePicker.getMonth() + 1) + Messages.FitnessAddDialog_9 + datePicker.getDay()+Messages.FitnessAddDialog_10, 
				Integer.parseInt(timeSpinner.getText()), 
				BodyPart.fromKoreanName(partCombo.getText())
		);

		if(noteText.getText() != null){
			entry.setDescription(noteText.getText());
		}
		this.entry = entry;
		addAction.addFitnessEntry(entry);

		super.okPressed();
	}
	
    public FitnessEntry getEntry() {
        return entry;
    }
}

