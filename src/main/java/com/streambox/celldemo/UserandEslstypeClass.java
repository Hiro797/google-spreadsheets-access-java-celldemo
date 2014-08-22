package com.streambox.celldemo;

import java.util.ArrayList;

public class UserandEslstypeClass {

	private String label;
	// store yes or no
	private String systemadmin;
	private String contibutor;
	private String groupadmin;
	private ArrayList<ArrayList<String>> eslstype;

	public UserandEslstypeClass(String label, String systemadmin,
			String contributor, String groupadmin, ArrayList<ArrayList<String>> storage) {

		this.label = label;
		this.systemadmin = systemadmin;
		this.contibutor = contributor;
		this.groupadmin = groupadmin;
		this.eslstype = storage;

	}

	String getlabel() {
		return label;

	}

	String getsystemadmin() {

		return systemadmin;

	}

	String getgroupadmin() {

		return groupadmin;
	}

	String getcontributor() {

		return contibutor;
	}

	ArrayList<ArrayList<String>> getrslstype() {

		return eslstype;
	}

}
