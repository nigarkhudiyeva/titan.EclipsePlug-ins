/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;

import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter;
import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter.basic_check_bits_t;
import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter.type_t;

/**
 * TTCN-3 default
 *
 * @author Kristof Szabados
 *
 */
public class TitanDefault extends Base_Type {
	/** internal object to indicate unbound state. */
	static final Default_Base UNBOUND_DEFAULT = new Default_Base() {
		@Override
		public TitanAlt_Status call_altstep() {
			return TitanAlt_Status.ALT_NO;
		}
	};

	Default_Base default_ptr;

	/**
	 * Initializes to unbound value.
	 * */
	public TitanDefault() {
		default_ptr = UNBOUND_DEFAULT;
	}

	/**
	 * Initializes to a given value.
	 *
	 * in the core has component parameter
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanDefault(final int otherValue) {
		if (otherValue != TitanComponent.NULL_COMPREF) {
			throw new TtcnError("Initialization from an invalid default reference.");
		}

		default_ptr = null;
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanDefault(final Default_Base otherValue) {
		default_ptr = otherValue;
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanDefault(final TitanDefault otherValue) {
		if (otherValue.default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("Copying an unbound default reference.");
		}

		default_ptr = otherValue.default_ptr;
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanDefault assign(final int otherValue) {
		if (otherValue != TitanComponent.NULL_COMPREF) {
			throw new TtcnError("Assignment of an invalid default reference.");
		}

		default_ptr = null;
		return this;
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanDefault assign(final Default_Base otherValue) {
		if (otherValue == UNBOUND_DEFAULT) {
			throw new TtcnError("Assignment of an unbound default reference.");
		}

		default_ptr = otherValue;
		return this;
	}

	/**
	 * Assigns the other value to this value.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new value object.
	 */
	public TitanDefault assign(final TitanDefault otherValue) {
		if (otherValue.default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("Assignment of an unbound default reference.");
		}

		if (otherValue != this) {
			default_ptr = otherValue.default_ptr;
		}

		return this;
	}

	@Override
	public Base_Type assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanDefault) {
			return assign((TitanDefault)otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to default", otherValue));
	}

	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core with component parameter
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are equivalent.
	 */
	public boolean operatorEquals(final int otherValue) {
		if (default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The left operand of comparison is an unbound default reference.");
		}
		if (otherValue != TitanComponent.NULL_COMPREF) {
			throw new TtcnError("Comparison of an invalid default value.");
		}

		return default_ptr == null;
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
	public boolean operatorEquals(final Default_Base otherValue) {
		if (default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The left operand of comparison is an unbound default reference.");
		}

		return default_ptr == otherValue;
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
	public boolean operatorEquals(final TitanDefault otherValue) {
		if (default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The left operand of comparison is an unbound default reference.");
		}
		if (otherValue.default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The right operand of comparison is an unbound default reference.");
		}

		return default_ptr == otherValue.default_ptr;
	}

	@Override
	public boolean operatorEquals(final Base_Type otherValue) {
		if (otherValue instanceof TitanDefault) {
			return operatorEquals((TitanDefault)otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to default", otherValue));

	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core with component parameter
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operatorNotEquals(final int otherValue) {
		return !operatorEquals(otherValue);
	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operatorNotEquals(final Default_Base otherValue) {
		return !operatorEquals(otherValue);
	}

	/**
	 * Checks if the current value is not equivalent to the provided one.
	 *
	 * operator!= in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if the values are not equivalent.
	 */
	public boolean operatorNotEquals(final TitanDefault otherValue) {
		return !operatorEquals(otherValue);
	}

	//originally operator Default_Base*
	public Default_Base getDefaultBase() {
		if (default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("Using the value of an unbound default reference.");
		}

		return default_ptr;
	}

	@Override
	public boolean isPresent() {
		return isBound();
	}

	@Override
	public boolean isBound() {
		return default_ptr != UNBOUND_DEFAULT;
	}

	@Override
	public boolean isValue() {
		return default_ptr != UNBOUND_DEFAULT;
	}

	@Override
	public void cleanUp() {
		default_ptr = UNBOUND_DEFAULT;
	}

	@Override
	public void log() {
		TTCN_Default.log(default_ptr);
	}

	@Override
	public void set_param(final Module_Parameter param) {
		param.basic_check(basic_check_bits_t.BC_VALUE.getValue(), "default reference (null) value");
		if (param.get_type() != type_t.MP_Ttcn_Null) {
			param.type_error("default reference (null) value");
		}
		default_ptr = null;
	}

	@Override
	/** {@inheritDoc} */
	public void encode_text(final Text_Buf text_buf) {
		throw new TtcnError("Default references cannot be sent to other test components.");
	}

	@Override
	/** {@inheritDoc} */
	public void decode_text(final Text_Buf text_buf) {
		throw new TtcnError("Default references cannot be received from other test components.");
	}

	//originally static operator== with component parameter
	public static boolean operatorEquals(final int defaultValue, final TitanDefault otherValue) {
		if (defaultValue != TitanComponent.NULL_COMPREF) {
			throw new TtcnError("The left operand of comparison is an invalid default reference.");
		}
		if (otherValue.default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The right operand of comparison is an unbound default reference.");
		}

		return otherValue.default_ptr == null;
	}

	//originally static operator== with component parameter
	public static boolean operatorEquals(final Default_Base defaultValue, final TitanDefault otherValue) {
		if (otherValue.default_ptr == UNBOUND_DEFAULT) {
			throw new TtcnError("The right operand of comparison is an unbound default reference.");
		}

		return defaultValue == otherValue.default_ptr;
	}

	//originally static operator!= with component parameter
	public static boolean operatorNotEquals(final int defaultValue, final TitanDefault otherValue) {
		return !operatorEquals(defaultValue, otherValue);
	}

	//originally static operator!= with component parameter
	public static boolean operatorNotEquals(final Default_Base defaultValue, final TitanDefault otherValue) {
		return !operatorEquals(defaultValue, otherValue);
	}
}
