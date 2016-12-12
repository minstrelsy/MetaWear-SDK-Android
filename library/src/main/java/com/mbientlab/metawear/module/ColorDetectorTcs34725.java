/*
 * Copyright 2014-2015 MbientLab Inc. All rights reserved.
 *
 * IMPORTANT: Your use of this Software is limited to those specific rights granted under the terms of a software
 * license agreement between the user who downloaded the software, his/her employer (which must be your
 * employer) and MbientLab Inc, (the "License").  You may not use this Software unless you agree to abide by the
 * terms of the License which can be found at www.mbientlab.com/terms.  The License limits your use, and you
 * acknowledge, that the Software may be modified, copied, and distributed when used in conjunction with an
 * MbientLab Inc, product.  Other than for the foregoing purpose, you may not use, reproduce, copy, prepare
 * derivative works of, modify, distribute, perform, display or sell this Software and/or its documentation for any
 * purpose.
 *
 * YOU FURTHER ACKNOWLEDGE AND AGREE THAT THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, TITLE,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT SHALL MBIENTLAB OR ITS LICENSORS BE LIABLE OR
 * OBLIGATED UNDER CONTRACT, NEGLIGENCE, STRICT LIABILITY, CONTRIBUTION, BREACH OF WARRANTY, OR OTHER LEGAL EQUITABLE
 * THEORY ANY DIRECT OR INDIRECT DAMAGES OR EXPENSES INCLUDING BUT NOT LIMITED TO ANY INCIDENTAL, SPECIAL, INDIRECT,
 * PUNITIVE OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY,
 * SERVICES, OR ANY CLAIMS BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE THEREOF), OR OTHER SIMILAR COSTS.
 *
 * Should you have any questions regarding your right to use this Software, contact MbientLab via email:
 * hello@mbientlab.com.
 */

package com.mbientlab.metawear.module;

import com.mbientlab.metawear.ForcedDataProducer;
import com.mbientlab.metawear.MetaWearBoard.Module;

import java.util.Locale;

/**
 * Controls the TCS34725 color detector adc data
 * @author Eric Tsai
 */
public interface ColorDetectorTcs34725 extends Module {
    /**
     * Analog gain scales
     * @author Eric Tsai
     */
    enum Gain {
        TCS34725_1X,
        TCS34725_4X,
        TCS34725_16X,
        TCS34725_60X
    }

    /**
     * Interface for configuring the color detector
     * @author Eric Tsai
     */
    interface ConfigEditor {
        /**
         * Set the integration time.  This impacts both resolution and sensitivity of the adc values.
         * @param time    Between [2.4, 614.4] milliseconds
         * @return Calling object
         */
        ConfigEditor integrationTime(float time);
        /**
         * Sets the analog gain
         * @param gain    Gain scale
         * @return Calling object
         */
        ConfigEditor gain(Gain gain);
        /**
         * Enable or disable the illuminator LED
         * @param enable    True if it should be used
         * @return Calling object
         */
        ConfigEditor illuminatorLed(boolean enable);
        /**
         * Write the changes to the board
         */
        void commit();
    }
    /**
     * Configure the color detector
     *
     * @return Editor object to configure the detector
     */
    ConfigEditor configure();

    /**
     * Wrapper class encapsulating adc data from the sensor
     * @author Eric Tsai
     */
    final class ColorAdc {
        public final int clear, red, green, blue;

        public ColorAdc(int clear, int red, int green, int blue) {
            this.clear = clear;
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        @Override
        public String toString() {
            return String.format(Locale.US, "{clear: %d, red: %d, green: %d, blue: %d}", clear, red, green, blue);
        }

        @Override
        public boolean equals(Object o) {
            ///< Generated by IntelliJ

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ColorAdc colorAdc = (ColorAdc) o;

            return clear == colorAdc.clear && red == colorAdc.red && green == colorAdc.green && blue == colorAdc.blue;

        }

        @Override
        public int hashCode() {
            ///< Generated by IntelliJ

            int result = clear;
            result = 31 * result + red;
            result = 31 * result + green;
            result = 31 * result + blue;
            return result;
        }
    }
    /**
     * Extension of the {@link ForcedDataProducer} interface providing names for the component values
     * of the color adc data
     * @author Eric Tsai
     */
    interface ColorAdcDataProducer extends ForcedDataProducer {
        /**
         * Get the name for clear adc data
         * @return Clear adc data name
         */
        String clearName();
        /**
         * Get the name for red adc data
         * @return Red adc data name
         */
        String redName();
        /**
         * Get the name for green adc data
         * @return Green adc data name
         */
        String greenName();
        /**
         * Get the name for blue adc data
         * @return Blue adc data name
         */
        String blueName();
    }
    /**
     * Gets an object to manage the adc data from the color detector
     * @return Object managing the adc data
     */
    ColorAdcDataProducer adc();
}
