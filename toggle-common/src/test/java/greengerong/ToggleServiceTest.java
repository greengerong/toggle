package greengerong;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

/******************************************
 *                                        *
 * Auth: green gerong                     *
 * Date: 2018                             *
 * blog: http://greengerong.github.io/    *
 * github: https://github.com/greengerong *
 *                                        *
 ******************************************/
@RunWith(MockitoJUnitRunner.class)
public class ToggleServiceTest {

    private final String enableFeature = "enable";
    private final String disableFeature = "disable";
    @Mock
    private FeaturesFetcher featuresFetcher;
    @InjectMocks
    private ToggleService toggleService;

    @Before
    public void setUp() throws Exception {
        final Map<String, Boolean> features = new HashMap<>();
        features.put(enableFeature, true);
        features.put(disableFeature, false);
        when(featuresFetcher.getFeatures()).thenReturn(features);
    }

    @Test
    public void should_is_active_when_feature_is_enable() throws Exception {
        //given

        //when
        final boolean active = toggleService.isActive(enableFeature);
        //then
        assertThat(active).isTrue();
    }

    @Test
    public void should_toggle_result_when_feature_is_enable() throws Exception {
        //given
        final String enableResult = "enable";

        //when
        final String result = toggleService.toggle(enableFeature, () -> enableResult);
        //then
        assertThat(result).isEqualTo(enableResult);
    }

    @Test
    public void should_toggle_result_when_feature_is_disable() throws Exception {
        //given
        final String enableResult = "enable";
        final String disableResult = "disable";

        //when
        final String result = toggleService.toggle(disableFeature, () -> enableResult, () -> disableResult);
        //then
        assertThat(result).isEqualTo(disableResult);
    }

    @Test
    public void should_toggle_action_when_feature_is_enable() throws Exception {
        //given
        final String[] toggleResult = new String[1];

        //when
        toggleService.toggle(enableFeature, () -> toggleResult[0] = "enable");
        //then
        assertThat(toggleResult[0]).isEqualTo("enable");
    }

    @Test
    public void should_toggle_action_when_feature_is_disable() throws Exception {
        //given
        final String[] toggleResult = new String[1];

        //when
        toggleService.toggle(disableFeature, () -> toggleResult[0] = "enable", () -> toggleResult[0] = "disable");
        //then

        assertThat(toggleResult[0]).isEqualTo("disable");
    }
}