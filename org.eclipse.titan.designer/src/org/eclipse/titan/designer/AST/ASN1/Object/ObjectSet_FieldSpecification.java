/******************************************************************************
 * Copyright (c) 2000-2018 Ericsson Telecom AB
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/EPL-2.0.html
 ******************************************************************************/
package org.eclipse.titan.designer.AST.ASN1.Object;

import org.eclipse.titan.designer.AST.ASTVisitor;
import org.eclipse.titan.designer.AST.ISetting;
import org.eclipse.titan.designer.AST.Identifier;
import org.eclipse.titan.designer.AST.Scope;
import org.eclipse.titan.designer.AST.ASN1.ObjectClass;
import org.eclipse.titan.designer.AST.ASN1.ObjectSet;
import org.eclipse.titan.designer.compiler.JavaGenData;
import org.eclipse.titan.designer.editors.ProposalCollector;
import org.eclipse.titan.designer.editors.actions.DeclarationCollector;
import org.eclipse.titan.designer.parsers.CompilationTimeStamp;

/**
 * Class to represent an ObjectSetFieldSpec.
 *
 * @author Kristof Szabados
 */
public final class ObjectSet_FieldSpecification extends FieldSpecification {

	private final ObjectClass objectClass;
	private final ObjectSet defaultObjectSet;

	public ObjectSet_FieldSpecification(final Identifier identifier, final ObjectClass objectClass, final boolean isOptional,
			final ObjectSet defaultObjectSet) {
		super(identifier, isOptional);
		this.objectClass = objectClass;
		this.defaultObjectSet = defaultObjectSet;

		if (null != defaultObjectSet) {
			defaultObjectSet.setFullNameParent(this);
		}
	}

	@Override
	/** {@inheritDoc} */
	public Fieldspecification_types getFieldSpecificationType() {
		return Fieldspecification_types.FS_OS;
	}

	@Override
	/** {@inheritDoc} */
	public void setMyObjectClass(final ObjectClass_Definition paramObjectClass) {
		super.setMyObjectClass(paramObjectClass);
		final Scope scope = myObjectClass.getMyScope();
		objectClass.setMyScope(scope);
		if (null != defaultObjectSet) {
			defaultObjectSet.setMyScope(scope);
		}
	}

	@Override
	/** {@inheritDoc} */
	public boolean hasDefault() {
		return null != defaultObjectSet;
	}

	@Override
	/** {@inheritDoc} */
	public ISetting getDefault() {
		return defaultObjectSet;
	}

	public ObjectClass getObjectClass() {
		return objectClass;
	}

	@Override
	/** {@inheritDoc} */
	public void check(final CompilationTimeStamp timestamp) {
		if (null != lastTimeChecked && !lastTimeChecked.isLess(timestamp)) {
			return;
		}

		objectClass.setGenName(myObjectClass.getGenNameOwn(), identifier.getName());
		objectClass.check(timestamp);
		if (null != defaultObjectSet) {
			defaultObjectSet.setMyGovernor(objectClass);
			defaultObjectSet.setGenName(objectClass.getGenNameOwn(), "_defobj_");
			defaultObjectSet.check(timestamp);
		}

		lastTimeChecked = timestamp;
	}

	@Override
	/** {@inheritDoc} */
	public void addDeclaration(final DeclarationCollector declarationCollector, final int i) {
		if (null != objectClass) {
			objectClass.addDeclaration(declarationCollector, i);
		}
	}

	@Override
	/** {@inheritDoc} */
	public void addProposal(final ProposalCollector propCollector, final int i) {
		if (null != objectClass) {
			objectClass.addProposal(propCollector, i);
		}
	}

	@Override
	/** {@inheritDoc} */
	protected boolean memberAccept(final ASTVisitor v) {
		if (identifier != null && !identifier.accept(v)) {
			return false;
		}
		if (objectClass != null && !objectClass.accept(v)) {
			return false;
		}
		if (defaultObjectSet != null && !defaultObjectSet.accept(v)) {
			return false;
		}
		return true;
	}

	@Override
	/** {@inheritDoc} */
	public void generateCode(final JavaGenData aData) {
		objectClass.generateCode(aData);

		if (defaultObjectSet != null) {
			defaultObjectSet.generateCode(aData);
		}
	}
}
