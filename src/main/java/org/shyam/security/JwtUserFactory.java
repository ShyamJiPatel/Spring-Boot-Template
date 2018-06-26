package org.shyam.security;

import java.util.List;
import java.util.stream.Collectors;

import org.shyam.security.entity.Authority;
import org.shyam.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(),
				mapToGrantedAuthorities(user.getAuthorities()), user.getEnabled(), user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
				.collect(Collectors.toList());
	}
}
