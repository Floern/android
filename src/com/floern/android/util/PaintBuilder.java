/* 
 * Floern, dev@floern.com, 2016, MIT Licence
 */
package com.floern.android.util;

import android.annotation.TargetApi;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.ColorInt;

import java.util.Locale;

/**
 * A utility class to create a <code>Paint</code> using the builder pattern.
 *
 * @author Floern
 */
public class PaintBuilder {

	private final Paint paint;


	/**
	 * Create a new PaintBuilder based on a default Paint.
	 */
	public PaintBuilder() {
		paint = new Paint();
	}


	/**
	 * Wrap a PaintBuilder around an existing Paint instance.
	 *
	 * @param source a Paint
	 */
	public PaintBuilder(Paint source) {
		paint = source;
	}


	/**
	 * Set the paint's flags. Use the Flag enum to specific flag values.
	 *
	 * @param flags The new flag bits for the paint
	 */
	public PaintBuilder setFlags(int flags) {
		paint.setFlags(flags);
		return this;
	}


	/**
	 * Set the paint's hinting mode.  May be either
	 * {@link Paint#HINTING_OFF} or {@link Paint#HINTING_ON}.
	 */
	public PaintBuilder setHinting(int mode) {
		paint.setHinting(mode);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the ANTI_ALIAS_FLAG bit
	 * AntiAliasing smooths out the edges of what is being drawn, but is has
	 * no impact on the interior of the shape. See setDither() and
	 * setFilterBitmap() to affect how colors are treated.
	 *
	 * @param aa true to set the antialias bit in the flags, false to clear it
	 */
	public PaintBuilder setAntiAlias(boolean aa) {
		paint.setAntiAlias(aa);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the DITHER_FLAG bit
	 * Dithering affects how colors that are higher precision than the device
	 * are down-sampled. No dithering is generally faster, but higher precision
	 * colors are just truncated down (e.g. 8888 -> 565). Dithering tries to
	 * distribute the error inherent in this process, to reduce the visual
	 * artifacts.
	 *
	 * @param dither true to set the dithering bit in flags, false to clear it
	 */
	public PaintBuilder setDither(boolean dither) {
		paint.setDither(dither);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the LINEAR_TEXT_FLAG bit
	 *
	 * @param linearText true to set the linearText bit in the paint's flags,
	 * false to clear it.
	 */
	public PaintBuilder setLinearText(boolean linearText) {
		paint.setLinearText(linearText);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the SUBPIXEL_TEXT_FLAG bit
	 *
	 * @param subpixelText true to set the subpixelText bit in the paint's
	 * flags, false to clear it.
	 */
	public PaintBuilder setSubpixelText(boolean subpixelText) {
		paint.setSubpixelText(subpixelText);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the UNDERLINE_TEXT_FLAG bit
	 *
	 * @param underlineText true to set the underlineText bit in the paint's
	 * flags, false to clear it.
	 */
	public PaintBuilder setUnderlineText(boolean underlineText) {
		paint.setUnderlineText(underlineText);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the STRIKE_THRU_TEXT_FLAG bit
	 *
	 * @param strikeThruText true to set the strikeThruText bit in the paint's
	 * flags, false to clear it.
	 */
	public PaintBuilder setStrikeThruText(boolean strikeThruText) {
		paint.setStrikeThruText(strikeThruText);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the FAKE_BOLD_TEXT_FLAG bit
	 *
	 * @param fakeBoldText true to set the fakeBoldText bit in the paint's
	 * flags, false to clear it.
	 */
	public PaintBuilder setFakeBoldText(boolean fakeBoldText) {
		paint.setFakeBoldText(fakeBoldText);
		return this;
	}


	/**
	 * Helper for setFlags(), setting or clearing the FILTER_BITMAP_FLAG bit.
	 * Filtering affects the sampling of bitmaps when they are transformed.
	 * Filtering does not affect how the colors in the bitmap are converted into
	 * device pixels. That is dependent on dithering and xfermodes.
	 *
	 * @param filter true to set the FILTER_BITMAP_FLAG bit in the paint's
	 * flags, false to clear it.
	 */
	public PaintBuilder setFilterBitmap(boolean filter) {
		paint.setFilterBitmap(filter);
		return this;
	}


	/**
	 * Set the paint's style, used for controlling how primitives'
	 * geometries are interpreted (except for drawBitmap, which always assumes
	 * Fill).
	 *
	 * @param style The new style to set in the paint
	 */
	public PaintBuilder setStyle(Paint.Style style) {
		paint.setStyle(style);
		return this;
	}


	/**
	 * Set the paint's color. Note that the color is an int containing alpha
	 * as well as r,g,b. This 32bit value is not premultiplied, meaning that
	 * its alpha can be any value, regardless of the values of r,g,b.
	 * See the Color class for more details.
	 *
	 * @param color The new color (including alpha) to set in the paint.
	 */
	public PaintBuilder setColor(@ColorInt int color) {
		paint.setColor(color);
		return this;
	}


	/**
	 * Helper to setColor(), that only assigns the color's alpha value,
	 * leaving its r,g,b values unchanged. Results are undefined if the alpha
	 * value is outside of the range [0..255]
	 *
	 * @param a set the alpha component [0..255] of the paint's color.
	 */
	public PaintBuilder setAlpha(int a) {
		paint.setAlpha(a);
		return this;
	}


	/**
	 * Helper to setColor(), that takes a,r,g,b and constructs the color int
	 *
	 * @param a The new alpha component (0..255) of the paint's color.
	 * @param r The new red component (0..255) of the paint's color.
	 * @param g The new green component (0..255) of the paint's color.
	 * @param b The new blue component (0..255) of the paint's color.
	 */
	public PaintBuilder setARGB(int a, int r, int g, int b) {
		paint.setARGB(a, r, g, b);
		return this;
	}


	/**
	 * Set the width for stroking.
	 * Pass 0 to stroke in hairline mode.
	 * Hairlines always draws a single pixel independent of the canva's matrix.
	 *
	 * @param width set the paint's stroke width, used whenever the paint's
	 * style is Stroke or StrokeAndFill.
	 */
	public PaintBuilder setStrokeWidth(float width) {
		paint.setStrokeWidth(width);
		return this;
	}


	/**
	 * Set the paint's stroke miter value. This is used to control the behavior
	 * of miter joins when the joins angle is sharp. This value must be >= 0.
	 *
	 * @param miter set the miter limit on the paint, used whenever the paint's
	 * style is Stroke or StrokeAndFill.
	 */
	public PaintBuilder setStrokeMiter(float miter) {
		paint.setStrokeMiter(miter);
		return this;
	}


	/**
	 * Set the paint's Cap.
	 *
	 * @param cap set the paint's line cap style, used whenever the paint's
	 * style is Stroke or StrokeAndFill.
	 */
	public PaintBuilder setStrokeCap(Paint.Cap cap) {
		paint.setStrokeCap(cap);
		return this;
	}


	/**
	 * Set the paint's Join.
	 *
	 * @param join set the paint's Join, used whenever the paint's style is
	 * Stroke or StrokeAndFill.
	 */
	public PaintBuilder setStrokeJoin(Paint.Join join) {
		paint.setStrokeJoin(join);
		return this;
	}


	/**
	 * Set or clear the shader object.
	 * <p/>
	 * Pass null to clear any previous shader.
	 *
	 * @param shader May be null. the new shader to be installed in the paint
	 */
	public PaintBuilder setShader(Shader shader) {
		paint.setShader(shader);
		return this;
	}


	/**
	 * Set or clear the paint's colorfilter.
	 *
	 * @param filter May be null. The new filter to be installed in the paint
	 */
	public PaintBuilder setColorFilter(ColorFilter filter) {
		paint.setColorFilter(filter);
		return this;
	}


	/**
	 * Set or clear the xfermode object.
	 * <p/>
	 * Pass null to clear any previous xfermode.
	 *
	 * @param xfermode May be null. The xfermode to be installed in the paint
	 */
	public PaintBuilder setXfermode(Xfermode xfermode) {
		paint.setXfermode(xfermode);
		return this;
	}


	/**
	 * Set or clear the patheffect object.
	 * <p/>
	 * Pass null to clear any previous patheffect.
	 *
	 * @param effect May be null. The patheffect to be installed in the paint
	 */
	public PaintBuilder setPathEffect(PathEffect effect) {
		paint.setPathEffect(effect);
		return this;
	}


	/**
	 * Set or clear the maskfilter object.
	 * <p/>
	 * Pass null to clear any previous maskfilter.
	 *
	 * @param maskfilter May be null. The maskfilter to be installed in the
	 * paint
	 */
	public PaintBuilder setMaskFilter(MaskFilter maskfilter) {
		paint.setMaskFilter(maskfilter);
		return this;
	}


	/**
	 * Set or clear the typeface object.
	 * <p/>
	 * Pass null to clear any previous typeface.
	 *
	 * @param typeface May be null. The typeface to be installed in the paint
	 */
	public PaintBuilder setTypeface(Typeface typeface) {
		paint.setTypeface(typeface);
		return this;
	}


	/**
	 * This draws a shadow layer below the main layer, with the specified
	 * offset and color, and blur radius. If radius is 0, then the shadow
	 * layer is removed.
	 * <p/>
	 * Can be used to create a blurred shadow underneath text. Support for use
	 * with other drawing operations is constrained to the software rendering
	 * pipeline.
	 * <p/>
	 * The alpha of the shadow will be the paint's alpha if the shadow color is
	 * opaque, or the alpha from the shadow color if not.
	 */
	public PaintBuilder setShadowLayer(float radius, float dx, float dy, int shadowColor) {
		paint.setShadowLayer(radius, dx, dy, shadowColor);
		return this;
	}


	/**
	 * Set the paint's text alignment. This controls how the
	 * text is positioned relative to its origin. LEFT align means that all of
	 * the text will be drawn to the right of its origin (i.e. the origin
	 * specifieds the LEFT edge of the text) and so on.
	 *
	 * @param align set the paint's Align value for drawing text.
	 */
	public PaintBuilder setTextAlign(Paint.Align align) {
		paint.setTextAlign(align);
		return this;
	}


	/**
	 * Set the text locale.
	 * <p/>
	 * The text locale affects how the text is drawn for some languages.
	 * <p/>
	 * For example, if the locale is {@link Locale#CHINESE} or {@link Locale#CHINA},
	 * then the text renderer will prefer to draw text using a Chinese font. Likewise,
	 * if the locale is {@link Locale#JAPANESE} or {@link Locale#JAPAN}, then the text
	 * renderer will prefer to draw text using a Japanese font.
	 * <p/>
	 * This distinction is important because Chinese and Japanese text both use many
	 * of the same Unicode code points but their appearance is subtly different for
	 * each language.
	 * <p/>
	 * By default, the text locale is initialized to the system locale (as returned
	 * by {@link Locale#getDefault}). This assumes that the text to be rendered will
	 * most likely be in the user's preferred language.
	 * <p/>
	 * If the actual language of the text is known, then it can be provided to the
	 * text renderer using this method. The text renderer may attempt to guess the
	 * language script based on the contents of the text to be drawn independent of
	 * the text locale here. Specifying the text locale just helps it do a better
	 * job in certain ambiguous cases
	 *
	 * @param locale the paint's locale value for drawing text, must not be null.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public PaintBuilder setTextLocale(Locale locale) {
		paint.setTextLocale(locale);
		return this;
	}


	/**
	 * Set the paint's elegant height metrics flag. This setting selects font
	 * variants that have not been compacted to fit Latin-based vertical
	 * metrics, and also increases top and bottom bounds to provide more space.
	 *
	 * @param elegant set the paint's elegant metrics flag for drawing text.
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PaintBuilder setElegantTextHeight(boolean elegant) {
		paint.setElegantTextHeight(elegant);
		return this;
	}


	/**
	 * Set the paint's text size. This value must be > 0
	 *
	 * @param textSize set the paint's text size.
	 */
	public PaintBuilder setTextSize(float textSize) {
		paint.setTextSize(textSize);
		return this;
	}


	/**
	 * Set the paint's horizontal scale factor for text. The default value
	 * is 1.0. Values > 1.0 will stretch the text wider. Values < 1.0 will
	 * stretch the text narrower.
	 *
	 * @param scaleX set the paint's scale in X for drawing/measuring text.
	 */
	public PaintBuilder setTextScaleX(float scaleX) {
		paint.setTextScaleX(scaleX);
		return this;
	}


	/**
	 * Set the paint's horizontal skew factor for text. The default value
	 * is 0. For approximating oblique text, use values around -0.25.
	 *
	 * @param skewX set the paint's skew factor in X for drawing text.
	 */
	public PaintBuilder setTextSkewX(float skewX) {
		paint.setTextSkewX(skewX);
		return this;
	}


	/**
	 * Set the paint's letter-spacing for text. The default value
	 * is 0.  The value is in 'EM' units.  Typical values for slight
	 * expansion will be around 0.05.  Negative values tighten text.
	 *
	 * @param letterSpacing set the paint's letter-spacing for drawing text.
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PaintBuilder setLetterSpacing(float letterSpacing) {
		paint.setLetterSpacing(letterSpacing);
		return this;
	}


	/**
	 * Set font feature settings.
	 * <p/>
	 * The format is the same as the CSS font-feature-settings attribute:
	 * http://dev.w3.org/csswg/css-fonts/#propdef-font-feature-settings
	 *
	 * @param settings the font feature settings string to use, may be null.
	 */
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PaintBuilder setFontFeatureSettings(String settings) {
		paint.setFontFeatureSettings(settings);
		return this;
	}


	/**
	 * Get the Paint.
	 *
	 * @return built Paint instance
	 */
	public Paint build() {
		return paint;
	}


}
