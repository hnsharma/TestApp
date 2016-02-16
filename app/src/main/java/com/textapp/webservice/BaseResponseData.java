package com.textapp.webservice;


public abstract class BaseResponseData 
{
    String      error;
    String      error_description;
    String      description;



    public String getError()
	{
		return error;
	}
    public String getDes()
    {
        return error_description==null?description:error_description;
    }
}
