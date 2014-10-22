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
package com.b3dgs.lionheart.effect;

import com.b3dgs.lionengine.anim.AnimState;
import com.b3dgs.lionengine.anim.Animation;
import com.b3dgs.lionengine.core.Core;
import com.b3dgs.lionengine.core.Graphic;
import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.drawable.Drawable;
import com.b3dgs.lionengine.drawable.SpriteAnimated;
import com.b3dgs.lionengine.game.CameraGame;
import com.b3dgs.lionengine.game.ContextGame;
import com.b3dgs.lionengine.game.FactoryObjectGame;
import com.b3dgs.lionengine.game.ObjectGame;
import com.b3dgs.lionengine.game.SetupSurfaceRasteredGame;
import com.b3dgs.lionengine.game.configurer.ConfigAnimations;
import com.b3dgs.lionengine.game.configurer.ConfigFrames;
import com.b3dgs.lionengine.game.configurer.Configurer;
import com.b3dgs.lionengine.game.purview.Rasterable;
import com.b3dgs.lionengine.game.purview.model.RasterableModel;
import com.b3dgs.lionheart.AppLionheart;
import com.b3dgs.lionheart.map.Map;

/**
 * Effect base implementation.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public class Effect
        extends ObjectGame
        implements Rasterable
{
    /**
     * Get an effect configuration file.
     * 
     * @param type The config associated class.
     * @return The media config.
     */
    public static Media getConfig(Class<?> type)
    {
        return Core.MEDIA.create(AppLionheart.EFFECTS_DIR, type.getSimpleName() + "."
                + FactoryObjectGame.FILE_DATA_EXTENSION);
    }

    /** Raster model. */
    private final Rasterable rasterable;
    /** Sprite. */
    private final SpriteAnimated sprite;
    /** Animation. */
    private final Animation animStart;
    /** Index. */
    private int index;

    /**
     * Constructor.
     * 
     * @param setup The setup reference.
     */
    public Effect(SetupSurfaceRasteredGame setup)
    {
        super(setup);
        final Configurer configurer = setup.getConfigurer();
        final ConfigFrames framesData = ConfigFrames.create(configurer);
        final ConfigAnimations configAnimations = ConfigAnimations.create(configurer);
        animStart = configAnimations.getAnimation("start");
        sprite = Drawable.loadSpriteAnimated(setup.surface, framesData.getHorizontal(), framesData.getVertical());
        rasterable = new RasterableModel(setup, Map.TILE_HEIGHT);
        setSize(sprite.getFrameWidth(), sprite.getFrameHeight());
    }

    /**
     * Start the effect.
     * 
     * @param x The horizontal location.
     * @param y The vertical location.
     */
    public void start(int x, int y)
    {
        teleport(x, y);
        sprite.play(animStart);
    }

    /*
     * EffectGame
     */

    @Override
    public void prepare(ContextGame context)
    {
        // Nothing to do
    }

    @Override
    public void update(double extrp)
    {
        sprite.updateAnimation(extrp);
        if (sprite.getAnimState() == AnimState.FINISHED)
        {
            destroy();
        }
        if (rasterable.isRastered())
        {
            index = rasterable.getRasterIndex(getLocationIntY());
            final SpriteAnimated anim = getRasterAnim(index);
            if (anim != null)
            {
                anim.setFrame(sprite.getFrame());
            }
        }
    }

    @Override
    public void render(Graphic g, CameraGame camera)
    {
        final SpriteAnimated anim;
        if (rasterable.isRastered())
        {
            anim = getRasterAnim(index);
        }
        else
        {
            anim = sprite;
        }
        anim.render(g, camera.getViewpointX(getLocationIntX()),
                camera.getViewpointY(getLocationIntY() + sprite.getFrameHeight()));
    }

    /*
     * Rasterable
     */

    @Override
    public int getRasterIndex(double y)
    {
        return rasterable.getRasterIndex(y);
    }

    @Override
    public SpriteAnimated getRasterAnim(int rasterIndex)
    {
        return rasterable.getRasterAnim(rasterIndex);
    }

    @Override
    public boolean isRastered()
    {
        return rasterable.isRastered();
    }
}