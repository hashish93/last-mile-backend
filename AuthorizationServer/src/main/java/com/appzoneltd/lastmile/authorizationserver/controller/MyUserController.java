package com.appzoneltd.lastmile.authorizationserver.controller;

//@RestController
//@EnableResourceServer
public class MyUserController {

//	@Autowired
//	private UserService userService;
//		
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Bean
//	public TokenStore tokenStore() {
//		return new InMemoryTokenStore();
//	}
//	
//	@RequestMapping("/user")
//	public MyUser user(Principal principal) throws JsonParseException, JsonMappingException, IOException {
//		
//		MyPrincipal myPrincipal = objectMapper.readValue(principal.getName(),MyPrincipal.class);
//		UserEntity userEntity=userService.findByIdentifier(myPrincipal.getName());
//		
//		MyUser myUser=new MyUser();
//		if(userEntity!=null){
//			myUser.setUserId(userEntity.getUserId());
//			myUser.setUsername(userEntity.getUsername());
//			myUser.setEmail(userEntity.getEmail());
//			myUser.setPhone(userEntity.getPhone());
//			myUser.setStatus(userEntity.getStatus());
//			myUser.setDescription(userEntity.getDescription());
//			if(userEntity.getUserType() !=null){
//				myUser.setUserType(userEntity.getUserType().getName());
//				myUser.setUserTypeId(userEntity.getUserType().getId());
//			}
//			myUser.setPersonalPhotoId(userEntity.getPhotoId());
//			myUser.setPageSize(25);
//		}
//		return myUser;
//	} 

}
