package com.jaquadro.minecraft.storagedrawers.api.storage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IDrawer
{
    /**
     * Gets an ItemStack of size 1 representing the type, metadata, and tags of the stored items.
     * The returned ItemStack should not be modified for any reason.  Make a copy if you need to store or modify it.
     */
    public ItemStack getStoredItemPrototype ();

    /**
     * Gets an ItemStack initialized to the number of items stored in this drawer.
     * The returned ItemStack is guaranteed to be a new copy and can be used for any purpose.  Does not affect drawer contents.
     */
    public ItemStack getStoredItemCopy ();

    /**
     * Sets the type of the stored item and initializes it to the given amount.  Any existing item will be replaced.
     *
     * @param itemPrototype An ItemStack representing the type, metadata, and tags of the item to store.
     * @param amount The amount to initialize the stored item count to.
     */
    public void setStoredItem (ItemStack itemPrototype, int amount);

    /**
     * Gets the number of items stored in this drawer.
     */
    public int getStoredItemCount ();

    /**
     * Sets the number of items stored in this drawer.  Triggers syncing of inventories and client data.
     * Setting a drawer's count to 0 may also result in the item type being cleared, depending in implementation.
     *
     * @param amount The new amount of items stored in this drawer.
     */
    public void setStoredItemCount (int amount);

    /**
     * Gets the maximum number of items that can be stored in this drawer.
     * This value will vary depending on the max stack size of the stored item type.
     */
    public int getMaxCapacity ();

    /**
     * Gets the number of items that could still be added to this drawer before it is full.
     */
    public int getRemainingCapacity ();

    /**
     * Gets the max stack size of the item type stored in this drawer.  Convenience method.
     */
    public int getStoredItemStackSize ();

    /**
     * Gets whether or not an item of the given type and data can be stored in this drawer.
     *
     * Stack size and available capacity are not considered.  For drawers that are not empty, this
     * method can allow ore-dictionary compatible items to be accepted into the drawer, as defined by what
     * the drawer considers to be an equivalent item.
     *
     * @param itemPrototype An ItemStack representing the type, metadata, and tags of an item.
     */
    boolean canItemBeStored (ItemStack itemPrototype);

    /**
     * Gets whether or not an item of the given type and data can be extracted from this drawer.
     *
     * This is intended to allow outbound ore-dictionary conversions of compatible items, as defined by what
     * the drawer considers to be an equivalent item.
     *
     * @param itemPrototype An ItemStack representing the type, metadata, and tags of an item.
     */
    boolean canItemBeExtracted (ItemStack itemPrototype);

    /**
     * Gets whether or not the drawer has items.
     * A drawer set with an item type and 0 count is not considered empty.
     */
    boolean isEmpty ();

    public void writeToNBT (NBTTagCompound tag);

    public void readFromNBT (NBTTagCompound tag);
}
