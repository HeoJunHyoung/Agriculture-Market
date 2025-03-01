package market.agriculture.service;

import market.agriculture.dto.CustomOAuth2User;
import market.agriculture.dto.NaverResponse;
import market.agriculture.dto.Oauth2Response;
import market.agriculture.dto.UserDto;
import market.agriculture.entity.Customer;
import market.agriculture.entity.Seller;
import market.agriculture.entity.UserEntity;
import market.agriculture.entity.enumerate.Role;
import market.agriculture.repository.CustomerRepository;
import market.agriculture.repository.SellerRepository;
import market.agriculture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2Service extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomOauth2Service(UserRepository userRepository, SellerRepository sellerRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2Response oauth2Response = null;
        String oauthId = oauth2Response.getProvider() + " " + oauth2Response.getProviderId();
        Seller seller = null;
        Customer customer = null;

        if (registrationId.equals("naver")){
            oauth2Response = new NaverResponse(oAuth2User.getAttributes());
            seller = sellerRepository.findByNaverId(oauthId);
            customer = customerRepository.findByNaverId(oauthId);
        }
        else{
            System.out.println("registrationId값이 naver가 아님");
            System.out.println("registrationId : "+registrationId);
            return null;
        }

        if(seller != null){

            seller.setName(oauth2Response.getName());
            seller.setNaverId(oauthId);

            sellerRepository.save(seller);

            UserDto userDTO = UserDto.builder()
                    .name(seller.getName())
                    .oauthId(oauthId)
                    .role(seller.getRole())
                    .build();

            return new CustomOAuth2User(userDTO);
        }
        else if(customer != null){

            customer.setName(oauth2Response.getName());
            customer.setNaverId(oauthId);

            customerRepository.save(customer);

            UserDto userDTO = UserDto.builder()
                    .name(customer.getName())
                    .oauthId(oauthId)
                    .role(customer.getRole())
                    .build();

            return new CustomOAuth2User(userDTO);
        }
        else{
            UserEntity userEntity = new UserEntity();
            userEntity.setOauthId(oauthId);
            userEntity.setName(oauth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

            UserDto userDTO = UserDto.builder()
                    .name(oauth2Response.getName())
                    .oauthId(oauthId)
                    .role(Role.Temp)
                    .build();

            return new CustomOAuth2User(userDTO);
        }
    }

}
