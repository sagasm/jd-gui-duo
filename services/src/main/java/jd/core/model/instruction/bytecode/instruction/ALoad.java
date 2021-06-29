/*******************************************************************************
 * Copyright (C) 2007-2019 Emmanuel Dupuy
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package jd.core.model.instruction.bytecode.instruction;

import jd.core.model.classfile.ConstantPool;
import jd.core.model.classfile.LocalVariable;
import jd.core.model.classfile.LocalVariables;
import jd.core.util.StringConstants;

public class ALoad extends LoadInstruction 
{
	public ALoad(int opcode, int offset, int lineNumber, int index)
	{
		super(opcode, offset, lineNumber, index, null);
	}

	public String getReturnedSignature(
			ConstantPool constants, LocalVariables localVariables) 
	{
		if ((constants == null) || (localVariables == null))
			return null;
		
		LocalVariable lv = localVariables.getLocalVariableWithIndexAndOffset(this.index, this.offset);
		
		if ((lv != null) && (lv.signature_index > 0))
			return constants.getConstantUtf8(lv.signature_index);
		
		return StringConstants.INTERNAL_OBJECT_SIGNATURE;
	}
}
