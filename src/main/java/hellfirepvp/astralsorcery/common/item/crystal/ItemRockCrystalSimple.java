/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2017
 *
 * This project is licensed under GNU GENERAL PUBLIC LICENSE Version 3.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.item.crystal;

import hellfirepvp.astralsorcery.common.item.crystal.base.ItemRockCrystalBase;
import hellfirepvp.astralsorcery.common.item.crystal.base.ItemTunedCrystalBase;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.List;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: ItemRockCrystalSimple
 * Created by HellFirePvP
 * Date: 15.09.2016 / 14:25
 */
public class ItemRockCrystalSimple extends ItemRockCrystalBase {

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        ItemStack stack = new ItemStack(this);
        CrystalProperties.applyCrystalProperties(stack, new CrystalProperties(CrystalProperties.MAX_SIZE_ROCK, 100, 100));
        subItems.add(stack);
    }

    @Override
    public ItemTunedCrystalBase getTunedItemVariant() {
        return ItemsAS.tunedRockCrystal;
    }
}
