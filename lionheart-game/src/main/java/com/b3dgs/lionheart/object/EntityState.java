/*
 * Copyright (C) 2013-2017 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
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
package com.b3dgs.lionheart.object;

import java.util.Locale;

import com.b3dgs.lionengine.game.state.State;
import com.b3dgs.lionengine.game.state.StateAnimationBased;

/**
 * Main entity states.
 */
public enum EntityState implements StateAnimationBased
{
    /** Idle state. */
    IDLE(StateIdle.class),
    /** Walk state. */
    WALK(StateWalk.class);

    /** State class. */
    private final Class<? extends State> clazz;
    /** Associated animation. */
    private final String animation;

    /**
     * Create the state.
     * 
     * @param clazz The state class.
     */
    EntityState(Class<? extends State> clazz)
    {
        this.clazz = clazz;
        animation = name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public Class<? extends State> getStateClass()
    {
        return clazz;
    }

    @Override
    public String getAnimationName()
    {
        return animation;
    }
}
