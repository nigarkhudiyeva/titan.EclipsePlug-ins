/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.titan.runtime.core.Param_Types.Module_Parameter;

/**
 * Part of the representation of the ASN.1 EXTERNAL type
 *
 * @author Kristof Szabados
 */
public class TitanExternal_identification_context__negotiation_template extends Base_Template {
	private TitanInteger_template presentation__context__id; //ASN1_Integer_Type
	private TitanObjectid_template transfer__syntax; //ObjectID_Type
	//originally value_list/list_value
	private List<TitanExternal_identification_context__negotiation_template> list_value;

	/**
	 * Gives access to the field presentation-context-id.
	 * Turning the template into a specific value template if needed.
	 *
	 * @return the field presentation-context-id.
	 * */
	public TitanInteger_template getpresentation__context__id() {
		setSpecific();
		return presentation__context__id;
	}

	/**
	 * Gives read-only access to the field presentation-context-id.
	 * Being called on a non specific value template causes dynamic test case error.
	 *
	 * @return the field presentation-context-id.
	 * */
	public TitanInteger_template constGetpresentation__context__id() {
		if (templateSelection != template_sel.SPECIFIC_VALUE) {
			throw new TtcnError("Accessing field presentation-context-id of a non-specific template of type EXTERNAL.identification.context-negotiation.");
		}
		return presentation__context__id;
	}

	/**
	 * Gives access to the field transfer-syntax.
	 * Turning the template into a specific value template if needed.
	 *
	 * @return the field transfer-syntax.
	 * */
	public TitanObjectid_template gettransfer__syntax() {
		setSpecific();
		return transfer__syntax;
	}

	/**
	 * Gives read-only access to the field transfer-syntax.
	 * Being called on a non specific value template causes dynamic test case error.
	 *
	 * @return the field transfer-syntax.
	 * */
	public TitanObjectid_template constGettransfer__syntax() {
		if (templateSelection != template_sel.SPECIFIC_VALUE) {
			throw new TtcnError("Accessing field transfer-syntax of a non-specific template of type EXTERNAL.identification.context-negotiation.");
		}
		return transfer__syntax;
	}

	private void setSpecific() {
		if (templateSelection != template_sel.SPECIFIC_VALUE) {
			final template_sel old_selection = templateSelection;
			cleanUp();
			set_selection(template_sel.SPECIFIC_VALUE);
			presentation__context__id = new TitanInteger_template();
			transfer__syntax = new TitanObjectid_template();
			if (old_selection == template_sel.ANY_VALUE || old_selection == template_sel.ANY_OR_OMIT) {
				presentation__context__id.assign(template_sel.ANY_VALUE);
				transfer__syntax.assign(template_sel.ANY_VALUE);
			}
		}
	}

	public TitanExternal_identification_context__negotiation_template() {
		// do nothing
	}

	public TitanExternal_identification_context__negotiation_template(final template_sel other_value ) {
		super( other_value );
		checkSingleSelection( other_value );
	}

	public TitanExternal_identification_context__negotiation_template( final TitanExternal_identification_context__negotiation otherValue ) {
		copyValue(otherValue);
	}

	public TitanExternal_identification_context__negotiation_template( final TitanExternal_identification_context__negotiation_template otherValue ) {
		copyTemplate( otherValue );
	}

	public TitanExternal_identification_context__negotiation_template( final Optional<TitanExternal_identification_context__negotiation> other_value ) {
		switch (other_value.get_selection()) {
		case OPTIONAL_PRESENT:
			copyValue(other_value.constGet());
			break;
		case OPTIONAL_OMIT:
			set_selection(template_sel.OMIT_VALUE);
			break;
		default:
			throw new TtcnError("Creating a template of type EXTERNAL.identification.context-negotiation from an unbound optional field.");
		}
	}

	@Override
	public TitanExternal_identification_context__negotiation_template assign( final template_sel otherValue ) {
		checkSingleSelection(otherValue);
		cleanUp();
		set_selection(otherValue);
		return this;
	}

	/**
	 * Assigns the other value to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanExternal_identification_context__negotiation_template assign( final TitanExternal_identification_context__negotiation otherValue ) {
		cleanUp();
		copyValue(otherValue);
		return this;
	}

	/**
	 * Assigns the other template to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanExternal_identification_context__negotiation_template assign( final TitanExternal_identification_context__negotiation_template otherValue ) {
		if (otherValue != this) {
			cleanUp();
			copyTemplate(otherValue);
		}
		return this;
	}

	@Override
	public TitanExternal_identification_context__negotiation_template assign(final Base_Type otherValue) {
		if (otherValue instanceof TitanExternal_identification_context__negotiation) {
			return assign((TitanExternal_identification_context__negotiation) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `TitanExternal_identification_context__negotiation' can not be cast to {1}", otherValue));
	}

	@Override
	public TitanExternal_identification_context__negotiation_template assign(final Base_Template otherValue) {
		if (otherValue instanceof TitanExternal_identification_context__negotiation_template) {
			return assign((TitanExternal_identification_context__negotiation_template) otherValue);
		}

		throw new TtcnError(MessageFormat.format("Internal Error: value `TitanExternal_identification_context__negotiation' can not be cast to {1}_template", otherValue));
	}

	/**
	 * Assigns the other value to this template.
	 * Overwriting the current content in the process.
	 *<p>
	 * operator= in the core.
	 *
	 * @param otherValue
	 *                the other value to assign.
	 * @return the new template object.
	 */
	public TitanExternal_identification_context__negotiation_template assign( final Optional<TitanExternal_identification_context__negotiation> otherValue ) {
		cleanUp();
		switch (otherValue.get_selection()) {
		case OPTIONAL_PRESENT:
			copyValue(otherValue.constGet());
			break;
		case OPTIONAL_OMIT:
			set_selection(template_sel.OMIT_VALUE);
			break;
		default:
			throw new TtcnError("Assignment of an unbound optional field to a template of type EXTERNAL.identification.context-negotiation.");
		}
		return this;
	}

	private void copyValue(final TitanExternal_identification_context__negotiation other_value) {
		if (other_value.getpresentation__context__id().isBound()) {
			getpresentation__context__id().assign(other_value.getpresentation__context__id());
		} else {
			getpresentation__context__id().cleanUp();
		}
		if (other_value.gettransfer__syntax().isBound()) {
			gettransfer__syntax().assign(other_value.gettransfer__syntax());
		} else {
			gettransfer__syntax().cleanUp();
		}
		set_selection(template_sel.SPECIFIC_VALUE);
	}

	private void copyTemplate(final TitanExternal_identification_context__negotiation_template other_value) {
		switch (other_value.templateSelection) {
		case SPECIFIC_VALUE:
			if (template_sel.UNINITIALIZED_TEMPLATE == other_value.getpresentation__context__id().get_selection()) {
				getpresentation__context__id().cleanUp();
			} else {
				getpresentation__context__id().assign(other_value.getpresentation__context__id());
			}
			if (template_sel.UNINITIALIZED_TEMPLATE == other_value.gettransfer__syntax().get_selection()) {
				gettransfer__syntax().cleanUp();
			} else {
				gettransfer__syntax().assign(other_value.gettransfer__syntax());
			}
			break;
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			list_value = new ArrayList<TitanExternal_identification_context__negotiation_template>(other_value.list_value.size());
			for(int i = 0; i < other_value.list_value.size(); i++) {
				final TitanExternal_identification_context__negotiation_template temp = new TitanExternal_identification_context__negotiation_template(other_value.list_value.get(i));
				list_value.add(temp);
			}
			break;
		default:
			throw new TtcnError("Copying an uninitialized template of type EXTERNAL.identification.context-negotiation.");
		}
		set_selection(other_value);
	}

	public boolean isPresent(final boolean legacy) {
		return isPresent_(legacy);
	}

	private boolean isPresent_(final boolean legacy) {
		if (templateSelection==template_sel.UNINITIALIZED_TEMPLATE) {
			return false;
		}
		return !match_omit_(legacy);
	}

	public boolean match_omit(final boolean legacy) {
		return match_omit_(legacy);
	}

	private boolean match_omit_(final boolean legacy) {
		if (is_ifPresent) {
			return true;
		}
		switch (templateSelection) {
		case OMIT_VALUE:
		case ANY_OR_OMIT:
			return true;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			if (legacy) {
				for (int l_idx=0; l_idx<list_value.size(); l_idx++) {
					if (list_value.get(l_idx).match_omit_(legacy)) {
						return templateSelection==template_sel.VALUE_LIST;
					}
				}
				return templateSelection==template_sel.COMPLEMENTED_LIST;
			} // else fall through
		default:
			return false;
		}
	}

	public TitanExternal_identification_context__negotiation valueOf() {
		if (templateSelection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
			throw new TtcnError("Performing a valueof or send operation on a non-specific template of type EXTERNAL.identification.context-negotiation.");
		}
		final TitanExternal_identification_context__negotiation ret_val = new TitanExternal_identification_context__negotiation();
		if (presentation__context__id.isBound()) {
			ret_val.getpresentation__context__id().assign(presentation__context__id.valueOf());
		}
		if (transfer__syntax.isBound()) {
			ret_val.gettransfer__syntax().assign(transfer__syntax.valueOf());
		}
		return ret_val;
	}

	public TitanExternal_identification_context__negotiation_template listItem(final int list_index) {
		if (templateSelection != template_sel.VALUE_LIST && templateSelection != template_sel.COMPLEMENTED_LIST) {
			throw new TtcnError("Accessing a list element of a non-list template of type EXTERNAL.identification.context-negotiation.");
		}
		if (list_index >= list_value.size()) {
			throw new TtcnError("Index overflow in a value list template of type EXTERNAL.identification.context-negotiation.");
		}
		return list_value.get(list_index);
	}

	public void setType(final template_sel template_type, final int list_length) {
		if (template_type != template_sel.VALUE_LIST && template_type != template_sel.COMPLEMENTED_LIST) {
			throw new TtcnError("Setting an invalid list for a template of type EXTERNAL.identification.context-negotiation.");
		}
		cleanUp();
		set_selection(template_type);
		list_value = new ArrayList<TitanExternal_identification_context__negotiation_template>(list_length);
		for(int i = 0 ; i < list_length; i++) {
			list_value.add(new TitanExternal_identification_context__negotiation_template());
		}
	}

	@Override
	public boolean isBound() {
		if (templateSelection == template_sel.UNINITIALIZED_TEMPLATE && !is_ifPresent) {
			return false;
		}
		if (templateSelection != template_sel.SPECIFIC_VALUE) {
			return true;
		}
		if (presentation__context__id.isBound()) {
			return true;
		}
		if (transfer__syntax.isBound()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isValue() {
		if (templateSelection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
			return false;
		}
		if (!presentation__context__id.isValue()) {
			return false;
		}
		if (!transfer__syntax.isValue()) {
			return false;
		}
		return true;
	}

	public boolean match(final TitanExternal_identification_context__negotiation other_value) {
		return match(other_value, false);
	}

	public boolean match(final TitanExternal_identification_context__negotiation other_value, final boolean legacy) {
		if (!other_value.isBound()) {
			return false;
		}
		switch (templateSelection) {
		case ANY_VALUE:
		case ANY_OR_OMIT:
			return true;
		case OMIT_VALUE:
			return false;
		case SPECIFIC_VALUE:
			if(!other_value.getpresentation__context__id().isBound()) {
				return false;
			}
			if(!presentation__context__id.match(other_value.getpresentation__context__id(), legacy)) {
				return false;
			}
			if(!other_value.gettransfer__syntax().isBound()) {
				return false;
			}
			if(!transfer__syntax.match(other_value.gettransfer__syntax(), legacy)) {
				return false;
			}
			return true;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			for (int list_count = 0; list_count < list_value.size(); list_count++) {
				if (list_value.get(list_count).match(other_value, legacy)) {
					return templateSelection == template_sel.VALUE_LIST;
				}
			}
			return templateSelection == template_sel.COMPLEMENTED_LIST;
		default:
			throw new TtcnError("Matching an uninitialized/unsupported template of type EXTERNAL.identification.context-negotiation.");
		}
	}

	@Override
	public boolean match(final Base_Type otherValue, final boolean legacy) {
		if (otherValue instanceof TitanExternal_identification_context__negotiation) {
			return match((TitanExternal_identification_context__negotiation)otherValue, legacy);
		}

		throw new TtcnError("Internal Error: The left operand of assignment is not of type TitanExternal_identification_context__negotiation.");
	}

	/**
	 * Returns the size (number of fields).
	 *
	 * size_of in the core
	 *
	 * @return the size of the structure.
	 * */
	public TitanInteger sizeOf() {
		if (is_ifPresent) {
			throw new TtcnError("Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation which has an ifpresent attribute.");
		}
		switch (templateSelection) {
		case SPECIFIC_VALUE:
			return new TitanInteger(2);
		case VALUE_LIST:
			if (list_value.isEmpty()) {
				throw new TtcnError("Internal error: Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation containing an empty list.");
			}
			final int item_size = list_value.get(0).sizeOf().getInt();
			for (int l_idx = 1; l_idx < list_value.size(); l_idx++) {
				if (list_value.get(l_idx).sizeOf().getInt() != item_size) {
					throw new TtcnError("Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation containing a value list with different sizes.");
				}
			}
			return new TitanInteger(item_size);
		case OMIT_VALUE:
			throw new TtcnError("Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation containing omit value.");
		case ANY_VALUE:
		case ANY_OR_OMIT:
			throw new TtcnError("Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation containing */? value.");
		case COMPLEMENTED_LIST:
			throw new TtcnError("Performing sizeof() operation on a template of type EXTERNAL.identification.context-negotiation containing complemented list.");
		default:
			throw new TtcnError("Performing sizeof() operation on an uninitialized/unsupported template of type EXTERNAL.identification.context-negotiation.");
		}
	}

	@Override
	public void log() {
		switch (templateSelection) {
		case SPECIFIC_VALUE:
			TTCN_Logger.log_char('{');
			TTCN_Logger.log_event_str(" presentation-context-id := ");
			presentation__context__id.log();
			TTCN_Logger.log_char(',');
			TTCN_Logger.log_event_str(" transfer-syntax := ");
			transfer__syntax.log();
			TTCN_Logger.log_event_str(" }");
			break;
		case COMPLEMENTED_LIST:
			TTCN_Logger.log_event_str("complement");
		case VALUE_LIST:
			TTCN_Logger.log_char('(');
			for (int list_count = 0; list_count < list_value.size(); list_count++) {
				if (list_count > 0) {
					TTCN_Logger.log_event_str(", ");
				}
				list_value.get(list_count).log();
			}
			TTCN_Logger.log_char(')');
			break;
		default:
			log_generic();
			break;
		}
		log_ifpresent();
	}

	public void log_match(final TitanExternal_identification_context__negotiation match_value) {
		log_match(match_value, false);
	}

	@Override
	public void log_match(final Base_Type match_value, final boolean legacy) {
		if (match_value instanceof TitanExternal_identification_context__negotiation) {
			log_match((TitanExternal_identification_context__negotiation)match_value, legacy);
			return;
		}

		throw new TtcnError("Internal Error: value can not be cast to EXTERNAL.identification.context-negotiation.");
	}

	public void log_match(final TitanExternal_identification_context__negotiation match_value, final boolean legacy) {
		if ( TTCN_Logger.matching_verbosity_t.VERBOSITY_COMPACT == TTCN_Logger.get_matching_verbosity() ) {
			if(match(match_value, legacy)) {
				TTCN_Logger.print_logmatch_buffer();
				TTCN_Logger.log_event_str(" matched");
			} else {
				if (templateSelection == template_sel.SPECIFIC_VALUE) {
					final int previous_size = TTCN_Logger.get_logmatch_buffer_len();
					if( !presentation__context__id.match(match_value.constGetpresentation__context__id(), legacy) ) {
						TTCN_Logger.log_logmatch_info(".presentation-context-id");
						presentation__context__id.log_match(match_value.constGetpresentation__context__id(), legacy);
						TTCN_Logger.set_logmatch_buffer_len(previous_size);
					}
					if( !transfer__syntax.match(match_value.constGettransfer__syntax(), legacy) ) {
						TTCN_Logger.log_logmatch_info(".transfer-syntax");
						transfer__syntax.log_match(match_value.constGettransfer__syntax(), legacy);
						TTCN_Logger.set_logmatch_buffer_len(previous_size);
					}
				} else {
					TTCN_Logger.print_logmatch_buffer();
					match_value.log();
					TTCN_Logger.log_event_str(" with ");
					log();
					TTCN_Logger.log_event_str(" unmatched");
				}
			}
			return;
		}
		if (templateSelection == template_sel.SPECIFIC_VALUE) {
			TTCN_Logger.log_event_str("{ presentation-context-id := ");
			presentation__context__id.log_match(match_value.constGetpresentation__context__id(), legacy);
			TTCN_Logger.log_event_str("{ transfer-syntax := ");
			transfer__syntax.log_match(match_value.constGettransfer__syntax(), legacy);
			TTCN_Logger.log_event_str(" }");
		} else {
			match_value.log();
			TTCN_Logger.log_event_str(" with ");
			log();
			if ( match(match_value, legacy) ) {
				TTCN_Logger.log_event_str(" matched");
			} else {
				TTCN_Logger.log_event_str(" unmatched");
			}
		}
	}

	@Override
	public void encode_text(final Text_Buf text_buf) {
		encode_text_base(text_buf);
		switch (templateSelection) {
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case SPECIFIC_VALUE:
			presentation__context__id.encode_text(text_buf);
			transfer__syntax.encode_text(text_buf);
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST:
			text_buf.push_int(list_value.size());
			for (int i = 0; i < list_value.size(); i++) {
				list_value.get(i).encode_text(text_buf);
			}
			break;
		default:
			throw new TtcnError("Text encoder: Encoding an uninitialized/unsupported template of type EXTERNAL.identification.context-negotiation.");
		}
	}

	@Override
	public void decode_text(final Text_Buf text_buf) {
		cleanUp();
		decode_text_base(text_buf);
		switch (templateSelection) {
		case OMIT_VALUE:
		case ANY_VALUE:
		case ANY_OR_OMIT:
			break;
		case SPECIFIC_VALUE:
			presentation__context__id = new TitanInteger_template();
			presentation__context__id.decode_text(text_buf);
			transfer__syntax = new TitanObjectid_template();
			transfer__syntax.decode_text(text_buf);
			break;
		case VALUE_LIST:
		case COMPLEMENTED_LIST: {
			final int size = text_buf.pull_int().getInt();
			list_value = new ArrayList<TitanExternal_identification_context__negotiation_template>(size);
			for (int i = 0; i < size; i++) {
				final TitanExternal_identification_context__negotiation_template temp = new TitanExternal_identification_context__negotiation_template();
				temp.decode_text(text_buf);
				list_value.add(temp);
			}
			break;
		}
		default:
			throw new TtcnError("Text decoder: An unknown/unsupported selection was received in a template of type EXTERNAL.identification.context-negotiation.");
		}
	}

	@Override
	public void set_param(final Module_Parameter param) {
		param.basic_check(Module_Parameter.basic_check_bits_t.BC_TEMPLATE.getValue(), "record template");
		switch (param.get_type()) {
		case MP_Omit:
			assign(template_sel.OMIT_VALUE);
			break;
		case MP_Any:
			assign(template_sel.ANY_VALUE);
			break;
		case MP_AnyOrNone:
			assign(template_sel.ANY_OR_OMIT);
			break;
		case MP_List_Template:
		case MP_ComplementList_Template: {
			final int size = param.get_size();
			setType(param.get_type() == Module_Parameter.type_t.MP_List_Template ? template_sel.VALUE_LIST : template_sel.COMPLEMENTED_LIST, size);
			for (int i = 0; i < size; i++) {
				listItem(i).set_param(param.get_elem(i));
			}
			break;
		}
		case MP_Value_List:
			if (param.get_size() > 2) {
				param.error(MessageFormat.format("record template of type EXTERNAL.identification.context-negotiation has 2 fields but list value has {0} fields.", param.get_size()));
			}
			if (param.get_size() > 0 && param.get_elem(0).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				getpresentation__context__id().set_param(param.get_elem(0));
			}
			if (param.get_size() > 1 && param.get_elem(1).get_type() != Module_Parameter.type_t.MP_NotUsed) {
				gettransfer__syntax().set_param(param.get_elem(1));
			}
			break;
		case MP_Assignment_List: {
			final boolean value_used[] = new boolean[param.get_size()];
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("presentation-context-id".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						getpresentation__context__id().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				final Module_Parameter curr_param = param.get_elem(val_idx);
				if ("transfer-syntax".equals(curr_param.get_id().get_name())) {
					if (curr_param.get_type() != Module_Parameter.type_t.MP_NotUsed) {
						gettransfer__syntax().set_param(curr_param);
					}
					value_used[val_idx] = true;
				}
			}
			for (int val_idx = 0; val_idx < param.get_size(); val_idx++) {
				if (!value_used[val_idx]) {
					final Module_Parameter curr_param = param.get_elem(val_idx);
					curr_param.error(MessageFormat.format("Non existent field name in type EXTERNAL.identification.context-negotiation: {0}", curr_param.get_id().get_name()));
					break;
				}
			}
			break;
		}
		default:
			param.type_error("record template", "EXTERNAL.identification.context-negotiation");
		}
		is_ifPresent = param.get_ifpresent();
	}

	@Override
	public void check_restriction(final template_res restriction, final String name, final boolean legacy) {
		if (templateSelection == template_sel.UNINITIALIZED_TEMPLATE) {
			return;
		}
		switch ((name != null && restriction == template_res.TR_VALUE) ? template_res.TR_OMIT : restriction) {
		case TR_OMIT:
			if (templateSelection == template_sel.OMIT_VALUE) {
				return;
			}
		case TR_VALUE:
			if (templateSelection != template_sel.SPECIFIC_VALUE || is_ifPresent) {
				break;
			}
			this.presentation__context__id.check_restriction(restriction, name == null ? "EXTERNAL.identification.context-negotiation" : name, legacy);
			this.transfer__syntax.check_restriction(restriction, name == null ? "EXTERNAL.identification.context-negotiation" : name, legacy);
			return;
		case TR_PRESENT:
			if (!match_omit(legacy)) {
				return;
			}
			break;
		default:
			return;
		}
		throw new TtcnError(MessageFormat.format("Restriction `{0}'' on template of type {1} violated.", getResName(restriction), name == null ? "EXTERNAL.identification.context-negotiation" : name));
	}
}