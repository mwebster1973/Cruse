//******************************************************************************
//* Copyright (c) 2009 JLR. All Rights Reserved.
//*
//*   $Workfile:   StaticDataManager.java  $
//*   $Revision:   1.0  $
//*     $Author:   mwebst28  $
//*       $Date:   Oct 29 2009 14:49:36  $
//*
//******************************************************************************
package com.cruse.util;

import java.util.ArrayList;
import java.util.Collection;

import com.butter.domain.ButterReference;

public class StaticDataManager {
	
	public static Collection<ButterReference> getInputTypes(){
		
		Collection<ButterReference> types = new ArrayList<ButterReference>();
		
		types.add( new ButterReference("Checkbox", "Checkbox") );
		types.add( new ButterReference("Dropdown", "Dropdown") );
		types.add( new ButterReference("Numeric field", "Numeric field") );
		types.add( new ButterReference("Text field", "Text field") );
		
		return types;
	}

}
