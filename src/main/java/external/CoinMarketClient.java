package external;

import com.cashrich.backend.DTO.CoinMarketResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinMarketClient {

    public static final String X_CMC_PRO_API_KEY = "X-CMC_PRO_API_KEY";

    @Value("${coin.market.api.key}")
    String apiKey;

    @Value("${coin.market.fetch.api}")
    String cointMarketUri;

    private final RestTemplate restTemplate;

    public CoinMarketResponseDto getCoinsMarketDetails(List<String> coinType) {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add(X_CMC_PRO_API_KEY, apiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeader);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        UriComponents uri = uriComponentsBuilder.host(cointMarketUri)
                .queryParam("symbol", coinType).build();


        ResponseEntity<CoinMarketResponseDto> responseEntity = restTemplate
                .exchange(uri.toString(), HttpMethod.GET, httpEntity, CoinMarketResponseDto.class);

        return responseEntity.getBody();

    }
}
