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
import jd.core.model.classfile.LocalVariables;
import jd.core.model.instruction.bytecode.ByteCodeConstants;


public class IBinaryOperatorInstruction extends BinaryOperatorInstruction 
{
	public IBinaryOperatorInstruction(
			int opcode, int offset, int lineNumber, int priority, 
			String operator, Instruction value1, Instruction value2)
	{
		super(
			opcode, offset, lineNumber, priority, 
			null, operator, value1, value2);
	}

	public String getReturnedSignature(
			ConstantPool constants, LocalVariables localVariables) 
	{		
		String signature;

		switch (this.value1.opcode)
		{
		case ByteCodeConstants.ICONST:
		case ByteCodeConstants.BIPUSH:
		case ByteCodeConstants.SIPUSH:
			signature = this.value2.getReturnedSignature(constants, localVariables);
			if (signature == null)
				signature = this.value1.getReturnedSignature(constants, localVariables);
			return signature;
		default:
			signature = this.value1.getReturnedSignature(constants, localVariables);
			if (signature == null)
				signature = this.value2.getReturnedSignature(constants, localVariables);
			return signature;
		}
	}
}
