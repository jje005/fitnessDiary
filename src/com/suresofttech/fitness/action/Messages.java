package com.suresofttech.fitness.action;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.suresofttech.fitness.action.messages"; //$NON-NLS-1$
	public static String AboutAction_0;
	public static String AboutAction_1;
	public static String ExitAction_0;
	public static String ExitAction_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
