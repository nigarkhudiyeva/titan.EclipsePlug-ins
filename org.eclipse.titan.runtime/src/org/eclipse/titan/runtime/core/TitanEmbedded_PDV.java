/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;

import org.eclipse.titan.runtime.core.Base_Template.template_sel;
import org.eclipse.titan.runtime.core.Optional.optional_sel;
import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter;
import org.eclipse.titan.runtime.core.RAW.RAW_enc_tr_pos;
import org.eclipse.titan.runtime.core.RAW.RAW_enc_tree;
import org.eclipse.titan.runtime.core.TTCN_EncDec.coding_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.error_type;
import org.eclipse.titan.runtime.core.TTCN_EncDec.raw_order_t;

/**
 * Part of the representation of the ASN.1 EMBEDDED PDV type
 *
 * @author Kristof Szabados
 */
public class TitanEmbedded_PDV extends Base_Type {
	private final TitanEmbedded_PDV_identification identification; //ASN1_Choice_Type
	private final Optional<TitanUniversalCharString> data__value__descriptor; //ObjectDescriptor_Type
	private final TitanOctetString data__value; //OctetString_Type

	/**
	 * Initializes to unbound value.
	 * */
	public TitanEmbedded_PDV() {
		this.identification = new TitanEmbedded_PDV_identification();
		this.data__value__descriptor = new Optional<TitanUniversalCharString>(TitanUniversalCharString.class);
		this.data__value = new TitanOctetString();
	}

	/**
	 * Initializes from given field values. The number of arguments equals
	 * to the number of fields.
	 *
	 * @param identification
	 *                the value of field identification
	 * @param data__value__descriptor
	 *                the value of field data-value-descriptor
	 * @param data__value
	 *                the value of field data-value
	 * */
	public TitanEmbedded_PDV(final TitanEmbedded_PDV_identification identification, final Optional<TitanUniversalCharString> data__value__descriptor, final TitanOctetString data__value ) {
		this.identification = new TitanEmbedded_PDV_identification( identification );
		this.data__value__descriptor = new Optional<TitanUniversalCharString>(TitanUniversalCharString.class);
		this.data__value__descriptor.assign( data__value__descriptor );
		this.data__value = new TitanOctetString( data__value );
	}

	/**
	 * Initializes to a given value.
	 *
	 * @param otherValue
	 *                the value to initialize to.
	 * */
	public TitanEmbedded_PDV( final TitanEmbedded_PDV otherValue) {
		if(!otherValue.isBound()) {
			throw new TtcnError("Copying of an unbound value of type EMBEDDED PDV.");
		}
		identification = new TitanEmbedded_PDV_identification();
		data__value__descriptor = new Optional<TitanUniversalCharString>(TitanUniversalCharString.class);
		data__value = new TitanOctetString();
		assign( otherValue );
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
	public TitanEmbedded_PDV assign(final TitanEmbedded_PDV otherValue ) {
		if ( !otherValue.isBound() ) {
			throw new TtcnError( "Assignment of an unbound value of type EMBEDDED PDV");
		}

		if (otherValue != this) {
			if ( otherValue.getidentification().isBound() ) {
				this.identification.assign( otherValue.getidentification() );
			} else {
				this.identification.cleanUp();
			}
			if ( otherValue.getdata__value__descriptor().isBound() ) {
				this.data__value__descriptor.assign( otherValue.getdata__value__descriptor() );
			} else {
				this.data__value__descriptor.cleanUp();
			}
			if ( otherValue.getdata__value().isBound() ) {
				this.data__value.assign( otherValue.getdata__value() );
			} else {
				this.data__value.cleanUp();
			}
		}

		return this;
	}

	@Override
	public TitanEmbedded_PDV assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanEmbedded_PDV ) {
			return assign((TitanEmbedded_PDV) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to EMBEDDED PDV", otherValue));
	}

	@Override
	public void cleanUp() {
		identification.cleanUp();
		data__value__descriptor.cleanUp();
		data__value.cleanUp();
	}

	@Override
	public boolean isBound() {
		if ( identification.isBound() ) { return true; }
		if ( optional_sel.OPTIONAL_OMIT.equals(data__value__descriptor.get_selection()) || data__value__descriptor.isBound() ) { return true; }
		if ( data__value.isBound() ) { return true; }
		return false;
	}

	@Override
	public boolean isPresent() {
		return isBound();
	}

	@Override
	public boolean isValue() {
		if ( !identification.isValue() ) { return false; }
		if ( !optional_sel.OPTIONAL_OMIT.equals(data__value__descriptor.get_selection()) && !data__value__descriptor.isValue() ) { return false; }
		if ( !data__value.isValue() ) { return false; }
		return true;
	}


	/**
	 * Checks if the current value is equivalent to the provided one.
	 *
	 * operator== in the core
	 *
	 * @param otherValue
	 *                the other value to check against.
	 * @return {@code true} if all fields are equivalent, {@code false} otherwise.
	 */
	public boolean operatorEquals( final TitanEmbedded_PDV otherValue) {
		if ( !this.identification.operatorEquals( otherValue.identification ) ) { return false; }
		if ( !this.data__value__descriptor.operatorEquals( otherValue.data__value__descriptor ) ) { return false; }
		if ( !this.data__value.operatorEquals( otherValue.data__value ) ) { return false; }
		return true;
	}

	@Override
	public boolean operatorEquals(final Base_Type otherValue) {
		if (otherValue instanceof TitanEmbedded_PDV ) {
			return operatorEquals((TitanEmbedded_PDV) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `{0}'' can not be cast to EMBEDDED PDV", otherValue));
	}

	/**
	 * Gives access to the field identification.
	 *
	 * @return the field identification.
	 * */

	public TitanEmbedded_PDV_identification getidentification() {
		return identification;
	}

	/**
	 * Gives read-only access to the field identification.
	 *
	 * @return the field identification.
	 * */

	public TitanEmbedded_PDV_identification constGetidentification() {
		return identification;
	}
	/**
	 * Gives access to the field data-value-descriptor.
	 *
	 * @return the field data-value-descriptor.
	 * */

	public Optional<TitanUniversalCharString> getdata__value__descriptor() {
		return data__value__descriptor;
	}

	/**
	 * Gives read-only access to the field data-value-descriptor.
	 *
	 * @return the field data-value-descriptor.
	 * */

	public Optional<TitanUniversalCharString> constGetdata__value__descriptor() {
		return data__value__descriptor;
	}
	/**
	 * Gives access to the field data-value.
	 *
	 * @return the field data-value.
	 * */

	public TitanOctetString getdata__value() {
		return data__value;
	}

	/**
	 * Gives read-only access to the field data-value.
	 *
	 * @return the field data-value.
	 * */

	public TitanOctetString constGetdata__value() {
		return data__value;
	}
	/**
	 * Returns the size (number of fields).
	 *
	 * size_of in the core
	 *
	 * @return the size of the structure.
	 * */
	public TitanInteger sizeOf() {
		int sizeof = 2;
		if (data__value__descriptor.isPresent()) {
			sizeof++;
		}
		return new TitanInteger(sizeof);
	}

	@Override
	public void log() {
		if (!isBound()) {
			TTCN_Logger.log_event_unbound();
			return;
		}
		TTCN_Logger.log_char('{');
		TTCN_Logger.log_event_str(" identification := ");
		identification.log();
		TTCN_Logger.log_char(',');
		TTCN_Logger.log_event_str(" data-value-descriptor := ");
		data__value__descriptor.log();
		TTCN_Logger.log_char(',');
		TTCN_Logger.log_event_str(" data-value := ");
		data__value.log();
		TTCN_Logger.log_event_str(" }");
	}

	@Override
	public void set_param(final Module_Parameter param) {
		param.basic_check(Module_Parameter.basic_check_bits_t.BC_VALUE.getValue(), "set value");
		switch (param.get_type()) {
		case MP_Value_List:
			if (param.get_size() > 3) {
				param.error(MessageFormat.format("set value of type EMBEDDED PDV has 3 fields but list value has {0} fields.", param.get_size()));
			}
			if (param.get_size() > 0 && param.get_elem(0).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				getidentification().set_param(param.get_elem(0));
			}
			if (param.get_size() > 1 && param.get_elem(1).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				getdata__value__descriptor().set_param(param.get_elem(1));
			}
			if (param.get_size() > 2 && param.get_elem(2).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				getdata__value().set_param(param.get_elem(2));
			}
			break;
		case MP_Assignment_List: {
			final boolean value_used[] = new boolean[param.get_size()];
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("identification".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						getidentification().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("data-value-descriptor".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						getdata__value__descriptor().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("data-value".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						getdata__value().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				if (!value_used[val_idx]) {
					final Module_Parameter curr_param = param.get_elem(val_idx);
					curr_param.error(MessageFormat.format("Non existent field name in type EMBEDDED PDV: {0}", curr_param.get_id().get_name()));
					break;
				}
			}
			break;
		}
		default:
			param.type_error("set value", "EMBEDDED PDV");
		}
	}

	@Override
	public void set_implicit_omit() {
		if (identification.isBound()) {
			identification.set_implicit_omit();
		}
		if (data__value__descriptor.isBound()) {
			data__value__descriptor.set_implicit_omit();
		} else {
			data__value__descriptor.assign(template_sel.OMIT_VALUE);
		}
		if (data__value.isBound()) {
			data__value.set_implicit_omit();
		}
	}

	@Override
	public void encode_text(final Text_Buf text_buf) {
		identification.encode_text(text_buf);
		data__value__descriptor.encode_text(text_buf);
		data__value.encode_text(text_buf);
	}

	@Override
	public void decode_text(final Text_Buf text_buf) {
		identification.decode_text(text_buf);
		data__value__descriptor.decode_text(text_buf);
		data__value.decode_text(text_buf);
	}

	@Override
	public void encode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-encoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}
			final RAW_enc_tr_pos rp = new RAW_enc_tr_pos(0, null);
			final RAW_enc_tree root = new RAW_enc_tree(false, null, rp, 1, p_td.raw);
			RAW_encode(p_td, root);
			root.put_to_buf(p_buf);
			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to encode type `{0}''", p_td.name));
		}
	}

	@Override
	public void decode(final TTCN_Typedescriptor p_td, final TTCN_Buffer p_buf, final coding_type p_coding, final int flavour) {
		switch (p_coding) {
		case CT_RAW: {
			final TTCN_EncDec_ErrorContext errorContext = new TTCN_EncDec_ErrorContext("While RAW-decoding type '%s': ", p_td.name);
			if (p_td.raw == null) {
				TTCN_EncDec_ErrorContext.error_internal("No RAW descriptor available for type '%s'.", p_td.name);
			}
			raw_order_t order;
			switch (p_td.raw.top_bit_order) {
			case TOP_BIT_LEFT:
				order = raw_order_t.ORDER_LSB;
				break;
			case TOP_BIT_RIGHT:
			default:
				order = raw_order_t.ORDER_MSB;
				break;
			}
			final int rawr = RAW_decode(p_td, p_buf, p_buf.get_len() * 8, order);
			if (rawr < 0) {
				final error_type temp = error_type.values()[-rawr];
				switch (temp) {
				case ET_INCOMPL_MSG:
				case ET_LEN_ERR:
					TTCN_EncDec_ErrorContext.error(temp, "Can not decode type '%s', because invalid or incomplete message was received", p_td.name);
					break;
				case ET_UNBOUND:
				default:
					TTCN_EncDec_ErrorContext.error(error_type.ET_INVAL_MSG, "Can not decode type '%s', because invalid or incomplete message was received", p_td.name);
					break;
				}
			}
			errorContext.leaveContext();
			break;
		}
		default:
			throw new TtcnError(MessageFormat.format("Unknown coding method requested to decode type `{0}''", p_td.name));
		}
	}

}