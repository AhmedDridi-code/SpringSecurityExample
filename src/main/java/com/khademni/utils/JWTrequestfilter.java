package com.khademni.utils;



import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.khademni.services.MyUserDetailsService;


@Component
public class JWTrequestfilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtility jwtutil;
	@Autowired
	private MyUserDetailsService msd;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String Authoheder = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		if (Authoheder != null && Authoheder.startsWith("Bearer")) {
			jwt = Authoheder.substring(7);
			username = jwtutil.getUsernameFromToken(jwt);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails ud = msd.loadUserByUsername(username);
			if (jwtutil.validateToken(jwt, ud)) {
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(ud, null,
						ud.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		filterChain.doFilter (request,response);
	}

}
