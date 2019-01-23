package com.projects.client_messages;

import com.projects.enums.eProvidersCategories;

@SuppressWarnings("serial")
public class ProviderByCategoryRequest implements IClientRequest
{
	private eProvidersCategories mCategory;
	
	public ProviderByCategoryRequest(eProvidersCategories category)
	{
		mCategory = category;
	}
	public eProvidersCategories category()
	{
		return mCategory;
	}
}
