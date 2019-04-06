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
package org.assertj.core.error.buffer;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import java.nio.Buffer;

public class ShouldHaveLength extends BasicErrorMessageFactory {

  private static final String SHOULD_HAVE_LENGTH = "%nExpected%n  <%s>%nto have length%n  <%s>%nbut was%n  <%s>%n";

  public static ErrorMessageFactory shouldHaveLength(int expected, int actual, Buffer buffer) {
    return new ShouldHaveLength(expected, actual, buffer);
  }

  private ShouldHaveLength(int expected, int actual, Buffer buffer) {
    super(SHOULD_HAVE_LENGTH, buffer, expected, actual);
  }
}