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
package org.assertj.core.api.bytebuffer;

import com.google.common.base.Charsets;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.bytebuffer.ContentsShouldContain.contentsShouldContain;

public class ByteBuffer_contains_Test {

  @Test
  public void should_pass_when_buffer_contains_expected_string_with_default_charset() {
    ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
    assertThat(buffer).contains("es");
  }

  @Test
  public void should_fail_when_buffer_does_not_contain_expected_string_with_default_charset() {
    String actual = "test";
    String expected = "xy";

    ByteBuffer buffer = ByteBuffer.wrap(actual.getBytes());
    assertThatThrownBy(() -> assertThat(buffer).contains(expected))
      .isInstanceOf(AssertionError.class)
      .hasMessage(contentsShouldContain(expected, buffer).create());
  }

  @Test
  public void should_pass_when_buffer_contains_expected_string_with_specified_charset() {
    Charset specified = Charsets.UTF_8;

    ByteBuffer buffer = specified.encode("test");
    assertThat(buffer).contains("es", specified);
  }

  @Test
  public void should_fail_when_buffer_does_not_contain_expected_string_with_specified_charset() {
    String actual = "test";
    String expected = "xy";
    Charset specified = Charsets.UTF_8;

    ByteBuffer buffer = specified.encode(actual);
    assertThatThrownBy(() -> assertThat(buffer).contains(expected, specified))
      .isInstanceOf(AssertionError.class)
      .hasMessage(contentsShouldContain(expected, buffer).create());
  }

  @Test
  public void should_pass_when_buffer_contains_expected_byte_array() {
    ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
    assertThat(buffer).contains("es".getBytes());
  }

  @Test
  public void should_fail_when_buffer_does_not_contain_expected_byte_array() {
    String actual = "test";
    String expected = "xy";

    ByteBuffer buffer = ByteBuffer.wrap(actual.getBytes());
    assertThatThrownBy(() -> assertThat(buffer).contains(expected.getBytes()))
      .isInstanceOf(AssertionError.class);
  }

  @Test
  public void should_pass_when_buffer_contains_expected_byte_buffer() {
    ByteBuffer actual = ByteBuffer.wrap("test".getBytes());
    ByteBuffer expected = ByteBuffer.wrap("es".getBytes());

    assertThat(actual).contains(expected);
  }

  @Test
  public void should_fail_when_buffer_does_not_contain_expected_byte_buffer() {
    ByteBuffer actual = ByteBuffer.wrap("test".getBytes());
    ByteBuffer expected = ByteBuffer.wrap("xy".getBytes());

    assertThatThrownBy(() -> assertThat(actual).contains(expected))
      .isInstanceOf(AssertionError.class);
  }
}