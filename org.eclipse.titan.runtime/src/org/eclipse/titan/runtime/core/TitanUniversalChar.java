/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

/**
 * originally universal_char
 * Represents UTF-32 character
 *
 * @author Arpad Lovassy
 */
public class TitanUniversalChar {
	private char uc_group;
	private char uc_plane;
	private char uc_row;
	private char uc_cell;

	public TitanUniversalChar(final char uc_group, final char uc_plane, final char uc_row, final char uc_cell) {
		this.uc_group = uc_group;
		this.uc_plane = uc_plane;
		this.uc_row = uc_row;
		this.uc_cell = uc_cell;
	}

	public TitanUniversalChar(final TitanUniversalChar uc) {
		this.uc_group = uc.uc_group;
		this.uc_plane = uc.uc_plane;
		this.uc_row = uc.uc_row;
		this.uc_cell = uc.uc_cell;
	}

	public TitanUniversalChar() {

	}

	public boolean is_char() {
		return getUc_group() == 0 && getUc_plane() == 0 && getUc_row() == 0 && getUc_cell() < 128;
	}

	//originally boolean operator==(const universal_char& left_value, const universal_char& right_value)
	public static boolean operatorEquals(final TitanUniversalChar left_value, final TitanUniversalChar right_value) {
		return left_value.getUc_group() == right_value.getUc_group() &&
				left_value.getUc_plane() == right_value.getUc_plane() &&
				left_value.getUc_row() == right_value.getUc_row() &&
				left_value.getUc_cell() == right_value.getUc_cell();
	}

	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are equivalent.
	 */
	public boolean operatorEquals(final TitanUniversalChar right_value) {
		return operatorEquals(this, right_value);
	}

	// originally inline boolean operator!=(const universal_char& uchar_value, const universal_char& other_value)
	public static boolean operatorNotEquals(final TitanUniversalChar left_value, final TitanUniversalChar right_value) {
		return !operatorEquals(left_value, right_value);
	}

	//originally boolean operator<(const universal_char& left_value, const universal_char& right_value)
	public static TitanBoolean lessThan(final TitanUniversalChar left_value, final TitanUniversalChar right_value) {
		if (left_value.getUc_group() < right_value.getUc_group()) {
			return new TitanBoolean(true);
		} else if (left_value.getUc_group() == right_value.getUc_group()) {
			if (left_value.getUc_plane() < right_value.getUc_plane()) {
				return new TitanBoolean(true);
			} else if (left_value.getUc_plane() == right_value.getUc_plane()) {
				if (left_value.getUc_row() < right_value.getUc_row()) {
					return new TitanBoolean(true);
				} else if (left_value.getUc_row() == right_value.getUc_row()) {
					if (left_value.getUc_cell() < right_value.getUc_cell()) {
						return new TitanBoolean(true);
					} else {
						return new TitanBoolean(false);
					}
				} else {
					return new TitanBoolean(false);
				}
			} else {
				return new TitanBoolean(false);
			}
		} else {
			return new TitanBoolean(false);
		}
	}

	public TitanBoolean lessThan(final TitanUniversalChar right_value) {
		return lessThan(this, right_value);
	}

	public char getUc_group() {
		return uc_group;
	}

	public char getUc_plane() {
		return uc_plane;
	}

	public char getUc_row() {
		return uc_row;
	}

	public char getUc_cell() {
		return uc_cell;
	}

	/** 
	 * Do not use this function!<br>
	 * It is provided by Java and currently used for debugging.
	 * But it is not part of the intentionally provided interface,
	 *   and so can be changed without notice. 
	 * <p>
	 * JAVA DESCRIPTION:
	 * <p>
	 * {@inheritDoc}
	 *  */
	@Override
	public String toString() {
		return new StringBuilder("(").append((int) uc_group).append(",").append((int) uc_plane)
				.append(",").append((int) uc_row).append(",").append((int) uc_cell).append(")").toString();
	}

	/**
	 * @return decoded quadruple as unicode string
	 */
	public String toUtf() {
		byte[] arr = new byte[4];
		arr[0] = (byte)uc_group;
		arr[1] = (byte)uc_plane;
		arr[2] = (byte)uc_row;
		arr[3] = (byte)uc_cell;
		try {
			return new String(arr, "UTF-32");
		} catch (UnsupportedEncodingException e) {
			throw new TtcnError(MessageFormat.format("Cannot decode quadruple: {0}, {1}, {2}, {3}", uc_group, uc_plane, uc_row, uc_cell));
		}
	}
}
