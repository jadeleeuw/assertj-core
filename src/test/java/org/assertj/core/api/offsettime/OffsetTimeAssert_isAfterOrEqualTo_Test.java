/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.api.offsettime;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.time.OffsetTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class OffsetTimeAssert_isAfterOrEqualTo_Test extends OffsetTimeAssertBaseTest {

  @Test
  public void test_isAfterOrEqual_assertion() {
    // WHEN
    assertThat(AFTER).isAfterOrEqualTo(REFERENCE);
    assertThat(AFTER).isAfterOrEqualTo(REFERENCE.toString());
    assertThat(REFERENCE).isAfterOrEqualTo(REFERENCE);
    assertThat(REFERENCE).isAfterOrEqualTo(REFERENCE.toString());
    // THEN
    verify_that_isAfterOrEqual_assertion_fails_and_throws_AssertionError(BEFORE, REFERENCE);
  }

  @Test
  public void test_isAfterOrEqual_assertion_error_message() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> assertThat(OffsetTime.of(3, 0, 5, 0,
                                                                                              ZoneOffset.UTC)).isAfterOrEqualTo(OffsetTime.of(3,
                                                                                                                                              3,
                                                                                                                                              3,
                                                                                                                                              0,
                                                                                                                                              ZoneOffset.UTC)))
                                                   .withMessage(format("%n" +
                                                                      "Expecting:%n" +
                                                                      "  <03:00:05Z>%n" +
                                                                      "to be after or equals to:%n" +
                                                                      "  <03:03:03Z>"));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      OffsetTime actual = null;
      assertThat(actual).isAfterOrEqualTo(OffsetTime.now());
    }).withMessage(actualIsNull());
  }

  @Test
  public void should_fail_if_offsetTime_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(OffsetTime.now()).isAfterOrEqualTo((OffsetTime) null))
                                        .withMessage("The OffsetTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_offsetTime_as_string_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(OffsetTime.now()).isAfterOrEqualTo((String) null))
                                        .withMessage("The String representing the OffsetTime to compare actual with should not be null");
  }

  private static void verify_that_isAfterOrEqual_assertion_fails_and_throws_AssertionError(OffsetTime timeToCheck,
                                                                                           OffsetTime reference) {
    try {
      assertThat(timeToCheck).isAfterOrEqualTo(reference);
    } catch (AssertionError e) {
      // AssertionError was expected, test same assertion with String based parameter
      try {
        assertThat(timeToCheck).isAfterOrEqualTo(reference.toString());
      } catch (AssertionError e2) {
        // AssertionError was expected (again)
        return;
      }
    }
    fail("Should have thrown AssertionError");
  }

}
