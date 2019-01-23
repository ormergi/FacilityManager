package com.projects.client_messages;

import com.projects.enums.eProvidersCategories;

@SuppressWarnings("serial")
public class OptimalProviderRequest implements IClientRequest
{
	private eProvidersCategories mCategory;

	public OptimalProviderRequest(eProvidersCategories category)
	{
		mCategory = category;
	}
	
	public eProvidersCategories category()
	{
		return mCategory;
	}
}
