package com.api.services;

public class JWTLoginFilter {

//	public JWTLoginFilter(String url, AuthenticationManager authManager) {
//		super(new AntPathRequestMatcher(url));
//		setAuthenticationManager(authManager);
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException, IOException, ServletException {
//		
//		AccountCredentials credentials = new ObjectMapper()
//				.readValue(request.getInputStream(), AccountCredentials.class);
//		
//		return getAuthenticationManager().authenticate(
//				new UsernamePasswordAuthenticationToken(
//						credentials.getEmail(), 
//						credentials.getPassword(), 
//						Collections.emptyList()
//						)
//				);
//	}
//	
//	@Override
//	protected void successfulAuthentication(
//			HttpServletRequest request, 
//			HttpServletResponse response,
//			FilterChain filterChain,
//			Authentication auth) throws IOException, ServletException {
//		
//		TokenAuthenticationService.addAuthentication(response, auth.getName());
//	}

}
