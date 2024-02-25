package com.suresofttech.fitness.provider;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.suresofttech.fitness.provider.messages"; //$NON-NLS-1$
	public static String FitnessLabelProvider_0;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
