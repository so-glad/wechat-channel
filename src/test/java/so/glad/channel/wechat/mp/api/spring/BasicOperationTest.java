package so.glad.channel.wechat.mp.api.spring;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;
import so.glad.channel.wechat.model.AccessToken;
import so.glad.channel.wechat.mp.api.BasicOperation;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author palmtale
 *         on 15/7/9.
 */
public class BasicOperationTest {
    private BasicOperation basicOperation;
    @Before
    public void prepare(){
        BasicOperationSpring springImplementation = new BasicOperationSpring();
        springImplementation.setRestTemplate(new RestTemplate());
        basicOperation = springImplementation;
    }
    @Test
    public void test(){
        AccessToken accessToken =
                basicOperation.archieveAccessToken("wx9dc43bc21ace677c","d81b88a0f4b4750394100a301c1dab4a");
    }

    private RestTemplate createSSLRestTemplate(){
        SSLContextBuilder builder = new SSLContextBuilder();
        SSLConnectionSocketFactory sslsf = null;
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            sslsf = new SSLConnectionSocketFactory(
                    builder.build());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpclient);
        return new RestTemplate(httpComponentsClientHttpRequestFactory);
    }
}
