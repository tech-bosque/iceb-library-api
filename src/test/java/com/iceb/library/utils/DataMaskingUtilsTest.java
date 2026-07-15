package com.iceb.library.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DataMaskingUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "user@example.org, u***@e******.org",
            "contact@mail.co, c******@m***.co",
            "ana.paula@escola.edu, a********@e*****.edu",
            "dev.team@company.io, d*******@c******.io",
            "j@x.io, j@x.io"
    })
    void maskEmail(String input, String expected) {
        assertThat(DataMaskingUtils.maskEmail(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void maskEmailWhenBlankReturnsAsIs(String input) {
        assertThat(DataMaskingUtils.maskEmail(input)).isEqualTo(input);
    }

    @ParameterizedTest
    @CsvSource({
            "1234567890, ******7890",
            "12982653239, *******3239",
            "'+55 12 98169-6137', '+55 12 *****-6137'",
            "'+129997702520', '+*********2520'"
    })
    void maskPhone(String input, String expected) {
        assertThat(DataMaskingUtils.maskPhone(input)).isEqualTo(expected);
    }

    @Test
    void maskPhoneWhenFourOrFewerDigitsMasksAllDigits() {
        assertThat(DataMaskingUtils.maskPhone("1234")).isEqualTo("****");
        assertThat(DataMaskingUtils.maskPhone("(12) 34")).isEqualTo("(**) **");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void maskPhoneWhenBlankReturnsAsIs(String input) {
        assertThat(DataMaskingUtils.maskPhone(input)).isEqualTo(input);
    }
}
