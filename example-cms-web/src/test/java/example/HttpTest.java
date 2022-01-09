package example;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import java.time.Duration;

import example.starter.hbase.HbaseTemplate;
import example.util.AssertUtil;
import example.util.ResourceUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ActiveProfiles("test")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class HttpTest {

    @Autowired
    protected WebTestClient http;

    @MockBean
    protected HbaseTemplate hbaseTemplate;

    @BeforeEach
    void beforeEach(
        final RestDocumentationContextProvider restDocumentationContextProvider
    ) {
        this.http = http.mutate()
            .responseTimeout(Duration.ofHours(1))
            .filter(documentationConfiguration(restDocumentationContextProvider)
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint())
            )
            .build();
    }

    protected ResourceUtil getResourceUtil() {
        return ResourceUtil.of();
    }

    protected AssertUtil getAssertUtil() {
        return AssertUtil.of(this.getResourceUtil());
    }
}
