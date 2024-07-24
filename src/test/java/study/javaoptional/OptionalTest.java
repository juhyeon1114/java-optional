package study.javaoptional;

import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class OptionalTest {

    @Test
    void 초기화() throws Exception {
        Optional<String> opt = Optional.of("abc");
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> opt3 = Optional.empty();

        assertThatThrownBy(() -> Optional.of(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    void 값의_존재_여부_체크() throws Exception {
        assertThat(Optional.of("abc").isPresent()).isTrue();
        assertThat(Optional.empty().isEmpty()).isTrue();

        Optional.of("Hello").ifPresent(s -> {
            log.info("{} 있다", s);
            // 무언가 수행
        });
    }

    @Test
    void orElse() throws Exception {
        Optional<Object> emptyOptional = Optional.empty();
        Optional<Object> notEmptyOptional = Optional.of("hello");

        assertThat(notEmptyOptional.orElse("abc")).isEqualTo("hello");
        assertThat(emptyOptional.orElse("abc")).isEqualTo("abc");
        assertThat(emptyOptional.orElseGet(() -> "abc")).isEqualTo("abc");

        assertThatThrownBy(() -> emptyOptional.orElseThrow(() -> new IllegalArgumentException("비어 있음")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 값_가져오기() throws Exception {
        assertThat(Optional.of("abc").get()).isEqualTo("abc");

        assertThatThrownBy(() -> Optional.empty().get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 값_가져오기_filter() throws Exception {
        Optional<String> opt = Optional.of("abc");

        assertThat(opt.filter(s -> "abc".equals(s)).get()).isEqualTo("abc");
        assertThatThrownBy(() -> opt.filter(s -> "hello".equals(s)).get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void 값_가져오기_map() throws Exception {
        Optional<String> opt = Optional.of("abc");

        assertThat(opt.map(s -> s.length()).get()).isEqualTo(3);
    }

}
