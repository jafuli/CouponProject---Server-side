package com.example.project3.webConfig;

import com.example.project3.facades.ClientFacade;

public class Session {

	private ClientFacade facade;
	private long lastAccessed;

	public Session(ClientFacade facade, long lastAccessed) {
		super();
		this.facade = facade;
		this.lastAccessed = lastAccessed;
	}

	public ClientFacade getFacade() {
		return facade;
	}

	public void setFacade(ClientFacade facade) {
		this.facade = facade;
	}

	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

}
