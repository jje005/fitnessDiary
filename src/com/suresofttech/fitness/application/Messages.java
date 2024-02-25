package com.suresofttech.fitness.application;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.suresofttech.fitness.application.messages"; //$NON-NLS-1$
	public static String ApplicationActionBarAdvisor_0;
	public static String ApplicationActionBarAdvisor_1;
	public static String ApplicationActionBarAdvisor_2;
	public static String ApplicationActionBarAdvisor_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
