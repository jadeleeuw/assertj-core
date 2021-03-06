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
package org.assertj.core.util;

/**
 * @author Mariusz Smykula
 * @author Jean de Leeuw
 */
public class Hexadecimals {

  protected static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

  public static String byteToHexString(byte b) {
    int v = b & 0xFF;
    return new String(new char[] { HEX_ARRAY[v >>> 4], HEX_ARRAY[v & 0x0F] });
  }

  /**
   * Returns the byte array as a hexadecimal string.
   * It will place the given separator between all hexadecimal numbers.
   *
   * @param array the byte array to represent as a hexadecimal string.
   * @param separator the separator to use between the hexadecimal numbers.
   * @return A string consisting of hexadecimal numbers representing the given byte array.
   */
  public static String byteArrayToHexString(byte[] array, String separator) {
    StringBuilder sb = new StringBuilder();
    for (byte b : array) {
      sb.append(Hexadecimals.byteToHexString(b))
        .append(separator);
    }
    sb.deleteCharAt(sb.length() - 1); // Remove trailing separator.
    return sb.toString();
  }

  private Hexadecimals() {

  }

}
