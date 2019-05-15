package com.company.company.client;

import org.apache.wink.client.ClientResponse;

public class RestClient extends ClientBase {

	public RestClient() {
		super();
	}

	protected int getConnectionTimeout() {
		return 15000;
	}
	protected int getReadTimeout() {
		return 15000;
	}

	/**
	 * @param url - the url to get data from
	 * @return - json string of data
	 */
	public String getData(String url) {
		ClientResponse response = super.get(url);
		return handleResult(response, String.class);
	}
}
