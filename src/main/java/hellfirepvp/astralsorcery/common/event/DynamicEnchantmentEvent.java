/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2018
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.event;

import hellfirepvp.astralsorcery.common.enchantment.dynamic.DynamicEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: DynamicEnchantmentEvent
 * Created by HellFirePvP
 * Date: 10.08.2018 / 20:25
 */
public class DynamicEnchantmentEvent {

    //The event to ADD new dynamic enchantments
    public static class Add extends Event {

        private List<DynamicEnchantment> enchantmentsToApply = new LinkedList<>();
        private final ItemStack itemStack;

        public Add(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        public ItemStack getEnchantedItemStack() {
            return itemStack;
        }

        public List<DynamicEnchantment> getEnchantmentsToApply() {
            return enchantmentsToApply;
        }
    }

    //The event to MODIFY or REACT to previously defined/added dynamic enchantments + enchantments
    public static class Modify extends Event {

        private List<DynamicEnchantment> enchantmentsToApply;
        private final ItemStack itemStack;

        public Modify(ItemStack itemStack, List<DynamicEnchantment> enchantmentsToApply) {
            this.itemStack = itemStack;
            this.enchantmentsToApply = enchantmentsToApply;
        }

        public ItemStack getEnchantedItemStack() {
            return itemStack;
        }

        public List<DynamicEnchantment> getEnchantmentsToApply() {
            return enchantmentsToApply;
        }
    }
}