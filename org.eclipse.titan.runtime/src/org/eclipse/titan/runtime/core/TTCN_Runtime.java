/******************************************************************************
 * Copyright (c) 2000-2017 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.eclipse.titan.runtime.core;

import java.text.MessageFormat;

import org.eclipse.titan.runtime.core.TitanVerdictType.VerdictTypeEnum;
import org.eclipse.titan.runtime.core.TtcnLogger.Severity;

/**
 * TTCN-3 runtime class
 *
 * TODO: lots to implement
 * TODO: reorganize according to .hh
 *
 * @author Kristof Szabados
 */
public final class TTCN_Runtime {
	public enum executorStateEnum {
		UNDEFINED_STATE,

		SINGLE_CONTROLPART, SINGLE_TESTCASE,

		HC_INITIAL, HC_IDLE, HC_CONFIGURING, HC_ACTIVE, HC_OVERLOADED,  HC_OVERLOADED_TIMEOUT, HC_EXIT,

		MTC_INITIAL, MTC_IDLE, MTC_CONTROLPART, MTC_TESTCASE,
		MTC_TERMINATING_TESTCASE, MTC_TERMINATING_EXECUTION, MTC_PAUSED,
		MTC_CREATE, MTC_START, MTC_STOP, MTC_KILL, MTC_RUNNING, MTC_ALIVE,
		MTC_DONE, MTC_KILLED, MTC_CONNECT, MTC_DISCONNECT, MTC_MAP, MTC_UNMAP,
		MTC_CONFIGURING, MTC_EXIT,

		PTC_INITIAL, PTC_IDLE, PTC_FUNCTION, PTC_CREATE, PTC_START, PTC_STOP,
		PTC_KILL, PTC_RUNNING, PTC_ALIVE, PTC_DONE, PTC_KILLED, PTC_CONNECT,
		PTC_DISCONNECT, PTC_MAP, PTC_UNMAP, PTC_STOPPED, PTC_EXIT
	}
	private static executorStateEnum executorState = executorStateEnum.UNDEFINED_STATE;

	private static String component_type_module = null;
	private static String component_type_name = null;
	private static String component_name = null;

	private static String control_module_name = null;

	//originally testcase_name
	private static String testcaseModuleName;
	private static String testcaseDefinitionName;

	private static VerdictTypeEnum localVerdict = VerdictTypeEnum.NONE;
	private static int verdictCount[] = new int[] {0,0,0,0,0};
	private static int controlErrorCount = 0;
	private static String verdictReason = "";

	//in the compiler in_ttcn_try_block
	private static int ttcn_try_block_counter = 0;

	private TTCN_Runtime() {
		// private constructor to disable accidental instantiation
	}

	//originally get_state
	public static executorStateEnum get_state() {
		return executorState;
	}

	// originally set_state
	public static void set_state(final executorStateEnum newState) {
		executorState = newState;
	}

	//originally is_hc
	public static boolean is_hc() {
		switch (executorState) {
		case HC_INITIAL:
		case HC_IDLE:
		case HC_CONFIGURING:
		case HC_ACTIVE:
		case HC_OVERLOADED:
		case HC_OVERLOADED_TIMEOUT:
		case HC_EXIT:
			return true;
		default:
			return false;
		}
	}

	//originally is_mtc
	public static boolean is_mtc() {
		switch (executorState) {
		case MTC_INITIAL:
		case MTC_IDLE:
		case MTC_CONTROLPART:
		case MTC_TESTCASE:
		case MTC_TERMINATING_TESTCASE:
		case MTC_TERMINATING_EXECUTION:
		case MTC_PAUSED:
		case MTC_CREATE:
		case MTC_START:
		case MTC_STOP:
		case MTC_KILL:
		case MTC_RUNNING:
		case MTC_ALIVE:
		case MTC_DONE:
		case MTC_KILLED:
		case MTC_CONNECT:
		case MTC_DISCONNECT:
		case MTC_MAP:
		case MTC_UNMAP:
		case MTC_CONFIGURING:
		case MTC_EXIT:
			return true;
		default:
			return false;
		}
	}

	//originally is_ptc
	public static boolean is_ptc() {
		switch (executorState) {
		case PTC_INITIAL:
		case PTC_IDLE:
		case PTC_FUNCTION:
		case PTC_CREATE:
		case PTC_START:
		case PTC_STOP:
		case PTC_KILL:
		case PTC_RUNNING:
		case PTC_ALIVE:
		case PTC_DONE:
		case PTC_KILLED:
		case PTC_CONNECT:
		case PTC_DISCONNECT:
		case PTC_MAP:
		case PTC_UNMAP:
		case PTC_STOPPED:
		case PTC_EXIT:
			return true;
		default:
			return false;
		}
	}

	//originally is_tc
	public static boolean is_tc() {
		return is_mtc() || is_ptc();
	}

	//originally is_single
	public static boolean is_single() {
		switch (executorState) {
		case SINGLE_CONTROLPART:
		case SINGLE_TESTCASE:
			return true;
		default:
			return false;
		}
	}

	//originally is_undefined
	public static boolean is_undefined() {
		return executorState == executorStateEnum.UNDEFINED_STATE;
	}

	//originally is_idle
	public static boolean is_idle() {
		switch (executorState) {
		case HC_IDLE:
		case HC_ACTIVE:
		case HC_OVERLOADED:
		case MTC_IDLE:
		case PTC_IDLE:
		case PTC_STOPPED:
			return true;
		default:
			return false;
		}
	}

	//originally is_overloaded
	public static boolean is_overloaded() {
		switch (executorState) {
		case HC_OVERLOADED:
		case HC_OVERLOADED_TIMEOUT:
			return true;
		default:
			return false;
		}
	}

	public static void increase_try_catch_counter() {
		ttcn_try_block_counter++;
	}

	public static void decrease_try_catch_counter() {
		ttcn_try_block_counter--;
	}

	public static boolean is_in_ttcn_try_block() {
		return ttcn_try_block_counter > 0;
	}

	//originally in_controlpart
	private static boolean in_controlPart() {
		return executorState == executorStateEnum.SINGLE_CONTROLPART || executorState == executorStateEnum.MTC_CONTROLPART;
	}

	//originally verdict_enabled
	private static boolean verdict_enabled() {
		return executorState == executorStateEnum.SINGLE_TESTCASE || is_mtc() || is_ptc();
	}

	public static void begin_controlpart(final String moduleName) {
		control_module_name = moduleName;
		//FIXME implement execute_command
		TtcnLogger.log_controlpart_start_stop(moduleName, false);
	}

	public static void end_controlpart() {
		TTCN_Default.deactivateAll();
		TTCN_Default.resetCounter();
		TitanTimer.allStop();
		TtcnLogger.log_controlpart_start_stop(control_module_name, true);
		//FIXME implement execute_command
		control_module_name = null;
	}

	//originally TTCN_Runtime::check_begin_testcase
	public static void check_begin_testcase(final boolean hasTimer, final TitanFloat timerValue) {
		//FIXME missing checks

		if (hasTimer && timerValue.getValue() < 0.0) {
			throw new TtcnError(MessageFormat.format("The test case supervisortimer has negative duration ({0} s).", timerValue.getValue()));
		}
	}

	// originally TTCN_Runtime::begin_testcase
	//FIXME this is more complex
	public static void begin_testcase(final String moduleName, final String testcaseName, final String mtc_comptype_module, final String mtc_comptype_name, final boolean hasTimer, final TitanFloat timerValue) {
		switch (executorState) {
		case SINGLE_CONTROLPART:
			executorState = executorStateEnum.SINGLE_TESTCASE;
			break;
		case MTC_CONTROLPART:
			//FIXME implement
			executorState = executorStateEnum.MTC_TESTCASE;
			break;
		default:
			throw new TtcnError("Internal error: Executing a test case in an invalid state.");
		}
		TitanTimer.saveControlTimers();
		TTCN_Default.save_control_defaults();
		set_testcase_name(moduleName, testcaseName);
		//FIXME this is much more complex

		TtcnLogger.log_testcase_started(moduleName, testcaseName);
		if (hasTimer) {
			TitanTimer.testcaseTimer.start(timerValue.getValue());
		}

		set_component_type(mtc_comptype_module, mtc_comptype_name);
		initialize_component_type();
	}

	//originally TTCN_Runtime::end_testcase
	// FIXME this is more complex
	public static VerdictTypeEnum end_testcase() {
		switch (executorState) {
		case MTC_CREATE:
		case MTC_START:
		case MTC_STOP:
		case MTC_KILL:
		case MTC_RUNNING:
		case MTC_ALIVE:
		case MTC_DONE:
		case MTC_KILLED:
		case MTC_CONNECT:
		case MTC_DISCONNECT:
		case MTC_MAP:
		case MTC_UNMAP:
			executorState = executorStateEnum.MTC_TESTCASE;
		case MTC_TESTCASE:
			break;
		case SINGLE_TESTCASE:
			// implement disable_interrupt_handler();
			break;
		default:
			throw new TtcnError("Internal error: Ending a testcase in an invalid state.");
		}
		TitanTimer.testcaseTimer.stop();
		terminate_component_type();

		if (executorState == executorStateEnum.MTC_TESTCASE) {
			TtcnLogger.log_executor_runtime(TitanLoggerApi.ExecutorRuntime_reason.enum_type.waiting__for__ptcs__to__finish);
			// FIXME implement
			executorState = executorStateEnum.MTC_TERMINATING_TESTCASE;
		} else if (executorState == executorStateEnum.SINGLE_TESTCASE) {
			executorState = executorStateEnum.SINGLE_CONTROLPART;
			// FIXME implement
		}

		TtcnLogger.log_testcase_finished(testcaseModuleName, testcaseDefinitionName, localVerdict, verdictReason);

		verdictCount[localVerdict.getValue()]++;

		testcaseModuleName = null;
		testcaseDefinitionName = null;

		TTCN_Default.restoreControlDefaults();
		TitanTimer.restore_control_timers();

		if (executorState == executorStateEnum.MTC_PAUSED) {
			TtcnLogger.log_executor_runtime(TitanLoggerApi.ExecutorRuntime_reason.enum_type.user__paused__waiting__to__resume);
			// FIXME implement
			if (executorState != executorStateEnum.MTC_TERMINATING_EXECUTION) {
				TtcnLogger.log_executor_runtime(TitanLoggerApi.ExecutorRuntime_reason.enum_type.resuming__execution);
			}
		}
		if (executorState == executorStateEnum.MTC_TERMINATING_EXECUTION) {
			executorState = executorStateEnum.MTC_CONTROLPART;
			TtcnLogger.log_executor_runtime(TitanLoggerApi.ExecutorRuntime_reason.enum_type.terminating__execution);
			// FIXME implement
		}

		//FIXME this is more complex
		return localVerdict;
	}

	//originally TTCN_Runtime::initialize_component_type
	private static void initialize_component_type() {
		Module_List.initialize_component(component_type_module, component_type_name, true);

		//FIXME port set parameters
		TitanPort.all_start();

		localVerdict = VerdictTypeEnum.NONE;
		verdictReason = "";
	}

	//originally TTCN_Runtime::terminate_component_type
	private static void terminate_component_type() {
		//TODO add check
		TTCN_Default.deactivateAll();
		TitanTimer.allStop();
		TitanPort.deactivate_all();
		//TODO add log

		component_type_module = null;
		component_type_name = null;
	}

	//originally TTCN_Runtime::set_component_type
	private static void set_component_type(final String par_component_type_module, final String par_component_type_name) {
		//FIXME add checks
		component_type_module = par_component_type_module;
		component_type_name = par_component_type_name;
	}

	// originally TTCN_Runtime::set_component_name
	public static void set_component_name(final String new_component_name) {
		if (new_component_name != null && new_component_name.length() > 0) {
			component_name = new_component_name;
		} else {
			component_name = null;
		}
	}

	//originally set_testcase_name
	private static void set_testcase_name(final String parModuleName, final String parTestcaseName) {
		if (parModuleName == null || parModuleName.length() == 0 ||
				parTestcaseName == null || parTestcaseName.length() == 0) {
			throw new TtcnError("Internal error: TTCN_Runtime::set_testcase_name: Trying to set an invalid testcase name.");
		}

		if (testcaseModuleName != null || testcaseDefinitionName != null) {
			throw new TtcnError(MessageFormat.format("Internal error: TTCN_Runtime::set_testcase_name: Trying to set testcase name {0}.{1} while another one is active.", parModuleName, parTestcaseName));
		}

		testcaseModuleName = parModuleName;
		testcaseDefinitionName = parTestcaseName;
	}

	//originally get_component_type
	public static String get_component_type() {
		return component_type_name;
	}

	public static String get_component_name() {
		return component_name;
	}

	public static String get_testcase_name() {
		return testcaseDefinitionName;
	}

	//originally get_testcase_id_macro
	public static TitanCharString get_testcase_id_macro() {
		if (in_controlPart()) {
			throw new TtcnError("Macro %%testcaseId cannot be used from the control part outside test cases.");
		}

		if (testcaseDefinitionName == null || testcaseDefinitionName.length() == 0) {
			throw new TtcnError("Internal error: Evaluating macro %%testcaseId, but the name of the current testcase is not set.");
		}

		return new TitanCharString(testcaseDefinitionName);
	}

	//originally get_testcasename
	public static TitanCharString get_testcasename() {
		if (in_controlPart() || is_hc()) {
			return new TitanCharString("");
		}

		if (testcaseDefinitionName == null || testcaseDefinitionName.length() == 0) {
			throw new TtcnError("Internal error: Evaluating predefined function testcasename(), but the name of the current testcase is not set.");
		}

		return new TitanCharString(testcaseDefinitionName);
	}

	//originally map_port
	public static void map_port(final TitanComponent sourceComponentRef, final String sourePort, final TitanComponent destinationComponentRef, final String destinationPort) {
		//FIXME implement
		if (!sourceComponentRef.isBound()) {
			throw new TtcnError("The first argument of map operation contains an unbound component reference.");
		}

		final TitanComponent sourceComponent = sourceComponentRef;
		if (sourceComponent.getComponent() == TitanComponent.NULL_COMPREF) {
			throw new TtcnError("The first argument of map operation contains the null component reference.");
		}

		if (!destinationComponentRef.isBound()) {
			throw new TtcnError("The second argument of map operation contains an unbound component reference.");
		}

		final TitanComponent destinationComponent = destinationComponentRef;
		if (destinationComponent.getComponent() == TitanComponent.NULL_COMPREF) {
			throw new TtcnError("The second argument of map operation contains the null component reference.");
		}

		TitanComponent componentReference;
		String componentPort;
		String systemPort;
		if (sourceComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
			if (destinationComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
				throw new TtcnError("Both arguments of map operation refer to system ports.");
			}
			componentReference = destinationComponent;
			componentPort = destinationPort;
			systemPort = sourePort;
		} else if (destinationComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
			componentReference = sourceComponent;
			componentPort = sourePort;
			systemPort = destinationPort;
		} else {
			throw new TtcnError("Both arguments of map operation refer to test component ports.");
		}

		//FIXME implement
		TitanPort.map_port(componentPort, systemPort, false);
	}

	//originally unmap_port
	public static void unmap_port(final TitanComponent sourceComponentRef, final String sourePort, final TitanComponent destinationComponentRef, final String destinationPort) {
		//FIXME implement
		if (!sourceComponentRef.isBound()) {
			throw new TtcnError("The first argument of unmap operation contains an unbound component reference.");
		}

		final TitanComponent sourceComponent = sourceComponentRef;
		if (sourceComponent.getComponent() == TitanComponent.NULL_COMPREF) {
			throw new TtcnError("The first argument of unmap operation contains the null component reference.");
		}

		if (!destinationComponentRef.isBound()) {
			throw new TtcnError("The second argument of unmap operation contains an unbound component reference.");
		}

		final TitanComponent destinationComponent = destinationComponentRef;
		if (destinationComponent.getComponent() == TitanComponent.NULL_COMPREF) {
			throw new TtcnError("The second argument of unmap operation contains the null component reference.");
		}

		TitanComponent componentReference;
		String componentPort;
		String systemPort;
		if (sourceComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
			if (destinationComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
				throw new TtcnError("Both arguments of unmap operation refer to system ports.");
			}
			componentReference = destinationComponent;
			componentPort = destinationPort;
			systemPort = sourePort;
		} else if (destinationComponent.getComponent() == TitanComponent.SYSTEM_COMPREF) {
			componentReference = sourceComponent;
			componentPort = sourePort;
			systemPort = destinationPort;
		} else {
			throw new TtcnError("Both arguments of unmap operation refer to test component ports.");
		}

		//FIXME implement
		TitanPort.unmap_port(componentPort, systemPort, false);
	}

	public static void connect_port(final TitanComponent sourceComponent, final String sourePort, final TitanComponent destinationComponent, final String destinationPort) {
		//FIXME implement
		throw new TtcnError("Connecting components is not yet supported!");
	}

	public static void disconnect_port(final TitanComponent sourceComponent, final String sourePort, final TitanComponent destinationComponent, final String destinationPort) {
		//FIXME implement
		throw new TtcnError("Disconnecting components is not yet supported!");
	}

	//originally create_component
	public static int create_component(final String createdComponentTypeModule, final String createdComponentTypeName,
			final String createdComponentName, final String createdComponentLocation, final boolean createdComponentAlive) {
		//FIXME implement
		throw new TtcnError("Creating component is not yet supported!");
	}

	//originally component_done, with component parameter
	public static TitanAlt_Status component_done(final int component_reference) {
		if (in_controlPart()) {
			throw new TtcnError("Done operation cannot be performed in the control part.");
		}

		switch (component_reference) {
		case TitanComponent.NULL_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the null component reference.");
		case TitanComponent.MTC_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the component reference of MTC.");
		case TitanComponent.SYSTEM_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the component reference of system.");
		default:
			//FIXME implement rest of the branches
			throw new TtcnError("component_done is not yet supported!");
		}
	}

	//FIXME needs text_buffer parameter once decoding is available
	//originally component_done, with component parameter
	public static TitanAlt_Status component_done(final int component_reference, final String return_type) {
		if (in_controlPart()) {
			throw new TtcnError("Done operation cannot be performed in the control part.");
		}

		switch (component_reference) {
		case TitanComponent.NULL_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the null component reference.");
		case TitanComponent.MTC_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the component reference of MTC.");
		case TitanComponent.SYSTEM_COMPREF:
			throw new TtcnError("Done operation cannot be performed on the component reference of system.");
		case TitanComponent.ANY_COMPREF:
			throw new TtcnError("Done operation with return value cannot be performed on 'any component'.");
		case TitanComponent.ALL_COMPREF:
			throw new TtcnError("Done operation with return value cannot be performed on 'all component'.");
		default:
			break;
		}

		if (is_single()) {
			throw new TtcnError("Done operation on a component reference cannot be performed in single mode.");
		}
		//FIXME implement
		throw new TtcnError("component_done is not yet supported!");
	}

	//originally component_killed, with component parameter
	public static TitanAlt_Status component_killed(final int component_reference) {
		if (in_controlPart()) {
			throw new TtcnError("Killed operation cannot be performed in the control part.");
		}

		switch (component_reference) {
		case TitanComponent.NULL_COMPREF:
			throw new TtcnError("Killed operation cannot be performed on the null component reference.");
		case TitanComponent.MTC_COMPREF:
			throw new TtcnError("Killed operation cannot be performed on the component reference of MTC.");
		case TitanComponent.SYSTEM_COMPREF:
			throw new TtcnError("Killed operation cannot be performed on the component reference of system.");
		default:
			//FIXME implement rest of the branches
			throw new TtcnError("Component_killed is not yet supported!");
		}
	}

	//originally component_running, with component parameter
	public static boolean component_running(final int component_reference) {
		if (in_controlPart()) {
			throw new TtcnError("Component running operation cannot be performed in the control part.");
		}

		switch (component_reference) {
		case TitanComponent.NULL_COMPREF:
			throw new TtcnError("Running operation cannot be performed on the null component reference.");
		case TitanComponent.MTC_COMPREF:
			throw new TtcnError("Running operation cannot be performed on the component reference of MTC.");
		case TitanComponent.SYSTEM_COMPREF:
			throw new TtcnError("Running operation cannot be performed on the component reference of system.");
		default:
			//FIXME implement rest of the branches
			throw new TtcnError("Component_running is not yet supported!");
		}
	}

	//originally component_alive, with component parameter
	public static boolean component_alive(final int component_reference) {
		if (in_controlPart()) {
			throw new TtcnError("Alive operation cannot be performed in the control part.");
		}

		switch (component_reference) {
		case TitanComponent.NULL_COMPREF:
			throw new TtcnError("Alive operation cannot be performed on the null component reference.");
		case TitanComponent.MTC_COMPREF:
			throw new TtcnError("Alive operation cannot be performed on the component reference of MTC.");
		case TitanComponent.SYSTEM_COMPREF:
			throw new TtcnError("Alive operation cannot be performed on the component reference of system.");
		default:
			//FIXME implement rest of the branches
			throw new TtcnError("Component_alive is not yet supported!");
		}
	}

	//originally stop_component
	public static void stop_component(final int component_reference) {
		//FIXME implement
		throw new TtcnError("Stoping a component is not yet supported!");
	}

	//originally stop_execution
	public static void stop_execution() {
		//FIXME implement
		throw new TtcnError("Stoping execution is not yet supported!");
	}

	//originally kill_component
	public static void kill_component(final int component_reference) {
		//FIXME implement
		throw new TtcnError("Killing a component is not yet supported!");
	}

	//originally kill_execution
	public static void kill_execution() {
		//FIXME implement
		throw new TtcnError("Killing execution is not yet supported!");
	}

	public static void setverdict(final TitanVerdictType.VerdictTypeEnum newValue) {
		setverdict(newValue, "");
	}

	public static void setverdict(final TitanVerdictType.VerdictTypeEnum newValue, final String reason) {
		if (verdict_enabled()) {
			if (VerdictTypeEnum.ERROR.equals(newValue)) {
				throw new TtcnError("Error verdict cannot be set explicitly.");
			}

			setverdict_internal(newValue, reason);
		} else if (in_controlPart()) {
			throw new TtcnError("Verdict cannot be set in the control part.");
		} else {
			throw new TtcnError("Internal error: Setting the verdict in invalid state.");
		}
	}

	public static void setverdict(final TitanVerdictType newValue) {
		setverdict(newValue, "");
	}

	public static void setverdict(final TitanVerdictType newValue, final String reason) {
		if (!newValue.isBound()) {
			throw new TtcnError("The argument of setverdict operation is an unbound verdict value.");
		}

		setverdict(newValue.getValue(), reason);
	}

	//originally set_error_verdict
	public static void set_error_verdict() {
		if (verdict_enabled()) {
			setverdict_internal(VerdictTypeEnum.ERROR, "");
		}
		//FIXME implement else
	}

	//originally getverdict
	public static TitanVerdictType get_verdict() {
		if (verdict_enabled()) {
			//FIXME logging
		} else if (in_controlPart()) {
			throw new TtcnError("Getverdict operation cannot be performed in the control part.");
		} else {
			throw new TtcnError("Internal error: Performing getverdict operation in invalid state.");
		}

		return new TitanVerdictType(localVerdict);
	}

	//originally setverdict_internal
	private static void setverdict_internal(final TitanVerdictType.VerdictTypeEnum newValue, final String reason) {
		if (newValue.getValue() < VerdictTypeEnum.NONE.getValue() || newValue.getValue() > VerdictTypeEnum.ERROR.getValue()) {
			throw new TtcnError(MessageFormat.format("Internal error: setting an invalid verdict value ({0}).", newValue.getValue()));
		}

		final VerdictTypeEnum oldVerdict = localVerdict;
		if (localVerdict.getValue() < newValue.getValue()) {
			verdictReason = reason;
			localVerdict = newValue;
			if (reason == null || reason.length() == 0) {
				TtcnLogger.log_setverdict(newValue, oldVerdict, localVerdict, null, null);
			} else {
				TtcnLogger.log_setverdict(newValue, oldVerdict, localVerdict, reason, reason);
			}
		} else if (localVerdict.getValue() == newValue.getValue()) {
			if (reason == null || reason.length() == 0) {
				TtcnLogger.log_setverdict(newValue, oldVerdict, localVerdict, null, null);
			} else {
				TtcnLogger.log_setverdict(newValue, oldVerdict, localVerdict, reason, reason);
			}
		}

		//FIXME handle debugger breakpoints
	}

	//originially log_verdict_statistics
	public static void log_verdict_statistics() {
		final int totalTestcases = verdictCount[VerdictTypeEnum.NONE.getValue()] + verdictCount[VerdictTypeEnum.PASS.getValue()]
				+ verdictCount[VerdictTypeEnum.INCONC.getValue()] + verdictCount[VerdictTypeEnum.FAIL.getValue()]
						+ verdictCount[VerdictTypeEnum.ERROR.getValue()];

		VerdictTypeEnum overallVerdict;
		if (controlErrorCount > 0 || verdictCount[VerdictTypeEnum.ERROR.getValue()] > 0) {
			overallVerdict = VerdictTypeEnum.ERROR;
		} else if (verdictCount[VerdictTypeEnum.FAIL.getValue()] > 0) {
			overallVerdict = VerdictTypeEnum.FAIL;
		} else if (verdictCount[VerdictTypeEnum.INCONC.getValue()] > 0) {
			overallVerdict = VerdictTypeEnum.INCONC;
		} else if (verdictCount[VerdictTypeEnum.PASS.getValue()] > 0) {
			overallVerdict = VerdictTypeEnum.PASS;
		} else {
			overallVerdict = VerdictTypeEnum.NONE;
		}

		if (totalTestcases > 0) {
			TtcnLogger.log_verdict_statistics(verdictCount[VerdictTypeEnum.NONE.getValue()], (100.0 * verdictCount[VerdictTypeEnum.NONE.getValue()]) / totalTestcases,
					verdictCount[VerdictTypeEnum.PASS.getValue()], (100.0 * verdictCount[VerdictTypeEnum.PASS.getValue()]) / totalTestcases,
					verdictCount[VerdictTypeEnum.INCONC.getValue()], (100.0 * verdictCount[VerdictTypeEnum.INCONC.getValue()]) / totalTestcases,
					verdictCount[VerdictTypeEnum.FAIL.getValue()], (100.0 * verdictCount[VerdictTypeEnum.FAIL.getValue()]) / totalTestcases,
					verdictCount[VerdictTypeEnum.ERROR.getValue()], (100.0 * verdictCount[VerdictTypeEnum.ERROR.getValue()]) / totalTestcases);
		} else {
			TtcnLogger.log_verdict_statistics(0, 0.0, 0, 0.0, 0, 0.0, 0, 0.0, 0, 0.0);
		}

		if (controlErrorCount > 0) {
			TtcnLogger.log_controlpart_errors(controlErrorCount);
		}

		TtcnLogger.log_str(Severity.STATISTICS_VERDICT, MessageFormat.format("Test execution summary: {0} test case{1} executed. Overall verdict: {2}", totalTestcases, totalTestcases > 1 ? "s were" : " was", overallVerdict.getName()));

		verdictCount[VerdictTypeEnum.NONE.getValue()] = 0;
		verdictCount[VerdictTypeEnum.PASS.getValue()] = 0;
		verdictCount[VerdictTypeEnum.INCONC.getValue()] = 0;
		verdictCount[VerdictTypeEnum.FAIL.getValue()] = 0;
		verdictCount[VerdictTypeEnum.ERROR.getValue()] = 0;
		controlErrorCount = 0;
	}

	public static void begin_action() {
		TtcnLogger.begin_event(Severity.ACTION_UNQUALIFIED);
		TtcnLogger.log_event_str("Action: ");
	}

	public static void end_action() {
		TtcnLogger.end_event();
	}
}
