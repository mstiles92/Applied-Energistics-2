/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.util.inv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class WrapperInventoryRange implements IInventory
{

	private final IInventory src;
	int[] slots;
	protected boolean ignoreValidItems = false;

	public static String concatLines(int[] s, String separator)
	{
		if ( s.length > 0 )
		{
			StringBuilder sb = new StringBuilder();
			for (int value : s)
			{
				if ( sb.length() > 0 )
				{
					sb.append( separator );
				}
				sb.append( value );
			}
			return sb.toString();
		}
		return "";
	}

	public WrapperInventoryRange(IInventory a, int[] s, boolean ignoreValid) {
		src = a;
		slots = s;

		if ( slots == null )
			slots = new int[0];

		ignoreValidItems = ignoreValid;
	}

	public WrapperInventoryRange(IInventory a, int _min, int _size, boolean ignoreValid) {
		src = a;
		slots = new int[_size];
		for (int x = 0; x < _size; x++)
			slots[x] = _min + x;
		ignoreValidItems = ignoreValid;
	}

	@Override
	public int getSizeInventory()
	{
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return src.getStackInSlot( slots[var1] );
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		return src.decrStackSize( slots[var1], var2 );
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		return src.getStackInSlotOnClosing( slots[var1] );
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
	{
		src.setInventorySlotContents( slots[var1], var2 );
	}

	@Override
	public String getInventoryName()
	{
		return src.getInventoryName();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return src.getInventoryStackLimit();
	}

	@Override
	public void markDirty()
	{
		src.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return src.isUseableByPlayer( var1 );
	}

	@Override
	public void openInventory()
	{
		src.openInventory();
	}

	@Override
	public void closeInventory()
	{
		src.closeInventory();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if ( ignoreValidItems )
			return true;

		return src.isItemValidForSlot( slots[i], itemstack );
	}

}
