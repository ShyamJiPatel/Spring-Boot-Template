package org.shyam.security.entity;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String sub;
	private final String iat;
	private final String token;
	private final String aud;
	private final String iss;
	private final String exp;

	public JwtAuthenticationResponse(String token, String iss, String exp, String iat, String sub, String aud) {
		this.token = token;
		this.iss = iss;
		this.exp = exp;
		this.iat = iat;
		this.sub = sub;
		this.aud = aud;

	}

	public String getSub() {
		return sub;
	}

	public String getToken() {
		return token;
	}

	public String getAud() {
		return aud;
	}

	public String getIss() {
		return iss;
	}

	public String getIat() {
		return iat;
	}

	public String getExp() {
		return exp;
	}

}
