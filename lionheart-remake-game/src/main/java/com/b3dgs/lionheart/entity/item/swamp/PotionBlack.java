/*
 * Copyright (C) 2013-2014 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package com.b3dgs.lionheart.entity.item.swamp;

import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.game.SetupSurfaceRasteredGame;
import com.b3dgs.lionheart.CategoryType;
import com.b3dgs.lionheart.Sfx;
import com.b3dgs.lionheart.ThemeType;
import com.b3dgs.lionheart.entity.Entity;
import com.b3dgs.lionheart.entity.item.EntityItem;
import com.b3dgs.lionheart.entity.player.Valdyn;

/**
 * Potion black implementation.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public final class PotionBlack
        extends EntityItem
{
    /** Class media. */
    public static final Media MEDIA = Entity.getConfig(CategoryType.ITEM, ThemeType.SWAMP, PotionBlack.class);

    /**
     * Constructor.
     * 
     * @param setup The setup reference.
     */
    public PotionBlack(SetupSurfaceRasteredGame setup)
    {
        super(setup);
    }

    /*
     * EntityItem
     */

    @Override
    protected void onTaken(Valdyn entity)
    {
        Sfx.ITEM_POTION_BIG.play();
    }
}