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
 * @author Farkas Izabella Ingrid
 */
public class TitanValueArray<T extends Base_Type> extends Base_Type {

	Base_Type[] array_elements;

	public Class<T> clazz;

	int array_size;
	int indexOffset;

	// only package visible
/*	TitanValueArray(final Class<T> clazz) {
		this.clazz = clazz;
		array_elements = new ArrayList<T>();
	}*/

	public TitanValueArray(final TitanValueArray<T> otherValue) {
		clazz = otherValue.clazz;
		array_size = otherValue.array_size;
		indexOffset = otherValue.indexOffset;
		array_elements = new Base_Type[array_size];

		for (int i = 0; i < array_size; ++i) {
			try {
				final T helper = clazz.newInstance();
				helper.assign(otherValue.array_elements[i]);
				array_elements[i] = helper;
			} catch (InstantiationException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			} catch (IllegalAccessException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			}
		}
	}

	public TitanValueArray(final Class<T> clazz, final int size, final int offset) {
		this.clazz = clazz;
		indexOffset = offset;

		array_size = size;
		array_elements = new Base_Type[size];
		for (int i = 0; i < size; ++i) {
			try {
				final T emply = clazz.newInstance();
				array_elements[i] = emply;
			} catch (InstantiationException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			} catch (IllegalAccessException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			}
		}
	}

	//FIXME: implement
/*	public void setSize(final int length) {
		for (int i = array_size; i < length; ++i) {
			try {
				final T emply = clazz.newInstance();
				array_elements[i] = emply;
			} catch (InstantiationException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			} catch (IllegalAccessException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			}
		}
		array_size = length;
	}*/

	public int getOffset() {
		return indexOffset;
	}

	public void setOffset(final int offset) {
		indexOffset = offset;
	}

	@Override
	public void set_implicit_omit() {
		for (int i = 0; i < array_size; ++i) {
			array_elements[i].set_implicit_omit();
		}
	}

	@Override
	public boolean isPresent() {
		return isBound();
	}

	@Override
	public boolean isBound() {
		for (int i = 0; i < array_size; ++i) {
			if (array_elements[i].isBound()) {
				return true;
			}
		}

		return false;
	}

	//FIXME: originally array_elements.get(i).clean_up()
	@Override
	public void cleanUp() {
		//array_elements.clear();
		array_elements = null;
	}

	@Override
	public boolean isValue() {
		for (int i = 0; i < array_size; ++i) {
			if (!array_elements[i].isValue()) {
				return false;
			}
		}

		return true;
	}

	public TitanInteger lengthOf() {
		for (int i = array_size - 1; i >= 0; --i) {
			if (array_elements[i].isBound()) {
				return new TitanInteger(i + 1);
			}
		}

		return new TitanInteger(0);
	}

	// TODO: void set_param(Module_Param& param);

	// originally not implemented operator=
	@SuppressWarnings("unchecked")
	@Override
	public TitanValueArray<T> assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanValueArray<?>) {
			final TitanValueArray<T> arrayOther = (TitanValueArray<T>)otherValue;
			return assign(arrayOther);
		} else {
			try {
				array_size = 1;
				array_elements = new Base_Type[1];
				final T value = clazz.newInstance();
				value.assign(otherValue);
				array_elements[0] = value;
			} catch (InstantiationException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			} catch (IllegalAccessException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			}

			return this;
		}
	}

	public TitanValueArray<T> assign(final TitanValueArray<T> otherValue) {
		cleanUp();
		array_size = otherValue.array_size;
		indexOffset = otherValue.indexOffset;
		array_elements = new Base_Type[array_size];

		for (int i = 0; i < otherValue.array_size; ++i) {
			try {
				final T helper = clazz.newInstance();
				helper.assign(otherValue.array_element(i));
				array_elements[i] = helper;
			} catch (InstantiationException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			} catch (IllegalAccessException e) {
				throw new TtcnError(MessageFormat.format("Internal error: class `{0}'' could not be instantiated ({1}).", clazz, e));
			}
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean operatorEquals(final Base_Type otherValue) {
		if (otherValue instanceof TitanValueArray<?>) {
			final TitanValueArray<T> arrayOther = (TitanValueArray<T>)otherValue;
			return operatorEquals(arrayOther);
		} else {
			if (array_size == 1) {
				return array_elements[0].operatorEquals(otherValue);
			}
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to array value", otherValue));
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
	public boolean operatorEquals(final TitanValueArray<T> otherValue) {
		if (array_size != otherValue.array_size) {
			return false;
		}

		for (int i = 0; i < array_size; ++i) {
			if (! array_elements[i].operatorEquals(otherValue.array_elements[i])) {
				return false;
			}
		}

		return true;
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
	public boolean operatorNotEquals(final Base_Type otherValue) {
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
	public boolean operatorNotEquals(final TitanValueArray<T> otherValue) {
		return !operatorEquals(otherValue);
	}

	// originally  operator<<=
	public TitanValueArray<T> rotateLeft(int rotateCount) {
		//new TitanValueArray<T>((TitanValueArray<T>).getClass());
		if (array_size == 0) {
			return this;
		}
		if (rotateCount >= 0) {
			rotateCount = rotateCount % array_size;
			if (rotateCount == 0) {
				return this;
			}

			final TitanValueArray<T> result = new TitanValueArray<T>(clazz, array_size, indexOffset);
//			result.array_size = array_size;
//			result.indexOffset = indexOffset;
			if (rotateCount > array_size) {
				rotateCount = array_size;
			}
			for (int i = 0; i < array_size - rotateCount; i++) {
				result.array_elements[i] = array_elements[i + rotateCount];
			}
			for (int i = array_size - rotateCount; i < array_size; i++) {
				result.array_elements[i] = array_elements[i + rotateCount - array_size];
			}
			return result;
		} else {
			return rotateLeft(-rotateCount);
		}
	}

	//originally  operator<<=
	public TitanValueArray<T> rotateLeft(final TitanInteger rotateCount) {
		rotateCount.mustBound("Unbound integer operand of rotate left operator.");

		return rotateLeft(rotateCount.getInt());
	}

	//originally  operator>>=
	public TitanValueArray<T> rotateRight(int rotateCount) {
		if (array_size == 0) {
			return this;
		}
		if (rotateCount >= 0) {
			rotateCount = rotateCount % array_size;
			if (rotateCount == 0) {
				return this;
			}

			final TitanValueArray<T> result = new TitanValueArray<T>(clazz, array_size, indexOffset);
//			result.array_size = array_size;
//			result.indexOffset = indexOffset;
			if (rotateCount > array_size) {
				rotateCount = array_size;
			}
			for (int i = 0; i < rotateCount; i++) {
				result.array_elements[i] = array_elements[i - rotateCount + array_size];
			}
			for (int i = rotateCount; i < array_size; i++) {
				result.array_elements[i] = array_elements[i - rotateCount];
			}
			return result;
		} else {
			return rotateLeft(-rotateCount);
		}
	}

	//originally  operator>>=
	public TitanValueArray<T> rotateRight(final TitanInteger rotateCount) {
		rotateCount.mustBound("Unbound integer operand of rotate right operator.");

		return rotateRight(rotateCount.getInt());
	}

	// originally T& operator[](int)
	@SuppressWarnings("unchecked")
	public T getAt(final int index) {
		return (T)array_elements[getArrayIndex(index, array_size, indexOffset)];
	}

	//originally T& operator[](const INTEGER)
	@SuppressWarnings("unchecked")
	public T getAt(final TitanInteger index) {
		return (T)array_elements[getArrayIndex(index, array_size, indexOffset)];
	}
	//const originally T& operator[](int)
	@SuppressWarnings("unchecked")
	public T constGetAt(final int index) {
		return (T)array_elements[getArrayIndex(index, array_size, indexOffset)];
	}

	// const // originally T& operator[](const INTEGER)
	@SuppressWarnings("unchecked")
	public T constGetAt(final TitanInteger index) {
		return (T)array_elements[getArrayIndex(index, array_size, indexOffset)];
	}

	@SuppressWarnings("unchecked")
	public T array_element(final int index) {
		return (T)array_elements[index];
	}

	@SuppressWarnings("unchecked")
	public T array_element(final TitanInteger index) {
		if (! index.isBound()) {
			throw new TtcnError("Accessing an element of an array using an unbound index.");
		}

		return (T)array_elements[index.getInt()];
	}

	// originally n_elem()
	public int n_elem() {
		return array_size;
	}

	/**
	 * Returns the number of elements, that is, the largest used index plus
	 * one and zero for the empty value.
	 *
	 * size_of in the core
	 *
	 * @return the number of elements.
	 * */
	public TitanInteger sizeOf() {
		return new TitanInteger(array_size);
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
		final StringBuilder str = new StringBuilder("{");
		for (int i = 0; i < array_size - 1; ++i) {
			str.append(array_elements[i].toString());
			str.append(" , ");
		}
		if (array_size > 0) {
			str.append(array_elements[array_size - 1].toString());
		}
		str.append('}');
		return str.toString();
	}

	//static method

	public static int getArrayIndex(final int index, final int arraySize, final int indexofset) {
		if (arraySize < 0) {
			throw new TtcnError("Invalid array size");
		}
		if (index < indexofset) {
			throw new TtcnError("Index underflow when accessing an element of an array. " + "The index value should be between "
					+ indexofset + " and " + (indexofset + arraySize - 1) + " instead of " + index + ".");
		}

		final int result = index - indexofset;
		if (result >= arraySize) {
			throw new TtcnError("Index overflow when accessing an element of an array. " + "The index value should be between "
					+ indexofset + " and " + (indexofset + arraySize - 1) + " instead of " + index + ".");
		}

		return result;
	}

	public static int getArrayIndex(final TitanInteger index, final int arraySize, final int indexofset) {
		if (! index.isBound()) {
			throw new TtcnError("Accessing an element of an array using an unbound index.");
		}

		return getArrayIndex(index.getInt(), arraySize, indexofset);
	}

	@Override
	public void log() {
		TTCN_Logger.log_event_str("{ ");
		for (int elem_count = 0; elem_count < array_size; elem_count++) {
			if (elem_count > 0) {
				TTCN_Logger.log_event_str(", ");
			}
			array_elements[elem_count].log();
		}
		TTCN_Logger.log_event_str(" }");
	}

	@Override
	public void set_param(final Module_Parameter param) {
		param.basic_check(basic_check_bits_t.BC_VALUE.getValue(), "array value");
		switch (param.get_type()) {
		case MP_Value_List:
			if (param.get_size() != array_size) {
				param.error("The array value has incorrect number of elements: %lu was expected instead of %lu.", param.get_size(), array_size);
			}
			for (int i = 0; i < param.get_size(); i++) {
				final Module_Parameter curr = param.get_elem(i);
				if (curr.get_type() != type_t.MP_NotUsed) {
					array_elements[i].set_param(curr);
				}
			}
			break;
		case MP_Indexed_List:
			for (int i = 0; i < param.get_size(); i++) {
				final Module_Parameter curr = param.get_elem(i);
				array_elements[curr.get_id().get_index()].set_param(curr);
			}
			break;
		default:
			param.type_error("array value");
		}
	}

	@Override
	/** {@inheritDoc} */
	public void encode_text(final Text_Buf text_buf) {
		for (int i = 0; i < array_size; i++) {
			array_elements[i].encode_text(text_buf);
		}
	}

	@Override
	/** {@inheritDoc} */
	public void decode_text(final Text_Buf text_buf) {
		for (int i = 0; i < array_size; i++) {
			array_elements[i].decode_text(text_buf);
		}
	}

	@Override
	/** {@inheritDoc} */
	public void encode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final TTCN_EncDec.coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_JSON:
			//TODO: implement JSON support
			break;
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to encode type {0}", p_td.name));
		}
	}
	
	@Override
	/** {@inheritDoc} */
	public void decode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final TTCN_EncDec.coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_JSON:
			//TODO: implement JSON support
			break;
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to decode type {0}", p_td.name));
		}
	}

	public TitanAlt_Status done(final TitanVerdictType value_redirect, final Index_Redirect index_redirect) {
		if (index_redirect != null) {
			index_redirect.incrPos();
		}

		TitanAlt_Status result = TitanAlt_Status.ALT_NO;
		for (int i = 0; i < array_size; i++) {
			final TitanAlt_Status returnValue = ((TitanComponent)array_elements[i]).done(value_redirect, index_redirect);
			if (returnValue == TitanAlt_Status.ALT_YES) {
				if (index_redirect != null) {
					index_redirect.addIndex(i + indexOffset);
				}

				result = returnValue;
				break;
			} else if (returnValue == TitanAlt_Status.ALT_REPEAT ||
					(returnValue == TitanAlt_Status.ALT_MAYBE && result == TitanAlt_Status.ALT_NO)) {
				result = returnValue;
			}
		}

		if (index_redirect != null) {
			index_redirect.decrPos();
		}

		return result;
	}

	public TitanAlt_Status killed(final Index_Redirect index_redirect) {
		if (index_redirect != null) {
			index_redirect.incrPos();
		}

		TitanAlt_Status result = TitanAlt_Status.ALT_NO;
		for (int i = 0; i < array_size; i++) {
			final TitanAlt_Status returnValue = ((TitanComponent)array_elements[i]).killed(index_redirect);
			if (returnValue == TitanAlt_Status.ALT_YES) {
				if (index_redirect != null) {
					index_redirect.addIndex(i + indexOffset);
				}

				result = returnValue;
				break;
			}
		}

		if (index_redirect != null) {
			index_redirect.decrPos();
		}

		return result;
	}

	public boolean running(final Index_Redirect index_redirect) {
		if (index_redirect != null) {
			index_redirect.incrPos();
		}

		boolean returnValue = false;
		for (int i = 0; i < array_size; i++) {
			returnValue = ((TitanComponent)array_elements[i]).alive(index_redirect);
			if (returnValue) {
				if (index_redirect != null) {
					index_redirect.addIndex(i + indexOffset);
				}
				break;
			}
		}

		if (index_redirect != null) {
			index_redirect.decrPos();
		}

		return returnValue;
	}

	public boolean alive(final Index_Redirect index_redirect) {
		if (index_redirect != null) {
			index_redirect.incrPos();
		}

		boolean returnValue = false;
		for (int i = 0; i < array_size; i++) {
			returnValue = ((TitanComponent)array_elements[i]).alive(index_redirect);
			if (returnValue) {
				if (index_redirect != null) {
					index_redirect.addIndex(i + indexOffset);
				}
				break;
			}
		}

		if (index_redirect != null) {
			index_redirect.decrPos();
		}

		return returnValue;
	}
}