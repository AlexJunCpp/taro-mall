package org.rxjava.api.user;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.rxjava.api.user.client.UserApi;
import org.rxjava.api.user.form.LoginByPhoneSmsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author happy 2019-05-10 17:45
 */
@SpringBootTest(classes = {TestContext.class})
@RunWith(SpringRunner.class)
public class TestBase {
    @Autowired
    protected UserApi userApi;
    @Autowired
    protected ApplicationContext applicationContext;

    @Before
    public void init() {
        LoginByPhoneSmsForm form = new LoginByPhoneSmsForm();
        form.setPhone("18101010101");
        form.setSms("283843");
        String token = userApi.loginByPhoneSms(form).block();

        Map<String, ReactiveHttpClientAdapter> beans = applicationContext
                .getBeansOfType(ReactiveHttpClientAdapter.class);
        beans.values().forEach(a -> {
            a.setToken(token);
        });
    }
}
