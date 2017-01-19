
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.gthr.android.animations;


import com.gthr.android.animations.Shrinking.ShrinkingVerticalAnimator;
import com.gthr.android.animations.attention.BounceAnimator;
import com.gthr.android.animations.attention.FlashAnimator;
import com.gthr.android.animations.attention.PulseAnimator;
import com.gthr.android.animations.attention.RubberBandAnimator;
import com.gthr.android.animations.attention.ShakeAnimator;
import com.gthr.android.animations.attention.StandUpAnimator;
import com.gthr.android.animations.attention.SwingAnimator;
import com.gthr.android.animations.attention.TadaAnimator;
import com.gthr.android.animations.attention.WaveAnimator;
import com.gthr.android.animations.attention.WobbleAnimator;
import com.gthr.android.animations.bouncing_entrances.BounceInAnimator;
import com.gthr.android.animations.bouncing_entrances.BounceInDownAnimator;
import com.gthr.android.animations.bouncing_entrances.BounceInLeftAnimator;
import com.gthr.android.animations.bouncing_entrances.BounceInRightAnimator;
import com.gthr.android.animations.bouncing_entrances.BounceInUpAnimator;
import com.gthr.android.animations.expanding.ExpandingVerticalAnimator;
import com.gthr.android.animations.fading_entrances.FadeInAnimator;
import com.gthr.android.animations.fading_entrances.FadeInDownAnimator;
import com.gthr.android.animations.fading_entrances.FadeInLeftAnimator;
import com.gthr.android.animations.fading_entrances.FadeInRightAnimator;
import com.gthr.android.animations.fading_entrances.FadeInUpAnimator;
import com.gthr.android.animations.fading_exits.FadeOutAnimator;
import com.gthr.android.animations.fading_exits.FadeOutDownAnimator;
import com.gthr.android.animations.fading_exits.FadeOutLeftAnimator;
import com.gthr.android.animations.fading_exits.FadeOutRightAnimator;
import com.gthr.android.animations.fading_exits.FadeOutUpAnimator;
import com.gthr.android.animations.flippers.FlipInXAnimator;
import com.gthr.android.animations.flippers.FlipInYAnimator;
import com.gthr.android.animations.flippers.FlipOutXAnimator;
import com.gthr.android.animations.flippers.FlipOutYAnimator;
import com.gthr.android.animations.rotating_entrances.RotateInAnimator;
import com.gthr.android.animations.rotating_entrances.RotateInDownLeftAnimator;
import com.gthr.android.animations.rotating_entrances.RotateInDownRightAnimator;
import com.gthr.android.animations.rotating_entrances.RotateInUpLeftAnimator;
import com.gthr.android.animations.rotating_entrances.RotateInUpRightAnimator;
import com.gthr.android.animations.rotating_exits.RotateOutAnimator;
import com.gthr.android.animations.rotating_exits.RotateOutDownLeftAnimator;
import com.gthr.android.animations.rotating_exits.RotateOutDownRightAnimator;
import com.gthr.android.animations.rotating_exits.RotateOutUpLeftAnimator;
import com.gthr.android.animations.rotating_exits.RotateOutUpRightAnimator;
import com.gthr.android.animations.sliders.SlideInDownAnimator;
import com.gthr.android.animations.sliders.SlideInLeftAnimator;
import com.gthr.android.animations.sliders.SlideInRightAnimator;
import com.gthr.android.animations.sliders.SlideInUpAnimator;
import com.gthr.android.animations.sliders.SlideOutDownAnimator;
import com.gthr.android.animations.sliders.SlideOutLeftAnimator;
import com.gthr.android.animations.sliders.SlideOutRightAnimator;
import com.gthr.android.animations.sliders.SlideOutUpAnimator;
import com.gthr.android.animations.specials.HingeAnimator;
import com.gthr.android.animations.specials.RollInAnimator;
import com.gthr.android.animations.specials.RollOutAnimator;
import com.gthr.android.animations.specials.in.DropOutAnimator;
import com.gthr.android.animations.specials.in.LandingAnimator;
import com.gthr.android.animations.specials.out.TakingOffAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInDownAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInLeftAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInMaximalAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInMediumAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInMinimalAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInRightAnimator;
import com.gthr.android.animations.zooming_entrances.ZoomInUpAnimator;
import com.gthr.android.animations.zooming_exits.ZoomOutAnimator;
import com.gthr.android.animations.zooming_exits.ZoomOutDownAnimator;
import com.gthr.android.animations.zooming_exits.ZoomOutLeftAnimator;
import com.gthr.android.animations.zooming_exits.ZoomOutRightAnimator;
import com.gthr.android.animations.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    DropOut(DropOutAnimator.class),
    Landing(LandingAnimator.class),
    TakingOff(TakingOffAnimator.class),

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    Hinge(HingeAnimator.class),
    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    ExpandingVertical(ExpandingVerticalAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    ShrinkingVertical(ShrinkingVerticalAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInMinimal(ZoomInMinimalAnimator.class),
    ZoomInMedium(ZoomInMediumAnimator.class),
    ZoomInMaximal(ZoomInMaximalAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);




    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
